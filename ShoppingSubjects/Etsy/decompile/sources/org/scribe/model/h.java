package org.scribe.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.scribe.e.d;
import org.scribe.exceptions.OAuthException;

/* compiled from: Response */
public class h {
    private int a;
    private String b;
    private String c;
    private InputStream d;
    private Map<String, String> e;

    h(HttpURLConnection httpURLConnection) throws IOException {
        try {
            httpURLConnection.connect();
            this.a = httpURLConnection.getResponseCode();
            this.b = httpURLConnection.getResponseMessage();
            this.e = a(httpURLConnection);
            this.d = a() ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
        } catch (UnknownHostException e2) {
            throw new OAuthException("The IP address of a host could not be determined.", e2);
        }
    }

    private String e() {
        this.c = d.a(c());
        return this.c;
    }

    private Map<String, String> a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        for (String str : httpURLConnection.getHeaderFields().keySet()) {
            hashMap.put(str, ((List) httpURLConnection.getHeaderFields().get(str)).get(0));
        }
        return hashMap;
    }

    public boolean a() {
        return d() >= 200 && d() < 400;
    }

    public String b() {
        return this.c != null ? this.c : e();
    }

    public InputStream c() {
        return this.d;
    }

    public int d() {
        return this.a;
    }

    public String a(String str) {
        return (String) this.e.get(str);
    }
}
