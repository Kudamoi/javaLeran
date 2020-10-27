import net.sf.saxon.functions.FormatDate;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        int day = 10;
        int month = 04;
        int year = 2001;

        System.out.println(collectBirthdays(year, month, day));

    }

    public static String collectBirthdays(int year, int month, int day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(" - dd.MM.yyyy - EE", Locale.ENGLISH);
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        Calendar date = new GregorianCalendar();
        System.out.println();
        StringBuilder dateCalendar = new StringBuilder("");
        System.out.println(calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DAY_OF_MONTH) + " ");
        System.out.println(date.get(Calendar.YEAR) + " " + date.get(Calendar.MONTH) + " " + date.get(Calendar.DAY_OF_MONTH) + " ");
        for (int i = 0; calendar.get(Calendar.YEAR) <= date.get(Calendar.YEAR); i++) {
            if (calendar.get(Calendar.YEAR) == date.get(Calendar.YEAR)) {
                if (calendar.get(Calendar.MONTH) < date.get(Calendar.MONTH)) {
                    dateCalendar.append(i).append(dateFormat.format(calendar.getTime())).append("\n");
                } else if (calendar.get(Calendar.MONTH) == date.get(Calendar.MONTH)) {
                    if (calendar.get(Calendar.DAY_OF_MONTH) <= date.get(Calendar.DAY_OF_MONTH))
                        dateCalendar.append(i).append(dateFormat.format(calendar.getTime())).append("\n");
                }
            } else dateCalendar.append(i).append(dateFormat.format(calendar.getTime())).append("\n");

            calendar.roll(Calendar.YEAR, +1);
        }
        return dateCalendar.toString();
    }
}

