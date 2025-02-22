package com.app.service;

import com.app.dto.DepartmentDto;
import com.app.dto.UpdateDepartmentDto;
import com.app.model.Department;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {

    Department findDepartmentById(int deptId);

    Department saveDepartment(Department department);

    Department addDepartment(DepartmentDto departmentDto);

    Department updateDepartment(UpdateDepartmentDto updateDepartmentDto, int deptId);

    String deleteDepartment(int depId);

    Page<Department> allDepartments(int pageNo, int size);

}
