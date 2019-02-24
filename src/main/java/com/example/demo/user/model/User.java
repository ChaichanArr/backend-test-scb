package com.example.demo.user.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="USERNAME")
    private String username;

    @Column(name="PASSWORD")
    private String password;

    @Column(name="NAME")
    private String name;

    @Column(name="SURNAME")
    private String surname;

    @Column(name="DATEOFBIRTH")
    private Date dateOfBirth;

    @Column(name="BOOKS")
    private String books;
}