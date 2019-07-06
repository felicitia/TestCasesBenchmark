package com.fasterxml.jackson.core.json;

import android.support.v4.internal.view.SupportMenu;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class UTF8JsonGenerator extends JsonGeneratorImpl {
    private static final byte BYTE_0 = 48;
    private static final byte BYTE_BACKSLASH = 92;
    private static final byte BYTE_COLON = 58;
    private static final byte BYTE_COMMA = 44;
    private static final byte BYTE_LBRACKET = 91;
    private static final byte BYTE_LCURLY = 123;
    private static final byte BYTE_QUOTE = 34;
    private static final byte BYTE_RBRACKET = 93;
    private static final byte BYTE_RCURLY = 125;
    private static final byte BYTE_u = 117;
    private static final byte[] FALSE_BYTES = {102, 97, 108, 115, 101};
    static final byte[] HEX_CHARS = CharTypes.copyHexBytes();
    private static final int MAX_BYTES_TO_BUFFER = 512;
    private static final byte[] NULL_BYTES = {110, BYTE_u, 108, 108};
    protected static final int SURR1_FIRST = 55296;
    protected static final int SURR1_LAST = 56319;
    protected static final int SURR2_FIRST = 56320;
    protected static final int SURR2_LAST = 57343;
    private static final byte[] TRUE_BYTES = {116, 114, BYTE_u, 101};
    protected boolean _bufferRecyclable;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected byte[] _entityBuffer;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected final int _outputMaxContiguous;
    protected final OutputStream _outputStream;
    protected int _outputTail = 0;

    public UTF8JsonGenerator(IOContext iOContext, int i, ObjectCodec objectCodec, OutputStream outputStream) {
        super(iOContext, i, objectCodec);
        this._outputStream = outputStream;
        this._bufferRecyclable = true;
        this._outputBuffer = iOContext.allocWriteEncodingBuffer();
        this._outputEnd = this._outputBuffer.length;
        this._outputMaxContiguous = this._outputEnd >> 3;
        this._charBuffer = iOContext.allocConcatBuffer();
        this._charBufferLength = this._charBuffer.length;
        if (isEnabled(Feature.ESCAPE_NON_ASCII)) {
            setHighestNonEscapedChar(127);
        }
    }

    public UTF8JsonGenerator(IOContext iOContext, int i, ObjectCodec objectCodec, OutputStream outputStream, byte[] bArr, int i2, boolean z) {
        super(iOContext, i, objectCodec);
        this._outputStream = outputStream;
        this._bufferRecyclable = z;
        this._outputTail = i2;
        this._outputBuffer = bArr;
        this._outputEnd = this._outputBuffer.length;
        this._outputMaxContiguous = this._outputEnd >> 3;
        this._charBuffer = iOContext.allocConcatBuffer();
        this._charBufferLength = this._charBuffer.length;
    }

    public Object getOutputTarget() {
        return this._outputStream;
    }

    public final void writeFieldName(String str) throws IOException, JsonGenerationException {
        int writeFieldName = this._writeContext.writeFieldName(str);
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        boolean z = true;
        if (this._cfgPrettyPrinter != null) {
            if (writeFieldName != 1) {
                z = false;
            }
            _writePPFieldName(str, z);
            return;
        }
        if (writeFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = BYTE_COMMA;
        }
        _writeFieldName(str);
    }

    public final void writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        int writeFieldName = this._writeContext.writeFieldName(serializableString.getValue());
        if (writeFieldName == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        boolean z = true;
        if (this._cfgPrettyPrinter != null) {
            if (writeFieldName != 1) {
                z = false;
            }
            _writePPFieldName(serializableString, z);
            return;
        }
        if (writeFieldName == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = BYTE_COMMA;
        }
        _writeFieldName(serializableString);
    }

    public final void writeStartArray() throws IOException, JsonGenerationException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = BYTE_LBRACKET;
    }

    public final void writeEndArray() throws IOException, JsonGenerationException {
        if (!this._writeContext.inArray()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Current context not an ARRAY but ");
            sb.append(this._writeContext.getTypeDesc());
            _reportError(sb.toString());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = BYTE_RBRACKET;
        }
        this._writeContext = this._writeContext.getParent();
    }

    public final void writeStartObject() throws IOException, JsonGenerationException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = BYTE_LCURLY;
    }

    public final void writeEndObject() throws IOException, JsonGenerationException {
        if (!this._writeContext.inObject()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Current context not an object but ");
            sb.append(this._writeContext.getTypeDesc());
            _reportError(sb.toString());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = BYTE_RCURLY;
        }
        this._writeContext = this._writeContext.getParent();
    }

    /* access modifiers changed from: protected */
    public final void _writeFieldName(String str) throws IOException, JsonGenerationException {
        if (!isEnabled(Feature.QUOTE_FIELD_NAMES)) {
            _writeStringSegments(str);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = BYTE_QUOTE;
        int length = str.length();
        if (length <= this._charBufferLength) {
            str.getChars(0, length, this._charBuffer, 0);
            if (length <= this._outputMaxContiguous) {
                if (this._outputTail + length > this._outputEnd) {
                    _flushBuffer();
                }
                _writeStringSegment(this._charBuffer, 0, length);
            } else {
                _writeStringSegments(this._charBuffer, 0, length);
            }
        } else {
            _writeStringSegments(str);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = BYTE_QUOTE;
    }

    /* access modifiers changed from: protected */
    public final void _writeFieldName(SerializableString serializableString) throws IOException, JsonGenerationException {
        if (!isEnabled(Feature.QUOTE_FIELD_NAMES)) {
            int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
            if (appendQuotedUTF8 < 0) {
                _writeBytes(serializableString.asQuotedUTF8());
            } else {
                this._outputTail += appendQuotedUTF8;
            }
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = BYTE_QUOTE;
        int appendQuotedUTF82 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF82 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF82;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = BYTE_QUOTE;
    }

    /* access modifiers changed from: protected */
    public final void _writePPFieldName(String str, boolean z) throws IOException, JsonGenerationException {
        if (z) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (isEnabled(Feature.QUOTE_FIELD_NAMES)) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = BYTE_QUOTE;
            int length = str.length();
            if (length <= this._charBufferLength) {
                str.getChars(0, length, this._charBuffer, 0);
                if (length <= this._outputMaxContiguous) {
                    if (this._outputTail + length > this._outputEnd) {
                        _flushBuffer();
                    }
                    _writeStringSegment(this._charBuffer, 0, length);
                } else {
                    _writeStringSegments(this._charBuffer, 0, length);
                }
            } else {
                _writeStringSegments(str);
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = BYTE_QUOTE;
            return;
        }
        _writeStringSegments(str);
    }

    /* access modifiers changed from: protected */
    public final void _writePPFieldName(SerializableString serializableString, boolean z) throws IOException, JsonGenerationException {
        if (z) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        boolean isEnabled = isEnabled(Feature.QUOTE_FIELD_NAMES);
        if (isEnabled) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = BYTE_QUOTE;
        }
        _writeBytes(serializableString.asQuotedUTF8());
        if (isEnabled) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = BYTE_QUOTE;
        }
    }

    public void writeString(String str) throws IOException, JsonGenerationException {
        _verifyValueWrite("write text value");
        if (str == null) {
            _writeNull();
            return;
        }
        int length = str.length();
        if (length > this._charBufferLength) {
            _writeLongString(str);
            return;
        }
        str.getChars(0, length, this._charBuffer, 0);
        if (length > this._outputMaxContiguous) {
            _writeLongString(this._charBuffer, 0, length);
            return;
        }
        if (this._outputTail + length >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = BYTE_QUOTE;
        _writeStringSegment(this._charBuffer, 0, length);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = BYTE_QUOTE;
    }

    private void _writeLongString(String str) throws IOException, JsonGenerationException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = BYTE_QUOTE;
        _writeStringSegments(str);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = BYTE_QUOTE;
    }

    private void _writeLongString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = BYTE_QUOTE;
        _writeStringSegments(this._charBuffer, 0, i2);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr2[i4] = BYTE_QUOTE;
    }

    public void writeString(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = BYTE_QUOTE;
        if (i2 <= this._outputMaxContiguous) {
            if (this._outputTail + i2 > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, i, i2);
        } else {
            _writeStringSegments(cArr, i, i2);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr2[i4] = BYTE_QUOTE;
    }

    public final void writeString(SerializableString serializableString) throws IOException, JsonGenerationException {
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = BYTE_QUOTE;
        int appendQuotedUTF8 = serializableString.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (appendQuotedUTF8 < 0) {
            _writeBytes(serializableString.asQuotedUTF8());
        } else {
            this._outputTail += appendQuotedUTF8;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = BYTE_QUOTE;
    }

    public void writeRawUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = BYTE_QUOTE;
        _writeBytes(bArr, i, i2);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr3[i4] = BYTE_QUOTE;
    }

    public void writeUTF8String(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        _verifyValueWrite("write text value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = BYTE_QUOTE;
        if (i2 <= this._outputMaxContiguous) {
            _writeUTF8Segment(bArr, i, i2);
        } else {
            _writeUTF8Segments(bArr, i, i2);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr3[i4] = BYTE_QUOTE;
    }

    public void writeRaw(String str) throws IOException, JsonGenerationException {
        int length = str.length();
        int i = 0;
        while (length > 0) {
            char[] cArr = this._charBuffer;
            int length2 = cArr.length;
            if (length < length2) {
                length2 = length;
            }
            int i2 = i + length2;
            str.getChars(i, i2, cArr, 0);
            writeRaw(cArr, 0, length2);
            length -= length2;
            i = i2;
        }
    }

    public void writeRaw(String str, int i, int i2) throws IOException, JsonGenerationException {
        while (i2 > 0) {
            char[] cArr = this._charBuffer;
            int length = cArr.length;
            if (i2 < length) {
                length = i2;
            }
            int i3 = i + length;
            str.getChars(i, i3, cArr, 0);
            writeRaw(cArr, 0, length);
            i2 -= length;
            i = i3;
        }
    }

    public void writeRaw(SerializableString serializableString) throws IOException, JsonGenerationException {
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        if (asUnquotedUTF8.length > 0) {
            _writeBytes(asUnquotedUTF8);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        r0 = r7 + 1;
        r7 = r6[r7];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        if (r7 >= 2048) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        r1 = r5._outputBuffer;
        r2 = r5._outputTail;
        r5._outputTail = r2 + 1;
        r1[r2] = (byte) (192 | (r7 >> 6));
        r1 = r5._outputBuffer;
        r2 = r5._outputTail;
        r5._outputTail = r2 + 1;
        r1[r2] = (byte) ((r7 & '?') | 128);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        _outputRawMultiByteChar(r7, r6, r0, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004a, code lost:
        r7 = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writeRaw(char[] r6, int r7, int r8) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r5 = this;
            int r0 = r8 + r8
            int r0 = r0 + r8
            int r1 = r5._outputTail
            int r1 = r1 + r0
            int r2 = r5._outputEnd
            if (r1 <= r2) goto L_0x0015
            int r1 = r5._outputEnd
            if (r1 >= r0) goto L_0x0012
            r5._writeSegmentedRaw(r6, r7, r8)
            return
        L_0x0012:
            r5._flushBuffer()
        L_0x0015:
            int r8 = r8 + r7
        L_0x0016:
            if (r7 >= r8) goto L_0x005b
        L_0x0018:
            char r0 = r6[r7]
            r1 = 127(0x7f, float:1.78E-43)
            if (r0 <= r1) goto L_0x004c
            int r0 = r7 + 1
            char r7 = r6[r7]
            r1 = 2048(0x800, float:2.87E-42)
            if (r7 >= r1) goto L_0x0047
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            r3 = 192(0xc0, float:2.69E-43)
            int r4 = r7 >> 6
            r3 = r3 | r4
            byte r3 = (byte) r3
            r1[r2] = r3
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            r3 = 128(0x80, float:1.794E-43)
            r7 = r7 & 63
            r7 = r7 | r3
            byte r7 = (byte) r7
            r1[r2] = r7
            goto L_0x004a
        L_0x0047:
            r5._outputRawMultiByteChar(r7, r6, r0, r8)
        L_0x004a:
            r7 = r0
            goto L_0x0016
        L_0x004c:
            byte[] r1 = r5._outputBuffer
            int r2 = r5._outputTail
            int r3 = r2 + 1
            r5._outputTail = r3
            byte r0 = (byte) r0
            r1[r2] = r0
            int r7 = r7 + 1
            if (r7 < r8) goto L_0x0018
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator.writeRaw(char[], int, int):void");
    }

    public void writeRaw(char c) throws IOException, JsonGenerationException {
        if (this._outputTail + 3 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        if (c <= 127) {
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = (byte) c;
        } else if (c < 2048) {
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr[i2] = (byte) (192 | (c >> 6));
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bArr[i3] = (byte) ((c & '?') | 128);
        } else {
            _outputRawMultiByteChar(c, null, 0, 0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003a, code lost:
        _outputRawMultiByteChar(r9, r8, r2, r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003d, code lost:
        r9 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0012, code lost:
        if ((r7._outputTail + 3) < r7._outputEnd) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        _flushBuffer();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        r2 = r9 + 1;
        r9 = r8[r9];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        if (r9 >= 2048) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r4 = r7._outputTail;
        r7._outputTail = r4 + 1;
        r1[r4] = (byte) (192 | (r9 >> 6));
        r4 = r7._outputTail;
        r7._outputTail = r4 + 1;
        r1[r4] = (byte) ((r9 & '?') | 128);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _writeSegmentedRaw(char[] r8, int r9, int r10) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r7 = this;
            int r0 = r7._outputEnd
            byte[] r1 = r7._outputBuffer
        L_0x0004:
            if (r9 >= r10) goto L_0x0053
        L_0x0006:
            char r2 = r8[r9]
            r3 = 128(0x80, float:1.794E-43)
            if (r2 < r3) goto L_0x003f
            int r2 = r7._outputTail
            int r2 = r2 + 3
            int r4 = r7._outputEnd
            if (r2 < r4) goto L_0x0017
            r7._flushBuffer()
        L_0x0017:
            int r2 = r9 + 1
            char r9 = r8[r9]
            r4 = 2048(0x800, float:2.87E-42)
            if (r9 >= r4) goto L_0x003a
            int r4 = r7._outputTail
            int r5 = r4 + 1
            r7._outputTail = r5
            r5 = 192(0xc0, float:2.69E-43)
            int r6 = r9 >> 6
            r5 = r5 | r6
            byte r5 = (byte) r5
            r1[r4] = r5
            int r4 = r7._outputTail
            int r5 = r4 + 1
            r7._outputTail = r5
            r9 = r9 & 63
            r9 = r9 | r3
            byte r9 = (byte) r9
            r1[r4] = r9
            goto L_0x003d
        L_0x003a:
            r7._outputRawMultiByteChar(r9, r8, r2, r10)
        L_0x003d:
            r9 = r2
            goto L_0x0004
        L_0x003f:
            int r3 = r7._outputTail
            if (r3 < r0) goto L_0x0046
            r7._flushBuffer()
        L_0x0046:
            int r3 = r7._outputTail
            int r4 = r3 + 1
            r7._outputTail = r4
            byte r2 = (byte) r2
            r1[r3] = r2
            int r9 = r9 + 1
            if (r9 < r10) goto L_0x0006
        L_0x0053:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator._writeSegmentedRaw(char[], int, int):void");
    }

    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        _verifyValueWrite("write binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = BYTE_QUOTE;
        _writeBinary(base64Variant, bArr, i, i2 + i);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr3[i4] = BYTE_QUOTE;
    }

    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) throws IOException, JsonGenerationException {
        _verifyValueWrite("write binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = BYTE_QUOTE;
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        if (i < 0) {
            try {
                i = _writeBinary(base64Variant, inputStream, allocBase64Buffer);
            } catch (Throwable th) {
                this._ioContext.releaseBase64Buffer(allocBase64Buffer);
                throw th;
            }
        } else {
            int _writeBinary = _writeBinary(base64Variant, inputStream, allocBase64Buffer, i);
            if (_writeBinary > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Too few bytes available: missing ");
                sb.append(_writeBinary);
                sb.append(" bytes (out of ");
                sb.append(i);
                sb.append(")");
                _reportError(sb.toString());
            }
        }
        this._ioContext.releaseBase64Buffer(allocBase64Buffer);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = BYTE_QUOTE;
        return i;
    }

    public void writeNumber(short s) throws IOException, JsonGenerationException {
        _verifyValueWrite("write number");
        if (this._outputTail + 6 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedShort(s);
        } else {
            this._outputTail = NumberOutput.outputInt((int) s, this._outputBuffer, this._outputTail);
        }
    }

    private void _writeQuotedShort(short s) throws IOException {
        if (this._outputTail + 8 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = BYTE_QUOTE;
        this._outputTail = NumberOutput.outputInt((int) s, this._outputBuffer, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = BYTE_QUOTE;
    }

    public void writeNumber(int i) throws IOException, JsonGenerationException {
        _verifyValueWrite("write number");
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(i);
        } else {
            this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
        }
    }

    private void _writeQuotedInt(int i) throws IOException {
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = BYTE_QUOTE;
        this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = BYTE_QUOTE;
    }

    public void writeNumber(long j) throws IOException, JsonGenerationException {
        _verifyValueWrite("write number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedLong(j);
            return;
        }
        if (this._outputTail + 21 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputLong(j, this._outputBuffer, this._outputTail);
    }

    private void _writeQuotedLong(long j) throws IOException {
        if (this._outputTail + 23 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = BYTE_QUOTE;
        this._outputTail = NumberOutput.outputLong(j, this._outputBuffer, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = BYTE_QUOTE;
    }

    public void writeNumber(BigInteger bigInteger) throws IOException, JsonGenerationException {
        _verifyValueWrite("write number");
        if (bigInteger == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(bigInteger);
        } else {
            writeRaw(bigInteger.toString());
        }
    }

    public void writeNumber(double d) throws IOException, JsonGenerationException {
        if (this._cfgNumbersAsStrings || ((Double.isNaN(d) || Double.isInfinite(d)) && isEnabled(Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            writeString(String.valueOf(d));
            return;
        }
        _verifyValueWrite("write number");
        writeRaw(String.valueOf(d));
    }

    public void writeNumber(float f) throws IOException, JsonGenerationException {
        if (this._cfgNumbersAsStrings || ((Float.isNaN(f) || Float.isInfinite(f)) && isEnabled(Feature.QUOTE_NON_NUMERIC_NUMBERS))) {
            writeString(String.valueOf(f));
            return;
        }
        _verifyValueWrite("write number");
        writeRaw(String.valueOf(f));
    }

    public void writeNumber(BigDecimal bigDecimal) throws IOException, JsonGenerationException {
        _verifyValueWrite("write number");
        if (bigDecimal == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(bigDecimal);
        } else {
            writeRaw(bigDecimal.toString());
        }
    }

    public void writeNumber(String str) throws IOException, JsonGenerationException {
        _verifyValueWrite("write number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(str);
        } else {
            writeRaw(str);
        }
    }

    private void _writeQuotedRaw(Object obj) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = BYTE_QUOTE;
        writeRaw(obj.toString());
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = BYTE_QUOTE;
    }

    public void writeBoolean(boolean z) throws IOException, JsonGenerationException {
        _verifyValueWrite("write boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = z ? TRUE_BYTES : FALSE_BYTES;
        int length = bArr.length;
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    public void writeNull() throws IOException, JsonGenerationException {
        _verifyValueWrite("write null value");
        _writeNull();
    }

    /* access modifiers changed from: protected */
    public final void _verifyValueWrite(String str) throws IOException, JsonGenerationException {
        byte b;
        int writeValue = this._writeContext.writeValue();
        if (writeValue == 5) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can not ");
            sb.append(str);
            sb.append(", expecting field name");
            _reportError(sb.toString());
        }
        if (this._cfgPrettyPrinter == null) {
            switch (writeValue) {
                case 1:
                    b = BYTE_COMMA;
                    break;
                case 2:
                    b = BYTE_COLON;
                    break;
                case 3:
                    if (this._rootValueSeparator != null) {
                        byte[] asUnquotedUTF8 = this._rootValueSeparator.asUnquotedUTF8();
                        if (asUnquotedUTF8.length > 0) {
                            _writeBytes(asUnquotedUTF8);
                        }
                    }
                    return;
                default:
                    return;
            }
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            this._outputBuffer[this._outputTail] = b;
            this._outputTail++;
            return;
        }
        _verifyPrettyValueWrite(str, writeValue);
    }

    /* access modifiers changed from: protected */
    public final void _verifyPrettyValueWrite(String str, int i) throws IOException, JsonGenerationException {
        switch (i) {
            case 0:
                if (this._writeContext.inArray()) {
                    this._cfgPrettyPrinter.beforeArrayValues(this);
                    return;
                } else if (this._writeContext.inObject()) {
                    this._cfgPrettyPrinter.beforeObjectEntries(this);
                    return;
                } else {
                    return;
                }
            case 1:
                this._cfgPrettyPrinter.writeArrayValueSeparator(this);
                return;
            case 2:
                this._cfgPrettyPrinter.writeObjectFieldValueSeparator(this);
                return;
            case 3:
                this._cfgPrettyPrinter.writeRootValueSeparator(this);
                return;
            default:
                _throwInternal();
                return;
        }
    }

    public final void flush() throws IOException {
        _flushBuffer();
        if (this._outputStream != null && isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
            this._outputStream.flush();
        }
    }

    public void close() throws IOException {
        super.close();
        if (this._outputBuffer != null && isEnabled(Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonWriteContext outputContext = getOutputContext();
                if (!outputContext.inArray()) {
                    if (!outputContext.inObject()) {
                        break;
                    }
                    writeEndObject();
                } else {
                    writeEndArray();
                }
            }
        }
        _flushBuffer();
        if (this._outputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(Feature.AUTO_CLOSE_TARGET)) {
                this._outputStream.close();
            } else if (isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
                this._outputStream.flush();
            }
        }
        _releaseBuffers();
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() {
        byte[] bArr = this._outputBuffer;
        if (bArr != null && this._bufferRecyclable) {
            this._outputBuffer = null;
            this._ioContext.releaseWriteEncodingBuffer(bArr);
        }
        char[] cArr = this._charBuffer;
        if (cArr != null) {
            this._charBuffer = null;
            this._ioContext.releaseConcatBuffer(cArr);
        }
    }

    private final void _writeBytes(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this._outputTail + length > this._outputEnd) {
            _flushBuffer();
            if (length > 512) {
                this._outputStream.write(bArr, 0, length);
                return;
            }
        }
        System.arraycopy(bArr, 0, this._outputBuffer, this._outputTail, length);
        this._outputTail += length;
    }

    private final void _writeBytes(byte[] bArr, int i, int i2) throws IOException {
        if (this._outputTail + i2 > this._outputEnd) {
            _flushBuffer();
            if (i2 > 512) {
                this._outputStream.write(bArr, i, i2);
                return;
            }
        }
        System.arraycopy(bArr, i, this._outputBuffer, this._outputTail, i2);
        this._outputTail += i2;
    }

    private final void _writeStringSegments(String str) throws IOException, JsonGenerationException {
        int length = str.length();
        char[] cArr = this._charBuffer;
        int i = 0;
        while (length > 0) {
            int min = Math.min(this._outputMaxContiguous, length);
            int i2 = i + min;
            str.getChars(i, i2, cArr, 0);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, 0, min);
            length -= min;
            i = i2;
        }
    }

    private final void _writeStringSegments(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        do {
            int min = Math.min(this._outputMaxContiguous, i2);
            if (this._outputTail + min > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cArr, i, min);
            i += min;
            i2 -= min;
        } while (i2 > 0);
    }

    private final void _writeStringSegment(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        int i3 = i2 + i;
        int i4 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i < i3) {
            char c = cArr[i];
            if (c > 127 || iArr[c] != 0) {
                break;
            }
            int i5 = i4 + 1;
            bArr[i4] = (byte) c;
            i++;
            i4 = i5;
        }
        this._outputTail = i4;
        if (i >= i3) {
            return;
        }
        if (this._characterEscapes != null) {
            _writeCustomStringSegment2(cArr, i, i3);
        } else if (this._maximumNonEscapedChar == 0) {
            _writeStringSegment2(cArr, i, i3);
        } else {
            _writeStringSegmentASCII2(cArr, i, i3);
        }
    }

    private final void _writeStringSegment2(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        if (this._outputTail + (6 * (i2 - i)) > this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        while (i < i2) {
            int i4 = i + 1;
            char c = cArr[i];
            if (c <= 127) {
                if (iArr[c] == 0) {
                    int i5 = i3 + 1;
                    bArr[i3] = (byte) c;
                    i = i4;
                    i3 = i5;
                } else {
                    int i6 = iArr[c];
                    if (i6 > 0) {
                        int i7 = i3 + 1;
                        bArr[i3] = BYTE_BACKSLASH;
                        i3 = i7 + 1;
                        bArr[i7] = (byte) i6;
                    } else {
                        i3 = _writeGenericEscape(c, i3);
                    }
                }
            } else if (c <= 2047) {
                int i8 = i3 + 1;
                bArr[i3] = (byte) (192 | (c >> 6));
                i3 = i8 + 1;
                bArr[i8] = (byte) ((c & '?') | 128);
            } else {
                i3 = _outputMultiByteChar(c, i3);
            }
            i = i4;
        }
        this._outputTail = i3;
    }

    private final void _writeStringSegmentASCII2(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        if (this._outputTail + (6 * (i2 - i)) > this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i4 = this._maximumNonEscapedChar;
        while (i < i2) {
            int i5 = i + 1;
            char c = cArr[i];
            if (c <= 127) {
                if (iArr[c] == 0) {
                    int i6 = i3 + 1;
                    bArr[i3] = (byte) c;
                    i = i5;
                    i3 = i6;
                } else {
                    int i7 = iArr[c];
                    if (i7 > 0) {
                        int i8 = i3 + 1;
                        bArr[i3] = BYTE_BACKSLASH;
                        i3 = i8 + 1;
                        bArr[i8] = (byte) i7;
                    } else {
                        i3 = _writeGenericEscape(c, i3);
                    }
                }
            } else if (c > i4) {
                i3 = _writeGenericEscape(c, i3);
            } else if (c <= 2047) {
                int i9 = i3 + 1;
                bArr[i3] = (byte) (192 | (c >> 6));
                i3 = i9 + 1;
                bArr[i9] = (byte) ((c & '?') | 128);
            } else {
                i3 = _outputMultiByteChar(c, i3);
            }
            i = i5;
        }
        this._outputTail = i3;
    }

    private void _writeCustomStringSegment2(char[] cArr, int i, int i2) throws IOException, JsonGenerationException {
        if (this._outputTail + (6 * (i2 - i)) > this._outputEnd) {
            _flushBuffer();
        }
        int i3 = this._outputTail;
        byte[] bArr = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i4 = this._maximumNonEscapedChar <= 0 ? SupportMenu.USER_MASK : this._maximumNonEscapedChar;
        CharacterEscapes characterEscapes = this._characterEscapes;
        while (i < i2) {
            int i5 = i + 1;
            char c = cArr[i];
            if (c <= 127) {
                if (iArr[c] == 0) {
                    int i6 = i3 + 1;
                    bArr[i3] = (byte) c;
                    i = i5;
                    i3 = i6;
                } else {
                    int i7 = iArr[c];
                    if (i7 > 0) {
                        int i8 = i3 + 1;
                        bArr[i3] = BYTE_BACKSLASH;
                        i3 = i8 + 1;
                        bArr[i8] = (byte) i7;
                    } else if (i7 == -2) {
                        SerializableString escapeSequence = characterEscapes.getEscapeSequence(c);
                        if (escapeSequence == null) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Invalid custom escape definitions; custom escape not found for character code 0x");
                            sb.append(Integer.toHexString(c));
                            sb.append(", although was supposed to have one");
                            _reportError(sb.toString());
                        }
                        i3 = _writeCustomEscape(bArr, i3, escapeSequence, i2 - i5);
                    } else {
                        i3 = _writeGenericEscape(c, i3);
                    }
                }
            } else if (c > i4) {
                i3 = _writeGenericEscape(c, i3);
            } else {
                SerializableString escapeSequence2 = characterEscapes.getEscapeSequence(c);
                if (escapeSequence2 != null) {
                    i3 = _writeCustomEscape(bArr, i3, escapeSequence2, i2 - i5);
                } else if (c <= 2047) {
                    int i9 = i3 + 1;
                    bArr[i3] = (byte) (192 | (c >> 6));
                    i3 = i9 + 1;
                    bArr[i9] = (byte) ((c & '?') | 128);
                } else {
                    i3 = _outputMultiByteChar(c, i3);
                }
            }
            i = i5;
        }
        this._outputTail = i3;
    }

    private int _writeCustomEscape(byte[] bArr, int i, SerializableString serializableString, int i2) throws IOException, JsonGenerationException {
        byte[] asUnquotedUTF8 = serializableString.asUnquotedUTF8();
        int length = asUnquotedUTF8.length;
        if (length > 6) {
            return _handleLongCustomEscape(bArr, i, this._outputEnd, asUnquotedUTF8, i2);
        }
        System.arraycopy(asUnquotedUTF8, 0, bArr, i, length);
        return i + length;
    }

    private int _handleLongCustomEscape(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws IOException, JsonGenerationException {
        int length = bArr2.length;
        if (i + length > i2) {
            this._outputTail = i;
            _flushBuffer();
            int i4 = this._outputTail;
            if (length > bArr.length) {
                this._outputStream.write(bArr2, 0, length);
                return i4;
            }
            System.arraycopy(bArr2, 0, bArr, i4, length);
            i = i4 + length;
        }
        if ((6 * i3) + i <= i2) {
            return i;
        }
        _flushBuffer();
        return this._outputTail;
    }

    private void _writeUTF8Segments(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        do {
            int min = Math.min(this._outputMaxContiguous, i2);
            _writeUTF8Segment(bArr, i, min);
            i += min;
            i2 -= min;
        } while (i2 > 0);
    }

    private void _writeUTF8Segment(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        int[] iArr = this._outputEscapes;
        int i3 = i + i2;
        int i4 = i;
        while (i4 < i3) {
            int i5 = i4 + 1;
            byte b = bArr[i4];
            if (b < 0 || iArr[b] == 0) {
                i4 = i5;
            } else {
                _writeUTF8Segment2(bArr, i, i2);
                return;
            }
        }
        if (this._outputTail + i2 > this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(bArr, i, this._outputBuffer, this._outputTail, i2);
        this._outputTail += i2;
    }

    private void _writeUTF8Segment2(byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        int i3;
        int i4 = this._outputTail;
        if ((i2 * 6) + i4 > this._outputEnd) {
            _flushBuffer();
            i4 = this._outputTail;
        }
        byte[] bArr2 = this._outputBuffer;
        int[] iArr = this._outputEscapes;
        int i5 = i2 + i;
        while (i < i5) {
            int i6 = i + 1;
            byte b = bArr[i];
            if (b < 0 || iArr[b] == 0) {
                int i7 = i3 + 1;
                bArr2[i3] = b;
                i = i6;
                i3 = i7;
            } else {
                int i8 = iArr[b];
                if (i8 > 0) {
                    int i9 = i3 + 1;
                    bArr2[i3] = BYTE_BACKSLASH;
                    i3 = i9 + 1;
                    bArr2[i9] = (byte) i8;
                } else {
                    i3 = _writeGenericEscape(b, i3);
                }
                i = i6;
            }
        }
        this._outputTail = i3;
    }

    /* access modifiers changed from: protected */
    public void _writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException, JsonGenerationException {
        int i3 = i2 - 3;
        int i4 = this._outputEnd - 6;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        while (i <= i3) {
            if (this._outputTail > i4) {
                _flushBuffer();
            }
            int i5 = i + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            this._outputTail = base64Variant.encodeBase64Chunk((int) (((bArr[i] << 8) | (bArr[i5] & 255)) << 8) | (bArr[i6] & 255), this._outputBuffer, this._outputTail);
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int i8 = this._outputTail;
                this._outputTail = i8 + 1;
                bArr2[i8] = BYTE_BACKSLASH;
                byte[] bArr3 = this._outputBuffer;
                int i9 = this._outputTail;
                this._outputTail = i9 + 1;
                bArr3[i9] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
            i = i7;
        }
        int i10 = i2 - i;
        if (i10 > 0) {
            if (this._outputTail > i4) {
                _flushBuffer();
            }
            int i11 = i + 1;
            int i12 = bArr[i] << 16;
            if (i10 == 2) {
                i12 |= (bArr[i11] & 255) << 8;
            }
            this._outputTail = base64Variant.encodeBase64Partial(i12, i10, this._outputBuffer, this._outputTail);
        }
    }

    /* access modifiers changed from: protected */
    public int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr, int i) throws IOException, JsonGenerationException {
        int i2 = this._outputEnd - 6;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i3 = -3;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i <= 2) {
                break;
            }
            if (i4 > i3) {
                int _readMore = _readMore(inputStream, bArr, i4, i5, i);
                if (_readMore < 3) {
                    i5 = _readMore;
                    i4 = 0;
                    break;
                }
                i5 = _readMore;
                i4 = 0;
                i3 = _readMore - 3;
            }
            if (this._outputTail > i2) {
                _flushBuffer();
            }
            int i6 = i4 + 1;
            int i7 = bArr[i4] << 8;
            int i8 = i6 + 1;
            i4 = i8 + 1;
            i -= 3;
            this._outputTail = base64Variant.encodeBase64Chunk((int) (((bArr[i6] & 255) | i7) << 8) | (bArr[i8] & 255), this._outputBuffer, this._outputTail);
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int i9 = this._outputTail;
                this._outputTail = i9 + 1;
                bArr2[i9] = BYTE_BACKSLASH;
                byte[] bArr3 = this._outputBuffer;
                int i10 = this._outputTail;
                this._outputTail = i10 + 1;
                bArr3[i10] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (i <= 0) {
            return i;
        }
        int _readMore2 = _readMore(inputStream, bArr, i4, i5, i);
        if (_readMore2 <= 0) {
            return i;
        }
        if (this._outputTail > i2) {
            _flushBuffer();
        }
        int i11 = bArr[0] << 16;
        int i12 = 1;
        if (1 < _readMore2) {
            i11 |= (bArr[1] & 255) << 8;
            i12 = 2;
        }
        this._outputTail = base64Variant.encodeBase64Partial(i11, i12, this._outputBuffer, this._outputTail);
        return i - i12;
    }

    /* access modifiers changed from: protected */
    public int _writeBinary(Base64Variant base64Variant, InputStream inputStream, byte[] bArr) throws IOException, JsonGenerationException {
        int _readMore;
        int i = this._outputEnd - 6;
        int i2 = -3;
        int maxLineLength = base64Variant.getMaxLineLength() >> 2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i4 > i2) {
                _readMore = _readMore(inputStream, bArr, i4, i5, bArr.length);
                if (_readMore < 3) {
                    break;
                }
                i2 = _readMore - 3;
                i4 = 0;
                i5 = _readMore;
            }
            if (this._outputTail > i) {
                _flushBuffer();
            }
            int i6 = i4 + 1;
            int i7 = bArr[i4] << 8;
            int i8 = i6 + 1;
            i4 = i8 + 1;
            i3 += 3;
            this._outputTail = base64Variant.encodeBase64Chunk((int) (((bArr[i6] & 255) | i7) << 8) | (bArr[i8] & 255), this._outputBuffer, this._outputTail);
            maxLineLength--;
            if (maxLineLength <= 0) {
                byte[] bArr2 = this._outputBuffer;
                int i9 = this._outputTail;
                this._outputTail = i9 + 1;
                bArr2[i9] = BYTE_BACKSLASH;
                byte[] bArr3 = this._outputBuffer;
                int i10 = this._outputTail;
                this._outputTail = i10 + 1;
                bArr3[i10] = 110;
                maxLineLength = base64Variant.getMaxLineLength() >> 2;
            }
        }
        if (_readMore <= 0) {
            return i3;
        }
        if (this._outputTail > i) {
            _flushBuffer();
        }
        int i11 = bArr[0] << 16;
        int i12 = 1;
        if (1 < _readMore) {
            i11 |= (bArr[1] & 255) << 8;
            i12 = 2;
        }
        int i13 = i3 + i12;
        this._outputTail = base64Variant.encodeBase64Partial(i11, i12, this._outputBuffer, this._outputTail);
        return i13;
    }

    private int _readMore(InputStream inputStream, byte[] bArr, int i, int i2, int i3) throws IOException {
        int i4 = 0;
        while (i < i2) {
            int i5 = i4 + 1;
            int i6 = i + 1;
            bArr[i4] = bArr[i];
            i4 = i5;
            i = i6;
        }
        int min = Math.min(i3, bArr.length);
        do {
            int i7 = min - i4;
            if (i7 == 0) {
                break;
            }
            int read = inputStream.read(bArr, i4, i7);
            if (read < 0) {
                return i4;
            }
            i4 += read;
        } while (i4 < 3);
        return i4;
    }

    private int _outputRawMultiByteChar(int i, char[] cArr, int i2, int i3) throws IOException {
        if (i < SURR1_FIRST || i > SURR2_LAST) {
            byte[] bArr = this._outputBuffer;
            int i4 = this._outputTail;
            this._outputTail = i4 + 1;
            bArr[i4] = (byte) (224 | (i >> 12));
            int i5 = this._outputTail;
            this._outputTail = i5 + 1;
            bArr[i5] = (byte) (((i >> 6) & 63) | 128);
            int i6 = this._outputTail;
            this._outputTail = i6 + 1;
            bArr[i6] = (byte) ((i & 63) | 128);
            return i2;
        }
        if (i2 >= i3) {
            _reportError("Split surrogate on writeRaw() input (last character)");
        }
        _outputSurrogates(i, cArr[i2]);
        return i2 + 1;
    }

    /* access modifiers changed from: protected */
    public final void _outputSurrogates(int i, int i2) throws IOException {
        int _decodeSurrogate = _decodeSurrogate(i, i2);
        if (this._outputTail + 4 > this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr[i3] = (byte) (240 | (_decodeSurrogate >> 18));
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bArr[i4] = (byte) (((_decodeSurrogate >> 12) & 63) | 128);
        int i5 = this._outputTail;
        this._outputTail = i5 + 1;
        bArr[i5] = (byte) (((_decodeSurrogate >> 6) & 63) | 128);
        int i6 = this._outputTail;
        this._outputTail = i6 + 1;
        bArr[i6] = (byte) ((_decodeSurrogate & 63) | 128);
    }

    private int _outputMultiByteChar(int i, int i2) throws IOException {
        byte[] bArr = this._outputBuffer;
        if (i < SURR1_FIRST || i > SURR2_LAST) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (224 | (i >> 12));
            int i4 = i3 + 1;
            bArr[i3] = (byte) (((i >> 6) & 63) | 128);
            int i5 = i4 + 1;
            bArr[i4] = (byte) ((i & 63) | 128);
            return i5;
        }
        int i6 = i2 + 1;
        bArr[i2] = BYTE_BACKSLASH;
        int i7 = i6 + 1;
        bArr[i6] = BYTE_u;
        int i8 = i7 + 1;
        bArr[i7] = HEX_CHARS[(i >> 12) & 15];
        int i9 = i8 + 1;
        bArr[i8] = HEX_CHARS[(i >> 8) & 15];
        int i10 = i9 + 1;
        bArr[i9] = HEX_CHARS[(i >> 4) & 15];
        int i11 = i10 + 1;
        bArr[i10] = HEX_CHARS[i & 15];
        return i11;
    }

    /* access modifiers changed from: protected */
    public final int _decodeSurrogate(int i, int i2) throws IOException {
        if (i2 < SURR2_FIRST || i2 > SURR2_LAST) {
            StringBuilder sb = new StringBuilder();
            sb.append("Incomplete surrogate pair: first char 0x");
            sb.append(Integer.toHexString(i));
            sb.append(", second 0x");
            sb.append(Integer.toHexString(i2));
            _reportError(sb.toString());
        }
        return 65536 + ((i - SURR1_FIRST) << 10) + (i2 - SURR2_FIRST);
    }

    private void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
        this._outputTail += 4;
    }

    private int _writeGenericEscape(int i, int i2) throws IOException {
        int i3;
        byte[] bArr = this._outputBuffer;
        int i4 = i2 + 1;
        bArr[i2] = BYTE_BACKSLASH;
        int i5 = i4 + 1;
        bArr[i4] = BYTE_u;
        if (i > 255) {
            int i6 = 255 & (i >> 8);
            int i7 = i5 + 1;
            bArr[i5] = HEX_CHARS[i6 >> 4];
            i3 = i7 + 1;
            bArr[i7] = HEX_CHARS[i6 & 15];
            i &= 255;
        } else {
            int i8 = i5 + 1;
            bArr[i5] = BYTE_0;
            i3 = i8 + 1;
            bArr[i8] = BYTE_0;
        }
        int i9 = i3 + 1;
        bArr[i3] = HEX_CHARS[i >> 4];
        int i10 = i9 + 1;
        bArr[i9] = HEX_CHARS[i & 15];
        return i10;
    }

    /* access modifiers changed from: protected */
    public final void _flushBuffer() throws IOException {
        int i = this._outputTail;
        if (i > 0) {
            this._outputTail = 0;
            this._outputStream.write(this._outputBuffer, 0, i);
        }
    }
}
