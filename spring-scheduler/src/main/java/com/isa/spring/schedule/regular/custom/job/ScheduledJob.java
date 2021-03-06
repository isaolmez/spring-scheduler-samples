package com.isa.spring.schedule.regular.custom.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScheduledJob {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduledJob.class);

    private final AtomicInteger count = new AtomicInteger(0);

    public void print() {
        LOG.info("Advanced scheduled job {}", count.incrementAndGet());
    }
}
