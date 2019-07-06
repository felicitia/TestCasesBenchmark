package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.appindexing.FirebaseAppIndexingException;

final class zzm extends TaskApiCall<zzd, Void> {
    final /* synthetic */ zzk zzcw;

    zzm(zzk zzk) {
        this.zzcw = zzk;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzf zza = ((zzs) ((zzd) anyClient).getService()).zza(new zzn(this, taskCompletionSource), this.zzcw.zzcs);
        int i = zza == null ? 2 : zza.status;
        boolean z = false;
        boolean z2 = true;
        zzk zzk = null;
        if (i == 3) {
            String str = "Queue was full. API call will be retried.";
            if (zzu.isLoggable(4)) {
                Log.i("FirebaseAppIndex", str);
            }
            if (taskCompletionSource.trySetResult(null)) {
                synchronized (this.zzcw.zzcu.zzcq) {
                    if (this.zzcw.zzcu.zzcr == 0) {
                        zzk zzk2 = (zzk) this.zzcw.zzcu.zzcq.peek();
                        if (zzk2 == this.zzcw) {
                            z = true;
                        }
                        Preconditions.checkState(z);
                        zzk = zzk2;
                    } else {
                        this.zzcw.zzcu.zzcr = 2;
                    }
                }
            }
        } else {
            if (i != 1) {
                StringBuilder sb = new StringBuilder(41);
                sb.append("API call failed. Status code: ");
                sb.append(i);
                String sb2 = sb.toString();
                if (zzu.isLoggable(6)) {
                    Log.e("FirebaseAppIndex", sb2);
                }
                if (taskCompletionSource.trySetResult(null)) {
                    this.zzcw.zzct.setException(new FirebaseAppIndexingException("Indexing error."));
                }
            }
            synchronized (this.zzcw.zzcu.zzcq) {
                if (((zzk) this.zzcw.zzcu.zzcq.poll()) != this.zzcw) {
                    z2 = false;
                }
                Preconditions.checkState(z2);
                zzk = (zzk) this.zzcw.zzcu.zzcq.peek();
                this.zzcw.zzcu.zzcr = 0;
            }
        }
        if (zzk != null) {
            zzk.execute();
        }
    }
}
