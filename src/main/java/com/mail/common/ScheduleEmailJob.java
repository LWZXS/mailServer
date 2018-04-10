package com.mail.common;

import java.util.List;
import java.util.logging.Logger;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;

import com.mail.entity.VoQuery;

public class ScheduleEmailJob implements Job {
	private static final Logger log = Logger.getLogger(ScheduleEmailJob.class.getName());

	@Override
	 public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            List<JobExecutionContext> jobs = jobExecutionContext.getScheduler().getCurrentlyExecutingJobs();
            if (jobs != null && !jobs.isEmpty()) {
                for (JobExecutionContext job : jobs) {
                    if (job.getTrigger().equals(jobExecutionContext.getTrigger()) && !job.getJobInstance().equals(this)) {
                        log.info("There's another instance running, : " + this);
                        return;
                    }
                }
            }
            SchedulerContext schedulerContext = jobExecutionContext.getScheduler().getContext();
            System.out.println("ScheduleEmailJob.execute: Sending an email...");
            VoQuery voQuery = (VoQuery) schedulerContext.get("emailData");
            //mailServiceDelegate.sendMail(toMail, mailCategory, title, userName, data);
            voQuery.getMailServiceDelegate().sendMailTest(voQuery.getToMail(), voQuery.getMailCategory(), voQuery.getTitle(), voQuery.getUserName(), voQuery.getData());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }

    }

}
