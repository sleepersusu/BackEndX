package com.example.bistro.jobTitle;

import java.util.ArrayList;
import java.util.List;

import com.example.bistro.employee.Employee;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "JobTitle")
public class JobTitle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	

	private String jobtitleName;
	
	@OneToMany(mappedBy ="jobtitle" )
	private List<Employee> list = new ArrayList<>();

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getJobtitleName() {
		return jobtitleName;
	}

	public void setJobtitleName(String jobtitleName) {
		this.jobtitleName = jobtitleName;
	}

	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}
	

	
	
	
}
