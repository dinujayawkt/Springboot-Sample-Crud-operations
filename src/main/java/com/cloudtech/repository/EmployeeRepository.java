package com.cloudtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cloudtech.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee,Long>{
}
