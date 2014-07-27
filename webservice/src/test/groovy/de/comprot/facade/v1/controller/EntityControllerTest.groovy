package de.comprot.facade.v1.controller

import de.comprot.core.model.ComprotEntity
import de.comprot.core.service.ComprotEntityIndexService
import de.comprot.facade.ControllerTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.http.MediaType

import static org.mockito.Matchers.any
import static org.mockito.Matchers.eq
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
class EntityControllerTest extends ControllerTest {

    def contentType = new MediaType('application', 'vnd.comprot-v1.0+json')

    @Mock ComprotEntityIndexService indexService

    @InjectMocks EntityController controller

    @Test void testSearchResultRetrieval() {
        when(mappingService.generateList(any(List), eq(ComprotEntity))).thenReturn null
    }

}
