package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import java.util.concurrent.TimeUnit;
import org.apache.commons.math3.geometry.VectorFormat;
import org.json.JSONException;
import org.json.JSONObject;

final class q {
    private static final long b = TimeUnit.DAYS.toMillis(7);
    final String a;
    private final String c;
    private final long d;

    private q(String str, String str2, long j) {
        this.a = str;
        this.c = str2;
        this.d = j;
    }

    static q a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!str.startsWith(VectorFormat.DEFAULT_PREFIX)) {
            return new q(str, null, 0);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new q(jSONObject.getString("token"), jSONObject.getString("appVersion"), jSONObject.getLong("timestamp"));
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(23 + String.valueOf(valueOf).length());
            sb.append("Failed to parse token: ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    static String a(String str, String str2, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("token", str);
            jSONObject.put("appVersion", str2);
            jSONObject.put("timestamp", j);
            return jSONObject.toString();
        } catch (JSONException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(24 + String.valueOf(valueOf).length());
            sb.append("Failed to encode token: ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean b(String str) {
        return System.currentTimeMillis() > this.d + b || !str.equals(this.c);
    }
}
