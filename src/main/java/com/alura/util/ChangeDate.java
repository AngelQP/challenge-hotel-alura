package com.alura.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ChangeDate {
	
	public static LocalDate formatDateToLocalDate(Date fecha) {
    	String pattern = "yyyy-MM-dd";
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    	String date = simpleDateFormat.format(fecha);
    	return LocalDate.parse(date);
    }
    
    public static Date formatLocalDateToDate(LocalDate fecha) {
//        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
//        try {
//            Date utilDate= formatter.parse(fecha.toString());
//            return utilDate;
//        } catch (ParseException e) {
//            // handle exception
//        	return null;
//        }
    	
    	// Nueva version
    	ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
        
        return date;
    }
    
    

}
