package com.example.persons.models;

import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@AllArgsConstructor
@Entity
@Table(name = "persons")
@Embeddable
@EntityListeners(AuditingEntityListener.class)

public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;


    @Column(name = "PersonId")
    private String personId;

    @NotEmpty(message = "Should not be empty")
    @NotBlank
    @Column(name = "Name")
    private String name;

    @NotEmpty(message = "Should not be empty")
    @Column(name =  "LastName")
    private String lastName;


    @Column(name = "DateOfBirth")
    private  int dateOfBrith;

    @NotEmpty(message = "Should not be empty")
    @Column(name = "Address")
    private  String address;


    @Size(min = 10, max = 10, message = "phone need to have 10 characters")
    @Column(name = "Phone")
    private  String phone;


    @Email(message = "Invalid email")
    @Column(name = "Email")
    private String email;

    @Column(name = "Age")
    private  int age;

    @Column(name = "enable")
    private  boolean enable;

    public Person(){
    }
}
