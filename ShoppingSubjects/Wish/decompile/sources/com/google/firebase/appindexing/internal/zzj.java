package com.google.firebase.appindexing.internal;

import android.os.Handler;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

final class zzj implements OnCompleteListener<Void>, Executor {
    private final Handler handler;
    /* access modifiers changed from: private */
    public final GoogleApi<?> zzcn;
    /* access modifiers changed from: private */
    public final Queue<zzk> zzcq = new ArrayDeque();
    /* access modifiers changed from: private */
    public int zzcr = 0;

    public zzj(GoogleApi<?> googleApi) {
        this.zzcn = googleApi;
        this.handler = new Handler(googleApi.getLooper());
    }

    public final void execute(Runnable runnable) {
        this.handler.post(runnable);
    }

    public final void onComplete(Task<Void> task) {
        zzk zzk;
        synchronized (this.zzcq) {
            if (this.zzcr == 2) {
                zzk = (zzk) this.zzcq.peek();
                Preconditions.checkState(zzk != null);
            } else {
                zzk = null;
            }
            this.zzcr = 0;
        }
        if (zzk != null) {
            zzk.execute();
        }
    }

    public final Task<Void> zzb(zzx zzx) {
        boolean isEmpty;
        zzk zzk = new zzk(this, zzx);
        Task<Void> task = zzk.getTask();
        task.addOnCompleteListener(this, this);
        synchronized (this.zzcq) {
            isEmpty = this.zzcq.isEmpty();
            this.zzcq.add(zzk);
        }
        if (isEmpty) {
            zzk.execute();
        }
        return task;
    }
}
