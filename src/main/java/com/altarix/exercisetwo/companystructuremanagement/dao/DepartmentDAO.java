package com.altarix.exercisetwo.companystructuremanagement.dao;

import com.altarix.exercisetwo.companystructuremanagement.domain.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentDAO {

    void add(Department department);

    void update(@Param("id") int id, @Param("name") String name);

    void delete(@Param("id") int id);

    Department getDepartmentById(@Param("id") int id);

    List<Department> getLowLvlDepartments(@Param("id") int id);

    List<Department> getAllLowLvlDepartments(@Param("id") int id);

    void swapDepartment(@Param("idSwapped") int idSwapped, @Param("idPointer") int idPointer);

    List<Department> getAllHighDepartments(@Param("id") int id);

    Department searchDepartmentByName(@Param("name") String name);

    double getFundOfSalary(@Param("id") int id);

    int checkIdChief(@Param("chiefId") int chiefId);

    boolean checkEmployeesOfDepartment(@Param("chiefId") int chiefId);

    boolean checkExistenceEmployeeInDepartment(@Param("id") int id);

    boolean checkingDepartmentName(@Param("name") String name);

    void appointChiefToEmployees(@Param("chiefId") int chiefId);

    void appointChiefToDepartment(@Param("chiefId") int chiefId, @Param("id") int id);

}
