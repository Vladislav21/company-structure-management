package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class NotFoundParametersOfCompanyException extends Exception {
    public NotFoundParametersOfCompanyException() {
        super();
    }

    public NotFoundParametersOfCompanyException(String message) {
        super(message);
    }
}
