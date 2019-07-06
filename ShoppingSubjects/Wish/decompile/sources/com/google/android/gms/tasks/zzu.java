package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

final class zzu<TResult> extends Task<TResult> {
    private final Object mLock = new Object();
    private final zzr<TResult> zzage = new zzr<>();
    private boolean zzagf;
    private TResult zzagg;
    private Exception zzagh;
    private volatile boolean zzfi;

    zzu() {
    }

    private final void zzdq() {
        Preconditions.checkState(this.zzagf, "Task is not yet complete");
    }

    private final void zzdr() {
        Preconditions.checkState(!this.zzagf, "Task is already complete");
    }

    private final void zzds() {
        if (this.zzfi) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    private final void zzdt() {
        synchronized (this.mLock) {
            if (this.zzagf) {
                this.zzage.zza((Task<TResult>) this);
            }
        }
    }

    public final Task<TResult> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        this.zzage.zza((zzq<TResult>) new zzg<TResult>(executor, onCanceledListener));
        zzdt();
        return this;
    }

    public final Task<TResult> addOnCompleteListener(OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    public final Task<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.zzage.zza((zzq<TResult>) new zzi<TResult>(executor, onCompleteListener));
        zzdt();
        return this;
    }

    public final Task<TResult> addOnFailureListener(OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    public final Task<TResult> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        this.zzage.zza((zzq<TResult>) new zzk<TResult>(executor, onFailureListener));
        zzdt();
        return this;
    }

    public final Task<TResult> addOnSuccessListener(Executor executor, OnSuccessListener<? super TResult> onSuccessListener) {
        this.zzage.zza((zzq<TResult>) new zzm<TResult>(executor, onSuccessListener));
        zzdt();
        return this;
    }

    public final <TContinuationResult> Task<TContinuationResult> continueWith(Executor executor, Continuation<TResult, TContinuationResult> continuation) {
        zzu zzu = new zzu();
        this.zzage.zza((zzq<TResult>) new zzc<TResult>(executor, continuation, zzu));
        zzdt();
        return zzu;
    }

    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor executor, Continuation<TResult, Task<TContinuationResult>> continuation) {
        zzu zzu = new zzu();
        this.zzage.zza((zzq<TResult>) new zze<TResult>(executor, continuation, zzu));
        zzdt();
        return zzu;
    }

    public final Exception getException() {
        Exception exc;
        synchronized (this.mLock) {
            exc = this.zzagh;
        }
        return exc;
    }

    public final TResult getResult() {
        TResult tresult;
        synchronized (this.mLock) {
            zzdq();
            zzds();
            if (this.zzagh != null) {
                throw new RuntimeExecutionException(this.zzagh);
            }
            tresult = this.zzagg;
        }
        return tresult;
    }

    public final <X extends Throwable> TResult getResult(Class<X> cls) throws Throwable {
        TResult tresult;
        synchronized (this.mLock) {
            zzdq();
            zzds();
            if (cls.isInstance(this.zzagh)) {
                throw ((Throwable) cls.cast(this.zzagh));
            } else if (this.zzagh != null) {
                throw new RuntimeExecutionException(this.zzagh);
            } else {
                tresult = this.zzagg;
            }
        }
        return tresult;
    }

    public final boolean isCanceled() {
        return this.zzfi;
    }

    public final boolean isComplete() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzagf;
        }
        return z;
    }

    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzagf && !this.zzfi && this.zzagh == null;
        }
        return z;
    }

    public final void setException(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.mLock) {
            zzdr();
            this.zzagf = true;
            this.zzagh = exc;
        }
        this.zzage.zza((Task<TResult>) this);
    }

    public final void setResult(TResult tresult) {
        synchronized (this.mLock) {
            zzdr();
            this.zzagf = true;
            this.zzagg = tresult;
        }
        this.zzage.zza((Task<TResult>) this);
    }

    public final boolean trySetException(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.mLock) {
            if (this.zzagf) {
                return false;
            }
            this.zzagf = true;
            this.zzagh = exc;
            this.zzage.zza((Task<TResult>) this);
            return true;
        }
    }

    public final boolean trySetResult(TResult tresult) {
        synchronized (this.mLock) {
            if (this.zzagf) {
                return false;
            }
            this.zzagf = true;
            this.zzagg = tresult;
            this.zzage.zza((Task<TResult>) this);
            return true;
        }
    }

    public final boolean zzdp() {
        synchronized (this.mLock) {
            if (this.zzagf) {
                return false;
            }
            this.zzagf = true;
            this.zzfi = true;
            this.zzage.zza((Task<TResult>) this);
            return true;
        }
    }
}
