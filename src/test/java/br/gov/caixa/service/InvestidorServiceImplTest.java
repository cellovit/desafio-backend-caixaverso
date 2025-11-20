package br.gov.caixa.service;

import br.gov.caixa.domain.entity.Investidor;
import br.gov.caixa.domain.entity.Investimento;
import br.gov.caixa.domain.entity.PerfilInvestidor;
import br.gov.caixa.domain.enums.PerfilInvestidorEnum;
import br.gov.caixa.domain.repository.InvestidorRepository;
import br.gov.caixa.domain.repository.PerfilInvestidorRepository;
import br.gov.caixa.dto.response.cliente.HistoricoInvestimentoResponseDto;
import br.gov.caixa.dto.response.cliente.PerfilRiscoResponseDto;
import br.gov.caixa.mapper.InvestimentoMapper;
import br.gov.caixa.service.impl.InvestidorServiceImpl;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import io.quarkus.test.component.QuarkusComponentTest;

//@QuarkusComponentTest(value = InvestimentoMapper.class)
@QuarkusTest
public class InvestidorServiceImplTest {

    @InjectMock
    InvestidorRepository investidorRepository;

    // https://github.com/quarkusio/quarkus/issues/47227
//    @Mock
//    InvestimentoMapper investimentoMapper = Mappers.getMapper(InvestimentoMapper.class);

    @InjectMock
    MotorRecomendacaoService motorRecomendacaoService;

    @InjectMock
    PerfilInvestidorRepository perfilInvestidorRepository;

    @Inject
    InvestidorServiceImpl investidorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testFindPerfilByClienteId_ComPerfilExistente() {

        Investidor investidor = new Investidor();
        investidor.setId(1L);

        PerfilInvestidor perfil = PerfilInvestidor.builder()
                .titulo(PerfilInvestidorEnum.CONSERVADOR)
                .pontuacao(BigDecimal.TEN)
                .investidor(investidor)
                .build();

        investidor.setPerfilInvestidor(perfil);

        when(investidorRepository.findByIdOptional(1L)).thenReturn(Optional.of(investidor));

        PerfilRiscoResponseDto response = investidorService.findPerfilByClienteId(1L);

        assertEquals(1L, response.clienteId());
        assertEquals(PerfilInvestidorEnum.CONSERVADOR.getTitulo(), response.perfilInvestidor());
        verify(investidorRepository, times(1)).findByIdOptional(1L);
        verifyNoInteractions(motorRecomendacaoService); // não deve calcular perfil
    }

    @Test
    public void testFindPerfilByClienteId_SemPerfilCalculaNovo() {
        Investidor investidor = new Investidor();
        investidor.setId(2L);
        investidor.setInvestimentos(List.of()); // sem investimentos

        when(investidorRepository.findByIdOptional(2L)).thenReturn(Optional.of(investidor));
        when(motorRecomendacaoService.calcularPerfil(2L)).thenReturn(PerfilInvestidorEnum.MODERADO);

        PerfilRiscoResponseDto response = investidorService.findPerfilByClienteId(2L);

        assertEquals(2L, response.clienteId());
        assertEquals(PerfilInvestidorEnum.MODERADO.getTitulo(), response.perfilInvestidor());

        verify(perfilInvestidorRepository, times(1)).persist(any(PerfilInvestidor.class));
        verify(investidorRepository, times(1)).persist(investidor);
    }

    @Test
    public void testGetHistoricoInvestimentos() {
        Investidor investidor = new Investidor();
        investidor.setId(3L);

        Investimento inv = new Investimento();
        inv.setTitulo("CDB Banco XP");
        investidor.setInvestimentos(List.of(inv));

        HistoricoInvestimentoResponseDto dto = new HistoricoInvestimentoResponseDto(1L, "CDB Banco XP", BigDecimal.ONE, BigDecimal.ONE, LocalDate.now());

        when(investidorRepository.findByIdOptional(3L)).thenReturn(Optional.of(investidor));
//        when(investimentoMapper.toHistoricoInvestimentoResponseDto(any())).thenReturn(dto);

        List<HistoricoInvestimentoResponseDto> result = investidorService.getHistoricoInvestimentos(3L);

        assertEquals(1, result.size());
//        assertEquals("CDB Banco XP", result.get(0).tipo());
//        verify(investimentoMapper, times(1)).toHistoricoInvestimentoResponseDto(inv);
    }
}

