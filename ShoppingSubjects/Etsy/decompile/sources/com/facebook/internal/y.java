package com.facebook.internal;

import android.net.Uri;
import com.facebook.internal.l.d;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* compiled from: UrlRedirectCache */
class y {
    static final String a = "y";
    private static final String b;
    private static l c;

    y() {
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append("_Redirect");
        b = sb.toString();
    }

    static synchronized l a() throws IOException {
        l lVar;
        synchronized (y.class) {
            if (c == null) {
                c = new l(a, new d());
            }
            lVar = c;
        }
        return lVar;
    }

    static Uri a(Uri uri) {
        InputStreamReader inputStreamReader;
        Throwable th;
        if (uri == null) {
            return null;
        }
        String uri2 = uri.toString();
        try {
            l a2 = a();
            InputStreamReader inputStreamReader2 = null;
            boolean z = false;
            while (true) {
                try {
                    InputStream a3 = a2.a(uri2, b);
                    if (a3 == null) {
                        break;
                    }
                    z = true;
                    inputStreamReader = new InputStreamReader(a3);
                    try {
                        char[] cArr = new char[128];
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            int read = inputStreamReader.read(cArr, 0, cArr.length);
                            if (read <= 0) {
                                break;
                            }
                            sb.append(cArr, 0, read);
                        }
                        z.a((Closeable) inputStreamReader);
                        inputStreamReader2 = inputStreamReader;
                        uri2 = sb.toString();
                    } catch (IOException unused) {
                        z.a((Closeable) inputStreamReader);
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        z.a((Closeable) inputStreamReader);
                        throw th;
                    }
                } catch (IOException unused2) {
                    inputStreamReader = inputStreamReader2;
                    z.a((Closeable) inputStreamReader);
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    inputStreamReader = inputStreamReader2;
                    z.a((Closeable) inputStreamReader);
                    throw th;
                }
            }
            if (z) {
                Uri parse = Uri.parse(uri2);
                z.a((Closeable) inputStreamReader2);
                return parse;
            }
            z.a((Closeable) inputStreamReader2);
            return null;
        } catch (IOException unused3) {
            inputStreamReader = null;
            z.a((Closeable) inputStreamReader);
            return null;
        } catch (Throwable th4) {
            th = th4;
            inputStreamReader = null;
            z.a((Closeable) inputStreamReader);
            throw th;
        }
    }

    static void a(Uri uri, Uri uri2) {
        if (uri != null && uri2 != null) {
            OutputStream outputStream = null;
            try {
                OutputStream b2 = a().b(uri.toString(), b);
                try {
                    b2.write(uri2.toString().getBytes());
                    z.a((Closeable) b2);
                } catch (IOException unused) {
                    outputStream = b2;
                    z.a((Closeable) outputStream);
                } catch (Throwable th) {
                    th = th;
                    outputStream = b2;
                    z.a((Closeable) outputStream);
                    throw th;
                }
            } catch (IOException unused2) {
                z.a((Closeable) outputStream);
            } catch (Throwable th2) {
                th = th2;
                z.a((Closeable) outputStream);
                throw th;
            }
        }
    }
}
