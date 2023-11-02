import java.util.Calendar;

public class Main {
	public static void main(String[] args) {
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		int date = today.get(Calendar.DATE);
		System.out.printf("%d\n%02d\n%02d", year, month, date);
	}
}