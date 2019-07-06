package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class ie {
    private final Object a = new Object();
    private String b = "";
    private String c = "";
    private boolean d = false;
    @VisibleForTesting
    private String e = "";

    private final String a(Context context) {
        String str;
        synchronized (this.a) {
            if (TextUtils.isEmpty(this.b)) {
                ao.e();
                this.b = hd.c(context, "debug_signals_id.txt");
                if (TextUtils.isEmpty(this.b)) {
                    ao.e();
                    this.b = hd.a();
                    ao.e();
                    hd.b(context, "debug_signals_id.txt", this.b);
                }
            }
            str = this.b;
        }
        return str;
    }

    @VisibleForTesting
    private final void a(Context context, String str, boolean z, boolean z2) {
        if (!(context instanceof Activity)) {
            gv.d("Can not create dialog without Activity Context");
            return;
        }
        Handler handler = hd.a;
        Cif ifVar = new Cif(this, context, str, z, z2);
        handler.post(ifVar);
    }

    @VisibleForTesting
    private final boolean b(Context context, String str, String str2) {
        String d2 = d(context, c(context, (String) ajh.f().a(akl.cT), str, str2).toString(), str2);
        if (TextUtils.isEmpty(d2)) {
            gv.b("Not linked for in app preview.");
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(d2.trim());
            String optString = jSONObject.optString("gct");
            this.e = jSONObject.optString("status");
            synchronized (this.a) {
                this.c = optString;
            }
            return true;
        } catch (JSONException e2) {
            gv.c("Fail to get in app preview response json.", e2);
            return false;
        }
    }

    private final Uri c(Context context, String str, String str2, String str3) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        buildUpon.appendQueryParameter("linkedDeviceId", a(context));
        buildUpon.appendQueryParameter("adSlotPath", str2);
        buildUpon.appendQueryParameter("afmaVersion", str3);
        return buildUpon.build();
    }

    @VisibleForTesting
    private final boolean c(Context context, String str, String str2) {
        String d2 = d(context, c(context, (String) ajh.f().a(akl.cU), str, str2).toString(), str2);
        if (TextUtils.isEmpty(d2)) {
            gv.b("Not linked for debug signals.");
            return false;
        }
        try {
            boolean equals = "1".equals(new JSONObject(d2.trim()).optString("debug_mode"));
            synchronized (this.a) {
                this.d = equals;
            }
            return equals;
        } catch (JSONException e2) {
            gv.c("Fail to get debug mode response json.", e2);
            return false;
        }
    }

    @VisibleForTesting
    private static String d(Context context, String str, String str2) {
        String str3;
        Throwable e2;
        String str4;
        String str5;
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", ao.e().b(context, str2));
        kt a2 = new in(context).a(str, (Map<String, String>) hashMap);
        try {
            return (String) a2.get((long) ((Integer) ajh.f().a(akl.cW)).intValue(), TimeUnit.MILLISECONDS);
        } catch (TimeoutException e3) {
            e2 = e3;
            str5 = "Timeout while retriving a response from: ";
            str4 = String.valueOf(str);
            if (str4.length() == 0) {
                str3 = new String(str5);
                gv.b(str3, e2);
                a2.cancel(true);
                return null;
            }
            str3 = str5.concat(str4);
            gv.b(str3, e2);
            a2.cancel(true);
            return null;
        } catch (InterruptedException e4) {
            e2 = e4;
            str5 = "Interrupted while retriving a response from: ";
            str4 = String.valueOf(str);
            if (str4.length() == 0) {
                str3 = new String(str5);
                gv.b(str3, e2);
                a2.cancel(true);
                return null;
            }
            str3 = str5.concat(str4);
            gv.b(str3, e2);
            a2.cancel(true);
            return null;
        } catch (Exception e5) {
            String str6 = "Error retriving a response from: ";
            String valueOf = String.valueOf(str);
            gv.b(valueOf.length() != 0 ? str6.concat(valueOf) : new String(str6), e5);
            return null;
        }
    }

    private final void e(Context context, String str, String str2) {
        ao.e();
        hd.a(context, c(context, (String) ajh.f().a(akl.cS), str, str2));
    }

    public final String a() {
        String str;
        synchronized (this.a) {
            str = this.c;
        }
        return str;
    }

    public final void a(Context context, String str, String str2) {
        if (!b(context, str, str2)) {
            a(context, "In-app preview failed to load because of a system error. Please try again later.", true, true);
        } else if ("2".equals(this.e)) {
            gv.b("Creative is not pushed for this device.");
            a(context, "There was no creative pushed from DFP to the device.", false, false);
        } else if ("1".equals(this.e)) {
            gv.b("The app is not linked for creative preview.");
            e(context, str, str2);
        } else {
            if ("0".equals(this.e)) {
                gv.b("Device is linked for in app preview.");
                a(context, "The device is successfully linked for creative preview.", false, true);
            }
        }
    }

    public final void a(Context context, String str, String str2, String str3) {
        boolean b2 = b();
        if (c(context, str, str2)) {
            if (!b2 && !TextUtils.isEmpty(str3)) {
                b(context, str2, str3, str);
            }
            gv.b("Device is linked for debug signals.");
            a(context, "The device is successfully linked for troubleshooting.", false, true);
            return;
        }
        e(context, str, str2);
    }

    public final void b(Context context, String str, String str2, String str3) {
        Builder buildUpon = c(context, (String) ajh.f().a(akl.cV), str3, str).buildUpon();
        buildUpon.appendQueryParameter("debugData", str2);
        ao.e();
        hd.a(context, str, buildUpon.build().toString());
    }

    public final boolean b() {
        boolean z;
        synchronized (this.a) {
            z = this.d;
        }
        return z;
    }
}
