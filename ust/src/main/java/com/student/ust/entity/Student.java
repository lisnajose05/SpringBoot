package com.student.ust.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

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


    @OneToMany(cascade  =CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="student")
    private Set<Book> bookSet;



}

