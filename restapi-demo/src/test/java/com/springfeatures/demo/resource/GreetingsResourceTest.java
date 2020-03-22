package com.springfeatures.demo.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springfeatures.demo.dto.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingsResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void successIfValidRequest() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PersonDTO personDTO = mapper.readValue("{\"name\":\"John\", \"age\":12}", PersonDTO.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/greet")
            .contentType("application/json")
            .content(mapper.writeValueAsString(personDTO)))
            .andExpect(status().isOk());
    }

    @Test
    public void failsIfWrongContentType() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        PersonDTO personDTO = mapper.readValue("{\"name\":\"John\", \"age\":12}", PersonDTO.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/greet")
            .contentType("text/plain")
            .content(mapper.writeValueAsString(personDTO)))
            .andExpect(status().isUnsupportedMediaType());
    }


}