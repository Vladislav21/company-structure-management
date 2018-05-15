package com.altarix.exercisetwo.companystructuremanagement.dao;

import com.altarix.exercisetwo.companystructuremanagement.domain.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentDAO {

    void add(Department department);

    void update(@Param("id") int id, @Param("name") String name);

    void delete(@Param("id") int id);

    Department getDepartmentById(@Param("id") int id);

    Department getDepartmentByIdWithoutChief(@Param("id") int id);

    List<Department> getDepartmentChildren(@Param("id") int id);

    List<Department> getAllDepartmentChildren(@Param("id") int id);

    void swapDepartment(@Param("idSwapped") int idSwapped, @Param("idPointer") int idPointer);

    List<Department> getAllDepartmentParents(@Param("id") int id);

    List<Department> searchDepartmentByName(@Param("name") String name);

    double getFundOfSalary(@Param("id") int id);

    int getDepartmentIdForEmployee(@Param("employeeId") int employeeId);

    boolean checkEmployeesOfDepartment(@Param("departmentId") int departmentId);

    boolean checkExistenceEmployeeInDepartment(@Param("id") int id);

    boolean checkingDepartmentName(@Param("name") String name);

    boolean checkExistenceDepartmentChief(@Param("id") int id);

    boolean checkExistenceDepartment(@Param("id") int id);

    void appointChiefToEmployees(@Param("chiefId") int chiefId);

    void appointChiefToDepartment(@Param("chiefId") int chiefId, @Param("id") int id);

    boolean checkExistenceDateOfDismissal(@Param("employeeId") int employeeId);

}
