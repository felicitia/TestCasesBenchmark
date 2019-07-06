package com.google.android.gms.internal.ads;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class zzet implements OnGlobalLayoutListener, OnScrollChangedListener {
    private final Object mLock = new Object();
    private boolean zzaaq = false;
    private je zzadz;
    private final Context zzaeo;
    private final WeakReference<ga> zzaeq;
    private WeakReference<ViewTreeObserver> zzaer;
    private final afx zzaes;
    protected final aem zzaet;
    private final WindowManager zzaeu;
    private final PowerManager zzaev;
    private final KeyguardManager zzaew;
    private final DisplayMetrics zzaex;
    @Nullable
    private aev zzaey;
    private boolean zzaez;
    private boolean zzafa = false;
    private boolean zzafb;
    private boolean zzafc;
    private boolean zzafd;
    @Nullable
    @VisibleForTesting
    private BroadcastReceiver zzafe;
    private final HashSet<ael> zzaff = new HashSet<>();
    private final HashSet<afj> zzafg = new HashSet<>();
    private final Rect zzafh = new Rect();
    private final aeq zzafi;
    private float zzafj;

    public zzet(Context context, zzjn zzjn, ga gaVar, zzang zzang, afx afx) {
        this.zzaeq = new WeakReference<>(gaVar);
        this.zzaes = afx;
        this.zzaer = new WeakReference<>(null);
        this.zzafb = true;
        this.zzafd = false;
        this.zzadz = new je(200);
        aem aem = new aem(UUID.randomUUID().toString(), zzang, zzjn.zzarb, gaVar.k, gaVar.a(), zzjn.zzare);
        this.zzaet = aem;
        this.zzaeu = (WindowManager) context.getSystemService("window");
        this.zzaev = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzaew = (KeyguardManager) context.getSystemService("keyguard");
        this.zzaeo = context;
        this.zzafi = new aeq(this, new Handler());
        this.zzaeo.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.zzafi);
        this.zzaex = context.getResources().getDisplayMetrics();
        Display defaultDisplay = this.zzaeu.getDefaultDisplay();
        this.zzafh.right = defaultDisplay.getWidth();
        this.zzafh.bottom = defaultDisplay.getHeight();
        zzgb();
    }

    @VisibleForTesting
    private final boolean isScreenOn() {
        return VERSION.SDK_INT >= 20 ? this.zzaev.isInteractive() : this.zzaev.isScreenOn();
    }

    private static int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    private final JSONObject zza(@Nullable View view, @Nullable Boolean bool) throws JSONException {
        if (view == null) {
            return zzgg().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
        }
        boolean a = ao.g().a(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Exception e) {
            gv.b("Failure getting view location.", e);
        }
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect2, null);
        Rect rect3 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect3);
        Rect rect4 = new Rect();
        view.getHitRect(rect4);
        JSONObject zzgg = zzgg();
        zzgg.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", a).put("viewBox", new JSONObject().put("top", zza(this.zzafh.top, this.zzaex)).put("bottom", zza(this.zzafh.bottom, this.zzaex)).put("left", zza(this.zzafh.left, this.zzaex)).put("right", zza(this.zzafh.right, this.zzaex))).put("adBox", new JSONObject().put("top", zza(rect.top, this.zzaex)).put("bottom", zza(rect.bottom, this.zzaex)).put("left", zza(rect.left, this.zzaex)).put("right", zza(rect.right, this.zzaex))).put("globalVisibleBox", new JSONObject().put("top", zza(rect2.top, this.zzaex)).put("bottom", zza(rect2.bottom, this.zzaex)).put("left", zza(rect2.left, this.zzaex)).put("right", zza(rect2.right, this.zzaex))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect3.top, this.zzaex)).put("bottom", zza(rect3.bottom, this.zzaex)).put("left", zza(rect3.left, this.zzaex)).put("right", zza(rect3.right, this.zzaex))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect4.top, this.zzaex)).put("bottom", zza(rect4.bottom, this.zzaex)).put("left", zza(rect4.left, this.zzaex)).put("right", zza(rect4.right, this.zzaex))).put("screenDensity", (double) this.zzaex.density);
        if (bool == null) {
            bool = Boolean.valueOf(ao.e().a(view, this.zzaev, this.zzaew));
        }
        zzgg.put("isVisible", bool.booleanValue());
        return zzgg;
    }

    private static JSONObject zza(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        jSONArray.put(jSONObject);
        jSONObject2.put("units", jSONArray);
        return jSONObject2;
    }

    private final void zza(JSONObject jSONObject, boolean z) {
        try {
            JSONObject zza = zza(jSONObject);
            ArrayList arrayList = new ArrayList(this.zzafg);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((afj) obj).a(zza, z);
            }
        } catch (Throwable th) {
            gv.b("Skipping active view message.", th);
        }
    }

    private final void zzgd() {
        if (this.zzaey != null) {
            this.zzaey.a(this);
        }
    }

    private final void zzgf() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzaer.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    private final JSONObject zzgg() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzaet.b()).put("activeViewJSON", this.zzaet.c()).put("timestamp", ao.l().elapsedRealtime()).put("adFormat", this.zzaet.a()).put("hashCode", this.zzaet.d()).put("isMraid", this.zzaet.e()).put("isStopped", this.zzafa).put("isPaused", this.zzaaq).put("isNative", this.zzaet.f()).put("isScreenOn", isScreenOn()).put("appMuted", ao.D().b()).put("appVolume", (double) ao.D().a()).put("deviceVolume", (double) this.zzafj);
        return jSONObject;
    }

    public final void onGlobalLayout() {
        zzl(2);
    }

    public final void onScrollChanged() {
        zzl(1);
    }

    public final void pause() {
        synchronized (this.mLock) {
            this.zzaaq = true;
            zzl(3);
        }
    }

    public final void resume() {
        synchronized (this.mLock) {
            this.zzaaq = false;
            zzl(3);
        }
    }

    public final void stop() {
        synchronized (this.mLock) {
            this.zzafa = true;
            zzl(3);
        }
    }

    public final void zza(aev aev) {
        synchronized (this.mLock) {
            this.zzaey = aev;
        }
    }

    public final void zza(afj afj) {
        if (this.zzafg.isEmpty()) {
            synchronized (this.mLock) {
                if (this.zzafe == null) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.SCREEN_ON");
                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                    this.zzafe = new aeo(this);
                    ao.E().a(this.zzaeo, this.zzafe, intentFilter);
                }
            }
            zzl(3);
        }
        this.zzafg.add(afj);
        try {
            afj.a(zza(zza(this.zzaes.a(), (Boolean) null)), false);
        } catch (JSONException e) {
            gv.b("Skipping measurement update for new client.", e);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(afj afj, Map<String, String> map) {
        String str = "Received request to untrack: ";
        String valueOf = String.valueOf(this.zzaet.d());
        gv.b(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        zzb(afj);
    }

    public final void zzb(afj afj) {
        this.zzafg.remove(afj);
        afj.b();
        if (this.zzafg.isEmpty()) {
            synchronized (this.mLock) {
                zzgf();
                synchronized (this.mLock) {
                    if (this.zzafe != null) {
                        try {
                            ao.E().a(this.zzaeo, this.zzafe);
                        } catch (IllegalStateException e) {
                            gv.b("Failed trying to unregister the receiver", e);
                        } catch (Exception e2) {
                            ao.i().a((Throwable) e2, "ActiveViewUnit.stopScreenStatusMonitoring");
                        }
                        this.zzafe = null;
                    }
                }
                this.zzaeo.getContentResolver().unregisterContentObserver(this.zzafi);
                int i = 0;
                this.zzafb = false;
                zzgd();
                ArrayList arrayList = new ArrayList(this.zzafg);
                int size = arrayList.size();
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    zzb((afj) obj);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final boolean zzc(@Nullable Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = (String) map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(this.zzaet.d());
    }

    /* access modifiers changed from: 0000 */
    public final void zzd(Map<String, String> map) {
        zzl(3);
    }

    /* access modifiers changed from: 0000 */
    public final void zze(Map<String, String> map) {
        if (map.containsKey("isVisible")) {
            boolean z = "1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"));
            Iterator it = this.zzaff.iterator();
            while (it.hasNext()) {
                ((ael) it.next()).a(this, z);
            }
        }
    }

    public final void zzgb() {
        this.zzafj = hv.a(this.zzaeo);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0036 A[Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003b A[Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzgc() {
        /*
            r5 = this;
            java.lang.Object r0 = r5.mLock
            monitor-enter(r0)
            boolean r1 = r5.zzafb     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0044
            r1 = 1
            r5.zzafc = r1     // Catch:{ all -> 0x0046 }
            org.json.JSONObject r2 = r5.zzgg()     // Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }
            java.lang.String r3 = "doneReasonCode"
            java.lang.String r4 = "u"
            r2.put(r3, r4)     // Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }
            r5.zza(r2, r1)     // Catch:{ JSONException -> 0x0020, RuntimeException -> 0x0019 }
            goto L_0x0024
        L_0x0019:
            r1 = move-exception
            java.lang.String r2 = "Failure while processing active view data."
        L_0x001c:
            com.google.android.gms.internal.ads.gv.b(r2, r1)     // Catch:{ all -> 0x0046 }
            goto L_0x0024
        L_0x0020:
            r1 = move-exception
            java.lang.String r2 = "JSON failure while processing active view data."
            goto L_0x001c
        L_0x0024:
            java.lang.String r1 = "Untracking ad unit: "
            com.google.android.gms.internal.ads.aem r2 = r5.zzaet     // Catch:{ all -> 0x0046 }
            java.lang.String r2 = r2.d()     // Catch:{ all -> 0x0046 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ all -> 0x0046 }
            int r3 = r2.length()     // Catch:{ all -> 0x0046 }
            if (r3 == 0) goto L_0x003b
            java.lang.String r1 = r1.concat(r2)     // Catch:{ all -> 0x0046 }
            goto L_0x0041
        L_0x003b:
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x0046 }
            r2.<init>(r1)     // Catch:{ all -> 0x0046 }
            r1 = r2
        L_0x0041:
            com.google.android.gms.internal.ads.gv.b(r1)     // Catch:{ all -> 0x0046 }
        L_0x0044:
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            return
        L_0x0046:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0046 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzet.zzgc():void");
    }

    public final boolean zzge() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzafb;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00cd, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzl(int r8) {
        /*
            r7 = this;
            java.lang.Object r0 = r7.mLock
            monitor-enter(r0)
            java.util.HashSet<com.google.android.gms.internal.ads.afj> r1 = r7.zzafg     // Catch:{ all -> 0x00ce }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x00ce }
        L_0x0009:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x00ce }
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x001f
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x00ce }
            com.google.android.gms.internal.ads.afj r2 = (com.google.android.gms.internal.ads.afj) r2     // Catch:{ all -> 0x00ce }
            boolean r2 = r2.a()     // Catch:{ all -> 0x00ce }
            if (r2 == 0) goto L_0x0009
            r1 = r4
            goto L_0x0020
        L_0x001f:
            r1 = r3
        L_0x0020:
            if (r1 == 0) goto L_0x00cc
            boolean r1 = r7.zzafb     // Catch:{ all -> 0x00ce }
            if (r1 != 0) goto L_0x0028
            goto L_0x00cc
        L_0x0028:
            com.google.android.gms.internal.ads.afx r1 = r7.zzaes     // Catch:{ all -> 0x00ce }
            android.view.View r1 = r1.a()     // Catch:{ all -> 0x00ce }
            if (r1 == 0) goto L_0x0040
            com.google.android.gms.internal.ads.hd r2 = com.google.android.gms.ads.internal.ao.e()     // Catch:{ all -> 0x00ce }
            android.os.PowerManager r5 = r7.zzaev     // Catch:{ all -> 0x00ce }
            android.app.KeyguardManager r6 = r7.zzaew     // Catch:{ all -> 0x00ce }
            boolean r2 = r2.a(r1, r5, r6)     // Catch:{ all -> 0x00ce }
            if (r2 == 0) goto L_0x0040
            r2 = r4
            goto L_0x0041
        L_0x0040:
            r2 = r3
        L_0x0041:
            if (r1 == 0) goto L_0x0053
            if (r2 == 0) goto L_0x0053
            android.graphics.Rect r5 = new android.graphics.Rect     // Catch:{ all -> 0x00ce }
            r5.<init>()     // Catch:{ all -> 0x00ce }
            r6 = 0
            boolean r5 = r1.getGlobalVisibleRect(r5, r6)     // Catch:{ all -> 0x00ce }
            if (r5 == 0) goto L_0x0053
            r5 = r4
            goto L_0x0054
        L_0x0053:
            r5 = r3
        L_0x0054:
            com.google.android.gms.internal.ads.afx r6 = r7.zzaes     // Catch:{ all -> 0x00ce }
            boolean r6 = r6.b()     // Catch:{ all -> 0x00ce }
            if (r6 == 0) goto L_0x0061
            r7.zzgc()     // Catch:{ all -> 0x00ce }
            monitor-exit(r0)     // Catch:{ all -> 0x00ce }
            return
        L_0x0061:
            if (r8 != r4) goto L_0x0071
            com.google.android.gms.internal.ads.je r6 = r7.zzadz     // Catch:{ all -> 0x00ce }
            boolean r6 = r6.a()     // Catch:{ all -> 0x00ce }
            if (r6 != 0) goto L_0x0071
            boolean r6 = r7.zzafd     // Catch:{ all -> 0x00ce }
            if (r5 != r6) goto L_0x0071
            monitor-exit(r0)     // Catch:{ all -> 0x00ce }
            return
        L_0x0071:
            if (r5 != 0) goto L_0x007b
            boolean r6 = r7.zzafd     // Catch:{ all -> 0x00ce }
            if (r6 != 0) goto L_0x007b
            if (r8 != r4) goto L_0x007b
            monitor-exit(r0)     // Catch:{ all -> 0x00ce }
            return
        L_0x007b:
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r2)     // Catch:{ RuntimeException | JSONException -> 0x0089 }
            org.json.JSONObject r8 = r7.zza(r1, r8)     // Catch:{ RuntimeException | JSONException -> 0x0089 }
            r7.zza(r8, r3)     // Catch:{ RuntimeException | JSONException -> 0x0089 }
            r7.zzafd = r5     // Catch:{ RuntimeException | JSONException -> 0x0089 }
            goto L_0x008f
        L_0x0089:
            r8 = move-exception
            java.lang.String r1 = "Active view update failed."
            com.google.android.gms.internal.ads.gv.a(r1, r8)     // Catch:{ all -> 0x00ce }
        L_0x008f:
            com.google.android.gms.internal.ads.afx r8 = r7.zzaes     // Catch:{ all -> 0x00ce }
            com.google.android.gms.internal.ads.afx r8 = r8.c()     // Catch:{ all -> 0x00ce }
            android.view.View r8 = r8.a()     // Catch:{ all -> 0x00ce }
            if (r8 == 0) goto L_0x00c7
            java.lang.ref.WeakReference<android.view.ViewTreeObserver> r1 = r7.zzaer     // Catch:{ all -> 0x00ce }
            java.lang.Object r1 = r1.get()     // Catch:{ all -> 0x00ce }
            android.view.ViewTreeObserver r1 = (android.view.ViewTreeObserver) r1     // Catch:{ all -> 0x00ce }
            android.view.ViewTreeObserver r8 = r8.getViewTreeObserver()     // Catch:{ all -> 0x00ce }
            if (r8 == r1) goto L_0x00c7
            r7.zzgf()     // Catch:{ all -> 0x00ce }
            boolean r2 = r7.zzaez     // Catch:{ all -> 0x00ce }
            if (r2 == 0) goto L_0x00b8
            if (r1 == 0) goto L_0x00c0
            boolean r1 = r1.isAlive()     // Catch:{ all -> 0x00ce }
            if (r1 == 0) goto L_0x00c0
        L_0x00b8:
            r7.zzaez = r4     // Catch:{ all -> 0x00ce }
            r8.addOnScrollChangedListener(r7)     // Catch:{ all -> 0x00ce }
            r8.addOnGlobalLayoutListener(r7)     // Catch:{ all -> 0x00ce }
        L_0x00c0:
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference     // Catch:{ all -> 0x00ce }
            r1.<init>(r8)     // Catch:{ all -> 0x00ce }
            r7.zzaer = r1     // Catch:{ all -> 0x00ce }
        L_0x00c7:
            r7.zzgd()     // Catch:{ all -> 0x00ce }
            monitor-exit(r0)     // Catch:{ all -> 0x00ce }
            return
        L_0x00cc:
            monitor-exit(r0)     // Catch:{ all -> 0x00ce }
            return
        L_0x00ce:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ce }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzet.zzl(int):void");
    }
}
