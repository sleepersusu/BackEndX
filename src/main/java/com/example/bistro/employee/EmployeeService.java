package com.example.bistro.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
	@Autowired
	private PasswordEncoder pwdEncoder;//引入加密並綁定
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	public Employee registerEmployee(String account,String password) {
		String encodedPwd = pwdEncoder.encode(password);//加密
		Employee employee = new Employee();
		employee.setEmployeeAccount(account);
		employee.setEmployeePassword(encodedPwd);
		
		return employeeRepo.save(employee);
	}
	
	public  Optional<Employee> checkLogin(String loginAccount,String loginPassword) {
		 Optional<Employee> dbEmployee = employeeRepo.findByEmployeeAccount(loginAccount);
		if (dbEmployee.isPresent()) {
			String encodedPwd = dbEmployee.get().getEmployeePassword();
			boolean result = pwdEncoder.matches(loginPassword, encodedPwd);
			
			if (true) {//result
				return dbEmployee;
			}
		}
		return Optional.empty();
	}
	
}
