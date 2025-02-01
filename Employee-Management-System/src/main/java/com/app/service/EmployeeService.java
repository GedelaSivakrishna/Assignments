package com.app.service;

import com.app.dto.SalariedEmployees;
import com.app.model.Employee;
import java.util.List;

public interface EmployeeService {

    Employee findEmployeeById(int empId);

    Employee saveEmployee(Employee employee);

    Employee addEmployee(Employee employee, int deptId);

    Employee updateEmployee(Employee employee, int empId);

    String deleteEmployee(int empId);

    List<Employee> allEmployees();

    Object employeesWithSalaryGreaterThan(double amount);

    Object employeesJoinedIn(String key, int value);

    List<Object> employeesInDepartment();

    Object departmentEmployees(String deptName);

    List<SalariedEmployees> topThreeHighestPaidEmployees();

    Object employeesInBatch(int pageNo, int size, String sortBy, String direction);

    String incrementEmployeeSalaryBy(float percent);

    Employee transferDepartment(int empId, int fromDeptId, int toDeptId);

    Employee addEmployeeToDepartment(int empId, int deptId);

    Employee removeEmployeeFromDepartment(int empId, int deptId);

}
