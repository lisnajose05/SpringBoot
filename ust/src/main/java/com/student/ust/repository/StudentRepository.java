package com.student.ust.repository;

import com.student.ust.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Student findByName(String l);


   Student findByrollNo(int i);

    @Query("select s from Student s where s.age=?1")
    Student findByStudentAge(int i);

}
