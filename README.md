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
- **Swagger**
- **JUNIT / TESTES DE INTEGRACAO**
- **DOCKER / DOCKER COMPOSE**
- **HIBERNATE / JPA**
- **Interface:** ANGULAR

## Classe Pessoa

A classe `Pessoa` possui os seguintes atributos:
- `Long id`
- `String nome`
- `String cpf`
- `LocalDate dataNascimento`
- `String email`

## Configuração do Projeto

## URL DO SWAGGER

 ```http://localhost:8080/onsafety/api/swagger-ui/index.html ```

### Requisitos

- Java 17
- Spring Boot 3.x
- MySQL 8.x
- Docker (opcional, para execução em contêiner)


### Configuração do Banco de Dados Sem o Docker.


Passo 1: Certifique-se de que o MySQL está instalado e em execução. Crie um banco de dados para a aplicação caso queira optar por nao usar o docker.

 ```sql

CREATE DATABASE onsafety-db;
 ```
Passo 2: Execute os Scripts do projeto dentro da pasta docker/sql. Esse Script pode ser executado diretamente no banco mesmo nao usando o docker.

- caminho do script esta dentro do proprio projeto em /api/docker/sql/data.sql

## Script inicial de criacao da tabela EXEMPLO ---> execute o script data.sql 
```
CREATE TABLE IF NOT EXISTS pessoa (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       nome VARCHAR(100) NOT NULL,
       cpf CHAR(11) NOT NULL,
       data_nascimento DATE NOT NULL,
       email VARCHAR(100) NOT NULL,
       UNIQUE (cpf)
) ENGINE=InnoDB;
```

## Script da function validate_cpf EXEMPLO ---> execute o script data.sql 
```
DELIMITER //

CREATE FUNCTION validate_cpf(cpf CHAR(11))
    RETURNS BOOLEAN
    DETERMINISTIC
BEGIN
    DECLARE digit1, digit2, calc1, calc2, i INT;

    SET cpf = REPLACE(cpf, '.', '');
    SET cpf = REPLACE(cpf, '-', '');

    IF LENGTH(cpf) != 11 THEN
        RETURN FALSE;
    END IF;

    SET calc1 = 0;
    SET i = 1;
    WHILE i <= 9 DO
            SET calc1 = calc1 + CAST(SUBSTRING(cpf, i, 1) AS UNSIGNED) * (11 - i);
            SET i = i + 1;
        END WHILE;

    SET digit1 = (calc1 * 10) % 11;
    IF digit1 = 10 THEN
        SET digit1 = 0;
    END IF;

    SET calc2 = 0;
    SET i = 1;
    WHILE i <= 10 DO
            SET calc2 = calc2 + CAST(SUBSTRING(cpf, i, 1) AS UNSIGNED) * (12 - i);
            SET i = i + 1;
        END WHILE;

    SET digit2 = (calc2 * 10) % 11;
    IF digit2 = 10 THEN
        SET digit2 = 0;
    END IF;

    IF SUBSTRING(cpf, 10, 1) != digit1 OR SUBSTRING(cpf, 11, 1) != digit2 THEN
        RETURN FALSE;
    END IF;

    RETURN TRUE;
END //

DELIMITER ;

DELIMITER //
```
## Script da trigger insert_tb_pessoa EXEMPLO ---> execute o script data.sql 
```
CREATE TRIGGER before_insert_tb_pessoa
    BEFORE INSERT ON pessoa
    FOR EACH ROW
BEGIN
    IF NOT validate_cpf(NEW.cpf) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'CPF inválido';
    END IF;
END //

DELIMITER ;

DELIMITER //
```
## Script da trigger before_update_tb_pessoa EXEMPLO ---> execute o script data.sql 
```
CREATE TRIGGER before_update_tb_pessoa
    BEFORE UPDATE ON pessoa
    FOR EACH ROW
BEGIN
    IF NOT validate_cpf(NEW.cpf) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'CPF inválido';
    END IF;
END //

DELIMITER ;

```

### Passo 3 

- Usando o Intellij ou o Eclipse rodar o projeto no proprio starter da api ou executando o seguinte comando no graddle.

``` ./gradlew bootRun```


