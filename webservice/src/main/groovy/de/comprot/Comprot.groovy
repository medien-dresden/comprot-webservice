package de.comprot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ImportResource
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@EnableAutoConfiguration
@ImportResource("spring/root-context.xml")
class Comprot extends WebMvcConfigurerAdapter {

    public static void main(String[] args) { SpringApplication.run(Comprot.class, args) }

}