package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class ParserMinimalBase extends JsonParser {
    protected static final int INT_APOSTROPHE = 39;
    protected static final int INT_ASTERISK = 42;
    protected static final int INT_BACKSLASH = 92;
    protected static final int INT_COLON = 58;
    protected static final int INT_COMMA = 44;
    protected static final int INT_CR = 13;
    protected static final int INT_LBRACKET = 91;
    protected static final int INT_LCURLY = 123;
    protected static final int INT_LF = 10;
    protected static final int INT_QUOTE = 34;
    protected static final int INT_RBRACKET = 93;
    protected static final int INT_RCURLY = 125;
    protected static final int INT_SLASH = 47;
    protected static final int INT_SPACE = 32;
    protected static final int INT_TAB = 9;
    protected static final int INT_b = 98;
    protected static final int INT_f = 102;
    protected static final int INT_n = 110;
    protected static final int INT_r = 114;
    protected static final int INT_t = 116;
    protected static final int INT_u = 117;
    protected JsonToken _currToken;
    protected JsonToken _lastClearedToken;

    /* access modifiers changed from: protected */
    public abstract void _handleEOF() throws JsonParseException;

    public abstract void close() throws IOException;

    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException, JsonParseException;

    public abstract String getCurrentName() throws IOException, JsonParseException;

    public abstract JsonStreamContext getParsingContext();

    public abstract String getText() throws IOException, JsonParseException;

    public abstract char[] getTextCharacters() throws IOException, JsonParseException;

    public abstract int getTextLength() throws IOException, JsonParseException;

    public abstract int getTextOffset() throws IOException, JsonParseException;

    public abstract boolean hasTextCharacters();

    public abstract boolean isClosed();

    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    public abstract void overrideCurrentName(String str);

    protected ParserMinimalBase() {
    }

    protected ParserMinimalBase(int i) {
        super(i);
    }

    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    public JsonToken nextValue() throws IOException, JsonParseException {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    public JsonParser skipChildren() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            int i = 1;
            while (true) {
                JsonToken nextToken = nextToken();
                if (nextToken != null) {
                    switch (nextToken) {
                        case START_OBJECT:
                        case START_ARRAY:
                            i++;
                            break;
                        case END_OBJECT:
                        case END_ARRAY:
                            i--;
                            if (i != 0) {
                                break;
                            } else {
                                return this;
                            }
                    }
                } else {
                    _handleEOF();
                    return this;
                }
            }
        } else {
            return this;
        }
    }

    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._lastClearedToken = this._currToken;
            this._currToken = null;
        }
    }

    public JsonToken getLastClearedToken() {
        return this._lastClearedToken;
    }

    public boolean getValueAsBoolean(boolean z) throws IOException, JsonParseException {
        if (this._currToken != null) {
            boolean z2 = false;
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                    if (getIntValue() != 0) {
                        z2 = true;
                    }
                    return z2;
                case VALUE_TRUE:
                    return true;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return false;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Boolean) {
                        return ((Boolean) embeddedObject).booleanValue();
                    }
                    break;
                case VALUE_STRING:
                    break;
            }
            if ("true".equals(getText().trim())) {
                return true;
            }
        }
        return z;
    }

    public int getValueAsInt(int i) throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return getIntValue();
                case VALUE_TRUE:
                    return 1;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return 0;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).intValue();
                    }
                    break;
                case VALUE_STRING:
                    return NumberInput.parseAsInt(getText(), i);
            }
        }
        return i;
    }

    public long getValueAsLong(long j) throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return getLongValue();
                case VALUE_TRUE:
                    return 1;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return 0;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).longValue();
                    }
                    break;
                case VALUE_STRING:
                    return NumberInput.parseAsLong(getText(), j);
            }
        }
        return j;
    }

    public double getValueAsDouble(double d) throws IOException, JsonParseException {
        if (this._currToken != null) {
            switch (this._currToken) {
                case VALUE_NUMBER_INT:
                case VALUE_NUMBER_FLOAT:
                    return getDoubleValue();
                case VALUE_TRUE:
                    return 1.0d;
                case VALUE_FALSE:
                case VALUE_NULL:
                    return 0.0d;
                case VALUE_EMBEDDED_OBJECT:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Number) {
                        return ((Number) embeddedObject).doubleValue();
                    }
                    break;
                case VALUE_STRING:
                    return NumberInput.parseAsDouble(getText(), d);
            }
        }
        return d;
    }

    public String getValueAsString(String str) throws IOException, JsonParseException {
        if (this._currToken == JsonToken.VALUE_STRING || (this._currToken != null && this._currToken != JsonToken.VALUE_NULL && this._currToken.isScalarValue())) {
            return getText();
        }
        return str;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0022, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        r2 = r3 + 1;
        r3 = r11.charAt(r3);
        r6 = r13.decodeBase64Char(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        if (r6 >= 0) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        _reportInvalidBase64(r13, r3, 1, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        r3 = (r4 << 6) | r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0038, code lost:
        if (r2 < r0) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
        if (r13.usesPadding() != false) goto L_0x0047;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
        r12.append(r3 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0047, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004a, code lost:
        r4 = r2 + 1;
        r2 = r11.charAt(r2);
        r6 = r13.decodeBase64Char(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        if (r6 >= 0) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        if (r6 == -2) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        _reportInvalidBase64(r13, r2, 2, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005e, code lost:
        if (r4 < r0) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0060, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0063, code lost:
        r2 = r4 + 1;
        r4 = r11.charAt(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006d, code lost:
        if (r13.usesPaddingChar(r4) != false) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006f, code lost:
        r5 = new java.lang.StringBuilder();
        r5.append("expected padding character '");
        r5.append(r13.getPaddingChar());
        r5.append("'");
        _reportInvalidBase64(r13, r4, 3, r5.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008c, code lost:
        r12.append(r3 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0093, code lost:
        r2 = (r3 << 6) | r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0096, code lost:
        if (r4 < r0) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009c, code lost:
        if (r13.usesPadding() != false) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009e, code lost:
        r12.appendTwoBytes(r2 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a4, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a7, code lost:
        r3 = r4 + 1;
        r4 = r11.charAt(r4);
        r6 = r13.decodeBase64Char(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b1, code lost:
        if (r6 >= 0) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b3, code lost:
        if (r6 == -2) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b5, code lost:
        _reportInvalidBase64(r13, r4, 3, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b8, code lost:
        r12.appendTwoBytes(r2 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00be, code lost:
        r12.appendThreeBytes((r2 << 6) | r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c4, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0016, code lost:
        r4 = r13.decodeBase64Char(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        if (r4 >= 0) goto L_0x0020;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        _reportInvalidBase64(r13, r2, 0, null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0020, code lost:
        if (r3 < r0) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void _decodeBase64(java.lang.String r11, com.fasterxml.jackson.core.util.ByteArrayBuilder r12, com.fasterxml.jackson.core.Base64Variant r13) throws java.io.IOException, com.fasterxml.jackson.core.JsonParseException {
        /*
            r10 = this;
            int r0 = r11.length()
            r1 = 0
            r2 = r1
        L_0x0006:
            if (r2 >= r0) goto L_0x00ca
        L_0x0008:
            int r3 = r2 + 1
            char r2 = r11.charAt(r2)
            if (r3 < r0) goto L_0x0012
            goto L_0x00ca
        L_0x0012:
            r4 = 32
            if (r2 <= r4) goto L_0x00c7
            int r4 = r13.decodeBase64Char(r2)
            r5 = 0
            if (r4 >= 0) goto L_0x0020
            r10._reportInvalidBase64(r13, r2, r1, r5)
        L_0x0020:
            if (r3 < r0) goto L_0x0025
            r10._reportBase64EOF()
        L_0x0025:
            int r2 = r3 + 1
            char r3 = r11.charAt(r3)
            int r6 = r13.decodeBase64Char(r3)
            if (r6 >= 0) goto L_0x0035
            r7 = 1
            r10._reportInvalidBase64(r13, r3, r7, r5)
        L_0x0035:
            int r3 = r4 << 6
            r3 = r3 | r6
            if (r2 < r0) goto L_0x004a
            boolean r4 = r13.usesPadding()
            if (r4 != 0) goto L_0x0047
            int r11 = r3 >> 4
            r12.append(r11)
            goto L_0x00ca
        L_0x0047:
            r10._reportBase64EOF()
        L_0x004a:
            int r4 = r2 + 1
            char r2 = r11.charAt(r2)
            int r6 = r13.decodeBase64Char(r2)
            r7 = 3
            r8 = -2
            r9 = 2
            if (r6 >= 0) goto L_0x0093
            if (r6 == r8) goto L_0x005e
            r10._reportInvalidBase64(r13, r2, r9, r5)
        L_0x005e:
            if (r4 < r0) goto L_0x0063
            r10._reportBase64EOF()
        L_0x0063:
            int r2 = r4 + 1
            char r4 = r11.charAt(r4)
            boolean r5 = r13.usesPaddingChar(r4)
            if (r5 != 0) goto L_0x008c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "expected padding character '"
            r5.append(r6)
            char r6 = r13.getPaddingChar()
            r5.append(r6)
            java.lang.String r6 = "'"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r10._reportInvalidBase64(r13, r4, r7, r5)
        L_0x008c:
            int r3 = r3 >> 4
            r12.append(r3)
            goto L_0x0006
        L_0x0093:
            int r2 = r3 << 6
            r2 = r2 | r6
            if (r4 < r0) goto L_0x00a7
            boolean r3 = r13.usesPadding()
            if (r3 != 0) goto L_0x00a4
            int r11 = r2 >> 2
            r12.appendTwoBytes(r11)
            goto L_0x00ca
        L_0x00a4:
            r10._reportBase64EOF()
        L_0x00a7:
            int r3 = r4 + 1
            char r4 = r11.charAt(r4)
            int r6 = r13.decodeBase64Char(r4)
            if (r6 >= 0) goto L_0x00be
            if (r6 == r8) goto L_0x00b8
            r10._reportInvalidBase64(r13, r4, r7, r5)
        L_0x00b8:
            int r2 = r2 >> 2
            r12.appendTwoBytes(r2)
            goto L_0x00c4
        L_0x00be:
            int r2 = r2 << 6
            r2 = r2 | r6
            r12.appendThreeBytes(r2)
        L_0x00c4:
            r2 = r3
            goto L_0x0006
        L_0x00c7:
            r2 = r3
            goto L_0x0008
        L_0x00ca:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.base.ParserMinimalBase._decodeBase64(java.lang.String, com.fasterxml.jackson.core.util.ByteArrayBuilder, com.fasterxml.jackson.core.Base64Variant):void");
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidBase64(Base64Variant base64Variant, char c, int i, String str) throws JsonParseException {
        String str2;
        if (c <= ' ') {
            StringBuilder sb = new StringBuilder();
            sb.append("Illegal white space character (code 0x");
            sb.append(Integer.toHexString(c));
            sb.append(") as character #");
            sb.append(i + 1);
            sb.append(" of 4-char base64 unit: can only used between units");
            str2 = sb.toString();
        } else if (base64Variant.usesPaddingChar(c)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unexpected padding character ('");
            sb2.append(base64Variant.getPaddingChar());
            sb2.append("') as character #");
            sb2.append(i + 1);
            sb2.append(" of 4-char base64 unit: padding only legal as 3rd or 4th character");
            str2 = sb2.toString();
        } else if (!Character.isDefined(c) || Character.isISOControl(c)) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Illegal character (code 0x");
            sb3.append(Integer.toHexString(c));
            sb3.append(") in base64 content");
            str2 = sb3.toString();
        } else {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Illegal character '");
            sb4.append(c);
            sb4.append("' (code 0x");
            sb4.append(Integer.toHexString(c));
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
        throw _constructError(str2);
    }

    /* access modifiers changed from: protected */
    public void _reportBase64EOF() throws JsonParseException {
        throw _constructError("Unexpected end-of-String in base64 content");
    }

    /* access modifiers changed from: protected */
    public void _reportUnexpectedChar(int i, String str) throws JsonParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected character (");
        sb.append(_getCharDesc(i));
        sb.append(")");
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
    public void _reportInvalidEOF() throws JsonParseException {
        StringBuilder sb = new StringBuilder();
        sb.append(" in ");
        sb.append(this._currToken);
        _reportInvalidEOF(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidEOF(String str) throws JsonParseException {
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected end-of-input");
        sb.append(str);
        _reportError(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidEOFInValue() throws JsonParseException {
        _reportInvalidEOF(" in a value");
    }

    /* access modifiers changed from: protected */
    public void _throwInvalidSpace(int i) throws JsonParseException {
        char c = (char) i;
        StringBuilder sb = new StringBuilder();
        sb.append("Illegal character (");
        sb.append(_getCharDesc(c));
        sb.append("): only regular white space (\\r, \\n, \\t) is allowed between tokens");
        _reportError(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void _throwUnquotedSpace(int i, String str) throws JsonParseException {
        if (!isEnabled(Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i >= 32) {
            char c = (char) i;
            StringBuilder sb = new StringBuilder();
            sb.append("Illegal unquoted character (");
            sb.append(_getCharDesc(c));
            sb.append("): has to be escaped using backslash to be included in ");
            sb.append(str);
            _reportError(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    public char _handleUnrecognizedCharacterEscape(char c) throws JsonProcessingException {
        if (isEnabled(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c;
        }
        if (c == '\'' && isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return c;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unrecognized character escape ");
        sb.append(_getCharDesc(c));
        _reportError(sb.toString());
        return c;
    }

    protected static final String _getCharDesc(int i) {
        char c = (char) i;
        if (Character.isISOControl(c)) {
            StringBuilder sb = new StringBuilder();
            sb.append("(CTRL-CHAR, code ");
            sb.append(i);
            sb.append(")");
            return sb.toString();
        } else if (i > 255) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("'");
            sb2.append(c);
            sb2.append("' (code ");
            sb2.append(i);
            sb2.append(" / 0x");
            sb2.append(Integer.toHexString(i));
            sb2.append(")");
            return sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("'");
            sb3.append(c);
            sb3.append("' (code ");
            sb3.append(i);
            sb3.append(")");
            return sb3.toString();
        }
    }

    /* access modifiers changed from: protected */
    public final void _reportError(String str) throws JsonParseException {
        throw _constructError(str);
    }

    /* access modifiers changed from: protected */
    public final void _wrapError(String str, Throwable th) throws JsonParseException {
        throw _constructError(str, th);
    }

    /* access modifiers changed from: protected */
    public final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    /* access modifiers changed from: protected */
    public final JsonParseException _constructError(String str, Throwable th) {
        return new JsonParseException(str, getCurrentLocation(), th);
    }
}
