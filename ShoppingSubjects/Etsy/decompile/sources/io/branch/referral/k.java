package io.branch.referral;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import com.etsy.android.lib.models.AppBuild;
import io.branch.referral.Defines.Jsonkey;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DeviceInfo */
class k {
    private static k q;
    private final String a;
    private final boolean b;
    private final String c;
    private final String d;
    private final int e;
    private final int f;
    private final int g;
    private final boolean h;
    private final String i;
    private final String j;
    private final int k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final String p;

    public static k a(boolean z, aa aaVar, boolean z2) {
        if (q == null) {
            q = new k(z, aaVar, z2);
        }
        return q;
    }

    public static k a() {
        return q;
    }

    private k(boolean z, aa aaVar, boolean z2) {
        if (z2) {
            this.a = aaVar.a(true);
        } else {
            this.a = aaVar.a(z);
        }
        this.b = aaVar.a();
        this.c = aaVar.f();
        this.d = aaVar.g();
        DisplayMetrics l2 = aaVar.l();
        this.e = l2.densityDpi;
        this.f = l2.heightPixels;
        this.g = l2.widthPixels;
        this.h = aaVar.m();
        this.i = aa.n();
        this.j = aaVar.j();
        this.k = aaVar.k();
        this.m = aaVar.b();
        this.n = aaVar.e();
        this.o = aaVar.h();
        this.p = aaVar.i();
        this.l = aaVar.o();
    }

    public void a(JSONObject jSONObject) {
        try {
            if (!this.a.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.HardwareID.getKey(), this.a);
                jSONObject.put(Jsonkey.IsHardwareIDReal.getKey(), this.b);
            }
            if (!this.c.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.Brand.getKey(), this.c);
            }
            if (!this.d.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.Model.getKey(), this.d);
            }
            jSONObject.put(Jsonkey.ScreenDpi.getKey(), this.e);
            jSONObject.put(Jsonkey.ScreenHeight.getKey(), this.f);
            jSONObject.put(Jsonkey.ScreenWidth.getKey(), this.g);
            jSONObject.put(Jsonkey.WiFi.getKey(), this.h);
            jSONObject.put(Jsonkey.UIMode.getKey(), this.l);
            if (!this.j.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.OS.getKey(), this.j);
            }
            jSONObject.put(Jsonkey.OSVersion.getKey(), this.k);
            if (!TextUtils.isEmpty(this.o)) {
                jSONObject.put(Jsonkey.Country.getKey(), this.o);
            }
            if (!TextUtils.isEmpty(this.p)) {
                jSONObject.put(Jsonkey.Language.getKey(), this.p);
            }
            if (!TextUtils.isEmpty(this.i)) {
                jSONObject.put(Jsonkey.LocalIP.getKey(), this.i);
            }
        } catch (JSONException unused) {
        }
    }

    public void a(Context context, m mVar, JSONObject jSONObject) {
        try {
            if (this.a.equals("bnc_no_value") || !this.b) {
                jSONObject.put(Jsonkey.UnidentifiedDevice.getKey(), true);
            } else {
                jSONObject.put(Jsonkey.AndroidID.getKey(), this.a);
            }
            if (!this.c.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.Brand.getKey(), this.c);
            }
            if (!this.d.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.Model.getKey(), this.d);
            }
            jSONObject.put(Jsonkey.ScreenDpi.getKey(), this.e);
            jSONObject.put(Jsonkey.ScreenHeight.getKey(), this.f);
            jSONObject.put(Jsonkey.ScreenWidth.getKey(), this.g);
            if (!this.j.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.OS.getKey(), this.j);
            }
            jSONObject.put(Jsonkey.OSVersion.getKey(), this.k);
            if (!TextUtils.isEmpty(this.o)) {
                jSONObject.put(Jsonkey.Country.getKey(), this.o);
            }
            if (!TextUtils.isEmpty(this.p)) {
                jSONObject.put(Jsonkey.Language.getKey(), this.p);
            }
            if (!TextUtils.isEmpty(this.i)) {
                jSONObject.put(Jsonkey.LocalIP.getKey(), this.i);
            }
            if (mVar != null && !mVar.g().equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.DeviceFingerprintID.getKey(), mVar.g());
            }
            String j2 = mVar.j();
            if (j2 != null && !j2.equals("bnc_no_value")) {
                jSONObject.put(Jsonkey.DeveloperIdentity.getKey(), mVar.j());
            }
            jSONObject.put(Jsonkey.AppVersion.getKey(), a().b());
            jSONObject.put(Jsonkey.SDK.getKey(), AppBuild.ANDROID_PLATFORM);
            jSONObject.put(Jsonkey.SdkVersion.getKey(), "2.18.1");
            jSONObject.put(Jsonkey.UserAgent.getKey(), a(context));
        } catch (JSONException unused) {
        }
    }

    public String b() {
        return this.n;
    }

    public boolean c() {
        return this.b;
    }

    public String d() {
        if (this.a.equals("bnc_no_value")) {
            return null;
        }
        return this.a;
    }

    public String e() {
        return this.j;
    }

    private String a(Context context) {
        return VERSION.SDK_INT >= 17 ? WebSettings.getDefaultUserAgent(context) : "";
    }
}
