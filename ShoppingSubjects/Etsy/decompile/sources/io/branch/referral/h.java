package io.branch.referral;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import com.etsy.android.lib.models.AppBuild;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BranchUtil */
public class h {
    static boolean a = false;

    /* compiled from: BranchUtil */
    public static class a {
        private final JSONObject a;

        public a(JSONObject jSONObject) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2 = new JSONObject(jSONObject.toString());
            } catch (JSONException unused) {
            }
            this.a = jSONObject2;
        }

        public JSONObject a() {
            return this.a;
        }

        public Integer a(String str, Integer num) {
            if (!this.a.has(str)) {
                return num;
            }
            Integer valueOf = Integer.valueOf(this.a.optInt(str));
            this.a.remove(str);
            return valueOf;
        }

        public String a(String str) {
            String optString = this.a.optString(str);
            this.a.remove(str);
            return optString;
        }

        public long b(String str) {
            long optLong = this.a.optLong(str);
            this.a.remove(str);
            return optLong;
        }

        public Double a(String str, Double d) {
            if (!this.a.has(str)) {
                return d;
            }
            Double valueOf = Double.valueOf(this.a.optDouble(str));
            this.a.remove(str);
            return valueOf;
        }

        public boolean c(String str) {
            boolean optBoolean = this.a.optBoolean(str);
            this.a.remove(str);
            return optBoolean;
        }

        public JSONArray d(String str) {
            JSONArray optJSONArray = this.a.optJSONArray(str);
            this.a.remove(str);
            return optJSONArray;
        }

        public Object e(String str) {
            Object opt = this.a.opt(str);
            this.a.remove(str);
            return opt;
        }
    }

    public static boolean a(Context context) {
        boolean parseBoolean;
        if (a) {
            return a;
        }
        String str = "io.branch.sdk.TestMode";
        boolean z = false;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null || !applicationInfo.metaData.containsKey(str)) {
                Resources resources = context.getResources();
                parseBoolean = Boolean.parseBoolean(resources.getString(resources.getIdentifier(str, "string", context.getPackageName())));
            } else {
                parseBoolean = applicationInfo.metaData.getBoolean(str, false);
            }
            z = parseBoolean;
        } catch (Exception unused) {
        }
        return z;
    }

    static JSONObject a(JSONObject jSONObject) {
        return b(jSONObject);
    }

    static JSONObject b(JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("source", AppBuild.ANDROID_PLATFORM);
        } catch (JSONException e) {
            com.google.a.a.a.a.a.a.a(e);
        }
        return jSONObject;
    }

    public static Drawable a(@NonNull Context context, @DrawableRes int i) {
        if (VERSION.SDK_INT >= 21) {
            return context.getResources().getDrawable(i, context.getTheme());
        }
        return context.getResources().getDrawable(i);
    }

    public static int b(Context context, int i) {
        return Math.round(((float) i) * (context.getResources().getDisplayMetrics().xdpi / 160.0f));
    }
}
