# [Spring Quick start ](https://spring.io/quickstart)

# [Guide](https://www.javaguides.net/2022/12/spring-boot-3-crud-restful-webservices.html) 

# Setting for project
1. Create project in IDEA
2. pox.xml:
<br/>

      <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>3.0.2</version>
      </parent>

      <properties>
          <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
          <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
          <java.version>19</java.version>
      </properties>
  
<br/>

3. Download it, too in pox.xml:

<br/>

     <dependencies>
            <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web</artifactId>
                <version>3.0.2</version>
            </dependency>

            <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-thymeleaf</artifactId>
                <version>3.0.2</version>
            </dependency>

            <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <version>3.0.2</version>
                <scope>test</scope>
            </dependency>

            <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.18.26</version>
                <scope>provided</scope>
            </dependency>

            <!-- https://mvnrepository.com/artifact/org.mapstruct/mapstruct -->
            <dependency>
                <groupId>org.mapstruct</groupId>
                <artifactId>mapstruct</artifactId>
                <version>1.5.3.Final</version>
            </dependency>

            <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok-mapstruct-binding -->
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok-mapstruct-binding</artifactId>
                <version>0.2.0</version>
            </dependency>

            <!-- https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui -->
            <dependency>
                <groupId>org.springdoc</groupId>
                <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
                <version>2.0.2</version>
            </dependency>

            <!-- https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui -->
            <dependency>
                <groupId>org.springdoc</groupId>
                <artifactId>springdoc-openapi-ui</artifactId>
                <version>1.6.14</version>
            </dependency>

        </dependencies>

        <build>
            <finalName>ProjectName</finalName>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>
            </plugins>
        </build>
  

<br/>

4. Make class Main

<br/>

    package program;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

    @SpringBootApplication
    public class Main {
        public static void main(String[] args) {
            System.out.println("Hello Spring !");
            SpringApplication.run(Main.class, args);
        }
    }

<br/>

5. Make Controllers in folder main>java>program (HomeController) etc...
6. Test localhost:8080
7. (change port of server) main folder>aplication.properties
<br/>

        sever.port=8086

8. Swagger
<br/>resources>Create (aplication.properties)

            spring.api-docs.enabled=true
            spring.api-docs.path=/rest-api-docs
            spring.swagger-ui.path=/swagger-ui
            Restart project
            localhost:8086/swagger-ui/index.html
            
9. Connect DataBase
10. Repositories, Entity
<br/> https://github.com/capittan/Spring-Java/blob/main/src/main/java/program/repositories/CategoryRepository.java            
  
 11. Add CORS configuration
 <br/> program>configuration>Make class> CorsConfig
 <br/>
 
            package program.configuration;
            import org.springframework.context.annotation.Bean;
            import org.springframework.context.annotation.Configuration;
            import org.springframework.web.cors.CorsConfiguration;
            import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
            import org.springframework.web.filter.CorsFilter;

            @Configuration
            public class CorsConfig {
                @Bean
                public CorsFilter corsFilter() {
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(false); //updated to false
                    config.addAllowedOrigin("*");
                    config.addAllowedHeader("*");
                    config.addAllowedMethod("GET");
                    config.addAllowedMethod("PUT");
                    config.addAllowedMethod("POST");
                    config.addAllowedMethod("DELETE");
                    source.registerCorsConfiguration("/**", config);
                    return new CorsFilter(source);
                }
            }

12. Rebuild project 
13. Start work

