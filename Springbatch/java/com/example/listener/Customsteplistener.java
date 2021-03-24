package com.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

import com.example.demo.SpringbatchApplication;

public class Customsteplistener implements StepExecutionListener {

	private static final Logger logger = LoggerFactory.getLogger(SpringbatchApplication.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub

		logger.info("step" + stepExecution.getStepName() + "started");

	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {

		// TODO Auto-generated method stub

		logger.info("step completed with status" + stepExecution.getExitStatus());
		return stepExecution.getExitStatus();

	}

}
