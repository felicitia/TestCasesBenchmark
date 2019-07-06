package org.apache.commons.math3.linear;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.math3.exception.MathParseException;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.commons.math3.util.CompositeFormat;

public class RealMatrixFormat {
    private static final String DEFAULT_COLUMN_SEPARATOR = ",";
    private static final String DEFAULT_PREFIX = "{";
    private static final String DEFAULT_ROW_PREFIX = "{";
    private static final String DEFAULT_ROW_SEPARATOR = ",";
    private static final String DEFAULT_ROW_SUFFIX = "}";
    private static final String DEFAULT_SUFFIX = "}";
    private final String columnSeparator;
    private final NumberFormat format;
    private final String prefix;
    private final String rowPrefix;
    private final String rowSeparator;
    private final String rowSuffix;
    private final String suffix;

    public RealMatrixFormat() {
        this(VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, ",", ",", CompositeFormat.getDefaultNumberFormat());
    }

    public RealMatrixFormat(NumberFormat numberFormat) {
        this(VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, VectorFormat.DEFAULT_PREFIX, VectorFormat.DEFAULT_SUFFIX, ",", ",", numberFormat);
    }

    public RealMatrixFormat(String str, String str2, String str3, String str4, String str5, String str6) {
        this(str, str2, str3, str4, str5, str6, CompositeFormat.getDefaultNumberFormat());
    }

    public RealMatrixFormat(String str, String str2, String str3, String str4, String str5, String str6, NumberFormat numberFormat) {
        this.prefix = str;
        this.suffix = str2;
        this.rowPrefix = str3;
        this.rowSuffix = str4;
        this.rowSeparator = str5;
        this.columnSeparator = str6;
        this.format = numberFormat;
        this.format.setGroupingUsed(false);
    }

    public static Locale[] getAvailableLocales() {
        return NumberFormat.getAvailableLocales();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public String getRowPrefix() {
        return this.rowPrefix;
    }

    public String getRowSuffix() {
        return this.rowSuffix;
    }

    public String getRowSeparator() {
        return this.rowSeparator;
    }

    public String getColumnSeparator() {
        return this.columnSeparator;
    }

    public NumberFormat getFormat() {
        return this.format;
    }

    public static RealMatrixFormat getInstance() {
        return getInstance(Locale.getDefault());
    }

    public static RealMatrixFormat getInstance(Locale locale) {
        return new RealMatrixFormat(CompositeFormat.getDefaultNumberFormat(locale));
    }

    public String format(RealMatrix realMatrix) {
        return format(realMatrix, new StringBuffer(), new FieldPosition(0)).toString();
    }

    public StringBuffer format(RealMatrix realMatrix, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        fieldPosition.setBeginIndex(0);
        fieldPosition.setEndIndex(0);
        stringBuffer.append(this.prefix);
        int rowDimension = realMatrix.getRowDimension();
        for (int i = 0; i < rowDimension; i++) {
            stringBuffer.append(this.rowPrefix);
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                if (i2 > 0) {
                    stringBuffer.append(this.columnSeparator);
                }
                CompositeFormat.formatDouble(realMatrix.getEntry(i, i2), this.format, stringBuffer, fieldPosition);
            }
            stringBuffer.append(this.rowSuffix);
            if (i < rowDimension - 1) {
                stringBuffer.append(this.rowSeparator);
            }
        }
        stringBuffer.append(this.suffix);
        return stringBuffer;
    }

    public RealMatrix parse(String str) {
        ParsePosition parsePosition = new ParsePosition(0);
        RealMatrix parse = parse(str, parsePosition);
        if (parsePosition.getIndex() != 0) {
            return parse;
        }
        throw new MathParseException(str, parsePosition.getErrorIndex(), Array2DRowRealMatrix.class);
    }

    public RealMatrix parse(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        String trim = this.prefix.trim();
        String trim2 = this.suffix.trim();
        String trim3 = this.rowPrefix.trim();
        String trim4 = this.rowSuffix.trim();
        String trim5 = this.columnSeparator.trim();
        String trim6 = this.rowSeparator.trim();
        CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
        if (!CompositeFormat.parseFixedstring(str, trim, parsePosition)) {
            return null;
        }
        ArrayList<List> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        boolean z = true;
        while (z) {
            if (!arrayList2.isEmpty()) {
                CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
                if (!CompositeFormat.parseFixedstring(str, trim5, parsePosition)) {
                    if (trim4.length() != 0 && !CompositeFormat.parseFixedstring(str, trim4, parsePosition)) {
                        return null;
                    }
                    CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
                    if (CompositeFormat.parseFixedstring(str, trim6, parsePosition)) {
                        arrayList.add(arrayList2);
                        arrayList2 = new ArrayList();
                    } else {
                        z = false;
                    }
                }
            } else {
                CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
                if (trim3.length() != 0 && !CompositeFormat.parseFixedstring(str, trim3, parsePosition)) {
                    return null;
                }
            }
            if (z) {
                CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
                Number parseNumber = CompositeFormat.parseNumber(str, this.format, parsePosition);
                if (parseNumber != null) {
                    arrayList2.add(parseNumber);
                } else if (arrayList2.isEmpty()) {
                    z = false;
                } else {
                    parsePosition.setIndex(index);
                    return null;
                }
            } else {
                continue;
            }
        }
        if (!arrayList2.isEmpty()) {
            arrayList.add(arrayList2);
        }
        CompositeFormat.parseAndIgnoreWhitespace(str, parsePosition);
        if (!CompositeFormat.parseFixedstring(str, trim2, parsePosition)) {
            return null;
        }
        if (arrayList.isEmpty()) {
            parsePosition.setIndex(index);
            return null;
        }
        double[][] dArr = new double[arrayList.size()][];
        int i = 0;
        for (List list : arrayList) {
            dArr[i] = new double[list.size()];
            for (int i2 = 0; i2 < list.size(); i2++) {
                dArr[i][i2] = ((Number) list.get(i2)).doubleValue();
            }
            i++;
        }
        return MatrixUtils.createRealMatrix(dArr);
    }
}
