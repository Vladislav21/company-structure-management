package com.altarix.exercisetwo.companystructuremanagement.controllers;

import com.altarix.exercisetwo.companystructuremanagement.domain.Employee;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidParamOfEmployeeException;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidValueOfDepartmentIdException;
import com.altarix.exercisetwo.companystructuremanagement.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/employees")
public class EmployeeController {

    private final static Logger logger = Logger.getLogger(EmployeeController.class.getSimpleName());

    @Autowired
    private EmployeeService employeeService;


    @RequestMapping(value = "/getList/{idDepartment}", method = RequestMethod.GET)
    public List<Employee> getListEmployeeByDepartmentId(@PathVariable("idDepartment") int idDepartment) throws InvalidValueOfDepartmentIdException {
        List<Employee> result = employeeService.getListEmployeeByDepartmentId(idDepartment);
        if (!result.isEmpty()) {
            return result;
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public void add(@RequestBody Employee employee) throws InvalidParamOfEmployeeException {
        if (employeeService.checkValidDataOfEmployee(employee)) {
            employeeService.add(employee);
            logger.info("Upload succeeded");
        } else {
            throw new InvalidParamOfEmployeeException();
        }
    }

    @RequestMapping(value = "/putEmployee", method = RequestMethod.PUT)
    public Employee update(@RequestBody Employee employee) throws InvalidParamOfEmployeeException {
        if (employeeService.checkValidDataOfEmployee(employee)) {
            return employeeService.update(employee);
        } else {
            throw new InvalidParamOfEmployeeException();
        }
    }

}
