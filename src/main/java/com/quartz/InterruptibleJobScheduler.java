package com.quartz;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
 
public class InterruptibleJobScheduler {
    private Class<? extends Job> jobClass;
    public InterruptibleJobScheduler(Class<? extends Job> jobClass) {
        this.jobClass = jobClass;
    }
     
    public void start() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        try {
            JobDetail jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity("myJob", "myGroup").build();
 
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("myTrigger", "myGroup")
                    .startNow()
                    .withSchedule(
                            SimpleScheduleBuilder
                                    .simpleSchedule()
                    )
                    .build();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("before interrupt " + scheduler.getCurrentlyExecutingJobs());
            scheduler.interrupt(jobDetail.getKey());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("after interrupt " + scheduler.getCurrentlyExecutingJobs());
        } catch (SchedulerException e) {
            e.printStackTrace();
        } finally {
            scheduler.shutdown();
        }
    }
}
