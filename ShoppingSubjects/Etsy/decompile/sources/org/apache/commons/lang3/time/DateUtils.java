package org.apache.commons.lang3.time;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DateUtils {
    public static final long MILLIS_PER_DAY = 86400000;
    public static final long MILLIS_PER_HOUR = 3600000;
    public static final long MILLIS_PER_MINUTE = 60000;
    public static final long MILLIS_PER_SECOND = 1000;
    private static final int MODIFY_CEILING = 2;
    private static final int MODIFY_ROUND = 1;
    private static final int MODIFY_TRUNCATE = 0;
    public static final int RANGE_MONTH_MONDAY = 6;
    public static final int RANGE_MONTH_SUNDAY = 5;
    public static final int RANGE_WEEK_CENTER = 4;
    public static final int RANGE_WEEK_MONDAY = 2;
    public static final int RANGE_WEEK_RELATIVE = 3;
    public static final int RANGE_WEEK_SUNDAY = 1;
    public static final int SEMI_MONTH = 1001;
    private static final int[][] fields = {new int[]{14}, new int[]{13}, new int[]{12}, new int[]{11, 10}, new int[]{5, 5, 9}, new int[]{2, 1001}, new int[]{1}, new int[]{0}};

    static class DateIterator implements Iterator<Calendar> {
        private final Calendar endFinal;
        private final Calendar spot;

        DateIterator(Calendar calendar, Calendar calendar2) {
            this.endFinal = calendar2;
            this.spot = calendar;
            this.spot.add(5, -1);
        }

        public boolean hasNext() {
            return this.spot.before(this.endFinal);
        }

        public Calendar next() {
            if (this.spot.equals(this.endFinal)) {
                throw new NoSuchElementException();
            }
            this.spot.add(5, 1);
            return (Calendar) this.spot.clone();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static boolean isSameDay(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return isSameDay(instance, instance2);
    }

    public static boolean isSameDay(Calendar calendar, Calendar calendar2) {
        if (calendar != null && calendar2 != null) {
            return calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean isSameInstant(Date date, Date date2) {
        if (date != null && date2 != null) {
            return date.getTime() == date2.getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean isSameInstant(Calendar calendar, Calendar calendar2) {
        if (calendar != null && calendar2 != null) {
            return calendar.getTime().getTime() == calendar2.getTime().getTime();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean isSameLocalTime(Calendar calendar, Calendar calendar2) {
        if (calendar != null && calendar2 != null) {
            return calendar.get(14) == calendar2.get(14) && calendar.get(13) == calendar2.get(13) && calendar.get(12) == calendar2.get(12) && calendar.get(11) == calendar2.get(11) && calendar.get(6) == calendar2.get(6) && calendar.get(1) == calendar2.get(1) && calendar.get(0) == calendar2.get(0) && calendar.getClass() == calendar2.getClass();
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date parseDate(String str, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, strArr, true);
    }

    public static Date parseDateStrictly(String str, String... strArr) throws ParseException {
        return parseDateWithLeniency(str, strArr, false);
    }

    private static Date parseDateWithLeniency(String str, String[] strArr, boolean z) throws ParseException {
        if (str == null || strArr == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setLenient(z);
        ParsePosition parsePosition = new ParsePosition(0);
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String str2 = strArr[i];
            simpleDateFormat.applyPattern(str2.endsWith("ZZ") ? str2.substring(0, str2.length() - 1) : str2);
            parsePosition.setIndex(0);
            String replaceAll = str2.endsWith("ZZ") ? str.replaceAll("([-+][0-9][0-9]):([0-9][0-9])$", "$1$2") : str;
            Date parse = simpleDateFormat.parse(replaceAll, parsePosition);
            if (parse != null && parsePosition.getIndex() == replaceAll.length()) {
                return parse;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unable to parse the date: ");
        sb.append(str);
        throw new ParseException(sb.toString(), -1);
    }

    public static Date addYears(Date date, int i) {
        return add(date, 1, i);
    }

    public static Date addMonths(Date date, int i) {
        return add(date, 2, i);
    }

    public static Date addWeeks(Date date, int i) {
        return add(date, 3, i);
    }

    public static Date addDays(Date date, int i) {
        return add(date, 5, i);
    }

    public static Date addHours(Date date, int i) {
        return add(date, 11, i);
    }

    public static Date addMinutes(Date date, int i) {
        return add(date, 12, i);
    }

    public static Date addSeconds(Date date, int i) {
        return add(date, 13, i);
    }

    public static Date addMilliseconds(Date date, int i) {
        return add(date, 14, i);
    }

    private static Date add(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(i, i2);
        return instance.getTime();
    }

    public static Date setYears(Date date, int i) {
        return set(date, 1, i);
    }

    public static Date setMonths(Date date, int i) {
        return set(date, 2, i);
    }

    public static Date setDays(Date date, int i) {
        return set(date, 5, i);
    }

    public static Date setHours(Date date, int i) {
        return set(date, 11, i);
    }

    public static Date setMinutes(Date date, int i) {
        return set(date, 12, i);
    }

    public static Date setSeconds(Date date, int i) {
        return set(date, 13, i);
    }

    public static Date setMilliseconds(Date date, int i) {
        return set(date, 14, i);
    }

    private static Date set(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setLenient(false);
        instance.setTime(date);
        instance.set(i, i2);
        return instance.getTime();
    }

    public static Calendar toCalendar(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance;
    }

    public static Date round(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        modify(instance, i, 1);
        return instance.getTime();
    }

    public static Calendar round(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 1);
        return calendar2;
    }

    public static Date round(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (obj instanceof Date) {
            return round((Date) obj, i);
        } else {
            if (obj instanceof Calendar) {
                return round((Calendar) obj, i).getTime();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Could not round ");
            sb.append(obj);
            throw new ClassCastException(sb.toString());
        }
    }

    public static Date truncate(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        modify(instance, i, 0);
        return instance.getTime();
    }

    public static Calendar truncate(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 0);
        return calendar2;
    }

    public static Date truncate(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (obj instanceof Date) {
            return truncate((Date) obj, i);
        } else {
            if (obj instanceof Calendar) {
                return truncate((Calendar) obj, i).getTime();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Could not truncate ");
            sb.append(obj);
            throw new ClassCastException(sb.toString());
        }
    }

    public static Date ceiling(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        modify(instance, i, 2);
        return instance.getTime();
    }

    public static Calendar ceiling(Calendar calendar, int i) {
        if (calendar == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar calendar2 = (Calendar) calendar.clone();
        modify(calendar2, i, 2);
        return calendar2;
    }

    public static Date ceiling(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (obj instanceof Date) {
            return ceiling((Date) obj, i);
        } else {
            if (obj instanceof Calendar) {
                return ceiling((Calendar) obj, i).getTime();
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Could not find ceiling of for type: ");
            sb.append(obj.getClass());
            throw new ClassCastException(sb.toString());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:84:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x013f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void modify(java.util.Calendar r17, int r18, int r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = 1
            int r4 = r0.get(r3)
            r5 = 280000000(0x10b07600, float:6.960157E-29)
            if (r4 <= r5) goto L_0x0018
            java.lang.ArithmeticException r0 = new java.lang.ArithmeticException
            java.lang.String r1 = "Calendar value too large for accurate calculations"
            r0.<init>(r1)
            throw r0
        L_0x0018:
            r4 = 14
            if (r1 != r4) goto L_0x001d
            return
        L_0x001d:
            java.util.Date r5 = r17.getTime()
            long r6 = r5.getTime()
            int r4 = r0.get(r4)
            if (r2 == 0) goto L_0x002f
            r8 = 500(0x1f4, float:7.0E-43)
            if (r4 >= r8) goto L_0x0033
        L_0x002f:
            long r8 = (long) r4
            long r10 = r6 - r8
            r6 = r10
        L_0x0033:
            r4 = 13
            if (r1 != r4) goto L_0x0039
            r9 = r3
            goto L_0x003a
        L_0x0039:
            r9 = 0
        L_0x003a:
            int r4 = r0.get(r4)
            r10 = 30
            if (r9 != 0) goto L_0x004d
            if (r2 == 0) goto L_0x0046
            if (r4 >= r10) goto L_0x004d
        L_0x0046:
            long r11 = (long) r4
            r13 = 1000(0x3e8, double:4.94E-321)
            long r11 = r11 * r13
            long r13 = r6 - r11
            goto L_0x004e
        L_0x004d:
            r13 = r6
        L_0x004e:
            r4 = 12
            if (r1 != r4) goto L_0x0053
            r9 = r3
        L_0x0053:
            int r6 = r0.get(r4)
            if (r9 != 0) goto L_0x0065
            if (r2 == 0) goto L_0x005d
            if (r6 >= r10) goto L_0x0065
        L_0x005d:
            long r6 = (long) r6
            r9 = 60000(0xea60, double:2.9644E-319)
            long r6 = r6 * r9
            long r9 = r13 - r6
            r13 = r9
        L_0x0065:
            long r6 = r5.getTime()
            int r9 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            if (r9 == 0) goto L_0x0073
            r5.setTime(r13)
            r0.setTime(r5)
        L_0x0073:
            int[][] r5 = fields
            int r6 = r5.length
            r7 = 0
            r9 = 0
        L_0x0078:
            if (r7 >= r6) goto L_0x0144
            r10 = r5[r7]
            int r11 = r10.length
            r12 = 0
        L_0x007e:
            r14 = 15
            r15 = 1001(0x3e9, float:1.403E-42)
            r8 = 2
            r4 = 5
            if (r12 >= r11) goto L_0x00cd
            r13 = r10[r12]
            if (r13 != r1) goto L_0x00c7
            if (r2 == r8) goto L_0x0090
            if (r2 != r3) goto L_0x00c6
            if (r9 == 0) goto L_0x00c6
        L_0x0090:
            if (r1 != r15) goto L_0x00a5
            int r1 = r0.get(r4)
            if (r1 != r3) goto L_0x009c
            r0.add(r4, r14)
            goto L_0x00c6
        L_0x009c:
            r1 = -15
            r0.add(r4, r1)
            r0.add(r8, r3)
            goto L_0x00c6
        L_0x00a5:
            r2 = 9
            if (r1 != r2) goto L_0x00c0
            r1 = 11
            int r2 = r0.get(r1)
            if (r2 != 0) goto L_0x00b7
            r2 = 12
            r0.add(r1, r2)
            goto L_0x00c6
        L_0x00b7:
            r2 = -12
            r0.add(r1, r2)
            r0.add(r4, r3)
            goto L_0x00c6
        L_0x00c0:
            r13 = 0
            r1 = r10[r13]
            r0.add(r1, r3)
        L_0x00c6:
            return
        L_0x00c7:
            r13 = 0
            int r12 = r12 + 1
            r4 = 12
            goto L_0x007e
        L_0x00cd:
            r13 = 0
            r11 = 9
            if (r1 == r11) goto L_0x00ee
            if (r1 == r15) goto L_0x00d7
        L_0x00d4:
            r11 = 12
            goto L_0x0109
        L_0x00d7:
            r11 = r10[r13]
            if (r11 != r4) goto L_0x00d4
            int r4 = r0.get(r4)
            int r4 = r4 - r3
            if (r4 < r14) goto L_0x00e4
            int r4 = r4 + -15
        L_0x00e4:
            r9 = 7
            if (r4 <= r9) goto L_0x00e9
            r9 = r3
            goto L_0x00ea
        L_0x00e9:
            r9 = 0
        L_0x00ea:
            r12 = r9
            r11 = 12
            goto L_0x0106
        L_0x00ee:
            r4 = r13
            r11 = r10[r4]
            r4 = 11
            if (r11 != r4) goto L_0x00d4
            int r4 = r0.get(r4)
            r11 = 12
            if (r4 < r11) goto L_0x00ff
            int r4 = r4 + -12
        L_0x00ff:
            r9 = 6
            if (r4 < r9) goto L_0x0104
            r9 = r3
            goto L_0x0105
        L_0x0104:
            r9 = 0
        L_0x0105:
            r12 = r9
        L_0x0106:
            r9 = r4
            r4 = r3
            goto L_0x010c
        L_0x0109:
            r12 = r9
            r4 = 0
            r9 = 0
        L_0x010c:
            if (r4 != 0) goto L_0x012b
            r13 = 0
            r4 = r10[r13]
            int r4 = r0.getActualMinimum(r4)
            r9 = r10[r13]
            int r9 = r0.getActualMaximum(r9)
            r12 = r10[r13]
            int r12 = r0.get(r12)
            int r12 = r12 - r4
            int r9 = r9 - r4
            int r9 = r9 / r8
            if (r12 <= r9) goto L_0x0128
            r4 = r3
            goto L_0x0129
        L_0x0128:
            r4 = r13
        L_0x0129:
            r9 = r4
            goto L_0x0131
        L_0x012b:
            r13 = 0
            r16 = r12
            r12 = r9
            r9 = r16
        L_0x0131:
            if (r12 == 0) goto L_0x013f
            r4 = r10[r13]
            r8 = r10[r13]
            int r8 = r0.get(r8)
            int r8 = r8 - r12
            r0.set(r4, r8)
        L_0x013f:
            int r7 = r7 + 1
            r4 = r11
            goto L_0x0078
        L_0x0144:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "The field "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = " is not supported"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.DateUtils.modify(java.util.Calendar, int, int):void");
    }

    public static Iterator<Calendar> iterator(Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return iterator(instance, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0043, code lost:
        r7 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0048, code lost:
        r7 = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006f, code lost:
        if (r1 >= 1) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0071, code lost:
        r1 = r1 + 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0073, code lost:
        if (r1 <= 7) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0075, code lost:
        r1 = r1 - 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0077, code lost:
        if (r7 >= 1) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0079, code lost:
        r7 = r7 + 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007b, code lost:
        if (r7 <= 7) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007d, code lost:
        r7 = r7 - 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0083, code lost:
        if (r5.get(7) == r1) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0085, code lost:
        r5.add(5, -1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x008d, code lost:
        if (r6.get(7) == r7) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008f, code lost:
        r6.add(5, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0098, code lost:
        return new org.apache.commons.lang3.time.DateUtils.DateIterator(r5, r6);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Iterator<java.util.Calendar> iterator(java.util.Calendar r7, int r8) {
        /*
            if (r7 != 0) goto L_0x000a
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "The date must not be null"
            r7.<init>(r8)
            throw r7
        L_0x000a:
            r0 = -1
            r1 = 2
            r2 = 5
            r3 = 1
            r4 = 7
            switch(r8) {
                case 1: goto L_0x004a;
                case 2: goto L_0x004a;
                case 3: goto L_0x004a;
                case 4: goto L_0x004a;
                case 5: goto L_0x002e;
                case 6: goto L_0x002e;
                default: goto L_0x0012;
            }
        L_0x0012:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "The range style "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = " is not valid."
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r7.<init>(r8)
            throw r7
        L_0x002e:
            java.util.Calendar r7 = truncate(r7, r1)
            java.lang.Object r5 = r7.clone()
            java.util.Calendar r5 = (java.util.Calendar) r5
            r5.add(r1, r3)
            r5.add(r2, r0)
            r6 = 6
            if (r8 != r6) goto L_0x0045
            r6 = r5
            r5 = r7
        L_0x0043:
            r7 = r3
            goto L_0x006f
        L_0x0045:
            r1 = r3
            r6 = r5
            r5 = r7
        L_0x0048:
            r7 = r4
            goto L_0x006f
        L_0x004a:
            java.util.Calendar r5 = truncate(r7, r2)
            java.util.Calendar r6 = truncate(r7, r2)
            switch(r8) {
                case 1: goto L_0x006d;
                case 2: goto L_0x0043;
                case 3: goto L_0x0064;
                case 4: goto L_0x0056;
                default: goto L_0x0055;
            }
        L_0x0055:
            goto L_0x006d
        L_0x0056:
            int r8 = r7.get(r4)
            int r8 = r8 + -3
            int r7 = r7.get(r4)
            int r7 = r7 + 3
            r1 = r8
            goto L_0x006f
        L_0x0064:
            int r7 = r7.get(r4)
            int r8 = r7 + -1
            r1 = r7
            r7 = r8
            goto L_0x006f
        L_0x006d:
            r1 = r3
            goto L_0x0048
        L_0x006f:
            if (r1 >= r3) goto L_0x0073
            int r1 = r1 + 7
        L_0x0073:
            if (r1 <= r4) goto L_0x0077
            int r1 = r1 + -7
        L_0x0077:
            if (r7 >= r3) goto L_0x007b
            int r7 = r7 + 7
        L_0x007b:
            if (r7 <= r4) goto L_0x007f
            int r7 = r7 + -7
        L_0x007f:
            int r8 = r5.get(r4)
            if (r8 == r1) goto L_0x0089
            r5.add(r2, r0)
            goto L_0x007f
        L_0x0089:
            int r8 = r6.get(r4)
            if (r8 == r7) goto L_0x0093
            r6.add(r2, r3)
            goto L_0x0089
        L_0x0093:
            org.apache.commons.lang3.time.DateUtils$DateIterator r7 = new org.apache.commons.lang3.time.DateUtils$DateIterator
            r7.<init>(r5, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.DateUtils.iterator(java.util.Calendar, int):java.util.Iterator");
    }

    public static Iterator<?> iterator(Object obj, int i) {
        if (obj == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else if (obj instanceof Date) {
            return iterator((Date) obj, i);
        } else {
            if (obj instanceof Calendar) {
                return iterator((Calendar) obj, i);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Could not iterate based on ");
            sb.append(obj);
            throw new ClassCastException(sb.toString());
        }
    }

    public static long getFragmentInMilliseconds(Date date, int i) {
        return getFragment(date, i, 14);
    }

    public static long getFragmentInSeconds(Date date, int i) {
        return getFragment(date, i, 13);
    }

    public static long getFragmentInMinutes(Date date, int i) {
        return getFragment(date, i, 12);
    }

    public static long getFragmentInHours(Date date, int i) {
        return getFragment(date, i, 11);
    }

    public static long getFragmentInDays(Date date, int i) {
        return getFragment(date, i, 6);
    }

    public static long getFragmentInMilliseconds(Calendar calendar, int i) {
        return getFragment(calendar, i, 14);
    }

    public static long getFragmentInSeconds(Calendar calendar, int i) {
        return getFragment(calendar, i, 13);
    }

    public static long getFragmentInMinutes(Calendar calendar, int i) {
        return getFragment(calendar, i, 12);
    }

    public static long getFragmentInHours(Calendar calendar, int i) {
        return getFragment(calendar, i, 11);
    }

    public static long getFragmentInDays(Calendar calendar, int i) {
        return getFragment(calendar, i, 6);
    }

    private static long getFragment(Date date, int i, int i2) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return getFragment(instance, i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005e, code lost:
        r4 = r2 + ((((long) r8.get(12)) * MILLIS_PER_MINUTE) / r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return r2 + (((long) (r8.get(14) * 1)) / r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long getFragment(java.util.Calendar r8, int r9, int r10) {
        /*
            if (r8 != 0) goto L_0x000a
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "The date must not be null"
            r8.<init>(r9)
            throw r8
        L_0x000a:
            long r0 = getMillisPerUnit(r10)
            r2 = 0
            r4 = 86400000(0x5265c00, double:4.2687272E-316)
            switch(r9) {
                case 1: goto L_0x0023;
                case 2: goto L_0x0018;
                default: goto L_0x0016;
            }
        L_0x0016:
            r4 = r2
            goto L_0x002d
        L_0x0018:
            r10 = 5
            int r10 = r8.get(r10)
            long r6 = (long) r10
            long r6 = r6 * r4
            long r6 = r6 / r0
            long r4 = r2 + r6
            goto L_0x002d
        L_0x0023:
            r10 = 6
            int r10 = r8.get(r10)
            long r6 = (long) r10
            long r6 = r6 * r4
            long r6 = r6 / r0
            long r4 = r2 + r6
        L_0x002d:
            switch(r9) {
                case 1: goto L_0x0050;
                case 2: goto L_0x0050;
                case 3: goto L_0x0030;
                case 4: goto L_0x0030;
                case 5: goto L_0x0050;
                case 6: goto L_0x0050;
                case 7: goto L_0x0030;
                case 8: goto L_0x0030;
                case 9: goto L_0x0030;
                case 10: goto L_0x0030;
                case 11: goto L_0x004e;
                case 12: goto L_0x006c;
                case 13: goto L_0x004c;
                case 14: goto L_0x0085;
                default: goto L_0x0030;
            }
        L_0x0030:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = "The fragment "
            r10.append(r0)
            r10.append(r9)
            java.lang.String r9 = " is not supported"
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            r8.<init>(r9)
            throw r8
        L_0x004c:
            r2 = r4
            goto L_0x0079
        L_0x004e:
            r2 = r4
            goto L_0x005e
        L_0x0050:
            r9 = 11
            int r9 = r8.get(r9)
            long r9 = (long) r9
            r2 = 3600000(0x36ee80, double:1.7786363E-317)
            long r9 = r9 * r2
            long r9 = r9 / r0
            long r2 = r4 + r9
        L_0x005e:
            r9 = 12
            int r9 = r8.get(r9)
            long r9 = (long) r9
            r4 = 60000(0xea60, double:2.9644E-319)
            long r9 = r9 * r4
            long r9 = r9 / r0
            long r4 = r2 + r9
        L_0x006c:
            r9 = 13
            int r9 = r8.get(r9)
            long r9 = (long) r9
            r2 = 1000(0x3e8, double:4.94E-321)
            long r9 = r9 * r2
            long r9 = r9 / r0
            long r2 = r4 + r9
        L_0x0079:
            r9 = 14
            int r8 = r8.get(r9)
            int r8 = r8 * 1
            long r8 = (long) r8
            long r8 = r8 / r0
            long r4 = r2 + r8
        L_0x0085:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.DateUtils.getFragment(java.util.Calendar, int, int):long");
    }

    public static boolean truncatedEquals(Calendar calendar, Calendar calendar2, int i) {
        return truncatedCompareTo(calendar, calendar2, i) == 0;
    }

    public static boolean truncatedEquals(Date date, Date date2, int i) {
        return truncatedCompareTo(date, date2, i) == 0;
    }

    public static int truncatedCompareTo(Calendar calendar, Calendar calendar2, int i) {
        return truncate(calendar, i).compareTo(truncate(calendar2, i));
    }

    public static int truncatedCompareTo(Date date, Date date2, int i) {
        return truncate(date, i).compareTo(truncate(date2, i));
    }

    private static long getMillisPerUnit(int i) {
        switch (i) {
            case 5:
            case 6:
                return MILLIS_PER_DAY;
            default:
                switch (i) {
                    case 11:
                        return MILLIS_PER_HOUR;
                    case 12:
                        return MILLIS_PER_MINUTE;
                    case 13:
                        return 1000;
                    case 14:
                        return 1;
                    default:
                        StringBuilder sb = new StringBuilder();
                        sb.append("The unit ");
                        sb.append(i);
                        sb.append(" cannot be represented is milleseconds");
                        throw new IllegalArgumentException(sb.toString());
                }
        }
    }
}
