package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class InvalidValueOfDepartmentIdException extends InvalidValueOfDataException {
    public InvalidValueOfDepartmentIdException() {
        super();
    }

    public InvalidValueOfDepartmentIdException(String message) {
        super(message);
    }
}
