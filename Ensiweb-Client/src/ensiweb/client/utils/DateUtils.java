/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ensiweb.client.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Calendar resetCalendarTime(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public static Date parseDate(String date, String format) throws Exception {
        Date result = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            result = formatter.parse(date);
            return result;
        } catch (RuntimeException e) {
            return null;
        }
    }

    public static Calendar parseCalendar(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar = resetCalendarTime(calendar);
        calendar.set(year, month, date);
        return calendar;
    }

    public static Calendar getDatefromPhpDate(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int dayOfMonth = Integer.parseInt(date.substring(8, 10));

        return parseCalendar(year, month, dayOfMonth);
    }

    public static int getDatebyStack(String stackBy, String date) {
        Calendar c = getDatefromPhpDate(date);
        int tmp = 0;
        switch (stackBy) {
            case "Year":
                tmp = c.get(Calendar.YEAR);
                break;
            case "Month":
                tmp = c.get(Calendar.MONTH);
                break;
            case "Week":
                tmp = c.get(Calendar.WEEK_OF_YEAR);
                break;
            case "Day":
                tmp = c.get(Calendar.DAY_OF_MONTH);
                break;
            case "Hour":
                tmp = c.get(Calendar.HOUR_OF_DAY);
                break;
            default:
                tmp = c.get(Calendar.YEAR);
                break;
        }
        return tmp;
    }
}
