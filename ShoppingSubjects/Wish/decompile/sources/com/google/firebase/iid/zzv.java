package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class zzv {
    private static zzv zzbm;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService zzbn;
    private zzx zzbo = new zzx(this);
    private int zzbp = 1;
    /* access modifiers changed from: private */
    public final Context zzz;

    private zzv(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzbn = scheduledExecutorService;
        this.zzz = context.getApplicationContext();
    }

    private final synchronized <T> Task<T> zza(zzae<T> zzae) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(zzae);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 9);
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.zzbo.zzb(zzae)) {
            this.zzbo = new zzx(this);
            this.zzbo.zzb(zzae);
        }
        return zzae.zzbz.getTask();
    }

    public static synchronized zzv zzc(Context context) {
        zzv zzv;
        synchronized (zzv.class) {
            if (zzbm == null) {
                zzbm = new zzv(context, Executors.newSingleThreadScheduledExecutor());
            }
            zzv = zzbm;
        }
        return zzv;
    }

    private final synchronized int zzr() {
        int i;
        i = this.zzbp;
        this.zzbp = i + 1;
        return i;
    }

    public final Task<Void> zza(int i, Bundle bundle) {
        return zza((zzae<T>) new zzad<T>(zzr(), 2, bundle));
    }

    public final Task<Bundle> zzb(int i, Bundle bundle) {
        return zza((zzae<T>) new zzag<T>(zzr(), 1, bundle));
    }
}
