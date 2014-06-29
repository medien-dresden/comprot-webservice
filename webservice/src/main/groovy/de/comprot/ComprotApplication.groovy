package de.comprot

import org.apache.coyote.http11.Http11NioProtocol
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.Profile
import org.springframework.util.ResourceUtils
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@EnableAutoConfiguration(exclude = [
        DataSourceAutoConfiguration,
        DataSourceTransactionManagerAutoConfiguration,
        SecurityAutoConfiguration
])
@ImportResource('spring/root-context.xml')
class ComprotApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) { SpringApplication.run ComprotApplication, args }

    @Autowired
    @Profile("production")
    @Bean public EmbeddedServletContainerFactory containerFactory(
            @Value('${keystore.file}') String file,
            @Value('${keystore.password}') String password,
            @Value('${keystore.type}') String type,
            @Value('${keystore.alias}') String alias,
            @Value('${keystore.provider}') String provider) {

        def filePath = ResourceUtils.getFile(file).getAbsolutePath()
        def factory = new TomcatEmbeddedServletContainerFactory()

        factory.addConnectorCustomizers({ connector ->
            connector.with {
                port = 8443
                secure = true
                scheme = "https"
            }

            (connector.protocolHandler as Http11NioProtocol).with {
                SSLEnabled = true
                keyAlias = alias
                keystoreFile = filePath
                keystorePass = password
                keystoreType = type
                keystoreProvider = provider
            }

        } as TomcatConnectorCustomizer)

        return factory
    }

}
