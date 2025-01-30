package com.app.service;

import com.app.Exceptions.DepartmentException;
import com.app.Exceptions.EmployeeException;
import com.app.dto.SalariedEmployees;
import com.app.model.Department;
import com.app.model.Employee;
import com.app.repository.EmployeeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Gedela sivakrishna
 * @since 2025-01-30
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private DepartmentService departmentService;

    /**
     * Checks whether the employee with the given id exists in database
     * @param empId - The unique id of the employee
     * @return the employee matched with the id
     * @throws EmployeeException If employee with the given id is not found
     */
    @Override
    public Employee findEmployeeById(int empId) {
        return employeeRepo.findById(empId).orElseThrow(() -> new EmployeeException("Invalid employee id"));
    }

    /**
     * This method is to save the employee object
     * @param employee the employee who details need to be saved
     * @return saved employee
     */
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    /***
     * Creates a new Employee
     * @param employee the employee details  to create a new employee
     * @param deptId the unique id of the department
     * @return the newly created employee
     * @throws DepartmentException if department with the given id is not found
     */
    @Override
    public Employee addEmployee(Employee employee, int deptId) {
        Department department = departmentService.findDepartmentById(deptId);
        employee.setDepartments(List.of(department));
        return saveEmployee(employee);
    }

    /**
     * Updates the employee with the given information
     * @param reqEmployee the details of the employee which need to be updated
     * @param empId  the unique id of the employee
     * @return the updated employee
     * @throws EmployeeException if the employee with the given id is not found
     */
    @Override
    public Employee updateEmployee(Employee reqEmployee, int empId) {
        Employee employee = findEmployeeById(empId);
        // Name
        if(reqEmployee.getName() != null && !employee.getName().equals(reqEmployee.getName())) {
            employee.setName(reqEmployee.getName());
        }
        // Email
        if(reqEmployee.getEmail() != null && !employee.getEmail().equals(reqEmployee.getEmail())) {
            String email = reqEmployee.getEmail();
            employee.setEmail(email);
        }
        // Salary
        if(reqEmployee.getSalary() != 0 && employee.getSalary() != reqEmployee.getSalary()) {
            employee.setSalary(reqEmployee.getSalary());
        }
        // Date Of Joining
        if(reqEmployee.getDateOfJoining() != null && !employee.getDateOfJoining().equals(reqEmployee.getDateOfJoining())) {
            employee.setDateOfJoining(reqEmployee.getDateOfJoining());
        }
        return saveEmployee(employee);
    }

    /**
     * Deletes the employee with the given id
     * @param empId the unique id of the employee
     * @return Message whether the employee deleted from the database
     * @throws EmployeeException if employee not found with the given id
     */
    @Override
    public String  deleteEmployee(int empId) {
        Employee employee = findEmployeeById(empId);
        employeeRepo.delete(employee);
        return !employeeRepo.existsById(empId) ? "Employee deleted successfully " : "Error, While deleting Employee";
    }

    /**
     * Find's out all the employee details
     * @return a list of employee details
     */
    @Override
    public List<Employee> allEmployees() {
        return employeeRepo.findAll();
    }

    /**
     * Finds out all the employees whose salary is above the given amount
     * @param amount the salary amount
     * @return the list of employees if found or else returns no employees found message
     */
    @Override
    public Object employeesWithSalaryGreaterThan(double amount) {
        if(amount < 0)
            throw new EmployeeException("Enter valid amount ");
        List<Employee> employees = employeeRepo.findBySalaryGreaterThan(amount);
        if(employees.isEmpty())
            return "No employees with salary greater than " + (long)amount + " found";
        return employees;
    }

    /**
     * Finds out the employees joined in the recent tim
     * @param key string value indicates year, month, week, day
     * @param value the value of the keys like for 1 year / month / week / day
     * @return list of employees whose joining date is greater than the calculated date, if not found returns a
     *          a string message indicating, no employees found
     */
    @Override
    public Object employeesJoinedIn(String key, int value) {
        LocalDate date = switch(key.toUpperCase()) {
            case "Y", "YEAR", "YEARS" -> LocalDate.now().minusYears(value);
            case "M", "MONTH", "MONTHS" -> LocalDate.now().minusMonths(value);
            case "W", "WEEK", "WEEKS" -> LocalDate.now().minusWeeks(value);
            case "D", "DAY", "DAYS" -> LocalDate.now().minusDays(value);
            default -> throw new IllegalArgumentException("Excepted Key values y,year,years | m,month,months | w,week,weeks | d,day,days but got = " + key.toUpperCase());
        };
        List<Employee> employees = employeeRepo.findByDateOfJoiningAfter(date);
        if(employees.isEmpty()) {
            String message = "No Employees joined in the last " + value + switch(key.toUpperCase()) {
                case "Y", "YEAR", "YEARS" -> " years";
                case "M", "MONTH", "MONTHS" -> " months";
                case "W", "WEEK", "WEEKS" -> "  weeks";
                case "D", "DAY", "DAYS" -> " days";
                default -> "";
            };
            if(value == 1)
                message = message.substring(0, message.length()-1);
            return message;
        }
        return employees;
    }

    /**
     * Gives the count of employees in each department
     * @return  object which has the count of employees in each department
     */
    @Override
    public List<Object> employeesInDepartment() {
        return employeeRepo.employeesInDepartment();
    }

    /**
     * Finds out the employees from particular department
     * @param deptName the name of the department
     * @return list of employees who belongs to that department or No employees found if doesn't find any employees
     * @throws DepartmentException if the department name doesn't exist
     */
    @Override
    public Object departmentEmployees(String deptName) {
        List<String> allDepartmentNames = employeeRepo.findAllDepartmentNames();
        if(!allDepartmentNames.contains(deptName))
            throw new DepartmentException("Invalid department name!");
        List<Employee> employees = employeeRepo.departmentEmployees(deptName);
        return employees.isEmpty() ? "No employees found in " + deptName + " department" : employees;
    }

    /**
     * Find's out the employees who are getting top 3 highest salaries
     * @return employees who are getting top 3 highest salaries
     */
    @Override
    public List<SalariedEmployees> top3HighestPaidEmployees() {
        List<SalariedEmployees> result = new ArrayList<>();
        String[] labels = {"firstHighest", "secondHighest", "thirdHighest"};

        // Find out top 3 salaries
        Pageable pageable = PageRequest.of(0,3);
        List<Integer> topSalaries = employeeRepo.top3Salaries(pageable);

        // Find out the employees getting top salaries & Format the results
        for(int i = 0; i < topSalaries.size(); i++) {
            long salary = topSalaries.get(i);
            List<Employee> employees = employeeRepo.findEmployeesBySalary(salary);
            result.add(new SalariedEmployees(labels[i], salary, employees));
        }

        return result;
    }

    /**
     * Fetches the employee details in batch
     * @param pageNo the page number whose data need to be returned
     * @param size the number of elements / objects should be returned in a single page
     * @param sortBy the column name according to which the records need to be sorted
     * @param order  whether the records need to be sorted in ascending / descending order
     * @return page of employees if found else No employees found message
     * @throws IllegalArgumentException if any parameter values doesn't satisfy the required conditions
     */
    @Override
    public Object employeesInBatch(int pageNo, int size, String sortBy, String order) {
        if(size < 0)
            throw new IllegalArgumentException("Size should be a positive value ");
        if(!List.of("id", "salary", "email", "name", "dateOfJoining").contains(sortBy))
            throw new IllegalArgumentException("Expected column names [id, name, email, salary, dataOfJoining] but got " + sortBy);
        if(!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc"))
            throw new IllegalArgumentException("Invalid sorting order. Expected [asc, desc] but found " + order);
        int totalEmployees = allEmployees().size();
        int totalPages = totalEmployees / size;
        if(totalEmployees % size != 0)
                totalPages += 1;
        if(pageNo < 0 || pageNo >= totalPages) {
            throw new IllegalArgumentException("Page number should be between " + 0 + " and " + (totalPages-1));
        }
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() :
                                                            Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, size, sort);
        Page<Employee> employees = employeeRepo.findAll(pageable);
        if(employees.isEmpty())
            return "No Employees found in the database ";
        return employees.getContent();
    }

    /**
     * Increments the employees salary in bulk
     * @param percent the percent value of how much the salary need to be incremented
     * @return message whether the records are updated successfully or not
     * @throws IllegalArgumentException if the percent value is invalid
     */
    @Override
    public String incrementEmployeeSalaryBy(float percent) {
        if(percent <= 0) {
            throw new IllegalArgumentException("Invalid! Enter a valid percent value ");
        }
        int updatedRecords = employeeRepo.incrementSalaryBy(percent);
        return updatedRecords == allEmployees().size() ? "Employees salary updated by " + percent + " % " :
                "Error, While updating employees salary";
    }

    /**
     * Transfers the employee from one department to another
     * @param empId the unique id of the employee
     * @param fromDeptId the unique department id from which the employee need to be replaced
     * @param toDeptId the unique department id to which the employee need to be replaced
     * @return employee with  the updated department details
     * @throws IllegalArgumentException If the fromDepartment id == toDepartment id
     */
    @Override
    @Transactional
    public Employee transferDepartment(int empId, int fromDeptId, int toDeptId) {
        if(fromDeptId == toDeptId)
            throw new IllegalArgumentException("Cannot transfer employee to same department ");
        Employee employee = findEmployeeById(empId);
        Department fromDepartment = departmentService.findDepartmentById(fromDeptId);
        Department toDepartment = departmentService.findDepartmentById(toDeptId);
        fromDepartment.getEmployees().remove(employee);
        employee.getDepartments().remove(fromDepartment);
        toDepartment.getEmployees().add(employee);
        employee.getDepartments().add(toDepartment);

        departmentService.saveDepartment(fromDepartment);
        departmentService.saveDepartment(toDepartment);

        return saveEmployee(employee);
    }

    /**
     * Adds employee to a department
     * @param empId the unique id of the employee
     * @param deptId the unique id of the department
     * @return the employee with the department details updated
     * @throws EmployeeException If employee not found with the given id
     * @throws DepartmentException If department not found with the given id
     */
    @Override
    public Employee addEmployeeToDepartment(int empId, int deptId) {
        Employee employee = findEmployeeById(empId);
        Department department = departmentService.findDepartmentById(deptId);
        if(Objects.isNull(employee.getDepartments()))
            employee.setDepartments(List.of(department));
        else
            employee.getDepartments().add(department);
        return saveEmployee(employee);
    }

    /**
     * Removes the employee from a department
     * @param empId the unique id of the employee
     * @param deptId the unique id of the department
     * @return the employee with the updated department details
     * @throws EmployeeException If the employee not found with the given id
     * @throws DepartmentException If the department not found the given id
     */
    @Override
    public Employee removeEmployeeFromDepartment(int empId, int deptId) {
        Employee employee = findEmployeeById(empId);
        Department department = departmentService.findDepartmentById(deptId);
        // Remove employee from department's employee List
        department.getEmployees().remove(employee);
        // Remove the Department from the employe's department List
        employee.getDepartments().remove(department);
        departmentService.saveDepartment(department);
        return saveEmployee(employee);
    }
}
