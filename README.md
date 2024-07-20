# OnSafety - Projeto de Gestão de Pessoas

## Visão Geral

Estamos na quarta fase do processo seletivo para a vaga de Programador Júnior aqui no OnSafety. Esta é a última etapa do nosso processo e tem como objetivo avaliar a lógica de programação, organização do código e a forma de programar. O desafio é desenvolver uma aplicação Web para gestão de Pessoas.

## Objetivos

- Desenvolver uma aplicação Web para gestão de Pessoas.
- Disponibilizar o código completo do projeto em um repositório Git.

## Especificações

- A aplicação consiste em uma API REST, com os dados persistidos em um banco de dados MySQL.
- A aplicação deve ser capaz de auto configurar sua base na inicialização ou deve conter um README com os passos necessários para sua execução correta.
- Incluir pelo menos uma validação na interface, uma na API e uma no banco de dados.
- Validar e formatar CPF.
- Obrigatória a utilização do Spring Boot no backend e banco de dados MySQL.
- A interface pode ser Web ou Mobile, com frameworks de livre escolha do programador.

## Tecnologias Utilizadas

- **Backend:** Spring Boot
- **Banco de Dados:** MySQL
- **Interface:** Web ou Mobile (framework à escolha do programador)

## Classe Pessoa

A classe `Pessoa` possui os seguintes atributos:
- `Long id`
- `String nome`
- `String cpf`
- `LocalDate dataNascimento`
- `String email`

## Configuração do Projeto

### Requisitos

- Java 17
- Spring Boot 3.x
- MySQL 8.x
- Docker (opcional, para execução em contêiner)

### Passos para Configuração

1. **Clone o Repositório**

   ```bash
   git clone https://github.com/usuario/repo.git
   cd repo

    Configuração do Banco de Dados

        Local:

        Certifique-se de que o MySQL está instalado e em execução. Crie um banco de dados para a aplicação.

        sql

CREATE DATABASE gestao_pessoas;

Docker (opcional):

Se estiver usando Docker, você pode utilizar o docker-compose.yml incluído no projeto para configurar o MySQL.

Crie um arquivo docker-compose.yml na raiz do projeto com o seguinte conteúdo:

yaml

version: '3'
services:
  mysql:
    image: mysql:8.0
    container_name: mysql-container
    environment:
      MYSQL_ROOT_PASSWORD: root_password
      MYSQL_DATABASE: gestao_pessoas
    ports:
      - "3306:3306"
    volumes:
      - mysql-data:/var/lib/mysql

volumes:
  mysql-data:

Execute o Docker Compose para iniciar o contêiner do MySQL:

bash

    docker-compose up -d

Configuração do Projeto

    Arquivo de Propriedades:

    Edite o arquivo src/main/resources/application.properties com as informações do seu banco de dados:

    properties

    spring.datasource.url=jdbc:mysql://localhost:3306/gestao_pessoas
    spring.datasource.username=root
    spring.datasource.password=root_password
    spring.jpa.hibernate.ddl-auto=update

Executar o Projeto

Compile e execute a aplicação Spring Boot:

    Usando Maven:

    bash

./mvnw spring-boot:run

Usando Gradle:

bash

    ./gradlew bootRun

Testar a API

Utilize ferramentas como Postman ou cURL para testar as endpoints da API:

    Adicionar Pessoa:

    bash

curl -X POST http://localhost:8080/persons \
-H "Content-Type: application/json" \
-d '{
  "nome": "Jarmison Paiva",
  "cpf": "97455387016",
  "email": "jarmisondev@example.com",
  "dataNascimento": "2004-01-01"
}'

Listar Pessoas:

bash

curl http://localhost:8080/persons

Obter Pessoa por ID:

bash

curl http://localhost:8080/persons/{id}

Atualizar Pessoa:

bash

curl -X PUT http://localhost:8080/persons/{id} \
-H "Content-Type: application/json" \
-d '{
  "nome": "Jarmison Paiva",
  "cpf": "97455387016",
  "email": "jarmisondev@example.com",
  "dataNascimento": "2004-01-01"
}'
```
Deletar Pessoa:

bash

        curl -X DELETE http://localhost:8080/persons/{id}

    Validações
        Validação de Interface: Implementada na camada de frontend (web/mobile).
        Validação de API: Implementada no backend utilizando Spring Boot.
        Validação de Banco de Dados: Validação e formatação de CPF implementadas no nível da aplicação.

```
