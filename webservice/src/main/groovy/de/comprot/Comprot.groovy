package de.comprot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
@ComponentScan
@EnableAutoConfiguration
class Comprot extends WebMvcConfigurerAdapter {

    static main(String[] args) {
        SpringApplication.run(Comprot.class, args);
    }

}
