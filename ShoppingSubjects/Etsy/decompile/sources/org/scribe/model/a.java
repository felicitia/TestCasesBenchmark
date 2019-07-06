package org.scribe.model;

import java.io.OutputStream;

/* compiled from: OAuthConfig */
public class a {
    private final String a;
    private final String b;
    private final String c;
    private final SignatureType d;
    private final String e;
    private final OutputStream f;

    public a(String str, String str2, String str3, SignatureType signatureType, String str4, OutputStream outputStream) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = signatureType;
        this.e = str4;
        this.f = outputStream;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public SignatureType c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public boolean e() {
        return this.e != null;
    }

    public void a(String str) {
        if (this.f != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("\n");
            try {
                this.f.write(sb.toString().getBytes("UTF8"));
            } catch (Exception e2) {
                throw new RuntimeException("there were problems while writting to the debug stream", e2);
            }
        }
    }
}
