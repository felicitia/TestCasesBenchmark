package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.overlay.i;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.age;
import com.google.android.gms.internal.ads.ahl;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.aky;
import com.google.android.gms.internal.ads.ara;
import com.google.android.gms.internal.ads.arh;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.bx;
import com.google.android.gms.internal.ads.cr;
import com.google.android.gms.internal.ads.cu;
import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.ge;
import com.google.android.gms.internal.ads.gq;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.hj;
import com.google.android.gms.internal.ads.kz;
import com.google.android.gms.internal.ads.nn;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzhu.zza.zzb;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzxn;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public abstract class zzd extends zza implements ai, i, ara {
    protected final zzxn zzwh;
    private transient boolean zzwi;

    public zzd(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, bg bgVar) {
        this(new zzbw(context, zzjn, str, zzang), zzxn, null, bgVar);
    }

    @VisibleForTesting
    private zzd(zzbw zzbw, zzxn zzxn, @Nullable af afVar, bg bgVar) {
        super(zzbw, null, bgVar);
        this.zzwh = zzxn;
        this.zzwi = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0189  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.ads.cr zza(com.google.android.gms.internal.ads.zzjj r65, android.os.Bundle r66, com.google.android.gms.internal.ads.ge r67, int r68) {
        /*
            r64 = this;
            r0 = r64
            com.google.android.gms.ads.internal.zzbw r1 = r0.zzvw
            android.content.Context r1 = r1.zzrt
            android.content.pm.ApplicationInfo r7 = r1.getApplicationInfo()
            r2 = 0
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzvw     // Catch:{ NameNotFoundException -> 0x001b }
            android.content.Context r3 = r3.zzrt     // Catch:{ NameNotFoundException -> 0x001b }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r3 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r3)     // Catch:{ NameNotFoundException -> 0x001b }
            java.lang.String r4 = r7.packageName     // Catch:{ NameNotFoundException -> 0x001b }
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r4, r2)     // Catch:{ NameNotFoundException -> 0x001b }
            r8 = r3
            goto L_0x001c
        L_0x001b:
            r8 = 0
        L_0x001c:
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzvw
            android.content.Context r3 = r3.zzrt
            android.content.res.Resources r3 = r3.getResources()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzvw
            com.google.android.gms.ads.internal.zzbx r4 = r4.zzacs
            if (r4 == 0) goto L_0x0093
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzvw
            com.google.android.gms.ads.internal.zzbx r4 = r4.zzacs
            android.view.ViewParent r4 = r4.getParent()
            if (r4 == 0) goto L_0x0093
            r4 = 2
            int[] r4 = new int[r4]
            com.google.android.gms.ads.internal.zzbw r5 = r0.zzvw
            com.google.android.gms.ads.internal.zzbx r5 = r5.zzacs
            r5.getLocationOnScreen(r4)
            r5 = r4[r2]
            r6 = 1
            r4 = r4[r6]
            com.google.android.gms.ads.internal.zzbw r9 = r0.zzvw
            com.google.android.gms.ads.internal.zzbx r9 = r9.zzacs
            int r9 = r9.getWidth()
            com.google.android.gms.ads.internal.zzbw r10 = r0.zzvw
            com.google.android.gms.ads.internal.zzbx r10 = r10.zzacs
            int r10 = r10.getHeight()
            com.google.android.gms.ads.internal.zzbw r11 = r0.zzvw
            com.google.android.gms.ads.internal.zzbx r11 = r11.zzacs
            boolean r11 = r11.isShown()
            if (r11 == 0) goto L_0x0072
            int r11 = r5 + r9
            if (r11 <= 0) goto L_0x0072
            int r11 = r4 + r10
            if (r11 <= 0) goto L_0x0072
            int r11 = r3.widthPixels
            if (r5 > r11) goto L_0x0072
            int r11 = r3.heightPixels
            if (r4 > r11) goto L_0x0072
            goto L_0x0073
        L_0x0072:
            r6 = r2
        L_0x0073:
            android.os.Bundle r11 = new android.os.Bundle
            r12 = 5
            r11.<init>(r12)
            java.lang.String r12 = "x"
            r11.putInt(r12, r5)
            java.lang.String r5 = "y"
            r11.putInt(r5, r4)
            java.lang.String r4 = "width"
            r11.putInt(r4, r9)
            java.lang.String r4 = "height"
            r11.putInt(r4, r10)
            java.lang.String r4 = "visible"
            r11.putInt(r4, r6)
            goto L_0x0094
        L_0x0093:
            r11 = 0
        L_0x0094:
            com.google.android.gms.internal.ads.gf r4 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gm r4 = r4.a()
            java.lang.String r9 = r4.a()
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzvw
            com.google.android.gms.internal.ads.gc r5 = new com.google.android.gms.internal.ads.gc
            com.google.android.gms.ads.internal.zzbw r6 = r0.zzvw
            java.lang.String r6 = r6.zzacp
            r5.<init>(r9, r6)
            r4.zzacy = r5
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzvw
            com.google.android.gms.internal.ads.gc r4 = r4.zzacy
            r5 = r65
            r4.a(r5)
            com.google.android.gms.ads.internal.ao.e()
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzvw
            android.content.Context r4 = r4.zzrt
            com.google.android.gms.ads.internal.zzbw r6 = r0.zzvw
            com.google.android.gms.ads.internal.zzbx r6 = r6.zzacs
            com.google.android.gms.ads.internal.zzbw r10 = r0.zzvw
            com.google.android.gms.internal.ads.zzjn r10 = r10.zzacv
            java.lang.String r20 = com.google.android.gms.internal.ads.hd.a(r4, r6, r10)
            r12 = 0
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzvw
            com.google.android.gms.internal.ads.zzlg r4 = r4.zzadd
            if (r4 == 0) goto L_0x00e1
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzvw     // Catch:{ RemoteException -> 0x00dc }
            com.google.android.gms.internal.ads.zzlg r4 = r4.zzadd     // Catch:{ RemoteException -> 0x00dc }
            long r14 = r4.getValue()     // Catch:{ RemoteException -> 0x00dc }
            r21 = r14
            goto L_0x00e3
        L_0x00dc:
            java.lang.String r4 = "Cannot get correlation id, default to 0."
            com.google.android.gms.internal.ads.gv.e(r4)
        L_0x00e1:
            r21 = r12
        L_0x00e3:
            java.util.UUID r4 = java.util.UUID.randomUUID()
            java.lang.String r23 = r4.toString()
            com.google.android.gms.internal.ads.go r4 = com.google.android.gms.ads.internal.ao.j()
            com.google.android.gms.ads.internal.zzbw r6 = r0.zzvw
            android.content.Context r6 = r6.zzrt
            android.os.Bundle r12 = r4.a(r6, r0, r9)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            r4 = r2
        L_0x0102:
            com.google.android.gms.ads.internal.zzbw r6 = r0.zzvw
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.internal.ads.zzrf> r6 = r6.zzadi
            int r6 = r6.size()
            if (r4 >= r6) goto L_0x0133
            com.google.android.gms.ads.internal.zzbw r6 = r0.zzvw
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.internal.ads.zzrf> r6 = r6.zzadi
            java.lang.Object r6 = r6.keyAt(r4)
            java.lang.String r6 = (java.lang.String) r6
            r14.add(r6)
            com.google.android.gms.ads.internal.zzbw r10 = r0.zzvw
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.internal.ads.zzrc> r10 = r10.zzadh
            boolean r10 = r10.containsKey(r6)
            if (r10 == 0) goto L_0x0130
            com.google.android.gms.ads.internal.zzbw r10 = r0.zzvw
            android.support.v4.util.SimpleArrayMap<java.lang.String, com.google.android.gms.internal.ads.zzrc> r10 = r10.zzadh
            java.lang.Object r10 = r10.get(r6)
            if (r10 == 0) goto L_0x0130
            r15.add(r6)
        L_0x0130:
            int r4 = r4 + 1
            goto L_0x0102
        L_0x0133:
            com.google.android.gms.ads.internal.as r4 = new com.google.android.gms.ads.internal.as
            r4.<init>(r0)
            com.google.android.gms.internal.ads.kt r34 = com.google.android.gms.internal.ads.hb.a(r4)
            com.google.android.gms.ads.internal.at r4 = new com.google.android.gms.ads.internal.at
            r4.<init>(r0)
            com.google.android.gms.internal.ads.kt r44 = com.google.android.gms.internal.ads.hb.a(r4)
            if (r67 == 0) goto L_0x014e
            java.lang.String r4 = r67.c()
            r35 = r4
            goto L_0x0150
        L_0x014e:
            r35 = 0
        L_0x0150:
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzvw
            java.util.List<java.lang.String> r4 = r4.zzads
            if (r4 == 0) goto L_0x01a8
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzvw
            java.util.List<java.lang.String> r4 = r4.zzads
            int r4 = r4.size()
            if (r4 <= 0) goto L_0x01a8
            if (r8 == 0) goto L_0x0164
            int r2 = r8.versionCode
        L_0x0164:
            com.google.android.gms.internal.ads.gf r4 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r4 = r4.l()
            int r4 = r4.g()
            if (r2 <= r4) goto L_0x0189
            com.google.android.gms.internal.ads.gf r4 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r4 = r4.l()
            r4.m()
            com.google.android.gms.internal.ads.gf r4 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r4 = r4.l()
            r4.a(r2)
            goto L_0x01a8
        L_0x0189:
            com.google.android.gms.internal.ads.gf r2 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r2 = r2.l()
            org.json.JSONObject r2 = r2.l()
            if (r2 == 0) goto L_0x01a8
            com.google.android.gms.ads.internal.zzbw r4 = r0.zzvw
            java.lang.String r4 = r4.zzacp
            org.json.JSONArray r2 = r2.optJSONArray(r4)
            if (r2 == 0) goto L_0x01a8
            java.lang.String r2 = r2.toString()
            r46 = r2
            goto L_0x01aa
        L_0x01a8:
            r46 = 0
        L_0x01aa:
            com.google.android.gms.internal.ads.cr r53 = new com.google.android.gms.internal.ads.cr
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            com.google.android.gms.internal.ads.zzjn r6 = r2.zzacv
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            java.lang.String r10 = r2.zzacp
            java.lang.String r13 = com.google.android.gms.internal.ads.ajh.c()
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            com.google.android.gms.internal.ads.zzang r4 = r2.zzacr
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            java.util.List<java.lang.String> r2 = r2.zzads
            com.google.android.gms.internal.ads.gf r1 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r1 = r1.l()
            boolean r16 = r1.a()
            int r1 = r3.widthPixels
            r54 = r1
            int r1 = r3.heightPixels
            float r3 = r3.density
            java.util.List r24 = com.google.android.gms.internal.ads.akl.a()
            r55 = r2
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            java.lang.String r2 = r2.zzaco
            r56 = r2
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            com.google.android.gms.internal.ads.zzpl r2 = r2.zzadj
            r57 = r2
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            java.lang.String r27 = r2.zzfq()
            com.google.android.gms.internal.ads.hv r2 = com.google.android.gms.ads.internal.ao.D()
            float r28 = r2.a()
            com.google.android.gms.internal.ads.hv r2 = com.google.android.gms.ads.internal.ao.D()
            boolean r29 = r2.b()
            com.google.android.gms.ads.internal.ao.e()
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            android.content.Context r2 = r2.zzrt
            int r30 = com.google.android.gms.internal.ads.hd.i(r2)
            com.google.android.gms.ads.internal.ao.e()
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            com.google.android.gms.ads.internal.zzbx r2 = r2.zzacs
            int r31 = com.google.android.gms.internal.ads.hd.d(r2)
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            android.content.Context r2 = r2.zzrt
            boolean r2 = r2 instanceof android.app.Activity
            r58 = r2
            com.google.android.gms.internal.ads.gf r2 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r2 = r2.l()
            boolean r33 = r2.f()
            com.google.android.gms.internal.ads.gf r2 = com.google.android.gms.ads.internal.ao.i()
            boolean r36 = r2.d()
            com.google.android.gms.internal.ads.my r2 = com.google.android.gms.ads.internal.ao.z()
            int r37 = r2.a()
            com.google.android.gms.ads.internal.ao.e()
            android.os.Bundle r38 = com.google.android.gms.internal.ads.hd.c()
            com.google.android.gms.internal.ads.ie r2 = com.google.android.gms.ads.internal.ao.o()
            java.lang.String r39 = r2.a()
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            com.google.android.gms.internal.ads.zzlu r2 = r2.zzadl
            r59 = r2
            com.google.android.gms.internal.ads.ie r2 = com.google.android.gms.ads.internal.ao.o()
            boolean r41 = r2.b()
            com.google.android.gms.internal.ads.aob r2 = com.google.android.gms.internal.ads.aob.a()
            android.os.Bundle r42 = r2.j()
            com.google.android.gms.internal.ads.gf r2 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r2 = r2.l()
            r60 = r3
            com.google.android.gms.ads.internal.zzbw r3 = r0.zzvw
            java.lang.String r3 = r3.zzacp
            boolean r43 = r2.e(r3)
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            java.util.List<java.lang.Integer> r3 = r2.zzadn
            com.google.android.gms.ads.internal.zzbw r2 = r0.zzvw
            android.content.Context r2 = r2.zzrt
            com.google.android.gms.common.wrappers.PackageManagerWrapper r2 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r2)
            boolean r49 = r2.isCallerInstantApp()
            com.google.android.gms.internal.ads.gf r2 = com.google.android.gms.ads.internal.ao.i()
            boolean r50 = r2.e()
            com.google.android.gms.ads.internal.ao.g()
            boolean r51 = com.google.android.gms.internal.ads.hj.e()
            com.google.android.gms.internal.ads.gf r2 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.kt r2 = r2.n()
            r62 = r3
            r61 = r4
            r3 = 1000(0x3e8, double:4.94E-321)
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.MILLISECONDS
            r63 = r15
            r15 = 0
            java.lang.Object r0 = com.google.android.gms.internal.ads.ki.a(r2, r15, r3, r0)
            r52 = r0
            java.util.ArrayList r52 = (java.util.ArrayList) r52
            r0 = r55
            r25 = r56
            r26 = r57
            r32 = r58
            r40 = r59
            r2 = r53
            r19 = r60
            r45 = r62
            r3 = r11
            r11 = r61
            r4 = r5
            r5 = r6
            r6 = r10
            r10 = r13
            r13 = r0
            r0 = r63
            r15 = r66
            r17 = r54
            r18 = r1
            r47 = r0
            r48 = r68
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52)
            return r53
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzd.zza(com.google.android.gms.internal.ads.zzjj, android.os.Bundle, com.google.android.gms.internal.ads.ge, int):com.google.android.gms.internal.ads.cr");
    }

    @Nullable
    static String zzc(ga gaVar) {
        if (gaVar == null) {
            return null;
        }
        String str = gaVar.q;
        if (("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) && gaVar.o != null) {
            try {
                return new JSONObject(gaVar.o.k).getString("class_name");
            } catch (NullPointerException | JSONException unused) {
            }
        }
        return str;
    }

    @Nullable
    public final String getMediationAdapterClassName() {
        if (this.zzvw.zzacw == null) {
            return null;
        }
        return this.zzvw.zzacw.q;
    }

    public void onAdClicked() {
        if (this.zzvw.zzacw == null) {
            gv.e("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzvw.zzacw.r == null || this.zzvw.zzacw.r.c == null)) {
            ao.x();
            arh.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw, this.zzvw.zzacp, false, zzc(this.zzvw.zzacw.r.c));
        }
        if (!(this.zzvw.zzacw.o == null || this.zzvw.zzacw.o.f == null)) {
            ao.x();
            arh.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw, this.zzvw.zzacp, false, this.zzvw.zzacw.o.f);
        }
        super.onAdClicked();
    }

    public final void onPause() {
        this.zzvy.c(this.zzvw.zzacw);
    }

    public final void onResume() {
        this.zzvy.d(this.zzvw.zzacw);
    }

    public void pause() {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.b == null || !this.zzvw.zzfo())) {
            ao.g();
            hj.a(this.zzvw.zzacw.b);
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.p == null)) {
            try {
                this.zzvw.zzacw.p.pause();
            } catch (RemoteException unused) {
                gv.e("Could not pause mediation adapter.");
            }
        }
        this.zzvy.c(this.zzvw.zzacw);
        this.zzvv.b();
    }

    public final void recordImpression() {
        zza(this.zzvw.zzacw, false);
    }

    public void resume() {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
        nn nnVar = (this.zzvw.zzacw == null || this.zzvw.zzacw.b == null) ? null : this.zzvw.zzacw.b;
        if (nnVar != null && this.zzvw.zzfo()) {
            ao.g();
            hj.b(this.zzvw.zzacw.b);
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.p == null)) {
            try {
                this.zzvw.zzacw.p.resume();
            } catch (RemoteException unused) {
                gv.e("Could not resume mediation adapter.");
            }
        }
        if (nnVar == null || !nnVar.zzum()) {
            this.zzvv.c();
        }
        this.zzvy.d(this.zzvw.zzacw);
    }

    public void showInterstitial() {
        gv.e("showInterstitial is not supported for current ad type");
    }

    /* access modifiers changed from: protected */
    public void zza(@Nullable ga gaVar, boolean z) {
        if (gaVar == null) {
            gv.e("Ad state was null when trying to ping impression URLs.");
            return;
        }
        if (gaVar == null) {
            gv.e("Ad state was null when trying to ping impression URLs.");
        } else {
            gv.b("Pinging Impression URLs.");
            if (this.zzvw.zzacy != null) {
                this.zzvw.zzacy.a();
            }
            gaVar.K.a(zzb.AD_IMPRESSION);
            if (gaVar.e != null && !gaVar.D) {
                ao.e();
                hd.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzc(gaVar.e));
                gaVar.D = true;
            }
        }
        if (!gaVar.F || z) {
            if (!(gaVar.r == null || gaVar.r.d == null)) {
                ao.x();
                arh.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, gaVar, this.zzvw.zzacp, z, zzc(gaVar.r.d));
            }
            if (!(gaVar.o == null || gaVar.o.g == null)) {
                ao.x();
                arh.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, gaVar, this.zzvw.zzacp, z, gaVar.o.g);
            }
            gaVar.F = true;
        }
    }

    public final void zza(zzqs zzqs, String str) {
        Object obj;
        zzrc zzrc = null;
        if (zzqs != null) {
            try {
                obj = zzqs.getCustomTemplateId();
            } catch (RemoteException e) {
                gv.c("Unable to call onCustomClick.", e);
                return;
            }
        } else {
            obj = null;
        }
        if (!(this.zzvw.zzadh == null || obj == null)) {
            zzrc = (zzrc) this.zzvw.zzadh.get(obj);
        }
        if (zzrc == null) {
            gv.e("Mediation adapter invoked onCustomClick but no listeners were set.");
        } else {
            zzrc.zzb(zzqs, str);
        }
    }

    public final boolean zza(cr crVar, aky aky) {
        this.zzvr = aky;
        aky.a("seq_num", crVar.g);
        aky.a("request_id", crVar.v);
        aky.a("session_id", crVar.h);
        if (crVar.f != null) {
            aky.a("app_version", String.valueOf(crVar.f.versionCode));
        }
        zzbw zzbw = this.zzvw;
        ao.a();
        Context context = this.zzvw.zzrt;
        ahl ahl = this.zzwc.d;
        gq cuVar = crVar.b.extras.getBundle("sdk_less_server_data") != null ? new cu(context, crVar, this, ahl) : new bx(context, crVar, this, ahl);
        cuVar.h();
        zzbw.zzact = cuVar;
        return true;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(ga gaVar) {
        zzjj zzjj;
        boolean z = false;
        if (this.zzvx != null) {
            zzjj = this.zzvx;
            this.zzvx = null;
        } else {
            zzjj = gaVar.a;
            if (zzjj.extras != null) {
                z = zzjj.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(zzjj, gaVar, z);
    }

    /* access modifiers changed from: protected */
    public boolean zza(@Nullable ga gaVar, ga gaVar2) {
        int i;
        if (!(gaVar == null || gaVar.s == null)) {
            gaVar.s.zza((ara) null);
        }
        if (gaVar2.s != null) {
            gaVar2.s.zza((ara) this);
        }
        int i2 = 0;
        if (gaVar2.r != null) {
            i2 = gaVar2.r.r;
            i = gaVar2.r.s;
        } else {
            i = 0;
        }
        this.zzvw.zzadt.a(i2, i);
        return true;
    }

    public boolean zza(zzjj zzjj, aky aky) {
        return zza(zzjj, aky, 1);
    }

    public final boolean zza(zzjj zzjj, aky aky, int i) {
        ge geVar;
        if (!zzca()) {
            return false;
        }
        ao.e();
        age a = ao.i().a(this.zzvw.zzrt);
        String str = null;
        Bundle a2 = a == null ? null : hd.a(a);
        this.zzvv.a();
        this.zzvw.zzadv = 0;
        if (((Boolean) ajh.f().a(akl.cs)).booleanValue()) {
            geVar = ao.i().l().h();
            d m = ao.m();
            Context context = this.zzvw.zzrt;
            zzang zzang = this.zzvw.zzacr;
            String str2 = this.zzvw.zzacp;
            if (geVar != null) {
                str = geVar.d();
            }
            m.a(context, zzang, false, geVar, str, str2, null);
        } else {
            geVar = null;
        }
        return zza(zza(zzjj, a2, geVar, i), aky);
    }

    /* access modifiers changed from: protected */
    public boolean zza(zzjj zzjj, ga gaVar, boolean z) {
        af afVar;
        long j;
        if (!z && this.zzvw.zzfo()) {
            if (gaVar.i > 0) {
                afVar = this.zzvv;
                j = gaVar.i;
            } else if (gaVar.r != null && gaVar.r.j > 0) {
                afVar = this.zzvv;
                j = gaVar.r.j;
            } else if (!gaVar.n && gaVar.d == 2) {
                this.zzvv.b(zzjj);
            }
            afVar.a(zzjj, j);
        }
        return this.zzvv.e();
    }

    public final void zzb(ga gaVar) {
        super.zzb(gaVar);
        if (gaVar.o != null) {
            gv.b("Disable the debug gesture detector on the mediation ad frame.");
            if (this.zzvw.zzacs != null) {
                this.zzvw.zzacs.zzfu();
            }
            gv.b("Pinging network fill URLs.");
            ao.x();
            arh.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, gaVar, this.zzvw.zzacp, false, gaVar.o.j);
            if (!(gaVar.r == null || gaVar.r.g == null || gaVar.r.g.size() <= 0)) {
                gv.b("Pinging urls remotely");
                ao.e().a(this.zzvw.zzrt, gaVar.r.g);
            }
        } else {
            gv.b("Enable the debug gesture detector on the admob ad frame.");
            if (this.zzvw.zzacs != null) {
                this.zzvw.zzacs.zzft();
            }
        }
        if (gaVar.d == 3 && gaVar.r != null && gaVar.r.f != null) {
            gv.b("Pinging no fill URLs.");
            ao.x();
            arh.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, gaVar, this.zzvw.zzacp, false, gaVar.r.f);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb(@Nullable ga gaVar, boolean z) {
        if (gaVar != null) {
            if (!(gaVar == null || gaVar.f == null || gaVar.E)) {
                ao.e();
                hd.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzb(gaVar.f));
                gaVar.E = true;
            }
            if (!gaVar.G || z) {
                if (!(gaVar.r == null || gaVar.r.e == null)) {
                    ao.x();
                    arh.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, gaVar, this.zzvw.zzacp, z, zzb(gaVar.r.e));
                }
                if (!(gaVar.o == null || gaVar.o.h == null)) {
                    ao.x();
                    arh.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, gaVar, this.zzvw.zzacp, z, gaVar.o.h);
                }
                gaVar.G = true;
            }
        }
    }

    public final void zzb(String str, String str2) {
        onAppEvent(str, str2);
    }

    /* access modifiers changed from: protected */
    public final boolean zzc(zzjj zzjj) {
        return super.zzc(zzjj) && !this.zzwi;
    }

    /* access modifiers changed from: protected */
    public boolean zzca() {
        ao.e();
        if (hd.a(this.zzvw.zzrt, "android.permission.INTERNET")) {
            ao.e();
            if (hd.a(this.zzvw.zzrt)) {
                return true;
            }
        }
        return false;
    }

    public void zzcb() {
        this.zzwi = false;
        zzbn();
        this.zzvw.zzacy.c();
    }

    public void zzcc() {
        this.zzwi = true;
        zzbp();
    }

    public void zzcd() {
        gv.e("Mediated ad does not support onVideoEnd callback");
    }

    public void zzce() {
        onAdClicked();
    }

    public final void zzcf() {
        zzcb();
    }

    public final void zzcg() {
        zzbo();
    }

    public final void zzch() {
        zzcc();
    }

    public final void zzci() {
        if (this.zzvw.zzacw != null) {
            String str = this.zzvw.zzacw.q;
            StringBuilder sb = new StringBuilder(74 + String.valueOf(str).length());
            sb.append("Mediation adapter ");
            sb.append(str);
            sb.append(" refreshed, but mediation adapters should never refresh.");
            gv.e(sb.toString());
        }
        zza(this.zzvw.zzacw, true);
        zzb(this.zzvw.zzacw, true);
        zzbq();
    }

    public void zzcj() {
        recordImpression();
    }

    @Nullable
    public final String zzck() {
        if (this.zzvw.zzacw == null) {
            return null;
        }
        return zzc(this.zzvw.zzacw);
    }

    public final void zzcl() {
        Executor executor = kz.a;
        af afVar = this.zzvv;
        afVar.getClass();
        executor.execute(aq.a(afVar));
    }

    public final void zzcm() {
        Executor executor = kz.a;
        af afVar = this.zzvv;
        afVar.getClass();
        executor.execute(ar.a(afVar));
    }
}
