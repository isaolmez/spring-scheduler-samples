package com.isa.spring.schedule.quartz.basic.config;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import java.util.Collections;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.core.jmx.JobDataMapSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.isa.spring.schedule.quartz.basic.job.QuartzJob;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private final ApplicationContext applicationContext;

    @Autowired
    public SchedulerConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob()
                .ofType(QuartzJob.class)
                .usingJobData(JobDataMapSupport.newJobDataMap(Collections.singletonMap("jobParam", "Basic")))
                .usingJobData("otherKey", "otherValue")
                .storeDurably()
                .withIdentity("Quartz_JobDetail")
                .withDescription("Sample Quartz job")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        return TriggerBuilder.newTrigger()
                .forJob(job)
                .withIdentity("Quartz_Trigger")
                .withDescription("Sample Quartz trigger")
                .withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(1))
                .build();
    }

    @Bean
    public SchedulerFactoryBean scheduler(Trigger trigger, JobDetail job) {
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
        schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
        // Or through programmatic access
        // schedulerFactory.setQuartzProperties(new Properties());

        schedulerFactory.setJobFactory(springBeanJobFactory());
        schedulerFactory.setJobDetails(job);
        schedulerFactory.setTriggers(trigger);
        return schedulerFactory;
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        return new AutowiringSpringBeanJobFactory(applicationContext);
    }
}
