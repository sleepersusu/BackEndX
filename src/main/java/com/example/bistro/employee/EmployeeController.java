package com.example.bistro.employee;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/employee/createEmployee")
	@ResponseBody
	public String addEmployee(String employeeAccount,String employeePassword,
			String employeeName,String employeeGender,String employeeBorn,
			String employeeTel,Integer employeeSeniority,Integer employeeSalary,Integer jobtitleId) {
		
		String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
		String regex = "^[-]?\\d+$";
		if(!employeeGender.equals("男")&&!employeeGender.equals("女")) {
			return "employeeGender need to be 男 or 女";
		}else if(!employeeBorn.matches(dateRegex)) {
			return "employeeBorn need like 1911-01-01";
		}else if(!employeeTel.matches(regex)) {
			return "employeeTel need to be number,example 09999999 or 06-5777777";
		}
		
		
		Employee employeeBean = new Employee();
		employeeBean.setEmployeeAccount(employeeAccount);
		employeeBean.setEmployeePassword(employeePassword);
		employeeBean.setEmployeeName(employeeName);
		employeeBean.setEmployeeGender(employeeGender);
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date employeeBornDate;
			employeeBornDate = dateFormat.parse(employeeBorn);
			employeeBean.setEmployeeBorn(employeeBornDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		employeeBean.setEmployeeTel(employeeTel);
		employeeBean.setEmployeeSeniority(employeeSeniority);
		employeeBean.setEmployeeSalary(employeeSalary);
				
		Employee result = employeeService.insertEmployee(employeeBean,jobtitleId);
		if(result==null) {
			return "Employee already exists.";
		}else {
			return "Insert employee OK.";
		}
	}
}
