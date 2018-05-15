package com.altarix.exercisetwo.companystructuremanagement.service;

import com.altarix.exercisetwo.companystructuremanagement.dao.DepartmentDAO;
import com.altarix.exercisetwo.companystructuremanagement.domain.Department;
import com.altarix.exercisetwo.companystructuremanagement.exceptions.UnavailableOperationForEmployeeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final static Logger logger = Logger.getLogger(DepartmentService.class.getSimpleName());

    @Autowired
    private DepartmentDAO departmentDAO;

    public void add(Department department) {
        String s = firstUpperCase(department.getName());
        department.setName(s);
        departmentDAO.add(department);
    }

    public Department update(int id, String name) {
        departmentDAO.update(id, firstUpperCase(name));
        logger.info("Update successful");
        saveActionIntoLogs("Update successful");
        return getDepartmentById(id);
    }

    public void delete(int id) {
        departmentDAO.delete(id);
    }

    public Department getDepartmentById(int id) {
        if (departmentDAO.checkExistenceDepartmentChief(id)) {
            return departmentDAO.getDepartmentById(id);
        } else {
            return departmentDAO.getDepartmentByIdWithoutChief(id);
        }
    }

    public List<Department> searchDepartmentByName(String name) {
        String result = firstUpperCase(name);
        String s = "%" +
                result +
                "%";
        return departmentDAO.searchDepartmentByName(s);
    }

    public List<Department> getDepartmentChildren(int id) {
        return departmentDAO.getDepartmentChildren(id);
    }

    public List<Department> getAllDepartmentChildren(int id) {
        return departmentDAO.getAllDepartmentChildren(id);
    }

    public void swapDepartment(int idSwapped, int idPointer) {
        departmentDAO.swapDepartment(idSwapped, idPointer);
    }

    public List<Department> getAllDepartmentParents(int id) {
        return departmentDAO.getAllDepartmentParents(id);
    }

    public double getFundOfSalary(int id) {
        return departmentDAO.getFundOfSalary(id);
    }

    public int getDepartmentIdForEmployee(int employeeId) {
        return departmentDAO.getDepartmentIdForEmployee(employeeId);
    }

    public boolean checkingDepartmentName(String name) {
        return departmentDAO.checkingDepartmentName(firstUpperCase(name));
    }

    public void appointChiefToDepartment(int chiefId, int id) throws UnavailableOperationForEmployeeException {
        if (!checkExistenceDateOfDismissal(chiefId)) {
            departmentDAO.appointChiefToDepartment(chiefId, id);
        } else {
            throw new UnavailableOperationForEmployeeException();
        }
    }

    public void appointChiefToEmployees(int chiefId) throws UnavailableOperationForEmployeeException {
        if (!checkExistenceDateOfDismissal(chiefId)) {
            departmentDAO.appointChiefToEmployees(chiefId);
        } else {
            throw new UnavailableOperationForEmployeeException();
        }
    }

    public boolean checkEmployeesOfDepartment(int departmentId) {
        return departmentDAO.checkEmployeesOfDepartment(departmentId);
    }

    public boolean checkExistenceEmployeeInDepartment(int id) {
        return departmentDAO.checkExistenceEmployeeInDepartment(id);
    }

    public boolean checkExistenceDepartment(int id) {
        return departmentDAO.checkExistenceDepartment(id);
    }

    private String firstUpperCase(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    private boolean checkExistenceDateOfDismissal(int employeeId) {
        return departmentDAO.checkExistenceDateOfDismissal(employeeId);
    }

    public void saveActionIntoLogs(String action) {
        departmentDAO.saveActionIntoLogs(action);
    }

    @Scheduled(fixedRate = 300000)
    public void saveInfoAboutFundOfSalary() {
        departmentDAO.saveInfoAboutFundOfSalary();
        logger.info("Preservation of data for salary fund");
    }
}
