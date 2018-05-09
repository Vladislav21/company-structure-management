package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class InvalidValueOfDepartmentNameException extends InvalidValueOfDataException {
    public InvalidValueOfDepartmentNameException() {
        super();
    }

    public InvalidValueOfDepartmentNameException(String message) {
        super(message);
    }
}
