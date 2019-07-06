package org.scribe.e;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import org.scribe.exceptions.OAuthException;

/* compiled from: OAuthEncoder */
public class b {
    private static String a = "UTF-8";
    private static final Map<String, String> b;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("*", "%2A");
        hashMap.put("+", "%20");
        hashMap.put("%7E", "~");
        b = Collections.unmodifiableMap(hashMap);
    }

    public static String a(String str) {
        c.a((Object) str, "Cannot encode null object");
        try {
            String encode = URLEncoder.encode(str, a);
            for (Entry entry : b.entrySet()) {
                encode = a(encode, (String) entry.getKey(), (String) entry.getValue());
            }
            return encode;
        } catch (UnsupportedEncodingException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Charset not found while encoding string: ");
            sb.append(a);
            throw new OAuthException(sb.toString(), e);
        }
    }

    private static String a(String str, String str2, String str3) {
        return str.replaceAll(Pattern.quote(str2), str3);
    }

    public static String b(String str) {
        c.a((Object) str, "Cannot decode null object");
        try {
            return URLDecoder.decode(str, a);
        } catch (UnsupportedEncodingException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Charset not found while decoding string: ");
            sb.append(a);
            throw new OAuthException(sb.toString(), e);
        }
    }
}
