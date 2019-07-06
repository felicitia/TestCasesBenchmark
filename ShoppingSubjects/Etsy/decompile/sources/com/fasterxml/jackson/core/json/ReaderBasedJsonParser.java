package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import org.apache.commons.lang3.CharUtils;

public final class ReaderBasedJsonParser extends ParserBase {
    protected final int _hashSeed;
    protected char[] _inputBuffer;
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete = false;

    public ReaderBasedJsonParser(IOContext iOContext, int i, Reader reader, ObjectCodec objectCodec, CharsToNameCanonicalizer charsToNameCanonicalizer) {
        super(iOContext, i);
        this._reader = reader;
        this._inputBuffer = iOContext.allocTokenBuffer();
        this._objectCodec = objectCodec;
        this._symbols = charsToNameCanonicalizer;
        this._hashSeed = charsToNameCanonicalizer.hashSeed();
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    public int releaseBuffered(Writer writer) throws IOException {
        int i = this._inputEnd - this._inputPtr;
        if (i < 1) {
            return 0;
        }
        writer.write(this._inputBuffer, this._inputPtr, i);
        return i;
    }

    public Object getInputSource() {
        return this._reader;
    }

    /* access modifiers changed from: protected */
    public boolean loadMore() throws IOException {
        this._currInputProcessed += (long) this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        if (this._reader != null) {
            int read = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Reader returned 0 characters when trying to read ");
                sb.append(this._inputEnd);
                throw new IOException(sb.toString());
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public char getNextChar(String str) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(str);
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return cArr[i];
    }

    /* access modifiers changed from: protected */
    public void _closeInput() throws IOException {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        char[] cArr = this._inputBuffer;
        if (cArr != null) {
            this._inputBuffer = null;
            this._ioContext.releaseTokenBuffer(cArr);
        }
    }

    public String getText() throws IOException, JsonParseException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return _getText2(jsonToken);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    public String getValueAsString() throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return super.getValueAsString(null);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    public String getValueAsString(String str) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return super.getValueAsString(str);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    /* access modifiers changed from: protected */
    public String _getText2(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        switch (jsonToken) {
            case FIELD_NAME:
                return this._parsingContext.getCurrentName();
            case VALUE_STRING:
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return this._textBuffer.contentsAsString();
            default:
                return jsonToken.asString();
        }
    }

    public char[] getTextCharacters() throws IOException, JsonParseException {
        if (this._currToken == null) {
            return null;
        }
        switch (this._currToken) {
            case FIELD_NAME:
                if (!this._nameCopied) {
                    String currentName = this._parsingContext.getCurrentName();
                    int length = currentName.length();
                    if (this._nameCopyBuffer == null) {
                        this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
                    } else if (this._nameCopyBuffer.length < length) {
                        this._nameCopyBuffer = new char[length];
                    }
                    currentName.getChars(0, length, this._nameCopyBuffer, 0);
                    this._nameCopied = true;
                }
                return this._nameCopyBuffer;
            case VALUE_STRING:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                break;
            default:
                return this._currToken.asCharArray();
        }
        return this._textBuffer.getTextBuffer();
    }

    public int getTextLength() throws IOException, JsonParseException {
        if (this._currToken == null) {
            return 0;
        }
        switch (this._currToken) {
            case FIELD_NAME:
                return this._parsingContext.getCurrentName().length();
            case VALUE_STRING:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                break;
            default:
                return this._currToken.asCharArray().length;
        }
        return this._textBuffer.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0022, code lost:
        return r3._textBuffer.getTextOffset();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getTextOffset() throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r3 = this;
            com.fasterxml.jackson.core.JsonToken r0 = r3._currToken
            r1 = 0
            if (r0 == 0) goto L_0x0024
            int[] r0 = com.fasterxml.jackson.core.json.ReaderBasedJsonParser.AnonymousClass1.a
            com.fasterxml.jackson.core.JsonToken r2 = r3._currToken
            int r2 = r2.ordinal()
            r0 = r0[r2]
            switch(r0) {
                case 1: goto L_0x0023;
                case 2: goto L_0x0013;
                case 3: goto L_0x001c;
                case 4: goto L_0x001c;
                default: goto L_0x0012;
            }
        L_0x0012:
            goto L_0x0024
        L_0x0013:
            boolean r0 = r3._tokenIncomplete
            if (r0 == 0) goto L_0x001c
            r3._tokenIncomplete = r1
            r3._finishString()
        L_0x001c:
            com.fasterxml.jackson.core.util.TextBuffer r0 = r3._textBuffer
            int r0 = r0.getTextOffset()
            return r0
        L_0x0023:
            return r1
        L_0x0024:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser.getTextOffset():int");
    }

    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Current token (");
            sb.append(this._currToken);
            sb.append(") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
            _reportError(sb.toString());
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(base64Variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Failed to decode VALUE_STRING as base64 (");
                sb2.append(base64Variant);
                sb2.append("): ");
                sb2.append(e.getMessage());
                throw _constructError(sb2.toString());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), _getByteArrayBuilder, base64Variant);
            this._binaryValue = _getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException, JsonParseException {
        if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
            byte[] binaryValue = getBinaryValue(base64Variant);
            outputStream.write(binaryValue);
            return binaryValue.length;
        }
        byte[] allocBase64Buffer = this._ioContext.allocBase64Buffer();
        try {
            int _readBinary = _readBinary(base64Variant, outputStream, allocBase64Buffer);
            return _readBinary;
        } finally {
            this._ioContext.releaseBase64Buffer(allocBase64Buffer);
        }
    }

    /* access modifiers changed from: protected */
    public int _readBinary(Base64Variant base64Variant, OutputStream outputStream, byte[] bArr) throws IOException, JsonParseException {
        int i;
        int length = bArr.length - 3;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            char c = cArr[i4];
            if (c > ' ') {
                int decodeBase64Char = base64Variant.decodeBase64Char(c);
                if (decodeBase64Char < 0) {
                    if (c == '\"') {
                        break;
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, c, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (i2 > length) {
                    i3 += i2;
                    outputStream.write(bArr, 0, i2);
                    i2 = 0;
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr2 = this._inputBuffer;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                char c2 = cArr2[i5];
                int decodeBase64Char2 = base64Variant.decodeBase64Char(c2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, c2, 1);
                }
                int i6 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr3 = this._inputBuffer;
                int i7 = this._inputPtr;
                this._inputPtr = i7 + 1;
                char c3 = cArr3[i7];
                int decodeBase64Char3 = base64Variant.decodeBase64Char(c3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (c3 == '\"' && !base64Variant.usesPadding()) {
                            int i8 = i2 + 1;
                            bArr[i2] = (byte) (i6 >> 4);
                            i2 = i8;
                            break;
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, c3, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        char[] cArr4 = this._inputBuffer;
                        int i9 = this._inputPtr;
                        this._inputPtr = i9 + 1;
                        char c4 = cArr4[i9];
                        if (!base64Variant.usesPaddingChar(c4)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("expected padding character '");
                            sb.append(base64Variant.getPaddingChar());
                            sb.append("'");
                            throw reportInvalidBase64Char(base64Variant, c4, 3, sb.toString());
                        }
                        i = i2 + 1;
                        bArr[i2] = (byte) (i6 >> 4);
                        i2 = i;
                    }
                }
                int i10 = (i6 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr5 = this._inputBuffer;
                int i11 = this._inputPtr;
                this._inputPtr = i11 + 1;
                char c5 = cArr5[i11];
                int decodeBase64Char4 = base64Variant.decodeBase64Char(c5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (c5 == '\"' && !base64Variant.usesPadding()) {
                            int i12 = i10 >> 2;
                            int i13 = i2 + 1;
                            bArr[i2] = (byte) (i12 >> 8);
                            i2 = i13 + 1;
                            bArr[i13] = (byte) i12;
                            break;
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, c5, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        int i14 = i10 >> 2;
                        int i15 = i2 + 1;
                        bArr[i2] = (byte) (i14 >> 8);
                        i2 = i15 + 1;
                        bArr[i15] = (byte) i14;
                    }
                }
                int i16 = (i10 << 6) | decodeBase64Char4;
                int i17 = i2 + 1;
                bArr[i2] = (byte) (i16 >> 16);
                int i18 = i17 + 1;
                bArr[i17] = (byte) (i16 >> 8);
                i = i18 + 1;
                bArr[i18] = (byte) i16;
                i2 = i;
            }
        }
        this._tokenIncomplete = false;
        if (i2 <= 0) {
            return i3;
        }
        int i19 = i3 + i2;
        outputStream.write(bArr, 0, i2);
        return i19;
    }

    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken jsonToken;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._tokenInputTotal = (this._currInputProcessed + ((long) this._inputPtr)) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        if (_skipWSOrEnd == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken2 = JsonToken.END_ARRAY;
            this._currToken = jsonToken2;
            return jsonToken2;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            JsonToken jsonToken3 = JsonToken.END_OBJECT;
            this._currToken = jsonToken3;
            return jsonToken3;
        } else {
            if (this._parsingContext.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("was expecting comma to separate ");
                    sb.append(this._parsingContext.getTypeDesc());
                    sb.append(" entries");
                    _reportUnexpectedChar(_skipWSOrEnd, sb.toString());
                }
                _skipWSOrEnd = _skipWS();
            }
            boolean inObject = this._parsingContext.inObject();
            if (inObject) {
                this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd));
                this._currToken = JsonToken.FIELD_NAME;
                int _skipWS = _skipWS();
                if (_skipWS != 58) {
                    _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
                }
                _skipWSOrEnd = _skipWS();
            }
            if (_skipWSOrEnd != 34) {
                if (_skipWSOrEnd != 45) {
                    if (_skipWSOrEnd != 91) {
                        if (_skipWSOrEnd != 93) {
                            if (_skipWSOrEnd == 102) {
                                _matchToken("false", 1);
                                jsonToken = JsonToken.VALUE_FALSE;
                            } else if (_skipWSOrEnd != 110) {
                                if (_skipWSOrEnd != 116) {
                                    if (_skipWSOrEnd != 123) {
                                        if (_skipWSOrEnd != 125) {
                                            switch (_skipWSOrEnd) {
                                                case 48:
                                                case 49:
                                                case 50:
                                                case 51:
                                                case 52:
                                                case 53:
                                                case 54:
                                                case 55:
                                                case 56:
                                                case 57:
                                                    break;
                                                default:
                                                    jsonToken = _handleUnexpectedValue(_skipWSOrEnd);
                                                    break;
                                            }
                                        }
                                    } else {
                                        if (!inObject) {
                                            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                                        }
                                        jsonToken = JsonToken.START_OBJECT;
                                    }
                                }
                                _matchToken("true", 1);
                                jsonToken = JsonToken.VALUE_TRUE;
                            } else {
                                _matchToken("null", 1);
                                jsonToken = JsonToken.VALUE_NULL;
                            }
                        }
                        _reportUnexpectedChar(_skipWSOrEnd, "expected a value");
                        _matchToken("true", 1);
                        jsonToken = JsonToken.VALUE_TRUE;
                    } else {
                        if (!inObject) {
                            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                        }
                        jsonToken = JsonToken.START_ARRAY;
                    }
                }
                jsonToken = parseNumberText(_skipWSOrEnd);
            } else {
                this._tokenIncomplete = true;
                jsonToken = JsonToken.VALUE_STRING;
            }
            if (inObject) {
                this._nextToken = jsonToken;
                return this._currToken;
            }
            this._currToken = jsonToken;
            return jsonToken;
        }
    }

    private JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken jsonToken = this._nextToken;
        this._nextToken = null;
        if (jsonToken == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (jsonToken == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = jsonToken;
        return jsonToken;
    }

    public String nextTextValue() throws IOException, JsonParseException {
        String str = null;
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_STRING) {
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                }
                return this._textBuffer.contentsAsString();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        if (nextToken() == JsonToken.VALUE_STRING) {
            str = getText();
        }
        return str;
    }

    public int nextIntValue(int i) throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
                return getIntValue();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return i;
        }
        if (nextToken() == JsonToken.VALUE_NUMBER_INT) {
            i = getIntValue();
        }
        return i;
    }

    public long nextLongValue(long j) throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
                return getLongValue();
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return j;
        }
        if (nextToken() == JsonToken.VALUE_NUMBER_INT) {
            j = getLongValue();
        }
        return j;
    }

    public Boolean nextBooleanValue() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken jsonToken = this._nextToken;
            this._nextToken = null;
            this._currToken = jsonToken;
            if (jsonToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (jsonToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            if (jsonToken == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            } else if (jsonToken == JsonToken.START_OBJECT) {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
            }
            return null;
        }
        switch (nextToken()) {
            case VALUE_TRUE:
                return Boolean.TRUE;
            case VALUE_FALSE:
                return Boolean.FALSE;
            default:
                return null;
        }
    }

    public void close() throws IOException {
        super.close();
        this._symbols.release();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0066, code lost:
        r9 = r10;
        r11 = r4;
        r4 = r13;
        r13 = r11;
     */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken parseNumberText(int r13) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r12 = this;
            r0 = 45
            r1 = 0
            r2 = 1
            if (r13 != r0) goto L_0x0008
            r3 = r2
            goto L_0x0009
        L_0x0008:
            r3 = r1
        L_0x0009:
            int r4 = r12._inputPtr
            int r5 = r4 + -1
            int r6 = r12._inputEnd
            r7 = 57
            r8 = 48
            if (r3 == 0) goto L_0x002f
            int r13 = r12._inputEnd
            if (r4 < r13) goto L_0x001b
            goto L_0x0098
        L_0x001b:
            char[] r13 = r12._inputBuffer
            int r9 = r4 + 1
            char r13 = r13[r4]
            if (r13 > r7) goto L_0x0028
            if (r13 >= r8) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            r4 = r9
            goto L_0x002f
        L_0x0028:
            r12._inputPtr = r9
            com.fasterxml.jackson.core.JsonToken r13 = r12._handleInvalidNumberStart(r13, r2)
            return r13
        L_0x002f:
            if (r13 != r8) goto L_0x0033
            goto L_0x0098
        L_0x0033:
            int r13 = r12._inputEnd
            if (r4 < r13) goto L_0x0039
            goto L_0x0098
        L_0x0039:
            char[] r13 = r12._inputBuffer
            int r9 = r4 + 1
            char r13 = r13[r4]
            if (r13 < r8) goto L_0x0048
            if (r13 <= r7) goto L_0x0044
            goto L_0x0048
        L_0x0044:
            int r2 = r2 + 1
            r4 = r9
            goto L_0x0033
        L_0x0048:
            r4 = 46
            if (r13 != r4) goto L_0x006b
            r13 = r1
        L_0x004d:
            if (r9 < r6) goto L_0x0050
            goto L_0x0098
        L_0x0050:
            char[] r4 = r12._inputBuffer
            int r10 = r9 + 1
            char r4 = r4[r9]
            if (r4 < r8) goto L_0x005f
            if (r4 <= r7) goto L_0x005b
            goto L_0x005f
        L_0x005b:
            int r13 = r13 + 1
            r9 = r10
            goto L_0x004d
        L_0x005f:
            if (r13 != 0) goto L_0x0066
            java.lang.String r9 = "Decimal point not followed by a digit"
            r12.reportUnexpectedNumberChar(r4, r9)
        L_0x0066:
            r9 = r10
            r11 = r4
            r4 = r13
            r13 = r11
            goto L_0x006c
        L_0x006b:
            r4 = r1
        L_0x006c:
            r10 = 101(0x65, float:1.42E-43)
            if (r13 == r10) goto L_0x0074
            r10 = 69
            if (r13 != r10) goto L_0x00b1
        L_0x0074:
            if (r9 < r6) goto L_0x0077
            goto L_0x0098
        L_0x0077:
            char[] r13 = r12._inputBuffer
            int r10 = r9 + 1
            char r13 = r13[r9]
            if (r13 == r0) goto L_0x0086
            r0 = 43
            if (r13 != r0) goto L_0x0084
            goto L_0x0086
        L_0x0084:
            r9 = r10
            goto L_0x0090
        L_0x0086:
            if (r10 < r6) goto L_0x0089
            goto L_0x0098
        L_0x0089:
            char[] r13 = r12._inputBuffer
            int r0 = r10 + 1
            char r13 = r13[r10]
        L_0x008f:
            r9 = r0
        L_0x0090:
            if (r13 > r7) goto L_0x00aa
            if (r13 < r8) goto L_0x00aa
            int r1 = r1 + 1
            if (r9 < r6) goto L_0x00a3
        L_0x0098:
            if (r3 == 0) goto L_0x009c
            int r5 = r5 + 1
        L_0x009c:
            r12._inputPtr = r5
            com.fasterxml.jackson.core.JsonToken r13 = r12.parseNumberText2(r3)
            return r13
        L_0x00a3:
            char[] r13 = r12._inputBuffer
            int r0 = r9 + 1
            char r13 = r13[r9]
            goto L_0x008f
        L_0x00aa:
            if (r1 != 0) goto L_0x00b1
            java.lang.String r0 = "Exponent indicator not followed by a digit"
            r12.reportUnexpectedNumberChar(r13, r0)
        L_0x00b1:
            int r9 = r9 + -1
            r12._inputPtr = r9
            int r9 = r9 - r5
            com.fasterxml.jackson.core.util.TextBuffer r13 = r12._textBuffer
            char[] r0 = r12._inputBuffer
            r13.resetWithShared(r0, r5, r9)
            com.fasterxml.jackson.core.JsonToken r13 = r12.reset(r3, r2, r4, r1)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser.parseNumberText(int):com.fasterxml.jackson.core.JsonToken");
    }

    private JsonToken parseNumberText2(boolean z) throws IOException, JsonParseException {
        int i;
        char c;
        boolean z2;
        boolean z3;
        int i2;
        char c2;
        char nextChar;
        int i3;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i4 = 0;
        if (z) {
            emptyAndGetCurrentSegment[0] = '-';
            i = 1;
        } else {
            i = 0;
        }
        if (this._inputPtr < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            int i5 = this._inputPtr;
            this._inputPtr = i5 + 1;
            c = cArr[i5];
        } else {
            c = getNextChar("No digit following minus sign");
        }
        if (c == '0') {
            c = _verifyNoLeadingZeroes();
        }
        char[] cArr2 = emptyAndGetCurrentSegment;
        int i6 = 0;
        while (true) {
            if (c >= '0' && c <= '9') {
                i6++;
                if (i >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i = 0;
                }
                int i7 = i + 1;
                cArr2[i] = c;
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    c = 0;
                    i = i7;
                    z2 = true;
                    break;
                }
                char[] cArr3 = this._inputBuffer;
                int i8 = this._inputPtr;
                this._inputPtr = i8 + 1;
                c = cArr3[i8];
                i = i7;
            } else {
                z2 = false;
            }
        }
        z2 = false;
        if (i6 == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Missing integer part (next char ");
            sb.append(_getCharDesc(c));
            sb.append(")");
            reportInvalidNumber(sb.toString());
        }
        if (c == '.') {
            int i9 = i + 1;
            cArr2[i] = c;
            int i10 = 0;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    z2 = true;
                    break;
                }
                char[] cArr4 = this._inputBuffer;
                int i11 = this._inputPtr;
                this._inputPtr = i11 + 1;
                c = cArr4[i11];
                if (c < '0' || c > '9') {
                    break;
                }
                i10++;
                if (i9 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i9 = 0;
                }
                int i12 = i9 + 1;
                cArr2[i9] = c;
                i9 = i12;
            }
            if (i10 == 0) {
                reportUnexpectedNumberChar(c, "Decimal point not followed by a digit");
            }
            boolean z4 = z2;
            i2 = i10;
            i = i9;
            z3 = z4;
        } else {
            z3 = z2;
            i2 = 0;
        }
        if (c == 'e' || c == 'E') {
            if (i >= cArr2.length) {
                cArr2 = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int i13 = i + 1;
            cArr2[i] = c;
            if (this._inputPtr < this._inputEnd) {
                char[] cArr5 = this._inputBuffer;
                int i14 = this._inputPtr;
                this._inputPtr = i14 + 1;
                c2 = cArr5[i14];
            } else {
                c2 = getNextChar("expected a digit for number exponent");
            }
            if (c2 == '-' || c2 == '+') {
                if (i13 >= cArr2.length) {
                    cArr2 = this._textBuffer.finishCurrentSegment();
                    i13 = 0;
                }
                int i15 = i13 + 1;
                cArr2[i13] = c2;
                if (this._inputPtr < this._inputEnd) {
                    char[] cArr6 = this._inputBuffer;
                    int i16 = this._inputPtr;
                    this._inputPtr = i16 + 1;
                    nextChar = cArr6[i16];
                } else {
                    nextChar = getNextChar("expected a digit for number exponent");
                }
                i13 = i15;
            }
            int i17 = 0;
            while (true) {
                if (c2 <= '9' && c2 >= '0') {
                    i17++;
                    if (i13 >= cArr2.length) {
                        i13 = 0;
                        cArr2 = this._textBuffer.finishCurrentSegment();
                    }
                    i3 = i13 + 1;
                    cArr2[i13] = c2;
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        i4 = i17;
                        z3 = true;
                        break;
                    }
                    char[] cArr7 = this._inputBuffer;
                    int i18 = this._inputPtr;
                    this._inputPtr = i18 + 1;
                    c2 = cArr7[i18];
                    i13 = i3;
                } else {
                    i4 = i17;
                    i3 = i13;
                }
            }
            i4 = i17;
            i3 = i13;
            if (i4 == 0) {
                reportUnexpectedNumberChar(c2, "Exponent indicator not followed by a digit");
            }
            i = i3;
        }
        if (!z3) {
            this._inputPtr--;
        }
        this._textBuffer.setCurrentLength(i);
        return reset(z, i6, i2, i4);
    }

    private char _verifyNoLeadingZeroes() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return '0';
        }
        char c = this._inputBuffer[this._inputPtr];
        if (c < '0' || c > '9') {
            return '0';
        }
        if (!isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (c == '0') {
            do {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    break;
                }
                c = this._inputBuffer[this._inputPtr];
                if (c < '0' || c > '9') {
                    return '0';
                }
                this._inputPtr++;
            } while (c == '0');
        }
        return c;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=char, for r7v0, types: [int] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken _handleInvalidNumberStart(char r7, boolean r8) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r6 = this;
            r0 = 73
            if (r7 != r0) goto L_0x0091
            int r7 = r6._inputPtr
            int r0 = r6._inputEnd
            if (r7 < r0) goto L_0x0013
            boolean r7 = r6.loadMore()
            if (r7 != 0) goto L_0x0013
            r6._reportInvalidEOFInValue()
        L_0x0013:
            char[] r7 = r6._inputBuffer
            int r0 = r6._inputPtr
            int r1 = r0 + 1
            r6._inputPtr = r1
            char r7 = r7[r0]
            r0 = 78
            r1 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            r3 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            r5 = 3
            if (r7 != r0) goto L_0x005a
            if (r8 == 0) goto L_0x002b
            java.lang.String r0 = "-INF"
            goto L_0x002d
        L_0x002b:
            java.lang.String r0 = "+INF"
        L_0x002d:
            r6._matchToken(r0, r5)
            com.fasterxml.jackson.core.JsonParser$Feature r5 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS
            boolean r5 = r6.isEnabled(r5)
            if (r5 == 0) goto L_0x0040
            if (r8 == 0) goto L_0x003b
            r1 = r3
        L_0x003b:
            com.fasterxml.jackson.core.JsonToken r7 = r6.resetAsNaN(r0, r1)
            return r7
        L_0x0040:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "Non-standard token '"
            r8.append(r1)
            r8.append(r0)
            java.lang.String r0 = "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            r6._reportError(r8)
            goto L_0x0091
        L_0x005a:
            r0 = 110(0x6e, float:1.54E-43)
            if (r7 != r0) goto L_0x0091
            if (r8 == 0) goto L_0x0063
            java.lang.String r0 = "-Infinity"
            goto L_0x0065
        L_0x0063:
            java.lang.String r0 = "+Infinity"
        L_0x0065:
            r6._matchToken(r0, r5)
            com.fasterxml.jackson.core.JsonParser$Feature r5 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS
            boolean r5 = r6.isEnabled(r5)
            if (r5 == 0) goto L_0x0078
            if (r8 == 0) goto L_0x0073
            r1 = r3
        L_0x0073:
            com.fasterxml.jackson.core.JsonToken r7 = r6.resetAsNaN(r0, r1)
            return r7
        L_0x0078:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r1 = "Non-standard token '"
            r8.append(r1)
            r8.append(r0)
            java.lang.String r0 = "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            r6._reportError(r8)
        L_0x0091:
            java.lang.String r8 = "expected digit (0-9) to follow minus sign, for valid numeric value"
            r6.reportUnexpectedNumberChar(r7, r8)
            r7 = 0
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._handleInvalidNumberStart(int, boolean):com.fasterxml.jackson.core.JsonToken");
    }

    /* access modifiers changed from: protected */
    public String _parseFieldName(int i) throws IOException, JsonParseException {
        if (i != 34) {
            return _handleUnusualFieldName(i);
        }
        int i2 = this._inputPtr;
        int i3 = this._hashSeed;
        int i4 = this._inputEnd;
        if (i2 < i4) {
            int[] inputCodeLatin1 = CharTypes.getInputCodeLatin1();
            int length = inputCodeLatin1.length;
            while (true) {
                char c = this._inputBuffer[i2];
                if (c >= length || inputCodeLatin1[c] == 0) {
                    i3 = (i3 * 33) + c;
                    i2++;
                    if (i2 >= i4) {
                        break;
                    }
                } else if (c == '\"') {
                    int i5 = this._inputPtr;
                    this._inputPtr = i2 + 1;
                    return this._symbols.findSymbol(this._inputBuffer, i5, i2 - i5, i3);
                }
            }
        }
        int i6 = this._inputPtr;
        this._inputPtr = i2;
        return _parseFieldName2(i6, i3, 34);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String _parseFieldName2(int r5, int r6, int r7) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r4 = this;
            com.fasterxml.jackson.core.util.TextBuffer r0 = r4._textBuffer
            char[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r2 = r2 - r5
            r0.resetWithShared(r1, r5, r2)
            com.fasterxml.jackson.core.util.TextBuffer r5 = r4._textBuffer
            char[] r5 = r5.getCurrentSegment()
            com.fasterxml.jackson.core.util.TextBuffer r0 = r4._textBuffer
            int r0 = r0.getCurrentSegmentSize()
        L_0x0016:
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L_0x003c
            boolean r1 = r4.loadMore()
            if (r1 != 0) goto L_0x003c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = ": was expecting closing '"
            r1.append(r2)
            char r2 = (char) r7
            r1.append(r2)
            java.lang.String r2 = "' for name"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r4._reportInvalidEOF(r1)
        L_0x003c:
            char[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r3 = r2 + 1
            r4._inputPtr = r3
            char r1 = r1[r2]
            r2 = 92
            if (r1 > r2) goto L_0x0078
            if (r1 != r2) goto L_0x0051
            char r2 = r4._decodeEscaped()
            goto L_0x0079
        L_0x0051:
            if (r1 > r7) goto L_0x0078
            if (r1 != r7) goto L_0x006f
            com.fasterxml.jackson.core.util.TextBuffer r5 = r4._textBuffer
            r5.setCurrentLength(r0)
            com.fasterxml.jackson.core.util.TextBuffer r5 = r4._textBuffer
            char[] r7 = r5.getTextBuffer()
            int r0 = r5.getTextOffset()
            int r5 = r5.size()
            com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer r1 = r4._symbols
            java.lang.String r5 = r1.findSymbol(r7, r0, r5, r6)
            return r5
        L_0x006f:
            r2 = 32
            if (r1 >= r2) goto L_0x0078
            java.lang.String r2 = "name"
            r4._throwUnquotedSpace(r1, r2)
        L_0x0078:
            r2 = r1
        L_0x0079:
            int r6 = r6 * 33
            int r6 = r6 + r1
            int r1 = r0 + 1
            r5[r0] = r2
            int r0 = r5.length
            if (r1 < r0) goto L_0x008b
            com.fasterxml.jackson.core.util.TextBuffer r5 = r4._textBuffer
            char[] r5 = r5.finishCurrentSegment()
            r0 = 0
            goto L_0x0016
        L_0x008b:
            r0 = r1
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.ReaderBasedJsonParser._parseFieldName2(int, int, int):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public String _handleUnusualFieldName(int i) throws IOException, JsonParseException {
        if (i == 39 && isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeLatin1JsNames = CharTypes.getInputCodeLatin1JsNames();
        int length = inputCodeLatin1JsNames.length;
        boolean z = i < length ? inputCodeLatin1JsNames[i] == 0 && (i < 48 || i > 57) : Character.isJavaIdentifierPart((char) i);
        if (!z) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i2 = this._inputPtr;
        int i3 = this._hashSeed;
        int i4 = this._inputEnd;
        if (i2 < i4) {
            do {
                char c = this._inputBuffer[i2];
                if (c < length) {
                    if (inputCodeLatin1JsNames[c] != 0) {
                        int i5 = this._inputPtr - 1;
                        this._inputPtr = i2;
                        return this._symbols.findSymbol(this._inputBuffer, i5, i2 - i5, i3);
                    }
                } else if (!Character.isJavaIdentifierPart((char) c)) {
                    int i6 = this._inputPtr - 1;
                    this._inputPtr = i2;
                    return this._symbols.findSymbol(this._inputBuffer, i6, i2 - i6, i3);
                }
                i3 = (i3 * 33) + c;
                i2++;
            } while (i2 < i4);
        }
        int i7 = this._inputPtr - 1;
        this._inputPtr = i2;
        return _parseUnusualFieldName2(i7, i3, inputCodeLatin1JsNames);
    }

    /* access modifiers changed from: protected */
    public String _parseApostropheFieldName() throws IOException, JsonParseException {
        int i = this._inputPtr;
        int i2 = this._hashSeed;
        int i3 = this._inputEnd;
        if (i < i3) {
            int[] inputCodeLatin1 = CharTypes.getInputCodeLatin1();
            int length = inputCodeLatin1.length;
            do {
                char c = this._inputBuffer[i];
                if (c != '\'') {
                    if (c < length && inputCodeLatin1[c] != 0) {
                        break;
                    }
                    i2 = (i2 * 33) + c;
                    i++;
                } else {
                    int i4 = this._inputPtr;
                    this._inputPtr = i + 1;
                    return this._symbols.findSymbol(this._inputBuffer, i4, i - i4, i2);
                }
            } while (i < i3);
        }
        int i5 = this._inputPtr;
        this._inputPtr = i;
        return _parseFieldName2(i5, i2, 39);
    }

    /* access modifiers changed from: protected */
    public JsonToken _handleUnexpectedValue(int i) throws IOException, JsonParseException {
        if (i != 39) {
            if (i == 43) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                char[] cArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(cArr[i2], false);
            } else if (i == 78) {
                _matchToken("NaN", 1);
                if (isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    return resetAsNaN("NaN", Double.NaN);
                }
                _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        } else if (isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return _handleApostropheValue();
        }
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonToken _handleApostropheValue() throws IOException, JsonParseException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    c = _decodeEscaped();
                } else if (c <= '\'') {
                    if (c == '\'') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return JsonToken.VALUE_STRING;
                    } else if (c < ' ') {
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            if (currentSegmentSize >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            int i2 = currentSegmentSize + 1;
            emptyAndGetCurrentSegment[currentSegmentSize] = c;
            currentSegmentSize = i2;
        }
    }

    private String _parseUnusualFieldName2(int i, int i2, int[] iArr) throws IOException, JsonParseException {
        this._textBuffer.resetWithShared(this._inputBuffer, i, this._inputPtr - i);
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        int length = iArr.length;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            if (c > length) {
                if (!Character.isJavaIdentifierPart(c)) {
                    break;
                }
            } else if (iArr[c] != 0) {
                break;
            }
            this._inputPtr++;
            i2 = (i2 * 33) + c;
            int i3 = currentSegmentSize + 1;
            currentSegment[currentSegmentSize] = c;
            if (i3 >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            } else {
                currentSegmentSize = i3;
            }
        }
        this._textBuffer.setCurrentLength(currentSegmentSize);
        TextBuffer textBuffer = this._textBuffer;
        return this._symbols.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i2);
    }

    /* access modifiers changed from: protected */
    public void _finishString() throws IOException, JsonParseException {
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        if (i < i2) {
            int[] inputCodeLatin1 = CharTypes.getInputCodeLatin1();
            int length = inputCodeLatin1.length;
            while (true) {
                char c = this._inputBuffer[i];
                if (c >= length || inputCodeLatin1[c] == 0) {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                } else if (c == '\"') {
                    this._textBuffer.resetWithShared(this._inputBuffer, this._inputPtr, i - this._inputPtr);
                    this._inputPtr = i + 1;
                    return;
                }
            }
        }
        this._textBuffer.resetWithCopy(this._inputBuffer, this._inputPtr, i - this._inputPtr);
        this._inputPtr = i;
        _finishString2();
    }

    /* access modifiers changed from: protected */
    public void _finishString2() throws IOException, JsonParseException {
        char[] currentSegment = this._textBuffer.getCurrentSegment();
        int currentSegmentSize = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    c = _decodeEscaped();
                } else if (c <= '\"') {
                    if (c == '\"') {
                        this._textBuffer.setCurrentLength(currentSegmentSize);
                        return;
                    } else if (c < ' ') {
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = this._textBuffer.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            int i2 = currentSegmentSize + 1;
            currentSegment[currentSegmentSize] = c;
            currentSegmentSize = i2;
        }
    }

    /* access modifiers changed from: protected */
    public void _skipString() throws IOException, JsonParseException {
        this._tokenIncomplete = false;
        int i = this._inputPtr;
        int i2 = this._inputEnd;
        char[] cArr = this._inputBuffer;
        while (true) {
            if (i >= i2) {
                this._inputPtr = i;
                if (!loadMore()) {
                    _reportInvalidEOF(": was expecting closing quote for a string value");
                }
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c <= '\\') {
                if (c == '\\') {
                    this._inputPtr = i3;
                    _decodeEscaped();
                    i = this._inputPtr;
                    i2 = this._inputEnd;
                } else if (c <= '\"') {
                    if (c == '\"') {
                        this._inputPtr = i3;
                        return;
                    } else if (c < ' ') {
                        this._inputPtr = i3;
                        _throwUnquotedSpace(c, "string value");
                    }
                }
            }
            i = i3;
        }
    }

    /* access modifiers changed from: protected */
    public void _skipCR() throws IOException {
        if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == 10) {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    /* access modifiers changed from: protected */
    public void _skipLF() throws IOException {
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    private int _skipWS() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                char[] cArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                char c = cArr[i];
                if (c > ' ') {
                    if (c != '/') {
                        return c;
                    }
                    _skipComment();
                } else if (c != ' ') {
                    if (c == 10) {
                        _skipLF();
                    } else if (c == 13) {
                        _skipCR();
                    } else if (c != 9) {
                        _throwInvalidSpace(c);
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected end-of-input within/between ");
                sb.append(this._parsingContext.getTypeDesc());
                sb.append(" entries");
                throw _constructError(sb.toString());
            }
        }
    }

    private int _skipWSOrEnd() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                char[] cArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                char c = cArr[i];
                if (c > ' ') {
                    if (c != '/') {
                        return c;
                    }
                    _skipComment();
                } else if (c != ' ') {
                    if (c == 10) {
                        _skipLF();
                    } else if (c == 13) {
                        _skipCR();
                    } else if (c != 9) {
                        _throwInvalidSpace(c);
                    }
                }
            } else {
                _handleEOF();
                return -1;
            }
        }
    }

    private void _skipComment() throws IOException, JsonParseException {
        if (!isEnabled(Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c = cArr[i];
        if (c == '/') {
            _skipCppComment();
        } else if (c == '*') {
            _skipCComment();
        } else {
            _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipCComment() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c <= '*') {
                if (c == '*') {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        break;
                    } else if (this._inputBuffer[this._inputPtr] == '/') {
                        this._inputPtr++;
                        return;
                    }
                } else if (c < ' ') {
                    if (c == 10) {
                        _skipLF();
                    } else if (c == 13) {
                        _skipCR();
                    } else if (c != 9) {
                        _throwInvalidSpace(c);
                    }
                }
            }
        }
        _reportInvalidEOF(" in a comment");
    }

    private void _skipCppComment() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                char[] cArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                char c = cArr[i];
                if (c < ' ') {
                    if (c == 10) {
                        _skipLF();
                        return;
                    } else if (c == 13) {
                        _skipCR();
                        return;
                    } else if (c != 9) {
                        _throwInvalidSpace(c);
                    }
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public char _decodeEscaped() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c = cArr[i];
        if (c == '\"' || c == '/' || c == '\\') {
            return c;
        }
        if (c == 'b') {
            return 8;
        }
        if (c == 'f') {
            return 12;
        }
        if (c == 'n') {
            return 10;
        }
        if (c == 'r') {
            return CharUtils.CR;
        }
        switch (c) {
            case 't':
                return 9;
            case 'u':
                int i2 = 0;
                for (int i3 = 0; i3 < 4; i3++) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in character escape sequence");
                    }
                    char[] cArr2 = this._inputBuffer;
                    int i4 = this._inputPtr;
                    this._inputPtr = i4 + 1;
                    char c2 = cArr2[i4];
                    int charToHex = CharTypes.charToHex(c2);
                    if (charToHex < 0) {
                        _reportUnexpectedChar(c2, "expected a hex-digit for character escape sequence");
                    }
                    i2 = (i2 << 4) | charToHex;
                }
                return (char) i2;
            default:
                return _handleUnrecognizedCharacterEscape(c);
        }
    }

    /* access modifiers changed from: protected */
    public void _matchToken(String str, int i) throws IOException, JsonParseException {
        int length = str.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidToken(str.substring(0, i));
            }
            if (this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i));
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if (this._inputPtr < this._inputEnd || loadMore()) {
            char c = this._inputBuffer[this._inputPtr];
            if (c >= '0' && c != ']' && c != '}' && Character.isJavaIdentifierPart(c)) {
                _reportInvalidToken(str.substring(0, i));
            }
        }
    }

    /* access modifiers changed from: protected */
    public byte[] _decodeBase64(Base64Variant base64Variant) throws IOException, JsonParseException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            if (c > ' ') {
                int decodeBase64Char = base64Variant.decodeBase64Char(c);
                if (decodeBase64Char < 0) {
                    if (c == '\"') {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, c, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                char c2 = cArr2[i2];
                int decodeBase64Char2 = base64Variant.decodeBase64Char(c2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, c2, 1);
                }
                int i3 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr3 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                char c3 = cArr3[i4];
                int decodeBase64Char3 = base64Variant.decodeBase64Char(c3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (c3 != '\"' || base64Variant.usesPadding()) {
                            decodeBase64Char3 = _decodeBase64Escape(base64Variant, c3, 2);
                        } else {
                            _getByteArrayBuilder.append(i3 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        char[] cArr4 = this._inputBuffer;
                        int i5 = this._inputPtr;
                        this._inputPtr = i5 + 1;
                        char c4 = cArr4[i5];
                        if (!base64Variant.usesPaddingChar(c4)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("expected padding character '");
                            sb.append(base64Variant.getPaddingChar());
                            sb.append("'");
                            throw reportInvalidBase64Char(base64Variant, c4, 3, sb.toString());
                        }
                        _getByteArrayBuilder.append(i3 >> 4);
                    }
                }
                int i6 = (i3 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                char[] cArr5 = this._inputBuffer;
                int i7 = this._inputPtr;
                this._inputPtr = i7 + 1;
                char c5 = cArr5[i7];
                int decodeBase64Char4 = base64Variant.decodeBase64Char(c5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (c5 != '\"' || base64Variant.usesPadding()) {
                            decodeBase64Char4 = _decodeBase64Escape(base64Variant, c5, 3);
                        } else {
                            _getByteArrayBuilder.appendTwoBytes(i6 >> 2);
                            return _getByteArrayBuilder.toByteArray();
                        }
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i6 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i6 << 6) | decodeBase64Char4);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidToken(String str) throws IOException, JsonParseException {
        _reportInvalidToken(str, "'null', 'true', 'false' or NaN");
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidToken(String str, String str2) throws IOException, JsonParseException {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            this._inputPtr++;
            sb.append(c);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Unrecognized token '");
        sb2.append(sb.toString());
        sb2.append("': was expecting ");
        _reportError(sb2.toString());
    }
}
