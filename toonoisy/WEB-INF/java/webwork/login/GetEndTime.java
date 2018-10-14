package login;

import java.util.Calendar;

public class GetEndTime {
	
	public String getendtime() {
	Calendar c = Calendar.getInstance();
	
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH); 
	int date = c.get(Calendar.DATE); 
	int hour = c.get(Calendar.HOUR_OF_DAY);
	int minute = c.get(Calendar.MINUTE) + 10; 
	int second = c.get(Calendar.SECOND); 
	
	String endTime = (year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second);
	return endTime;
	}
}
