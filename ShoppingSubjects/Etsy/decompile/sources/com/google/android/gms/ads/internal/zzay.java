package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.aqy;
import com.google.android.gms.internal.ads.aqz;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.fa;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hx;
import com.google.android.gms.internal.ads.zzagr;
import com.google.android.gms.internal.ads.zzaic;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzlk;
import com.google.android.gms.internal.ads.zzxq;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@bu
public final class zzay extends zzlk {
    private static final Object sLock = new Object();
    @Nullable
    private static zzay zzzu;
    private final Context mContext;
    private final Object mLock = new Object();
    private boolean zzzv;
    private zzang zzzw;

    @VisibleForTesting
    private zzay(Context context, zzang zzang) {
        this.mContext = context;
        this.zzzw = zzang;
        this.zzzv = false;
    }

    public static zzay zza(Context context, zzang zzang) {
        zzay zzay;
        synchronized (sLock) {
            if (zzzu == null) {
                zzzu = new zzay(context.getApplicationContext(), zzang);
            }
            zzay = zzzu;
        }
        return zzay;
    }

    public final void setAppMuted(boolean z) {
        ao.D().a(z);
    }

    public final void setAppVolume(float f) {
        ao.D().a(f);
    }

    public final void zza() {
        synchronized (sLock) {
            if (this.zzzv) {
                gv.e("Mobile ads is initialized already.");
                return;
            }
            this.zzzv = true;
            akl.a(this.mContext);
            ao.i().a(this.mContext, this.zzzw);
            ao.k().a(this.mContext);
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Runnable runnable) {
        Context context = this.mContext;
        Preconditions.checkMainThread("Adapters must be initialized on the main thread.");
        Map e = ao.i().l().h().e();
        if (e != null && !e.isEmpty()) {
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    gv.c("Could not initialize rewarded ads.", th);
                    return;
                }
            }
            zzagr zzox = zzagr.zzox();
            if (zzox != null) {
                Collection<aqz> values = e.values();
                HashMap hashMap = new HashMap();
                IObjectWrapper wrap = ObjectWrapper.wrap(context);
                for (aqz aqz : values) {
                    for (aqy aqy : aqz.a) {
                        String str = aqy.k;
                        for (String str2 : aqy.c) {
                            if (!hashMap.containsKey(str2)) {
                                hashMap.put(str2, new ArrayList());
                            }
                            if (str != null) {
                                ((Collection) hashMap.get(str2)).add(str);
                            }
                        }
                    }
                }
                for (Entry entry : hashMap.entrySet()) {
                    String str3 = (String) entry.getKey();
                    try {
                        fa zzca = zzox.zzca(str3);
                        if (zzca != null) {
                            zzxq a = zzca.a();
                            if (!a.isInitialized()) {
                                if (a.zzms()) {
                                    a.zza(wrap, (zzaic) zzca.b(), (List) entry.getValue());
                                    String str4 = "Initialized rewarded video mediation adapter ";
                                    String valueOf = String.valueOf(str3);
                                    gv.b(valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        StringBuilder sb = new StringBuilder(56 + String.valueOf(str3).length());
                        sb.append("Failed to initialize rewarded video mediation adapter \"");
                        sb.append(str3);
                        sb.append("\"");
                        gv.c(sb.toString(), th2);
                    }
                }
            }
        }
    }

    public final void zza(String str, IObjectWrapper iObjectWrapper) {
        if (!TextUtils.isEmpty(str)) {
            akl.a(this.mContext);
            boolean booleanValue = ((Boolean) ajh.f().a(akl.cs)).booleanValue() | ((Boolean) ajh.f().a(akl.aD)).booleanValue();
            t tVar = null;
            if (((Boolean) ajh.f().a(akl.aD)).booleanValue()) {
                booleanValue = true;
                tVar = new t(this, (Runnable) ObjectWrapper.unwrap(iObjectWrapper));
            }
            if (booleanValue) {
                ao.m().a(this.mContext, this.zzzw, str, tVar);
            }
        }
    }

    public final void zzb(IObjectWrapper iObjectWrapper, String str) {
        if (iObjectWrapper == null) {
            gv.c("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        if (context == null) {
            gv.c("Context is null. Failed to open debug menu.");
            return;
        }
        hx hxVar = new hx(context);
        hxVar.a(str);
        hxVar.b(this.zzzw.zzcw);
        hxVar.a();
    }

    public final float zzdo() {
        return ao.D().a();
    }

    public final boolean zzdp() {
        return ao.D().b();
    }

    public final void zzt(String str) {
        akl.a(this.mContext);
        if (!TextUtils.isEmpty(str)) {
            if (((Boolean) ajh.f().a(akl.cs)).booleanValue()) {
                ao.m().a(this.mContext, this.zzzw, str, null);
            }
        }
    }
}
