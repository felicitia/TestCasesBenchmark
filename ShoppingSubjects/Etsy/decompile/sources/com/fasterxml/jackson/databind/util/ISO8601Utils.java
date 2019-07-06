package com.fasterxml.jackson.databind.util;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.ClassUtils;

public class ISO8601Utils {
    private static final String GMT_ID = "GMT";
    private static final TimeZone TIMEZONE_GMT = TimeZone.getTimeZone(GMT_ID);

    public static TimeZone timeZoneGMT() {
        return TIMEZONE_GMT;
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_GMT);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_GMT);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder("yyyy-MM-ddThh:mm:ss".length() + (z ? ".sss".length() : 0) + (timeZone.getRawOffset() == 0 ? "Z" : "+hh:mm").length());
        padInt(sb, gregorianCalendar.get(1), "yyyy".length());
        char c = '-';
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, "MM".length());
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), "dd".length());
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), "hh".length());
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), "mm".length());
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), "ss".length());
        if (z) {
            sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            padInt(sb, gregorianCalendar.get(14), "sss".length());
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            padInt(sb, abs, "hh".length());
            sb.append(':');
            padInt(sb, abs2, "mm".length());
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x00a5 A[Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00ab A[Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Date parse(java.lang.String r17) {
        /*
            r1 = r17
            r2 = 4
            r3 = 0
            int r4 = parseInt(r1, r3, r2)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r5 = 45
            checkOffset(r1, r2, r5)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r2 = 7
            r6 = 5
            int r7 = parseInt(r1, r6, r2)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            checkOffset(r1, r2, r5)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r2 = 8
            r8 = 10
            int r2 = parseInt(r1, r2, r8)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r9 = 84
            checkOffset(r1, r8, r9)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r8 = 13
            r9 = 11
            int r10 = parseInt(r1, r9, r8)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r11 = 58
            checkOffset(r1, r8, r11)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r12 = 16
            r13 = 14
            int r14 = parseInt(r1, r13, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            checkOffset(r1, r12, r11)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r11 = 17
            r12 = 19
            int r11 = parseInt(r1, r11, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            char r15 = r1.charAt(r12)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r13 = 46
            if (r15 != r13) goto L_0x0057
            checkOffset(r1, r12, r13)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r12 = 20
            r13 = 23
            int r12 = parseInt(r1, r12, r13)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            goto L_0x0059
        L_0x0057:
            r13 = r12
            r12 = r3
        L_0x0059:
            char r15 = r1.charAt(r13)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r8 = 43
            if (r15 == r8) goto L_0x0082
            if (r15 != r5) goto L_0x0064
            goto L_0x0082
        L_0x0064:
            r5 = 90
            if (r15 != r5) goto L_0x006b
            java.lang.String r5 = "GMT"
            goto L_0x0097
        L_0x006b:
            java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r3.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            java.lang.String r4 = "Invalid time zone indicator "
            r3.append(r4)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r3.append(r15)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            java.lang.String r3 = r3.toString()     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r2.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            throw r2     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
        L_0x0082:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r5.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            java.lang.String r8 = "GMT"
            r5.append(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            java.lang.String r8 = r1.substring(r13)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r5.append(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            java.lang.String r5 = r5.toString()     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
        L_0x0097:
            java.util.TimeZone r8 = java.util.TimeZone.getTimeZone(r5)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            java.lang.String r13 = r8.getID()     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            boolean r5 = r13.equals(r5)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            if (r5 != 0) goto L_0x00ab
            java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r2.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            throw r2     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
        L_0x00ab:
            java.util.GregorianCalendar r5 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r5.<init>(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r5.setLenient(r3)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r3 = 1
            r5.set(r3, r4)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r4 = 2
            int r7 = r7 - r3
            r5.set(r4, r7)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r5.set(r6, r2)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r5.set(r9, r10)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r2 = 12
            r5.set(r2, r14)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r2 = 13
            r5.set(r2, r11)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            r2 = 14
            r5.set(r2, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            java.util.Date r2 = r5.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x0108, NumberFormatException -> 0x00ef, IllegalArgumentException -> 0x00d6 }
            return r2
        L_0x00d6:
            r0 = move-exception
            r2 = r0
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Failed to parse date "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r3.<init>(r1, r2)
            throw r3
        L_0x00ef:
            r0 = move-exception
            r2 = r0
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Failed to parse date "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r3.<init>(r1, r2)
            throw r3
        L_0x0108:
            r0 = move-exception
            r2 = r0
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Failed to parse date "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r3.<init>(r1, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.ISO8601Utils.parse(java.lang.String):java.util.Date");
    }

    private static void checkOffset(String str, int i, char c) throws IndexOutOfBoundsException {
        char charAt = str.charAt(i);
        if (charAt != c) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expected '");
            sb.append(c);
            sb.append("' character but found '");
            sb.append(charAt);
            sb.append("'");
            throw new IndexOutOfBoundsException(sb.toString());
        }
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        int i3 = 0;
        if (i < i2) {
            int i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid number: ");
                sb.append(str);
                throw new NumberFormatException(sb.toString());
            }
            int i5 = i4;
            i3 = -digit;
            i = i5;
        }
        while (i < i2) {
            int i6 = i + 1;
            int digit2 = Character.digit(str.charAt(i), 10);
            if (digit2 < 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid number: ");
                sb2.append(str);
                throw new NumberFormatException(sb2.toString());
            }
            i3 = (i3 * 10) - digit2;
            i = i6;
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }
}
