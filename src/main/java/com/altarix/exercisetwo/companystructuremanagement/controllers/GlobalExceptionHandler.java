package com.altarix.exercisetwo.companystructuremanagement.controllers;

import com.altarix.exercisetwo.companystructuremanagement.exceptions.*;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getSimpleName());

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The value of id of the chief already exists")
    @ExceptionHandler(InvalidValueOfChiefException.class)
    public ModelAndView handlerInvalidValueOfChiefException(InvalidValueOfDataException ex) {
        logger.error("The value of id of the chief already exists");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The value of department name already exists")
    @ExceptionHandler(InvalidValueOfDepartmentNameException.class)
    public ModelAndView handlerInvalidValueOfDepartmentNameException(InvalidValueOfDataException ex) {
        logger.error("The value of department name already exists");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "In this department exists employees or current id is absent")
    @ExceptionHandler(InvalidValueOfDepartmentIdException.class)
    public ModelAndView handlerInvalidValueOfDepartmentIdException(InvalidValueOfDataException ex) {
        logger.error("In this department exists employees or current id is absent");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Departments are absent")
    @ExceptionHandler(DepartmentsNotFoundException.class)
    public ModelAndView handlerDepartmentsNotFoundException(NotFoundParametersOfCompanyException ex) {
        logger.error("Departments are absent");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid parameters of employee")
    @ExceptionHandler(InvalidParametersOfEmployeeException.class)
    public ModelAndView handlerInvalidParamOfEmployeeException(InvalidValueOfDataException ex) {
        logger.error("Invalid parameters of employee");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Employee with this id is absent")
    @ExceptionHandler(InvalidValueOfEmployeeIdException.class)
    public ModelAndView handlerInvalidValueOfEmployeeIdException(InvalidValueOfDataException ex) {
        logger.error("Employee with this id is absent");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid format of Date")
    @ExceptionHandler(ParseException.class)
    public ModelAndView handlerParseException(ParseException ex) {
        logger.error("Invalid format of Date");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Chief is absent")
    @ExceptionHandler(ChiefNotFoundException.class)
    public ModelAndView handlerParseException(NotFoundParametersOfCompanyException ex) {
        logger.error("Chief is absent");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Employees are absent")
    @ExceptionHandler(EmployeesNotFoundException.class)
    public ModelAndView handlerEmployeesNotFoundException(NotFoundParametersOfCompanyException ex) {
        logger.error("Employees are absent");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "This employee is fired")
    @ExceptionHandler(UnavailableOperationForEmployeeException.class)
    public ModelAndView handlerUnavailableOperationExceptionForEmployee(UnavailableOperationForEmployeeException ex) {
        logger.error("This employee is fired");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }
}
