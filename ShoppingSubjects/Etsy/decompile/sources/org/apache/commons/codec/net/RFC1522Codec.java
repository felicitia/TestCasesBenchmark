package org.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.StringUtils;

abstract class RFC1522Codec {
    protected static final String POSTFIX = "?=";
    protected static final String PREFIX = "=?";
    protected static final char SEP = '?';

    /* access modifiers changed from: protected */
    public abstract byte[] doDecoding(byte[] bArr) throws DecoderException;

    /* access modifiers changed from: protected */
    public abstract byte[] doEncoding(byte[] bArr) throws EncoderException;

    /* access modifiers changed from: protected */
    public abstract String getEncoding();

    RFC1522Codec() {
    }

    /* access modifiers changed from: protected */
    public String encodeText(String str, String str2) throws EncoderException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(PREFIX);
        stringBuffer.append(str2);
        stringBuffer.append(SEP);
        stringBuffer.append(getEncoding());
        stringBuffer.append(SEP);
        stringBuffer.append(StringUtils.newStringUsAscii(doEncoding(str.getBytes(str2))));
        stringBuffer.append(POSTFIX);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public String decodeText(String str) throws DecoderException, UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        if (!str.startsWith(PREFIX) || !str.endsWith(POSTFIX)) {
            throw new DecoderException("RFC 1522 violation: malformed encoded content");
        }
        int length = str.length() - 2;
        int indexOf = str.indexOf(63, 2);
        if (indexOf == length) {
            throw new DecoderException("RFC 1522 violation: charset token not found");
        }
        String substring = str.substring(2, indexOf);
        if (substring.equals("")) {
            throw new DecoderException("RFC 1522 violation: charset not specified");
        }
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(63, i);
        if (indexOf2 == length) {
            throw new DecoderException("RFC 1522 violation: encoding token not found");
        }
        String substring2 = str.substring(i, indexOf2);
        if (!getEncoding().equalsIgnoreCase(substring2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("This codec cannot decode ");
            sb.append(substring2);
            sb.append(" encoded content");
            throw new DecoderException(sb.toString());
        }
        int i2 = indexOf2 + 1;
        return new String(doDecoding(StringUtils.getBytesUsAscii(str.substring(i2, str.indexOf(63, i2)))), substring);
    }
}
