package com.altarix.exercisetwo.companystructuremanagement.dao;

import com.altarix.exercisetwo.companystructuremanagement.domain.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentDAO {

    void add(Department department);

    void update(@Param("name") String name);

    void delete(@Param("id") int id);

    Department getDepartmentById(@Param("id") int id);

    List<Department> getLowLvlDepartments(@Param("id") int id);

    List<Department> getAllLowLvlDepartments(@Param("id") int id);

    void swapDepartment(@Param("idSwapped") int idSwapped, @Param("idPointer") int idPointer);

    List<Department> getAllHighDepartments(@Param("id") int id);

    Department searchDepartmentByName(@Param("name") String name);

    double getFundOfSalary(@Param("id") int id);

}
