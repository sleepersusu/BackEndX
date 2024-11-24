package com.example.bistro.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepositoryDao extends JpaRepository<Employee, Integer> {
}
