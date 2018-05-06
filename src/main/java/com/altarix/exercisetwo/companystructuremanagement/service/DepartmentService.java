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

    public Department update(String name) {
        departmentDAO.update(name);
        return departmentDAO.searchDepartmentByName(name);
    }

    public void delete(int id) {
        departmentDAO.delete(id);
    }

    public Department getDepartmentById(int id) {
        return departmentDAO.getDepartmentById(id);
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

}
