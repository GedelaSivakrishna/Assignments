package com.app.controller;

import com.app.dto.DepartmentInfoDto;
import com.app.dto.EmployeeDto;
import com.app.dto.SalariedEmployees;
import com.app.model.Employee;
import com.app.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.openapitools.client.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Add Employee")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "New Employee created")
            }
    )
    @PostMapping("employee")
    public ResponseEntity<Employee> addEmployee(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Employee to create",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = EmployeeDto.class)
            )) @Valid @RequestBody EmployeeDto employeeDto) {
        Employee savedEmployee = employeeService.addEmployee(employeeDto, employeeDto.getDeptIds());
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @Operation(summary = "Update Employee")
    @PutMapping("employee/{empId}")
    public ResponseEntity<Employee> updateEmployee(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Employee details to update",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = EmployeeDto.class)
            )
                                                       ) @RequestBody EmployeeDto employeeDto,
         @Parameter(description = "Id of the employee") @PathVariable int empId) {
        Employee updatedEmployee = employeeService.updateEmployee(employeeDto, empId);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @Operation(summary = "Delete Employee", description = "Employee should exist")
    @DeleteMapping("employee/{empId}")
    public ResponseEntity<String> deleteEmployee(
            @Parameter(description = "Id of the employee") @PathVariable int empId) {
        String responseMsg = employeeService.deleteEmployee(empId);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    @Operation(summary = "List all Employees")
    @GetMapping("employees")
    public ResponseEntity<List<Employee>> allEmployees() {
        return new ResponseEntity<>(employeeService.allEmployees(), HttpStatus.OK);
    }

    @Operation(summary = "Find Employees with salary greater than")
    @GetMapping("employees/salary")
    public ResponseEntity<?> employeesWithSalaryGreaterThan(
            @Parameter(description = "Salary amount") @RequestParam double amount) {
        Object response = employeeService.employeesWithSalaryGreaterThan(amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Employee joined recently")
    @GetMapping("employees/joined")
    public ResponseEntity<?> employeesJoinedIn(
            @Parameter(description = "Search by year,month,week,day") @RequestParam String key,
            @Parameter(description = "value of year,month,week,day") @RequestParam int value) {
        Object response = employeeService.employeesJoinedIn(key, value);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Department employees count")
    @GetMapping("employees/department")
    public ResponseEntity<Page<DepartmentInfoDto>> employeesInDepartment(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(employeeService.employeesInDepartment(pageNo, size), HttpStatus.OK);
    }

    @Operation(summary = "Department employees")
    @GetMapping("department/employees")
    public ResponseEntity<Object> employeesAndDepartment(
            @Parameter(description = "Department name", required = true) @RequestParam String deptName) {
        return new ResponseEntity<>(employeeService.departmentEmployees(deptName), HttpStatus.OK);
    }

    @Operation(summary = "Top Three salaried employees")
    @GetMapping("employees/top/salaries")
    public ResponseEntity<List<SalariedEmployees>> topThreeHighestPaidEmployees() {
        return new ResponseEntity<>(employeeService.topThreeHighestPaidEmployees(), HttpStatus.OK);
    }

    @Operation(summary = "Employees in Batch")
    @GetMapping("employees/batch")
    public ResponseEntity<Page<Employee>> employeesInBatch(
            @Parameter(description = "Page number") @RequestParam int pageNo,
            @Parameter(description = "Page size") @RequestParam int size,
            @Parameter(description = "Column name by which employees need to be sorted") @RequestParam(defaultValue = "name") String sortBy,
            @Parameter(description = "Id of the employee") @RequestParam(defaultValue = "asc") String order
    ) {
        System.out.println("Requested Page No: " + pageNo);
        System.out.println("Requested Page size: " + size);
        Page<Employee> response = employeeService.employeesInBatch(pageNo, size, sortBy, order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Update Employees salary")
    @PutMapping("employees/salary/hike")
    public ResponseEntity<String> incrementEmployeesSalary(
            @Parameter(description = "Increment salary percentage value") @RequestParam float percent) {
        String response = employeeService.incrementEmployeeSalaryBy(percent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Transfer Employees Department")
    @PutMapping("department/transfer")
    public ResponseEntity<Employee> transferEmployeeDepartment(
            @Parameter(description = "Id of the employee") @RequestParam int empId,
            @Parameter(description = "Id of the department from which employee to be transferred") @RequestParam int fromDeptId,
            @Parameter(description = "Id of the department to which employee to be transferred") @RequestParam int toDeptId
    ) {
        return new ResponseEntity<>(employeeService.transferDepartment(empId, fromDeptId, toDeptId), HttpStatus.OK);
    }

    @Operation(summary = "Add Employee to department", description = "Employee Id & Department Id should be valid ")
    @PostMapping("department/employee/add")
    public ResponseEntity<Employee> addEmployeeToDepartment(
            @Parameter(description = "Id of the employee") @RequestParam int empId,
            @Parameter(description = "Id of the department") @RequestParam int deptId) {
        return new ResponseEntity<>(employeeService.addEmployeeToDepartment(empId, deptId), HttpStatus.OK);
    }

    @Operation(summary = "Remove Employee from department", description = "Employee Id & Department Id should be valid ")
    @PutMapping("department/employee/remove")
    public ResponseEntity<Employee> removeEmployeeFromDepartment(
            @Parameter(description = "Id of the employee") @RequestParam int empId,
            @Parameter(description = "Id of the department") @RequestParam int deptId) {
        return new ResponseEntity<>(employeeService.removeEmployeeFromDepartment(empId, deptId), HttpStatus.OK);
    }

    @GetMapping("/get/all/employees")
    public ResponseEntity<List<org.openapitools.client.model.Employee>> getAllEmployees() throws ApiException {
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }

    @GetMapping("/jpa/specification")
    public ResponseEntity<List<Employee>> allEmployeesWorksInDepartmentWithSalaryGreaterThanAndJoinedAfter(
            @Parameter(description = "Department Name", example = "HR") @RequestParam String dname,
            @Parameter(description = "Filtering Salary Amount", example = "10000") @RequestParam long salary,
            @Parameter(description = "Filtering Joining Date", example = "2025-01-01") @RequestParam LocalDate date) {
        return new ResponseEntity<>(employeeService.findAllEmployeesInDepartmentWithSalaryGreaterThanAndJoinedAfter(dname, salary,date),
                HttpStatus.OK);
    }

    @GetMapping("/employee/stp/{empId}")
    public ResponseEntity<Employee> getEmployeeFromStoredProcedure(@PathVariable int empId) {
        return new ResponseEntity<>(employeeService.getEmployeeUsingStoredProcedure(empId), HttpStatus.OK);
    }

}
