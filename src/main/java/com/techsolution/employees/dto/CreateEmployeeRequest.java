package com.techsolution.employees.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateEmployeeRequest {

    @NotBlank(message = "name is required")
    @Size(max = 120, message = "name must be <= 120 chars")
    private String name;

    @Email(message = "email must be valid")
    @Size(max = 150, message = "email must be <= 150 chars")
    private String email;

    @Size(max = 80, message = "position must be <= 80 chars")
    private String position;

    private Long departmentId;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}