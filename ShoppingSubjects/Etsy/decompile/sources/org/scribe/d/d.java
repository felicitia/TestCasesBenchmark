package org.scribe.d;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.scribe.e.b;
import org.scribe.e.c;
import org.scribe.exceptions.OAuthSignatureException;

/* compiled from: HMACSha1SignatureService */
public class d implements e {
    public String a() {
        return "HMAC-SHA1";
    }

    public String a(String str, String str2, String str3) {
        try {
            c.a(str, "Base string cant be null or empty string");
            c.a(str2, "Api secret cant be null or empty string");
            StringBuilder sb = new StringBuilder();
            sb.append(b.a(str2));
            sb.append('&');
            sb.append(b.a(str3));
            return a(str, sb.toString());
        } catch (Exception e) {
            throw new OAuthSignatureException(str, e);
        }
    }

    private String a(String str, String str2) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), "HmacSHA1");
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(secretKeySpec);
        return a(instance.doFinal(str.getBytes("UTF-8"))).replace("\r\n", "");
    }

    private String a(byte[] bArr) {
        return a.a().a(bArr);
    }
}
