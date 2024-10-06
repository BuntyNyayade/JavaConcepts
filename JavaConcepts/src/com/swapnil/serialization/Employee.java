package com.swapnil.serialization;

import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L; // Recommended for version control
	private int id;
	private String name;
	private transient String password;
	
	public Employee(int id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
}
