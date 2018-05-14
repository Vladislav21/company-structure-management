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
    public void add(@RequestBody Employee employee) throws InvalidParamOfEmployeeException {
        if (employeeService.checkValidDataOfEmployee(employee)) {
            employeeService.add(employee);
            logger.info("Upload succeeded");
        } else {
            throw new InvalidParamOfEmployeeException();
        }
    }

    @RequestMapping(value = "/putEmployee/{id}", method = RequestMethod.PUT)
    public Employee update(@RequestBody Employee employee, @PathVariable("id") int id) throws InvalidParamOfEmployeeException, InvalidValueOfEmployeeIdException {
        if (employeeService.isThereEmployee(id)) {
            employee.setId(id);
            if (employeeService.checkValidDataOfEmployee(employee)) {
                return employeeService.update(employee);
            } else {
                throw new InvalidParamOfEmployeeException();
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
    public void dismissEmployeeById(@PathVariable("id") int id, @PathVariable("dateOfDismissal") String dateOfDismissal) throws InvalidValueOfEmployeeIdException, InvalidParamOfEmployeeException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date disnissalDate = format.parse(dateOfDismissal);
        if (employeeService.isThereEmployee(id)) {
            if (employeeService.checkDateOfDismissal(id, disnissalDate)) {
                employeeService.dismissEmployeeById(id, disnissalDate);
            } else {
                throw new InvalidParamOfEmployeeException();
            }
        } else {
            throw new InvalidValueOfEmployeeIdException();
        }
    }

    @RequestMapping(value = "/swapEmployeeToDepartment/{idEmployee}/{idPointer}", method = RequestMethod.PUT)
    public void swapEmployeeToDepartment(@PathVariable("idEmployee") int idEmployee, @PathVariable("idPointer") int idPointer) throws InvalidValueOfEmployeeIdException, InvalidParamOfEmployeeException {
        if (employeeService.isThereEmployee(idEmployee)) {
            employeeService.swapEmployeeToDepartment(idEmployee, idPointer);
            logger.info("Update successful");
        } else {
            throw new InvalidValueOfEmployeeIdException();
        }
    }

    @RequestMapping(value = "/swapAllEmployeesToDepartment/{idSwapped}/{idPointer}",method = RequestMethod.PUT)
    public void swapAllEmployeesToDepartment(@PathVariable("idSwapped") int idSwapped, @PathVariable("idPointer") int idPointer) throws InvalidValueOfDepartmentIdException {
        if (employeeService.isThereDepartment(idSwapped) && employeeService.isThereDepartment(idPointer)) {
            employeeService.swapAllEmployeesToDepartment(idSwapped, idPointer);
            logger.info("Update successful");
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }

    @RequestMapping(value = "/getChiefByIdEmployee/{id}", method = RequestMethod.GET)
    public Employee getChiefByIdEmployee(@PathVariable("id") int id) throws ChiefNotFoundException {
        Employee chief = employeeService.getChiefByIdEmployee(id);
        if (chief != null) {
            return chief;
        } else {
            throw new ChiefNotFoundException();
        }
    }

    @RequestMapping(value = "/searchEmployeeByPhoneNumber/{phoneNumber}", method = RequestMethod.GET)
    public List<Employee> searchEmployeeByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) throws InvalidParamOfEmployeeException, EmployeesNotFoundException {
        List<Employee> result = employeeService.searchEmployeeByPhoneNumber(phoneNumber);
        if (!result.isEmpty()) {
            return result;
        } else {
            throw new EmployeesNotFoundException();
        }
    }
}
