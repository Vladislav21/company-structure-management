package com.altarix.exercisetwo.companystructuremanagement.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

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
}
