package de.comprot

import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer

/**  This is used as a web.xml substitution for war deployment. */
class ComprotServletInitializer extends SpringBootServletInitializer {

    @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.sources(ComprotApplication.class);
    }

}