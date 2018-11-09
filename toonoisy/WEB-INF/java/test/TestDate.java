package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestDate {
	public static void main(String[] args) throws ParseException {
		/*Date nowDate = new Date(System.currentTimeMillis());
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String aTime = "2018-10-17 23:08:20";
		Date newDate = df.parse(aTime);
		
		int i = nowDate.compareTo(newDate);
		System.out.println(i);*/
		
		Calendar c = Calendar.getInstance();
		Date d = new Date();
		c.setTime(d);
		System.out.println(c);
		
	}
}
