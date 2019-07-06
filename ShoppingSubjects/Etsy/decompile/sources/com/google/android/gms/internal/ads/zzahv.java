package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@bu
public final class zzahv extends zzaid {
    private volatile eu zzclm;
    private volatile es zzcma;
    private volatile et zzcmb;
    private volatile ez zzcmc;

    public zzahv(et etVar) {
        this.zzcmb = etVar;
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaig zzaig) {
        if (this.zzcmb != null) {
            this.zzcmb.zzc(zzaig);
        }
    }

    public final void zza(es esVar) {
        this.zzcma = esVar;
    }

    public final void zza(eu euVar) {
        this.zzclm = euVar;
    }

    public final void zza(ez ezVar) {
        this.zzcmc = ezVar;
    }

    public final void zzc(Bundle bundle) {
        if (this.zzcmc != null) {
            this.zzcmc.a(bundle);
        }
    }

    public final void zzc(IObjectWrapper iObjectWrapper, int i) {
        if (this.zzcma != null) {
            this.zzcma.a(i);
        }
    }

    public final void zzd(IObjectWrapper iObjectWrapper, int i) {
        if (this.zzclm != null) {
            this.zzclm.a(ObjectWrapper.unwrap(iObjectWrapper).getClass().getName(), i);
        }
    }

    public final void zzq(IObjectWrapper iObjectWrapper) {
        if (this.zzcma != null) {
            this.zzcma.g();
        }
    }

    public final void zzr(IObjectWrapper iObjectWrapper) {
        if (this.zzclm != null) {
            this.zzclm.a(ObjectWrapper.unwrap(iObjectWrapper).getClass().getName());
        }
    }

    public final void zzs(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoAdOpened();
        }
    }

    public final void zzt(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoStarted();
        }
    }

    public final void zzu(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoAdClosed();
        }
    }

    public final void zzv(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.zzdm();
        }
    }

    public final void zzw(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoAdLeftApplication();
        }
    }

    public final void zzx(IObjectWrapper iObjectWrapper) {
        if (this.zzcmb != null) {
            this.zzcmb.onRewardedVideoCompleted();
        }
    }
}
