package com.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateDepartmentDto {
    private String name;
    private String location;
    private List<Integer> empIds;

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

    public List<Integer> getEmpIds() {
        return empIds;
    }

    public void setEmpIds(List<Integer> empIds) {
        this.empIds = empIds;
    }

    public UpdateDepartmentDto(String name, String location, List<Integer> empIds) {
        this.name = name;
        this.location = location;
        this.empIds = empIds;
    }

    public UpdateDepartmentDto() {
    }
}
