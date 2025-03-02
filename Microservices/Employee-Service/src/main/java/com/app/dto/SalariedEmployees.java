package com.app.dto;

import com.app.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalariedEmployees {
    private String category;
    private long salary;
    private  List<Employee> employees;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public SalariedEmployees(String category, long salary, List<Employee> employees) {
        this.category = category;
        this.salary = salary;
        this.employees = employees;
    }

    public SalariedEmployees() {
    }
}
