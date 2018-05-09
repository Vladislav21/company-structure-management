package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class InvalidValueOfDataException extends Exception {
    public InvalidValueOfDataException() {
        super();
    }

    public InvalidValueOfDataException(String message) {
        super(message);
    }
}
