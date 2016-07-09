package manager;

import java.text.*;
import java.util.*;

public class DateManager {
	
	public static long getHoursBetween(String dateStart, String dateEnd){
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		
		Date d1 = null;
	    Date d2 = null;
	    try {
	        d1 = format.parse(dateStart);
	        d2 = format.parse(dateEnd);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    
	    long diff = d2.getTime() - d1.getTime();
	    long diffHours = diff / (60 * 60 * 1000);
	    return diffHours;
	}
	
	public static long getMinutesBetween(String dateStart, String dateEnd){
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		
		Date d1 = null;
	    Date d2 = null;
	    try {
	        d1 = format.parse(dateStart);
	        d2 = format.parse(dateEnd);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    
	    long diff = d2.getTime() - d1.getTime();
	    long diffHours = diff / (60 * 1000);
	    return diffHours;
	}
	
	public static String getCurrentDateTime() {
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		String d = dateFormat.format(date);
		return d;
	}

}
