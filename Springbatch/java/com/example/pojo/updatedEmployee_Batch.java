package com.example.pojo;

import java.util.Date;

public class updatedEmployee_Batch {
	
	private int Id;
	private String Name;
	private Double Salary;
	private Date Dateofjoining;
	private String Address;
	public updatedEmployee_Batch(int id, String name, Double salary, Date dateofjoining, String address) {
		super();
		Id = id;
		Name = name;
		Salary = salary;
		Dateofjoining = dateofjoining;
		Address = address;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Double getSalary() {
		return Salary;
	}
	public void setSalary(Double salary) {
		Salary = salary;
	}
	public Date getDateofjoining() {
		return Dateofjoining;
	}
	public void setDateofjoining(Date dateofjoining) {
		Dateofjoining = dateofjoining;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	@Override
	public String toString() {
		return "updatedEmployee_Batch [Id=" + Id + ", Name=" + Name + ", Salary=" + Salary + ", Dateofjoining="
				+ Dateofjoining + ", Address=" + Address + "]";
	}
	
	

}
