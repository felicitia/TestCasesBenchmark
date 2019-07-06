package com.onfido.c.a;

import android.text.TextUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPOutputStream;

class e {
    final f a;
    final String b;

    static abstract class a implements Closeable {
        final HttpURLConnection a;
        final InputStream b;
        final OutputStream c;

        a(HttpURLConnection httpURLConnection, InputStream inputStream, OutputStream outputStream) {
            if (httpURLConnection == null) {
                throw new IllegalArgumentException("connection == null");
            }
            this.a = httpURLConnection;
            this.b = inputStream;
            this.c = outputStream;
        }

        public void close() {
            this.a.disconnect();
        }
    }

    static class b extends IOException {
        final int a;
        final String b;
        final String c;

        b(int i, String str, String str2) {
            StringBuilder sb = new StringBuilder();
            sb.append("HTTP ");
            sb.append(i);
            sb.append(": ");
            sb.append(str);
            sb.append(". Response: ");
            sb.append(str2);
            super(sb.toString());
            this.a = i;
            this.b = str;
            this.c = str2;
        }
    }

    e(String str, f fVar) {
        this.b = str;
        this.a = fVar;
    }

    private static a a(HttpURLConnection httpURLConnection) {
        return new a(httpURLConnection, null, TextUtils.equals("gzip", httpURLConnection.getRequestProperty("Content-Encoding")) ? new GZIPOutputStream(httpURLConnection.getOutputStream()) : httpURLConnection.getOutputStream()) {
            public void close() {
                String str;
                try {
                    int responseCode = this.a.getResponseCode();
                    if (responseCode >= 300) {
                        str = com.onfido.c.a.b.b.b(com.onfido.c.a.b.b.a(this.a));
                        throw new b(responseCode, this.a.getResponseMessage(), str);
                    }
                    super.close();
                    this.c.close();
                } catch (IOException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Could not read response body for rejected message: ");
                    sb.append(e.toString());
                    str = sb.toString();
                } catch (Throwable th) {
                    super.close();
                    this.c.close();
                    throw th;
                }
            }
        };
    }

    private static a b(HttpURLConnection httpURLConnection) {
        return new a(httpURLConnection, com.onfido.c.a.b.b.a(httpURLConnection), null) {
            public void close() {
                super.close();
                this.b.close();
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public a a() {
        return a(this.a.upload(this.b));
    }

    /* access modifiers changed from: 0000 */
    public a b() {
        return a(this.a.attribution(this.b));
    }

    /* access modifiers changed from: 0000 */
    public a c() {
        HttpURLConnection projectSettings = this.a.projectSettings(this.b);
        int responseCode = projectSettings.getResponseCode();
        if (responseCode == 200) {
            return b(projectSettings);
        }
        projectSettings.disconnect();
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP ");
        sb.append(responseCode);
        sb.append(": ");
        sb.append(projectSettings.getResponseMessage());
        throw new IOException(sb.toString());
    }
}
