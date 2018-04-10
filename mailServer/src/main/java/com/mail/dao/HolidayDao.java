package com.mail.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mail.entity.Holiday;

public interface HolidayDao {

    public List<Holiday> getHolidays();
    
}
