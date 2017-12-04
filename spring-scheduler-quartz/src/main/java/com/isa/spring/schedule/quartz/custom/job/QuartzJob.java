package com.isa.spring.schedule.quartz.custom.job;

import com.isa.spring.schedule.quartz.custom.service.SchedulerService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class QuartzJob extends QuartzJobBean {

    private static final Logger LOG = LoggerFactory.getLogger(QuartzJob.class);

    @Override
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
        LOG.info("Quartz job: {} - {}",
                ctx.getMergedJobDataMap().get(SchedulerService.JOB_PARAM_NAME),
                ctx.getMergedJobDataMap().get(SchedulerService.TRIGGER_PARAM_NAME));
    }
}
