package com.app.repository;

import com.app.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>, EmployeeCustomRepo {

    List<Employee> findBySalaryGreaterThan(double amount);

    List<Employee> findByDateOfJoiningAfter(LocalDate date);

    @Query("Select d.name as Department, COUNT(e) as total_employees from Department d LEFT JOIN d.employees e group by d.name")
    List<Object> employeesInDepartment();

    @Query("Select e from Employee e JOIN e.departments d where d.name = :deptName")
    List<Employee> departmentEmployees(String deptName);

    @Query("Select d.name from Department d")
    List<String> findAllDepartmentNames();

    @Query("Select Distinct e.salary from Employee e Order by e.salary DESC")
    List<Integer> top3Salaries(Pageable pageable);

    @Query("Select e from Employee e where e.salary = :salary")
    List<Employee> findEmployeesBySalary(long salary);

}
