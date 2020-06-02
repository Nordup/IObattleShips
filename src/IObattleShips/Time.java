package IObattleShips;
import	java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
	
	public static String getDateTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		String formattedDateTime = localDateTime.format(formatter);
		return formattedDateTime;
	}
}