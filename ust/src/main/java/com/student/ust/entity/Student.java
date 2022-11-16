package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * The type Student.
 */
@Entity
@Data

@Table(name = "student_ustbatch_mappedBy")
public class Student {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int studentId;
    private int rollNo;
    private String name;
    private int age;
    private LocalDateTime Date,modifiedDate;
    @Column(name = "email",unique = true,nullable = false)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;


    @OneToMany(cascade  =CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="student")
    private Set<Book> bookSet;






}

