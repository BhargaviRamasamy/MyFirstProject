package com.example.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.pojo.Employee_Batch;

public class CustomerRowMapper implements RowMapper<Employee_Batch> {

	@Override
	public Employee_Batch mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		return new Employee_Batch(rs.getInt("id"), rs.getString("name"), rs.getDouble("salary"),rs.getDate("dateofjoining"),rs.getString("address"));
	}

}
