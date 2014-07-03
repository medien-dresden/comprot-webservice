package de.comprot.facade.v1.controller

import de.comprot.core.model.UserEntity
import de.comprot.core.service.UserService
import de.comprot.facade.ControllerTest
import de.comprot.facade.v1.model.RegistrationDto
import de.comprot.facade.v1.model.UserDto
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.http.MediaType

import static org.hamcrest.CoreMatchers.endsWith
import static org.hamcrest.CoreMatchers.is
import static org.mockito.Matchers.any
import static org.mockito.Matchers.eq
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(MockitoJUnitRunner.class)
class UserControllerTest extends ControllerTest {

    def contentType = new MediaType('application', 'vnd.comprot-v1.0+json')

    def userEntity = new UserEntity(username: 'paul')

    def userDto = new UserDto(username: 'paul')

    def registrationDto = new RegistrationDto(username: 'paul', password: 'paul123456', email: 'paul@comprot.de')

    @Mock UserService userService

    @InjectMocks UserController controller

    @Test void testPositiveRetrieval() {
        // mocking
        when(userService.loadByUsername('paul')).thenReturn userEntity
        when(mappingService.generate(any(), eq(UserDto))).thenReturn userDto

        // black-box
        mvc.perform(get('/api/users/paul').accept(contentType))

                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))

                .andExpect(jsonPath('$.username', is('paul')))
                .andExpect(jsonPath('$.links[?(@.rel == self)]').exists())
                .andExpect(jsonPath('$.links[?(@.rel == self)].href[0]', endsWith('/api/users/paul')))

        // white-box
        verify(userService, times(1)).loadByUsername 'paul'
        verifyNoMoreInteractions userService
    }

    @Test void testPositiveRegistration() {
        // mocking
        when(mappingService.generate(any(), eq(UserEntity))).thenReturn userEntity
        when(mappingService.generate(any(), eq(UserDto))).thenReturn userDto

        // black-box
        mvc.perform(post('/api/users').contentType(contentType).content(convertToJson(registrationDto)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(contentType))

                // TODO more expectations

        // white-box
        verify(userService, times(1)).register any(UserEntity)
    }

}
