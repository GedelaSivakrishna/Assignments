package com.app.specifications;

import com.app.model.Department;
import com.app.model.Employee;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeSpecification {

    public Specification<Employee> hasSalaryGreaterThan(long salary) {
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThan(root.get("salary"), salary);
    }

    public Specification<Employee> hiredAfter(LocalDate date) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.greaterThan(root.get("dateOfJoining"), date);
    }

    public Specification<Employee> worksInDepartment(String departmentName) {
        return (root, query, criteriaBuilder) -> {
            // Join Employee & Department Table
            Join<Employee, Department> joinedTable = root.join("departments");
            return criteriaBuilder.equal(joinedTable.get("name"), departmentName);
        };
    }
}
