package com.mail.dao;

import java.util.List;

import com.mail.entity.Holiday;

public interface HolidayDao {
    List<Holiday> getHolidays(int year);
}
