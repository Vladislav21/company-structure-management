package com.altarix.exercisetwo.companystructuremanagement.service;

import com.altarix.exercisetwo.companystructuremanagement.dao.DepartmentDAO;
import com.altarix.exercisetwo.companystructuremanagement.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    public void add(Department department) {
        departmentDAO.add(department);
    }

    public Department update(int id, String name) {
        departmentDAO.update(id, name);
        return departmentDAO.getDepartmentById(id);
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
        return departmentDAO.checkingDepartmentName(name);
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


}
