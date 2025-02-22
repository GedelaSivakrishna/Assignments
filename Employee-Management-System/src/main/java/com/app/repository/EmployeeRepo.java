package com.app.repository;

import com.app.dto.DepartmentInfoDto;
import com.app.dto.EmployeeEmailDto;
import com.app.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer>, EmployeeCustomRepo, JpaSpecificationExecutor<Employee> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findBySalaryGreaterThan(double amount);

    List<Employee> findByDateOfJoiningAfter(LocalDate date);

    // Here using constructor projection to fetch only the required results from the entity
    @Query(value = "SELECT new com.app.dto.DepartmentInfoDto(d.id, d.name, d.location, COUNT(e)) " +
            "FROM Department d LEFT JOIN d.employees e GROUP BY d.id, d.name, d.location",
            countQuery = "SELECT COUNT(DISTINCT d.id) FROM Department d")
    // Here the countQuery, counts the total departments count, which is required for pagination
    Page<DepartmentInfoDto> employeesInDepartment(Pageable pageable);

    @Query("SELECT new com.app.dto.EmployeeEmailDto(e.id, e.email) " +
            "FROM Employee e JOIN e.departments d " +
            "WHERE d.id = :departmentId")
    List<EmployeeEmailDto> findEmployeeEmailsByDepartmentId(@Param("departmentId") int departmentId);


    @Query("Select e from Employee e JOIN e.departments d where d.name = :deptName")
    List<Employee> departmentEmployees(String deptName);

    @Query("Select d.name from Department d")
    List<String> findAllDepartmentNames();

    @Query("Select Distinct e.salary from Employee e Order by e.salary DESC")
    List<Integer> top3Salaries(Pageable pageable);

    @Query("Select e from Employee e where e.salary = :salary")
    List<Employee> findEmployeesBySalary(long salary);

    @Query(value = "CALL getEmployeeById(:empId);", nativeQuery = true)
    Employee findEmployeeUsingStoredProcedure(@Param("empId") int empId);

}
