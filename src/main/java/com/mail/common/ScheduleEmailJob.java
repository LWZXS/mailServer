package com.mail.common;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import com.mail.delegate.MailServiceDelegate;
import com.mail.entity.VoQuery;

public class ScheduleEmailJob extends ScheduleJob {
	private MailServiceDelegate mailServiceDelegate;
	
	@Autowired
	public void setMailServiceDelegate(MailServiceDelegate mailServiceDelegate) {
		this.mailServiceDelegate = mailServiceDelegate;
	}

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		super.execute(jobExecutionContext);
		SchedulerContext schedulerContext;
		try {
			schedulerContext = jobExecutionContext.getScheduler().getContext();
			System.out.println("ScheduleEmailJob.execute: Sending an email...");
			VoQuery voQuery = (VoQuery) schedulerContext.get("emailData");
			mailServiceDelegate.sendMail(voQuery);
		} catch (SchedulerException e) {
			logger.fatal("Some exceptions happend on fetching a scheduler context");
			logger.fatal(e.toString());
		} catch (Exception e) {
			logger.fatal("Some exceptions happend on sending emails");
			logger.fatal(e.toString());
		}
	}

}
