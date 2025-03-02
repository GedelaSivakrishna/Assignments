package com.app.service;

import com.app.dto.DepartmentInfoDto;
import com.app.dto.EmployeeDto;
import com.app.dto.PerformanceDto;
import com.app.dto.SalariedEmployees;
import com.app.model.Employee;
import org.openapitools.client.ApiException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

    Employee findEmployeeById(int empId);
    // fetch the data using Client API
    List<org.openapitools.client.model.Employee> getEmployees() throws ApiException;

    Employee saveEmployee(Employee employee);

    Employee addEmployee(EmployeeDto employeeDto, List<Integer> deptIds);

    Employee updateEmployee(EmployeeDto employeeDto, int empId);

    String deleteEmployee(int empId);

    List<Employee> allEmployees();

    Object employeesWithSalaryGreaterThan(double amount);

    Object employeesJoinedIn(String key, int value);

    Page<DepartmentInfoDto> employeesInDepartment(int pageNo, int size);

    Object departmentEmployees(String deptName);

    List<SalariedEmployees> topThreeHighestPaidEmployees();

    Page<Employee> employeesInBatch(int pageNo, int size, String sortBy, String direction);

    String incrementEmployeeSalaryBy(float percent);

    Employee transferDepartment(int empId, int fromDeptId, int toDeptId);

    Employee addEmployeeToDepartment(int empId, int deptId);

    Employee removeEmployeeFromDepartment(int empId, int deptId);

    List<Employee> findAllEmployeesInDepartmentWithSalaryGreaterThanAndJoinedAfter(String departmentName, long salary, LocalDate date);

    Employee getEmployeeUsingStoredProcedure(int empId);

    ResponseEntity<PerformanceDto> createEmployeePerformance(int employeeId, PerformanceDto performanceDto);

    ResponseEntity<List<PerformanceDto>> viewAllEmployeePerformance(int employeeId);

    ResponseEntity<PerformanceDto> viewEmployeeWeeklyPerformance(int employeeId, int weekId);

    String topPerformingEmployee();

    String leastPerformingEmployee();

    ResponseEntity<PerformanceDto> updateEmployeeWeeklyPerformance(int employeeId, int weekId, PerformanceDto updatedPerformance);

}
