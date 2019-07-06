package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.widget.FrameLayout;
import com.etsy.android.lib.models.ResponseConstants;

@bu
public class aiz {
    private zzld a;
    private final Object b = new Object();
    /* access modifiers changed from: private */
    public final aiv c;
    /* access modifiers changed from: private */
    public final aiu d;
    private final ajq e;
    /* access modifiers changed from: private */
    public final amh f;
    private final el g;
    /* access modifiers changed from: private */
    public final p h;
    private final ami i;

    @VisibleForTesting
    abstract class a<T> {
        a() {
        }

        /* access modifiers changed from: protected */
        @Nullable
        public abstract T a() throws RemoteException;

        /* access modifiers changed from: protected */
        @Nullable
        public abstract T a(zzld zzld) throws RemoteException;

        /* access modifiers changed from: protected */
        @Nullable
        public final T b() {
            zzld a2 = aiz.this.b();
            if (a2 == null) {
                ka.e("ClientApi class cannot be loaded.");
                return null;
            }
            try {
                return a(a2);
            } catch (RemoteException e) {
                ka.c("Cannot invoke local loader using ClientApi class", e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        @Nullable
        public final T c() {
            try {
                return a();
            } catch (RemoteException e) {
                ka.c("Cannot invoke remote loader", e);
                return null;
            }
        }
    }

    public aiz(aiv aiv, aiu aiu, ajq ajq, amh amh, el elVar, p pVar, ami ami) {
        this.c = aiv;
        this.d = aiu;
        this.e = ajq;
        this.f = amh;
        this.g = elVar;
        this.h = pVar;
        this.i = ami;
    }

    @Nullable
    private static zzld a() {
        try {
            Object newInstance = aiz.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").newInstance();
            if (newInstance instanceof IBinder) {
                return zzle.asInterface((IBinder) newInstance);
            }
            ka.e("ClientApi class is not an instance of IBinder");
            return null;
        } catch (Exception e2) {
            ka.c("Failed to instantiate ClientApi class.", e2);
            return null;
        }
    }

    @VisibleForTesting
    static <T> T a(Context context, boolean z, a<T> aVar) {
        T t;
        if (!z) {
            ajh.a();
            if (!jp.c(context)) {
                ka.b("Google Play Services is not available");
                z = true;
            }
        }
        ajh.a();
        int e2 = jp.e(context);
        ajh.a();
        if (e2 > jp.d(context)) {
            z = true;
        }
        akl.a(context);
        if (((Boolean) ajh.f().a(akl.f2de)).booleanValue()) {
            z = false;
        }
        if (z) {
            t = aVar.b();
            if (t == null) {
                return aVar.c();
            }
        } else {
            t = aVar.c();
            if (t == null) {
                t = aVar.b();
            }
        }
        return t;
    }

    /* access modifiers changed from: private */
    public static void a(Context context, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(ResponseConstants.ACTION, "no_ads_fallback");
        bundle.putString("flow", str);
        ajh.a().a(context, (String) null, "gmob-apps", bundle, true);
    }

    /* access modifiers changed from: private */
    @Nullable
    public final zzld b() {
        zzld zzld;
        synchronized (this.b) {
            if (this.a == null) {
                this.a = a();
            }
            zzld = this.a;
        }
        return zzld;
    }

    @Nullable
    public final zzaap a(Activity activity) {
        String str = "com.google.android.gms.ads.internal.overlay.useClientJar";
        Intent intent = activity.getIntent();
        boolean z = false;
        if (!intent.hasExtra(str)) {
            ka.c("useClientJar flag not found in activity intent extras.");
        } else {
            z = intent.getBooleanExtra(str, false);
        }
        return (zzaap) a((Context) activity, z, (a<T>) new ajg<T>(this, activity));
    }

    public final zzkn a(Context context, String str, zzxn zzxn) {
        return (zzkn) a(context, false, (a<T>) new ajd<T>(this, context, str, zzxn));
    }

    public final zzqa a(Context context, FrameLayout frameLayout, FrameLayout frameLayout2) {
        return (zzqa) a(context, false, (a<T>) new aje<T>(this, frameLayout, frameLayout2, context));
    }
}
