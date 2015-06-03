package datetime;

import java.util.Calendar;

public class CalendarTestDriver {
	public static void main(String[] args) {
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 9+24);
		
		System.out.println(DateTimeUtil.format(c.getTime()));
		
		
	}
}
