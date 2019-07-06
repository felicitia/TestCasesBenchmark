package com.google.gson.internal.a;

import com.google.gson.a.a;
import com.google.gson.e;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: DateTypeAdapter */
public final class c extends q<Date> {
    public static final r a = new r() {
        public <T> q<T> a(e eVar, a<T> aVar) {
            if (aVar.a() == Date.class) {
                return new c();
            }
            return null;
        }
    };
    private final DateFormat b = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat c = DateFormat.getDateTimeInstance(2, 2);

    /* renamed from: a */
    public Date b(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.f() != JsonToken.NULL) {
            return a(aVar.h());
        }
        aVar.j();
        return null;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:6|7|8|9) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        return com.google.gson.internal.a.a.a.a(r3, new java.text.ParsePosition(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        throw new com.google.gson.JsonSyntaxException(r3, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        return r2.b.parse(r3);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0013 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.Date a(java.lang.String r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.text.DateFormat r0 = r2.c     // Catch:{ ParseException -> 0x000b }
            java.util.Date r0 = r0.parse(r3)     // Catch:{ ParseException -> 0x000b }
            monitor-exit(r2)
            return r0
        L_0x0009:
            r3 = move-exception
            goto L_0x0026
        L_0x000b:
            java.text.DateFormat r0 = r2.b     // Catch:{ ParseException -> 0x0013 }
            java.util.Date r0 = r0.parse(r3)     // Catch:{ ParseException -> 0x0013 }
            monitor-exit(r2)
            return r0
        L_0x0013:
            java.text.ParsePosition r0 = new java.text.ParsePosition     // Catch:{ ParseException -> 0x001f }
            r1 = 0
            r0.<init>(r1)     // Catch:{ ParseException -> 0x001f }
            java.util.Date r0 = com.google.gson.internal.a.a.a.a(r3, r0)     // Catch:{ ParseException -> 0x001f }
            monitor-exit(r2)
            return r0
        L_0x001f:
            r0 = move-exception
            com.google.gson.JsonSyntaxException r1 = new com.google.gson.JsonSyntaxException     // Catch:{ all -> 0x0009 }
            r1.<init>(r3, r0)     // Catch:{ all -> 0x0009 }
            throw r1     // Catch:{ all -> 0x0009 }
        L_0x0026:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.a.c.a(java.lang.String):java.util.Date");
    }

    public synchronized void a(b bVar, Date date) throws IOException {
        if (date == null) {
            bVar.f();
        } else {
            bVar.b(this.b.format(date));
        }
    }
}
