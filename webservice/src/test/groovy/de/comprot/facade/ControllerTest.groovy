package de.comprot.facade

import com.fasterxml.jackson.databind.ObjectMapper
import de.comprot.core.service.MappingService
import de.comprot.core.service.UserService
import de.comprot.facade.v1.controller.UserController
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

import static org.mockito.MockitoAnnotations.initMocks

class ControllerTest {

    def mvc

    def jsonMapper = new ObjectMapper()

    def validator = new LocalValidatorFactoryBean()

    @Mock UserService userService

    @Mock MappingService mappingService

    @InjectMocks UserController controller

    @Before void setUp() {
        initMocks this
        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setValidator(validator)
                .build()
    }

    def convertToJson(Object object) { jsonMapper.writeValueAsBytes object }

}
