package com.example.test.repositories;

import com.example.test.entities.Employee;
import com.example.test.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
