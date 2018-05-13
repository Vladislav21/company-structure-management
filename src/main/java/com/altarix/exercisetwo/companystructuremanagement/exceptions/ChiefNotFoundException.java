package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class ChiefNotFoundException extends NotFoundParamOfCompanyException {
    public ChiefNotFoundException() {
        super();
    }

    public ChiefNotFoundException(String message) {
        super(message);
    }
}
