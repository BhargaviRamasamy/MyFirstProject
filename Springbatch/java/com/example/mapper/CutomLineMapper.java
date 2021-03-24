package com.example.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.example.pojo.Employee_Batch;

public class CutomLineMapper implements FieldSetMapper<Employee_Batch> {

	@Override
	public Employee_Batch mapFieldSet(FieldSet fieldSet) throws BindException {
		// TODO Auto-generated method stub
		return new Employee_Batch(fieldSet.readInt("id"),fieldSet.readString("name"), 
				fieldSet.readDouble("salary"), fieldSet.readDate("dateofjoining","dd-MM-yyyy"),fieldSet.readString("address"));
	}

}
