package com.google.android.gms.internal.ads;

import android.content.Context;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzhu.zza.zzb;

@bu
public final class bx extends gq implements cj {
    @VisibleForTesting
    hw a;
    private final bw b;
    /* access modifiers changed from: private */
    public final cr c;
    /* access modifiers changed from: private */
    public final Object d = new Object();
    private final Context e;
    private final ahh f;
    private final ahl g;
    @VisibleForTesting
    private zzaef h;
    /* access modifiers changed from: private */
    public Runnable i;
    @VisibleForTesting
    private zzaej j;
    @VisibleForTesting
    private aqz k;

    public bx(Context context, cr crVar, bw bwVar, ahl ahl) {
        ahh ahh;
        ahi ahi;
        this.b = bwVar;
        this.e = context;
        this.c = crVar;
        this.g = ahl;
        this.f = new ahh(this.g);
        this.f.a((ahi) new by(this));
        aii aii = new aii();
        aii.a = Integer.valueOf(this.c.j.zzcve);
        aii.b = Integer.valueOf(this.c.j.zzcvf);
        aii.c = Integer.valueOf(this.c.j.zzcvg ? 0 : 2);
        this.f.a((ahi) new bz(aii));
        if (this.c.f != null) {
            this.f.a((ahi) new ca(this));
        }
        zzjn zzjn = this.c.c;
        if (zzjn.zzarc && "interstitial_mb".equals(zzjn.zzarb)) {
            ahh = this.f;
            ahi = cb.a;
        } else if (zzjn.zzarc && "reward_mb".equals(zzjn.zzarb)) {
            ahh = this.f;
            ahi = cc.a;
        } else if (zzjn.zzare || zzjn.zzarc) {
            ahh = this.f;
            ahi = ce.a;
        } else {
            ahh = this.f;
            ahi = cd.a;
        }
        ahh.a(ahi);
        this.f.a(zzb.AD_REQUEST);
    }

