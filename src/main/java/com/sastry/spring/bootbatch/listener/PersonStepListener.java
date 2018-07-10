package com.sastry.spring.bootbatch.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class PersonStepListener implements StepExecutionListener {


    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("In before step");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("In after step");
        return ExitStatus.COMPLETED;
    }
}