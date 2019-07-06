package org.apache.commons.math3.geometry.euclidean.oned;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.commons.math3.util.CompositeFormat;

public class Vector1DFormat extends VectorFormat<Euclidean1D> {
    public Vector1DFormat() {
        super(VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, VectorFormat.DEFAULT_SEPARATOR, CompositeFormat.getDefaultNumberFormat());
    }

    public Vector1DFormat(NumberFormat numberFormat) {
        super(VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, VectorFormat.DEFAULT_SEPARATOR, numberFormat);
    }

    public Vector1DFormat(String str, String str2) {
        super(str, str2, VectorFormat.DEFAULT_SEPARATOR, CompositeFormat.getDefaultNumberFormat());
    }

    public Vector1DFormat(String str, String str2, NumberFormat numberFormat) {
        super(str, str2, VectorFormat.DEFAULT_SEPARATOR, numberFormat);
    }

    public static Vector1DFormat getInstance() {
        return getInstance(Locale.getDefault());
    }

    public static Vector1DFormat getInstance(Locale locale) {
        return new Vector1DFormat(CompositeFormat.getDefaultNumberFormat(locale));
    }

    public StringBuffer format(Vector<Euclidean1D> vector, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        return format(stringBuffer, fieldPosition, ((Vector1D) vector).getX());
    }

    public Vector1D parse(String str) throws MathParseException {
        ParsePosition parsePosition = new ParsePosition(0);
        Vector1D parse = parse(str, parsePosition);
        if (parsePosition.getIndex() != 0) {
            return parse;
        }
        throw new MathParseException(str, parsePosition.getErrorIndex(), Vector1D.class);
    }

    public Vector1D parse(String str, ParsePosition parsePosition) {
        double[] parseCoordinates = parseCoordinates(1, str, parsePosition);
        if (parseCoordinates == null) {
            return null;
        }
        return new Vector1D(parseCoordinates[0]);
    }
}
