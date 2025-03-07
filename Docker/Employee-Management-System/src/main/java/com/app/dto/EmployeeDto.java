package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @NotBlank(message = "Please enter the employee name ")
    @Schema(description = "Employee name" ,example ="sivakrishna")
    private String name;
    @NotBlank(message = "Please enter the email ")
    @Email(message = "Invalid email!")
    @Schema(description = "Employee email",example = "example@gmail.com")
    private String email;
    @NotNull(message = "Please enter the salary")
    @Positive(message = "Salary must be positive number")
    @Schema(description = "Employee salary",example = "15000")
    private long salary;
    @NotNull(message = "Please enter the employee date of joining ")
    @Schema(description = "Employee joining date",example = "2025-01-06")
    private LocalDate dateOfJoining;
    @Schema(description = "Department Ids", example = "1,2,3")
    private List<Integer> deptIds;
//    @NotBlank(message = "Please enter language preference")
//    @Schema(description = "Employee language preference", example = "EN for English")
//    private String language;
}
