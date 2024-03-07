package com.example.persons.service;

import com.example.persons.errorHandler.PersonNotFoundException;
import com.example.persons.models.Person;
import com.example.persons.models.Response;
import com.example.persons.models.ResponseWithSons;
import com.example.persons.models.Son;
import com.example.persons.validator.PersonsValidator;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.example.persons.repository.IPersonRepository;
import org.apache.logging.log4j.LogManager;
import java.util.List;


@Service
public class PersonsService implements IPersonsService {
    private static final Logger logger = LogManager.getLogger(PersonsService.class);

    @Autowired
    private IPersonRepository repository;

    @Autowired
    private PersonsValidator validator;

    @Override
    public Response createPerson(Person person) throws Exception {
        logger.info("Starting process create person:", person);

        String name = person.getName();
        Boolean isValid = validator.validatePersonName(name);

        if (isValid != false) {
            Person personRes = repository.createPerson(person);

            Response response = new Response();
            response.setResponse("Correct");
            response.setCode("001.001.001");
            response.setDetail("Person added");
            response.setObject(personRes);

            return response;
        } else {
            Response response = new Response();
            response.setResponse("Incorrect");
            response.setDetail("The name is equal");
            response.setObject(person);

            return response;
        }
    }

    @Override
    public Response createSon(Son son) {
        logger.info("Starting process son person:", son);

        Son sonRes = repository.createSon(son);

        Response response = new Response();
        response.setResponse("Correct");
        response.setCode("001.001.001");
        response.setDetail("Son added");
        response.setObject(sonRes);

        return response;
    }

    @Override
    public ResponseWithSons getPersonById(String personId) throws PersonNotFoundException {
        logger.info("Starting process getPersonId:", personId);

        List<Son> sons = validator.haveChild(personId);
        Person person = repository.getPersonById(personId);

        ResponseWithSons response = new ResponseWithSons();
        response.setResponse("Correct");
        response.setDetail("person found");
        response.setCode("001.001.001");
        response.setObject(person);
        response.setSons(sons);

        return response;
    }

    @Override
    public Person deletePerson(String personId) throws PersonNotFoundException {
        logger.info("Starting process delete person:", personId);

        return repository.deletePerson(personId);
    }

    @Override
    public Response updatePerson(Person person) throws PersonNotFoundException {
        logger.info("Starting process update person:", person);

        Person personUpdate = repository.updatePerson(person);

        Response response = new Response();
        response.setDetail("person updated");
        response.setCode("001.001.001");
        response.setResponse("Correct");
        response.setObject(personUpdate);

        return response;
    }

    @Override
    public List<Person> getAllPersons() {
        logger.info("Starting process get all persons");

        return repository.getAll();
    }
}
