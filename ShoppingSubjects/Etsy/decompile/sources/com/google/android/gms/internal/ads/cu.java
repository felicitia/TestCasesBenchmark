package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.gmsg.HttpClient;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.ads.internal.gmsg.b;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class cu extends gq {
    @VisibleForTesting
    private static final long a = TimeUnit.SECONDS.toMillis(10);
    private static final Object b = new Object();
    @VisibleForTesting
    private static boolean c = false;
    /* access modifiers changed from: private */
    public static apg d;
    private static HttpClient e;
    /* access modifiers changed from: private */
    public static b f;
    private static ae<Object> g;
    /* access modifiers changed from: private */
    public final bw h;
    private final cr i;
    private final Object j = new Object();
    private final Context k;
    /* access modifiers changed from: private */
    public apt l;
    private ahl m;

    public cu(Context context, cr crVar, bw bwVar, ahl ahl) {
        super(true);
        this.h = bwVar;
        this.k = context;
        this.i = crVar;
        this.m = ahl;
        synchronized (b) {
            if (!c) {
                f = new b();
                e = new HttpClient(context.getApplicationContext(), crVar.j);
                g = new dc();
                apg apg = new apg(this.k.getApplicationContext(), this.i.j, (String) ajh.f().a(akl.a), new db(), new da());
                d = apg;
                c = true;
            }
        }
    }

    private final zzaej a(zzaef zzaef) {
        ao.e();
        String a2 = hd.a();
        JSONObject a3 = a(zzaef, a2);
        if (a3 == null) {
            return new zzaej(0);
        }
        long elapsedRealtime = ao.l().elapsedRealtime();
        Future a4 = f.a(a2);
        jp.a.post(new cw(this, a3, a2));
        try {
            JSONObject jSONObject = (JSONObject) a4.get(a - (ao.l().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new zzaej(-1);
            }
            zzaej a5 = dj.a(this.k, zzaef, jSONObject.toString());
            if (a5.errorCode != -3 && TextUtils.isEmpty(a5.zzceo)) {
                a5 = new zzaej(3);
            }
            return a5;
        } catch (InterruptedException | CancellationException unused) {
            return new zzaej(-1);
        } catch (TimeoutException unused2) {
            return new zzaej(2);
        } catch (ExecutionException unused3) {
            return new zzaej(0);
        }
    }

    private final JSONObject a(zzaef zzaef, String str) {
        ds dsVar;
        Info info;
        Bundle bundle = zzaef.zzccv.extras.getBundle("sdk_less_server_data");
        if (bundle == null) {
            return null;
        }
        try {
            dsVar = (ds) ao.p().a(this.k).get();
        } catch (Exception e2) {
            gv.c("Error grabbing device info: ", e2);
            dsVar = null;
        }
        Context context = this.k;
        dd ddVar = new dd();
        ddVar.j = zzaef;
        ddVar.k = dsVar;
        JSONObject a2 = dj.a(context, ddVar);
        if (a2 == null) {
            return null;
        }
        try {
            info = AdvertisingIdClient.getAdvertisingIdInfo(this.k);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e3) {
            gv.c("Cannot get advertising id info", e3);
            info = null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("request_id", str);
        hashMap.put("request_param", a2);
        hashMap.put("data", bundle);
        if (info != null) {
            hashMap.put("adid", info.getId());
            hashMap.put(ResponseConstants.LAT, Integer.valueOf(info.isLimitAdTrackingEnabled() ? 1 : 0));
        }
        try {
            return ao.e().a((Map<String, ?>) hashMap);
        } catch (JSONException unused) {
            return null;
        }
    }

    protected static void a(aou aou) {
        aou.a("/loadAd", f);
        aou.a("/fetchHttpRequest", e);
        aou.a("/invalidRequest", g);
    }

    protected static void b(aou aou) {
        aou.b("/loadAd", f);
        aou.b("/fetchHttpRequest", e);
        aou.b("/invalidRequest", g);
    }

    public final void a() {
        gv.b("SdkLessAdLoaderBackgroundTask started.");
        String j2 = ao.B().j(this.k);
        zzaef zzaef = new zzaef(this.i, -1, ao.B().h(this.k), ao.B().i(this.k), j2);
        ao.B().f(this.k, j2);
        zzaej a2 = a(zzaef);
        long elapsedRealtime = ao.l().elapsedRealtime();
        zzaef zzaef2 = zzaef;
        gb gbVar = new gb(zzaef2, a2, null, null, a2.errorCode, elapsedRealtime, a2.zzceu, null, this.m);
        jp.a.post(new cv(this, gbVar));
    }

    public final void c_() {
        synchronized (this.j) {
            jp.a.post(new cz(this));
        }
    }
}
