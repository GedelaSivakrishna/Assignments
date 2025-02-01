package com.app.controller;

import com.app.dto.SalariedEmployees;
import com.app.model.Employee;
import com.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee, @RequestParam int deptId) {
        Employee savedEmployee = employeeService.addEmployee(employee, deptId);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping("employee/{empId}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, @PathVariable int empId) {
        Employee updatedEmployee = employeeService.updateEmployee(employee, empId);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("employee/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int empId) {
        String responseMsg = employeeService.deleteEmployee(empId);
        return new ResponseEntity<>(responseMsg, HttpStatus.OK);
    }

    @GetMapping("employees")
    public ResponseEntity<List<Employee>> allEmployees() {
        return new ResponseEntity<>(employeeService.allEmployees(), HttpStatus.OK);
    }

    @GetMapping("employees/salary")
    public ResponseEntity<?> employeesWithSalaryGreaterThan(@RequestParam double amount) {
        Object response = employeeService.employeesWithSalaryGreaterThan(amount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("employees/joined")
    public ResponseEntity<?> employeesJoinedIn(@RequestParam String key, @RequestParam int value) {
        Object response = employeeService.employeesJoinedIn(key, value);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("employees/department")
    public ResponseEntity<List<Object>> employeesInDepartment() {
        return new ResponseEntity<>(employeeService.employeesInDepartment(), HttpStatus.OK);
    }

    @GetMapping("department/employees")
    public ResponseEntity<Object> employeesAndDepartment(@RequestParam String deptName) {
        return new ResponseEntity<>(employeeService.departmentEmployees(deptName), HttpStatus.OK);
    }

    @GetMapping("employees/top3")
    public ResponseEntity<List<SalariedEmployees>> top3HighestPaidEmployees() {
        return new ResponseEntity<>(employeeService.topThreeHighestPaidEmployees(), HttpStatus.OK);
    }

    @GetMapping("employees/batch")
    public ResponseEntity<?> employeesInBatch(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "salary") String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Object response = employeeService.employeesInBatch(pageNo, size, sortBy, order);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("employees/salary/hike")
    public ResponseEntity<String> incrementEmployeesSalary(@RequestParam float percent) {
        String response = employeeService.incrementEmployeeSalaryBy(percent);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("department/transfer")
    public ResponseEntity<Employee> transferEmployeeDepartment(
            @RequestParam int empId,
            @RequestParam int fromDeptId,
            @RequestParam int toDeptId
    ) {
        return new ResponseEntity<>(employeeService.transferDepartment(empId, fromDeptId, toDeptId), HttpStatus.OK);
    }

    @PostMapping("department/employee/add")
    public ResponseEntity<Employee> addEmployeeToDepartment(@RequestParam int empId, @RequestParam int deptId) {
        return new ResponseEntity<>(employeeService.addEmployeeToDepartment(empId, deptId), HttpStatus.OK);
    }

    @PutMapping("department/employee/remove")
    public ResponseEntity<Employee> removeEmployeeFromDepartment(@RequestParam int empId, @RequestParam int deptId) {
        return new ResponseEntity<>(employeeService.removeEmployeeFromDepartment(empId, deptId), HttpStatus.OK);
    }

}
