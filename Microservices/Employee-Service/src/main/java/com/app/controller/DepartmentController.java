package com.app.controller;

import com.app.dto.DepartmentDto;
import com.app.dto.UpdateDepartmentDto;
import com.app.model.Department;
import com.app.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
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

    @Operation(summary = "Update Department", description = "Department Id must be valid")
    @PutMapping("department/{deptId}")
    public ResponseEntity<Department> updateDepartment(@RequestBody UpdateDepartmentDto updateDepartmentDto, @PathVariable int deptId) {
        Department department = departmentService.updateDepartment(updateDepartmentDto, deptId);
        return new ResponseEntity<>(department, HttpStatus.OK);
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
    public ResponseEntity<Page<Department>> allDepartments(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(departmentService.allDepartments(pageNo, size), HttpStatus.OK);
    }

}
