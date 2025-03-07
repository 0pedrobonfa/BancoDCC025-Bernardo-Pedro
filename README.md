# BancoDCC025-Bernardo-Pedro

## Descrição

Projeto em Java da disciplina DCC025.

## Pré-requisitos

- Git ([download](https://git-scm.com/))
- Java JDK 11 ou superior ([download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html))
- Maven 3.6.3 ou superior ([download](https://maven.apache.org/download.cgi))

## Instalação

###  1. Copie o repositório abaixo por uma chave ssh:
```bash
https://github.com/0pedrobonfa/BancoDCC025-Bernardo-Pedro
```
### 2. Pelo terminal, navegue para a pasta do projeto e use git clone, atribuindo sua chave ssh

```bash
cd BancoDCC025-Bernardo-Pedro
git clone (link ssh copiado)
```

### 3. Compile o projeto com o Maven

```bash
mvn clean install
```

## Execução

### Execute o código com

```bash
mvn exec:java -Dexec.mainClass="br.ufjf.dcc.dcc205.bancodcc025.BancoDCC025"
```
### Execute os testes de Unidade:

```bash
mvn test
```

