package com.example.persons.repository;

import com.example.persons.errorHandler.PersonNotFoundException;
import com.example.persons.models.Person;
import com.example.persons.models.Son;

import java.util.List;

public interface IPersonRepository {

    Person createPerson(Person person) throws Exception;

    Son createSon(Son son);

    Person getPersonById(String personId) throws PersonNotFoundException;

    Person deletePerson(String personId) throws PersonNotFoundException;

    Person updatePerson(Person person) throws PersonNotFoundException;

    List<Person> getAll();
}
