package com.example.persons.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class Response {
    private String Response;
    private String Detail;
    private String Code;
    private Object object;
    public  Response(){
    }
}
