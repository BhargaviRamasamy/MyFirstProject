//package com.example.scheduler;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobExecution;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.JobParametersInvalidException;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
//import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
//import org.springframework.batch.core.repository.JobRestartException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Springbatchscheduler {
//
//	@Autowired
//	public JobLauncher jobLauncher;
//	
//	@Autowired
//	public Job job;
//	
//	@Scheduled(fixedRate = 5000)
//	public void myscheduler()
//	{
//		try {
//
//			System.out.println(job.getName());
//			JobParameters jobParameters=new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
//			JobExecution jobExecution=jobLauncher.run(job, jobParameters);
//			System.out.println(jobExecution.getStatus());
//		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
//				| JobParametersInvalidException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
//
//}
