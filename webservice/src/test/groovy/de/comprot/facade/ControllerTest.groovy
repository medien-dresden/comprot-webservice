package de.comprot.facade

import com.fasterxml.jackson.databind.ObjectMapper
import de.comprot.core.service.MappingService
import org.junit.Before
import org.mockito.Mock
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

import static org.mockito.MockitoAnnotations.initMocks

@SuppressWarnings(["GroovyAssignabilityCheck", "GrUnresolvedAccess"])
abstract class ControllerTest {

    def mvc

    def jsonMapper = new ObjectMapper()

    def validator = new LocalValidatorFactoryBean()

    @Mock MappingService mappingService

    @Before void setUp() {
        initMocks this

        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setValidator(validator)
                .build()
    }

    def convertToJson(Object object) { jsonMapper.writeValueAsBytes object }

}
