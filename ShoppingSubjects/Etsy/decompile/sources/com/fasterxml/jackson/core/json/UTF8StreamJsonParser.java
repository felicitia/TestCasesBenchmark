package com.fasterxml.jackson.core.json;

import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer;
import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.math3.dfp.Dfp;

public final class UTF8StreamJsonParser extends ParserBase {
    static final byte BYTE_LF = 10;
    private static final int[] sInputCodesLatin1 = CharTypes.getInputCodeLatin1();
    private static final int[] sInputCodesUtf8 = CharTypes.getInputCodeUtf8();
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer = new int[16];
    protected final BytesToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete = false;

    public UTF8StreamJsonParser(IOContext iOContext, int i, InputStream inputStream, ObjectCodec objectCodec, BytesToNameCanonicalizer bytesToNameCanonicalizer, byte[] bArr, int i2, int i3, boolean z) {
        super(iOContext, i);
        this._inputStream = inputStream;
        this._objectCodec = objectCodec;
        this._symbols = bytesToNameCanonicalizer;
        this._inputBuffer = bArr;
        this._inputPtr = i2;
        this._inputEnd = i3;
        this._bufferRecyclable = z;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public void setCodec(ObjectCodec objectCodec) {
        this._objectCodec = objectCodec;
    }

    public int releaseBuffered(OutputStream outputStream) throws IOException {
        int i = this._inputEnd - this._inputPtr;
        if (i < 1) {
            return 0;
        }
        outputStream.write(this._inputBuffer, this._inputPtr, i);
        return i;
    }

    public Object getInputSource() {
        return this._inputStream;
    }

    /* access modifiers changed from: protected */
    public boolean loadMore() throws IOException {
        this._currInputProcessed += (long) this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        if (this._inputStream != null) {
            int read = this._inputStream.read(this._inputBuffer, 0, this._inputBuffer.length);
            if (read > 0) {
                this._inputPtr = 0;
                this._inputEnd = read;
                return true;
            }
            _closeInput();
            if (read == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("InputStream.read() returned 0 characters when trying to read ");
                sb.append(this._inputBuffer.length);
                sb.append(" bytes");
                throw new IOException(sb.toString());
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean _loadToHaveAtLeast(int i) throws IOException {
        if (this._inputStream == null) {
            return false;
        }
        int i2 = this._inputEnd - this._inputPtr;
        if (i2 <= 0 || this._inputPtr <= 0) {
            this._inputEnd = 0;
        } else {
            this._currInputProcessed += (long) this._inputPtr;
            this._currInputRowStart -= this._inputPtr;
            System.arraycopy(this._inputBuffer, this._inputPtr, this._inputBuffer, 0, i2);
            this._inputEnd = i2;
        }
        this._inputPtr = 0;
        while (this._inputEnd < i) {
            int read = this._inputStream.read(this._inputBuffer, this._inputEnd, this._inputBuffer.length - this._inputEnd);
            if (read < 1) {
                _closeInput();
                if (read != 0) {
                    return false;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("InputStream.read() returned 0 characters when trying to read ");
                sb.append(i2);
                sb.append(" bytes");
                throw new IOException(sb.toString());
            }
            this._inputEnd += read;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        if (this._bufferRecyclable) {
            byte[] bArr = this._inputBuffer;
            if (bArr != null) {
                this._inputBuffer = null;
                this._ioContext.releaseReadIOBuffer(bArr);
            }
        }
    }

    public String getText() throws IOException, JsonParseException {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return _getText2(this._currToken);
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
            int[] r0 = com.fasterxml.jackson.core.json.UTF8StreamJsonParser.AnonymousClass1.a
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.getTextOffset():int");
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
            byte[] bArr2 = this._inputBuffer;
            int i4 = this._inputPtr;
            this._inputPtr = i4 + 1;
            byte b = bArr2[i4] & 255;
            if (b > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char((int) b);
                if (decodeBase64Char < 0) {
                    if (b == 34) {
                        break;
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, (int) b, 0);
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
                byte[] bArr3 = this._inputBuffer;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                byte b2 = bArr3[i5] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char((int) b2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, (int) b2, 1);
                }
                int i6 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr4 = this._inputBuffer;
                int i7 = this._inputPtr;
                this._inputPtr = i7 + 1;
                byte b3 = bArr4[i7] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char((int) b3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (b3 == 34 && !base64Variant.usesPadding()) {
                            int i8 = i2 + 1;
                            bArr[i2] = (byte) (i6 >> 4);
                            i2 = i8;
                            break;
                        }
                        decodeBase64Char3 = _decodeBase64Escape(base64Variant, (int) b3, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr5 = this._inputBuffer;
                        int i9 = this._inputPtr;
                        this._inputPtr = i9 + 1;
                        byte b4 = bArr5[i9] & 255;
                        if (!base64Variant.usesPaddingChar((int) b4)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("expected padding character '");
                            sb.append(base64Variant.getPaddingChar());
                            sb.append("'");
                            throw reportInvalidBase64Char(base64Variant, b4, 3, sb.toString());
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
                byte[] bArr6 = this._inputBuffer;
                int i11 = this._inputPtr;
                this._inputPtr = i11 + 1;
                byte b5 = bArr6[i11] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char((int) b5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (b5 == 34 && !base64Variant.usesPadding()) {
                            int i12 = i10 >> 2;
                            int i13 = i2 + 1;
                            bArr[i2] = (byte) (i12 >> 8);
                            i2 = i13 + 1;
                            bArr[i13] = (byte) i12;
                            break;
                        }
                        decodeBase64Char4 = _decodeBase64Escape(base64Variant, (int) b5, 3);
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
            if (!this._parsingContext.inObject()) {
                return _nextTokenNotInObject(_skipWSOrEnd);
            }
            this._parsingContext.setCurrentName(_parseFieldName(_skipWSOrEnd).getName());
            this._currToken = JsonToken.FIELD_NAME;
            int _skipWS = _skipWS();
            if (_skipWS != 58) {
                _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
            }
            int _skipWS2 = _skipWS();
            if (_skipWS2 == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return this._currToken;
            }
            if (_skipWS2 != 45) {
                if (_skipWS2 != 91) {
                    if (_skipWS2 != 93) {
                        if (_skipWS2 == 102) {
                            _matchToken("false", 1);
                            jsonToken = JsonToken.VALUE_FALSE;
                        } else if (_skipWS2 != 110) {
                            if (_skipWS2 != 116) {
                                if (_skipWS2 != 123) {
                                    if (_skipWS2 != 125) {
                                        switch (_skipWS2) {
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
                                                jsonToken = _handleUnexpectedValue(_skipWS2);
                                                break;
                                        }
                                    }
                                } else {
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
                    _reportUnexpectedChar(_skipWS2, "expected a value");
                    _matchToken("true", 1);
                    jsonToken = JsonToken.VALUE_TRUE;
                } else {
                    jsonToken = JsonToken.START_ARRAY;
                }
                this._nextToken = jsonToken;
                return this._currToken;
            }
            jsonToken = parseNumberText(_skipWS2);
            this._nextToken = jsonToken;
            return this._currToken;
        }
    }

    private JsonToken _nextTokenNotInObject(int i) throws IOException, JsonParseException {
        if (i == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        }
        if (i != 45) {
            if (i != 91) {
                if (i != 93) {
                    if (i == 102) {
                        _matchToken("false", 1);
                        JsonToken jsonToken2 = JsonToken.VALUE_FALSE;
                        this._currToken = jsonToken2;
                        return jsonToken2;
                    } else if (i != 110) {
                        if (i != 116) {
                            if (i == 123) {
                                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                                JsonToken jsonToken3 = JsonToken.START_OBJECT;
                                this._currToken = jsonToken3;
                                return jsonToken3;
                            } else if (i != 125) {
                                switch (i) {
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
                                        JsonToken _handleUnexpectedValue = _handleUnexpectedValue(i);
                                        this._currToken = _handleUnexpectedValue;
                                        return _handleUnexpectedValue;
                                }
                            }
                        }
                        _matchToken("true", 1);
                        JsonToken jsonToken4 = JsonToken.VALUE_TRUE;
                        this._currToken = jsonToken4;
                        return jsonToken4;
                    } else {
                        _matchToken("null", 1);
                        JsonToken jsonToken5 = JsonToken.VALUE_NULL;
                        this._currToken = jsonToken5;
                        return jsonToken5;
                    }
                }
                _reportUnexpectedChar(i, "expected a value");
                _matchToken("true", 1);
                JsonToken jsonToken42 = JsonToken.VALUE_TRUE;
                this._currToken = jsonToken42;
                return jsonToken42;
            }
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
            JsonToken jsonToken6 = JsonToken.START_ARRAY;
            this._currToken = jsonToken6;
            return jsonToken6;
        }
        JsonToken parseNumberText = parseNumberText(i);
        this._currToken = parseNumberText;
        return parseNumberText;
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

    public void close() throws IOException {
        super.close();
        this._symbols.release();
    }

    public boolean nextFieldName(SerializableString serializableString) throws IOException, JsonParseException {
        int i = 0;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return false;
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this._currToken = null;
            return false;
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
            this._currToken = JsonToken.END_ARRAY;
            return false;
        } else if (_skipWSOrEnd == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(_skipWSOrEnd, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            this._currToken = JsonToken.END_OBJECT;
            return false;
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
            if (!this._parsingContext.inObject()) {
                _nextTokenNotInObject(_skipWSOrEnd);
                return false;
            }
            if (_skipWSOrEnd == 34) {
                byte[] asQuotedUTF8 = serializableString.asQuotedUTF8();
                int length = asQuotedUTF8.length;
                if (this._inputPtr + length < this._inputEnd) {
                    int i2 = this._inputPtr + length;
                    if (this._inputBuffer[i2] == 34) {
                        int i3 = this._inputPtr;
                        while (i != length) {
                            if (asQuotedUTF8[i] == this._inputBuffer[i3 + i]) {
                                i++;
                            }
                        }
                        this._inputPtr = i2 + 1;
                        this._parsingContext.setCurrentName(serializableString.getValue());
                        this._currToken = JsonToken.FIELD_NAME;
                        _isNextTokenNameYes();
                        return true;
                    }
                }
            }
            return _isNextTokenNameMaybe(_skipWSOrEnd, serializableString);
        }
    }

    private void _isNextTokenNameYes() throws IOException, JsonParseException {
        int i;
        if (this._inputPtr >= this._inputEnd - 1 || this._inputBuffer[this._inputPtr] != 58) {
            i = _skipColon();
        } else {
            byte[] bArr = this._inputBuffer;
            int i2 = this._inputPtr + 1;
            this._inputPtr = i2;
            byte b = bArr[i2];
            this._inputPtr++;
            if (b == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return;
            } else if (b == 123) {
                this._nextToken = JsonToken.START_OBJECT;
                return;
            } else if (b == 91) {
                this._nextToken = JsonToken.START_ARRAY;
                return;
            } else {
                i = b & 255;
                if (i <= 32 || i == 47) {
                    this._inputPtr--;
                    i = _skipWS();
                }
            }
        }
        if (i != 34) {
            if (i != 45) {
                if (i != 91) {
                    if (i != 93) {
                        if (i == 102) {
                            _matchToken("false", 1);
                            this._nextToken = JsonToken.VALUE_FALSE;
                            return;
                        } else if (i != 110) {
                            if (i != 116) {
                                if (i == 123) {
                                    this._nextToken = JsonToken.START_OBJECT;
                                    return;
                                } else if (i != 125) {
                                    switch (i) {
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
                                            this._nextToken = _handleUnexpectedValue(i);
                                            return;
                                    }
                                }
                            }
                            _matchToken("true", 1);
                            this._nextToken = JsonToken.VALUE_TRUE;
                            return;
                        } else {
                            _matchToken("null", 1);
                            this._nextToken = JsonToken.VALUE_NULL;
                            return;
                        }
                    }
                    _reportUnexpectedChar(i, "expected a value");
                    _matchToken("true", 1);
                    this._nextToken = JsonToken.VALUE_TRUE;
                    return;
                }
                this._nextToken = JsonToken.START_ARRAY;
                return;
            }
            this._nextToken = parseNumberText(i);
            return;
        }
        this._tokenIncomplete = true;
        this._nextToken = JsonToken.VALUE_STRING;
    }

    private boolean _isNextTokenNameMaybe(int i, SerializableString serializableString) throws IOException, JsonParseException {
        JsonToken jsonToken;
        String name = _parseFieldName(i).getName();
        this._parsingContext.setCurrentName(name);
        boolean equals = name.equals(serializableString.getValue());
        this._currToken = JsonToken.FIELD_NAME;
        int _skipWS = _skipWS();
        if (_skipWS != 58) {
            _reportUnexpectedChar(_skipWS, "was expecting a colon to separate field name and value");
        }
        int _skipWS2 = _skipWS();
        if (_skipWS2 == 34) {
            this._tokenIncomplete = true;
            this._nextToken = JsonToken.VALUE_STRING;
            return equals;
        }
        if (_skipWS2 != 45) {
            if (_skipWS2 != 91) {
                if (_skipWS2 != 93) {
                    if (_skipWS2 == 102) {
                        _matchToken("false", 1);
                        jsonToken = JsonToken.VALUE_FALSE;
                    } else if (_skipWS2 != 110) {
                        if (_skipWS2 != 116) {
                            if (_skipWS2 != 123) {
                                if (_skipWS2 != 125) {
                                    switch (_skipWS2) {
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
                                            jsonToken = _handleUnexpectedValue(_skipWS2);
                                            break;
                                    }
                                }
                            } else {
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
                _reportUnexpectedChar(_skipWS2, "expected a value");
                _matchToken("true", 1);
                jsonToken = JsonToken.VALUE_TRUE;
            } else {
                jsonToken = JsonToken.START_ARRAY;
            }
            this._nextToken = jsonToken;
            return equals;
        }
        jsonToken = parseNumberText(_skipWS2);
        this._nextToken = jsonToken;
        return equals;
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

    /* access modifiers changed from: protected */
    public JsonToken parseNumberText(int i) throws IOException, JsonParseException {
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i2 = 0;
        boolean z = i == 45;
        if (z) {
            emptyAndGetCurrentSegment[0] = '-';
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            i = bArr[i3] & 255;
            if (i < 48 || i > 57) {
                return _handleInvalidNumberStart(i, true);
            }
            i2 = 1;
        }
        if (i == 48) {
            i = _verifyNoLeadingZeroes();
        }
        int i4 = i2 + 1;
        emptyAndGetCurrentSegment[i2] = (char) i;
        int length = this._inputPtr + emptyAndGetCurrentSegment.length;
        if (length > this._inputEnd) {
            length = this._inputEnd;
        }
        int i5 = 1;
        while (this._inputPtr < length) {
            byte[] bArr2 = this._inputBuffer;
            int i6 = this._inputPtr;
            this._inputPtr = i6 + 1;
            byte b = bArr2[i6] & 255;
            if (b >= 48 && b <= 57) {
                i5++;
                int i7 = i4 + 1;
                emptyAndGetCurrentSegment[i4] = (char) b;
                i4 = i7;
            } else if (b == 46 || b == 101 || b == 69) {
                return _parseFloatText(emptyAndGetCurrentSegment, i4, b, z, i5);
            } else {
                this._inputPtr--;
                this._textBuffer.setCurrentLength(i4);
                return resetInt(z, i5);
            }
        }
        return _parserNumber2(emptyAndGetCurrentSegment, i4, z, i5);
    }

    private JsonToken _parserNumber2(char[] cArr, int i, boolean z, int i2) throws IOException, JsonParseException {
        byte b;
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                b = bArr[i5] & 255;
                if (b <= 57 && b >= 48) {
                    if (i3 >= cArr2.length) {
                        i3 = 0;
                        cArr2 = this._textBuffer.finishCurrentSegment();
                    }
                    int i6 = i3 + 1;
                    cArr2[i3] = (char) b;
                    i4++;
                    i3 = i6;
                }
            } else {
                this._textBuffer.setCurrentLength(i3);
                return resetInt(z, i4);
            }
        }
        if (b == 46 || b == 101 || b == 69) {
            return _parseFloatText(cArr2, i3, b, z, i4);
        }
        this._inputPtr--;
        this._textBuffer.setCurrentLength(i3);
        return resetInt(z, i4);
    }

    private int _verifyNoLeadingZeroes() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return 48;
        }
        byte b = this._inputBuffer[this._inputPtr] & 255;
        if (b < 48 || b > 57) {
            return 48;
        }
        if (!isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (b == 48) {
            do {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    break;
                }
                b = this._inputBuffer[this._inputPtr] & 255;
                if (b < 48 || b > 57) {
                    return 48;
                }
                this._inputPtr++;
            } while (b == 48);
        }
        return b;
    }

    private JsonToken _parseFloatText(char[] cArr, int i, int i2, boolean z, int i3) throws IOException, JsonParseException {
        boolean z2;
        int i4;
        int i5;
        int i6 = 0;
        if (i2 == 46) {
            int i7 = i + 1;
            cArr[i] = (char) i2;
            i = i7;
            byte b = i2;
            char[] cArr2 = cArr;
            int i8 = 0;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    z2 = true;
                    break;
                }
                byte[] bArr = this._inputBuffer;
                int i9 = this._inputPtr;
                this._inputPtr = i9 + 1;
                b = bArr[i9] & 255;
                if (b < 48 || b > 57) {
                    z2 = false;
                } else {
                    i8++;
                    if (i >= cArr2.length) {
                        cArr2 = this._textBuffer.finishCurrentSegment();
                        i = 0;
                    }
                    int i10 = i + 1;
                    cArr2[i] = (char) b;
                    i = i10;
                }
            }
            if (i8 == 0) {
                reportUnexpectedNumberChar(b, "Decimal point not followed by a digit");
            }
            int i11 = b;
            i4 = i8;
            cArr = cArr2;
            i2 = i11;
        } else {
            i4 = 0;
            z2 = false;
        }
        if (i2 == 101 || i2 == 69) {
            if (i >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int i12 = i + 1;
            cArr[i] = (char) i2;
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i13 = this._inputPtr;
            this._inputPtr = i13 + 1;
            byte b2 = bArr2[i13] & 255;
            if (b2 == 45 || b2 == 43) {
                if (i12 >= cArr.length) {
                    cArr = this._textBuffer.finishCurrentSegment();
                    i12 = 0;
                }
                i5 = i12 + 1;
                cArr[i12] = (char) b2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i14 = this._inputPtr;
                this._inputPtr = i14 + 1;
                b2 = bArr3[i14] & 255;
            } else {
                i5 = i12;
            }
            char[] cArr3 = cArr;
            int i15 = 0;
            while (true) {
                if (b2 <= 57 && b2 >= 48) {
                    i15++;
                    if (i5 >= cArr3.length) {
                        cArr3 = this._textBuffer.finishCurrentSegment();
                        i5 = 0;
                    }
                    int i16 = i5 + 1;
                    cArr3[i5] = (char) b2;
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        i6 = i15;
                        z2 = true;
                        i5 = i16;
                        break;
                    }
                    byte[] bArr4 = this._inputBuffer;
                    int i17 = this._inputPtr;
                    this._inputPtr = i17 + 1;
                    b2 = bArr4[i17] & 255;
                    i5 = i16;
                } else {
                    i6 = i15;
                }
            }
            i6 = i15;
            if (i6 == 0) {
                reportUnexpectedNumberChar(b2, "Exponent indicator not followed by a digit");
            }
            i = i5;
        }
        if (!z2) {
            this._inputPtr--;
        }
        this._textBuffer.setCurrentLength(i);
        return resetFloat(z, i3, i4, i6);
    }

    /* access modifiers changed from: protected */
    public Name _parseFieldName(int i) throws IOException, JsonParseException {
        if (i != 34) {
            return _handleUnusualFieldName(i);
        }
        if (this._inputPtr + 9 > this._inputEnd) {
            return slowParseFieldName();
        }
        byte[] bArr = this._inputBuffer;
        int[] iArr = sInputCodesLatin1;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2] & 255;
        if (iArr[b] == 0) {
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b2 = bArr[i3] & 255;
            if (iArr[b2] == 0) {
                byte b3 = (b << 8) | b2;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                byte b4 = bArr[i4] & 255;
                if (iArr[b4] == 0) {
                    byte b5 = (b3 << 8) | b4;
                    int i5 = this._inputPtr;
                    this._inputPtr = i5 + 1;
                    byte b6 = bArr[i5] & 255;
                    if (iArr[b6] == 0) {
                        byte b7 = (b5 << 8) | b6;
                        int i6 = this._inputPtr;
                        this._inputPtr = i6 + 1;
                        byte b8 = bArr[i6] & 255;
                        if (iArr[b8] == 0) {
                            this._quad1 = b7;
                            return parseMediumFieldName(b8, iArr);
                        } else if (b8 == 34) {
                            return findName(b7, 4);
                        } else {
                            return parseFieldName(b7, b8, 4);
                        }
                    } else if (b6 == 34) {
                        return findName(b5, 3);
                    } else {
                        return parseFieldName(b5, b6, 3);
                    }
                } else if (b4 == 34) {
                    return findName(b3, 2);
                } else {
                    return parseFieldName(b3, b4, 2);
                }
            } else if (b2 == 34) {
                return findName(b, 1);
            } else {
                return parseFieldName(b, b2, 1);
            }
        } else if (b == 34) {
            return BytesToNameCanonicalizer.getEmptyName();
        } else {
            return parseFieldName(0, b, 0);
        }
    }

    /* access modifiers changed from: protected */
    public Name parseMediumFieldName(int i, int[] iArr) throws IOException, JsonParseException {
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2] & 255;
        if (iArr[b] == 0) {
            byte b2 = (i << 8) | b;
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b3 = bArr2[i3] & 255;
            if (iArr[b3] == 0) {
                byte b4 = (b2 << 8) | b3;
                byte[] bArr3 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                byte b5 = bArr3[i4] & 255;
                if (iArr[b5] == 0) {
                    int i5 = (b4 << 8) | b5;
                    byte[] bArr4 = this._inputBuffer;
                    int i6 = this._inputPtr;
                    this._inputPtr = i6 + 1;
                    byte b6 = bArr4[i6] & 255;
                    if (iArr[b6] == 0) {
                        this._quadBuffer[0] = this._quad1;
                        this._quadBuffer[1] = i5;
                        return parseLongFieldName(b6);
                    } else if (b6 == 34) {
                        return findName(this._quad1, i5, 4);
                    } else {
                        return parseFieldName(this._quad1, i5, b6, 4);
                    }
                } else if (b5 == 34) {
                    return findName(this._quad1, b4, 3);
                } else {
                    return parseFieldName(this._quad1, b4, b5, 3);
                }
            } else if (b3 == 34) {
                return findName(this._quad1, b2, 2);
            } else {
                return parseFieldName(this._quad1, b2, b3, 2);
            }
        } else if (b == 34) {
            return findName(this._quad1, i, 1);
        } else {
            return parseFieldName(this._quad1, i, b, 1);
        }
    }

    /* access modifiers changed from: protected */
    public Name parseLongFieldName(int i) throws IOException, JsonParseException {
        int[] iArr = sInputCodesLatin1;
        int i2 = 2;
        while (this._inputEnd - this._inputPtr >= 4) {
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            byte b = bArr[i3] & 255;
            if (iArr[b] == 0) {
                byte b2 = (i << 8) | b;
                byte[] bArr2 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                byte b3 = bArr2[i4] & 255;
                if (iArr[b3] == 0) {
                    byte b4 = (b2 << 8) | b3;
                    byte[] bArr3 = this._inputBuffer;
                    int i5 = this._inputPtr;
                    this._inputPtr = i5 + 1;
                    byte b5 = bArr3[i5] & 255;
                    if (iArr[b5] == 0) {
                        int i6 = (b4 << 8) | b5;
                        byte[] bArr4 = this._inputBuffer;
                        int i7 = this._inputPtr;
                        this._inputPtr = i7 + 1;
                        byte b6 = bArr4[i7] & 255;
                        if (iArr[b6] == 0) {
                            if (i2 >= this._quadBuffer.length) {
                                this._quadBuffer = growArrayBy(this._quadBuffer, i2);
                            }
                            int i8 = i2 + 1;
                            this._quadBuffer[i2] = i6;
                            i2 = i8;
                            i = b6;
                        } else if (b6 == 34) {
                            return findName(this._quadBuffer, i2, i6, 4);
                        } else {
                            return parseEscapedFieldName(this._quadBuffer, i2, i6, b6, 4);
                        }
                    } else if (b5 == 34) {
                        return findName(this._quadBuffer, i2, b4, 3);
                    } else {
                        return parseEscapedFieldName(this._quadBuffer, i2, b4, b5, 3);
                    }
                } else if (b3 == 34) {
                    return findName(this._quadBuffer, i2, b2, 2);
                } else {
                    return parseEscapedFieldName(this._quadBuffer, i2, b2, b3, 2);
                }
            } else if (b == 34) {
                return findName(this._quadBuffer, i2, i, 1);
            } else {
                return parseEscapedFieldName(this._quadBuffer, i2, i, b, 1);
            }
        }
        return parseEscapedFieldName(this._quadBuffer, i2, 0, i, 0);
    }

    /* access modifiers changed from: protected */
    public Name slowParseFieldName() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i] & 255;
        if (b == 34) {
            return BytesToNameCanonicalizer.getEmptyName();
        }
        return parseEscapedFieldName(this._quadBuffer, 0, 0, b, 0);
    }

    private Name parseFieldName(int i, int i2, int i3) throws IOException, JsonParseException {
        return parseEscapedFieldName(this._quadBuffer, 0, i, i2, i3);
    }

    private Name parseFieldName(int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        this._quadBuffer[0] = i;
        return parseEscapedFieldName(this._quadBuffer, 1, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public Name parseEscapedFieldName(int[] iArr, int i, int i2, int i3, int i4) throws IOException, JsonParseException {
        int[] iArr2 = sInputCodesLatin1;
        while (true) {
            if (iArr2[i3] != 0) {
                if (i3 == 34) {
                    break;
                }
                if (i3 != 92) {
                    _throwUnquotedSpace(i3, ResponseConstants.NAME);
                } else {
                    i3 = _decodeEscaped();
                }
                if (i3 > 127) {
                    if (r12 >= 4) {
                        if (i >= iArr.length) {
                            iArr = growArrayBy(iArr, iArr.length);
                            this._quadBuffer = iArr;
                        }
                        int i5 = i + 1;
                        iArr[i] = r10;
                        i = i5;
                        r10 = 0;
                        r12 = 0;
                    }
                    if (i3 < 2048) {
                        r10 = (r10 << 8) | 192 | (i3 >> 6);
                        r12++;
                    } else {
                        int i6 = (r10 << 8) | 224 | (i3 >> 12);
                        int i7 = r12 + 1;
                        if (i7 >= 4) {
                            if (i >= iArr.length) {
                                iArr = growArrayBy(iArr, iArr.length);
                                this._quadBuffer = iArr;
                            }
                            int i8 = i + 1;
                            iArr[i] = i6;
                            i = i8;
                            i6 = 0;
                            i7 = 0;
                        }
                        r10 = (i6 << 8) | ((i3 >> 6) & 63) | 128;
                        r12 = i7 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (r12 < 4) {
                i4 = r12 + 1;
                i2 = (r10 << 8) | i3;
            } else {
                if (i >= iArr.length) {
                    iArr = growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                int i9 = i + 1;
                iArr[i] = r10;
                i2 = i3;
                i = i9;
                i4 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            byte[] bArr = this._inputBuffer;
            int i10 = this._inputPtr;
            this._inputPtr = i10 + 1;
            i3 = bArr[i10] & 255;
        }
        if (r12 > 0) {
            if (i >= iArr.length) {
                iArr = growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            int i11 = i + 1;
            iArr[i] = r10;
            i = i11;
        }
        Name findName = this._symbols.findName(iArr, i);
        if (findName == null) {
            return addName(iArr, i, r12);
        }
        return findName;
    }

    /* access modifiers changed from: protected */
    public Name _handleUnusualFieldName(int i) throws IOException, JsonParseException {
        int i2;
        if (i == 39 && isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        if (!isEnabled(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        if (inputCodeUtf8JsNames[i] != 0) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int i3 = 0;
        int[] iArr = this._quadBuffer;
        int i4 = 0;
        byte b = i;
        int i5 = 0;
        while (true) {
            if (i3 < 4) {
                i3++;
                i5 = (i5 << 8) | b;
            } else {
                if (i4 >= iArr.length) {
                    iArr = growArrayBy(iArr, iArr.length);
                    this._quadBuffer = iArr;
                }
                int i6 = i4 + 1;
                iArr[i4] = i5;
                i5 = b;
                i4 = i6;
                i3 = 1;
            }
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(" in field name");
            }
            b = this._inputBuffer[this._inputPtr] & 255;
            if (inputCodeUtf8JsNames[b] != 0) {
                break;
            }
            this._inputPtr++;
        }
        if (i3 > 0) {
            if (i4 >= iArr.length) {
                iArr = growArrayBy(iArr, iArr.length);
                this._quadBuffer = iArr;
            }
            i2 = i4 + 1;
            iArr[i4] = i5;
        } else {
            i2 = i4;
        }
        Name findName = this._symbols.findName(iArr, i2);
        if (findName == null) {
            findName = addName(iArr, i2, i3);
        }
        return findName;
    }

    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r7v3 */
    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: type inference failed for: r0v20 */
    /* JADX WARNING: type inference failed for: r7v18 */
    /* JADX WARNING: type inference failed for: r0v25 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r7v3
      assigns: []
      uses: []
      mth insns count: 116
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.sym.Name _parseApostropheFieldName() throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r13 = this;
            int r0 = r13._inputPtr
            int r1 = r13._inputEnd
            if (r0 < r1) goto L_0x0011
            boolean r0 = r13.loadMore()
            if (r0 != 0) goto L_0x0011
            java.lang.String r0 = ": was expecting closing ''' for name"
            r13._reportInvalidEOF(r0)
        L_0x0011:
            byte[] r0 = r13._inputBuffer
            int r1 = r13._inputPtr
            int r2 = r1 + 1
            r13._inputPtr = r2
            byte r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 39
            if (r0 != r1) goto L_0x0026
            com.fasterxml.jackson.core.sym.Name r0 = com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer.getEmptyName()
            return r0
        L_0x0026:
            int[] r2 = r13._quadBuffer
            int[] r3 = sInputCodesLatin1
            r4 = 0
            r6 = r2
            r2 = r4
            r5 = r2
            r7 = r5
        L_0x002f:
            if (r0 != r1) goto L_0x0050
            if (r2 <= 0) goto L_0x0042
            int r0 = r6.length
            if (r5 < r0) goto L_0x003d
            int r0 = r6.length
            int[] r6 = growArrayBy(r6, r0)
            r13._quadBuffer = r6
        L_0x003d:
            int r0 = r5 + 1
            r6[r5] = r7
            goto L_0x0043
        L_0x0042:
            r0 = r5
        L_0x0043:
            com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer r1 = r13._symbols
            com.fasterxml.jackson.core.sym.Name r1 = r1.findName(r6, r0)
            if (r1 != 0) goto L_0x004f
            com.fasterxml.jackson.core.sym.Name r1 = r13.addName(r6, r0, r2)
        L_0x004f:
            return r1
        L_0x0050:
            r8 = 34
            r9 = 4
            r10 = 1
            if (r0 == r8) goto L_0x00b9
            r8 = r3[r0]
            if (r8 == 0) goto L_0x00b9
            r8 = 92
            if (r0 == r8) goto L_0x0064
            java.lang.String r8 = "name"
            r13._throwUnquotedSpace(r0, r8)
            goto L_0x0068
        L_0x0064:
            char r0 = r13._decodeEscaped()
        L_0x0068:
            r8 = 127(0x7f, float:1.78E-43)
            if (r0 <= r8) goto L_0x00b9
            if (r2 < r9) goto L_0x007f
            int r2 = r6.length
            if (r5 < r2) goto L_0x0078
            int r2 = r6.length
            int[] r6 = growArrayBy(r6, r2)
            r13._quadBuffer = r6
        L_0x0078:
            int r2 = r5 + 1
            r6[r5] = r7
            r5 = r2
            r2 = r4
            r7 = r2
        L_0x007f:
            r8 = 2048(0x800, float:2.87E-42)
            r11 = 128(0x80, float:1.794E-43)
            if (r0 >= r8) goto L_0x0090
            int r7 = r7 << 8
            r8 = 192(0xc0, float:2.69E-43)
            int r12 = r0 >> 6
            r8 = r8 | r12
            r7 = r7 | r8
            int r2 = r2 + 1
            goto L_0x00b6
        L_0x0090:
            int r7 = r7 << 8
            r8 = 224(0xe0, float:3.14E-43)
            int r12 = r0 >> 12
            r8 = r8 | r12
            r7 = r7 | r8
            int r2 = r2 + 1
            if (r2 < r9) goto L_0x00ad
            int r2 = r6.length
            if (r5 < r2) goto L_0x00a6
            int r2 = r6.length
            int[] r6 = growArrayBy(r6, r2)
            r13._quadBuffer = r6
        L_0x00a6:
            int r2 = r5 + 1
            r6[r5] = r7
            r5 = r2
            r2 = r4
            r7 = r2
        L_0x00ad:
            int r7 = r7 << 8
            int r8 = r0 >> 6
            r8 = r8 & 63
            r8 = r8 | r11
            r7 = r7 | r8
            int r2 = r2 + r10
        L_0x00b6:
            r0 = r0 & 63
            r0 = r0 | r11
        L_0x00b9:
            if (r2 >= r9) goto L_0x00c2
            int r2 = r2 + 1
            int r7 = r7 << 8
            r0 = r0 | r7
            r7 = r0
            goto L_0x00d3
        L_0x00c2:
            int r2 = r6.length
            if (r5 < r2) goto L_0x00cc
            int r2 = r6.length
            int[] r6 = growArrayBy(r6, r2)
            r13._quadBuffer = r6
        L_0x00cc:
            int r2 = r5 + 1
            r6[r5] = r7
            r7 = r0
            r5 = r2
            r2 = r10
        L_0x00d3:
            int r0 = r13._inputPtr
            int r8 = r13._inputEnd
            if (r0 < r8) goto L_0x00e4
            boolean r0 = r13.loadMore()
            if (r0 != 0) goto L_0x00e4
            java.lang.String r0 = " in field name"
            r13._reportInvalidEOF(r0)
        L_0x00e4:
            byte[] r0 = r13._inputBuffer
            int r8 = r13._inputPtr
            int r9 = r8 + 1
            r13._inputPtr = r9
            byte r0 = r0[r8]
            r0 = r0 & 255(0xff, float:3.57E-43)
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._parseApostropheFieldName():com.fasterxml.jackson.core.sym.Name");
    }

    private Name findName(int i, int i2) throws JsonParseException {
        Name findName = this._symbols.findName(i);
        if (findName != null) {
            return findName;
        }
        this._quadBuffer[0] = i;
        return addName(this._quadBuffer, 1, i2);
    }

    private Name findName(int i, int i2, int i3) throws JsonParseException {
        Name findName = this._symbols.findName(i, i2);
        if (findName != null) {
            return findName;
        }
        this._quadBuffer[0] = i;
        this._quadBuffer[1] = i2;
        return addName(this._quadBuffer, 2, i3);
    }

    private Name findName(int[] iArr, int i, int i2, int i3) throws JsonParseException {
        if (i >= iArr.length) {
            iArr = growArrayBy(iArr, iArr.length);
            this._quadBuffer = iArr;
        }
        int i4 = i + 1;
        iArr[i] = i2;
        Name findName = this._symbols.findName(iArr, i4);
        return findName == null ? addName(iArr, i4, i3) : findName;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.fasterxml.jackson.core.sym.Name addName(int[] r19, int r20, int r21) throws com.fasterxml.jackson.core.JsonParseException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            int r4 = r2 << 2
            r5 = 4
            int r4 = r4 - r5
            int r4 = r4 + r3
            r7 = 3
            if (r3 >= r5) goto L_0x001c
            int r8 = r2 + -1
            r9 = r1[r8]
            int r10 = 4 - r3
            int r10 = r10 << r7
            int r10 = r9 << r10
            r1[r8] = r10
            goto L_0x001d
        L_0x001c:
            r9 = 0
        L_0x001d:
            com.fasterxml.jackson.core.util.TextBuffer r8 = r0._textBuffer
            char[] r8 = r8.emptyAndGetCurrentSegment()
            r10 = r8
            r8 = 0
            r11 = 0
        L_0x0026:
            if (r8 >= r4) goto L_0x00f7
            int r12 = r8 >> 2
            r12 = r1[r12]
            r13 = r8 & 3
            int r13 = 3 - r13
            int r13 = r13 << r7
            int r12 = r12 >> r13
            r12 = r12 & 255(0xff, float:3.57E-43)
            int r8 = r8 + 1
            r13 = 127(0x7f, float:1.78E-43)
            if (r12 <= r13) goto L_0x00e4
            r13 = r12 & 224(0xe0, float:3.14E-43)
            r14 = 192(0xc0, float:2.69E-43)
            r5 = 1
            if (r13 != r14) goto L_0x0046
            r12 = r12 & 31
            r13 = r12
            r12 = r5
            goto L_0x0061
        L_0x0046:
            r13 = r12 & 240(0xf0, float:3.36E-43)
            r14 = 224(0xe0, float:3.14E-43)
            if (r13 != r14) goto L_0x0051
            r12 = r12 & 15
            r13 = r12
            r12 = 2
            goto L_0x0061
        L_0x0051:
            r13 = r12 & 248(0xf8, float:3.48E-43)
            r14 = 240(0xf0, float:3.36E-43)
            if (r13 != r14) goto L_0x005c
            r12 = r12 & 7
            r13 = r12
            r12 = r7
            goto L_0x0061
        L_0x005c:
            r0._reportInvalidInitial(r12)
            r12 = r5
            r13 = r12
        L_0x0061:
            int r14 = r8 + r12
            if (r14 <= r4) goto L_0x006a
            java.lang.String r14 = " in field name"
            r0._reportInvalidEOF(r14)
        L_0x006a:
            int r14 = r8 >> 2
            r14 = r1[r14]
            r16 = r8 & 3
            int r16 = 3 - r16
            int r16 = r16 << 3
            int r14 = r14 >> r16
            int r8 = r8 + 1
            r6 = r14 & 192(0xc0, float:2.69E-43)
            r15 = 128(0x80, float:1.794E-43)
            if (r6 == r15) goto L_0x0081
            r0._reportInvalidOther(r14)
        L_0x0081:
            int r6 = r13 << 6
            r13 = r14 & 63
            r6 = r6 | r13
            if (r12 <= r5) goto L_0x00be
            int r5 = r8 >> 2
            r5 = r1[r5]
            r13 = r8 & 3
            int r13 = 3 - r13
            int r13 = r13 << r7
            int r5 = r5 >> r13
            int r8 = r8 + 1
            r13 = r5 & 192(0xc0, float:2.69E-43)
            if (r13 == r15) goto L_0x009b
            r0._reportInvalidOther(r5)
        L_0x009b:
            int r6 = r6 << 6
            r5 = r5 & 63
            r5 = r5 | r6
            r6 = 2
            if (r12 <= r6) goto L_0x00c0
            int r6 = r8 >> 2
            r6 = r1[r6]
            r13 = r8 & 3
            int r13 = 3 - r13
            int r13 = r13 << r7
            int r6 = r6 >> r13
            int r8 = r8 + 1
            r13 = r6 & 192(0xc0, float:2.69E-43)
            if (r13 == r15) goto L_0x00b8
            r13 = r6 & 255(0xff, float:3.57E-43)
            r0._reportInvalidOther(r13)
        L_0x00b8:
            int r5 = r5 << 6
            r6 = r6 & 63
            r5 = r5 | r6
            goto L_0x00bf
        L_0x00be:
            r5 = r6
        L_0x00bf:
            r6 = 2
        L_0x00c0:
            if (r12 <= r6) goto L_0x00e3
            r6 = 65536(0x10000, float:9.18355E-41)
            int r5 = r5 - r6
            int r6 = r10.length
            if (r11 < r6) goto L_0x00cf
            com.fasterxml.jackson.core.util.TextBuffer r6 = r0._textBuffer
            char[] r6 = r6.expandCurrentSegment()
            r10 = r6
        L_0x00cf:
            int r6 = r11 + 1
            r12 = 55296(0xd800, float:7.7486E-41)
            int r13 = r5 >> 10
            int r12 = r12 + r13
            char r12 = (char) r12
            r10[r11] = r12
            r11 = 56320(0xdc00, float:7.8921E-41)
            r5 = r5 & 1023(0x3ff, float:1.434E-42)
            r12 = r11 | r5
            r11 = r6
            goto L_0x00e4
        L_0x00e3:
            r12 = r5
        L_0x00e4:
            int r5 = r10.length
            if (r11 < r5) goto L_0x00ee
            com.fasterxml.jackson.core.util.TextBuffer r5 = r0._textBuffer
            char[] r5 = r5.expandCurrentSegment()
            r10 = r5
        L_0x00ee:
            int r5 = r11 + 1
            char r6 = (char) r12
            r10[r11] = r6
            r11 = r5
            r5 = 4
            goto L_0x0026
        L_0x00f7:
            java.lang.String r4 = new java.lang.String
            r5 = 0
            r4.<init>(r10, r5, r11)
            r5 = 4
            if (r3 >= r5) goto L_0x0104
            int r3 = r2 + -1
            r1[r3] = r9
        L_0x0104:
            com.fasterxml.jackson.core.sym.BytesToNameCanonicalizer r3 = r0._symbols
            com.fasterxml.jackson.core.sym.Name r1 = r3.addName(r4, r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.addName(int[], int, int):com.fasterxml.jackson.core.sym.Name");
    }

    /* access modifiers changed from: protected */
    public void _finishString() throws IOException, JsonParseException {
        int i = this._inputPtr;
        if (i >= this._inputEnd) {
            loadMoreGuaranteed();
            i = this._inputPtr;
        }
        int i2 = 0;
        char[] emptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int[] iArr = sInputCodesUtf8;
        int min = Math.min(this._inputEnd, emptyAndGetCurrentSegment.length + i);
        byte[] bArr = this._inputBuffer;
        while (true) {
            if (i >= min) {
                break;
            }
            byte b = bArr[i] & 255;
            if (iArr[b] == 0) {
                i++;
                int i3 = i2 + 1;
                emptyAndGetCurrentSegment[i2] = (char) b;
                i2 = i3;
            } else if (b == 34) {
                this._inputPtr = i + 1;
                this._textBuffer.setCurrentLength(i2);
                return;
            }
        }
        this._inputPtr = i;
        _finishString2(emptyAndGetCurrentSegment, i2);
    }

    private void _finishString2(char[] cArr, int i) throws IOException, JsonParseException {
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i2 = this._inputPtr;
            if (i2 >= this._inputEnd) {
                loadMoreGuaranteed();
                i2 = this._inputPtr;
            }
            if (i >= cArr.length) {
                cArr = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            int min = Math.min(this._inputEnd, (cArr.length - i) + i2);
            while (true) {
                if (i2 < min) {
                    int i3 = i2 + 1;
                    int i4 = bArr[i2] & 255;
                    if (iArr[i4] != 0) {
                        this._inputPtr = i3;
                        if (i4 == 34) {
                            this._textBuffer.setCurrentLength(i);
                            return;
                        }
                        switch (iArr[i4]) {
                            case 1:
                                i4 = _decodeEscaped();
                                break;
                            case 2:
                                i4 = _decodeUtf8_2(i4);
                                break;
                            case 3:
                                if (this._inputEnd - this._inputPtr < 2) {
                                    i4 = _decodeUtf8_3(i4);
                                    break;
                                } else {
                                    i4 = _decodeUtf8_3fast(i4);
                                    break;
                                }
                            case 4:
                                int _decodeUtf8_4 = _decodeUtf8_4(i4);
                                int i5 = i + 1;
                                cArr[i] = (char) (55296 | (_decodeUtf8_4 >> 10));
                                if (i5 >= cArr.length) {
                                    cArr = this._textBuffer.finishCurrentSegment();
                                    i5 = 0;
                                }
                                i4 = (_decodeUtf8_4 & 1023) | 56320;
                                i = i5;
                                break;
                            default:
                                if (i4 >= 32) {
                                    _reportInvalidChar(i4);
                                    break;
                                } else {
                                    _throwUnquotedSpace(i4, "string value");
                                    break;
                                }
                        }
                        if (i >= cArr.length) {
                            cArr = this._textBuffer.finishCurrentSegment();
                            i = 0;
                        }
                        int i6 = i + 1;
                        cArr[i] = (char) i4;
                        i = i6;
                    } else {
                        int i7 = i + 1;
                        cArr[i] = (char) i4;
                        i2 = i3;
                        i = i7;
                    }
                } else {
                    this._inputPtr = i2;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _skipString() throws IOException, JsonParseException {
        this._tokenIncomplete = false;
        int[] iArr = sInputCodesUtf8;
        byte[] bArr = this._inputBuffer;
        while (true) {
            int i = this._inputPtr;
            int i2 = this._inputEnd;
            if (i >= i2) {
                loadMoreGuaranteed();
                i = this._inputPtr;
                i2 = this._inputEnd;
            }
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    byte b = bArr[i] & 255;
                    if (iArr[b] != 0) {
                        this._inputPtr = i3;
                        if (b != 34) {
                            switch (iArr[b]) {
                                case 1:
                                    _decodeEscaped();
                                    break;
                                case 2:
                                    _skipUtf8_2(b);
                                    break;
                                case 3:
                                    _skipUtf8_3(b);
                                    break;
                                case 4:
                                    _skipUtf8_4(b);
                                    break;
                                default:
                                    if (b >= 32) {
                                        _reportInvalidChar(b);
                                        break;
                                    } else {
                                        _throwUnquotedSpace(b, "string value");
                                        break;
                                    }
                            }
                        } else {
                            return;
                        }
                    } else {
                        i = i3;
                    }
                } else {
                    this._inputPtr = i;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public JsonToken _handleUnexpectedValue(int i) throws IOException, JsonParseException {
        if (i != 39) {
            if (i == 43) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                byte[] bArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(bArr[i2] & 255, false);
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
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x004b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken _handleApostropheValue() throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r10 = this;
            com.fasterxml.jackson.core.util.TextBuffer r0 = r10._textBuffer
            char[] r0 = r0.emptyAndGetCurrentSegment()
            int[] r1 = sInputCodesUtf8
            byte[] r2 = r10._inputBuffer
            r3 = 0
            r4 = r0
            r0 = r3
        L_0x000d:
            int r5 = r10._inputPtr
            int r6 = r10._inputEnd
            if (r5 < r6) goto L_0x0016
            r10.loadMoreGuaranteed()
        L_0x0016:
            int r5 = r4.length
            if (r0 < r5) goto L_0x0021
            com.fasterxml.jackson.core.util.TextBuffer r0 = r10._textBuffer
            char[] r0 = r0.finishCurrentSegment()
            r4 = r0
            r0 = r3
        L_0x0021:
            int r5 = r10._inputEnd
            int r6 = r10._inputPtr
            int r7 = r4.length
            int r7 = r7 - r0
            int r6 = r6 + r7
            if (r6 >= r5) goto L_0x002b
            r5 = r6
        L_0x002b:
            int r6 = r10._inputPtr
            if (r6 >= r5) goto L_0x000d
            int r6 = r10._inputPtr
            int r7 = r6 + 1
            r10._inputPtr = r7
            byte r6 = r2[r6]
            r6 = r6 & 255(0xff, float:3.57E-43)
            r7 = 39
            if (r6 == r7) goto L_0x0049
            r8 = r1[r6]
            if (r8 == 0) goto L_0x0042
            goto L_0x0049
        L_0x0042:
            int r7 = r0 + 1
            char r6 = (char) r6
            r4[r0] = r6
            r0 = r7
            goto L_0x002b
        L_0x0049:
            if (r6 != r7) goto L_0x0053
            com.fasterxml.jackson.core.util.TextBuffer r1 = r10._textBuffer
            r1.setCurrentLength(r0)
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.VALUE_STRING
            return r0
        L_0x0053:
            r5 = r1[r6]
            switch(r5) {
                case 1: goto L_0x009d;
                case 2: goto L_0x0098;
                case 3: goto L_0x0086;
                case 4: goto L_0x0062;
                default: goto L_0x0058;
            }
        L_0x0058:
            r5 = 32
            if (r6 >= r5) goto L_0x00a6
            java.lang.String r5 = "string value"
            r10._throwUnquotedSpace(r6, r5)
            goto L_0x00a6
        L_0x0062:
            int r5 = r10._decodeUtf8_4(r6)
            int r6 = r0 + 1
            r7 = 55296(0xd800, float:7.7486E-41)
            int r8 = r5 >> 10
            r7 = r7 | r8
            char r7 = (char) r7
            r4[r0] = r7
            int r0 = r4.length
            if (r6 < r0) goto L_0x007c
            com.fasterxml.jackson.core.util.TextBuffer r0 = r10._textBuffer
            char[] r0 = r0.finishCurrentSegment()
            r4 = r0
            r6 = r3
        L_0x007c:
            r0 = 56320(0xdc00, float:7.8921E-41)
            r5 = r5 & 1023(0x3ff, float:1.434E-42)
            r0 = r0 | r5
            r9 = r6
            r6 = r0
            r0 = r9
            goto L_0x00a9
        L_0x0086:
            int r5 = r10._inputEnd
            int r7 = r10._inputPtr
            int r5 = r5 - r7
            r7 = 2
            if (r5 < r7) goto L_0x0093
            int r6 = r10._decodeUtf8_3fast(r6)
            goto L_0x00a9
        L_0x0093:
            int r6 = r10._decodeUtf8_3(r6)
            goto L_0x00a9
        L_0x0098:
            int r6 = r10._decodeUtf8_2(r6)
            goto L_0x00a9
        L_0x009d:
            r5 = 34
            if (r6 == r5) goto L_0x00a9
            char r6 = r10._decodeEscaped()
            goto L_0x00a9
        L_0x00a6:
            r10._reportInvalidChar(r6)
        L_0x00a9:
            int r5 = r4.length
            if (r0 < r5) goto L_0x00b4
            com.fasterxml.jackson.core.util.TextBuffer r0 = r10._textBuffer
            char[] r0 = r0.finishCurrentSegment()
            r4 = r0
            r0 = r3
        L_0x00b4:
            int r5 = r0 + 1
            char r6 = (char) r6
            r4[r0] = r6
            r0 = r5
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._handleApostropheValue():com.fasterxml.jackson.core.JsonToken");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Incorrect condition in loop: B:1:0x0002 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken _handleInvalidNumberStart(int r4, boolean r5) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r3 = this;
        L_0x0000:
            r0 = 73
            if (r4 != r0) goto L_0x0066
            int r4 = r3._inputPtr
            int r0 = r3._inputEnd
            if (r4 < r0) goto L_0x0013
            boolean r4 = r3.loadMore()
            if (r4 != 0) goto L_0x0013
            r3._reportInvalidEOFInValue()
        L_0x0013:
            byte[] r4 = r3._inputBuffer
            int r0 = r3._inputPtr
            int r1 = r0 + 1
            r3._inputPtr = r1
            byte r4 = r4[r0]
            r0 = 78
            if (r4 != r0) goto L_0x0029
            if (r5 == 0) goto L_0x0026
            java.lang.String r0 = "-INF"
            goto L_0x0034
        L_0x0026:
            java.lang.String r0 = "+INF"
            goto L_0x0034
        L_0x0029:
            r0 = 110(0x6e, float:1.54E-43)
            if (r4 != r0) goto L_0x0066
            if (r5 == 0) goto L_0x0032
            java.lang.String r0 = "-Infinity"
            goto L_0x0034
        L_0x0032:
            java.lang.String r0 = "+Infinity"
        L_0x0034:
            r1 = 3
            r3._matchToken(r0, r1)
            com.fasterxml.jackson.core.JsonParser$Feature r1 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS
            boolean r1 = r3.isEnabled(r1)
            if (r1 == 0) goto L_0x004c
            if (r5 == 0) goto L_0x0045
            r4 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            goto L_0x0047
        L_0x0045:
            r4 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
        L_0x0047:
            com.fasterxml.jackson.core.JsonToken r4 = r3.resetAsNaN(r0, r4)
            return r4
        L_0x004c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Non-standard token '"
            r1.append(r2)
            r1.append(r0)
            java.lang.String r0 = "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r3._reportError(r0)
            goto L_0x0000
        L_0x0066:
            java.lang.String r5 = "expected digit (0-9) to follow minus sign, for valid numeric value"
            r3.reportUnexpectedNumberChar(r4, r5)
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._handleInvalidNumberStart(int, boolean):com.fasterxml.jackson.core.JsonToken");
    }

    /* access modifiers changed from: protected */
    public void _matchToken(String str, int i) throws IOException, JsonParseException {
        int length = str.length();
        do {
            if ((this._inputPtr >= this._inputEnd && !loadMore()) || this._inputBuffer[this._inputPtr] != str.charAt(i)) {
                _reportInvalidToken(str.substring(0, i));
            }
            this._inputPtr++;
            i++;
        } while (i < length);
        if (this._inputPtr < this._inputEnd || loadMore()) {
            byte b = this._inputBuffer[this._inputPtr] & 255;
            if (b >= 48 && b != 93 && b != 125 && Character.isJavaIdentifierPart((char) _decodeCharForError(b))) {
                _reportInvalidToken(str.substring(0, i));
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
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char _decodeCharForError = (char) _decodeCharForError(bArr[i]);
            if (!Character.isJavaIdentifierPart(_decodeCharForError)) {
                break;
            }
            sb.append(_decodeCharForError);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Unrecognized token '");
        sb2.append(sb.toString());
        sb2.append("': was expecting ");
        sb2.append(str2);
        _reportError(sb2.toString());
    }

    private int _skipWS() throws IOException, JsonParseException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                byte b = bArr[i] & 255;
                if (b > 32) {
                    if (b != 47) {
                        return b;
                    }
                    _skipComment();
                } else if (b != 32) {
                    if (b == 10) {
                        _skipLF();
                    } else if (b == 13) {
                        _skipCR();
                    } else if (b != 9) {
                        _throwInvalidSpace(b);
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
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                byte b = bArr[i] & 255;
                if (b > 32) {
                    if (b != 47) {
                        return b;
                    }
                    _skipComment();
                } else if (b != 32) {
                    if (b == 10) {
                        _skipLF();
                    } else if (b == 13) {
                        _skipCR();
                    } else if (b != 9) {
                        _throwInvalidSpace(b);
                    }
                }
            } else {
                _handleEOF();
                return -1;
            }
        }
    }

    private int _skipColon() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        if (b != 58) {
            byte b2 = b & 255;
            while (true) {
                if (b2 == 13) {
                    _skipCR();
                } else if (b2 != 32) {
                    if (b2 != 47) {
                        switch (b2) {
                            case 9:
                                break;
                            case 10:
                                _skipLF();
                                break;
                            default:
                                if (b2 < 32) {
                                    _throwInvalidSpace(b2);
                                }
                                if (b2 != 58) {
                                    _reportUnexpectedChar(b2, "was expecting a colon to separate field name and value");
                                    break;
                                }
                                break;
                        }
                    } else {
                        _skipComment();
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                b2 = bArr2[i2] & 255;
            }
        } else if (this._inputPtr < this._inputEnd) {
            byte b3 = this._inputBuffer[this._inputPtr] & 255;
            if (b3 > 32 && b3 != 47) {
                this._inputPtr++;
                return b3;
            }
        }
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr3 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                byte b4 = bArr3[i3] & 255;
                if (b4 > 32) {
                    if (b4 != 47) {
                        return b4;
                    }
                    _skipComment();
                } else if (b4 != 32) {
                    if (b4 == 10) {
                        _skipLF();
                    } else if (b4 == 13) {
                        _skipCR();
                    } else if (b4 != 9) {
                        _throwInvalidSpace(b4);
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

    private void _skipComment() throws IOException, JsonParseException {
        if (!isEnabled(Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i] & 255;
        if (b == 47) {
            _skipCppComment();
        } else if (b == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(b, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipCComment() throws IOException, JsonParseException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                byte b = bArr[i] & 255;
                int i2 = inputCodeComment[b];
                if (i2 != 0) {
                    if (i2 == 10) {
                        _skipLF();
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 42) {
                        switch (i2) {
                            case 2:
                                _skipUtf8_2(b);
                                break;
                            case 3:
                                _skipUtf8_3(b);
                                break;
                            case 4:
                                _skipUtf8_4(b);
                                break;
                            default:
                                _reportInvalidChar(b);
                                break;
                        }
                    } else if (this._inputPtr < this._inputEnd || loadMore()) {
                        if (this._inputBuffer[this._inputPtr] == 47) {
                            this._inputPtr++;
                            return;
                        }
                    }
                }
            }
        }
        _reportInvalidEOF(" in a comment");
    }

    private void _skipCppComment() throws IOException, JsonParseException {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                byte b = bArr[i] & 255;
                int i2 = inputCodeComment[b];
                if (i2 != 0) {
                    if (i2 == 10) {
                        _skipLF();
                        return;
                    } else if (i2 == 13) {
                        _skipCR();
                        return;
                    } else if (i2 != 42) {
                        switch (i2) {
                            case 2:
                                _skipUtf8_2(b);
                                break;
                            case 3:
                                _skipUtf8_3(b);
                                break;
                            case 4:
                                _skipUtf8_4(b);
                                break;
                            default:
                                _reportInvalidChar(b);
                                break;
                        }
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
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte b = bArr[i];
        if (b == 34 || b == 47 || b == 92) {
            return (char) b;
        }
        if (b == 98) {
            return 8;
        }
        if (b == 102) {
            return 12;
        }
        if (b == 110) {
            return 10;
        }
        if (b == 114) {
            return CharUtils.CR;
        }
        switch (b) {
            case 116:
                return 9;
            case 117:
                int i2 = 0;
                for (int i3 = 0; i3 < 4; i3++) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in character escape sequence");
                    }
                    byte[] bArr2 = this._inputBuffer;
                    int i4 = this._inputPtr;
                    this._inputPtr = i4 + 1;
                    byte b2 = bArr2[i4];
                    int charToHex = CharTypes.charToHex(b2);
                    if (charToHex < 0) {
                        _reportUnexpectedChar(b2, "expected a hex-digit for character escape sequence");
                    }
                    i2 = (i2 << 4) | charToHex;
                }
                return (char) i2;
            default:
                return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(b));
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int _decodeCharForError(int r7) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r6 = this;
            if (r7 >= 0) goto L_0x0064
            r0 = r7 & 224(0xe0, float:3.14E-43)
            r1 = 2
            r2 = 1
            r3 = 192(0xc0, float:2.69E-43)
            if (r0 != r3) goto L_0x000e
            r7 = r7 & 31
        L_0x000c:
            r0 = r2
            goto L_0x0028
        L_0x000e:
            r0 = r7 & 240(0xf0, float:3.36E-43)
            r3 = 224(0xe0, float:3.14E-43)
            if (r0 != r3) goto L_0x0018
            r7 = r7 & 15
            r0 = r1
            goto L_0x0028
        L_0x0018:
            r0 = r7 & 248(0xf8, float:3.48E-43)
            r3 = 240(0xf0, float:3.36E-43)
            if (r0 != r3) goto L_0x0022
            r7 = r7 & 7
            r0 = 3
            goto L_0x0028
        L_0x0022:
            r0 = r7 & 255(0xff, float:3.57E-43)
            r6._reportInvalidInitial(r0)
            goto L_0x000c
        L_0x0028:
            int r3 = r6.nextByte()
            r4 = r3 & 192(0xc0, float:2.69E-43)
            r5 = 128(0x80, float:1.794E-43)
            if (r4 == r5) goto L_0x0037
            r4 = r3 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r4)
        L_0x0037:
            int r7 = r7 << 6
            r3 = r3 & 63
            r7 = r7 | r3
            if (r0 <= r2) goto L_0x0064
            int r2 = r6.nextByte()
            r3 = r2 & 192(0xc0, float:2.69E-43)
            if (r3 == r5) goto L_0x004b
            r3 = r2 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r3)
        L_0x004b:
            int r7 = r7 << 6
            r2 = r2 & 63
            r7 = r7 | r2
            if (r0 <= r1) goto L_0x0064
            int r0 = r6.nextByte()
            r1 = r0 & 192(0xc0, float:2.69E-43)
            if (r1 == r5) goto L_0x005f
            r1 = r0 & 255(0xff, float:3.57E-43)
            r6._reportInvalidOther(r1)
        L_0x005f:
            int r7 = r7 << 6
            r0 = r0 & 63
            r7 = r7 | r0
        L_0x0064:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._decodeCharForError(int):int");
    }

    private int _decodeUtf8_2(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        return ((i & 31) << 6) | (b & 63);
    }

    private int _decodeUtf8_3(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        byte b2 = (i2 << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b3 = bArr2[i4];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
        return (b2 << 6) | (b3 & 63);
    }

    private int _decodeUtf8_3fast(int i) throws IOException, JsonParseException {
        int i2 = i & 15;
        byte[] bArr = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b = bArr[i3];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        byte b2 = (i2 << 6) | (b & 63);
        byte[] bArr2 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b3 = bArr2[i4];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
        return (b2 << 6) | (b3 & 63);
    }

    private int _decodeUtf8_4(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        byte b2 = ((i & 7) << 6) | (b & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b3 = bArr2[i3];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
        }
        byte b4 = (b2 << 6) | (b3 & 63);
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b5 = bArr3[i4];
        if ((b5 & 192) != 128) {
            _reportInvalidOther(b5 & 255, this._inputPtr);
        }
        return ((b4 << 6) | (b5 & 63)) - Dfp.FINITE;
    }

    private void _skipUtf8_2(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
    }

    private void _skipUtf8_3(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
    }

    private void _skipUtf8_4(int i) throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte b = bArr[i2];
        if ((b & 192) != 128) {
            _reportInvalidOther(b & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte b2 = bArr2[i3];
        if ((b2 & 192) != 128) {
            _reportInvalidOther(b2 & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i4 = this._inputPtr;
        this._inputPtr = i4 + 1;
        byte b3 = bArr3[i4];
        if ((b3 & 192) != 128) {
            _reportInvalidOther(b3 & 255, this._inputPtr);
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

    private int nextByte() throws IOException, JsonParseException {
        if (this._inputPtr >= this._inputEnd) {
            loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i] & 255;
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidChar(int i) throws JsonParseException {
        if (i < 32) {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidInitial(int i) throws JsonParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid UTF-8 start byte 0x");
        sb.append(Integer.toHexString(i));
        _reportError(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidOther(int i) throws JsonParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid UTF-8 middle byte 0x");
        sb.append(Integer.toHexString(i));
        _reportError(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidOther(int i, int i2) throws JsonParseException {
        this._inputPtr = i2;
        _reportInvalidOther(i);
    }

    public static int[] growArrayBy(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        int length = iArr.length;
        int[] iArr2 = new int[(i + length)];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    /* access modifiers changed from: protected */
    public byte[] _decodeBase64(Base64Variant base64Variant) throws IOException, JsonParseException {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b = bArr[i] & 255;
            if (b > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char((int) b);
                if (decodeBase64Char < 0) {
                    if (b == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = _decodeBase64Escape(base64Variant, (int) b, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                byte b2 = bArr2[i2] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char((int) b2);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = _decodeBase64Escape(base64Variant, (int) b2, 1);
                }
                int i3 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                byte b3 = bArr3[i4] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char((int) b3);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (b3 != 34 || base64Variant.usesPadding()) {
                            decodeBase64Char3 = _decodeBase64Escape(base64Variant, (int) b3, 2);
                        } else {
                            _getByteArrayBuilder.append(i3 >> 4);
                            return _getByteArrayBuilder.toByteArray();
                        }
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i5 = this._inputPtr;
                        this._inputPtr = i5 + 1;
                        byte b4 = bArr4[i5] & 255;
                        if (!base64Variant.usesPaddingChar((int) b4)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("expected padding character '");
                            sb.append(base64Variant.getPaddingChar());
                            sb.append("'");
                            throw reportInvalidBase64Char(base64Variant, b4, 3, sb.toString());
                        }
                        _getByteArrayBuilder.append(i3 >> 4);
                    }
                }
                int i6 = (i3 << 6) | decodeBase64Char3;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i7 = this._inputPtr;
                this._inputPtr = i7 + 1;
                byte b5 = bArr5[i7] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char((int) b5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (b5 != 34 || base64Variant.usesPadding()) {
                            decodeBase64Char4 = _decodeBase64Escape(base64Variant, (int) b5, 3);
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
}
