package com.altarix.exercisetwo.companystructuremanagement.controllers;

import com.altarix.exercisetwo.companystructuremanagement.domain.Department;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidValueOfChiefException;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidValueOfDataException;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidValueOfDepartmentIdException;
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
    public void add(@RequestBody Department department) throws InvalidValueOfDepartmentNameException {
        if (!departmentService.checkingDepartmentName(department.getName())) {
            departmentService.add(department);
            logger.info("Upload succeeded");
        } else {
            throw new InvalidValueOfDepartmentNameException();
        }
    }

    @RequestMapping(value = "/appointChief/{idDepartment}/{idChief}", method = RequestMethod.PUT)
    public void appointChief(@PathVariable("idDepartment") int idDepartment, @PathVariable("idChief") int idChief) throws InvalidValueOfChiefException {
        if (departmentService.checkIdChief(idChief) == idDepartment
                && !departmentService.checkEmployeesOfDepartment(idChief)) {
            departmentService.appointChiefToDepartment(idChief, idDepartment);
            departmentService.appointChiefToEmployees(idChief);
            logger.info("The appointment of the chief was successful");
        } else {
            throw new InvalidValueOfChiefException();
        }
    }

    @RequestMapping(value = "/{id}/{name}", method = RequestMethod.PUT)
    public Department update(@PathVariable("id") int id, @PathVariable("name") String name) throws InvalidValueOfDepartmentNameException {
        if (!departmentService.checkingDepartmentName(name)) {
            departmentService.update(id, name);
            logger.info("Update successful");
            return departmentService.getDepartmentById(id);
        } else {
            throw new InvalidValueOfDepartmentNameException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) throws InvalidValueOfDepartmentIdException {
        if (!departmentService.checkExistenceEmployeeInDepartment(id)) {
            departmentService.delete(id);
            logger.info("Uninstall succeeded");
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Department getDepartmentById(@PathVariable("id") int id) {
        return departmentService.getDepartmentById(id);
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

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "In this department exists employees")
    @ExceptionHandler(InvalidValueOfDepartmentIdException.class)
    public ModelAndView handlerInvalidValueOfDepartmentIdException(InvalidValueOfDataException ex) {
        logger.error("In this department exists employees");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex);
        return modelAndView;
    }

}
