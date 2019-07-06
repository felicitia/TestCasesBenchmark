package com.google.ads.conversiontracking;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.text.TextUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@TargetApi(4)
public class g {
    private static final Map<String, String> a = new HashMap();
    private static boolean b = false;
    private static long c = -1;
    private static boolean d = true;
    private static boolean e = false;
    private static final Object f = new Object();
    private static c g = null;
    private static boolean h = false;

    public static class a {
        /* access modifiers changed from: private */
        public final String a;
        /* access modifiers changed from: private */
        public final String b;
        /* access modifiers changed from: private */
        public final long c;

        private a(String str, String str2, long j) {
            this.a = str;
            this.b = str2;
            this.c = j;
        }

        public a(String str, String str2) {
            this(str, str2, g.a());
        }

        public boolean a() {
            return this.c + 7776000000L < g.a();
        }

        public static a a(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            String[] split = str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            if (split.length != 3) {
                return null;
            }
            try {
                a aVar = new a(split[0], split[1], Long.parseLong(split[2]));
                if (aVar.a()) {
                    return null;
                }
                return aVar;
            } catch (NumberFormatException unused) {
                return null;
            }
        }
    }

    public static class b {
        /* access modifiers changed from: private */
        public final String a;
        /* access modifiers changed from: private */
        public final a b;

        public b(String str, a aVar) {
            this.a = str;
            this.b = aVar;
        }
    }

    public enum d {
        DOUBLECLICK_AUDIENCE,
        DOUBLECLICK_CONVERSION,
        GOOGLE_CONVERSION,
        IAP_CONVERSION;

        public static d[] a() {
            return (d[]) e.clone();
        }
    }

    public static b a(Uri uri) {
        if (uri == null) {
            return null;
        }
        String queryParameter = uri.getQueryParameter("referrer");
        if (TextUtils.isEmpty(queryParameter)) {
            return null;
        }
        String str = "http://hostname/?";
        String valueOf = String.valueOf(queryParameter);
        Uri parse = Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        String queryParameter2 = parse.getQueryParameter("conv");
        String queryParameter3 = parse.getQueryParameter("gclid");
        if (TextUtils.isEmpty(queryParameter2) || TextUtils.isEmpty(queryParameter3)) {
            return null;
        }
        String queryParameter4 = parse.getQueryParameter("ai");
        if (queryParameter4 == null) {
            queryParameter4 = "";
        }
        return new b(queryParameter2, new a(queryParameter3, queryParameter4));
    }

    private static List<String> a(SharedPreferences sharedPreferences) {
        ArrayList arrayList = new ArrayList();
        for (Entry entry : sharedPreferences.getAll().entrySet()) {
            if (a.a((String) entry.getValue()) == null) {
                arrayList.add(entry.getKey());
            }
        }
        return arrayList;
    }

    public static boolean a(Context context, final b bVar) {
        if (bVar == null) {
            return false;
        }
        final SharedPreferences sharedPreferences = context.getSharedPreferences("google_conversion_click_referrer", 0);
        final List<String> a2 = a(sharedPreferences);
        if (sharedPreferences.getString(bVar.a, null) == null && sharedPreferences.getAll().size() == 100 && a2.isEmpty()) {
            return false;
        }
        String b2 = bVar.b.a;
        String valueOf = String.valueOf(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        String a3 = bVar.b.b;
        String valueOf2 = String.valueOf(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        long c2 = bVar.b.c;
        StringBuilder sb = new StringBuilder(20 + String.valueOf(b2).length() + String.valueOf(valueOf).length() + String.valueOf(a3).length() + String.valueOf(valueOf2).length());
        sb.append(b2);
        sb.append(valueOf);
        sb.append(a3);
        sb.append(valueOf2);
        sb.append(c2);
        final String sb2 = sb.toString();
        synchronized (a) {
            for (String remove : a2) {
                a.remove(remove);
            }
            a.put(bVar.a, sb2);
        }
        new Thread(new Runnable() {
            public void run() {
                Editor edit = sharedPreferences.edit();
                for (String remove : a2) {
                    edit.remove(remove);
                }
                edit.putString(bVar.a, sb2);
                edit.commit();
            }
        }).start();
        return true;
    }

    static long a() {
        if (!b || c < 0) {
            return System.currentTimeMillis();
        }
        return c;
    }
}
