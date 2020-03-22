package com.springfeatures.demo.service;

import com.springfeatures.demo.dto.GreetDTO;
import com.springfeatures.demo.dto.PersonDTO;

import java.util.Date;

public class GreetingService {
    private final PersonDTO person;

    public GreetingService(PersonDTO person) {
        this.person = person;
    }

    public GreetDTO getGreeting() {
        return new GreetDTO("Hi, "+person.getName()+" you're "+person.getAge(), new Date());
    }
}
