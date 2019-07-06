package org.apache.commons.lang3.time;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;

public class DurationFormatUtils {
    static final Object H = "H";
    public static final String ISO_EXTENDED_FORMAT_PATTERN = "'P'yyyy'Y'M'M'd'DT'H'H'm'M's.S'S'";
    static final Object M = "M";
    static final Object S = "S";
    static final Object d = "d";
    static final Object m = "m";
    static final Object s = "s";
    static final Object y = "y";

    static class Token {
        private int count;
        private final Object value;

        static boolean containsTokenWithValue(Token[] tokenArr, Object obj) {
            for (Token value2 : tokenArr) {
                if (value2.getValue() == obj) {
                    return true;
                }
            }
            return false;
        }

        Token(Object obj) {
            this.value = obj;
            this.count = 1;
        }

        Token(Object obj, int i) {
            this.value = obj;
            this.count = i;
        }

        /* access modifiers changed from: 0000 */
        public void increment() {
            this.count++;
        }

        /* access modifiers changed from: 0000 */
        public int getCount() {
            return this.count;
        }

        /* access modifiers changed from: 0000 */
        public Object getValue() {
            return this.value;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof Token)) {
                return false;
            }
            Token token = (Token) obj;
            if (this.value.getClass() != token.value.getClass() || this.count != token.count) {
                return false;
            }
            if (this.value instanceof StringBuffer) {
                return this.value.toString().equals(token.value.toString());
            }
            if (this.value instanceof Number) {
                return this.value.equals(token.value);
            }
            if (this.value == token.value) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public String toString() {
            return StringUtils.repeat(this.value.toString(), this.count);
        }
    }

    public static String formatDurationHMS(long j) {
        return formatDuration(j, "H:mm:ss.SSS");
    }

    public static String formatDurationISO(long j) {
        return formatDuration(j, ISO_EXTENDED_FORMAT_PATTERN, false);
    }

    public static String formatDuration(long j, String str) {
        return formatDuration(j, str, true);
    }

    public static String formatDuration(long j, String str, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        Token[] lexx = lexx(str);
        if (Token.containsTokenWithValue(lexx, d)) {
            int i5 = (int) (j / DateUtils.MILLIS_PER_DAY);
            j -= ((long) i5) * DateUtils.MILLIS_PER_DAY;
            i = i5;
        } else {
            i = 0;
        }
        if (Token.containsTokenWithValue(lexx, H)) {
            int i6 = (int) (j / DateUtils.MILLIS_PER_HOUR);
            j -= ((long) i6) * DateUtils.MILLIS_PER_HOUR;
            i2 = i6;
        } else {
            i2 = 0;
        }
        if (Token.containsTokenWithValue(lexx, m)) {
            int i7 = (int) (j / DateUtils.MILLIS_PER_MINUTE);
            j -= ((long) i7) * DateUtils.MILLIS_PER_MINUTE;
            i3 = i7;
        } else {
            i3 = 0;
        }
        if (Token.containsTokenWithValue(lexx, s)) {
            int i8 = (int) (j / 1000);
            j -= ((long) i8) * 1000;
            i4 = i8;
        } else {
            i4 = 0;
        }
        return format(lexx, 0, 0, i, i2, i3, i4, Token.containsTokenWithValue(lexx, S) ? (int) j : 0, z);
    }

    public static String formatDurationWords(long j, boolean z, boolean z2) {
        String formatDuration = formatDuration(j, "d' days 'H' hours 'm' minutes 's' seconds'");
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(formatDuration);
            formatDuration = sb.toString();
            String replaceOnce = StringUtils.replaceOnce(formatDuration, " 0 days", "");
            if (replaceOnce.length() != formatDuration.length()) {
                String replaceOnce2 = StringUtils.replaceOnce(replaceOnce, " 0 hours", "");
                if (replaceOnce2.length() != replaceOnce.length()) {
                    formatDuration = StringUtils.replaceOnce(replaceOnce2, " 0 minutes", "");
                    if (formatDuration.length() != formatDuration.length()) {
                        formatDuration = StringUtils.replaceOnce(formatDuration, " 0 seconds", "");
                    }
                } else {
                    formatDuration = replaceOnce;
                }
            }
            if (formatDuration.length() != 0) {
                formatDuration = formatDuration.substring(1);
            }
        }
        if (z2) {
            String replaceOnce3 = StringUtils.replaceOnce(formatDuration, " 0 seconds", "");
            if (replaceOnce3.length() != formatDuration.length()) {
                formatDuration = StringUtils.replaceOnce(replaceOnce3, " 0 minutes", "");
                if (formatDuration.length() != replaceOnce3.length()) {
                    String replaceOnce4 = StringUtils.replaceOnce(formatDuration, " 0 hours", "");
                    if (replaceOnce4.length() != formatDuration.length()) {
                        formatDuration = StringUtils.replaceOnce(replaceOnce4, " 0 days", "");
                    }
                } else {
                    formatDuration = replaceOnce3;
                }
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb2.append(formatDuration);
        return StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(StringUtils.replaceOnce(sb2.toString(), " 1 seconds", " 1 second"), " 1 minutes", " 1 minute"), " 1 hours", " 1 hour"), " 1 days", " 1 day").trim();
    }

    public static String formatPeriodISO(long j, long j2) {
        return formatPeriod(j, j2, ISO_EXTENDED_FORMAT_PATTERN, false, TimeZone.getDefault());
    }

    public static String formatPeriod(long j, long j2, String str) {
        return formatPeriod(j, j2, str, true, TimeZone.getDefault());
    }

    public static String formatPeriod(long j, long j2, String str, boolean z, TimeZone timeZone) {
        int i;
        int i2;
        int i3;
        Token[] lexx = lexx(str);
        Calendar instance = Calendar.getInstance(timeZone);
        instance.setTime(new Date(j));
        Calendar instance2 = Calendar.getInstance(timeZone);
        instance2.setTime(new Date(j2));
        int i4 = instance2.get(14) - instance.get(14);
        int i5 = instance2.get(13) - instance.get(13);
        int i6 = instance2.get(12) - instance.get(12);
        int i7 = instance2.get(11) - instance.get(11);
        int i8 = instance2.get(5) - instance.get(5);
        int i9 = instance2.get(2) - instance.get(2);
        int i10 = instance2.get(1) - instance.get(1);
        while (i4 < 0) {
            i4 += 1000;
            i5--;
        }
        while (i5 < 0) {
            i5 += 60;
            i6--;
        }
        while (i6 < 0) {
            i6 += 60;
            i7--;
        }
        while (i7 < 0) {
            i7 += 24;
            i8--;
        }
        int i11 = 0;
        if (Token.containsTokenWithValue(lexx, M)) {
            while (i8 < 0) {
                i8 += instance.getActualMaximum(5);
                i9--;
                instance.add(2, 1);
            }
            while (i9 < 0) {
                i9 += 12;
                i10--;
            }
            if (!Token.containsTokenWithValue(lexx, y) && i10 != 0) {
                while (i10 != 0) {
                    i9 += i10 * 12;
                    i10 = 0;
                }
            }
            i = i9;
        } else {
            if (!Token.containsTokenWithValue(lexx, y)) {
                int i12 = instance2.get(1);
                if (i9 < 0) {
                    i12--;
                }
                while (instance.get(1) != i12) {
                    int actualMaximum = i8 + (instance.getActualMaximum(6) - instance.get(6));
                    if ((instance instanceof GregorianCalendar) && instance.get(2) == 1 && instance.get(5) == 29) {
                        actualMaximum++;
                    }
                    instance.add(1, 1);
                    i8 = actualMaximum + instance.get(6);
                }
                i10 = 0;
            }
            while (instance.get(2) != instance2.get(2)) {
                i8 += instance.getActualMaximum(5);
                instance.add(2, 1);
            }
            i = 0;
            while (i8 < 0) {
                i8 += instance.getActualMaximum(5);
                i--;
                instance.add(2, 1);
            }
        }
        int i13 = i10;
        if (!Token.containsTokenWithValue(lexx, d)) {
            i7 += 24 * i8;
            i2 = 0;
        } else {
            i2 = i8;
        }
        if (!Token.containsTokenWithValue(lexx, H)) {
            i6 += i7 * 60;
            i7 = 0;
        }
        if (!Token.containsTokenWithValue(lexx, m)) {
            i5 += 60 * i6;
            i6 = 0;
        }
        if (!Token.containsTokenWithValue(lexx, s)) {
            i3 = i4 + (1000 * i5);
        } else {
            i3 = i4;
            i11 = i5;
        }
        return format(lexx, i13, i, i2, i7, i6, i11, i3, z);
    }

    static String format(Token[] tokenArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        Token[] tokenArr2 = tokenArr;
        StringBuffer stringBuffer = new StringBuffer();
        int i8 = i7;
        boolean z2 = false;
        for (Token token : tokenArr2) {
            Object value = token.getValue();
            int count = token.getCount();
            if (value instanceof StringBuffer) {
                stringBuffer.append(value.toString());
            } else {
                if (value == y) {
                    stringBuffer.append(z ? StringUtils.leftPad(Integer.toString(i), count, '0') : Integer.toString(i));
                } else if (value == M) {
                    stringBuffer.append(z ? StringUtils.leftPad(Integer.toString(i2), count, '0') : Integer.toString(i2));
                } else if (value == d) {
                    stringBuffer.append(z ? StringUtils.leftPad(Integer.toString(i3), count, '0') : Integer.toString(i3));
                } else if (value == H) {
                    stringBuffer.append(z ? StringUtils.leftPad(Integer.toString(i4), count, '0') : Integer.toString(i4));
                } else if (value == m) {
                    stringBuffer.append(z ? StringUtils.leftPad(Integer.toString(i5), count, '0') : Integer.toString(i5));
                } else if (value == s) {
                    stringBuffer.append(z ? StringUtils.leftPad(Integer.toString(i6), count, '0') : Integer.toString(i6));
                    z2 = true;
                } else if (value == S) {
                    if (z2) {
                        i8 += 1000;
                        stringBuffer.append((z ? StringUtils.leftPad(Integer.toString(i8), count, '0') : Integer.toString(i8)).substring(1));
                    } else {
                        stringBuffer.append(z ? StringUtils.leftPad(Integer.toString(i8), count, '0') : Integer.toString(i8));
                    }
                }
                z2 = false;
            }
        }
        return stringBuffer.toString();
    }

    static Token[] lexx(String str) {
        Object obj;
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList(charArray.length);
        boolean z = false;
        StringBuffer stringBuffer = null;
        Token token = null;
        for (char c : charArray) {
            if (!z || c == '\'') {
                if (c != '\'') {
                    if (c == 'H') {
                        obj = H;
                    } else if (c == 'M') {
                        obj = M;
                    } else if (c == 'S') {
                        obj = S;
                    } else if (c == 'd') {
                        obj = d;
                    } else if (c == 'm') {
                        obj = m;
                    } else if (c == 's') {
                        obj = s;
                    } else if (c != 'y') {
                        if (stringBuffer == null) {
                            stringBuffer = new StringBuffer();
                            arrayList.add(new Token(stringBuffer));
                        }
                        stringBuffer.append(c);
                        obj = null;
                    } else {
                        obj = y;
                    }
                } else if (z) {
                    z = false;
                    stringBuffer = null;
                    obj = null;
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    arrayList.add(new Token(stringBuffer2));
                    obj = null;
                    stringBuffer = stringBuffer2;
                    z = true;
                }
                if (obj != null) {
                    if (token == null || token.getValue() != obj) {
                        token = new Token(obj);
                        arrayList.add(token);
                    } else {
                        token.increment();
                    }
                    stringBuffer = null;
                }
            } else {
                stringBuffer.append(c);
            }
        }
        return (Token[]) arrayList.toArray(new Token[arrayList.size()]);
    }
}
