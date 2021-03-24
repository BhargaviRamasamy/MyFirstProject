package com.example.processor;

import java.util.Calendar;

import org.springframework.batch.item.ItemProcessor;

import com.example.pojo.Employee_Batch;

public class FilteringItemprocessor implements ItemProcessor<Employee_Batch, Employee_Batch> {

	@Override
	public Employee_Batch process(Employee_Batch item) throws Exception {
		// TODO Auto-generated method stub

		Calendar cal = Calendar.getInstance();
		cal.set(2021, 01, 01);

		Calendar cal1 = Calendar.getInstance();

		cal1.setTime(item.getDateofjoining());

		if (cal.before(cal1)) {

			// updatedEmployee_Batch updatedemp=new
			// updatedEmployee_Batch(item.getId(),item.getName(),item.getSalary(),item.getDateofjoining(),item.getAddress());
			return item;
		} else {
			return null;
		}
	}

}
