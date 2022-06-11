package com.openpayd.exchange.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getTodayDate() {
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
