package com.springfeatures.demo.resource;

import com.springfeatures.demo.dto.GreetDTO;
import com.springfeatures.demo.dto.PersonDTO;
import com.springfeatures.demo.service.GreetingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsResource {

    private static final Log LOG = LogFactory.getLog(GreetingsResource.class);

    private GreetingService greetingService;

    @PostMapping(value = "/greet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GreetDTO> greet(@RequestBody PersonDTO person) {
        if(person==null) {
            LOG.error("Request Body can not be null");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        greetingService = new GreetingService(person);
        GreetDTO response = greetingService.getGreeting();
        if(response==null){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
