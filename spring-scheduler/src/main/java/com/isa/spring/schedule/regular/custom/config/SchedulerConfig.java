package com.isa.spring.schedule.regular.custom.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;

import com.isa.spring.schedule.regular.custom.job.ScheduledJob;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

    private final ScheduledJob scheduledJob;

    @Autowired
    public SchedulerConfig(ScheduledJob scheduledJob) {
        this.scheduledJob = scheduledJob;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
        Trigger fixedRate = new PeriodicTrigger(1, TimeUnit.SECONDS);
        taskRegistrar.addTriggerTask(
                () -> scheduledJob.print(),
                fixedRate);
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(10);
    }
}
