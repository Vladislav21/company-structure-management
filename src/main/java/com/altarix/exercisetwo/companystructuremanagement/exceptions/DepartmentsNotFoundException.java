package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class DepartmentsNotFoundException extends NotFoundParametersOfCompanyException {
    public DepartmentsNotFoundException() {
        super();
    }

    public DepartmentsNotFoundException(String message) {
        super(message);
    }
}
