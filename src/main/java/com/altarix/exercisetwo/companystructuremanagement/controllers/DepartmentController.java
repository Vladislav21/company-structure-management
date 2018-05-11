package com.altarix.exercisetwo.companystructuremanagement.controllers;

import com.altarix.exercisetwo.companystructuremanagement.domain.Department;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidValueOfChiefException;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidValueOfDepartmentIdException;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidValueOfDepartmentNameException;
import com.altarix.exercisetwo.companystructuremanagement.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/departments")
public class DepartmentController {

    private final static Logger logger = Logger.getLogger(DepartmentController.class.getSimpleName());

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void add(@RequestBody Department department) throws InvalidValueOfDepartmentNameException {
        if (!departmentService.checkingDepartmentName(department.getName())) {
            departmentService.add(department);
            logger.info("Upload succeeded");
        } else {
            throw new InvalidValueOfDepartmentNameException();
        }
    }

    @RequestMapping(value = "/appointChief/{idDepartment}/{idChief}", method = RequestMethod.PUT)
    public void appointChief(@PathVariable("idDepartment") int idDepartment, @PathVariable("idChief") int idChief) throws InvalidValueOfChiefException, InvalidValueOfDepartmentIdException {
        if (departmentService.isThereDepartment(idDepartment)) {
            if (departmentService.checkIdChief(idChief) == idDepartment
                    && !departmentService.checkEmployeesOfDepartment(idChief)) {
                departmentService.appointChiefToDepartment(idChief, idDepartment);
                departmentService.appointChiefToEmployees(idChief);
                logger.info("The appointment of the chief was successful");
            } else {
                throw new InvalidValueOfChiefException();
            }
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }

    @RequestMapping(value = "/putName/{id}/{name}", method = RequestMethod.PUT)
    public Department update(@PathVariable("id") int id, @PathVariable("name") String name) throws InvalidValueOfDepartmentNameException, InvalidValueOfDepartmentIdException {
        if (departmentService.isThereDepartment(id)) {
            if (!departmentService.checkingDepartmentName(name)) {
                departmentService.update(id, name);
                logger.info("Update successful");
                return departmentService.getDepartmentById(id);
            } else {
                throw new InvalidValueOfDepartmentNameException();
            }
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) throws InvalidValueOfDepartmentIdException {
        if (departmentService.isThereDepartment(id)) {
            if (!departmentService.checkExistenceEmployeeInDepartment(id)) {
                departmentService.delete(id);
                logger.info("Uninstall succeeded");
            } else {
                throw new InvalidValueOfDepartmentIdException();
            }
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Department getDepartmentById(@PathVariable("id") int id) throws InvalidValueOfDepartmentIdException {
        Department result = departmentService.getDepartmentById(id);
        if (result != null) {
            return result;
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }
}
