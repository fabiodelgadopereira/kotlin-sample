### Autor: Fábio Delgado

Olá! Seja bem vindo ;)


## Índice
1. [SpringBootApp](#SpringBootApp)
2. [Projeto e Conteúdo](#Projeto-e-Conteudo)
3. [Swagger](#Swagger)
4. [Auntenticação com JWT](#Auntentica--o-com-JWT)
5. [SQL Server e Hibernate](#SQL-Server-e-Hibernate)
6. [SMTP](#SMTP)
7. [Testes unitários (jUnit)](#Testes-unitarios-jUnit))
8. [Publicação](#Publicação)
9. [Suporte](#Suporte)

## KotlinApp

Este repositório contém uma implementação que orientará você na criação de um aplicativo de básico contendo autenticação, documentação e integração com banco de dados.

### Pre-requisitos

JDK 1.8 +
Maven 3.0 +

### Como executar essa aplicação?

 - Faça o download do zip ou clone o repositório Git.
 - Descompacte o arquivo zip (caso tenha baixado o .zip)
 - Abra o diretório Prompt de Comando e Altere (cd) para a pasta que contém pom.xml
 - Abra o visual code ou execute o comando via prompt `code .`
 - Pressione `F5` para executar a aplicação.

A aplicação deverá estar disponivel em seu navegador no endereço: http://localhost:8080/swagger-ui/index.html

![swagger](/assets/swagger.png)

### Entedento a estrutura de projeto

![ini](/assets/ini.png)

## Swagger

O Swagger é uma aplicação open source que auxilia os desenvolvedores a definir, criar, documentar e consumir APIs REST;
É composto de um arquivo de configuração, que pode ser definido em YAML ou JSON;
Fornece ferramentas para: auxiliar na definição do arquivo de configuração (Swagger Editor), interagir com API através das definições do arquivo de configuração (Swagger UI) e gerar templates de código a partir do arquivo de configuração (Swagger Codegen).

fonte: https://swagger.io/resources/webinars/getting-started-with-swagger/

> A maneira mais fácil de instalar é usar o Maven:
`pom.xml`
```xml
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-boot-starter</artifactId>
	<version>3.0.0</version>
</dependency>
```
> Exemplo de implementação em `config/ApplicationConfig`

```kotlin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class ApplicationConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun getApiInfo(): ApiInfo {
        return ApiInfoBuilder()
                .title("Kotlin Api Sample")
                .build()
    }
}
```
## Auntenticação com JWT

O JWT (JSON Web Token) nada mais é que um padrão (RFC-7519) de mercado que define como transmitir e armazenar objetos JSON de forma simples, compacta e segura entre diferentes aplicações, muito utilizado para validar serviços em Web Services pois os dados contidos no token gerado pode ser validado a qualquer momento uma vez que ele é assinado digitalmente.

JSON Web Tokens (JWT) é um padrão stateless porque o servidor autorizador não precisa manter nenhum estado; o próprio token é sulficiente para verificar a autorização de um portador de token.

Os JWTs são assinados usando um algoritmo de assinatura digital (por exemplo, RSA) que não pode ser forjado. Por isso, qualquer pessoa que confie no certificado do assinante pode confiar com segurança que o JWT é autêntico. Não há necessidade de um servidor consultar o servidor emissor de token para confirmar sua autenticidade.

fonte: https://jwt.io/introduction/

> Exemplo de chamada curl:
```bash
curl -X POST "http://localhost:8080/login" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"username\": \"admin.admin\", \"password\": \"test1234\"}"
```

> A maneira mais fácil de instalar é usar o Maven:

`pom.xml`
```xml
<properties>
   <java.version>11</java.version>
   <kotlin.version>1.4.21</kotlin.version>
   <jjwt.version>0.11.2</jjwt.version>
</properties>
	<dependencies>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>${jjwt.version}</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>${jjwt.version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>${jjwt.version}</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-validator</artifactId>
			<version>6.2.0.Final</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>com.fasterxml.jackson.module</groupId>
			<artifactId>jackson-module-kotlin</artifactId>
					<compilerPlugins>
						<plugin>spring</plugin>
						<plugin>jpa</plugin>
					</compilerPlugins>
			<artifactId>kotlin-maven-allopen</artifactId>
			<version>${kotlin.version}</version>
		</dependency>
		<dependency>
			<groupId>org.jetbrains.kotlin</groupId>
			<artifactId>kotlin-maven-noarg</artifactId>
			<version>${kotlin.version}</version>
		</dependency>
	</dependencies>
```

Como foram muitas alterações, segue a lista de arquivos que envolvem a utilização de SMTP nesse exemplo:

    src/main/kotlin/com/example/demo/config/AppConfiguration.kt 
    src/main/kotlin/com/example/demo/config/ApplicationConfig.kt 
    src/main/kotlin/com/example/demo/config/SecurityProperties.kt 
    src/main/kotlin/com/example/demo/config/WebConfig.kt 
    src/main/kotlin/com/example/demo/db/AppUserDetailsService.kt 
    src/main/kotlin/com/example/demo/db/UserRepository.kt 
    src/main/kotlin/com/example/demo/model/Role.kt 
    src/main/kotlin/com/example/demo/model/User.kt 
    src/main/kotlin/com/example/demo/security/JWTAuthenticationFilter.kt 
    src/main/kotlin/com/example/demo/security/JWTAuthorizationFilter.kt 
    src/main/resources/application.properties 

## SQL Server e Hibernate

O mapeamento objeto-relacional foi criado para abstrair as diferenças entre o modelo relacional e o paradigma orientado a objetos. Assim, deixa de ser necessário criarmos soluções com o intuito de converter dados em objetos e vice-versa. Em Java, após a especificação JPA, isso passou a ser feito pelos frameworks que a implementam, como o Hibernate, EclipseLink e OpenJPA. A nós, desenvolvedores, basta fazer uso das anotações disponibilizadas pela JPA para viabilizar o mapeamento, evitando dessa forma criar uma forte dependência com alguma implementação. (fonte devmedia)

> Configurando o ambiente TCP para SQL Server

1. No menu **Iniciar**, abra o **SQL Server 2014 Configuration Manager**.
2. Clique em **Protocolo para SQLEXPRESS** em **SQL Server Network Configuration** no painel esquerdo. No painel direito, clique com o botão direito do mouse em **TCP / IP** e selecione **Propriedades**.
3. Na caixa de diálogo Propriedades de **TCP / IP**, clique na guia **Endereços IP**.
4. Role para baixo para localizar o **IPAL**L. Remova qualquer valor, se presente, para **portas dinâmicas TCP** e especifique **1433** para porta **TCP port**.

![TCPIP_Propertie](/assets/TCPIP_Propertie.png)

5. Clique OK.
6. Novamente, clique com o botão direito do mouse em **TCP / IP** no painel direito e selecione **Ativar**.
7. No Serviços do SQL Server, clique com o botão direito do mouse em **SQL Server (SQLEXPRESS)** e selecione **Reiniciar**.

> Firewall

 - Para validar se a porta do servido esta liberada, execute o comando `telnet localhost 1433`
 - Para validar se o servico esta no ar, execute o comando `sc query mssqlserver`
 - Para validar as conexoes na porta, execute o commando `netstat -ano | find "1433"`
 
> A maneira mais fácil de instalar é usar o Maven:

`pom.xml`
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
	<groupId>com.microsoft.sqlserver</groupId>
	<artifactId>mssql-jdbc</artifactId>
	<scope>runtime</scope>
</dependency>
<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-core</artifactId>
	<version>5.4.31.Final</version>
</dependency>
<dependency>
	<groupId>org.hibernate</groupId>
	<artifactId>hibernate-entitymanager</artifactId>
	<version>5.4.31.Final</version>
</dependency>
```

> Exemplo de implementação para testes

`application.properties`
```kotlin
spring.datasource.url=jdbc:sqlserver://localhost;databaseName=database
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
spring.jpa.show-sql=true
spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
security.ignored=none
```

`model\modelCliente`
```kotlin
import javax.persistence.*

@Entity
@Table(name="tablename", catalog="databasename", schema="dbo")
class Cliente(
        var nome: String = "",
        var cidade: String = "",
        var email: String = "",
        var sexo: String = "",
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)
```

`controller\ClienteController`
```kotlin
import com.example.demo.db.ClienteRepository
import com.example.demo.model.Cliente
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/Cliente")
class ClienteController(val repository: ClienteRepository) {

    @GetMapping
    fun findAll() = repository.findAll()

    @PostMapping
    fun addCliente(@RequestBody Cliente: Cliente)
            = repository.save(Cliente)

    @PutMapping("/{id}")
    fun updateCliente(@PathVariable id: Long, @RequestBody Cliente: Cliente) {
        assert(Cliente.id == id)
        repository.save(Cliente)
    }

    @DeleteMapping("/{id}")
    fun removeCliente(@PathVariable id: Long)
            = repository.deleteById(id)

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long)
            = repository.findById(id)
}
```

`db\ClienteRepository`
```kotlin
package com.example.demo.db

import com.example.demo.model.Cliente
import org.springframework.data.repository.CrudRepository


interface ClienteRepository : CrudRepository<Cliente, Long> {
    fun findById(name: String): List<Cliente>
}
```

## SMTP

O SMTP ou Simple Mail Transfer Protocol, é uma convenção padrão dedicada ao envio de e-mail. A princípio o protocolo SMTP utilizava por padrão a porta 25 ou 465 (conexão criptografada) para conexão, porém a partir de 2013 os provedores de internet e as operadoras do Brasil passaram a bloquear a porta 25, e começaram a usar a porta 587 para diminuir a quantidade de SPAM. O SMTP é um protocolo que faz apenas o envio de e-mails, isso significa que o usuário não tem permissão para baixar as mensagens do servidor, nesse caso é necessário utilizar um Client de e-mail que suporte os protocolos POP3 ou IMAP como o Outlook, Thunderbird e etc. Para negócios ou empresas pequenas com baixo volume de e-mails, o servidor SMTP gratuito do Google pode ser uma ótima solução e você pode usar o Gmail para enviar o seu e-mail. Eles possuem uma infraestrutura gigante e você pode confiar nos serviços deles para ficar online. Porém, mesmo sendo completamente grátis, tudo tem um limite. De acordo com a documentação do Google, você pode enviar até 100 e-mails a cada período de 24 horas quando envia através do servidor SMTP deles.  Ou você também pode pensar nisso como sendo 3 mil e-mails por mês gratuitamente.Dependendo de quantos e-mails você envia ou do tamanho do seu negócio, isto pode ser mais do que suficiente. Se você envia mais de 5 mil e-mails por mês, você vai preferir usar um serviço de e-mail transacional de terceiros ou um serviço premium.

![ini](/assets/smtp.png)
> A maneira mais fácil de instalar é usar o Maven:

`pom.xml`
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
			<version>2.4.5</version>
		</dependency>

		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-support</artifactId>
			<version>5.3.7</version>
		</dependency>
```

Como foram muitas alterações, segue a lista de arquivos que envolvem a utilização de SMTP nesse exemplo:


    src/main/kotlin/com/example/demo/config/MailSenderConfig.kt 
    src/main/kotlin/com/example/demo/config/TemplateConfig.kt 
    src/main/kotlin/com/example/demo/controller/ContatoController.kt 
    rc/main/kotlin/com/example/demo/model/Contato.kt 
    src/main/kotlin/com/example/demo/service/EmailSenderService.kt 
    src/main/resources/application.properties 
    
## Testes unitários (jUnit )

Teste de unidade é toda a aplicação de teste nas assinaturas de entrada e saída de um sistema. Consiste em validar dados válidos e inválidos via I/O (entrada/saída) sendo aplicado por desenvolvedores ou analistas de teste. Uma unidade é a menor parte testável de um programa de computador. Em programação procedural, uma unidade pode ser uma função individual ou um procedimento. Idealmente, cada teste de unidade é independente dos demais, o que possibilita ao programador testar cada módulo isoladamente.
**JUnit** é uma framework de teste de unitários para a linguagem de programação Java. JUnit tem sido importante no desenvolvimento TDD e faz parte de uma família de estruturas de teste de unidade que é coletivamente conhecida como xUnit que se originou com SUnit.

> Para instalar o JUnit utilize o commando abaixo:
`pom.xml`
```xml
<dependency>
	<groupId>org.jetbrains.kotlin</groupId>
	<artifactId>kotlin-test-junit</artifactId>
	<version>1.4.32</version>
	<scope>test</scope>
</dependency>
<dependency>
	<groupId>org.testng</groupId>
	<artifactId>testng</artifactId>
	<version>RELEASE</version>
	<scope>test</scope>
</dependency>
```
> Exemplo de classe teste:

```java
package com.example.demo

import org.junit.Test
import org.junit.Assert.assertEquals

internal  class DemoApplicationTests {

	@Test
	fun testSum() {
		val expected = 42
		assertEquals(expected, testSample.sum(40, 2))
	}
}
 ```