package com.example.persons.validator;


import com.example.persons.models.Person;
import com.example.persons.models.Son;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.List;

@Component
@Validated
public class PersonsValidator{

    @PersistenceContext
    private EntityManager entityManager;

    public Boolean validatePersonName(String name) {

        try{
            String sql = "SELECT COUNT(*) FROM persons WHERE name = ?";

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter(1,name);

            Long count = (Long) query.getSingleResult();

            if(count.intValue()==0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            Exception exception = e;
            return false;
        }
    }

    public List<Son> haveChild(String fatherId){
       String sql = "SELECT * FROM sons WHERE fatherId = ?";
       Query query = entityManager.createNativeQuery(sql,Son.class);
       query.setParameter(1,fatherId);

       List<Son> sons = query.getResultList();

       return sons;
    }
}
