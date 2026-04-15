package com.techsolution.employees.service;

import com.techsolution.employees.dto.CreateEmployeeRequest;
import com.techsolution.employees.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse create(CreateEmployeeRequest request);
    List<EmployeeResponse> list();
}