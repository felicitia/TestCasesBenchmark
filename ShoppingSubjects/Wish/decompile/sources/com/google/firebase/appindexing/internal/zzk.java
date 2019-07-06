package com.google.firebase.appindexing.internal;

import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzk {
    /* access modifiers changed from: private */
    public final zzx zzcs;
    /* access modifiers changed from: private */
    public final TaskCompletionSource<Void> zzct = new TaskCompletionSource<>();
    final /* synthetic */ zzj zzcu;

    public zzk(zzj zzj, zzx zzx) {
        this.zzcu = zzj;
        this.zzcs = zzx;
    }

    public final void execute() {
        synchronized (this.zzcu.zzcq) {
            Preconditions.checkState(this.zzcu.zzcr == 0);
            this.zzcu.zzcr = 1;
        }
        this.zzcu.zzcn.doWrite((TaskApiCall<A, TResult>) new zzm<A,TResult>(this)).addOnFailureListener(this.zzcu, new zzl(this));
    }

    public final Task<Void> getTask() {
        return this.zzct.getTask();
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(Exception exc) {
        zzk zzk;
        synchronized (this.zzcu.zzcq) {
            if (this.zzcu.zzcq.peek() == this) {
                this.zzcu.zzcq.remove();
                this.zzcu.zzcr = 0;
                zzk = (zzk) this.zzcu.zzcq.peek();
            } else {
                zzk = null;
            }
        }
        this.zzct.trySetException(exc);
        if (zzk != null) {
            zzk.execute();
        }
    }
}
