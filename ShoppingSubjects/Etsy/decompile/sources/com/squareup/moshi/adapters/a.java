package com.squareup.moshi.adapters;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.ClassUtils;

/* compiled from: Iso8601Utils */
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
        sb.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        a(sb, gregorianCalendar.get(14), "sss".length());
        sb.append('Z');
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ce A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d6 A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Date a(java.lang.String r18) {
        /*
            r1 = r18
            r2 = 4
            r3 = 0
            int r4 = a(r1, r3, r2)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r5 = 45
            boolean r6 = a(r1, r2, r5)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r6 == 0) goto L_0x0011
            r2 = 5
        L_0x0011:
            int r6 = r2 + 2
            int r2 = a(r1, r2, r6)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            boolean r8 = a(r1, r6, r5)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r8 == 0) goto L_0x001f
            int r6 = r6 + 1
        L_0x001f:
            int r8 = r6 + 2
            int r6 = a(r1, r6, r8)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r9 = 84
            boolean r9 = a(r1, r8, r9)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r10 = 1
            if (r9 != 0) goto L_0x003f
            int r11 = r18.length()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r11 > r8) goto L_0x003f
            java.util.GregorianCalendar r3 = new java.util.GregorianCalendar     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            int r2 = r2 - r10
            r3.<init>(r4, r2, r6)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.util.Date r2 = r3.getTime()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            return r2
        L_0x003f:
            r11 = 43
            r12 = 90
            if (r9 == 0) goto L_0x00bf
            int r8 = r8 + 1
            int r9 = r8 + 2
            int r8 = a(r1, r8, r9)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r13 = 58
            boolean r14 = a(r1, r9, r13)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r14 == 0) goto L_0x0057
            int r9 = r9 + 1
        L_0x0057:
            int r14 = r9 + 2
            int r9 = a(r1, r9, r14)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            boolean r13 = a(r1, r14, r13)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r13 == 0) goto L_0x0065
            int r14 = r14 + 1
        L_0x0065:
            int r13 = r18.length()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r13 <= r14) goto L_0x00b8
            char r13 = r1.charAt(r14)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r13 == r12) goto L_0x00b8
            if (r13 == r11) goto L_0x00b8
            if (r13 == r5) goto L_0x00b8
            int r13 = r14 + 2
            int r14 = a(r1, r14, r13)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r15 = 59
            if (r14 <= r15) goto L_0x0085
            r15 = 63
            if (r14 >= r15) goto L_0x0085
            r14 = 59
        L_0x0085:
            r15 = 46
            boolean r15 = a(r1, r13, r15)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r15 == 0) goto L_0x00b2
            int r13 = r13 + 1
            int r15 = r13 + 1
            int r15 = a(r1, r15)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            int r7 = r13 + 3
            int r7 = java.lang.Math.min(r15, r7)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            int r10 = a(r1, r13, r7)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r16 = r4
            r3 = 4621819117588971520(0x4024000000000000, double:10.0)
            int r7 = r7 - r13
            int r7 = 3 - r7
            r17 = r6
            double r5 = (double) r7     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            double r3 = java.lang.Math.pow(r3, r5)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            double r5 = (double) r10     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            double r3 = r3 * r5
            int r3 = (int) r3     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r13 = r15
            goto L_0x00c8
        L_0x00b2:
            r16 = r4
            r17 = r6
            r3 = 0
            goto L_0x00c8
        L_0x00b8:
            r16 = r4
            r17 = r6
            r13 = r14
            r3 = 0
            goto L_0x00c7
        L_0x00bf:
            r16 = r4
            r17 = r6
            r13 = r8
            r3 = 0
            r8 = 0
            r9 = 0
        L_0x00c7:
            r14 = 0
        L_0x00c8:
            int r4 = r18.length()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r4 > r13) goto L_0x00d6
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r3 = "No time zone indicator"
            r2.<init>(r3)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            throw r2     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
        L_0x00d6:
            char r4 = r1.charAt(r13)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r4 != r12) goto L_0x00e0
            java.util.TimeZone r4 = a     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            goto L_0x016c
        L_0x00e0:
            if (r4 == r11) goto L_0x0103
            r5 = 45
            if (r4 != r5) goto L_0x00e7
            goto L_0x0103
        L_0x00e7:
            java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r3.<init>()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r5 = "Invalid time zone indicator '"
            r3.append(r5)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r3.append(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r4 = "'"
            r3.append(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r3 = r3.toString()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r2.<init>(r3)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            throw r2     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
        L_0x0103:
            java.lang.String r4 = r1.substring(r13)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r5 = "+0000"
            boolean r5 = r5.equals(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r5 != 0) goto L_0x016a
            java.lang.String r5 = "+00:00"
            boolean r5 = r5.equals(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r5 == 0) goto L_0x0118
            goto L_0x016a
        L_0x0118:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r5.<init>()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r6 = "GMT"
            r5.append(r6)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r5.append(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r4 = r5.toString()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.util.TimeZone r5 = java.util.TimeZone.getTimeZone(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r6 = r5.getID()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            boolean r7 = r6.equals(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r7 != 0) goto L_0x0168
            java.lang.String r7 = ":"
            java.lang.String r10 = ""
            java.lang.String r6 = r6.replace(r7, r10)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            boolean r6 = r6.equals(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            if (r6 != 0) goto L_0x0168
            java.lang.IndexOutOfBoundsException r2 = new java.lang.IndexOutOfBoundsException     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r3.<init>()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r6 = "Mismatching time zone indicator: "
            r3.append(r6)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r3.append(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r4 = " given, resolves to "
            r3.append(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r4 = r5.getID()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r3.append(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.lang.String r3 = r3.toString()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r2.<init>(r3)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            throw r2     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
        L_0x0168:
            r4 = r5
            goto L_0x016c
        L_0x016a:
            java.util.TimeZone r4 = a     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
        L_0x016c:
            java.util.GregorianCalendar r5 = new java.util.GregorianCalendar     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r4 = 0
            r5.setLenient(r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r4 = r16
            r6 = 1
            r5.set(r6, r4)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            int r2 = r2 - r6
            r4 = 2
            r5.set(r4, r2)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r2 = r17
            r4 = 5
            r5.set(r4, r2)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r2 = 11
            r5.set(r2, r8)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r2 = 12
            r5.set(r2, r9)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r2 = 13
            r5.set(r2, r14)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            r2 = 14
            r5.set(r2, r3)     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            java.util.Date r2 = r5.getTime()     // Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x019f }
            return r2
        L_0x019f:
            r0 = move-exception
            r2 = r0
            com.squareup.moshi.JsonDataException r3 = new com.squareup.moshi.JsonDataException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Not an RFC 3339 date: "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r3.<init>(r1, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.adapters.a.a(java.lang.String):java.util.Date");
    }

    private static boolean a(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int a(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit < 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid number: ");
                sb.append(str.substring(i, i2));
                throw new NumberFormatException(sb.toString());
            }
            i3 = -digit;
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 < 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid number: ");
                sb2.append(str.substring(i, i2));
                throw new NumberFormatException(sb2.toString());
            }
            i3 = (i3 * 10) - digit2;
            i4 = i5;
        }
        return -i3;
    }

    private static void a(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int a(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
