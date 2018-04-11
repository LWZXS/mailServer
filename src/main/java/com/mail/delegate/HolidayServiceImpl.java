package com.mail.delegate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mail.dao.HolidayDao;
import com.mail.entity.Holiday;

@Service
public class HolidayServiceImpl implements HolidayService {
	private HolidayDao holidayDao;
	
	@Autowired
	public void setHolidayDao(HolidayDao holidayDao) {
		this.holidayDao = holidayDao;
	}
	
	@Override
	public List<Holiday> getHolidays(int year) {
		return holidayDao.getHolidays(year);
	}
}
