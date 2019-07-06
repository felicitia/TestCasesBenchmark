package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import org.apache.http.entity.mime.MIME;

public class ra extends amf<String> {
    private final Object a = new Object();
    private art<String> b;

    public ra(int i, String str, art<String> art, arn arn) {
        super(i, str, arn);
        this.b = art;
    }

    /* access modifiers changed from: protected */
    public final arb<String> a(all all) {
        String str;
        try {
            byte[] bArr = all.b;
            String str2 = "ISO-8859-1";
            String str3 = (String) all.c.get(MIME.CONTENT_TYPE);
            if (str3 != null) {
                String[] split = str3.split(";");
                int i = 1;
                while (true) {
                    if (i >= split.length) {
                        break;
                    }
                    String[] split2 = split[i].trim().split("=");
                    if (split2.length == 2 && split2[0].equals("charset")) {
                        str2 = split2[1];
                        break;
                    }
                    i++;
                }
            }
            str = new String(bArr, str2);
        } catch (UnsupportedEncodingException unused) {
            str = new String(all.b);
        }
        return arb.a(str, lu.a(all));
    }

    /* access modifiers changed from: protected */
    public void a(String str) {
        art<String> art;
        synchronized (this.a) {
            art = this.b;
        }
        if (art != null) {
            art.a(str);
        }
    }
}
