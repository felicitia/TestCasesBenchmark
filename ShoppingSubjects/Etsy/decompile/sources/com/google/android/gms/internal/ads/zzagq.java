package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.bg;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@bu
public final class zzagq extends zzaha {
    private final Context mContext;
    private final Object mLock;
    private final zzagr zzcld;
    private final zzang zzyf;

    public zzagq(Context context, bg bgVar, zzxn zzxn, zzang zzang) {
        zzagr zzagr = new zzagr(context, bgVar, zzjn.zzhx(), zzxn, zzang);
        this(context, zzang, zzagr);
    }

    @VisibleForTesting
    private zzagq(Context context, zzang zzang, zzagr zzagr) {
        this.mLock = new Object();
        this.mContext = context;
        this.zzyf = zzang;
        this.zzcld = zzagr;
    }

    public final void destroy() {
        zzf(null);
    }

    public final String getMediationAdapterClassName() {
        String mediationAdapterClassName;
        synchronized (this.mLock) {
            mediationAdapterClassName = this.zzcld.getMediationAdapterClassName();
        }
        return mediationAdapterClassName;
    }

    public final boolean isLoaded() {
        boolean isLoaded;
        synchronized (this.mLock) {
            isLoaded = this.zzcld.isLoaded();
        }
        return isLoaded;
    }

    public final void pause() {
        zzd(null);
    }

    public final void resume() {
        zze(null);
    }

    public final void setImmersiveMode(boolean z) {
        synchronized (this.mLock) {
            this.zzcld.setImmersiveMode(z);
        }
    }

    public final void setUserId(String str) {
        synchronized (this.mLock) {
            this.zzcld.setUserId(str);
        }
    }

    public final void show() {
        synchronized (this.mLock) {
            this.zzcld.zzoy();
        }
    }

    public final void zza(zzagx zzagx) {
        synchronized (this.mLock) {
            this.zzcld.zza(zzagx);
        }
    }

    public final void zza(zzahe zzahe) {
        synchronized (this.mLock) {
            this.zzcld.zza(zzahe);
        }
    }

    public final void zza(zzahk zzahk) {
        synchronized (this.mLock) {
            this.zzcld.zza(zzahk);
        }
    }

    public final void zza(zzkx zzkx) {
        if (((Boolean) ajh.f().a(akl.aF)).booleanValue()) {
            synchronized (this.mLock) {
                this.zzcld.zza(zzkx);
            }
        }
    }

    public final Bundle zzba() {
        Bundle zzba;
        if (!((Boolean) ajh.f().a(akl.aF)).booleanValue()) {
            return new Bundle();
        }
        synchronized (this.mLock) {
            zzba = this.zzcld.zzba();
        }
        return zzba;
    }

    public final void zzd(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzcld.pause();
        }
    }

    public final void zze(IObjectWrapper iObjectWrapper) {
        Context context;
        synchronized (this.mLock) {
            if (iObjectWrapper == null) {
                context = null;
            } else {
                try {
                    context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
                } catch (Exception e) {
                    gv.c("Unable to extract updated context.", e);
                }
            }
            if (context != null) {
                this.zzcld.onContextChanged(context);
            }
            this.zzcld.resume();
        }
    }

    public final void zzf(IObjectWrapper iObjectWrapper) {
        synchronized (this.mLock) {
            this.zzcld.destroy();
        }
    }
}
