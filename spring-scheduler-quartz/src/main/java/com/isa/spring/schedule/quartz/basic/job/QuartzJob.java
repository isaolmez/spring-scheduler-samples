package com.isa.spring.schedule.quartz.basic.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

// Always re-instantiated. Not singleton
public class QuartzJob extends QuartzJobBean {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzJob.class);

    @Override
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
        LOG.info("Quartz job: {}", ctx.getMergedJobDataMap().get("jobParam"));
    }
}
