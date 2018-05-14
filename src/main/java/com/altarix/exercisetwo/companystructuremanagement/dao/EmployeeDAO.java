package com.altarix.exercisetwo.companystructuremanagement.dao;

import com.altarix.exercisetwo.companystructuremanagement.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface EmployeeDAO {

    List<Employee> getListEmployeeByDepartmentId(@Param("idDepartment") int idDepartment);

    void add(Employee employee);

    void update(Employee employee);

    void dismissEmployeeById(@Param("id") int id, @Param("dateOfDismissal") Date dateOfDismissal);

    Employee getEmployeeById(@Param("id") int id);

    Double getSalaryChiefForCurrentDepartment(@Param("idDepartment") int idDepartment);

    void swapEmployeeToDepartment(@Param("idEmployee") int idEmployee, @Param("idPointer") int idPointer);

    void swapAllEmployeesToDepartment(@Param("idSwapped") int idSwapped, @Param("idPointer") int idPointer);

    void swapAllEmployeesToDepartmentIfExistsChief(@Param("idSwapped") int idSwapped, @Param("idPointer") int idPointer);

    Employee getChiefByIdEmployee(@Param("id") int id);

    List<Employee> searchEmployeeByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    boolean checkExistenceEmployee(@Param("id") int id);

    Date getEmploymentDate(@Param("id") int id);

    Double getSalaryForEmployee(@Param("idEmployee") int idEmployee);

    boolean checkExistenceChief(@Param("id") int id);

    boolean checkExistenceDepartment(@Param("id") int id);

    boolean checkExistenceChiefCurrentEmployee(@Param("id") int id);

    void updateDepartmentChief(@Param("idChief") int idChief);

    Integer getChiefIdInCurrentDepartment(@Param("idDepartment") int idDepartment);
}
