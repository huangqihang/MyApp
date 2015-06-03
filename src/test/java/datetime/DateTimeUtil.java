package datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil {
	
	public static String format(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
}
