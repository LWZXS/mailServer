package com.mail.common;

import com.mail.dao.TemplateDao;
import com.mail.delegate.HolidayService;
import com.mail.delegate.MailServiceDelegate;
import com.mail.entity.Holiday;
import com.mail.entity.Template;
import com.mail.entity.VoQuery;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ScheduleEmailJob extends ScheduleJob {
	@Autowired
	private MailServiceDelegate mailServiceDelegate;

	private HolidayService holidayService;
	@Autowired
	public void setHolidayService(HolidayService holidayService) {
		this.holidayService = holidayService;
	}

	private TemplateDao templateDao;
	@Autowired
	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}

	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		super.execute(jobExecutionContext);
		SchedulerContext schedulerContext;
		try {
			schedulerContext = jobExecutionContext.getScheduler().getContext();
			VoQuery voQuery = (VoQuery) schedulerContext.get("emailData");
			sendEmailByCondition(voQuery);

		} catch (SchedulerException e) {
			logger.fatal("Some exceptions happend on fetching a scheduler context");
		} catch (Exception e) {
			logger.fatal("Some exceptions happend on sending emails");
		}
	}


	private void sendEmailByCondition(VoQuery voQuery) throws Exception {
		List<Holiday> holidays = holidayService.getHolidays(Year.now().getValue());
		
		LocalDate now = LocalDate.now();
		LocalDate targetDay = now.plus(MailConstants.BEFORHOLIDAY, ChronoUnit.DAYS);
		boolean isHoliday = false;
		
		for (Holiday holiday :holidays) {
			LocalDate day =	LocalDate.of(holiday.getYear(), holiday.getMonth(), holiday.getDay());
			if (targetDay.isEqual(day)) {
				isHoliday = true;
				voQuery.setMailCategory(holiday.getContent());
				voQuery.setTitle(templateDao.selectTemplateBySubject(holiday.getContent()).getTemplate_title());
				System.out.println(voQuery.getMailCategory() + " -- " + voQuery.getTitle());
//				mailServiceDelegate.sendMail(voQuery);
				break;
			}
		}
		
		if (!isHoliday && (now.getDayOfWeek().toString().equalsIgnoreCase(MailConstants.TARGETDAY1) || now.getDayOfWeek().toString().equalsIgnoreCase(MailConstants.TARGETDAY2))) {
			voQuery.setMailCategory(MailConstants.REGULARTEMPLATE);
			voQuery.setTitle(templateDao.selectTemplateBySubject(MailConstants.REGULARTEMPLATE).getTemplate_title());
			mailServiceDelegate.sendMail(voQuery);
		}
	}
}
