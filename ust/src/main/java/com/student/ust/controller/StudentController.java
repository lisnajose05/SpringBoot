package com.student.ust.controller;

import com.student.ust.DTO.StudentDTO;
import com.student.ust.entity.Student;
import com.student.ust.exception.InvalidEmailException;
import com.student.ust.exception.PasswordException;
import com.student.ust.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;

import static javafx.scene.text.FontPosture.findByName;

/**
 * The type Student controller.
 */
@RestController
@Slf4j
public class StudentController {
    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;


    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDTO> get(@PathVariable Integer id) {
        log.debug("passed in id is >>>>" + id);

        try {
            Student student = studentService.getStudentByID(id);
            return new ResponseEntity<StudentDTO>(studentService.converttoDTO(student), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
        }


    }

    /**
     * Add response entity.
     *
     * @param student the student
     * @return the response entity
     */
    @PostMapping("/students")
    public ResponseEntity<Student> add(@RequestBody Student student) {
        try{
            studentService.saveStudent(student);
            log.debug("passed in student details >>>>" + student.getName() + " " + student.getAge() + " " + student.getRollNo());
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        catch (InvalidEmailException e){

            return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
        }
        catch (PasswordException e){

            return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
        }
        catch (NoSuchAlgorithmException e){

            return new ResponseEntity<Student>(HttpStatus.PRECONDITION_FAILED);
        }





    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping("/student")
    public ResponseEntity<List<StudentDTO>>
    getAll() {
        try {
            List<Student> studentAll = studentService.getStudentAll();
            return new ResponseEntity<List<StudentDTO>>(studentService.converttoDTO2(studentAll), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<StudentDTO>>(HttpStatus.NOT_FOUND);
        }


    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping("/students/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.deleteStudent1(id);
    }


    /**
     * Put response entity.
     *
     * @param student the student
     * @return the response entity
     */
    @PutMapping("/stud")
    public ResponseEntity<Student> put(@RequestBody Student student) {
        try {
            Student updatedstudent = studentService.updateStudent(student);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }




    }
}





