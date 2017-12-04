package com.isa.spring.schedule.quartz.advanced.service;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import com.isa.spring.schedule.quartz.custom.job.QuartzJob;
import java.util.Collections;
import java.util.UUID;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.core.jmx.JobDataMapSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    private static Logger LOG = LoggerFactory.getLogger(SchedulerService.class);

    private final Scheduler scheduler;

    public static final String TRIGGER_PARAM_NAME = "triggerParam";

    public static final String JOB_PARAM_NAME = "jobParam";

    @Autowired
    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * Create a predefined job and trigger. Associate the job with trigger and trigger with job Then start scheduling
     */
    public void scheduleNewJob() {
        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(QuartzJob.class)
                .usingJobData(JOB_PARAM_NAME, "Basic")
                .storeDurably()
                .withIdentity("Quartz_JobDetail")
                .withDescription("Sample Quartz job")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .usingJobData(TRIGGER_PARAM_NAME, "First Trigger")
                .withIdentity("Quartz_Trigger")
                .withDescription("Sample Quartz trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(1))
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add trigger to an existing job and start scheduling
     */
    public void addTriggerForJob(String jobName) {
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobName)
                .usingJobData(TRIGGER_PARAM_NAME, "Second Trigger")
                .withIdentity("Quartz_Trigger_New")
                .withDescription("Sample Quartz trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(1))
                .build();

        try {
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Use on-the-fly trigger
     */
    public void triggerJob(String name) {
        JobKey jobKey = JobKey.jobKey(name);
        try {
            scheduler.triggerJob(jobKey, JobDataMapSupport
                    .newJobDataMap(Collections.singletonMap(TRIGGER_PARAM_NAME, "On-the-fly Trigger")));
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Use on-the-fly trigger
     */
    public void pauseJob(String name) {
        JobKey jobKey = JobKey.jobKey(name);
        try {
            LOG.info("----------------- Pausing");
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Use on-the-fly trigger
     */
    public void resumeJob(String name) {
        JobKey jobKey = JobKey.jobKey(name);
        try {
            LOG.info("----------------- Resuming");
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
