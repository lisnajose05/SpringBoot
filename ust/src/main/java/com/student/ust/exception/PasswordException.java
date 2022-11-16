package com.student.ust.exception;

/**
 * The type Password exception.
 */
public class PasswordException extends BusinessException{
    /**
     * Instantiates a new Password exception.
     */
    public PasswordException(){
        super("Invalid password format");
    }
}
