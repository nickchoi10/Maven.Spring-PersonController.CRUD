package io.zipcoder.crudapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonService {
    private PersonRepository repository;
    @Autowired
    public PersonService (PersonRepository repository){
        this.repository=repository;
    }

    public Person create(Person person){
        return repository.save(person);
    }

    public Person findById(Long id){
        return repository.findOne(id);
    }

    public List<Person> findAll(){
        Iterable <Person> allPerson= repository.findAll();
        List<Person> personList = new ArrayList<>();
        allPerson.forEach(personList::add);
        return personList;
    }

    public Person update(Long id, Person newData){
        Person personInDatabase = this.findById(id);
        personInDatabase.setFirstName(newData.getFirstName());
        personInDatabase.setLastName(newData.getLastName());
        personInDatabase=repository.save(personInDatabase);
        return personInDatabase;
    }

    public Person deleteById(Long id) {
        Person personDeleted = this.findById(id);
        repository.delete(personDeleted);
        return  personDeleted;
    }




}
