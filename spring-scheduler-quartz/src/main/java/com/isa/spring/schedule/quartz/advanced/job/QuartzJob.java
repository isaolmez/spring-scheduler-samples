package com.isa.spring.schedule.quartz.advanced.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

public class QuartzJob extends QuartzJobBean {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzJob.class);

    private String jobParam;

    private String triggerParam;

    @Override
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
        LOG.info("Quartz job: {} - {}", jobParam, triggerParam);
    }
}
