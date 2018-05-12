package com.altarix.exercisetwo.companystructuremanagement.service;

import com.altarix.exercisetwo.companystructuremanagement.dao.EmployeeDAO;
import com.altarix.exercisetwo.companystructuremanagement.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private Double getSalaryChiefForCurrentDepartment(int idDepartment) {
        return employeeDAO.getSalaryChiefForCurrentDepartment(idDepartment);
    }

    public boolean checkValidDataOfEmployee(Employee employee) {
        return checkFullNameEmployee(employee.getLastName(), employee.getFirstName(), employee.getPatronymic())
                && checkPhoneNumber(employee.getPhoneNumber())
                && checkMail(employee.getMail())
                && checkDate(employee.getDateOfBirth(), employee.getEmploymentDate())
                && checkSalary(employee.getSalary(), employee.getDepartmentId());
    }

    private boolean checkFullNameEmployee(String lastName, String firstName, String patronymic) {
        Pattern pattern = Pattern.compile("[А-Яа-я-]+");
        String fullName = lastName + firstName + patronymic;
        Matcher matcher = pattern.matcher(fullName);
        return matcher.matches();
    }

    private boolean checkPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("[\\s\\(\\)0-9-+]+");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private boolean checkMail(String mail) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(mail);
            emailAddr.validate();
        } catch (AddressException e) {
            result = false;
        }
        return result;
    }

    private boolean checkDate(LocalDate dateOfBirth, LocalDate employmentDate) {
        return employmentDate.getYear() > dateOfBirth.getYear();
    }

    private boolean checkSalary(double salary, int idDepartment) {
        Double salaryChiefForCurrentDepartment = getSalaryChiefForCurrentDepartment(idDepartment);
        return salaryChiefForCurrentDepartment == null || salaryChiefForCurrentDepartment > salary;
    }
}


/*
 } else {
         if (employmentDate.getYear() > dateOfBirth.getYear() && dateOfDismissal.getYear() > employmentDate.getYear()) {
         return true;
         }
         if (employmentDate.getYear() > dateOfBirth.getYear() && dateOfDismissal.getYear() == employmentDate.getYear()
         && dateOfDismissal.getMonth().getValue() > employmentDate.getMonth().getValue()) {
         return true;
         }
         if (employmentDate.getYear() > dateOfBirth.getYear() && dateOfDismissal.getYear() == employmentDate.getYear()
         && dateOfDismissal.getMonth().getValue() == employmentDate.getMonth().getValue()
         && dateOfDismissal.getDayOfMonth() >= employmentDate.getDayOfMonth()) {
         return true;
         }
         }*/
