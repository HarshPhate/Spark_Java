package com.cfs.EmployeeMngt.repository;

import com.cfs.EmployeeMngt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//@repository we dont need to write here if you use  JpaRepository its automatically understand this is repo
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
}
