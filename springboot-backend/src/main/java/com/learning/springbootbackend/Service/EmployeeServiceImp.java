package com.learning.springbootbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springbootbackend.Model.Employee;
import com.learning.springbootbackend.Repository.EmployeeRepository;
import com.learning.springbootbackend.ServiceInterface.EmployeeService;

@Service
public class EmployeeServiceImp implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployee() {
		return employeeRepository.findAll();
		
	}
	

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		return optionalEmployee.orElse(null);
	}

	@Override
	public Employee saveEmployee(Employee emp) {
		return employeeRepository.save(emp);
		
	}

	

}
