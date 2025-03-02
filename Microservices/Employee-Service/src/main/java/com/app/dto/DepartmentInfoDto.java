package com.app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class DepartmentInfoDto {
    private int id;
    private String name;
    private String location;
    private int employeesCount;
    private List<EmployeeEmailDto> employees;

    public DepartmentInfoDto(int id, String name, String location, long employeesCount) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.employeesCount = (int) employeesCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
        this.employeesCount = employeesCount;
    }

    public List<EmployeeEmailDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeEmailDto> employees) {
        this.employees = employees;
    }

    public DepartmentInfoDto() {
    }
}
