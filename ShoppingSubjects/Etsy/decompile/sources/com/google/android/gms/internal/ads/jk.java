package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import java.util.concurrent.Callable;

final class jk implements Callable<String> {
    private final /* synthetic */ Context a;
    private final /* synthetic */ Context b;

    jk(ji jiVar, Context context, Context context2) {
        this.a = context;
        this.b = context2;
    }

    public final /* synthetic */ Object call() throws Exception {
        SharedPreferences sharedPreferences;
        boolean z = false;
        if (this.a != null) {
            gv.a("Attempting to read user agent from Google Play Services.");
            sharedPreferences = this.a.getSharedPreferences("admob_user_agent", 0);
        } else {
            gv.a("Attempting to read user agent from local cache.");
            sharedPreferences = this.b.getSharedPreferences("admob_user_agent", 0);
            z = true;
        }
        String string = sharedPreferences.getString("user_agent", "");
        if (TextUtils.isEmpty(string)) {
            gv.a("Reading user agent from WebSettings");
            string = WebSettings.getDefaultUserAgent(this.b);
            if (z) {
                sharedPreferences.edit().putString("user_agent", string).apply();
                gv.a("Persisting user agent.");
            }
        }
        return string;
    }
}
