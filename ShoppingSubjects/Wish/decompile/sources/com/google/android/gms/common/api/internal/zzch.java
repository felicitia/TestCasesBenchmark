package com.google.android.gms.common.api.internal;

import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

public final class zzch<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    /* access modifiers changed from: private */
    public final Object zzfa;
    /* access modifiers changed from: private */
    public final WeakReference<GoogleApiClient> zzfc;
    /* access modifiers changed from: private */
    public ResultTransform<? super R, ? extends Result> zzmd;
    /* access modifiers changed from: private */
    public zzch<? extends Result> zzme;
    private volatile ResultCallbacks<? super R> zzmf;
    private PendingResult<R> zzmg;
    private Status zzmh;
    /* access modifiers changed from: private */
    public final zzcj zzmi;
    private boolean zzmj;

    /* access modifiers changed from: private */
    public static void zzb(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (RuntimeException e) {
                String valueOf = String.valueOf(result);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
                sb.append("Unable to release ");
                sb.append(valueOf);
                Log.w("TransformedResultImpl", sb.toString(), e);
            }
        }
    }

    private final void zzcb() {
        if (this.zzmd != null || this.zzmf != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzfc.get();
            if (!(this.zzmj || this.zzmd == null || googleApiClient == null)) {
                googleApiClient.zza(this);
                this.zzmj = true;
            }
            if (this.zzmh != null) {
                zze(this.zzmh);
                return;
            }
            if (this.zzmg != null) {
                this.zzmg.setResultCallback(this);
            }
        }
    }

    private final boolean zzcd() {
        return (this.zzmf == null || ((GoogleApiClient) this.zzfc.get()) == null) ? false : true;
    }

    /* access modifiers changed from: private */
    public final void zzd(Status status) {
        synchronized (this.zzfa) {
            this.zzmh = status;
            zze(this.zzmh);
        }
    }

    private final void zze(Status status) {
        synchronized (this.zzfa) {
            if (this.zzmd != null) {
                Status onFailure = this.zzmd.onFailure(status);
                Preconditions.checkNotNull(onFailure, "onFailure must not return null");
                this.zzme.zzd(onFailure);
            } else if (zzcd()) {
                this.zzmf.onFailure(status);
            }
        }
    }

    public final void onResult(R r) {
        synchronized (this.zzfa) {
            if (!r.getStatus().isSuccess()) {
                zzd(r.getStatus());
                zzb(r);
            } else if (this.zzmd != null) {
                zzbw.zzbe().submit(new zzci(this, r));
            } else if (zzcd()) {
                this.zzmf.onSuccess(r);
            }
        }
    }

    public final void zza(PendingResult<?> pendingResult) {
        synchronized (this.zzfa) {
            this.zzmg = pendingResult;
            zzcb();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzcc() {
        this.zzmf = null;
    }
}
