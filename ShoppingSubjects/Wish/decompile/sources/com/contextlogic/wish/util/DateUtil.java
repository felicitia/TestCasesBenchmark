package com.contextlogic.wish.util;

import com.contextlogic.wish.R;
import com.contextlogic.wish.application.WishApplication;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

    public static class TimeParts {
        public long days;
        public long hours;
        public long minutes;
        public long seconds;

        public boolean isExpired() {
            return this.days <= 0 && this.hours <= 0 && this.minutes <= 0 && this.seconds <= 0;
        }
    }

    public enum TimeUnit {
        SECOND(0),
        MINUTE(1),
        HOUR(2),
        DAY(3);
        
        private int mValue;

        private TimeUnit(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }
    }

    public static Date parseIsoDate(String str) throws ParseException {
        return parseIsoDate(str, true);
    }

    public static Date parseIsoDate(String str, boolean z) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        if (z) {
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        return simpleDateFormat.parse(str.substring(0, 19));
    }

    public static String isoDateFromDate(Date date) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        simpleDateFormat.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }

    public static String getLocalizedReadableDate(Date date) {
        return new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(date);
    }

    public static Date safeParseLocalizedReadableDateString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).parse(str);
        } catch (ParseException unused) {
            return null;
        }
    }

    public static String getLocalizedDate(Date date) {
        return new SimpleDateFormat("MMM dd", Locale.getDefault()).format(date);
    }

    public static String getLocalizedTime(Date date) {
        return new SimpleDateFormat("h:mm a", Locale.getDefault()).format(date);
    }

    public static String getFuzzyDateFromNow(Date date) {
        return getFuzzyDate(date, new Date());
    }

    public static String getFuzzyDate(Date date, Date date2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        WishApplication instance = WishApplication.getInstance();
        long abs = Math.abs(date2.getTime() - date.getTime()) / 1000;
        if (abs < 60) {
            if (abs <= 2) {
                str6 = instance.getResources().getString(R.string.fuzzy_date_second_ago);
            } else {
                str6 = instance.getResources().getString(R.string.fuzzy_date_second_ago_plural, new Object[]{Long.valueOf(abs)});
            }
            return str6;
        } else if (abs < 3540) {
            int floor = (int) Math.floor((double) (abs / 60));
            if (floor <= 1) {
                str5 = instance.getResources().getString(R.string.fuzzy_date_minute_ago);
            } else {
                str5 = instance.getResources().getString(R.string.fuzzy_date_minute_ago_plural, new Object[]{Integer.valueOf(floor)});
            }
            return str5;
        } else if (abs < 86400) {
            int floor2 = (int) Math.floor((double) (abs / 3600));
            if (floor2 <= 1) {
                str4 = instance.getResources().getString(R.string.fuzzy_date_hour_ago);
            } else {
                str4 = instance.getResources().getString(R.string.fuzzy_date_hour_ago_plural, new Object[]{Integer.valueOf(floor2)});
            }
            return str4;
        } else if (abs < 2592000) {
            int floor3 = (int) Math.floor((double) (abs / 86400));
            if (floor3 <= 1) {
                str3 = instance.getResources().getString(R.string.fuzzy_date_day_ago);
            } else {
                str3 = instance.getResources().getString(R.string.fuzzy_date_day_ago_plural, new Object[]{Integer.valueOf(floor3)});
            }
            return str3;
        } else if (abs < 31104000) {
            int floor4 = (int) Math.floor((double) (abs / 2592000));
            if (floor4 <= 1) {
                str2 = instance.getResources().getString(R.string.fuzzy_date_month_ago);
            } else {
                str2 = instance.getResources().getString(R.string.fuzzy_date_month_ago_plural, new Object[]{Integer.valueOf(floor4)});
            }
            return str2;
        } else {
            int floor5 = (int) Math.floor((double) (abs / 31104000));
            if (floor5 <= 1) {
                str = instance.getResources().getString(R.string.fuzzy_date_year_ago);
            } else {
                str = instance.getResources().getString(R.string.fuzzy_date_year_ago_plural, new Object[]{Integer.valueOf(floor5)});
            }
            return str;
        }
    }

    public static String getFuzzyTimeRemaining(Date date) {
        return getFuzzyTimeRemaining(date, TimeUnit.DAY);
    }

    public static String getFuzzyTimeRemaining(Date date, TimeUnit timeUnit) {
        String str;
        String str2;
        String str3;
        String str4;
        WishApplication instance = WishApplication.getInstance();
        TimeParts timeDifferenceFromDate = getTimeDifferenceFromDate(date, new Date(), null, timeUnit);
        if (timeDifferenceFromDate.days > 0) {
            if (timeDifferenceFromDate.days < 2) {
                str4 = instance.getResources().getString(R.string.fuzzy_time_remaining_day);
            } else {
                str4 = instance.getResources().getString(R.string.fuzzy_time_remaining_day_plural, new Object[]{Long.valueOf(timeDifferenceFromDate.days + 1)});
            }
            return str4;
        } else if (timeDifferenceFromDate.hours > 0) {
            if (timeDifferenceFromDate.hours < 2) {
                str3 = instance.getResources().getString(R.string.fuzzy_time_remaining_hour);
            } else {
                str3 = instance.getResources().getString(R.string.fuzzy_time_remaining_hour_plural, new Object[]{Long.valueOf(timeDifferenceFromDate.hours + 1)});
            }
            return str3;
        } else if (timeDifferenceFromDate.minutes > 0) {
            if (timeDifferenceFromDate.minutes < 2) {
                str2 = instance.getResources().getString(R.string.fuzzy_time_remaining_minute);
            } else {
                str2 = instance.getResources().getString(R.string.fuzzy_time_remaining_minute_plural, new Object[]{Long.valueOf(timeDifferenceFromDate.minutes + 1)});
            }
            return str2;
        } else if (timeDifferenceFromDate.seconds <= 0) {
            return instance.getResources().getString(R.string.expired);
        } else {
            if (timeDifferenceFromDate.seconds < 2) {
                str = instance.getResources().getString(R.string.fuzzy_time_remaining_second);
            } else {
                str = instance.getResources().getString(R.string.fuzzy_time_remaining_second_plural, new Object[]{Long.valueOf(timeDifferenceFromDate.seconds + 1)});
            }
            return str;
        }
    }

    public static TimeParts getTimeDifferenceFromNow(Date date) {
        return getTimeDifferenceFromNow(date, null);
    }

    public static TimeParts getTimeDifferenceFromNow(Date date, TimeParts timeParts) {
        return getTimeDifferenceFromDate(date, new Date(), timeParts, TimeUnit.HOUR);
    }

    public static TimeParts getTimeDifferenceFromDate(Date date, Date date2, TimeParts timeParts, TimeUnit timeUnit) {
        long j;
        long j2;
        if (timeParts == null) {
            timeParts = new TimeParts();
        }
        long time = (date.getTime() - date2.getTime()) / 1000;
        long j3 = 0;
        if (time < 0) {
            time = 0;
        }
        if (timeUnit.getValue() >= TimeUnit.DAY.getValue()) {
            j = time / 86400;
            time -= 86400 * j;
        } else {
            j = 0;
        }
        if (timeUnit.getValue() >= TimeUnit.HOUR.getValue()) {
            j2 = time / 3600;
            time -= 3600 * j2;
        } else {
            j2 = 0;
        }
        if (timeUnit.getValue() >= TimeUnit.MINUTE.getValue()) {
            long j4 = time / 60;
            j3 = j4;
            time -= 60 * j4;
        }
        timeParts.days = j;
        timeParts.hours = j2;
        timeParts.minutes = j3;
        timeParts.seconds = time;
        return timeParts;
    }
}
