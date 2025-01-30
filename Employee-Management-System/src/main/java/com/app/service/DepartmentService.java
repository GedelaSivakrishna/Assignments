package com.app.service;

import com.app.model.Department;
import java.util.List;

public interface DepartmentService {

    Department findDepartmentById(int deptId);

    Department saveDepartment(Department department);

    Department addDepartment(Department department);

    String deleteDepartment(int depId);

    List<Department> allDepartments();

}
