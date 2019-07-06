package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import com.facebook.LoggingBehavior;
import com.facebook.internal.l.d;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

/* compiled from: ImageResponseCache */
class q {
    static final String a = "q";
    private static l b;

    /* compiled from: ImageResponseCache */
    private static class a extends BufferedInputStream {
        HttpURLConnection a;

        a(InputStream inputStream, HttpURLConnection httpURLConnection) {
            super(inputStream, 8192);
            this.a = httpURLConnection;
        }

        public void close() throws IOException {
            super.close();
            z.a((URLConnection) this.a);
        }
    }

    q() {
    }

    static synchronized l a(Context context) throws IOException {
        l lVar;
        synchronized (q.class) {
            if (b == null) {
                b = new l(a, new d());
            }
            lVar = b;
        }
        return lVar;
    }

    static InputStream a(Uri uri, Context context) {
        if (uri != null && a(uri)) {
            try {
                return a(context).a(uri.toString());
            } catch (IOException e) {
                t.a(LoggingBehavior.CACHE, 5, a, e.toString());
            }
        }
        return null;
    }

    static InputStream a(Context context, HttpURLConnection httpURLConnection) throws IOException {
        if (httpURLConnection.getResponseCode() != 200) {
            return null;
        }
        Uri parse = Uri.parse(httpURLConnection.getURL().toString());
        InputStream inputStream = httpURLConnection.getInputStream();
        try {
            return a(parse) ? a(context).a(parse.toString(), (InputStream) new a(inputStream, httpURLConnection)) : inputStream;
        } catch (IOException unused) {
            return inputStream;
        }
    }

    private static boolean a(Uri uri) {
        if (uri != null) {
            String host = uri.getHost();
            if (host.endsWith("fbcdn.net")) {
                return true;
            }
            if (host.startsWith("fbcdn") && host.endsWith("akamaihd.net")) {
                return true;
            }
        }
        return false;
    }
}
