package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class UnavailableOperationForEmployeeException extends Exception {
    public UnavailableOperationForEmployeeException() {
        super();
    }

    public UnavailableOperationForEmployeeException(String message) {
        super(message);
    }
}
