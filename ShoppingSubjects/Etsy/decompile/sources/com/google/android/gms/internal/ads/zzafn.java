package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.etsy.android.lib.convos.Draft;
import com.etsy.android.lib.models.apiv3.editable.EditableInventoryValue;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.ao;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class zzafn extends zzaeo {
    private static final Object sLock = new Object();
    private static zzafn zzchh;
    private final Context mContext;
    private final de zzchi;
    private final ScheduledExecutorService zzchj = Executors.newSingleThreadScheduledExecutor();

    private zzafn(Context context, de deVar) {
        this.mContext = context;
        this.zzchi = deVar;
    }

    private static zzaej zza(Context context, de deVar, zzaef zzaef, ScheduledExecutorService scheduledExecutorService) {
        char c;
        Context context2 = context;
        de deVar2 = deVar;
        zzaef zzaef2 = zzaef;
        ScheduledExecutorService scheduledExecutorService2 = scheduledExecutorService;
        gv.b("Starting ad request from service using: google.afma.request.getAdDictionary");
        aky aky = new aky(((Boolean) ajh.f().a(akl.N)).booleanValue(), "load_ad", zzaef2.zzacv.zzarb);
        if (zzaef2.versionCode > 10 && zzaef2.zzcdl != -1) {
            aky.a(aky.a(zzaef2.zzcdl), "cts");
        }
        akw a = aky.a();
        kt a2 = ki.a(deVar2.i.a(context2), ((Long) ajh.f().a(akl.cA)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        kt a3 = ki.a(deVar2.h.a(context2), ((Long) ajh.f().a(akl.bv)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        kt a4 = deVar2.c.a(zzaef2.zzccw.packageName);
        kt b = deVar2.c.b(zzaef2.zzccw.packageName);
        kt a5 = deVar2.j.a(zzaef2.zzccx, zzaef2.zzccw);
        Future a6 = ao.p().a(context2);
        kt a7 = ki.a(null);
        Bundle bundle = zzaef2.zzccv.extras;
        boolean z = (bundle == null || bundle.getString("_ad") == null) ? false : true;
        if (zzaef2.zzcdr && !z) {
            a7 = deVar2.f.a(zzaef2.applicationInfo);
        }
        aky aky2 = aky;
        kt a8 = ki.a(a7, ((Long) ajh.f().a(akl.cr)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        Future a9 = ki.a(null);
        if (((Boolean) ajh.f().a(akl.aJ)).booleanValue()) {
            a9 = ki.a(deVar2.j.a(context2), ((Long) ajh.f().a(akl.aK)).longValue(), TimeUnit.MILLISECONDS, scheduledExecutorService2);
        }
        Bundle bundle2 = (zzaef2.versionCode < 4 || zzaef2.zzcdc == null) ? null : zzaef2.zzcdc;
        ((Boolean) ajh.f().a(akl.ad)).booleanValue();
        ao.e();
        if (hd.a(context2, "android.permission.ACCESS_NETWORK_STATE") && ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo() == null) {
            gv.b("Device is offline.");
        }
        String uuid = zzaef2.versionCode >= 7 ? zzaef2.zzcdi : UUID.randomUUID().toString();
        new dk(context2, uuid, zzaef2.applicationInfo.packageName);
        if (zzaef2.zzccv.extras != null) {
            String string = zzaef2.zzccv.extras.getString("_ad");
            if (string != null) {
                return dj.a(context2, zzaef2, string);
            }
        }
        List<String> a10 = deVar2.d.a(zzaef2.zzcdj);
        akw akw = a;
        String str = uuid;
        Bundle bundle3 = (Bundle) ki.a((Future<T>) a2, null, ((Long) ajh.f().a(akl.cA)).longValue(), TimeUnit.MILLISECONDS);
        ec ecVar = (ec) ki.a((Future<T>) a3, null);
        Location location = (Location) ki.a((Future<T>) a8, null);
        Info info = (Info) ki.a(a9, null);
        String str2 = (String) ki.a((Future<T>) a5, null);
        String str3 = (String) ki.a((Future<T>) a4, null);
        String str4 = (String) ki.a((Future<T>) b, null);
        ds dsVar = (ds) ki.a(a6, null);
        if (dsVar == null) {
            gv.e("Error fetching device info. This is not recoverable.");
            return new zzaej(0);
        }
        dd ddVar = new dd();
        ddVar.j = zzaef2;
        ddVar.k = dsVar;
        ddVar.e = ecVar;
        ddVar.d = location;
        ddVar.b = bundle3;
        ddVar.h = str2;
        ddVar.i = info;
        if (a10 == null) {
            ddVar.c.clear();
        }
        ddVar.c = a10;
        ddVar.a = bundle2;
        ddVar.f = str3;
        ddVar.g = str4;
        Context context3 = context;
        ddVar.l = deVar2.b.a(context3);
        ddVar.m = deVar2.k;
        JSONObject a11 = dj.a(context3, ddVar);
        if (a11 == null) {
            return new zzaej(0);
        }
        if (zzaef2.versionCode < 7) {
            try {
                a11.put("request_id", str);
            } catch (JSONException unused) {
            }
        }
        aky aky3 = aky2;
        akw akw2 = akw;
        aky3.a(akw2, "arc");
        aky3.a();
        ScheduledExecutorService scheduledExecutorService3 = scheduledExecutorService;
        kt a12 = ki.a(ki.a(deVar2.l.a().b(a11), df.a, (Executor) scheduledExecutorService3), 10, TimeUnit.SECONDS, scheduledExecutorService3);
        kt a13 = deVar2.e.a();
        if (a13 != null) {
            kg.a(a13, "AdRequestServiceImpl.loadAd.flags");
        }
        dq dqVar = (dq) ki.a((Future<T>) a12, null);
        if (dqVar == null) {
            return new zzaej(0);
        }
        if (dqVar.a() != -2) {
            return new zzaej(dqVar.a());
        }
        aky3.d();
        zzaej a14 = !TextUtils.isEmpty(dqVar.i()) ? dj.a(context3, zzaef2, dqVar.i()) : null;
        if (a14 == null && !TextUtils.isEmpty(dqVar.e())) {
            a14 = zza(zzaef2, context3, zzaef2.zzacr.zzcw, dqVar.e(), str3, str4, dqVar, aky3, deVar2);
        }
        if (a14 == null) {
            c = 0;
            a14 = new zzaej(0);
        } else {
            c = 0;
        }
        String[] strArr = new String[1];
        strArr[c] = "tts";
        aky3.a(akw2, strArr);
        a14.zzcfd = aky3.b();
        return a14;
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.ads.do.a(long, boolean):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00e8, code lost:
        r1 = r7.toString();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r7 = new java.io.InputStreamReader(r12.getInputStream());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        com.google.android.gms.ads.internal.ao.e();
        r11 = com.google.android.gms.internal.ads.hd.a(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r7);
        r10.a(r11);
        zza(r1, r14, r11, r4);
        r6.a(r1, r14, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0108, code lost:
        if (r2 == null) goto L_0x0115;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x010a, code lost:
        r2.a(r5, "ufe");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0115, code lost:
        r1 = r6.a(r8, r24.j());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r12.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0120, code lost:
        if (r3 == null) goto L_0x0127;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0122, code lost:
        r3.g.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0127, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0128, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0129, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x012b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x012c, code lost:
        r1 = r0;
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0131, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x014c, code lost:
        com.google.android.gms.internal.ads.gv.e("No location header to follow redirect.");
        r1 = new com.google.android.gms.internal.ads.zzaej(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r12.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x015a, code lost:
        if (r3 == null) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x015c, code lost:
        r3.g.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0161, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x017b, code lost:
        com.google.android.gms.internal.ads.gv.e("Too many redirects.");
        r1 = new com.google.android.gms.internal.ads.zzaej(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
        r12.disconnect();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0189, code lost:
        if (r3 == null) goto L_0x0190;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x018b, code lost:
        r3.g.b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0190, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008a A[Catch:{ all -> 0x00c0, all -> 0x01cb }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzaej zza(com.google.android.gms.internal.ads.zzaef r18, android.content.Context r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, com.google.android.gms.internal.ads.dq r24, com.google.android.gms.internal.ads.aky r25, com.google.android.gms.internal.ads.de r26) {
        /*
            r1 = r18
            r2 = r25
            r3 = r26
            if (r2 == 0) goto L_0x000d
            com.google.android.gms.internal.ads.akw r5 = r25.a()
            goto L_0x000e
        L_0x000d:
            r5 = 0
        L_0x000e:
            com.google.android.gms.internal.ads.do r6 = new com.google.android.gms.internal.ads.do     // Catch:{ IOException -> 0x01d8 }
            java.lang.String r7 = r24.c()     // Catch:{ IOException -> 0x01d8 }
            r6.<init>(r1, r7)     // Catch:{ IOException -> 0x01d8 }
            java.lang.String r7 = "AdRequestServiceImpl: Sending request: "
            java.lang.String r8 = java.lang.String.valueOf(r21)     // Catch:{ IOException -> 0x01d8 }
            int r9 = r8.length()     // Catch:{ IOException -> 0x01d8 }
            if (r9 == 0) goto L_0x0028
            java.lang.String r7 = r7.concat(r8)     // Catch:{ IOException -> 0x01d8 }
            goto L_0x002e
        L_0x0028:
            java.lang.String r8 = new java.lang.String     // Catch:{ IOException -> 0x01d8 }
            r8.<init>(r7)     // Catch:{ IOException -> 0x01d8 }
            r7 = r8
        L_0x002e:
            com.google.android.gms.internal.ads.gv.b(r7)     // Catch:{ IOException -> 0x01d8 }
            java.net.URL r7 = new java.net.URL     // Catch:{ IOException -> 0x01d8 }
            r8 = r21
            r7.<init>(r8)     // Catch:{ IOException -> 0x01d8 }
            com.google.android.gms.common.util.Clock r8 = com.google.android.gms.ads.internal.ao.l()     // Catch:{ IOException -> 0x01d8 }
            long r8 = r8.elapsedRealtime()     // Catch:{ IOException -> 0x01d8 }
            r10 = 0
            r11 = r10
        L_0x0042:
            if (r3 == 0) goto L_0x0049
            com.google.android.gms.internal.ads.ea r12 = r3.g     // Catch:{ IOException -> 0x01d8 }
            r12.a()     // Catch:{ IOException -> 0x01d8 }
        L_0x0049:
            java.net.URLConnection r12 = r7.openConnection()     // Catch:{ IOException -> 0x01d8 }
            java.net.HttpURLConnection r12 = (java.net.HttpURLConnection) r12     // Catch:{ IOException -> 0x01d8 }
            com.google.android.gms.internal.ads.hd r13 = com.google.android.gms.ads.internal.ao.e()     // Catch:{ all -> 0x01cb }
            r14 = r19
            r15 = r20
            r13.a(r14, r15, r10, r12)     // Catch:{ all -> 0x01cb }
            boolean r13 = r24.g()     // Catch:{ all -> 0x01cb }
            if (r13 == 0) goto L_0x007e
            boolean r13 = android.text.TextUtils.isEmpty(r22)     // Catch:{ all -> 0x01cb }
            if (r13 != 0) goto L_0x006e
            java.lang.String r13 = "x-afma-drt-cookie"
            r4 = r22
            r12.addRequestProperty(r13, r4)     // Catch:{ all -> 0x01cb }
            goto L_0x0070
        L_0x006e:
            r4 = r22
        L_0x0070:
            boolean r13 = android.text.TextUtils.isEmpty(r23)     // Catch:{ all -> 0x01cb }
            if (r13 != 0) goto L_0x0080
            java.lang.String r13 = "x-afma-drt-v2-cookie"
            r10 = r23
            r12.addRequestProperty(r13, r10)     // Catch:{ all -> 0x01cb }
            goto L_0x0082
        L_0x007e:
            r4 = r22
        L_0x0080:
            r10 = r23
        L_0x0082:
            java.lang.String r13 = r1.zzcds     // Catch:{ all -> 0x01cb }
            boolean r16 = android.text.TextUtils.isEmpty(r13)     // Catch:{ all -> 0x01cb }
            if (r16 != 0) goto L_0x0094
            java.lang.String r4 = "Sending webview cookie in ad request header."
            com.google.android.gms.internal.ads.gv.b(r4)     // Catch:{ all -> 0x01cb }
            java.lang.String r4 = "Cookie"
            r12.addRequestProperty(r4, r13)     // Catch:{ all -> 0x01cb }
        L_0x0094:
            if (r24 == 0) goto L_0x00ca
            java.lang.String r4 = r24.d()     // Catch:{ all -> 0x01cb }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x01cb }
            if (r4 != 0) goto L_0x00ca
            r4 = 1
            r12.setDoOutput(r4)     // Catch:{ all -> 0x01cb }
            java.lang.String r4 = r24.d()     // Catch:{ all -> 0x01cb }
            byte[] r4 = r4.getBytes()     // Catch:{ all -> 0x01cb }
            int r10 = r4.length     // Catch:{ all -> 0x01cb }
            r12.setFixedLengthStreamingMode(r10)     // Catch:{ all -> 0x01cb }
            java.io.BufferedOutputStream r10 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x00c3 }
            java.io.OutputStream r14 = r12.getOutputStream()     // Catch:{ all -> 0x00c3 }
            r10.<init>(r14)     // Catch:{ all -> 0x00c3 }
            r10.write(r4)     // Catch:{ all -> 0x00c0 }
            com.google.android.gms.common.util.IOUtils.closeQuietly(r10)     // Catch:{ all -> 0x01cb }
            goto L_0x00cb
        L_0x00c0:
            r0 = move-exception
            r1 = r0
            goto L_0x00c6
        L_0x00c3:
            r0 = move-exception
            r1 = r0
            r10 = 0
        L_0x00c6:
            com.google.android.gms.common.util.IOUtils.closeQuietly(r10)     // Catch:{ all -> 0x01cb }
            throw r1     // Catch:{ all -> 0x01cb }
        L_0x00ca:
            r4 = 0
        L_0x00cb:
            com.google.android.gms.internal.ads.jt r10 = new com.google.android.gms.internal.ads.jt     // Catch:{ all -> 0x01cb }
            java.lang.String r14 = r1.zzcdi     // Catch:{ all -> 0x01cb }
            r10.<init>(r14)     // Catch:{ all -> 0x01cb }
            r10.a(r12, r4)     // Catch:{ all -> 0x01cb }
            int r4 = r12.getResponseCode()     // Catch:{ all -> 0x01cb }
            java.util.Map r14 = r12.getHeaderFields()     // Catch:{ all -> 0x01cb }
            r10.a(r12, r4)     // Catch:{ all -> 0x01cb }
            r1 = 200(0xc8, float:2.8E-43)
            r15 = 300(0x12c, float:4.2E-43)
            if (r4 < r1) goto L_0x0132
            if (r4 >= r15) goto L_0x0132
            java.lang.String r1 = r7.toString()     // Catch:{ all -> 0x01cb }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ all -> 0x012b }
            java.io.InputStream r11 = r12.getInputStream()     // Catch:{ all -> 0x012b }
            r7.<init>(r11)     // Catch:{ all -> 0x012b }
            com.google.android.gms.ads.internal.ao.e()     // Catch:{ all -> 0x0128 }
            java.lang.String r11 = com.google.android.gms.internal.ads.hd.a(r7)     // Catch:{ all -> 0x0128 }
            com.google.android.gms.common.util.IOUtils.closeQuietly(r7)     // Catch:{ all -> 0x01cb }
            r10.a(r11)     // Catch:{ all -> 0x01cb }
            zza(r1, r14, r11, r4)     // Catch:{ all -> 0x01cb }
            r6.a(r1, r14, r11)     // Catch:{ all -> 0x01cb }
            if (r2 == 0) goto L_0x0115
            r1 = 1
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ all -> 0x01cb }
            java.lang.String r4 = "ufe"
            r7 = 0
            r1[r7] = r4     // Catch:{ all -> 0x01cb }
            r2.a(r5, r1)     // Catch:{ all -> 0x01cb }
        L_0x0115:
            boolean r1 = r24.j()     // Catch:{ all -> 0x01cb }
            com.google.android.gms.internal.ads.zzaej r1 = r6.a(r8, r1)     // Catch:{ all -> 0x01cb }
            r12.disconnect()     // Catch:{ IOException -> 0x01d8 }
            if (r3 == 0) goto L_0x0127
            com.google.android.gms.internal.ads.ea r2 = r3.g     // Catch:{ IOException -> 0x01d8 }
            r2.b()     // Catch:{ IOException -> 0x01d8 }
        L_0x0127:
            return r1
        L_0x0128:
            r0 = move-exception
            r1 = r0
            goto L_0x012e
        L_0x012b:
            r0 = move-exception
            r1 = r0
            r7 = 0
        L_0x012e:
            com.google.android.gms.common.util.IOUtils.closeQuietly(r7)     // Catch:{ all -> 0x01cb }
            throw r1     // Catch:{ all -> 0x01cb }
        L_0x0132:
            java.lang.String r1 = r7.toString()     // Catch:{ all -> 0x01cb }
            r7 = 0
            zza(r1, r14, r7, r4)     // Catch:{ all -> 0x01cb }
            if (r4 < r15) goto L_0x01a4
            r1 = 400(0x190, float:5.6E-43)
            if (r4 >= r1) goto L_0x01a4
            java.lang.String r1 = "Location"
            java.lang.String r1 = r12.getHeaderField(r1)     // Catch:{ all -> 0x01cb }
            boolean r4 = android.text.TextUtils.isEmpty(r1)     // Catch:{ all -> 0x01cb }
            if (r4 == 0) goto L_0x0162
            java.lang.String r1 = "No location header to follow redirect."
            com.google.android.gms.internal.ads.gv.e(r1)     // Catch:{ all -> 0x01cb }
            com.google.android.gms.internal.ads.zzaej r1 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ all -> 0x01cb }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x01cb }
            r12.disconnect()     // Catch:{ IOException -> 0x01d8 }
            if (r3 == 0) goto L_0x0161
            com.google.android.gms.internal.ads.ea r2 = r3.g     // Catch:{ IOException -> 0x01d8 }
            r2.b()     // Catch:{ IOException -> 0x01d8 }
        L_0x0161:
            return r1
        L_0x0162:
            java.net.URL r4 = new java.net.URL     // Catch:{ all -> 0x01cb }
            r4.<init>(r1)     // Catch:{ all -> 0x01cb }
            r1 = 1
            int r11 = r11 + r1
            com.google.android.gms.internal.ads.akb<java.lang.Integer> r1 = com.google.android.gms.internal.ads.akl.df     // Catch:{ all -> 0x01cb }
            com.google.android.gms.internal.ads.akj r10 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x01cb }
            java.lang.Object r1 = r10.a(r1)     // Catch:{ all -> 0x01cb }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ all -> 0x01cb }
            int r1 = r1.intValue()     // Catch:{ all -> 0x01cb }
            if (r11 <= r1) goto L_0x0191
            java.lang.String r1 = "Too many redirects."
            com.google.android.gms.internal.ads.gv.e(r1)     // Catch:{ all -> 0x01cb }
            com.google.android.gms.internal.ads.zzaej r1 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ all -> 0x01cb }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x01cb }
            r12.disconnect()     // Catch:{ IOException -> 0x01d8 }
            if (r3 == 0) goto L_0x0190
            com.google.android.gms.internal.ads.ea r2 = r3.g     // Catch:{ IOException -> 0x01d8 }
            r2.b()     // Catch:{ IOException -> 0x01d8 }
        L_0x0190:
            return r1
        L_0x0191:
            r6.a(r14)     // Catch:{ all -> 0x01cb }
            r12.disconnect()     // Catch:{ IOException -> 0x01d8 }
            if (r3 == 0) goto L_0x019e
            com.google.android.gms.internal.ads.ea r1 = r3.g     // Catch:{ IOException -> 0x01d8 }
            r1.b()     // Catch:{ IOException -> 0x01d8 }
        L_0x019e:
            r7 = r4
            r1 = r18
            r10 = 0
            goto L_0x0042
        L_0x01a4:
            r1 = 46
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01cb }
            r2.<init>(r1)     // Catch:{ all -> 0x01cb }
            java.lang.String r1 = "Received error HTTP response code: "
            r2.append(r1)     // Catch:{ all -> 0x01cb }
            r2.append(r4)     // Catch:{ all -> 0x01cb }
            java.lang.String r1 = r2.toString()     // Catch:{ all -> 0x01cb }
            com.google.android.gms.internal.ads.gv.e(r1)     // Catch:{ all -> 0x01cb }
            com.google.android.gms.internal.ads.zzaej r1 = new com.google.android.gms.internal.ads.zzaej     // Catch:{ all -> 0x01cb }
            r2 = 0
            r1.<init>(r2)     // Catch:{ all -> 0x01cb }
            r12.disconnect()     // Catch:{ IOException -> 0x01d8 }
            if (r3 == 0) goto L_0x01ca
            com.google.android.gms.internal.ads.ea r2 = r3.g     // Catch:{ IOException -> 0x01d8 }
            r2.b()     // Catch:{ IOException -> 0x01d8 }
        L_0x01ca:
            return r1
        L_0x01cb:
            r0 = move-exception
            r1 = r0
            r12.disconnect()     // Catch:{ IOException -> 0x01d8 }
            if (r3 == 0) goto L_0x01d7
            com.google.android.gms.internal.ads.ea r2 = r3.g     // Catch:{ IOException -> 0x01d8 }
            r2.b()     // Catch:{ IOException -> 0x01d8 }
        L_0x01d7:
            throw r1     // Catch:{ IOException -> 0x01d8 }
        L_0x01d8:
            r0 = move-exception
            r1 = r0
            java.lang.String r2 = "Error while connecting to ad server: "
            java.lang.String r1 = r1.getMessage()
            java.lang.String r1 = java.lang.String.valueOf(r1)
            int r3 = r1.length()
            if (r3 == 0) goto L_0x01ef
            java.lang.String r1 = r2.concat(r1)
            goto L_0x01f4
        L_0x01ef:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
        L_0x01f4:
            com.google.android.gms.internal.ads.gv.e(r1)
            com.google.android.gms.internal.ads.zzaej r1 = new com.google.android.gms.internal.ads.zzaej
            r2 = 2
            r1.<init>(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafn.zza(com.google.android.gms.internal.ads.zzaef, android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.google.android.gms.internal.ads.dq, com.google.android.gms.internal.ads.aky, com.google.android.gms.internal.ads.de):com.google.android.gms.internal.ads.zzaej");
    }

    public static zzafn zza(Context context, de deVar) {
        zzafn zzafn;
        synchronized (sLock) {
            if (zzchh == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                akl.a(context);
                zzchh = new zzafn(context, deVar);
                if (context.getApplicationContext() != null) {
                    ao.e().c(context);
                }
                gs.a(context);
            }
            zzafn = zzchh;
        }
        return zzafn;
    }

    private static void zza(String str, Map<String, List<String>> map, String str2, int i) {
        if (gv.a(2)) {
            StringBuilder sb = new StringBuilder(39 + String.valueOf(str).length());
            sb.append("Http Response: {\n  URL:\n    ");
            sb.append(str);
            sb.append("\n  Headers:");
            gv.a(sb.toString());
            if (map != null) {
                for (String str3 : map.keySet()) {
                    StringBuilder sb2 = new StringBuilder(5 + String.valueOf(str3).length());
                    sb2.append("    ");
                    sb2.append(str3);
                    sb2.append(Draft.IMAGE_DELIMITER);
                    gv.a(sb2.toString());
                    for (String valueOf : (List) map.get(str3)) {
                        String str4 = "      ";
                        String valueOf2 = String.valueOf(valueOf);
                        gv.a(valueOf2.length() != 0 ? str4.concat(valueOf2) : new String(str4));
                    }
                }
            }
            gv.a("  Body:");
            if (str2 != null) {
                int i2 = 0;
                while (i2 < Math.min(str2.length(), EditableInventoryValue.ROOT_ID)) {
                    int i3 = i2 + 1000;
                    gv.a(str2.substring(i2, Math.min(str2.length(), i3)));
                    i2 = i3;
                }
            } else {
                gv.a("    null");
            }
            StringBuilder sb3 = new StringBuilder(34);
            sb3.append("  Response Code:\n    ");
            sb3.append(i);
            sb3.append("\n}");
            gv.a(sb3.toString());
        }
    }

    public final void zza(zzaef zzaef, zzaeq zzaeq) {
        ao.i().a(this.mContext, zzaef.zzacr);
        kt a = hb.a((Runnable) new dg(this, zzaef, zzaeq));
        ao.t().a();
        ao.t().b().postDelayed(new dh(this, a), DateUtils.MILLIS_PER_MINUTE);
    }

    public final void zza(zzaey zzaey, zzaet zzaet) {
        gv.a("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }

    public final zzaej zzb(zzaef zzaef) {
        return zza(this.mContext, this.zzchi, zzaef, this.zzchj);
    }

    public final void zzb(zzaey zzaey, zzaet zzaet) {
        gv.a("Nonagon code path entered in octagon");
        throw new IllegalArgumentException();
    }
}
