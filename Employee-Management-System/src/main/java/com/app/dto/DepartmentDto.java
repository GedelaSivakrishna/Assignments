package com.app.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @NotBlank(message = "Please enter the department name ")
    @Schema(description = "Department name" ,example = "HR")
    private String name;
    @NotBlank(message = "Please enter the department location")
    @Schema(description = "Department location" ,example = "BBSR")
    private String location;
}
