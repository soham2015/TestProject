package com.quartz;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerExample
{
    public static void main( String[] args ) throws Exception
    {
       	JobDetail job = new JobDetail();
    	job.setName("dummyJobName");
    	job.setJobClass(TestQuartz.class);

    	//configure the scheduler time
    	SimpleTrigger trigger = new SimpleTrigger();
    	trigger.setName("dummyTriggerName");
    	trigger.setStartTime(new Date(System.currentTimeMillis() + 1000));
    	trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
    	trigger.setRepeatInterval(10000);

    	//schedule it
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);

    }
}