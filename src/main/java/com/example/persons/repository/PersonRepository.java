package com.example.persons.repository;


import com.example.persons.errorHandler.PersonNotFoundException;
import com.example.persons.models.Person;
import com.example.persons.models.Son;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class PersonRepository implements  IPersonRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Person createPerson(Person person) throws Exception {
        try {
            UUID uuid = UUID.randomUUID();
            String personUuid = uuid.toString();
            person.setPersonId(personUuid);
            person.setEnable(true);

            int age = ageCalculator(person.getDateOfBrith());
            person.setAge(age);
            entityManager.persist(person);

            return person;

        }catch (Exception e){
            throw new Exception();
        }


    }

    @Override
    public Son createSon(Son son) {
        UUID uuid = UUID.randomUUID();
        String personUuid = uuid.toString();
        son.setSonId(personUuid);


        entityManager.persist(son);
        return son;
    }

    public int ageCalculator(int yearOfBirth){
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        return currentYear-yearOfBirth;
    }

    @Override
    public Person getPersonById(String id) throws PersonNotFoundException {

        try {
            String sql = "SELECT * FROM persons WHERE PersonId = ?";
            Query query = entityManager.createNativeQuery(sql, Person.class);
            query.setParameter(1,id);
            Person person = (Person) query.getSingleResult();
            System.out.println(person);
            return person;

             }catch (NoResultException e){
                System.out.println(e);
                throw  new PersonNotFoundException("Person not found");
            }
    }
    @Override
    public Person deletePerson(String personId) throws PersonNotFoundException {
        Person personDeleted = getPersonByIdGen(personId);
        personDeleted.setEnable(false);

        return personDeleted;
    }

    public Person getPersonByIdGen(String id) throws PersonNotFoundException {

        try {
            String sql = "SELECT * FROM persons WHERE PersonId = ?";
            Query query = entityManager.createNativeQuery(sql, Person.class);
            query.setParameter(1,id);
            Person person = (Person) query.getSingleResult();
            System.out.println(person);
            return person;

        }catch (NoResultException e){
            System.out.println(e);
            throw  new PersonNotFoundException("Person not found");
        }
    }

    @Override
    public Person updatePerson(Person person) throws PersonNotFoundException {

            Person personFromDb = getPersonByIdGen(person.getPersonId());

            personFromDb.setName(person.getName());
            personFromDb.setLastName(person.getLastName());
            personFromDb.setPhone(person.getPhone());
            personFromDb.setEmail(person.getEmail());
            personFromDb.setAddress(person.getAddress());
            entityManager.flush();

            return personFromDb;
    }

    @Override
    public List<Person> getAll() {
        String sql = "SELECT * FROM persons ORDER BY Name";
        Query query = entityManager.createNativeQuery(sql, Person.class);
        List<Person> persons = query.getResultList();

        return persons;
    }
}
