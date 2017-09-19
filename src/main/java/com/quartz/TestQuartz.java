package com.quartz;



	import org.quartz.Job;
	import org.quartz.JobExecutionContext;
	import org.quartz.JobExecutionException;

	public class TestQuartz implements Job
	{
		public void execute(JobExecutionContext context)
		throws JobExecutionException {

			System.out.println("Hi, This is simple Quartz Program!");

		}

	
	
	}
