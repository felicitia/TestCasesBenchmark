package com.google.firebase.appindexing.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.g;
import com.google.firebase.appindexing.FirebaseAppIndexingException;

@VisibleForTesting
final class i extends TaskApiCall<b, Void> {
    final /* synthetic */ g a;

    i(g gVar) {
        this.a = gVar;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void doExecute(AnyClient anyClient, g gVar) throws RemoteException {
        zzf zza = ((zzs) ((b) anyClient).getService()).zza(new j(this, gVar), this.a.b);
        int i = zza == null ? 2 : zza.status;
        boolean z = false;
        boolean z2 = true;
        g gVar2 = null;
        if (i == 3) {
            String str = "Queue was full. API call will be retried.";
            if (o.a(4)) {
                Log.i("FirebaseAppIndex", str);
            }
            if (gVar.b(null)) {
                synchronized (this.a.a.c) {
                    if (this.a.a.d == 0) {
                        g gVar3 = (g) this.a.a.c.peek();
                        if (gVar3 == this.a) {
                            z = true;
                        }
                        Preconditions.checkState(z);
                        gVar2 = gVar3;
                    } else {
                        this.a.a.d = 2;
                    }
                }
            }
        } else {
            if (i != 1) {
                StringBuilder sb = new StringBuilder(41);
                sb.append("API call failed. Status code: ");
                sb.append(i);
                String sb2 = sb.toString();
                if (o.a(6)) {
                    Log.e("FirebaseAppIndex", sb2);
                }
                if (gVar.b(null)) {
                    this.a.c.a((Exception) new FirebaseAppIndexingException("Indexing error."));
                }
            }
            synchronized (this.a.a.c) {
                if (((g) this.a.a.c.poll()) != this.a) {
                    z2 = false;
                }
                Preconditions.checkState(z2);
                gVar2 = (g) this.a.a.c.peek();
                this.a.a.d = 0;
            }
        }
        if (gVar2 != null) {
            gVar2.b();
        }
    }
}
