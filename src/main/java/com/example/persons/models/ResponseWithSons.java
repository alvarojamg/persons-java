package com.example.persons.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class ResponseWithSons {
    private String Response;
    private String Detail;
    private String Code;
    private Object object;
    private List<Son> sons;

    public  ResponseWithSons(){
    }
}
