package Main;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateMaker {

	/* DateTimeFormatter in format of Israel Local Date */
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
	
	/* static object of LocalDate */
	private static LocalDate localDate;
	
	/* static object of String */
	private static String customDate;

	
	/* This static method receive String of date and convert it to LocalDate object */
	public static LocalDate convertStringDate(String endDate) {
		localDate = LocalDate.parse(endDate, formatter);
		return localDate;
	}

	/* This static method receive LocalDate and convert it to String of date */
	public static String DateStringFormat(LocalDate date) {
		customDate = date.format(formatter);
		return customDate;
	}

	/* This static method receive Date and convert it to Local Date*/
	public static LocalDate convertDateLocal(Date date) {
		customDate = date.toString();
		localDate = LocalDate.parse(customDate);
		return localDate;
	}
}
