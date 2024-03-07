package com.example.persons.controller;
import com.example.persons.errorHandler.PersonNotFoundException;
import com.example.persons.models.Person;
import com.example.persons.models.Response;
import com.example.persons.models.ResponseWithSons;
import com.example.persons.models.Son;
import com.example.persons.service.IPersonsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonsController {

    @Autowired
    private IPersonsService service;

    @PostMapping("/addPerson")
    public ResponseEntity<Response> createPerson(@Valid @RequestBody Person person) throws Exception {
        Response response = service.createPerson(person);

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @PostMapping("/addSon")
    public ResponseEntity<Response> createSon(@RequestBody Son son){
        Response response = service.createSon(son);

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @PutMapping("/updatePerson")
    public ResponseEntity<Response> updatePerson(@RequestBody Person person) throws PersonNotFoundException {
        Response response = service.updatePerson(person);

        return new ResponseEntity<Response>(response, HttpStatus.OK);
    }

    @GetMapping("/personById/{id}")
    public ResponseEntity<ResponseWithSons> getPersonById(@PathVariable(value = "id") String id) throws PersonNotFoundException {
        ResponseWithSons response = service.getPersonById(id);

        return new ResponseEntity<ResponseWithSons>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable(value= "id") String id) throws PersonNotFoundException {
        Person personDel = service.deletePerson(id);

        return  new ResponseEntity<Person>(personDel, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public List<Person> getAllPersons(){
        List<Person> persons = service.getAllPersons();
        return persons;
    }

}
