package org.apache.commons.math3.fraction;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class BigFractionFormat extends AbstractFormat implements Serializable {
    private static final long serialVersionUID = -2932167925527338976L;

    public BigFractionFormat() {
    }

    public BigFractionFormat(NumberFormat numberFormat) {
        super(numberFormat);
    }

    public BigFractionFormat(NumberFormat numberFormat, NumberFormat numberFormat2) {
        super(numberFormat, numberFormat2);
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public static String formatBigFraction(BigFraction bigFraction) {
        return getImproperInstance().format(bigFraction);
    }

    public static BigFractionFormat getImproperInstance() {
        return getImproperInstance(Locale.getDefault());
    }

    public static BigFractionFormat getImproperInstance(Locale locale) {
        return new BigFractionFormat(getDefaultNumberFormat(locale));
    }

    public static BigFractionFormat getProperInstance() {
        return getProperInstance(Locale.getDefault());
    }

    public static BigFractionFormat getProperInstance(Locale locale) {
        return new ProperBigFractionFormat(getDefaultNumberFormat(locale));
    }

    public StringBuffer format(BigFraction bigFraction, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        getNumeratorFormat().format(bigFraction.getNumerator(), stringBuffer, fieldPosition);
        stringBuffer.append(" / ");
        getDenominatorFormat().format(bigFraction.getDenominator(), stringBuffer, fieldPosition);
        return stringBuffer;
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof BigFraction) {
            return format((BigFraction) obj, stringBuffer, fieldPosition);
        }
        if (obj instanceof BigInteger) {
            return format(new BigFraction((BigInteger) obj), stringBuffer, fieldPosition);
        }
        if (obj instanceof Number) {
            return format(new BigFraction(((Number) obj).doubleValue()), stringBuffer, fieldPosition);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_OBJECT_TO_FRACTION, new Object[0]);
    }

    public BigFraction parse(String str) throws MathParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        BigFraction parse = parse(str, parsePosition);
        if (parsePosition.getIndex() != 0) {
            return parse;
        }
        throw new MathParseException(str, parsePosition.getErrorIndex(), BigFraction.class);
    }

    public BigFraction parse(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        parseAndIgnoreWhitespace(str, parsePosition);
        BigInteger parseNextBigInteger = parseNextBigInteger(str, parsePosition);
        if (parseNextBigInteger == null) {
            parsePosition.setIndex(index);
            return null;
        }
        int index2 = parsePosition.getIndex();
        char parseNextCharacter = parseNextCharacter(str, parsePosition);
        if (parseNextCharacter == 0) {
            return new BigFraction(parseNextBigInteger);
        }
        if (parseNextCharacter != '/') {
            parsePosition.setIndex(index);
            parsePosition.setErrorIndex(index2);
            return null;
        }
        parseAndIgnoreWhitespace(str, parsePosition);
        BigInteger parseNextBigInteger2 = parseNextBigInteger(str, parsePosition);
        if (parseNextBigInteger2 != null) {
            return new BigFraction(parseNextBigInteger, parseNextBigInteger2);
        }
        parsePosition.setIndex(index);
        return null;
    }

    /* access modifiers changed from: protected */
    public BigInteger parseNextBigInteger(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        int i = str.charAt(index) == '-' ? index + 1 : index;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            i++;
        }
        try {
            BigInteger bigInteger = new BigInteger(str.substring(index, i));
            parsePosition.setIndex(i);
            return bigInteger;
        } catch (NumberFormatException unused) {
            parsePosition.setErrorIndex(index);
            return null;
        }
    }
}
