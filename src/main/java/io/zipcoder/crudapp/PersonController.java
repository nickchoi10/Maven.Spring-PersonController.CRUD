package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/people")
public class PersonController {

    private PersonService service;

    @Autowired
    public PersonController(PersonService service){
        this.service= service;
    }
    @Autowired
    PersonRepository personRepository;


    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person p){ //requestbody is data sent by client to my API
        return new ResponseEntity<>(personRepository.save(p), HttpStatus.CREATED); //saving person p in personRepository
    }


    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") Long id){
        Person person = personRepository.findOne(id);
        if(person ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPersonList(){
        List<Person> personList = (List<Person>) personRepository.findAll();
        if(personList ==null || personList.isEmpty()){
            return new ResponseEntity<List<Person>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Person>>(HttpStatus.OK);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id, String firstName, String lastName){
        Person currentPerson = new Person(firstName, lastName, id);
        if(personRepository.exists(id)){
            return new ResponseEntity<>(personRepository.save(currentPerson), HttpStatus.OK);
        }
        return new ResponseEntity<>(personRepository.save(currentPerson), HttpStatus.CREATED);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> DeletePerson(@PathVariable("id") Long id){
        Person p = personRepository.findOne(id);
        personRepository.delete(p);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
