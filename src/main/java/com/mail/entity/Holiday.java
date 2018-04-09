package com.mail.entity;



public class Holiday {

	private Integer rec_id;
	private Integer month;

    private Integer day;


	public Integer getRec_id() {
		return rec_id;
	}

	public void setRec_id(Integer rec_id) {
		this.rec_id = rec_id;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    final Holiday other = (Holiday) obj;
	    if(this.month!=other.getMonth()){
	    	return false;
	    }
	    if(this.day!=other.getDay()){
	    	return false;
	    }	 
	    return true;
	}
		

}
