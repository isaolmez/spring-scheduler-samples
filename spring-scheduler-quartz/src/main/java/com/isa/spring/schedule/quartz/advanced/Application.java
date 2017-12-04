package com.isa.spring.schedule.quartz.advanced;

import com.isa.spring.schedule.quartz.advanced.service.SchedulerService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Application.class)) {
            SchedulerService schedulerService = context.getBean(SchedulerService.class);
            final String jobName = "Quartz_JobDetail";
            schedulerService.scheduleNewJob();
            sleep(10000);

//      schedulerService.pauseJob(jobName);
//      sleep(3000);
//
//      schedulerService.resumeJob(jobName);
//      sleep(3000);
        }
    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted", e);
        }
    }
}
