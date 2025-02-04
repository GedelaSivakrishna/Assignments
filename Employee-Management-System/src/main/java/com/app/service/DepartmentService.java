package com.app.service;

import com.app.dto.DepartmentDto;
import com.app.model.Department;
import java.util.List;

public interface DepartmentService {

    Department findDepartmentById(int deptId);

    Department saveDepartment(Department department);

    Department addDepartment(DepartmentDto departmentDto);

    String deleteDepartment(int depId);

    List<Department> allDepartments();

}
