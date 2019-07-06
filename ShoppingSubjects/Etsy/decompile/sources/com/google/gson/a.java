package com.google.gson;

import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: DefaultDateTypeAdapter */
final class a extends q<Date> {
    private final Class<? extends Date> a;
    private final DateFormat b;
    private final DateFormat c;

    a(Class<? extends Date> cls, String str) {
        this(cls, (DateFormat) new SimpleDateFormat(str, Locale.US), (DateFormat) new SimpleDateFormat(str));
    }

    public a(Class<? extends Date> cls, int i, int i2) {
        this(cls, DateFormat.getDateTimeInstance(i, i2, Locale.US), DateFormat.getDateTimeInstance(i, i2));
    }

    a(Class<? extends Date> cls, DateFormat dateFormat, DateFormat dateFormat2) {
        if (cls == Date.class || cls == java.sql.Date.class || cls == Timestamp.class) {
            this.a = cls;
            this.b = dateFormat;
            this.c = dateFormat2;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Date type must be one of ");
        sb.append(Date.class);
        sb.append(", ");
        sb.append(Timestamp.class);
        sb.append(", or ");
        sb.append(java.sql.Date.class);
        sb.append(" but was ");
        sb.append(cls);
        throw new IllegalArgumentException(sb.toString());
    }

    public void a(b bVar, Date date) throws IOException {
        if (date == null) {
            bVar.f();
            return;
        }
        synchronized (this.c) {
            bVar.b(this.b.format(date));
        }
    }

    /* renamed from: a */
    public Date b(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.f() == JsonToken.NULL) {
            aVar.j();
            return null;
        }
        Date a2 = a(aVar.h());
        if (this.a == Date.class) {
            return a2;
        }
        if (this.a == Timestamp.class) {
            return new Timestamp(a2.getTime());
        }
        if (this.a == java.sql.Date.class) {
            return new java.sql.Date(a2.getTime());
        }
        throw new AssertionError();
    }

    /* JADX INFO: used method not loaded: com.google.gson.internal.a.a.a.a(java.lang.String, java.text.ParsePosition):null, types can be incorrect */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:13|14|15|16|17) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:8|9|10|11|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = com.google.gson.internal.a.a.a.a(r4, new java.text.ParsePosition(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0020, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0021, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0027, code lost:
        throw new com.google.gson.JsonSyntaxException(r4, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r1 = r3.b.parse(r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0015 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Date a(java.lang.String r4) {
        /*
            r3 = this;
            java.text.DateFormat r0 = r3.c
            monitor-enter(r0)
            java.text.DateFormat r1 = r3.c     // Catch:{ ParseException -> 0x000d }
            java.util.Date r1 = r1.parse(r4)     // Catch:{ ParseException -> 0x000d }
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            return r1
        L_0x000b:
            r4 = move-exception
            goto L_0x0028
        L_0x000d:
            java.text.DateFormat r1 = r3.b     // Catch:{ ParseException -> 0x0015 }
            java.util.Date r1 = r1.parse(r4)     // Catch:{ ParseException -> 0x0015 }
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            return r1
        L_0x0015:
            java.text.ParsePosition r1 = new java.text.ParsePosition     // Catch:{ ParseException -> 0x0021 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ ParseException -> 0x0021 }
            java.util.Date r1 = com.google.gson.internal.a.a.a.a(r4, r1)     // Catch:{ ParseException -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            return r1
        L_0x0021:
            r1 = move-exception
            com.google.gson.JsonSyntaxException r2 = new com.google.gson.JsonSyntaxException     // Catch:{ all -> 0x000b }
            r2.<init>(r4, r1)     // Catch:{ all -> 0x000b }
            throw r2     // Catch:{ all -> 0x000b }
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x000b }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.a.a(java.lang.String):java.util.Date");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DefaultDateTypeAdapter");
        sb.append('(');
        sb.append(this.c.getClass().getSimpleName());
        sb.append(')');
        return sb.toString();
    }
}
