package com.facebook.internal;

import android.util.Log;
import com.facebook.LoggingBehavior;
import com.facebook.f;
import com.google.a.a.a.a.a.a;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: Logger */
public class t {
    private static final HashMap<String, String> a = new HashMap<>();
    private final LoggingBehavior b;
    private final String c;
    private StringBuilder d;
    private int e = 3;

    public static synchronized void a(String str, String str2) {
        synchronized (t.class) {
            a.put(str, str2);
        }
    }

    public static synchronized void a(String str) {
        synchronized (t.class) {
            if (!f.a(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
                a(str, "ACCESS_TOKEN_REMOVED");
            }
        }
    }

    public static void a(LoggingBehavior loggingBehavior, String str, String str2) {
        a(loggingBehavior, 3, str, str2);
    }

    public static void a(LoggingBehavior loggingBehavior, String str, String str2, Object... objArr) {
        if (f.a(loggingBehavior)) {
            a(loggingBehavior, 3, str, String.format(str2, objArr));
        }
    }

    public static void a(LoggingBehavior loggingBehavior, int i, String str, String str2, Object... objArr) {
        if (f.a(loggingBehavior)) {
            a(loggingBehavior, i, str, String.format(str2, objArr));
        }
    }

    public static void a(LoggingBehavior loggingBehavior, int i, String str, String str2) {
        if (f.a(loggingBehavior)) {
            String d2 = d(str2);
            if (!str.startsWith("FacebookSDK.")) {
                StringBuilder sb = new StringBuilder();
                sb.append("FacebookSDK.");
                sb.append(str);
                str = sb.toString();
            }
            Log.println(i, str, d2);
            if (loggingBehavior == LoggingBehavior.DEVELOPER_ERRORS) {
                a.a(new Exception());
            }
        }
    }

    private static synchronized String d(String str) {
        synchronized (t.class) {
            for (Entry entry : a.entrySet()) {
                str = str.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
            }
        }
        return str;
    }

    public t(LoggingBehavior loggingBehavior, String str) {
        aa.a(str, "tag");
        this.b = loggingBehavior;
        StringBuilder sb = new StringBuilder();
        sb.append("FacebookSDK.");
        sb.append(str);
        this.c = sb.toString();
        this.d = new StringBuilder();
    }

    public void a() {
        b(this.d.toString());
        this.d = new StringBuilder();
    }

    public void b(String str) {
        a(this.b, this.e, this.c, str);
    }

    public void c(String str) {
        if (b()) {
            this.d.append(str);
        }
    }

    public void a(String str, Object... objArr) {
        if (b()) {
            this.d.append(String.format(str, objArr));
        }
    }

    public void a(String str, Object obj) {
        a("  %s:\t%s\n", str, obj);
    }

    private boolean b() {
        return f.a(this.b);
    }
}
