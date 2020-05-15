package es.uah.application

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.ComponentScan

/**
 * Definition of the main class of Spring Boot.
 */
@SpringBootApplication
@ComponentScan(basePackages = ['es.uah'])
@EnableAutoConfiguration
@EnableEurekaClient
class UserServiceApplication {

    /**
     * Main.
     * 
     * @param args Configuration.
     */
    static void main(String[] args) {
        SpringApplication.run(UserServiceApplication, args)
    }
}
