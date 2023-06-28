package com.learning.springbootbackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.springbootbackend.Model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
	
}
