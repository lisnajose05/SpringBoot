package com.student.ust.entity;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "book_ust_details_mappedBy")

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private String bookName;
    private String authorName;
    private int isbnNumber;
    private LocalDateTime Date,modifiedDate;

   @ManyToOne
    @JoinColumn(name = "student_id")
    Student student;
}
