package com.student.ust.service;

import com.student.ust.entity.Student;
import com.student.ust.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentService {
@Autowired
    StudentRepository studentRepository;
    public Student getStudentByID(Integer id) throws NoSuchElementException{

        return studentRepository.findById(id).orElseThrow(()->new NoSuchElementException());
    }
    public void saveStudent(Student student){
        student.setDate(LocalDateTime.now());
        student.setModifiedDate(student.getDate());
        studentRepository.save(student);

    }

    public List<Student> getStudentAll() {
        System.out.println(studentRepository.findByName("rita"));
        System.out.println(studentRepository.findByrollNo(30));
        System.out.println(studentRepository.findByStudentAge(70));

        return studentRepository.findAll();
    }

    public void deleteStudent1(Integer id) {
        studentRepository.deleteById(id);
    }
    public Student updateStudent(Student student){
        Student updateStudent=studentRepository.findById(student.getStudentId()).orElseThrow(()->new NoSuchElementException());
        updateStudent.setName(student.getName());
        updateStudent.setAge(student.getAge());
        updateStudent.setModifiedDate(LocalDateTime.now());
        studentRepository.save(updateStudent);
        return updateStudent;

    }

}

