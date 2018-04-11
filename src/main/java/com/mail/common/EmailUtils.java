package com.mail.common;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.mail.entity.VoQuery;

public class EmailUtils {
	public static void buildEmailScheduler(VoQuery voQuery) throws SchedulerException {
		JobDetail job = JobBuilder.newJob(ScheduleEmailJob.class).withIdentity("dummyJobName", "group1").build();

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule(MailConstants.SCEDULEDEMAILINTERVAL)).build();

		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.getContext().put("emailData", voQuery);
		scheduler.start();
		scheduler.scheduleJob(job, trigger);
	}
}
