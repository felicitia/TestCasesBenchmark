package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.ao;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;

@bu
public final class akn {
    private String a;
    private Map<String, String> b;
    private Context c = null;
    private String d = null;

    public akn(Context context, String str) {
        this.c = context;
        this.d = str;
        this.a = (String) ajh.f().a(akl.O);
        this.b = new LinkedHashMap();
        this.b.put("s", "gmob_sdk");
        this.b.put("v", "3");
        this.b.put("os", VERSION.RELEASE);
        this.b.put("sdk", VERSION.SDK);
        ao.e();
        this.b.put("device", hd.b());
        this.b.put("app", context.getApplicationContext() != null ? context.getApplicationContext().getPackageName() : context.getPackageName());
        Map<String, String> map = this.b;
        String str2 = "is_lite_sdk";
        ao.e();
        map.put(str2, hd.k(context) ? "1" : "0");
        Future a2 = ao.p().a(this.c);
        try {
            a2.get();
            this.b.put("network_coarse", Integer.toString(((ds) a2.get()).n));
            this.b.put("network_fine", Integer.toString(((ds) a2.get()).o));
        } catch (Exception e) {
            ao.i().a((Throwable) e, "CsiConfiguration.CsiConfiguration");
        }
    }

    /* access modifiers changed from: 0000 */
    public final String a() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public final Context b() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public final String c() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public final Map<String, String> d() {
        return this.b;
    }
}
