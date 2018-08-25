# Simulador de Saque

## Introducao

O aplicativo Saque Notas é um sistema que simula a operação de saque em um caixa eletrônico. O cliente faz o acesso ao terminal, solicita um valor para saque e o caixa deve disponibilizar a menor quantidade de notas para suprir o valor solicitado.

## Requerimentos

* Windows 7 ou Superior
* Java SE Development Kit (JDK)  - Download http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html
* Netbeans 8.2 - Download https://netbeans.org/downloads/start.html?platform=windows&lang=en&option=all
* MySQL Server 8 - Download https://dev.mysql.com/downloads/file/?id=479669

## Instruções de Instação

### 1. Java Runtime Environment 8 (JDK 8)

Instalando o JDK no Windows
```
- Executar o arquivo de instação do JDK
- Selecionar o local de instação para o JDK
- Avançar todos os passos seguintes até concluir a instalação
```

Configurando variaveis de  Java JDK

```
- Acessar o painel de controle do windows
- Clicar no item Sistema
- Acessar o caminho "Configurações avançadas do Sistema", guia avançados, variaveis de ambiente
- Na guia avançados, clicar em novo, inserir em nome a variavel "JAVA_HOME" e valor inserir o caminho "C:\[diretorio de instalação do java]\[versão do java jdk]" e depois clicar em "ok"
- Selecionar a variavel path e inserir no final da linha o valor ";%JAVA_HOME%\bin" e confirmar clicando em "ok".

- Para Verificar se está tudo correto, entrar no terminal de comando do Windows e digitar o comando "java -version", após apertar o "enter" deverá aparecer a versão do java instalada no sistema como mostrado abaixo
```

```
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_101-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.101-b13, mixed mode)
```

### 2. IDE de Desenvolvimento NetBeans

Instalando  o netBeans no Windows

```
- Executar o arquivo de Instalação do NetBeans.
- Clicar em avançar, avançar novamente e instalar.
- Esperar concluir a instalação
```

### 3. Servidor de Banco de Dados MySQL

Instalando o MySQL no Windows

```
- Executar o arquivo de Instalação do MySQL.
- Clicar em [avançar], aceitar os termos de instação e [avançar].
- Será solicitados os modulos do MySQL que o usuário deseja instalar, Podendo selecionar somente o serviço de banco de dados ou ferramentas adicionais como o aplicativo administrador MySQL.
- Clicar em [avançar] e [executar].
```

Configuração do Serviço MySQL

```
- Após a instalação do servidor MySQL será solicitado a configuração do tipo da instancia do serviço.
- O padrão de instancia mais simples e comum é [StandAlone MySQL Server], vamos selecionar esse tipo e clicar em [avançar].
- Na tela de configuração das propriedades de rede que será utilizado para acessar o serviço MySQL, manter o padrão previamente selecionado, e caso seja necessário iremos somente escolher a porta para que sera utilizada para acessar o banco.
- Clicar em [avançar].
- Em tipo de autenticação deixar o padrão [Usar senhas fortes criptografadas para autenticação] e clicar em avançar.
- Informar a senha para o usuário administrador Root, clicar em [avançar]
- Na tela de criação do [Serviço Windows] para o MySQL, deixar a configuração padrão e clicar em [avançar].
- Clicar em Executar e aguardar o termino da configuração do MYSQL.
- Clicar em [finalizar], [avançar] e [finalizar] novamente.
```

## Implantação

Esta seção descreve como deixar o ambiente preparado para executar o sistema.

Copie o conteúdo do repositorio Simulador Saque (Você pode fazer o download diretamente da página na [Github Page]) para um diretorio de sua preferência.

Acessar o diretorio com os aquivos do projeto.

>Na raiz do diretório do projeto, acessar a pasta "sql". Esta pasta contém os arquivos com a estrutura da base de dados.

```
- Abrir e executar os comandos SQL contidos no arquivo "criar_base_dados.sql".
- Abrir e executar os comandos SQL contidos no arquivo "insere_notas.sql"
```

>`Os comandos SQL podem ser executados através do aplicativo "MySQL Command Line" instalado na máquina hospedeira do servidor MySQL, ou se preferir pode ser usado um aplicativo de administração de base de dados MySQL da sua escolha.`

Após ter criado a base de dados através dos scripts SQL mencionados acima, vamos configurar o projeto no netBeans.

```
- Acessar o aplicativo NetBeans.
- Com o NetBeans aberto, clicar em arquivo, abrir projeto e selecionar o diretorio do projeto para que o NetBeans importe os arquivos fontes.
- Após ter importados os arquivos fontes do projeto, é necessário configurar a string de conexão a base de dados. Abaixo segue o caminho da classe onde se encontra os atributos de configuração para acesso ao banco.
```

>`SimuladorSaque->Source Packages->DAO->ConexaoDAO.java`
>`Caso Seja necessário, os atributos [url] , [user] e [password] devem ser editados conforme a configuração do servidor MySQL`

**Feito, o ambiente de execução do sistema já está pronto para simular o saque das notas.**


## **Simulação**

## Teste

Acesse a página do simulador de saque "http://[Endereco:porta]/SimuladorSaque"
>`Examplo: http://localhost:8080/SimuladorSaque`

**Cadastrando um cliente**

```
- A primeira etapa é cadastrar um cliente com seu saldo. Para isso clicar no icone de configuração no canto superior direito da página.
- Na página de cadastro do cliente, clicar em novo. Preencher as informações do cliente e depois clicar em salvar.
```

**Realizando um Saque**


> 1. Inserir o CPF do cliente, clicar no botão com ícone de loop ao lado do campo CPF.
> 2. Caso o cliente seja encontrado, o sistema irá mostrar o seu saldo e as notas disponíveis para saque.
> 3. Informar o valor de saque desejado e clicar no botão [sacar].
> 4. Se o saldo do cliente for superior ao valor informado, assim como as notas disponíveis conteplam a formação exata do valor solicitado, então será apresentado para o cliente as notas que serão entregues de acordo com o valor de saque informado.


#### Conferir o resultado da simulação no campo [Notas Entregues].

### por Erick Rafael
 
[//]: # (Referencias usadas no corpo MarkDown)
[Github Page]: <https://github.com/erk360/SimuladorSaque.git>
