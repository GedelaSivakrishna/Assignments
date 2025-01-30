package com.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Entity
@Table(name="employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Please enter the employee name ")
    private String name;
    @NotBlank(message = "Please enter the email ")
    @Email(message = "Invalid email!")
    @Column(unique = true)
    private String email;
    @Positive(message = "Salary must be positive number")
    private long salary;
    @NotNull(message = "Please enter the employee date of joining ")
    private LocalDate dateOfJoining;
    @ManyToMany
    private List<Department> departments;

}
