# Desafio backend Java caixaverso

API para simulação de investimentos com motor de recomendação e cálculo de perfil de risco

* **Java 21**
* **Quarkus 3.27.0**
* **Keycloak**
* **SQL Server**

## Como executar o projeto

### Requisitos
- [x] JDK 21
- [x] Docker
- [x] maven
- [x] Postman/Insomnia

* **Gerar o pacote da aplicação**
```bash
 ./mvnw clean package
 ```
* **Subir os serviços utilizando Docker compose**
```bash
 docker-compose up --build
 ```
* **Verificar se a aplicação está pronta para responder**
```bash
 curl --location 'http://localhost:8080/q/health/ready'
 ```

### Endpoints

* `POST http://localhost:8080/simular-investimento`
* `GET http://localhost:8080/simulacoes`
* `GET http://localhost:8080/simulacoes/por-produto-dia`
* `GET http://localhost:8080/telemetria`
* `GET http://localhost:8080/perfil-risco/{clienteId}`
* `GET http://localhost:8080/produtos-recomendados/{perfil}`
* `GET http://localhost:8080/investimentos/{clienteId}`

#### Collection do Postman disponível na pasta `/postman`

#### Swagger disponível em `http://localhost:8080/q/swagger-ui/`

## Regras da simulação e do motor de recomendação

* Se a requisição nao receber o parametro de tipo de produto, faz a simulação para todos os produtos do perfil do investidor
* Se ele possuir perfil cadastrado, a API recomenda e simula produtos de acordo com perfil cadastrado
* Caso ele não possua perfil realiza a simulação através do motor de recomendação que define uma pontuação analisando os investimentos deste cliente
* Caso ele não possua perfil cadastrado e nem investimentos a API define esse cliente como um investidor **Conservador** e recomenda produtos de investimento com risco **Baixo** 