package com.backend.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank
  	private String studentno;

	@NotBlank
	private String password;

	public String getStudentno() {
		return studentno;
	}

	public void setStudentno(String studentno) {
		this.studentno = studentno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
