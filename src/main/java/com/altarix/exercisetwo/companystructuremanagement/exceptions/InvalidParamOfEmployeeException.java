package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class InvalidParamOfEmployeeException extends InvalidValueOfDataException {
    public InvalidParamOfEmployeeException() {
        super();
    }

    public InvalidParamOfEmployeeException(String message) {
        super(message);
    }
}
