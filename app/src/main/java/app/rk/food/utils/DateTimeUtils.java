package app.rk.food.utils;

import android.support.annotation.Nullable;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtils {
    /**
     * Format the timestamp with SimpleDateFormat
     */
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    public static final Date FAR_FUTURE = new Date(2114380800L * 1000);

    private static final PrettyTime prettyTime = new PrettyTime();
    /**
     * To get the current time
     * @return  the current time in yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime(){
        return SIMPLE_DATE_FORMAT.format( new Date());
    }
    public static long getEpochSeconds() {
        return System.currentTimeMillis() / 1000L;
    }

    /**
     * Formats a {@link Date} relative to now. Examples: "moments ago", "3 days ago".
     *
     * @param date  the {@link Date} to format. Must not be {@code null}.
     * @return      a relative datetime string like "3 weeks ago"
     */
    public static String formatRelative(@Nullable Date date) {
        if (date == null) throw new IllegalArgumentException("date cannot be null!");
        return prettyTime.format(date);
    }

}
