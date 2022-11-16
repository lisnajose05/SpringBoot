package com.student.ust.DTO;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

/**
 * The type Student dto.
 */
@Data
public class StudentDTO {
    private int studentId;
    private int rollNo;
    private String name;
    private int age;
    private LocalDateTime Date;
    private LocalDateTime modifiedDate;

}
