package com.altarix.exercisetwo.companystructuremanagement.controllers;

import com.altarix.exercisetwo.companystructuremanagement.domain.Department;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidValueOfChiefException;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidValueOfDataException;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidValueOfDepartmentNameException;
import com.altarix.exercisetwo.companystructuremanagement.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final static Logger logger = Logger.getLogger(DepartmentController.class.getSimpleName());

    @Autowired
    private DepartmentService departmentService;


    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody Department department) throws InvalidValueOfChiefException {
        if (!departmentService.checkIdChief(department.getChiefId())) {
            departmentService.add(department);
            departmentService.appointChiefOfDepartment(department.getChiefId());
        } else {
            throw new InvalidValueOfChiefException();
        }
    }

    @RequestMapping(value = "/{id}/{name}", method = RequestMethod.PUT)
    public Department update(@PathVariable("id") int id, @PathVariable("name") String name) throws InvalidValueOfDepartmentNameException {
        if (!departmentService.checkingDepartmentName(name)) {
            departmentService.update(id, name);
            return departmentService.getDepartmentById(id);
        } else {
            throw new InvalidValueOfDepartmentNameException();
        }
    }

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
}
