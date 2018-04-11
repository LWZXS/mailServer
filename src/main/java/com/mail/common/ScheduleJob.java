package com.mail.common;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public abstract class ScheduleJob implements Job {
	
	protected Logger logger = LogManager.getLogger(this.getClass().getName());

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		try {
			List<JobExecutionContext> jobs = jobExecutionContext.getScheduler().getCurrentlyExecutingJobs();
			if (jobs != null && !jobs.isEmpty()) {
				for (JobExecutionContext job : jobs) {
					if (job.getTrigger().equals(jobExecutionContext.getTrigger())
							&& !job.getJobInstance().equals(this)) {
						logger.info("There's another instance running, : " + this);
						return;
					}
				}
			}
		} catch (Exception e) {
			logger.fatal("Some exceptions happened on checking previous jobs!!!");
			logger.fatal(e.toString());
		}
	}


}