### Configuração do Banco de Dados Com o Docker.

OBS: Importante ter na sua maquina o docker e o docker compose instalados, recomendo usar linux, mas tambem funciona no windowns

### Passo 1

- Apos ter instalado o docker e o compose na sua maquina seguir o seguintes passos para executar de forma correta o projeto usando docker.

linux: xecutar o seguinte comando dentro do projeto execute com o terminal bash aberto dentro do projeto

```docker compose up -d```

- Apos isso execute o projeto na ide clicando em debbug ou runner da ide


Windowns: executar o seguinte comando dentro do projeto execute com o terminal aberto dentro do projeto

```docker-compose up -d```

- Apos isso execute o projeto na ide clicando em debbug ou runner da ide ou com o comando

  ``` ./gradlew bootRun```

  OBS: sera executado automaticamente os scripts de sql caso opte por usar o docker isso facilita bastante subir o projeto ja com as tabelas criadas / banco de dados.


# Como instalar o docker / docker compose

- deixarei um breve guia de como instalar em windowns e linux seguindo exatamente os passos para instalar na sua maquina.


# Instalação do Docker e Docker Compose no Windows

## Passos para Instalar o Docker Desktop

1. **Baixar o Docker Desktop:**
   - Acesse a [página de download do Docker Desktop para Windows](https://www.docker.com/products/docker-desktop/).
   - Clique em "Download for Windows (Windows 10/11)".

2. **Instalar o Docker Desktop:**
   - Execute o arquivo baixado (`Docker Desktop Installer.exe`).
   - Siga as instruções do instalador. Você pode ser solicitado a habilitar o WSL 2 (Windows Subsystem for Linux) e a instalação do Hyper-V se ainda não estiver habilitado.
   - Após a instalação, reinicie o computador se solicitado.

3. **Verificar a Instalação:**
   - Abra o Docker Desktop através do menu iniciar.
   - Para verificar se o Docker está funcionando corretamente, abra o terminal (PowerShell ou Command Prompt) e execute:
     ```sh
     docker --version
     ```
   - Para verificar se o Docker Compose está instalado, execute:
     ```sh
     docker-compose --version
     ```

4. **Configurar Docker Desktop:**
   - O Docker Desktop deve iniciar automaticamente após a instalação. Se não iniciar, você pode abri-lo manualmente a partir do menu iniciar.
   - No Docker Desktop, você pode configurar recursos como a quantidade de memória e CPUs alocadas para o Docker.

## Instalação do Docker Compose

O Docker Compose já está incluído com o Docker Desktop para Windows, então você não precisa instalá-lo separadamente. Para verificar a versão do Docker Compose, execute:
```sh
docker-compose --version

```

# Instalação do Docker e Docker Compose no Linux

## Passos para Instalar o Docker

1. **Atualizar o Repositório e Instalar Pacotes Necessários:**
   - Abra o terminal e execute:
     ```sh
     sudo apt-get update
     sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
     ```

2. **Adicionar a Chave GPG do Docker:**
   - Execute:
     ```sh
     curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
     ```

3. **Adicionar o Repositório Docker:**
   - Execute:
     ```sh
     sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
     ```

4. **Instalar o Docker:**
   - Atualize o repositório e instale o Docker:
     ```sh
     sudo apt-get update
     sudo apt-get install docker-ce
     ```

5. **Verificar a Instalação:**
   - Verifique se o Docker está funcionando:
     ```sh
     sudo systemctl status docker
     ```
   - Verifique a versão do Docker:
     ```sh
     docker --version
     ```

## Instalação do Docker Compose

1. **Baixar a Última Versão do Docker Compose:**
   - Execute:
     ```sh
     sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
     ```

2. **Dar Permissões de Execução:**
   - Execute:
     ```sh
     sudo chmod +x /usr/local/bin/docker-compose
     ```

3. **Verificar a Instalação:**
   - Verifique a versão do Docker Compose:
     ```sh
     docker compose --version
     ```

## Configurar o Docker para o Usuário Atual (Opcional)

Para usar o Docker sem `sudo`, adicione seu usuário ao grupo `docker`:
```sh
sudo usermod -aG docker $USER

  




