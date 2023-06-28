package com.learning.springbootbackend.ServiceInterface;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.Employee;

@Service
public interface EmployeeService {
	
	List<Employee> getAllEmployee();
	
	Employee getEmployeeById(long id);
	
	Employee saveEmployee(Employee emp);

}
