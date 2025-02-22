package com.app.dto;

import lombok.AllArgsConstructor;
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

}
