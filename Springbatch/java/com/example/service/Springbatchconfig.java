package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.SqlServerPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import com.example.mapper.CustomerRowMapper;
import com.example.partitioner.ColumnRangePartitioner;
import com.example.pojo.Employee_Batch;

@Configuration
@EnableBatchProcessing
public class Springbatchconfig 
{
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private DataSource dataSource;

	@Bean
	public ColumnRangePartitioner partitioner() 
	{
		ColumnRangePartitioner columnRangePartitioner = new ColumnRangePartitioner();
		columnRangePartitioner.setColumn("id");
		columnRangePartitioner.setDataSource(dataSource);
		columnRangePartitioner.setTable("Employee_Batch");
		return columnRangePartitioner;
	}

	@Bean
	@StepScope
	public JdbcPagingItemReader<Employee_Batch> pagingItemReader(
			@Value("#{stepExecutionContext['minValue']}") Long minValue,
			@Value("#{stepExecutionContext['maxValue']}") Long maxValue) 
	{
		

		Map<String, Order> sortKeys = new HashMap<>();
		sortKeys.put("id", Order.ASCENDING);
		
		SqlServerPagingQueryProvider queryProvider = new SqlServerPagingQueryProvider();
		queryProvider.setSelectClause("id, name,salary,dateofjoining,address");
		queryProvider.setFromClause("from Employee_Batch");
		queryProvider.setWhereClause("where id >= " + minValue + " and id < " + maxValue);
		queryProvider.setSortKeys(sortKeys);
		
		JdbcPagingItemReader<Employee_Batch> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(this.dataSource);
		reader.setFetchSize(296);
		reader.setRowMapper(new CustomerRowMapper());
		reader.setQueryProvider(queryProvider);
		
		return reader;
	}
	
	
	@Bean
	@StepScope
	public ItemWriter<Employee_Batch> customerItemWriter()
	{
		return new ItemWriter<Employee_Batch>() {

			@Override
			public void write(List<? extends Employee_Batch> items) throws Exception {
				// TODO Auto-generated method stub
				
				for(Employee_Batch y:items)
				{
					System.out.println(y);
				}
				
			}
		};
		
	}
	
	// Master
	@Bean
	public Step step2example() 
	{
		return stepBuilderFactory.get("step2example")
				.partitioner(slaveStep2example().getName(), partitioner())
				.step(slaveStep2example())
				.gridSize(4)
				.taskExecutor(new SimpleAsyncTaskExecutor())
				.build();
	}
	
	// slave step
	@Bean
	public Step slaveStep2example() 
	{
		return stepBuilderFactory.get("slaveStep2example")
				.<Employee_Batch, Employee_Batch>chunk(296)
				.reader(pagingItemReader(null, null))
				.writer(customerItemWriter())
				.build();
	}
	
	@Bean
	public Job jobexample() 
	{
		return jobBuilderFactory.get("jobexample")
				.incrementer(new RunIdIncrementer())
				.start(step2example())
				.build();
	}
}