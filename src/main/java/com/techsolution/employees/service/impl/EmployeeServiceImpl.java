package com.techsolution.employees.service.impl;

import com.techsolution.employees.dto.CreateEmployeeRequest;
import com.techsolution.employees.dto.EmployeeResponse;
import com.techsolution.employees.entity.Department;
import com.techsolution.employees.entity.Employee;
import com.techsolution.employees.exception.ResourceNotFoundException;
import com.techsolution.employees.repository.DepartmentRepository;
import com.techsolution.employees.repository.EmployeeRepository;
import com.techsolution.employees.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository repository, DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeResponse create(CreateEmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());

        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Department " + request.getDepartmentId() + " not found"
                ));
            employee.setDepartment(department);
        }

        Employee saved = repository.save(employee);
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponse> list() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    private EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setPosition(employee.getPosition());
        response.setCreatedAt(employee.getCreatedAt());
        response.setUpdatedAt(employee.getUpdatedAt());

        if (employee.getDepartment() != null) {
            response.setDepartmentId(employee.getDepartment().getId());
            response.setDepartmentName(employee.getDepartment().getName());
        }

        return response;
    }
}