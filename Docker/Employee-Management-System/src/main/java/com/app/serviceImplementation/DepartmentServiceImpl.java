package com.app.serviceImplementation;

import com.app.Exceptions.DepartmentException;
import com.app.constants.Constants;
import com.app.dto.DepartmentDto;
import com.app.dto.UpdateDepartmentDto;
import com.app.model.Department;
import com.app.model.Employee;
import com.app.repository.DepartmentRepo;
import com.app.service.DepartmentService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Autowired
    private EntityManager entityManager;

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

    @Override
    @Transactional
    public Department updateDepartment(UpdateDepartmentDto updateDepartmentDto, int deptId) {
       Department department = findDepartmentById(deptId);
       if(!Objects.isNull(updateDepartmentDto.getName()) && !department.getName().equals(updateDepartmentDto.getName())) {
           department.setName(updateDepartmentDto.getName());
       }
       if(!Objects.isNull(updateDepartmentDto.getLocation()) && !department.getLocation().equals(updateDepartmentDto.getLocation())) {
           department.setLocation(updateDepartmentDto.getLocation());
       }

       if(!Objects.isNull(updateDepartmentDto.getEmpIds()) && !updateDepartmentDto.getEmpIds().isEmpty()) {
           System.out.println("Inside Update Department Remove employees logic");
           // Find the existing employees who need to be removed
           List<Employee> employeesToRemove = department.getEmployees().stream().filter(emp -> !updateDepartmentDto.getEmpIds().contains(emp.getId()))
                                                                     .toList();
           // Remove this particular department from all the Employees who need to be removed
           for(Employee employee : employeesToRemove) {
               employee.getDepartments().remove(department);
                entityManager.merge(employee);
           }
           department.getEmployees().removeAll(employeesToRemove);
       }
        return departmentRepo.save(department);
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
    public Page<Department> allDepartments(int pageNo, int size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        Page<Department> page = departmentRepo.findAll(pageable);
        return page;
    }

}
