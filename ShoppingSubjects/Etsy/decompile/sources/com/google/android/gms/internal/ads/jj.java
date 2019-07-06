package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.google.android.gms.common.util.SharedPreferencesUtils;
import java.util.concurrent.Callable;

final class jj implements Callable<String> {
    private final /* synthetic */ Context a;

    jj(ji jiVar, Context context) {
        this.a = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        SharedPreferences sharedPreferences = this.a.getSharedPreferences("admob_user_agent", 0);
        String string = sharedPreferences.getString("user_agent", "");
        if (TextUtils.isEmpty(string)) {
            gv.a("User agent is not initialized on Google Play Services. Initializing.");
            String defaultUserAgent = WebSettings.getDefaultUserAgent(this.a);
            SharedPreferencesUtils.publishWorldReadableSharedPreferences(this.a, sharedPreferences.edit().putString("user_agent", defaultUserAgent), "admob_user_agent");
            return defaultUserAgent;
        }
        gv.a("User agent is already initialized on Google Play Services.");
        return string;
    }
}
