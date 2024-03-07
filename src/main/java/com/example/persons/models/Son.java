package com.example.persons.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "sons")
@Embeddable
@EntityListeners(AutoCloseable.class)

public class Son {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "SonId")
    private String sonId;

    @Column(name = "FatherId")
    private  String fatherId;

    @Column(name = "Name")
    private  String name;

    @Column(name = "Age")
    private  int age;

    public Son(){
    }
}
