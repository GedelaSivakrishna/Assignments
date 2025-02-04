package com.app.serviceImplementation;

import com.app.Exceptions.DepartmentException;
import com.app.constants.Constants;
import com.app.dto.DepartmentDto;
import com.app.model.Department;
import com.app.repository.DepartmentRepo;
import com.app.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

/**
 * @author Gedela sivakrishna
 * @since 2025-01-30
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private ModelMapper mapper;

    /**
     * Find out the department with given id
     * @param deptId the unique id of department
     * @return the department matched with the given id
     */
    @Override
    public Department findDepartmentById(int deptId) {
        return departmentRepo.findById(deptId).orElseThrow(() -> new DepartmentException(Constants.INVALID_DEPARTMENT_ID));
    }

    /**
     * Checks if the DepartmentDto object is null
     * @param departmentDto departmentDto object to check
     * @throws DepartmentException if departmentDto object is null throws Invalid DepartmentDto exception
     */
    public static void checkDepartment(DepartmentDto departmentDto) {
        if(Objects.isNull(departmentDto)) {
            throw new DepartmentException(Constants.INVALID_DEPARTMENT_DTO);
        }
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
     * @param departmentDto  department details which need to be created
     * @return the newly created department
     */
    @Override
    public Department addDepartment(DepartmentDto departmentDto) {
        checkDepartment(departmentDto);
        Department department = mapper.map(departmentDto, Department.class);
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
        return !departmentRepo.existsById(deptId) ? Constants.DEPARTMENT_DELETED_SUCCESS :
                Constants.DEPARTMENT_DELETED_FAILURE;
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
