package org.apache.commons.lang3.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.lang3.Validate;

public class FastDateFormat extends Format {
    public static final int FULL = 0;
    public static final int LONG = 1;
    public static final int MEDIUM = 2;
    public static final int SHORT = 3;
    private static ConcurrentMap<TimeZoneDisplayKey, String> cTimeZoneDisplayCache = new ConcurrentHashMap(7);
    private static final FormatCache<FastDateFormat> cache = new FormatCache<FastDateFormat>() {
        /* access modifiers changed from: protected */
        public FastDateFormat createInstance(String str, TimeZone timeZone, Locale locale) {
            return new FastDateFormat(str, timeZone, locale);
        }
    };
    private static final long serialVersionUID = 1;
    private final Locale mLocale;
    private transient int mMaxLengthEstimate;
    private final String mPattern;
    private transient Rule[] mRules;
    private final TimeZone mTimeZone;

    private static class CharacterLiteral implements Rule {
        private final char mValue;

        public int estimateLength() {
            return 1;
        }

        CharacterLiteral(char c) {
            this.mValue = c;
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.mValue);
        }
    }

    private interface NumberRule extends Rule {
        void appendTo(StringBuffer stringBuffer, int i);
    }

    private static class PaddedNumberField implements NumberRule {
        private final int mField;
        private final int mSize;

        public int estimateLength() {
            return 4;
        }

        PaddedNumberField(int i, int i2) {
            if (i2 < 3) {
                throw new IllegalArgumentException();
            }
            this.mField = i;
            this.mSize = i2;
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(this.mField));
        }

        public final void appendTo(StringBuffer stringBuffer, int i) {
            int i2;
            if (i < 100) {
                int i3 = this.mSize;
                while (true) {
                    i3--;
                    if (i3 >= 2) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append((char) ((i / 10) + 48));
                        stringBuffer.append((char) ((i % 10) + 48));
                        return;
                    }
                }
            } else {
                if (i < 1000) {
                    i2 = 3;
                } else {
                    Validate.isTrue(i > -1, "Negative values should not be possible", (long) i);
                    i2 = Integer.toString(i).length();
                }
                int i4 = this.mSize;
                while (true) {
                    i4--;
                    if (i4 >= i2) {
                        stringBuffer.append('0');
                    } else {
                        stringBuffer.append(Integer.toString(i));
                        return;
                    }
                }
            }
        }
    }

    private interface Rule {
        void appendTo(StringBuffer stringBuffer, Calendar calendar);

        int estimateLength();
    }

    private static class StringLiteral implements Rule {
        private final String mValue;

        StringLiteral(String str) {
            this.mValue = str;
        }

        public int estimateLength() {
            return this.mValue.length();
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.mValue);
        }
    }

    private static class TextField implements Rule {
        private final int mField;
        private final String[] mValues;

        TextField(int i, String[] strArr) {
            this.mField = i;
            this.mValues = strArr;
        }

        public int estimateLength() {
            int i = 0;
            int length = this.mValues.length;
            while (true) {
                length--;
                if (length < 0) {
                    return i;
                }
                int length2 = this.mValues[length].length();
                if (length2 > i) {
                    i = length2;
                }
            }
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.mValues[calendar.get(this.mField)]);
        }
    }

    private static class TimeZoneDisplayKey {
        private final Locale mLocale;
        private final int mStyle;
        private final TimeZone mTimeZone;

        TimeZoneDisplayKey(TimeZone timeZone, boolean z, int i, Locale locale) {
            this.mTimeZone = timeZone;
            if (z) {
                i |= Integer.MIN_VALUE;
            }
            this.mStyle = i;
            this.mLocale = locale;
        }

        public int hashCode() {
            return (((this.mStyle * 31) + this.mLocale.hashCode()) * 31) + this.mTimeZone.hashCode();
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TimeZoneDisplayKey)) {
                return false;
            }
            TimeZoneDisplayKey timeZoneDisplayKey = (TimeZoneDisplayKey) obj;
            if (!this.mTimeZone.equals(timeZoneDisplayKey.mTimeZone) || this.mStyle != timeZoneDisplayKey.mStyle || !this.mLocale.equals(timeZoneDisplayKey.mLocale)) {
                z = false;
            }
            return z;
        }
    }

    private static class TimeZoneNameRule implements Rule {
        private final String mDaylight;
        private final String mStandard;
        private final TimeZone mTimeZone;

        TimeZoneNameRule(TimeZone timeZone, Locale locale, int i) {
            this.mTimeZone = timeZone;
            this.mStandard = FastDateFormat.getTimeZoneDisplay(timeZone, false, i, locale);
            this.mDaylight = FastDateFormat.getTimeZoneDisplay(timeZone, true, i, locale);
        }

        public int estimateLength() {
            return Math.max(this.mStandard.length(), this.mDaylight.length());
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            if (!this.mTimeZone.useDaylightTime() || calendar.get(16) == 0) {
                stringBuffer.append(this.mStandard);
            } else {
                stringBuffer.append(this.mDaylight);
            }
        }
    }

    private static class TimeZoneNumberRule implements Rule {
        static final TimeZoneNumberRule INSTANCE_COLON = new TimeZoneNumberRule(true);
        static final TimeZoneNumberRule INSTANCE_NO_COLON = new TimeZoneNumberRule(false);
        final boolean mColon;

        public int estimateLength() {
            return 5;
        }

        TimeZoneNumberRule(boolean z) {
            this.mColon = z;
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(15) + calendar.get(16);
            if (i < 0) {
                stringBuffer.append('-');
                i = -i;
            } else {
                stringBuffer.append('+');
            }
            int i2 = i / 3600000;
            stringBuffer.append((char) ((i2 / 10) + 48));
            stringBuffer.append((char) ((i2 % 10) + 48));
            if (this.mColon) {
                stringBuffer.append(':');
            }
            int i3 = (i / 60000) - (60 * i2);
            stringBuffer.append((char) ((i3 / 10) + 48));
            stringBuffer.append((char) ((i3 % 10) + 48));
        }
    }

    private static class TwelveHourField implements NumberRule {
        private final NumberRule mRule;

        TwelveHourField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(10);
            if (i == 0) {
                i = calendar.getLeastMaximum(10) + 1;
            }
            this.mRule.appendTo(stringBuffer, i);
        }

        public void appendTo(StringBuffer stringBuffer, int i) {
            this.mRule.appendTo(stringBuffer, i);
        }
    }

    private static class TwentyFourHourField implements NumberRule {
        private final NumberRule mRule;

        TwentyFourHourField(NumberRule numberRule) {
            this.mRule = numberRule;
        }

        public int estimateLength() {
            return this.mRule.estimateLength();
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(11);
            if (i == 0) {
                i = calendar.getMaximum(11) + 1;
            }
            this.mRule.appendTo(stringBuffer, i);
        }

        public void appendTo(StringBuffer stringBuffer, int i) {
            this.mRule.appendTo(stringBuffer, i);
        }
    }

    private static class TwoDigitMonthField implements NumberRule {
        static final TwoDigitMonthField INSTANCE = new TwoDigitMonthField();

        public int estimateLength() {
            return 2;
        }

        TwoDigitMonthField() {
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(2) + 1);
        }

        public final void appendTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class TwoDigitNumberField implements NumberRule {
        private final int mField;

        public int estimateLength() {
            return 2;
        }

        TwoDigitNumberField(int i) {
            this.mField = i;
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(this.mField));
        }

        public final void appendTo(StringBuffer stringBuffer, int i) {
            if (i < 100) {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
                return;
            }
            stringBuffer.append(Integer.toString(i));
        }
    }

    private static class TwoDigitYearField implements NumberRule {
        static final TwoDigitYearField INSTANCE = new TwoDigitYearField();

        public int estimateLength() {
            return 2;
        }

        TwoDigitYearField() {
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(1) % 100);
        }

        public final void appendTo(StringBuffer stringBuffer, int i) {
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class UnpaddedMonthField implements NumberRule {
        static final UnpaddedMonthField INSTANCE = new UnpaddedMonthField();

        public int estimateLength() {
            return 2;
        }

        UnpaddedMonthField() {
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(2) + 1);
        }

        public final void appendTo(StringBuffer stringBuffer, int i) {
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
                return;
            }
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class UnpaddedNumberField implements NumberRule {
        private final int mField;

        public int estimateLength() {
            return 4;
        }

        UnpaddedNumberField(int i) {
            this.mField = i;
        }

        public void appendTo(StringBuffer stringBuffer, Calendar calendar) {
            appendTo(stringBuffer, calendar.get(this.mField));
        }

        public final void appendTo(StringBuffer stringBuffer, int i) {
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
            } else if (i < 100) {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
            } else {
                stringBuffer.append(Integer.toString(i));
            }
        }
    }

    public static FastDateFormat getInstance() {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(3), Integer.valueOf(3), null, null);
    }

    public static FastDateFormat getInstance(String str) {
        return (FastDateFormat) cache.getInstance(str, null, null);
    }

    public static FastDateFormat getInstance(String str, TimeZone timeZone) {
        return (FastDateFormat) cache.getInstance(str, timeZone, null);
    }

    public static FastDateFormat getInstance(String str, Locale locale) {
        return (FastDateFormat) cache.getInstance(str, null, locale);
    }

    public static FastDateFormat getInstance(String str, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) cache.getInstance(str, timeZone, locale);
    }

    public static FastDateFormat getDateInstance(int i) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), null, null, null);
    }

    public static FastDateFormat getDateInstance(int i, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), null, null, locale);
    }

    public static FastDateFormat getDateInstance(int i, TimeZone timeZone) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), null, timeZone, null);
    }

    public static FastDateFormat getDateInstance(int i, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), null, timeZone, locale);
    }

    public static FastDateFormat getTimeInstance(int i) {
        return (FastDateFormat) cache.getDateTimeInstance(null, Integer.valueOf(i), null, null);
    }

    public static FastDateFormat getTimeInstance(int i, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(null, Integer.valueOf(i), null, locale);
    }

    public static FastDateFormat getTimeInstance(int i, TimeZone timeZone) {
        return (FastDateFormat) cache.getDateTimeInstance(null, Integer.valueOf(i), timeZone, null);
    }

    public static FastDateFormat getTimeInstance(int i, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(null, Integer.valueOf(i), timeZone, locale);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), Integer.valueOf(i2), null, null);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), Integer.valueOf(i2), null, locale);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, TimeZone timeZone) {
        return getDateTimeInstance(i, i2, timeZone, null);
    }

    public static FastDateFormat getDateTimeInstance(int i, int i2, TimeZone timeZone, Locale locale) {
        return (FastDateFormat) cache.getDateTimeInstance(Integer.valueOf(i), Integer.valueOf(i2), timeZone, locale);
    }

    static String getTimeZoneDisplay(TimeZone timeZone, boolean z, int i, Locale locale) {
        TimeZoneDisplayKey timeZoneDisplayKey = new TimeZoneDisplayKey(timeZone, z, i, locale);
        String str = (String) cTimeZoneDisplayCache.get(timeZoneDisplayKey);
        if (str != null) {
            return str;
        }
        String displayName = timeZone.getDisplayName(z, i, locale);
        String str2 = (String) cTimeZoneDisplayCache.putIfAbsent(timeZoneDisplayKey, displayName);
        return str2 != null ? str2 : displayName;
    }

    protected FastDateFormat(String str, TimeZone timeZone, Locale locale) {
        this.mPattern = str;
        this.mTimeZone = timeZone;
        this.mLocale = locale;
        init();
    }

    private void init() {
        List parsePattern = parsePattern();
        this.mRules = (Rule[]) parsePattern.toArray(new Rule[parsePattern.size()]);
        int i = 0;
        int length = this.mRules.length;
        while (true) {
            length--;
            if (length >= 0) {
                i += this.mRules[length].estimateLength();
            } else {
                this.mMaxLengthEstimate = i;
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<org.apache.commons.lang3.time.FastDateFormat.Rule> parsePattern() {
        /*
            r17 = this;
            r0 = r17
            java.text.DateFormatSymbols r1 = new java.text.DateFormatSymbols
            java.util.Locale r2 = r0.mLocale
            r1.<init>(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.lang.String[] r3 = r1.getEras()
            java.lang.String[] r4 = r1.getMonths()
            java.lang.String[] r5 = r1.getShortMonths()
            java.lang.String[] r6 = r1.getWeekdays()
            java.lang.String[] r7 = r1.getShortWeekdays()
            java.lang.String[] r1 = r1.getAmPmStrings()
            java.lang.String r8 = r0.mPattern
            int r8 = r8.length()
            r9 = 1
            int[] r10 = new int[r9]
            r11 = 0
            r12 = r11
        L_0x0031:
            if (r12 >= r8) goto L_0x0155
            r10[r11] = r12
            java.lang.String r12 = r0.mPattern
            java.lang.String r12 = r0.parseToken(r12, r10)
            r13 = r10[r11]
            int r14 = r12.length()
            if (r14 != 0) goto L_0x0045
            goto L_0x0155
        L_0x0045:
            char r15 = r12.charAt(r11)
            r11 = 4
            switch(r15) {
                case 39: goto L_0x0130;
                case 68: goto L_0x0129;
                case 69: goto L_0x011c;
                case 70: goto L_0x0115;
                case 71: goto L_0x010d;
                case 72: goto L_0x0106;
                case 75: goto L_0x00ff;
                case 77: goto L_0x00e4;
                case 83: goto L_0x00dd;
                case 87: goto L_0x00d8;
                case 90: goto L_0x00cf;
                case 97: goto L_0x00c6;
                case 100: goto L_0x00bf;
                case 104: goto L_0x00b2;
                case 107: goto L_0x00a5;
                case 109: goto L_0x009d;
                case 115: goto L_0x0095;
                case 119: goto L_0x008e;
                case 121: goto L_0x007e;
                case 122: goto L_0x0064;
                default: goto L_0x004d;
            }
        L_0x004d:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Illegal pattern component: "
            r2.append(r3)
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0064:
            if (r14 < r11) goto L_0x0071
            org.apache.commons.lang3.time.FastDateFormat$TimeZoneNameRule r11 = new org.apache.commons.lang3.time.FastDateFormat$TimeZoneNameRule
            java.util.TimeZone r12 = r0.mTimeZone
            java.util.Locale r14 = r0.mLocale
            r11.<init>(r12, r14, r9)
            goto L_0x012e
        L_0x0071:
            org.apache.commons.lang3.time.FastDateFormat$TimeZoneNameRule r11 = new org.apache.commons.lang3.time.FastDateFormat$TimeZoneNameRule
            java.util.TimeZone r12 = r0.mTimeZone
            java.util.Locale r14 = r0.mLocale
            r15 = 0
            r11.<init>(r12, r14, r15)
            r14 = r15
            goto L_0x014d
        L_0x007e:
            r12 = 2
            if (r14 != r12) goto L_0x0085
            org.apache.commons.lang3.time.FastDateFormat$TwoDigitYearField r11 = org.apache.commons.lang3.time.FastDateFormat.TwoDigitYearField.INSTANCE
            goto L_0x012e
        L_0x0085:
            if (r14 >= r11) goto L_0x0088
            r14 = r11
        L_0x0088:
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r9, r14)
            goto L_0x012e
        L_0x008e:
            r11 = 3
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
            goto L_0x012e
        L_0x0095:
            r11 = 13
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
            goto L_0x012e
        L_0x009d:
            r11 = 12
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
            goto L_0x012e
        L_0x00a5:
            org.apache.commons.lang3.time.FastDateFormat$TwentyFourHourField r11 = new org.apache.commons.lang3.time.FastDateFormat$TwentyFourHourField
            r12 = 11
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r12 = r0.selectNumberRule(r12, r14)
            r11.<init>(r12)
            goto L_0x012e
        L_0x00b2:
            org.apache.commons.lang3.time.FastDateFormat$TwelveHourField r11 = new org.apache.commons.lang3.time.FastDateFormat$TwelveHourField
            r12 = 10
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r12 = r0.selectNumberRule(r12, r14)
            r11.<init>(r12)
            goto L_0x012e
        L_0x00bf:
            r11 = 5
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
            goto L_0x012e
        L_0x00c6:
            org.apache.commons.lang3.time.FastDateFormat$TextField r11 = new org.apache.commons.lang3.time.FastDateFormat$TextField
            r12 = 9
            r11.<init>(r12, r1)
            goto L_0x012e
        L_0x00cf:
            if (r14 != r9) goto L_0x00d5
            org.apache.commons.lang3.time.FastDateFormat$TimeZoneNumberRule r11 = org.apache.commons.lang3.time.FastDateFormat.TimeZoneNumberRule.INSTANCE_NO_COLON
            goto L_0x012e
        L_0x00d5:
            org.apache.commons.lang3.time.FastDateFormat$TimeZoneNumberRule r11 = org.apache.commons.lang3.time.FastDateFormat.TimeZoneNumberRule.INSTANCE_COLON
            goto L_0x012e
        L_0x00d8:
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
            goto L_0x012e
        L_0x00dd:
            r11 = 14
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
            goto L_0x012e
        L_0x00e4:
            if (r14 < r11) goto L_0x00ed
            org.apache.commons.lang3.time.FastDateFormat$TextField r11 = new org.apache.commons.lang3.time.FastDateFormat$TextField
            r12 = 2
            r11.<init>(r12, r4)
            goto L_0x012e
        L_0x00ed:
            r11 = 3
            r12 = 2
            if (r14 != r11) goto L_0x00f7
            org.apache.commons.lang3.time.FastDateFormat$TextField r11 = new org.apache.commons.lang3.time.FastDateFormat$TextField
            r11.<init>(r12, r5)
            goto L_0x012e
        L_0x00f7:
            if (r14 != r12) goto L_0x00fc
            org.apache.commons.lang3.time.FastDateFormat$TwoDigitMonthField r11 = org.apache.commons.lang3.time.FastDateFormat.TwoDigitMonthField.INSTANCE
            goto L_0x012e
        L_0x00fc:
            org.apache.commons.lang3.time.FastDateFormat$UnpaddedMonthField r11 = org.apache.commons.lang3.time.FastDateFormat.UnpaddedMonthField.INSTANCE
            goto L_0x012e
        L_0x00ff:
            r11 = 10
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
            goto L_0x012e
        L_0x0106:
            r11 = 11
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
            goto L_0x012e
        L_0x010d:
            org.apache.commons.lang3.time.FastDateFormat$TextField r11 = new org.apache.commons.lang3.time.FastDateFormat$TextField
            r12 = 0
            r11.<init>(r12, r3)
            r14 = r12
            goto L_0x014d
        L_0x0115:
            r11 = 8
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
            goto L_0x012e
        L_0x011c:
            org.apache.commons.lang3.time.FastDateFormat$TextField r12 = new org.apache.commons.lang3.time.FastDateFormat$TextField
            r15 = 7
            if (r14 >= r11) goto L_0x0123
            r11 = r7
            goto L_0x0124
        L_0x0123:
            r11 = r6
        L_0x0124:
            r12.<init>(r15, r11)
            r11 = r12
            goto L_0x012e
        L_0x0129:
            r11 = 6
            org.apache.commons.lang3.time.FastDateFormat$NumberRule r11 = r0.selectNumberRule(r11, r14)
        L_0x012e:
            r14 = 0
            goto L_0x014d
        L_0x0130:
            java.lang.String r11 = r12.substring(r9)
            int r12 = r11.length()
            if (r12 != r9) goto L_0x0146
            org.apache.commons.lang3.time.FastDateFormat$CharacterLiteral r12 = new org.apache.commons.lang3.time.FastDateFormat$CharacterLiteral
            r14 = 0
            char r11 = r11.charAt(r14)
            r12.<init>(r11)
        L_0x0144:
            r11 = r12
            goto L_0x014d
        L_0x0146:
            r14 = 0
            org.apache.commons.lang3.time.FastDateFormat$StringLiteral r12 = new org.apache.commons.lang3.time.FastDateFormat$StringLiteral
            r12.<init>(r11)
            goto L_0x0144
        L_0x014d:
            r2.add(r11)
            int r12 = r13 + 1
            r11 = r14
            goto L_0x0031
        L_0x0155:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.time.FastDateFormat.parsePattern():java.util.List");
    }

    /* access modifiers changed from: protected */
    public String parseToken(String str, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        int i = iArr[0];
        int length = str.length();
        char charAt = str.charAt(i);
        if ((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z')) {
            sb.append(charAt);
            while (true) {
                int i2 = i + 1;
                if (i2 >= length || str.charAt(i2) != charAt) {
                    break;
                }
                sb.append(charAt);
                i = i2;
            }
        } else {
            sb.append('\'');
            boolean z = false;
            while (true) {
                if (i >= length) {
                    break;
                }
                char charAt2 = str.charAt(i);
                if (charAt2 == '\'') {
                    int i3 = i + 1;
                    if (i3 >= length || str.charAt(i3) != '\'') {
                        z = !z;
                    } else {
                        sb.append(charAt2);
                        i = i3;
                    }
                } else if (z || ((charAt2 < 'A' || charAt2 > 'Z') && (charAt2 < 'a' || charAt2 > 'z'))) {
                    sb.append(charAt2);
                }
                i++;
            }
            i--;
        }
        iArr[0] = i;
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public NumberRule selectNumberRule(int i, int i2) {
        switch (i2) {
            case 1:
                return new UnpaddedNumberField(i);
            case 2:
                return new TwoDigitNumberField(i);
            default:
                return new PaddedNumberField(i, i2);
        }
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Date) {
            return format((Date) obj, stringBuffer);
        }
        if (obj instanceof Calendar) {
            return format((Calendar) obj, stringBuffer);
        }
        if (obj instanceof Long) {
            return format(((Long) obj).longValue(), stringBuffer);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown class: ");
        sb.append(obj == null ? "<null>" : obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public String format(long j) {
        return format(new Date(j));
    }

    public String format(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.mTimeZone, this.mLocale);
        gregorianCalendar.setTime(date);
        return applyRules(gregorianCalendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    public String format(Calendar calendar) {
        return format(calendar, new StringBuffer(this.mMaxLengthEstimate)).toString();
    }

    public StringBuffer format(long j, StringBuffer stringBuffer) {
        return format(new Date(j), stringBuffer);
    }

    public StringBuffer format(Date date, StringBuffer stringBuffer) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.mTimeZone, this.mLocale);
        gregorianCalendar.setTime(date);
        return applyRules(gregorianCalendar, stringBuffer);
    }

    public StringBuffer format(Calendar calendar, StringBuffer stringBuffer) {
        return applyRules(calendar, stringBuffer);
    }

    /* access modifiers changed from: protected */
    public StringBuffer applyRules(Calendar calendar, StringBuffer stringBuffer) {
        for (Rule appendTo : this.mRules) {
            appendTo.appendTo(stringBuffer, calendar);
        }
        return stringBuffer;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        parsePosition.setIndex(0);
        parsePosition.setErrorIndex(0);
        return null;
    }

    public String getPattern() {
        return this.mPattern;
    }

    public TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    public Locale getLocale() {
        return this.mLocale;
    }

    public int getMaxLengthEstimate() {
        return this.mMaxLengthEstimate;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof FastDateFormat)) {
            return false;
        }
        FastDateFormat fastDateFormat = (FastDateFormat) obj;
        if (this.mPattern.equals(fastDateFormat.mPattern) && this.mTimeZone.equals(fastDateFormat.mTimeZone) && this.mLocale.equals(fastDateFormat.mLocale)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return this.mPattern.hashCode() + (13 * (this.mTimeZone.hashCode() + (this.mLocale.hashCode() * 13)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FastDateFormat[");
        sb.append(this.mPattern);
        sb.append("]");
        return sb.toString();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init();
    }
}
