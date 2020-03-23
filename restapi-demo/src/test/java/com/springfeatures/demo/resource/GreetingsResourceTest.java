package com.springfeatures.demo.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springfeatures.demo.dto.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"username = user1", "password = user1Pass"})
class GreetingsResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void successIfValidRequest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PersonDTO personDTO = mapper.readValue("{\"name\":\"John\", \"age\":12}", PersonDTO.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/greet")
            .with(SecurityMockMvcRequestPostProcessors.httpBasic("user1","user1Pass"))
            .contentType("application/json")
            .content(mapper.writeValueAsString(personDTO)))
            .andExpect(status().isOk());
    }

    @Test
    public void failsIfWrongContentType() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PersonDTO personDTO = mapper.readValue("{\"name\":\"John\", \"age\":12}", PersonDTO.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/greet")
            .with(SecurityMockMvcRequestPostProcessors.httpBasic("user1","user1Pass"))
            .contentType("text/plain")
            .content(mapper.writeValueAsString(personDTO)))
            .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void failsIfNoCredentialsSupplied() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PersonDTO personDTO = mapper.readValue("{\"name\":\"John\", \"age\":12}", PersonDTO.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/greet")
            .contentType("application/json")
            .content(mapper.writeValueAsString(personDTO)))
            .andExpect(status().isUnauthorized());
    }


}