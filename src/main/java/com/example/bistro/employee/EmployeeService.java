package com.example.bistro.employee;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EmployeeService {
	@Autowired
	private PasswordEncoder pwdEncoder;//引入加密並綁定
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private JobTitleRepository jobRepo;
	
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
	
	@Transactional
	public Employee insertEmployee(Employee employeeBean,Integer jobTitleId) {
		Optional<JobTitle> job = jobRepo.findById(jobTitleId);
		if(job.isPresent()) {
		employeeBean.setJobTitle(job.get());
		}else {
			throw new IllegalArgumentException("JobTitle with ID " + jobTitleId + " not found.");
		}
		String status="簽核";
		employeeBean.setEmployeeStatus(status);
		String password = employeeBean.getEmployeePassword();
		String encodedPwd = pwdEncoder.encode(password);//加密
		employeeBean.setEmployeePassword(encodedPwd);
		Optional<Employee> checkAccount = employeeRepo.findByEmployeeAccount(employeeBean.getEmployeeAccount());
		if(checkAccount.isPresent()) {
			return null;
		}else {
			return employeeRepo.save(employeeBean);
		}
	}
	
}
