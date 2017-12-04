package com.isa.spring.schedule.regular.basic.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScheduledJob {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledJob.class);

    private final AtomicInteger count = new AtomicInteger();

    @Scheduled(fixedDelay = 1000)
    public void print() {
        LOG.info("Basic scheduled job {}", count.incrementAndGet());
    }
}