    @VisibleForTesting
    private final zzjn a(zzaef zzaef) throws zzadu {
        zzjn[] zzjnArr;
        if (((this.h == null || this.h.zzadn == null || this.h.zzadn.size() <= 1) ? false : true) && this.k != null && !this.k.t) {
            return null;
        }
        if (this.j.zzarf) {
            for (zzjn zzjn : zzaef.zzacv.zzard) {
                if (zzjn.zzarf) {
                    return new zzjn(zzjn, zzaef.zzacv.zzard);
                }
            }
        }
        if (this.j.zzcet == null) {
            throw new zzadu("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.j.zzcet.split(EtsyDialogFragment.OPT_X_BUTTON);
        if (split.length != 2) {
            String str = "Invalid ad size format from the ad response: ";
            String valueOf = String.valueOf(this.j.zzcet);
            throw new zzadu(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            zzjn[] zzjnArr2 = zzaef.zzacv.zzard;
            int length = zzjnArr2.length;
            for (int i2 = 0; i2 < length; i2++) {
                zzjn zzjn2 = zzjnArr2[i2];
                float f2 = this.e.getResources().getDisplayMetrics().density;
                int i3 = zzjn2.width == -1 ? (int) (((float) zzjn2.widthPixels) / f2) : zzjn2.width;
                int i4 = zzjn2.height == -2 ? (int) (((float) zzjn2.heightPixels) / f2) : zzjn2.height;
                if (parseInt == i3 && parseInt2 == i4 && !zzjn2.zzarf) {
                    return new zzjn(zzjn2, zzaef.zzacv.zzard);
                }
            }
            String str2 = "The ad size from the ad response was not one of the requested sizes: ";
            String valueOf2 = String.valueOf(this.j.zzcet);
            throw new zzadu(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), 0);
        } catch (NumberFormatException unused) {
            String str3 = "Invalid ad size number from the ad response: ";
            String valueOf3 = String.valueOf(this.j.zzcet);
            throw new zzadu(valueOf3.length() != 0 ? str3.concat(valueOf3) : new String(str3), 0);
        }
    }

    /* access modifiers changed from: private */
    public final void a(int i2, String str) {
        int i3 = i2;
        if (i3 == 3 || i3 == -1) {
            gv.d(str);
        } else {
            gv.e(str);
        }
        this.j = this.j == null ? new zzaej(i3) : new zzaej(i3, this.j.zzbsu);
        gb gbVar = new gb(this.h != null ? this.h : new zzaef(this.c, -1, null, null, null), this.j, this.k, null, i3, -1, this.j.zzceu, null, this.f, null);
        this.b.zza(gbVar);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final hw a(zzang zzang, lg<zzaef> lgVar) {
        Context context = this.e;
        if (new ci(context).a(zzang)) {
            gv.b("Fetching ad response from local ad request service.");
            co coVar = new co(context, lgVar, this);
            coVar.c();
            return coVar;
        }
        gv.b("Fetching ad response from remote ad request service.");
        ajh.a();
        if (jp.c(context)) {
            return new cp(context, zzang, lgVar, this);
        }
        gv.e("Failed to connect to remote ad request service.");
        return null;
    }

    public final void a() {
        gv.b("AdLoaderBackgroundTask started.");
        this.i = new cf(this);
        hd.a.postDelayed(this.i, ((Long) ajh.f().a(akl.bA)).longValue());
        long elapsedRealtime = ao.l().elapsedRealtime();
        if (((Boolean) ajh.f().a(akl.by)).booleanValue() && this.c.b.extras != null) {
            String string = this.c.b.extras.getString("_ad");
            if (string != null) {
                zzaef zzaef = new zzaef(this.c, elapsedRealtime, null, null, null);
                this.h = zzaef;
                a(dj.a(this.e, this.h, string));
                return;
            }
        }
        lk lkVar = new lk();
        hb.a((Runnable) new cg(this, lkVar));
        String h2 = ao.B().h(this.e);
        String i2 = ao.B().i(this.e);
        String j2 = ao.B().j(this.e);
        ao.B().f(this.e, j2);
        zzaef zzaef2 = new zzaef(this.c, elapsedRealtime, h2, i2, j2);
        this.h = zzaef2;
        lkVar.a(this.h);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(ahx ahx) {
        ahx.c.a = this.c.f.packageName;
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x01e9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(@android.support.annotation.NonNull com.google.android.gms.internal.ads.zzaej r14) {
        /*
            r13 = this;
            java.lang.String r0 = "Received ad response."
            com.google.android.gms.internal.ads.gv.b(r0)
            r13.j = r14
            com.google.android.gms.common.util.Clock r14 = com.google.android.gms.ads.internal.ao.l()
            long r6 = r14.elapsedRealtime()
            java.lang.Object r14 = r13.d
            monitor-enter(r14)
            r0 = 0
            r13.a = r0     // Catch:{ all -> 0x0217 }
            monitor-exit(r14)     // Catch:{ all -> 0x0217 }
            com.google.android.gms.internal.ads.gf r14 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r14 = r14.l()
            com.google.android.gms.internal.ads.zzaej r1 = r13.j
            boolean r1 = r1.zzcdr
            r14.d(r1)
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r14 = com.google.android.gms.internal.ads.akl.aT
            com.google.android.gms.internal.ads.akj r1 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r14 = r1.a(r14)
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x005c
            com.google.android.gms.internal.ads.zzaej r14 = r13.j
            boolean r14 = r14.zzced
            if (r14 == 0) goto L_0x004d
            com.google.android.gms.internal.ads.gf r14 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r14 = r14.l()
            com.google.android.gms.internal.ads.zzaef r1 = r13.h
            java.lang.String r1 = r1.zzacp
            r14.c(r1)
            goto L_0x005c
        L_0x004d:
            com.google.android.gms.internal.ads.gf r14 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r14 = r14.l()
            com.google.android.gms.internal.ads.zzaef r1 = r13.h
            java.lang.String r1 = r1.zzacp
            r14.d(r1)
        L_0x005c:
            com.google.android.gms.internal.ads.zzaej r14 = r13.j     // Catch:{ zzadu -> 0x020a }
            int r14 = r14.errorCode     // Catch:{ zzadu -> 0x020a }
            r1 = -2
            r2 = -3
            if (r14 == r1) goto L_0x008b
            com.google.android.gms.internal.ads.zzaej r14 = r13.j     // Catch:{ zzadu -> 0x020a }
            int r14 = r14.errorCode     // Catch:{ zzadu -> 0x020a }
            if (r14 == r2) goto L_0x008b
            com.google.android.gms.internal.ads.zzadu r14 = new com.google.android.gms.internal.ads.zzadu     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.zzaej r0 = r13.j     // Catch:{ zzadu -> 0x020a }
            int r0 = r0.errorCode     // Catch:{ zzadu -> 0x020a }
            r1 = 66
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ zzadu -> 0x020a }
            r2.<init>(r1)     // Catch:{ zzadu -> 0x020a }
            java.lang.String r1 = "There was a problem getting an ad response. ErrorCode: "
            r2.append(r1)     // Catch:{ zzadu -> 0x020a }
            r2.append(r0)     // Catch:{ zzadu -> 0x020a }
            java.lang.String r0 = r2.toString()     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.zzaej r1 = r13.j     // Catch:{ zzadu -> 0x020a }
            int r1 = r1.errorCode     // Catch:{ zzadu -> 0x020a }
            r14.<init>(r0, r1)     // Catch:{ zzadu -> 0x020a }
            throw r14     // Catch:{ zzadu -> 0x020a }
        L_0x008b:
            com.google.android.gms.internal.ads.zzaej r14 = r13.j     // Catch:{ zzadu -> 0x020a }
            int r14 = r14.errorCode     // Catch:{ zzadu -> 0x020a }
            r1 = 0
            if (r14 == r2) goto L_0x0139
            com.google.android.gms.internal.ads.zzaej r14 = r13.j     // Catch:{ zzadu -> 0x020a }
            java.lang.String r14 = r14.zzceo     // Catch:{ zzadu -> 0x020a }
            boolean r14 = android.text.TextUtils.isEmpty(r14)     // Catch:{ zzadu -> 0x020a }
            if (r14 == 0) goto L_0x00a5
            com.google.android.gms.internal.ads.zzadu r14 = new com.google.android.gms.internal.ads.zzadu     // Catch:{ zzadu -> 0x020a }
            java.lang.String r0 = "No fill from ad server."
            r1 = 3
            r14.<init>(r0, r1)     // Catch:{ zzadu -> 0x020a }
            throw r14     // Catch:{ zzadu -> 0x020a }
        L_0x00a5:
            com.google.android.gms.internal.ads.gf r14 = com.google.android.gms.ads.internal.ao.i()     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.gw r14 = r14.l()     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.zzaej r2 = r13.j     // Catch:{ zzadu -> 0x020a }
            boolean r2 = r2.zzcdd     // Catch:{ zzadu -> 0x020a }
            r14.a(r2)     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.zzaej r14 = r13.j     // Catch:{ zzadu -> 0x020a }
            boolean r14 = r14.zzceq     // Catch:{ zzadu -> 0x020a }
            if (r14 == 0) goto L_0x00f8
            com.google.android.gms.internal.ads.aqz r14 = new com.google.android.gms.internal.ads.aqz     // Catch:{ JSONException -> 0x00d1 }
            com.google.android.gms.internal.ads.zzaej r2 = r13.j     // Catch:{ JSONException -> 0x00d1 }
            java.lang.String r2 = r2.zzceo     // Catch:{ JSONException -> 0x00d1 }
            r14.<init>(r2)     // Catch:{ JSONException -> 0x00d1 }
            r13.k = r14     // Catch:{ JSONException -> 0x00d1 }
            com.google.android.gms.internal.ads.gf r14 = com.google.android.gms.ads.internal.ao.i()     // Catch:{ JSONException -> 0x00d1 }
            com.google.android.gms.internal.ads.aqz r2 = r13.k     // Catch:{ JSONException -> 0x00d1 }
            boolean r2 = r2.h     // Catch:{ JSONException -> 0x00d1 }
            r14.a(r2)     // Catch:{ JSONException -> 0x00d1 }
            goto L_0x0103
        L_0x00d1:
            r14 = move-exception
            java.lang.String r0 = "Could not parse mediation config."
            com.google.android.gms.internal.ads.gv.b(r0, r14)     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.zzadu r14 = new com.google.android.gms.internal.ads.zzadu     // Catch:{ zzadu -> 0x020a }
            java.lang.String r0 = "Could not parse mediation config: "
            com.google.android.gms.internal.ads.zzaej r2 = r13.j     // Catch:{ zzadu -> 0x020a }
            java.lang.String r2 = r2.zzceo     // Catch:{ zzadu -> 0x020a }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ zzadu -> 0x020a }
            int r3 = r2.length()     // Catch:{ zzadu -> 0x020a }
            if (r3 == 0) goto L_0x00ee
            java.lang.String r0 = r0.concat(r2)     // Catch:{ zzadu -> 0x020a }
            goto L_0x00f4
        L_0x00ee:
            java.lang.String r2 = new java.lang.String     // Catch:{ zzadu -> 0x020a }
            r2.<init>(r0)     // Catch:{ zzadu -> 0x020a }
            r0 = r2
        L_0x00f4:
            r14.<init>(r0, r1)     // Catch:{ zzadu -> 0x020a }
            throw r14     // Catch:{ zzadu -> 0x020a }
        L_0x00f8:
            com.google.android.gms.internal.ads.gf r14 = com.google.android.gms.ads.internal.ao.i()     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.zzaej r2 = r13.j     // Catch:{ zzadu -> 0x020a }
            boolean r2 = r2.zzbss     // Catch:{ zzadu -> 0x020a }
            r14.a(r2)     // Catch:{ zzadu -> 0x020a }
        L_0x0103:
            com.google.android.gms.internal.ads.zzaej r14 = r13.j     // Catch:{ zzadu -> 0x020a }
            java.lang.String r14 = r14.zzcds     // Catch:{ zzadu -> 0x020a }
            boolean r14 = android.text.TextUtils.isEmpty(r14)     // Catch:{ zzadu -> 0x020a }
            if (r14 != 0) goto L_0x0139
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r14 = com.google.android.gms.internal.ads.akl.cC     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ zzadu -> 0x020a }
            java.lang.Object r14 = r2.a(r14)     // Catch:{ zzadu -> 0x020a }
            java.lang.Boolean r14 = (java.lang.Boolean) r14     // Catch:{ zzadu -> 0x020a }
            boolean r14 = r14.booleanValue()     // Catch:{ zzadu -> 0x020a }
            if (r14 == 0) goto L_0x0139
            java.lang.String r14 = "Received cookie from server. Setting webview cookie in CookieManager."
            com.google.android.gms.internal.ads.gv.b(r14)     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.hj r14 = com.google.android.gms.ads.internal.ao.g()     // Catch:{ zzadu -> 0x020a }
            android.content.Context r2 = r13.e     // Catch:{ zzadu -> 0x020a }
            android.webkit.CookieManager r14 = r14.c(r2)     // Catch:{ zzadu -> 0x020a }
            if (r14 == 0) goto L_0x0139
            java.lang.String r2 = "googleads.g.doubleclick.net"
            com.google.android.gms.internal.ads.zzaej r3 = r13.j     // Catch:{ zzadu -> 0x020a }
            java.lang.String r3 = r3.zzcds     // Catch:{ zzadu -> 0x020a }
            r14.setCookie(r2, r3)     // Catch:{ zzadu -> 0x020a }
        L_0x0139:
            com.google.android.gms.internal.ads.zzaef r14 = r13.h     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.zzjn r14 = r14.zzacv     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.zzjn[] r14 = r14.zzard     // Catch:{ zzadu -> 0x020a }
            if (r14 == 0) goto L_0x0149
            com.google.android.gms.internal.ads.zzaef r14 = r13.h     // Catch:{ zzadu -> 0x020a }
            com.google.android.gms.internal.ads.zzjn r14 = r13.a(r14)     // Catch:{ zzadu -> 0x020a }
            r4 = r14
            goto L_0x014a
        L_0x0149:
            r4 = r0
        L_0x014a:
            com.google.android.gms.internal.ads.gf r14 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r14 = r14.l()
            com.google.android.gms.internal.ads.zzaej r2 = r13.j
            boolean r2 = r2.zzcfa
            r14.b(r2)
            com.google.android.gms.internal.ads.gf r14 = com.google.android.gms.ads.internal.ao.i()
            com.google.android.gms.internal.ads.gw r14 = r14.l()
            com.google.android.gms.internal.ads.zzaej r2 = r13.j
            boolean r2 = r2.zzcfm
            r14.c(r2)
            com.google.android.gms.internal.ads.zzaej r14 = r13.j
            java.lang.String r14 = r14.zzcey
            boolean r14 = android.text.TextUtils.isEmpty(r14)
            if (r14 != 0) goto L_0x0183
            org.json.JSONObject r14 = new org.json.JSONObject     // Catch:{ Exception -> 0x017d }
            com.google.android.gms.internal.ads.zzaej r2 = r13.j     // Catch:{ Exception -> 0x017d }
            java.lang.String r2 = r2.zzcey     // Catch:{ Exception -> 0x017d }
            r14.<init>(r2)     // Catch:{ Exception -> 0x017d }
            r10 = r14
            goto L_0x0184
        L_0x017d:
            r14 = move-exception
            java.lang.String r2 = "Error parsing the JSON for Active View."
            com.google.android.gms.internal.ads.gv.b(r2, r14)
        L_0x0183:
            r10 = r0
        L_0x0184:
            com.google.android.gms.internal.ads.zzaej r14 = r13.j
            int r14 = r14.zzcfo
            r2 = 2
            r3 = 1
            if (r14 != r2) goto L_0x01cb
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            com.google.android.gms.internal.ads.zzaef r14 = r13.h
            com.google.android.gms.internal.ads.zzjj r14 = r14.zzccv
            android.os.Bundle r2 = r14.zzaqg
            if (r2 == 0) goto L_0x019b
            android.os.Bundle r14 = r14.zzaqg
            goto L_0x01a0
        L_0x019b:
            android.os.Bundle r14 = new android.os.Bundle
            r14.<init>()
        L_0x01a0:
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r2 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r2 = r2.getName()
            android.os.Bundle r2 = r14.getBundle(r2)
            if (r2 == 0) goto L_0x01b7
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r2 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r2 = r2.getName()
            android.os.Bundle r14 = r14.getBundle(r2)
            goto L_0x01c6
        L_0x01b7:
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            java.lang.Class<com.google.ads.mediation.admob.AdMobAdapter> r5 = com.google.ads.mediation.admob.AdMobAdapter.class
            java.lang.String r5 = r5.getName()
            r14.putBundle(r5, r2)
            r14 = r2
        L_0x01c6:
            java.lang.String r2 = "render_test_label"
            r14.putBoolean(r2, r3)
        L_0x01cb:
            com.google.android.gms.internal.ads.zzaej r14 = r13.j
            int r14 = r14.zzcfo
            if (r14 != r3) goto L_0x01d5
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
        L_0x01d5:
            com.google.android.gms.internal.ads.zzaej r14 = r13.j
            int r14 = r14.zzcfo
            if (r14 != 0) goto L_0x01e9
            com.google.android.gms.internal.ads.zzaef r14 = r13.h
            com.google.android.gms.internal.ads.zzjj r14 = r14.zzccv
            boolean r14 = com.google.android.gms.internal.ads.jh.a(r14)
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r14)
            r12 = r14
            goto L_0x01ea
        L_0x01e9:
            r12 = r0
        L_0x01ea:
            com.google.android.gms.internal.ads.gb r14 = new com.google.android.gms.internal.ads.gb
            com.google.android.gms.internal.ads.zzaef r1 = r13.h
            com.google.android.gms.internal.ads.zzaej r2 = r13.j
            com.google.android.gms.internal.ads.aqz r3 = r13.k
            r5 = -2
            com.google.android.gms.internal.ads.zzaej r0 = r13.j
            long r8 = r0.zzceu
            com.google.android.gms.internal.ads.ahh r11 = r13.f
            r0 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r8, r10, r11, r12)
            com.google.android.gms.internal.ads.bw r0 = r13.b
            r0.zza(r14)
        L_0x0202:
            android.os.Handler r14 = com.google.android.gms.internal.ads.hd.a
            java.lang.Runnable r0 = r13.i
            r14.removeCallbacks(r0)
            return
        L_0x020a:
            r14 = move-exception
            int r0 = r14.getErrorCode()
            java.lang.String r14 = r14.getMessage()
            r13.a(r0, r14)
            goto L_0x0202
        L_0x0217:
            r0 = move-exception
            monitor-exit(r14)     // Catch:{ all -> 0x0217 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.bx.a(com.google.android.gms.internal.ads.zzaej):void");
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void b(ahx ahx) {
        ahx.a = this.c.v;
    }

    public final void c_() {
        synchronized (this.d) {
            if (this.a != null) {
                this.a.b();
            }
        }
    }
}
