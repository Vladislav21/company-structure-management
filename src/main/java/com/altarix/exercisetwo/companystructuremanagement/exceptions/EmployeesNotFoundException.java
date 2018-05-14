package com.altarix.exercisetwo.companystructuremanagement.exceptions;

@SuppressWarnings("serial")
public class EmployeesNotFoundException extends NotFoundParametersOfCompanyException {
    public EmployeesNotFoundException() {
        super();
    }

    public EmployeesNotFoundException(String message) {
        super(message);
    }
}
