package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class NotFoundParamOfCompanyException extends Exception {
    public NotFoundParamOfCompanyException() {
        super();
    }

    public NotFoundParamOfCompanyException(String message) {
        super(message);
    }
}
