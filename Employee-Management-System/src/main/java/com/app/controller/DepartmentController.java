package com.app.controller;

import com.app.dto.DepartmentDto;
import com.app.model.Department;
import com.app.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "Add Department")
    @PostMapping("department")
    public ResponseEntity<Department> addDepartment(@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Department to create",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = DepartmentDto.class)
            )
    ) @Valid @RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.addDepartment(departmentDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete Department", description = "Department must exist")
    @DeleteMapping("department/{deptId}")
    public ResponseEntity<String> deleteDepartment(
            @Parameter(description = "Id of the department to be deleted") @PathVariable int deptId) {
        String msg = departmentService.deleteDepartment(deptId);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @Operation(summary = "List all Departments")
    @GetMapping("departments")
    public ResponseEntity<List<Department>> allDepartments() {
        return new ResponseEntity<>(departmentService.allDepartments(), HttpStatus.OK);
    }

}
