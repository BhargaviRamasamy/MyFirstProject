package com.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.example.demo.SpringbatchApplication;

public class Customlistener implements JobExecutionListener {
	private static final Logger logger = LoggerFactory.getLogger(SpringbatchApplication.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
		logger.info("job" +jobExecution.getJobId()+ "started");
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            logger.info("SpringBatchJobCompletionListener - BATCH JOB COMPLETED SUCCESSFULLY");
            logger.info("job" +jobExecution.getJobId()+ "completed");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            logger.info("SpringBatchJobCompletionListener - BATCH JOB FAILED");
        }
		
	}

}
