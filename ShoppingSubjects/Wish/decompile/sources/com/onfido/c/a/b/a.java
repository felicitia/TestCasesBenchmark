package com.onfido.c.a.b;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

final class a {
    static final TimeZone a = TimeZone.getTimeZone("GMT");

    public static String a(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(a, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder("yyyy-MM-ddThh:mm:ss.sssZ".length());
        a(sb, gregorianCalendar.get(1), "yyyy".length());
        sb.append('-');
        a(sb, gregorianCalendar.get(2) + 1, "MM".length());
        sb.append('-');
        a(sb, gregorianCalendar.get(5), "dd".length());
        sb.append('T');
        a(sb, gregorianCalendar.get(11), "hh".length());
        sb.append(':');
        a(sb, gregorianCalendar.get(12), "mm".length());
        sb.append(':');
        a(sb, gregorianCalendar.get(13), "ss".length());
        sb.append('.');
        a(sb, gregorianCalendar.get(14), "sss".length());
        sb.append('Z');
        return sb.toString();
    }

    private static void a(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }
}
