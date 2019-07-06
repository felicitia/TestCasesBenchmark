package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class ParserBase extends ParserMinimalBase {
    static final BigDecimal BD_MAX_INT = new BigDecimal(BI_MAX_INT);
    static final BigDecimal BD_MAX_LONG = new BigDecimal(BI_MAX_LONG);
    static final BigDecimal BD_MIN_INT = new BigDecimal(BI_MIN_INT);
    static final BigDecimal BD_MIN_LONG = new BigDecimal(BI_MIN_LONG);
    static final BigInteger BI_MAX_INT = BigInteger.valueOf(MAX_INT_L);
    static final BigInteger BI_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    static final BigInteger BI_MIN_INT = BigInteger.valueOf(MIN_INT_L);
    static final BigInteger BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    protected static final char CHAR_NULL = '\u0000';
    protected static final int INT_0 = 48;
    protected static final int INT_1 = 49;
    protected static final int INT_2 = 50;
    protected static final int INT_3 = 51;
    protected static final int INT_4 = 52;
    protected static final int INT_5 = 53;
    protected static final int INT_6 = 54;
    protected static final int INT_7 = 55;
    protected static final int INT_8 = 56;
    protected static final int INT_9 = 57;
    protected static final int INT_DECIMAL_POINT = 46;
    protected static final int INT_E = 69;
    protected static final int INT_MINUS = 45;
    protected static final int INT_PLUS = 43;
    protected static final int INT_e = 101;
    static final double MAX_INT_D = 2.147483647E9d;
    static final long MAX_INT_L = 2147483647L;
    static final double MAX_LONG_D = 9.223372036854776E18d;
    static final double MIN_INT_D = -2.147483648E9d;
    static final long MIN_INT_L = -2147483648L;
    static final double MIN_LONG_D = -9.223372036854776E18d;
    protected static final int NR_BIGDECIMAL = 16;
    protected static final int NR_BIGINT = 4;
    protected static final int NR_DOUBLE = 8;
    protected static final int NR_INT = 1;
    protected static final int NR_LONG = 2;
    protected static final int NR_UNKNOWN = 0;
    protected byte[] _binaryValue;
    protected ByteArrayBuilder _byteArrayBuilder = null;
    protected boolean _closed;
    protected long _currInputProcessed = 0;
    protected int _currInputRow = 1;
    protected int _currInputRowStart = 0;
    protected int _expLength;
    protected int _fractLength;
    protected int _inputEnd = 0;
    protected int _inputPtr = 0;
    protected int _intLength;
    protected final IOContext _ioContext;
    protected boolean _nameCopied = false;
    protected char[] _nameCopyBuffer = null;
    protected JsonToken _nextToken;
    protected int _numTypesValid = 0;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected int _numberInt;
    protected long _numberLong;
    protected boolean _numberNegative;
    protected JsonReadContext _parsingContext;
    protected final TextBuffer _textBuffer;
    protected int _tokenInputCol = 0;
    protected int _tokenInputRow = 1;
    protected long _tokenInputTotal = 0;

    /* access modifiers changed from: protected */
    public abstract void _closeInput() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void _finishString() throws IOException, JsonParseException;

    public Object getEmbeddedObject() throws IOException, JsonParseException {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract boolean loadMore() throws IOException;

    protected ParserBase(IOContext iOContext, int i) {
        this._features = i;
        this._ioContext = iOContext;
        this._textBuffer = iOContext.constructTextBuffer();
        this._parsingContext = JsonReadContext.createRootContext();
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public String getCurrentName() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            return this._parsingContext.getParent().getCurrentName();
        }
        return this._parsingContext.getCurrentName();
    }

    public void overrideCurrentName(String str) {
        JsonReadContext jsonReadContext = this._parsingContext;
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            jsonReadContext = jsonReadContext.getParent();
        }
        jsonReadContext.setCurrentName(str);
    }

    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            try {
                _closeInput();
            } finally {
                _releaseBuffers();
            }
        }
    }

    public boolean isClosed() {
        return this._closed;
    }

    public JsonReadContext getParsingContext() {
        return this._parsingContext;
    }

    public JsonLocation getTokenLocation() {
        JsonLocation jsonLocation = new JsonLocation(this._ioContext.getSourceReference(), getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
        return jsonLocation;
    }

    public JsonLocation getCurrentLocation() {
        int i = (this._inputPtr - this._currInputRowStart) + 1;
        JsonLocation jsonLocation = new JsonLocation(this._ioContext.getSourceReference(), (this._currInputProcessed + ((long) this._inputPtr)) - 1, this._currInputRow, i);
        return jsonLocation;
    }

    public boolean hasTextCharacters() {
        if (this._currToken == JsonToken.VALUE_STRING) {
            return true;
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nameCopied;
        }
        return false;
    }

    public long getTokenCharacterOffset() {
        return this._tokenInputTotal;
    }

    public int getTokenLineNr() {
        return this._tokenInputRow;
    }

    public int getTokenColumnNr() {
        int i = this._tokenInputCol;
        return i < 0 ? i : i + 1;
    }

    /* access modifiers changed from: protected */
    public final void loadMoreGuaranteed() throws IOException {
        if (!loadMore()) {
            _reportInvalidEOF();
        }
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() throws IOException {
        this._textBuffer.releaseBuffers();
        char[] cArr = this._nameCopyBuffer;
        if (cArr != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(cArr);
        }
    }

    /* access modifiers changed from: protected */
    public void _handleEOF() throws JsonParseException {
        if (!this._parsingContext.inRoot()) {
            StringBuilder sb = new StringBuilder();
            sb.append(": expected close marker for ");
            sb.append(this._parsingContext.getTypeDesc());
            sb.append(" (from ");
            sb.append(this._parsingContext.getStartLocation(this._ioContext.getSourceReference()));
            sb.append(")");
            _reportInvalidEOF(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void _reportMismatchedEndMarker(int i, char c) throws JsonParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this._parsingContext.getStartLocation(this._ioContext.getSourceReference()));
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Unexpected close marker '");
        sb3.append((char) i);
        sb3.append("': expected '");
        sb3.append(c);
        sb3.append("' (for ");
        sb3.append(this._parsingContext.getTypeDesc());
        sb3.append(" starting at ");
        sb3.append(sb2);
        sb3.append(")");
        _reportError(sb3.toString());
    }

    public ByteArrayBuilder _getByteArrayBuilder() {
        if (this._byteArrayBuilder == null) {
            this._byteArrayBuilder = new ByteArrayBuilder();
        } else {
            this._byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }

    /* access modifiers changed from: protected */
    public final JsonToken reset(boolean z, int i, int i2, int i3) {
        if (i2 >= 1 || i3 >= 1) {
            return resetFloat(z, i, i2, i3);
        }
        return resetInt(z, i);
    }

    /* access modifiers changed from: protected */
    public final JsonToken resetInt(boolean z, int i) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = 0;
        this._expLength = 0;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }

    /* access modifiers changed from: protected */
    public final JsonToken resetFloat(boolean z, int i, int i2, int i3) {
        this._numberNegative = z;
        this._intLength = i;
        this._fractLength = i2;
        this._expLength = i3;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    /* access modifiers changed from: protected */
    public final JsonToken resetAsNaN(String str, double d) {
        this._textBuffer.resetWithString(str);
        this._numberDouble = d;
        this._numTypesValid = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public Number getNumberValue() throws IOException, JsonParseException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if ((this._numTypesValid & 1) != 0) {
                return Integer.valueOf(this._numberInt);
            }
            if ((this._numTypesValid & 2) != 0) {
                return Long.valueOf(this._numberLong);
            }
            if ((this._numTypesValid & 4) != 0) {
                return this._numberBigInt;
            }
            return this._numberBigDecimal;
        } else if ((this._numTypesValid & 16) != 0) {
            return this._numberBigDecimal;
        } else {
            if ((this._numTypesValid & 8) == 0) {
                _throwInternal();
            }
            return Double.valueOf(this._numberDouble);
        }
    }

    public NumberType getNumberType() throws IOException, JsonParseException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if ((this._numTypesValid & 1) != 0) {
                return NumberType.INT;
            }
            if ((this._numTypesValid & 2) != 0) {
                return NumberType.LONG;
            }
            return NumberType.BIG_INTEGER;
        } else if ((this._numTypesValid & 16) != 0) {
            return NumberType.BIG_DECIMAL;
        } else {
            return NumberType.DOUBLE;
        }
    }

    public int getIntValue() throws IOException, JsonParseException {
        if ((this._numTypesValid & 1) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(1);
            }
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    public long getLongValue() throws IOException, JsonParseException {
        if ((this._numTypesValid & 2) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        if ((this._numTypesValid & 4) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }

    public float getFloatValue() throws IOException, JsonParseException {
        return (float) getDoubleValue();
    }

    public double getDoubleValue() throws IOException, JsonParseException {
        if ((this._numTypesValid & 8) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(8);
            }
            if ((this._numTypesValid & 8) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }

    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        if ((this._numTypesValid & 16) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(16);
            }
            if ((this._numTypesValid & 16) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }

    /* access modifiers changed from: protected */
    public void _parseNumericValue(int i) throws IOException, JsonParseException {
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            char[] textBuffer = this._textBuffer.getTextBuffer();
            int textOffset = this._textBuffer.getTextOffset();
            int i2 = this._intLength;
            if (this._numberNegative) {
                textOffset++;
            }
            if (i2 <= 9) {
                int parseInt = NumberInput.parseInt(textBuffer, textOffset, i2);
                if (this._numberNegative) {
                    parseInt = -parseInt;
                }
                this._numberInt = parseInt;
                this._numTypesValid = 1;
            } else if (i2 <= 18) {
                long parseLong = NumberInput.parseLong(textBuffer, textOffset, i2);
                if (this._numberNegative) {
                    parseLong = -parseLong;
                }
                if (i2 == 10) {
                    if (this._numberNegative) {
                        if (parseLong >= MIN_INT_L) {
                            this._numberInt = (int) parseLong;
                            this._numTypesValid = 1;
                            return;
                        }
                    } else if (parseLong <= MAX_INT_L) {
                        this._numberInt = (int) parseLong;
                        this._numTypesValid = 1;
                        return;
                    }
                }
                this._numberLong = parseLong;
                this._numTypesValid = 2;
            } else {
                _parseSlowIntValue(i, textBuffer, textOffset, i2);
            }
        } else if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            _parseSlowFloatValue(i);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Current token (");
            sb.append(this._currToken);
            sb.append(") not numeric, can not use numeric value accessors");
            _reportError(sb.toString());
        }
    }

    private void _parseSlowFloatValue(int i) throws IOException, JsonParseException {
        if (i == 16) {
            try {
                this._numberBigDecimal = this._textBuffer.contentsAsDecimal();
                this._numTypesValid = 16;
            } catch (NumberFormatException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Malformed numeric value '");
                sb.append(this._textBuffer.contentsAsString());
                sb.append("'");
                _wrapError(sb.toString(), e);
            }
        } else {
            this._numberDouble = this._textBuffer.contentsAsDouble();
            this._numTypesValid = 8;
        }
    }

    private void _parseSlowIntValue(int i, char[] cArr, int i2, int i3) throws IOException, JsonParseException {
        String contentsAsString = this._textBuffer.contentsAsString();
        try {
            if (NumberInput.inLongRange(cArr, i2, i3, this._numberNegative)) {
                this._numberLong = Long.parseLong(contentsAsString);
                this._numTypesValid = 2;
                return;
            }
            this._numberBigInt = new BigInteger(contentsAsString);
            this._numTypesValid = 4;
        } catch (NumberFormatException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Malformed numeric value '");
            sb.append(contentsAsString);
            sb.append("'");
            _wrapError(sb.toString(), e);
        }
    }

    /* access modifiers changed from: protected */
    public void convertNumberToInt() throws IOException, JsonParseException {
        if ((this._numTypesValid & 2) != 0) {
            int i = (int) this._numberLong;
            if (((long) i) != this._numberLong) {
                StringBuilder sb = new StringBuilder();
                sb.append("Numeric value (");
                sb.append(getText());
                sb.append(") out of range of int");
                _reportError(sb.toString());
            }
            this._numberInt = i;
        } else if ((this._numTypesValid & 4) != 0) {
            if (BI_MIN_INT.compareTo(this._numberBigInt) > 0 || BI_MAX_INT.compareTo(this._numberBigInt) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigInt.intValue();
        } else if ((this._numTypesValid & 8) != 0) {
            if (this._numberDouble < MIN_INT_D || this._numberDouble > MAX_INT_D) {
                reportOverflowInt();
            }
            this._numberInt = (int) this._numberDouble;
        } else if ((this._numTypesValid & 16) != 0) {
            if (BD_MIN_INT.compareTo(this._numberBigDecimal) > 0 || BD_MAX_INT.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigDecimal.intValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 1;
    }

    /* access modifiers changed from: protected */
    public void convertNumberToLong() throws IOException, JsonParseException {
        if ((this._numTypesValid & 1) != 0) {
            this._numberLong = (long) this._numberInt;
        } else if ((this._numTypesValid & 4) != 0) {
            if (BI_MIN_LONG.compareTo(this._numberBigInt) > 0 || BI_MAX_LONG.compareTo(this._numberBigInt) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigInt.longValue();
        } else if ((this._numTypesValid & 8) != 0) {
            if (this._numberDouble < MIN_LONG_D || this._numberDouble > MAX_LONG_D) {
                reportOverflowLong();
            }
            this._numberLong = (long) this._numberDouble;
        } else if ((this._numTypesValid & 16) != 0) {
            if (BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0 || BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigDecimal.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 2;
    }

    /* access modifiers changed from: protected */
    public void convertNumberToBigInteger() throws IOException, JsonParseException {
        if ((this._numTypesValid & 16) != 0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf((long) this._numberInt);
        } else if ((this._numTypesValid & 8) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 4;
    }

    /* access modifiers changed from: protected */
    public void convertNumberToDouble() throws IOException, JsonParseException {
        if ((this._numTypesValid & 16) != 0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberDouble = (double) this._numberLong;
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberDouble = (double) this._numberInt;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 8;
    }

    /* access modifiers changed from: protected */
    public void convertNumberToBigDecimal() throws IOException, JsonParseException {
        if ((this._numTypesValid & 8) != 0) {
            this._numberBigDecimal = new BigDecimal(getText());
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberBigDecimal = new BigDecimal(this._numberBigInt);
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf((long) this._numberInt);
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 16;
    }

    /* access modifiers changed from: protected */
    public void reportUnexpectedNumberChar(int i, String str) throws JsonParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected character (");
        sb.append(_getCharDesc(i));
        sb.append(") in numeric value");
        String sb2 = sb.toString();
        if (str != null) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(": ");
            sb3.append(str);
            sb2 = sb3.toString();
        }
        _reportError(sb2);
    }

    /* access modifiers changed from: protected */
    public void reportInvalidNumber(String str) throws JsonParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid numeric value: ");
        sb.append(str);
        _reportError(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void reportOverflowInt() throws IOException, JsonParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("Numeric value (");
        sb.append(getText());
        sb.append(") out of range of int (");
        sb.append(Integer.MIN_VALUE);
        sb.append(" - ");
        sb.append(Integer.MAX_VALUE);
        sb.append(")");
        _reportError(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void reportOverflowLong() throws IOException, JsonParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("Numeric value (");
        sb.append(getText());
        sb.append(") out of range of long (");
        sb.append(Long.MIN_VALUE);
        sb.append(" - ");
        sb.append(Long.MAX_VALUE);
        sb.append(")");
        _reportError(sb.toString());
    }

    /* access modifiers changed from: protected */
    public char _decodeEscaped() throws IOException, JsonParseException {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public final int _decodeBase64Escape(Base64Variant base64Variant, int i, int i2) throws IOException, JsonParseException {
        if (i != 92) {
            throw reportInvalidBase64Char(base64Variant, i, i2);
        }
        char _decodeEscaped = _decodeEscaped();
        if (_decodeEscaped <= ' ' && i2 == 0) {
            return -1;
        }
        int decodeBase64Char = base64Variant.decodeBase64Char((int) _decodeEscaped);
        if (decodeBase64Char >= 0) {
            return decodeBase64Char;
        }
        throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i2);
    }

    /* access modifiers changed from: protected */
    public final int _decodeBase64Escape(Base64Variant base64Variant, char c, int i) throws IOException, JsonParseException {
        if (c != '\\') {
            throw reportInvalidBase64Char(base64Variant, c, i);
        }
        char _decodeEscaped = _decodeEscaped();
        if (_decodeEscaped <= ' ' && i == 0) {
            return -1;
        }
        int decodeBase64Char = base64Variant.decodeBase64Char(_decodeEscaped);
        if (decodeBase64Char >= 0) {
            return decodeBase64Char;
        }
        throw reportInvalidBase64Char(base64Variant, _decodeEscaped, i);
    }

    /* access modifiers changed from: protected */
    public IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i, int i2) throws IllegalArgumentException {
        return reportInvalidBase64Char(base64Variant, i, i2, null);
    }

    /* access modifiers changed from: protected */
    public IllegalArgumentException reportInvalidBase64Char(Base64Variant base64Variant, int i, int i2, String str) throws IllegalArgumentException {
        String str2;
        if (i <= 32) {
            StringBuilder sb = new StringBuilder();
            sb.append("Illegal white space character (code 0x");
            sb.append(Integer.toHexString(i));
            sb.append(") as character #");
            sb.append(i2 + 1);
            sb.append(" of 4-char base64 unit: can only used between units");
            str2 = sb.toString();
        } else if (base64Variant.usesPaddingChar(i)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unexpected padding character ('");
            sb2.append(base64Variant.getPaddingChar());
            sb2.append("') as character #");
            sb2.append(i2 + 1);
            sb2.append(" of 4-char base64 unit: padding only legal as 3rd or 4th character");
            str2 = sb2.toString();
        } else if (!Character.isDefined(i) || Character.isISOControl(i)) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Illegal character (code 0x");
            sb3.append(Integer.toHexString(i));
            sb3.append(") in base64 content");
            str2 = sb3.toString();
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Illegal character '");
            sb4.append((char) i);
            sb4.append("' (code 0x");
            sb4.append(Integer.toHexString(i));
            sb4.append(") in base64 content");
            str2 = sb4.toString();
        }
        if (str != null) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append(str2);
            sb5.append(": ");
            sb5.append(str);
            str2 = sb5.toString();
        }
        return new IllegalArgumentException(str2);
    }
}
