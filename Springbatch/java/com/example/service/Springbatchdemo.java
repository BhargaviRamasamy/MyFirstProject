//package com.example.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecutionListener;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.StepExecutionListener;
//import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
//import org.springframework.batch.item.database.JdbcBatchItemWriter;
//import org.springframework.batch.item.database.JdbcPagingItemReader;
//import org.springframework.batch.item.database.Order;
//import org.springframework.batch.item.database.support.SqlServerPagingQueryProvider;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.support.CompositeItemProcessor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.task.SimpleAsyncTaskExecutor;
//
//import com.example.listener.Customlistener;
//import com.example.listener.Customsteplistener;
//import com.example.mapper.CustomerRowMapper;
//import com.example.mapper.CutomLineMapper;
//import com.example.partitioner.ColumnRangePartitioner;
//import com.example.pojo.Employee_Batch;
//import com.example.pojo.updatedEmployee_Batch;
//import com.example.processor.FilteringItemprocessor;
//import com.example.processor.UpdateSalaryprocessor;
//
//@Configuration
//@EnableBatchProcessing
//public class Springbatchdemo extends DefaultBatchConfigurer {
//
//	@Autowired
//	private JobBuilderFactory jobBuilderFactory;
//
//	@Autowired
//	private StepBuilderFactory stepBuilderFactory;
//
//	@Autowired
//	private DataSource dataSource;
//
//	@Bean
//	public FlatFileItemReader<Employee_Batch> itemreader() {
//		FlatFileItemReader<Employee_Batch> reader = new FlatFileItemReader<Employee_Batch>();
//		reader.setLinesToSkip(1);
//		reader.setResource(new ClassPathResource("Employee.csv"));
//
//		DefaultLineMapper<Employee_Batch> linemapper = new DefaultLineMapper<Employee_Batch>();
//
//		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
//		tokenizer.setNames(new String[] { "id", "name", "salary", "dateofjoining", "address" });
//
//		linemapper.setLineTokenizer(tokenizer);
//		linemapper.setFieldSetMapper(new CutomLineMapper());
//		linemapper.afterPropertiesSet();
//
//		reader.setLineMapper(linemapper);
//		return reader;
//	}
//	
////
////	@Bean
////	@StepScope
////	public ItemWriter<Employee_Batch> writer()
////	{
////		return new ItemWriter<Employee_Batch>() {
////
////			@Override
////			public void write(List<? extends Employee_Batch> items) throws Exception {
////				// TODO Auto-generated method stub
////				for(Employee_Batch y:items)
////					{
////						System.out.println(y);
////					}
////			}
////		};
////	}
//	
//	@Bean
//	@StepScope
//	public JdbcBatchItemWriter<Employee_Batch> Itemwriter() {
//		JdbcBatchItemWriter<Employee_Batch> writer = new JdbcBatchItemWriter<Employee_Batch>();
//		writer.setDataSource(dataSource);
//		writer.setSql("INSERT INTO Employee_Batch VALUES(:id, :name, :salary, :dateofjoining, :address)");
//		writer.setItemSqlParameterSourceProvider(
//				new BeanPropertyItemSqlParameterSourceProvider<Employee_Batch>());
//		writer.afterPropertiesSet();
//		
//		return writer;
//	}
//
//	@Bean
//	public StepExecutionListener Customsteplistener() {
//		return new Customsteplistener();
//	}
//
//	@Bean
//	public JobExecutionListener Customlistener() {
//		return new Customlistener();
//	}
//
//	@Bean
//	public FilteringItemprocessor filteritemprocessor() {
//		return new FilteringItemprocessor();
//	}
//
//	@Bean
//	public UpdateSalaryprocessor updatesalaryprocessor() {
//		return new UpdateSalaryprocessor();
//	}
//
////	@Bean
////	@StepScope
////	public ColumnRangePartitioner partitioner() 
////	{
////		ColumnRangePartitioner columnRangePartitioner = new ColumnRangePartitioner();
////		columnRangePartitioner.setColumn("id");
////		columnRangePartitioner.setDataSource(dataSource);
////		columnRangePartitioner.setTable("Employee_Batch");
////		return columnRangePartitioner;
////	}
////	
////	@Bean
////	@StepScope
////	public JdbcPagingItemReader<Employee_Batch> pagingItemReader() 
////	{
////		//System.out.println("reading " + minValue + " to " + maxValue);
////
////		Map<String, Order> sortKeys = new HashMap<>();
////		sortKeys.put("id", Order.ASCENDING);
////		
////		SqlServerPagingQueryProvider queryProvider = new SqlServerPagingQueryProvider();
////		queryProvider.setSelectClause("id, name,salary,dateofjoining,address");
////		queryProvider.setFromClause("from Employee_Batch");
////		//queryProvider.setWhereClause("where id >= " + minValue + " and id < " + maxValue);
////		queryProvider.setSortKeys(sortKeys);
////		
////		JdbcPagingItemReader<Employee_Batch> reader = new JdbcPagingItemReader<>();
////		reader.setDataSource(dataSource);
////		reader.setFetchSize(296);
////		reader.setRowMapper(new CustomerRowMapper());
////		reader.setQueryProvider(queryProvider);
////		
////		return reader;
////	}
////	
//	@Bean
//	public CompositeItemProcessor<Employee_Batch, Employee_Batch> compositeitemprocessor() throws Exception {
//		List<ItemProcessor<Employee_Batch, Employee_Batch>> delegates = new ArrayList<ItemProcessor<Employee_Batch, Employee_Batch>>();
//		delegates.add(filteritemprocessor());
//		delegates.add(updatesalaryprocessor());
//
//		CompositeItemProcessor<Employee_Batch, Employee_Batch> compositeprocessor = new CompositeItemProcessor<Employee_Batch, Employee_Batch>();
//		compositeprocessor.setDelegates(delegates);
//		compositeprocessor.afterPropertiesSet();
//
//		return compositeprocessor;
//	}
////	
////	@Bean
////	public AsyncItemProcessor<Employee_Batch, updatedEmployee_Batch> asyncitemprocessor() throws Exception
////	{
////		AsyncItemProcessor<Employee_Batch, updatedEmployee_Batch> async=new AsyncItemProcessor<Employee_Batch, updatedEmployee_Batch>();
////		async.setDelegate(compositeitemprocessor());
////		async.setTaskExecutor(new SimpleAsyncTaskExecutor());
////		async.afterPropertiesSet();
////		return async;
////	}
//	
////	@Bean
////	public AsyncItemWriter<updatedEmployee_Batch> asyncitemwriter() throws Exception
////	{
////		AsyncItemWriter<updatedEmployee_Batch> asyncwriter=new AsyncItemWriter<updatedEmployee_Batch>();
////		asyncwriter.setDelegate(Itemwriter());
////		asyncwriter.afterPropertiesSet();
////		
////		return asyncwriter;
////	}
//
////	@Bean
////	public Step updateemployeetablebatchexample1() throws Exception {
////		return stepBuilderFactory.get("updateemployeetablebatchexample1()")
////				.<Employee_Batch, Employee_Batch>chunk(100).reader(itemreader()).writer(writer())
////				.processor(compositeitemprocessor())
////				.listener(Customsteplistener()).build();
////	}
////
////	@Bean
////	public Job updateemployeetablebatchjobexample1() throws Exception {
////		return jobBuilderFactory.get("updateemployeetablebatchjobexample1").incrementer(new RunIdIncrementer())
////				.start(updateemployeetablebatchexample1()).listener(Customlistener()).build();
////
////	}
//
////	
////	@Bean
////	public Step step2() 
////	{
////		System.out.println("in master");
////		return stepBuilderFactory.get("step2")
////				.partitioner(slaveStep2().getName(), partitioner())
////				.step(slaveStep2())
////				.gridSize(4)
////				.taskExecutor(new SimpleAsyncTaskExecutor())
////				.build();
////	}
//	
//	// slave step
//	@Bean
//	public Step slaveStep2() 
//	{
//		
//		return stepBuilderFactory.get("slaveStep2")
//				.<Employee_Batch, Employee_Batch>chunk(10)
//				.reader(itemreader())
//				.writer(Itemwriter())
//				.build();
//	}
//	
//	@Bean
//	public Job job80() 
//	{
//		return jobBuilderFactory.get("job80")
//				.incrementer(new RunIdIncrementer())
//				.start(slaveStep2())
//				.build();
//	}
//
//}
