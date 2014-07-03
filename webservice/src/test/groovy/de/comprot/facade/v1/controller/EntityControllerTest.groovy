package de.comprot.facade.v1.controller

import de.comprot.core.model.UserEntity
import de.comprot.facade.ControllerTest
import de.comprot.facade.v1.model.RegistrationDto
import de.comprot.facade.v1.model.UserDto
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

import static org.hamcrest.CoreMatchers.endsWith
import static org.hamcrest.CoreMatchers.is
import static org.mockito.Matchers.any
import static org.mockito.Matchers.eq
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@RunWith(MockitoJUnitRunner.class)
class EntityControllerTest extends ControllerTest {

    final MediaType CONTENT_TYPE = new MediaType('application', 'vnd.comprot-v1.0+json')



}
