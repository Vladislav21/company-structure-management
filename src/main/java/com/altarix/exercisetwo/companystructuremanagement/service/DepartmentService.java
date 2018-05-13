package com.altarix.exercisetwo.companystructuremanagement.service;

import com.altarix.exercisetwo.companystructuremanagement.dao.DepartmentDAO;
import com.altarix.exercisetwo.companystructuremanagement.domain.Department;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        return getDepartmentById(id);
    }

    public void delete(int id) {
        departmentDAO.delete(id);
    }

    public Department getDepartmentById(int id) {
        if (departmentDAO.isThereChiefDepartment(id)) {
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

    public List<Department> getLowLvlDepartments(int id) {
        return departmentDAO.getLowLvlDepartments(id);
    }

    public List<Department> getAllLowLvlDepartments(int id) {
        return departmentDAO.getAllLowLvlDepartments(id);
    }

    public void swapDepartment(int idSwapped, int idPointer) {
        departmentDAO.swapDepartment(idSwapped, idPointer);
    }

    public List<Department> getAllHighDepartments(int id) {
        return departmentDAO.getAllHighDepartments(id);
    }

    public double getFundOfSalary(int id) {
        return departmentDAO.getFundOfSalary(id);
    }

    public int checkIdChief(int chiefId) {
        return departmentDAO.checkIdChief(chiefId);
    }

    public boolean checkingDepartmentName(String name) {
        return departmentDAO.checkingDepartmentName(firstUpperCase(name));
    }

    public void appointChiefToDepartment(int chiefId, int id) {
        departmentDAO.appointChiefToDepartment(chiefId, id);
    }

    public void appointChiefToEmployees(int chiefId) {
        departmentDAO.appointChiefToEmployees(chiefId);
    }

    public boolean checkEmployeesOfDepartment(int chiefId) {
        return departmentDAO.checkEmployeesOfDepartment(chiefId);
    }

    public boolean checkExistenceEmployeeInDepartment(int id) {
        return departmentDAO.checkExistenceEmployeeInDepartment(id);
    }

    public boolean isThereDepartment(int id) {
        return departmentDAO.isThereDepartment(id);
    }

    private String firstUpperCase(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

}
