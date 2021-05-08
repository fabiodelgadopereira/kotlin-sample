### Autor: Fábio Delgado

Olá! Seja bem vindo ;)


## Índice
1. [SpringBootApp](#SpringBootApp)
2. [Projeto e Conteúdo](#Projeto-e-Conteudo)
3. [Swagger](#Swagger)
4. [JWT](#JWT)
5. [SQL Server e Hibernate](#SQL-Server-e-Hibernate)
6. [SMTP](#SMTP)
7. [Testes unitários (jUnit e JaCoCo)](#Testes-unitarios-jUnit-e-JaCoCo))
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