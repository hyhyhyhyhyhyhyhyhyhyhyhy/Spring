package com.yedam.app.emp.service;

import lombok.Data;

@Data
public class EmpVO {
	String employeeId;
	String firstName;
	String lastName;
	String jobId;
	String email;
	String departmentId;
	String orderColumn;
	String jobTitle;
	String departmentName;
	
	String[] getDeptArr() {
		return departmentId.split(",");
	}
}
