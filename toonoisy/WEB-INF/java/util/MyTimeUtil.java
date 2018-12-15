package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTimeUtil {
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}
