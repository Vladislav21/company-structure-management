package com.altarix.exercisetwo.companystructuremanagement.service;

import com.altarix.exercisetwo.companystructuremanagement.dao.EmployeeDAO;
import com.altarix.exercisetwo.companystructuremanagement.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;

    public List<Employee> getListEmployeeByDepartmentId(int idDepartment) {
        return employeeDAO.getListEmployeeByDepartmentId(idDepartment);
    }

    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

    public Employee update(Employee employee) {
        employeeDAO.update(employee);
        return employeeDAO.getEmployeeById(employee.getId());
    }

    public void dismissEmployeeById(int id) {
        employeeDAO.dismissEmployeeById(id);
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public void swapEmployeeToDepartment(int idEmployee, int idPointer) {
        employeeDAO.swapEmployeeToDepartment(idEmployee, idPointer);
    }

    public void swapAllEmployeesToDepartment(int idSwapped, int idPointer) {
        employeeDAO.swapAllEmployeesToDepartment(idSwapped, idPointer);
    }

    public Employee getChiefByIdEmployee(int id) {
        return employeeDAO.getChiefByIdEmployee(id);
    }

    public Employee searchEmployeeByPhoneNumber(String phoneNumber) {
        return employeeDAO.searchEmployeeByPhoneNumber(phoneNumber);
    }

}
