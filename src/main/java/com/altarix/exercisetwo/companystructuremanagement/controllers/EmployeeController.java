package com.altarix.exercisetwo.companystructuremanagement.controllers;

import com.altarix.exercisetwo.companystructuremanagement.domain.Employee;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.*;
import com.altarix.exercisetwo.companystructuremanagement.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void add(@RequestBody Employee employee) throws InvalidParametersOfEmployeeException {
        if (employeeService.checkValidateDataOfEmployee(employee)) {
            employeeService.add(employee);
            logger.info("Upload succeeded");
        } else {
            throw new InvalidParametersOfEmployeeException();
        }
    }

    @RequestMapping(value = "/putEmployee/{id}", method = RequestMethod.PUT)
    public Employee update(@RequestBody Employee employee, @PathVariable("id") int id) throws InvalidParametersOfEmployeeException, InvalidValueOfEmployeeIdException, UnavailableOperationForEmployeeException {
        if (employeeService.checkExistenceEmployee(id)) {
            employee.setId(id);
            if (employeeService.checkValidateDataOfEmployee(employee)) {
                return employeeService.update(employee);
            } else {
                throw new InvalidParametersOfEmployeeException();
            }
        } else {
            throw new InvalidValueOfEmployeeIdException();
        }
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable("id") int id) throws InvalidValueOfEmployeeIdException {
        Employee result = employeeService.getEmployeeById(id);
        if (result != null) {
            return result;
        } else {
            throw new InvalidValueOfEmployeeIdException();
        }
    }

    @RequestMapping(value = "/dismissEmployee/{id}/{dateOfDismissal}", method = RequestMethod.PUT)
    public void dismissEmployeeById(@PathVariable("id") int id, @PathVariable("dateOfDismissal") String dateOfDismissal) throws InvalidValueOfEmployeeIdException, InvalidParametersOfEmployeeException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date dismissalDate = format.parse(dateOfDismissal);
        if (employeeService.checkExistenceEmployee(id)) {
            if (employeeService.checkDateOfDismissal(id, dismissalDate)) {
                employeeService.dismissEmployeeById(id, dismissalDate);
            } else {
                throw new InvalidParametersOfEmployeeException();
            }
        } else {
            throw new InvalidValueOfEmployeeIdException();
        }
    }

    @RequestMapping(value = "/swapEmployeeToDepartment/{idEmployee}/{idPointer}", method = RequestMethod.PUT)
    public void swapEmployeeToDepartment(@PathVariable("idEmployee") int idEmployee, @PathVariable("idPointer") int idPointer) throws InvalidValueOfEmployeeIdException, InvalidParametersOfEmployeeException, UnavailableOperationForEmployeeException {
        if (employeeService.checkExistenceEmployee(idEmployee)) {
            employeeService.swapEmployeeToDepartment(idEmployee, idPointer);
            logger.info("Update successful");
        } else {
            throw new InvalidValueOfEmployeeIdException();
        }
    }

    @RequestMapping(value = "/swapAllEmployeesToDepartment/{idSwapped}/{idPointer}", method = RequestMethod.PUT)
    public void swapAllEmployeesToDepartment(@PathVariable("idSwapped") int idSwapped, @PathVariable("idPointer") int idPointer) throws InvalidValueOfDepartmentIdException {
        if (employeeService.checkExistenceDepartment(idSwapped) && employeeService.checkExistenceDepartment(idPointer)) {
            employeeService.swapAllEmployeesToDepartment(idSwapped, idPointer);
            logger.info("Update successful");
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }

    @RequestMapping(value = "/getChiefByIdEmployee/{id}", method = RequestMethod.GET)
    public Employee getChiefByIdEmployee(@PathVariable("id") int id) throws ChiefNotFoundException, UnavailableOperationForEmployeeException {
        Employee chief = employeeService.getChiefByIdEmployee(id);
        if (chief != null) {
            return chief;
        } else {
            throw new ChiefNotFoundException();
        }
    }

    @RequestMapping(value = "/searchEmployeeByPhoneNumber/{phoneNumber}", method = RequestMethod.GET)
    public List<Employee> searchEmployeeByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) throws InvalidParametersOfEmployeeException, EmployeesNotFoundException {
        List<Employee> result = employeeService.searchEmployeeByPhoneNumber(phoneNumber);
        if (!result.isEmpty()) {
            return result;
        } else {
            throw new EmployeesNotFoundException();
        }
    }
}
