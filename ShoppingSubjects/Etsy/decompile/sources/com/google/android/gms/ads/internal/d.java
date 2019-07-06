package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.aqg;
import com.google.android.gms.internal.ads.aql;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.ge;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.kg;
import com.google.android.gms.internal.ads.ki;
import com.google.android.gms.internal.ads.kt;
import com.google.android.gms.internal.ads.kz;
import com.google.android.gms.internal.ads.zzang;
import org.json.JSONObject;

@bu
public final class d {
    private final Object a = new Object();
    private Context b;
    private long c = 0;

    public final void a(Context context, zzang zzang, String str, @Nullable Runnable runnable) {
        a(context, zzang, true, null, str, null, runnable);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void a(Context context, zzang zzang, boolean z, @Nullable ge geVar, String str, @Nullable String str2, @Nullable Runnable runnable) {
        if (ao.l().elapsedRealtime() - this.c < 5000) {
            gv.e("Not retrying to fetch app settings");
            return;
        }
        this.c = ao.l().elapsedRealtime();
        boolean z2 = true;
        if (geVar != null) {
            if (!(ao.l().currentTimeMillis() - geVar.a() > ((Long) ajh.f().a(akl.ct)).longValue()) && geVar.b()) {
                z2 = false;
            }
        }
        if (z2) {
            if (context == null) {
                gv.e("Context not provided to fetch application settings");
            } else if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext == null) {
                    applicationContext = context;
                }
                this.b = applicationContext;
                aqg a2 = ao.s().a(this.b, zzang).a("google.afma.config.fetchAppSettings", aql.a, aql.a);
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (!TextUtils.isEmpty(str)) {
                        jSONObject.put("app_id", str);
                    } else if (!TextUtils.isEmpty(str2)) {
                        jSONObject.put("ad_unit_id", str2);
                    }
                    jSONObject.put("is_init", z);
                    jSONObject.put("pn", context.getPackageName());
                    kt b2 = a2.b(jSONObject);
                    kt a3 = ki.a(b2, e.a, kz.b);
                    if (runnable != null) {
                        b2.a(runnable, kz.b);
                    }
                    kg.a(a3, "ConfigLoader.maybeFetchNewAppSettings");
                } catch (Exception e) {
                    gv.b("Error requesting application settings", e);
                }
            } else {
                gv.e("App settings could not be fetched. Required parameters missing");
            }
        }
    }
}
