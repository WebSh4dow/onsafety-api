# Instalação do Docker e Docker Compose no Windows

## Passos para Instalar o Docker Desktop

1. **Baixar o Docker Desktop:**
   - Acesse a página de download do Docker Desktop para Windows: [Docker Desktop for Windows](https://www.docker.com/products/docker-desktop/).
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
# Instalação do Docker e Docker Compose no Windows

## Passos para Instalar o Docker Desktop

1. **Baixar o Docker Desktop:**
   - Acesse a página de download do Docker Desktop para Windows: [Docker Desktop for Windows](https://www.docker.com/products/docker-desktop/).
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

