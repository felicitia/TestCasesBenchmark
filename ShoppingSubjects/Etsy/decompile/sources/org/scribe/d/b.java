package org.scribe.d;

import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;
import org.scribe.exceptions.OAuthSignatureException;

/* compiled from: CommonsEncoder */
public class b extends a {
    public String a(byte[] bArr) {
        try {
            return new String(Base64.encodeBase64(bArr), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new OAuthSignatureException("Can't perform base64 encoding", e);
        }
    }

    public static boolean b() {
        try {
            Class.forName("org.apache.commons.codec.binary.Base64");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
