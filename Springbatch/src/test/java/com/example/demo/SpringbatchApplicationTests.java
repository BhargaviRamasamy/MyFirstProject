package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.service.Springbatchconfig;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Configuration(value = "updateemployee")
public class SpringbatchApplicationTests {
	
	@Autowired
	public JobLauncherTestUtils jobLauncherTestUtils;
	
	
	@Configuration
	public class updateemployee
	{
		@Bean
		public JobLauncherTestUtils jobLauncherTestUtils()
		{
			return new JobLauncherTestUtils();
		}
		
	}
	
	@Test
	public void launchJob() throws Exception
	{
		JobExecution jobExecution=jobLauncherTestUtils.launchJob();
	
		System.out.println(jobExecution.getStatus());
		
		System.out.println(jobExecution.getExecutionContext());
		
		System.out.println(jobExecution.getStepExecutions());
	}

//	@Bean
//	public JobLauncherTestUtils getJobLauncherTestUtils() {
//
//		return new JobLauncherTestUtils() {
////			@Override
////			@Autowired
////			public void setJob(
////					@Qualifier("updateemployeetablebatchjobexample1") Job updateemployeetablebatchjobexample1) {
////				super.setJob(updateemployeetablebatchjobexample1);
////			}
//		};
//	}
//	
//    
//
//	@Test
//	public void launchJob() throws Exception {
////		JobParameters params = new JobParametersBuilder().addString("jobName", "updateemployeetablebatchjobexample1")
////				.toJobParameters();
//		JobExecution jobExecution = getJobLauncherTestUtils().launchJob();
//		System.out.println(jobExecution.getStatus());
//		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
//
//	}

}