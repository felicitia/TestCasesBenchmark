package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class ai implements Callable<ga> {
    @VisibleForTesting
    private static long a = 10;
    private final Context b;
    private final in c;
    /* access modifiers changed from: private */
    public final zzbc d;
    private final ack e;
    private final az f;
    private final Object g = new Object();
    private final gb h;
    private final aky i;
    private boolean j;
    private int k;
    private List<String> l;
    private JSONObject m;
    private String n;
    @Nullable
    private String o;

    public ai(Context context, zzbc zzbc, in inVar, ack ack, gb gbVar, aky aky) {
        this.b = context;
        this.d = zzbc;
        this.c = inVar;
        this.h = gbVar;
        this.e = ack;
        this.i = aky;
        this.f = zzbc.zzdr();
        this.j = false;
        this.k = -2;
        this.l = null;
        this.n = null;
        this.o = null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003e, code lost:
        if (r3.length() != 0) goto L_0x0044;
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007d A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a9 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0157 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0158 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01c2 A[Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01f1  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.ga call() {
        /*
            r15 = this;
            r0 = 0
            r1 = 0
            com.google.android.gms.ads.internal.zzbc r2 = r15.d     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r11 = r2.getUuid()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            boolean r2 = r15.b()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r2 != 0) goto L_0x006e
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.gb r3 = r15.h     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzaej r3 = r3.b     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r3 = r3.zzceo     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r2.<init>(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.gb r4 = r15.h     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzaej r4 = r4.b     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r4 = r4.zzceo     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r3.<init>(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            int r4 = r3.length()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r4 == 0) goto L_0x0040
            java.lang.String r4 = "ads"
            org.json.JSONArray r3 = r3.optJSONArray(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r3 == 0) goto L_0x0037
            org.json.JSONObject r3 = r3.optJSONObject(r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            goto L_0x0038
        L_0x0037:
            r3 = r1
        L_0x0038:
            if (r3 == 0) goto L_0x0040
            int r3 = r3.length()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r3 != 0) goto L_0x0044
        L_0x0040:
            r3 = 3
            r15.a(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
        L_0x0044:
            com.google.android.gms.internal.ads.az r3 = r15.f     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.kt r2 = r3.a(r2)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            long r3 = a     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.Object r2 = r2.get(r3, r5)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r3 = "success"
            boolean r3 = r2.optBoolean(r3, r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r3 == 0) goto L_0x006e
            java.lang.String r3 = "json"
            org.json.JSONObject r2 = r2.getJSONObject(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r3 = "ads"
            org.json.JSONArray r2 = r2.optJSONArray(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            org.json.JSONObject r2 = r2.getJSONObject(r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r8 = r2
            goto L_0x006f
        L_0x006e:
            r8 = r1
        L_0x006f:
            java.lang.String r2 = "enable_omid"
            boolean r2 = r8.optBoolean(r2)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r2 != 0) goto L_0x007d
        L_0x0077:
            com.google.android.gms.internal.ads.ks r3 = com.google.android.gms.internal.ads.ki.a(r1)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r12 = r3
            goto L_0x00a3
        L_0x007d:
            java.lang.String r3 = "omid_settings"
            org.json.JSONObject r3 = r8.optJSONObject(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r3 != 0) goto L_0x0086
            goto L_0x0077
        L_0x0086:
            java.lang.String r4 = "omid_html"
            java.lang.String r3 = r3.optString(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r4 == 0) goto L_0x0093
            goto L_0x0077
        L_0x0093:
            com.google.android.gms.internal.ads.le r4 = new com.google.android.gms.internal.ads.le     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r4.<init>()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.util.concurrent.Executor r5 = com.google.android.gms.internal.ads.kz.a     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.aj r6 = new com.google.android.gms.internal.ads.aj     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r6.<init>(r15, r4, r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r5.execute(r6)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r12 = r4
        L_0x00a3:
            boolean r3 = r15.b()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r3 != 0) goto L_0x014c
            if (r8 != 0) goto L_0x00ad
            goto L_0x014c
        L_0x00ad:
            java.lang.String r3 = "template_id"
            java.lang.String r3 = r8.getString(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.gb r4 = r15.h     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzaef r4 = r4.a     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzpl r4 = r4.zzadj     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r4 == 0) goto L_0x00c4
            com.google.android.gms.internal.ads.gb r4 = r15.h     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzaef r4 = r4.a     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzpl r4 = r4.zzadj     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            boolean r4 = r4.zzbjn     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            goto L_0x00c5
        L_0x00c4:
            r4 = r0
        L_0x00c5:
            com.google.android.gms.internal.ads.gb r5 = r15.h     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzaef r5 = r5.a     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzpl r5 = r5.zzadj     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r5 == 0) goto L_0x00d6
            com.google.android.gms.internal.ads.gb r5 = r15.h     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzaef r5 = r5.a     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzpl r5 = r5.zzadj     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            boolean r5 = r5.zzbjp     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            goto L_0x00d7
        L_0x00d6:
            r5 = r0
        L_0x00d7:
            java.lang.String r6 = "2"
            boolean r6 = r6.equals(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r6 == 0) goto L_0x00e9
            com.google.android.gms.internal.ads.ba r3 = new com.google.android.gms.internal.ads.ba     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.gb r6 = r15.h     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            boolean r6 = r6.j     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r3.<init>(r4, r5, r6)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            goto L_0x014d
        L_0x00e9:
            java.lang.String r6 = "1"
            boolean r6 = r6.equals(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r6 == 0) goto L_0x00fb
            com.google.android.gms.internal.ads.bb r3 = new com.google.android.gms.internal.ads.bb     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.gb r6 = r15.h     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            boolean r6 = r6.j     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r3.<init>(r4, r5, r6)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            goto L_0x014d
        L_0x00fb:
            java.lang.String r5 = "3"
            boolean r3 = r5.equals(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r3 == 0) goto L_0x0149
            java.lang.String r3 = "custom_template_id"
            java.lang.String r3 = r8.getString(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.le r5 = new com.google.android.gms.internal.ads.le     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r5.<init>()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            android.os.Handler r6 = com.google.android.gms.internal.ads.hd.a     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.al r7 = new com.google.android.gms.internal.ads.al     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r7.<init>(r15, r5, r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r6.post(r7)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            long r6 = a     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.Object r3 = r5.get(r6, r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r3 == 0) goto L_0x0128
            com.google.android.gms.internal.ads.bc r3 = new com.google.android.gms.internal.ads.bc     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r3.<init>(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            goto L_0x014d
        L_0x0128:
            java.lang.String r3 = "No handler for custom template: "
            java.lang.String r4 = "custom_template_id"
            java.lang.String r4 = r8.getString(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            int r5 = r4.length()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r5 == 0) goto L_0x013f
            java.lang.String r3 = r3.concat(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            goto L_0x0145
        L_0x013f:
            java.lang.String r4 = new java.lang.String     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r4.<init>(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r3 = r4
        L_0x0145:
            com.google.android.gms.internal.ads.gv.c(r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            goto L_0x014c
        L_0x0149:
            r15.a(r0)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
        L_0x014c:
            r3 = r1
        L_0x014d:
            boolean r4 = r15.b()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r4 != 0) goto L_0x01bd
            if (r3 == 0) goto L_0x01bd
            if (r8 != 0) goto L_0x0158
            goto L_0x01bd
        L_0x0158:
            java.lang.String r4 = "tracking_urls_and_actions"
            org.json.JSONObject r4 = r8.getJSONObject(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r5 = "impression_tracking_urls"
            org.json.JSONArray r5 = r4.optJSONArray(r5)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r5 != 0) goto L_0x0168
            r6 = r1
            goto L_0x017e
        L_0x0168:
            int r6 = r5.length()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r7 = r0
        L_0x016f:
            int r9 = r5.length()     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r7 >= r9) goto L_0x017e
            java.lang.String r9 = r5.getString(r7)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r6[r7] = r9     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            int r7 = r7 + 1
            goto L_0x016f
        L_0x017e:
            if (r6 != 0) goto L_0x0182
            r5 = r1
            goto L_0x0186
        L_0x0182:
            java.util.List r5 = java.util.Arrays.asList(r6)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
        L_0x0186:
            r15.l = r5     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r5 = "active_view"
            org.json.JSONObject r4 = r4.optJSONObject(r5)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r15.m = r4     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r4 = "debug_signals"
            java.lang.String r4 = r8.optString(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r15.n = r4     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r4 = "omid_settings"
            java.lang.String r4 = r8.optString(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r15.o = r4     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.aln r13 = r3.a(r15, r8)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.alp r14 = new com.google.android.gms.internal.ads.alp     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            android.content.Context r4 = r15.b     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.ads.internal.zzbc r5 = r15.d     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.az r6 = r15.f     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.ack r7 = r15.e     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.gb r3 = r15.h     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzaef r3 = r3.a     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.zzang r10 = r3.zzacr     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r3 = r14
            r9 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r13.zzb(r14)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            goto L_0x01be
        L_0x01bd:
            r13 = r1
        L_0x01be:
            boolean r3 = r13 instanceof com.google.android.gms.internal.ads.zzos     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            if (r3 == 0) goto L_0x01d1
            r3 = r13
            com.google.android.gms.internal.ads.zzos r3 = (com.google.android.gms.internal.ads.zzos) r3     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.am r4 = new com.google.android.gms.internal.ads.am     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r4.<init>(r15, r3)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.az r3 = r15.f     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            java.lang.String r5 = "/nativeAdCustomClick"
            r3.a(r5, r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
        L_0x01d1:
            com.google.android.gms.internal.ads.ga r2 = r15.a(r13, r2)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.ads.internal.zzbc r3 = r15.d     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            com.google.android.gms.internal.ads.nn r4 = b(r12)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            r3.zzg(r4)     // Catch:{ InterruptedException | CancellationException | ExecutionException | JSONException -> 0x01e7, TimeoutException -> 0x01e3, Exception -> 0x01df }
            return r2
        L_0x01df:
            r2 = move-exception
            java.lang.String r3 = "Error occured while doing native ads initialization."
            goto L_0x01ea
        L_0x01e3:
            r2 = move-exception
            java.lang.String r3 = "Timeout when loading native ad."
            goto L_0x01ea
        L_0x01e7:
            r2 = move-exception
            java.lang.String r3 = "Malformed native JSON response."
        L_0x01ea:
            com.google.android.gms.internal.ads.gv.c(r3, r2)
            boolean r2 = r15.j
            if (r2 != 0) goto L_0x01f4
            r15.a(r0)
        L_0x01f4:
            com.google.android.gms.internal.ads.ga r0 = r15.a(r1, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.ai.call():com.google.android.gms.internal.ads.ga");
    }

    private final ga a(aln aln, boolean z) {
        int i2;
        synchronized (this.g) {
            try {
                int i3 = this.k;
                if (aln == null && this.k == -2) {
                    i3 = 0;
                }
            } finally {
                while (true) {
                    i2 = th;
                }
            }
        }
        aln aln2 = i2 != -2 ? null : aln;
        zzjj zzjj = this.h.a.zzccv;
        List<String> list = this.h.b.zzbsn;
        List<String> list2 = this.h.b.zzbso;
        List<String> list3 = this.l;
        int i4 = this.h.b.orientation;
        long j2 = this.h.b.zzbsu;
        String str = this.h.a.zzccy;
        zzjn zzjn = this.h.d;
        long j3 = this.h.b.zzcep;
        List<String> list4 = list;
        long j4 = this.h.f;
        long j5 = j3;
        long j6 = this.h.g;
        String str2 = this.h.b.zzcev;
        JSONObject jSONObject = this.m;
        boolean z2 = this.h.b.zzcfh;
        zzael zzael = this.h.b.zzcfi;
        List<String> list5 = this.h.b.zzbsr;
        long j7 = j4;
        String str3 = this.n;
        ahh ahh = this.h.i;
        List<String> list6 = list5;
        boolean z3 = this.h.b.zzzl;
        boolean z4 = this.h.j;
        long j8 = j7;
        String str4 = str3;
        ahh ahh2 = ahh;
        List<String> list7 = list4;
        zzjn zzjn2 = zzjn;
        String str5 = str2;
        boolean z5 = z;
        ga gaVar = new ga(zzjj, null, list7, i2, list2, list3, i4, j2, str, false, null, null, null, null, null, 0, zzjn2, j5, j8, j6, str5, jSONObject, aln2, null, null, null, z2, zzael, null, list6, str4, ahh2, z3, z4, z5, this.h.b.zzbsp, this.h.b.zzzm, this.o);
        return gaVar;
    }

    private final kt<zzon> a(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString("url") : jSONObject.optString("url");
        double optDouble = jSONObject.optDouble(ResponseConstants.SCALE, 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        if (TextUtils.isEmpty(string)) {
            a(0, z);
            return ki.a(null);
        } else if (z2) {
            return ki.a(new zzon(null, Uri.parse(string), optDouble));
        } else {
            in inVar = this.c;
            ao aoVar = new ao(this, z, optDouble, optBoolean, string);
            return inVar.a(string, (it<T>) aoVar);
        }
    }

    static nn a(kt<nn> ktVar) {
        try {
            return (nn) ktVar.get((long) ((Integer) ajh.f().a(akl.cc)).intValue(), TimeUnit.SECONDS);
        } catch (InterruptedException e2) {
            gv.c("InterruptedException occurred while waiting for video to load", e2);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e3) {
            gv.c("Exception occurred while waiting for video to load", e3);
            return null;
        }
    }

    private final void a(int i2) {
        synchronized (this.g) {
            this.j = true;
            this.k = i2;
        }
    }

    /* access modifiers changed from: private */
    public final void a(zzqs zzqs, String str) {
        try {
            zzrc zzr = this.d.zzr(zzqs.getCustomTemplateId());
            if (zzr != null) {
                zzr.zzb(zzqs, str);
            }
        } catch (RemoteException e2) {
            StringBuilder sb = new StringBuilder(40 + String.valueOf(str).length());
            sb.append("Failed to call onCustomClick for asset ");
            sb.append(str);
            sb.append(".");
            gv.c(sb.toString(), e2);
        }
    }

    private static nn b(kt<nn> ktVar) {
        try {
            return (nn) ktVar.get((long) ((Integer) ajh.f().a(akl.cd)).intValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            ka.c("", e2);
            Thread.currentThread().interrupt();
            return null;
        } catch (CancellationException | ExecutionException | TimeoutException e3) {
            ka.c("", e3);
            return null;
        }
    }

    private static Integer b(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static <V> List<V> b(List<kt<V>> list) throws ExecutionException, InterruptedException {
        ArrayList arrayList = new ArrayList();
        for (kt ktVar : list) {
            Object obj = ktVar.get();
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final boolean b() {
        boolean z;
        synchronized (this.g) {
            z = this.j;
        }
        return z;
    }

    public final kt<zzoj> a(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return ki.a(null);
        }
        String optString = optJSONObject.optString("text");
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer b2 = b(optJSONObject, ResponseConstants.TEXT_COLOR);
        Integer b3 = b(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        int i2 = (this.h.a.zzadj == null || this.h.a.zzadj.versionCode < 2) ? 1 : this.h.a.zzadj.zzbjq;
        boolean optBoolean = optJSONObject.optBoolean("allow_pub_rendering");
        List<kt> arrayList = new ArrayList<>();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = a(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(a(optJSONObject, ResponseConstants.IMAGE, false, false));
        }
        le leVar = new le();
        int size = arrayList.size();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (kt a2 : arrayList) {
            List list = arrayList;
            a2.a(new ap(atomicInteger, size, leVar, arrayList), hb.a);
            arrayList = list;
        }
        le leVar2 = leVar;
        an anVar = new an(this, optString, b3, b2, optInt, optInt3, optInt2, i2, optBoolean);
        return ki.a((kt<A>) leVar2, (ke<A, B>) anVar, (Executor) hb.a);
    }

    public final kt<nn> a(JSONObject jSONObject, String str) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return ki.a(null);
        }
        if (TextUtils.isEmpty(optJSONObject.optString("vast_xml"))) {
            gv.e("Required field 'vast_xml' is missing");
            return ki.a(null);
        }
        ar arVar = new ar(this.b, this.e, this.h, this.i, this.d);
        le leVar = new le();
        kz.a.execute(new as(arVar, optJSONObject, leVar));
        return leVar;
    }

    public final kt<zzon> a(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return a(jSONObject2, z, z2);
    }

    public final List<kt<zzon>> a(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        ArrayList arrayList = new ArrayList();
        if (optJSONArray == null || optJSONArray.length() == 0) {
            a(0, false);
            return arrayList;
        }
        int length = z3 ? optJSONArray.length() : 1;
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(a(jSONObject2, false, z2));
        }
        return arrayList;
    }

    public final Future<zzon> a(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return a(jSONObject2, optBoolean, z);
    }

    public final void a(int i2, boolean z) {
        if (z) {
            a(i2);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(le leVar, String str) {
        try {
            ao.f();
            nn a2 = nt.a(this.b, ot.a(), "native-omid", false, false, this.e, this.h.a.zzacr, this.i, null, this.d.zzbi(), this.h.i);
            a2.zzuf().zza((op) new ak(leVar, a2));
            a2.loadData(str, "text/html", "UTF-8");
        } catch (Exception e2) {
            leVar.b(null);
            ka.c("", e2);
        }
    }
}
