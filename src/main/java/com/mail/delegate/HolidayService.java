package com.mail.delegate;

import java.util.List;

import com.mail.entity.Holiday;

public interface HolidayService {
	List<Holiday> getHolidays(int year);
}
