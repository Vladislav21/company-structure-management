package com.altarix.exercisetwo.companystructuremanagement.service;

import com.altarix.exercisetwo.companystructuremanagement.dao.EmployeeDAO;
import com.altarix.exercisetwo.companystructuremanagement.domain.Employee;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.InvalidParamOfEmployeeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployeeService {

    private final static Logger logger = Logger.getLogger(EmployeeService.class.getSimpleName());

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
        logger.info("Update successful");
        return getEmployeeById(employee.getId());
    }

    public void dismissEmployeeById(int id, Date dateOfDismissal) {
        employeeDAO.dismissEmployeeById(id, dateOfDismissal);
        logger.info("The dismissal was successful");
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public void swapEmployeeToDepartment(int idEmployee, int idPointer) throws InvalidParamOfEmployeeException {
        if (checkSalary(employeeDAO.getSalaryForEmployee(idEmployee), idPointer)) {
            if (employeeDAO.isThereChiefCurrentEmployee(idEmployee)) {
                employeeDAO.updateDepartmentOnChief(idEmployee);
                employeeDAO.swapEmployeeToDepartment(idEmployee, idPointer);
            } else {
                employeeDAO.swapEmployeeToDepartment(idEmployee, idPointer);
            }
        } else {
            throw new InvalidParamOfEmployeeException();
        }
    }

    public void swapAllEmployeesToDepartment(int idSwapped, int idPointer) throws InvalidParamOfEmployeeException {
        if (employeeDAO.isThereChief(idPointer)) {
            if (!employeeDAO.isThereChief(idSwapped)) {
                employeeDAO.swapAllEmployeesToDepartmentIfExistsChief(idSwapped, idPointer);
            } else {
                int idChief = employeeDAO.getChiefIdInCurrentDepartment(idSwapped);
                if (checkSalary(employeeDAO.getSalaryForEmployee(idChief), idPointer)) {
                    employeeDAO.updateDepartmentOnChief(idChief);
                    employeeDAO.swapAllEmployeesToDepartmentIfExistsChief(idSwapped, idPointer);
                } else {
                    throw new InvalidParamOfEmployeeException();
                }
            }
        } else {
            if (!employeeDAO.isThereChief(idSwapped)) {
                employeeDAO.swapAllEmployeesToDepartment(idSwapped, idPointer);
            } else {
                int idChief = employeeDAO.getChiefIdInCurrentDepartment(idSwapped);
                employeeDAO.updateDepartmentOnChief(idChief);
                employeeDAO.swapAllEmployeesToDepartment(idSwapped, idPointer);
            }
        }
    }

    public Employee getChiefByIdEmployee(int id) {
        return employeeDAO.getChiefByIdEmployee(id);
    }

    public List<Employee> searchEmployeeByPhoneNumber(String phoneNumber) throws InvalidParamOfEmployeeException {
        if (checkPhoneNumber(phoneNumber)) {
            String s = "%" +
                    phoneNumber +
                    "%";
            return employeeDAO.searchEmployeeByPhoneNumber(s);
        } else {
            throw new InvalidParamOfEmployeeException();
        }
    }

    public boolean isThereEmployee(int id) {
        return employeeDAO.isThereEmployee(id);
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
        return salaryChiefForCurrentDepartment == null || salaryChiefForCurrentDepartment >= salary;
    }

    @SuppressWarnings("deprecation")
    public boolean checkDateOfDismissal(int id, Date dateOfDismissal) {
        Date employmentDate = employeeDAO.getEmploymentDate(id);
        return dateOfDismissal.getYear() > employmentDate.getYear() ||
                dateOfDismissal.getYear() == employmentDate.getYear()
                        && dateOfDismissal.getMonth() > employmentDate.getMonth() ||
                dateOfDismissal.getYear() == employmentDate.getYear()
                        && dateOfDismissal.getMonth() == employmentDate.getMonth()
                        && dateOfDismissal.getDay() >= employmentDate.getDay();
    }

    public boolean isThereDepartment(int id) {
        return employeeDAO.isThereDepartment(id);
    }
}

