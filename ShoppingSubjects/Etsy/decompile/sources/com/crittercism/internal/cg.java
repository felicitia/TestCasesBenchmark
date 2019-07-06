package com.crittercism.internal;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

public final class cg {
    public SharedPreferences a;

    public cg(Context context) {
        this.a = context.getSharedPreferences("com.crittercism.usersettings", 0);
        if (this.a.contains("optOutStatusSettings")) {
            try {
                this.a.edit().putBoolean("isOptedOut", new JSONObject(this.a.getString("optOutStatusSettings", null)).getBoolean("optOutStatus")).commit();
            } catch (JSONException unused) {
            }
            this.a.edit().remove("optOutStatusSettings").commit();
        }
    }

    public final boolean a() {
        return this.a.getBoolean("isOptedOut", false);
    }
}
