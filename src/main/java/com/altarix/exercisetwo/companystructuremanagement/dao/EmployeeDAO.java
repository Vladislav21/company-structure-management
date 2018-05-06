package com.altarix.exercisetwo.companystructuremanagement.dao;

import com.altarix.exercisetwo.companystructuremanagement.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> getListEmployeeByDepartmentId(@Param("idDepartment") int idDepartment);

    boolean add(Employee employee);

    Employee update(Employee employee);

    boolean dismissEmployeeById(@Param("id") int id);

    Employee getEmployeeById(@Param("id") int id);

    boolean swapEmployeeToDepartment(@Param("idEmployee") int idEmployee, @Param("idPointer") int idPointer);

    boolean swapAllEmployeesToDepartment(@Param("idSwapped") int idSwapped, @Param("idPointer") int idPointer);

    Employee getChiefByIdEmployee(@Param("id") int id);

    Employee searchEmployeeByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
