package com.app.controller;

import com.app.model.Department;
import com.app.service.DepartmentService;
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

    @PostMapping("department")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        return new ResponseEntity<>(departmentService.addDepartment(department), HttpStatus.CREATED);
    }

    @DeleteMapping("department/{deptId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int deptId) {
        String msg = departmentService.deleteDepartment(deptId);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @GetMapping("departments")
    public ResponseEntity<List<Department>> allDepartments() {
        return new ResponseEntity<>(departmentService.allDepartments(), HttpStatus.OK);
    }

}
