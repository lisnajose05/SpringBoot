package com.student.ust.service;

import com.student.ust.DTO.StudentDTO;
import com.student.ust.entity.Student;
import com.student.ust.exception.InvalidEmailException;
import com.student.ust.exception.PasswordException;
import com.student.ust.repository.StudentRepository;
import com.student.ust.util.UstUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Student service.
 */
@Service
@Slf4j
public class StudentService {
    /**
     * The Student repository.
     */
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Gets student by id.
     *
     * @param id the id
     * @return the student by id
     * @throws NoSuchElementException the no such element exception
     */
    public Student getStudentByID(Integer id) throws NoSuchElementException{

        return studentRepository.findById(id).orElseThrow(()->new NoSuchElementException());
    }

    /**
     * Save student.
     *
     * @param student the student
     */
    public void saveStudent(Student student) throws NoSuchAlgorithmException {
        student.setDate(LocalDateTime.now());
        student.setModifiedDate(student.getDate());
        String email=student.getEmail();
        String password=student.getPassword();
        boolean validEmail = UstUtil.validEmail(email);
        boolean validPassword=UstUtil.validPassword(password);
        if (validEmail && validPassword) {
            String hashPassword = UstUtil.hashPassword(password);
            student.setPassword(hashPassword);
            studentRepository.save(student);
        } else if(!validEmail) {
            throw new InvalidEmailException();
        }
        else {
            throw new PasswordException();
        }


    }

    /**
     * Gets student all.
     *
     * @return the student all
     */
    public List<Student> getStudentAll() {
        log.debug(studentRepository.findByName("Lisna").getName());


        return studentRepository.findAll();
    }

    /**
     * Delete student 1.
     *
     * @param id the id
     */
    public void deleteStudent1(Integer id) {
        studentRepository.deleteById(id);
    }

    /**
     * Update student student.
     *
     * @param student the student
     * @return the student
     */
    public Student updateStudent(Student student){
        Student updateStudent=studentRepository.findById(student.getStudentId()).orElseThrow(()->new NoSuchElementException());
        updateStudent.setName(student.getName());
        updateStudent.setAge(student.getAge());
        updateStudent.setModifiedDate(LocalDateTime.now());
        studentRepository.save(updateStudent);
        return updateStudent;




    }

    public StudentDTO converttoDTO(Student student) {
        return modelMapper.map(student, StudentDTO.class);
    }
    public List<StudentDTO> converttoDTO2(List<Student> studentAll){
        List<StudentDTO> studentDTOList=modelMapper.map(studentAll, new TypeToken<List<StudentDTO>>(){}.getType());
        return studentDTOList;
    }
}

