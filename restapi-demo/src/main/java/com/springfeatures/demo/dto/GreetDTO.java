package com.springfeatures.demo.dto;

import java.util.Date;

public class GreetDTO {
    private String greet;
    private Date date;

    //Needed for Jackson
    public GreetDTO() {
    }

    public GreetDTO(String greet, Date date) {
        this.greet = greet;
        this.date = date;
    }

    public String getGreet() {
        return greet;
    }

    public void setGreet(String greet) {
        this.greet = greet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
