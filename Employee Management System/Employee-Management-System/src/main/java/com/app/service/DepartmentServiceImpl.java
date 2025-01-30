package com.app.service;

import com.app.Exceptions.DepartmentException;
import com.app.model.Department;
import com.app.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author Gedela sivakrishna
 * @since 2025-01-30
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepo departmentRepo;

    /**
     * Find out the department with given id
     * @param deptId the unique id of department
     * @return the department matched with the given id
     */
    @Override
    public Department findDepartmentById(int deptId) {
        return departmentRepo.findById(deptId).orElseThrow(() -> new DepartmentException("Invalid department id!"));
    }

    /**
     * Saves the department
     * @param department department details that need to be saved
     * @return saved department
     */
    @Override
    public Department saveDepartment(Department department) {
        return departmentRepo.save(department);
    }

    /**
     * Creates a new Department
     * @param department  department details which need to be created
     * @return the newly created department
     */
    @Override
    public Department addDepartment(Department department) {
        return saveDepartment(department);
    }

    /**
     * Deletes the department
     * @param deptId the unique id of the department
     * @return response message saying whether department deleted successfully or not
     * @throws com.app.Exceptions.DepartmentException If department not found with given id
     */
    @Override
    public String deleteDepartment(int deptId) {
        departmentRepo.delete(findDepartmentById(deptId));
        return !departmentRepo.existsById(deptId) ? "Department deleted successfully " :
                "Error, while deleting department";
    }

    /**
     * Find's out all the departments
     * @return the list of departments
     */
    @Override
    public List<Department> allDepartments() {
        return departmentRepo.findAll();
    }

}
