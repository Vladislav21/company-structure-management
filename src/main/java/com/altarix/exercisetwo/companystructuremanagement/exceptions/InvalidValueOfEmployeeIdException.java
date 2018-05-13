package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class InvalidValueOfEmployeeIdException extends InvalidValueOfDataException {
    public InvalidValueOfEmployeeIdException() {
        super();
    }

    public InvalidValueOfEmployeeIdException(String message) {
        super(message);
    }
}
