package com.sastry.spring.bootbatch.model;

public class Person {
	private String lastName;
	private String firstName;
	private String fatherName;

	public Person() {

	}

	public Person(String firstName, String lastName,String fatherName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.fatherName=fatherName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "firstName: " + firstName + ", lastName: " + lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

}