package org.apache.commons.math3.fraction;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class FractionFormat extends AbstractFormat {
    private static final long serialVersionUID = 3008655719530972611L;

    public FractionFormat() {
    }

    public FractionFormat(NumberFormat numberFormat) {
        super(numberFormat);
    }

    public FractionFormat(NumberFormat numberFormat, NumberFormat numberFormat2) {
        super(numberFormat, numberFormat2);
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public static String formatFraction(Fraction fraction) {
        return getImproperInstance().format(fraction);
    }

    public static FractionFormat getImproperInstance() {
        return getImproperInstance(Locale.getDefault());
    }

    public static FractionFormat getImproperInstance(Locale locale) {
        return new FractionFormat(getDefaultNumberFormat(locale));
    }

    public static FractionFormat getProperInstance() {
        return getProperInstance(Locale.getDefault());
    }

    public static FractionFormat getProperInstance(Locale locale) {
        return new ProperFractionFormat(getDefaultNumberFormat(locale));
    }

    protected static NumberFormat getDefaultNumberFormat() {
        return getDefaultNumberFormat(Locale.getDefault());
    }

    public StringBuffer format(Fraction fraction, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        getNumeratorFormat().format((long) fraction.getNumerator(), stringBuffer, fieldPosition);
        stringBuffer.append(" / ");
        getDenominatorFormat().format((long) fraction.getDenominator(), stringBuffer, fieldPosition);
        return stringBuffer;
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) throws FractionConversionException, MathIllegalArgumentException {
        if (obj instanceof Fraction) {
            return format((Fraction) obj, stringBuffer, fieldPosition);
        }
        if (obj instanceof Number) {
            return format(new Fraction(((Number) obj).doubleValue()), stringBuffer, fieldPosition);
        }
        throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_FORMAT_OBJECT_TO_FRACTION, new Object[0]);
    }

    public Fraction parse(String str) throws MathParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        Fraction parse = parse(str, parsePosition);
        if (parsePosition.getIndex() != 0) {
            return parse;
        }
        throw new MathParseException(str, parsePosition.getErrorIndex(), Fraction.class);
    }

    public Fraction parse(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        parseAndIgnoreWhitespace(str, parsePosition);
        Number parse = getNumeratorFormat().parse(str, parsePosition);
        if (parse == null) {
            parsePosition.setIndex(index);
            return null;
        }
        int index2 = parsePosition.getIndex();
        char parseNextCharacter = parseNextCharacter(str, parsePosition);
        if (parseNextCharacter == 0) {
            return new Fraction(parse.intValue(), 1);
        }
        if (parseNextCharacter != '/') {
            parsePosition.setIndex(index);
            parsePosition.setErrorIndex(index2);
            return null;
        }
        parseAndIgnoreWhitespace(str, parsePosition);
        Number parse2 = getDenominatorFormat().parse(str, parsePosition);
        if (parse2 != null) {
            return new Fraction(parse.intValue(), parse2.intValue());
        }
        parsePosition.setIndex(index);
        return null;
    }
}
