package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("people")
public class PersonController {

    private PersonService service;

    @Autowired
    public PersonController(PersonService service){
        this.service= service;
    }
//    @Autowired
//    PersonRepository personRepository;


    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person p){ //requestbody is data sent by client to my API
        return new ResponseEntity<>(service.create(p), HttpStatus.CREATED); //saving person p in personRepository
    }


    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersonList(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);

    }

    @PutMapping ("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person p){
        return new ResponseEntity<>(service.update(id,p), HttpStatus.OK);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Person> DeletePerson(@PathVariable Long id){
        return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);


    }

}
