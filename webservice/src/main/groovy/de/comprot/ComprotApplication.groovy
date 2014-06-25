package de.comprot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.context.annotation.ImportResource
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@EnableAutoConfiguration(exclude = [
        DataSourceAutoConfiguration,
        DataSourceTransactionManagerAutoConfiguration
])
@ImportResource('spring/root-context.xml')
class ComprotApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) { SpringApplication.run ComprotApplication, args }

}
