package com.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Bitly */
public final class a {
    private static a a = new a();
    private static String h;
    private String b;
    private String c;
    private List<String> d = new ArrayList();
    private List<String> e = new ArrayList();
    private C0006a f;
    private b g;

    /* renamed from: com.a.a$a reason: collision with other inner class name */
    /* compiled from: Bitly */
    public interface C0006a {
        void a(d dVar);

        void a(f fVar);
    }

    public static void a(@NonNull final Context context, @NonNull String str, @NonNull List<String> list, @NonNull List<String> list2, C0006a aVar) {
        a(context);
        a.b = str;
        a.c = Secure.getString(context.getContentResolver(), "android_id");
        a.d.addAll(list);
        a.e.addAll(list2);
        a.f = aVar;
        a.g = new b(context);
        a.g.a(a.b, a.c, new a() {
            public void a(boolean z) {
                c.a(context, z);
            }
        });
        Log.d("BitlySDK", String.format("Bitly SDK initialized with App ID: %s and Device ID: %s", new Object[]{a.b, a.c}));
    }

    public static void a(Intent intent) {
        c.a(intent);
    }

    static String a() {
        return a.b;
    }

    static String b() {
        return a.c;
    }

    static C0006a c() {
        return a.f;
    }

    static boolean a(@NonNull String str) {
        return a.d.contains(Uri.parse(str).getHost());
    }

    static boolean b(@NonNull String str) {
        Uri parse = Uri.parse(str);
        for (String str2 : a.e) {
            if (parse.getScheme().equals(str2) || (str2.contains("://") && parse.toString().startsWith(str2))) {
                return true;
            }
        }
        return false;
    }

    private static void a(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString("com.bitly.custom.base.url", null);
        if (!TextUtils.isEmpty(string)) {
            h = string;
        }
    }

    protected static String d() {
        if (TextUtils.isEmpty(h)) {
            h = "https://bit.ly/";
        }
        return h;
    }
}
