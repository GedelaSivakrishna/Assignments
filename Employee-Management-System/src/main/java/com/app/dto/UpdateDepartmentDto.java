package com.app.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateDepartmentDto {
    private String name;
    private String location;
    private List<Integer> empIds;
}
