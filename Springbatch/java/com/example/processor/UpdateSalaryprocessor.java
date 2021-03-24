package com.example.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.pojo.Employee_Batch;

public class UpdateSalaryprocessor implements ItemProcessor<Employee_Batch, Employee_Batch> {

	@Override
	public Employee_Batch process(Employee_Batch item) throws Exception {
		// TODO Auto-generated method stub
		//updatedEmployee_Batch itemupdated=new updatedEmployee_Batch(item.getId(),item.getName(),item.getSalary()* 1.12, item.getDateofjoining(),item.getAddress());
		item.setSalary(item.getSalary() * 0.12);

		return item;
	}

}
