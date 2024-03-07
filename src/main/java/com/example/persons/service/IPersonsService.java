package com.example.persons.service;

import com.example.persons.errorHandler.PersonNotFoundException;
import com.example.persons.models.Person;
import com.example.persons.models.Response;
import com.example.persons.models.ResponseWithSons;
import com.example.persons.models.Son;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

public interface IPersonsService {

    Response createPerson(Person person) throws Exception;

    Response createSon(Son son);

    ResponseWithSons getPersonById(String personId) throws PersonNotFoundException;

    Person deletePerson(String personId) throws PersonNotFoundException;

    Response updatePerson(Person person) throws PersonNotFoundException;

    List<Person> getAllPersons();

}
