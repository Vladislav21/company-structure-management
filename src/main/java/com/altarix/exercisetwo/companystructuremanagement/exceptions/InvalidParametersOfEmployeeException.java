package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class InvalidParametersOfEmployeeException extends InvalidValueOfDataException {
    public InvalidParametersOfEmployeeException() {
        super();
    }

    public InvalidParametersOfEmployeeException(String message) {
        super(message);
    }
}
