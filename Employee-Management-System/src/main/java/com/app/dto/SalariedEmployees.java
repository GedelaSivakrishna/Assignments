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
}
