package com.altarix.exercisetwo.companystructuremanagement.controllers;

import com.altarix.exercisetwo.companystructuremanagement.domain.Department;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.*;
import com.altarix.exercisetwo.companystructuremanagement.service.DepartmentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            departmentService.saveActionIntoLogs("Upload succeeded");
        } else {
            throw new InvalidValueOfDepartmentNameException();
        }
    }

    @RequestMapping(value = "/appointChief/{idDepartment}/{idChief}", method = RequestMethod.PUT)
    public void appointChief(@PathVariable("idDepartment") int idDepartment, @PathVariable("idChief") int idChief) throws InvalidValueOfChiefException, InvalidValueOfDepartmentIdException, UnavailableOperationForEmployeeException {
        if (departmentService.checkExistenceDepartment(idDepartment)) {
            if (departmentService.getDepartmentIdForEmployee(idChief) == idDepartment
                    && !departmentService.checkEmployeesOfDepartment(idDepartment)) {
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
        if (departmentService.checkExistenceDepartment(id)) {
            if (!departmentService.checkingDepartmentName(name)) {
                return departmentService.update(id, name);
            } else {
                throw new InvalidValueOfDepartmentNameException();
            }
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") int id) throws InvalidValueOfDepartmentIdException {
        if (departmentService.checkExistenceDepartment(id)) {
            if (!departmentService.checkExistenceEmployeeInDepartment(id)) {
                departmentService.delete(id);
                logger.info("Uninstall succeeded");
                departmentService.saveActionIntoLogs("Uninstall succeeded");
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

    @RequestMapping(value = "/getDepartmentChildren/{id}", method = RequestMethod.GET)
    public List<Department> getDepartmentChildren(@PathVariable("id") int id) throws DepartmentsNotFoundException {
        List<Department> result = departmentService.getDepartmentChildren(id);
        if (!result.isEmpty()) {
            return result;
        } else {
            throw new DepartmentsNotFoundException();
        }
    }

    @RequestMapping(value = "/getAllDepartmentChildren/{id}", method = RequestMethod.GET)
    public List<Department> getAllDepartmentChildren(@PathVariable("id") int id) throws DepartmentsNotFoundException {
        List<Department> result = departmentService.getAllDepartmentChildren(id);
        if (!result.isEmpty()) {
            return result;
        } else {
            throw new DepartmentsNotFoundException();
        }
    }

    @RequestMapping(value = "/swapDepartment/{idSwapped}/{idPointer}", method = RequestMethod.PUT)
    public void swapDepartment(@PathVariable("idSwapped") int idSwapped, @PathVariable("idPointer") int idPointer) throws InvalidValueOfDepartmentIdException {
        if (departmentService.checkExistenceDepartment(idSwapped)) {
            departmentService.swapDepartment(idSwapped, idPointer);
            logger.info("Swapping successful");
            departmentService.saveActionIntoLogs("Swapping successful");
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }

    @RequestMapping(value = "/getAllDepartmentParents/{id}", method = RequestMethod.GET)
    public List<Department> getAllDepartmentParents(@PathVariable("id") int id) throws DepartmentsNotFoundException {
        List<Department> result = departmentService.getAllDepartmentParents(id);
        if (!result.isEmpty()) {
            return result;
        } else {
            throw new DepartmentsNotFoundException();
        }
    }

    @RequestMapping(value = "/searchDepartmentByName/{name}", method = RequestMethod.GET)
    public List<Department> searchDepartmentByName(@PathVariable("name") String name) throws DepartmentsNotFoundException {
        List<Department> result = departmentService.searchDepartmentByName(name);
        if (!result.isEmpty()) {
            return result;
        } else {
            throw new DepartmentsNotFoundException();
        }
    }

    @RequestMapping(value = "/getFundOfSalary/{id}", method = RequestMethod.GET)
    public String getFundOfSalary(@PathVariable("id") int id) throws InvalidValueOfDepartmentIdException {
        if (departmentService.checkExistenceDepartment(id) && departmentService.checkExistenceEmployeeInDepartment(id)) {
            return "{\"fund_of_department\":" + String.valueOf(departmentService.getFundOfSalary(id)) + "}";
        } else {
            throw new InvalidValueOfDepartmentIdException();
        }
    }
}
