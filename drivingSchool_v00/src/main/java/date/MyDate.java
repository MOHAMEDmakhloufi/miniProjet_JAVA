package date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
	public static int  theCurrentYear() {
		int year=0;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		year = Integer.parseInt(sdf.format(date));
		return year;
	}
	
	public static boolean testAfterOrBefore(Date date) {
		Date currentDate = new Date();
		return date.after(currentDate);
	}
	
	//convert time to minute
	public static long convertHourToMinute(String hours) {
		int h=0, m=0;
		String tab[] = hours.split(":");
		try {
			h = Integer.parseInt(tab[0]);
			m = Integer.parseInt(tab[1]);
		}catch (Exception e) {}
		
		return (h * 60) + m;
	}
}

	