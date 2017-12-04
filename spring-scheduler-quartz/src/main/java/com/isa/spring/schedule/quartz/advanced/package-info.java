/**
 * - Job data map properties are injected to the properties of the Job (Is not it magic?) - Quartz instantiated job object. Uses Quartz
 * scheduler. - Uses AutowiringSpringBeanJobFactory to autowire dependencies to QuartzJobBean implementations. - Note that QuartzJobBean
 * class is not @Component, but @Autowired is respected - At each scheduled time, a new job object is created - Jobs have no-arg constructor
 * - Jobs should not store state, since they are not preserved
 */
package com.isa.spring.schedule.quartz.advanced;
