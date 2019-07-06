package org.apache.commons.math3.util;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class CompositeFormat {
    private CompositeFormat() {
    }

    public static NumberFormat getDefaultNumberFormat() {
        return getDefaultNumberFormat(Locale.getDefault());
    }

    public static NumberFormat getDefaultNumberFormat(Locale locale) {
        NumberFormat instance = NumberFormat.getInstance(locale);
        instance.setMaximumFractionDigits(10);
        return instance;
    }

    public static void parseAndIgnoreWhitespace(String str, ParsePosition parsePosition) {
        parseNextCharacter(str, parsePosition);
        parsePosition.setIndex(parsePosition.getIndex() - 1);
    }

    public static char parseNextCharacter(String str, ParsePosition parsePosition) {
        int i;
        char charAt;
        int index = parsePosition.getIndex();
        int length = str.length();
        if (index < length) {
            while (true) {
                i = index + 1;
                charAt = str.charAt(index);
                if (!Character.isWhitespace(charAt) || i >= length) {
                    parsePosition.setIndex(i);
                } else {
                    index = i;
                }
            }
            parsePosition.setIndex(i);
            if (i < length) {
                return charAt;
            }
        }
        return 0;
    }

    private static Number parseNumber(String str, double d, ParsePosition parsePosition) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append(d);
        sb.append(')');
        int length = sb.length();
        int index = parsePosition.getIndex();
        int i = length + index;
        if (i >= str.length() || str.substring(index, i).compareTo(sb.toString()) != 0) {
            return null;
        }
        Double valueOf = Double.valueOf(d);
        parsePosition.setIndex(i);
        return valueOf;
    }

    public static Number parseNumber(String str, NumberFormat numberFormat, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        Number parse = numberFormat.parse(str, parsePosition);
        if (index == parsePosition.getIndex()) {
            double[] dArr = {Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
            for (double parseNumber : dArr) {
                parse = parseNumber(str, parseNumber, parsePosition);
                if (parse != null) {
                    break;
                }
            }
        }
        return parse;
    }

    public static boolean parseFixedstring(String str, String str2, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        int length = str2.length() + index;
        if (index >= str.length() || length > str.length() || str.substring(index, length).compareTo(str2) != 0) {
            parsePosition.setIndex(index);
            parsePosition.setErrorIndex(index);
            return false;
        }
        parsePosition.setIndex(length);
        return true;
    }

    public static StringBuffer formatDouble(double d, NumberFormat numberFormat, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            stringBuffer.append('(');
            stringBuffer.append(d);
            stringBuffer.append(')');
        } else {
            numberFormat.format(d, stringBuffer, fieldPosition);
        }
        return stringBuffer;
    }
}
