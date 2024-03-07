package com.example.persons.errorHandler;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException(String message){
        super(message);
    }
}
