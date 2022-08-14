package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class PersonConfig {

    @Autowired
    private PersonService service;

    @PostConstruct
    public void setup(){
        service.create(new Person(1L,"nick", "choi"));
        service.create(new Person(2L,"christy", "choi"));
        service.create(new Person(3L,"nogi", "choi"));
        service.create(new Person(4L,"young", "choi"));
        service.create(new Person(5L,"mi", "choi"));
        service.create(new Person(6L,"jessica", "choi"));
        service.create(new Person(7L,"jesse", "choi"));
    }
}
