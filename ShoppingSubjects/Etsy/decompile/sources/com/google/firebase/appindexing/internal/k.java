package com.google.firebase.appindexing.internal;

import android.content.Context;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.f;
import com.google.android.gms.tasks.i;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.a;
import com.google.firebase.appindexing.c;

public final class k extends c {
    private m a;

    public k(Context context) {
        this.a = new m(context);
    }

    private final f<Void> a(int i, a aVar) {
        zza[] zzaArr = new zza[1];
        if (aVar != null) {
            if (!(aVar instanceof zza)) {
                return i.a((Exception) new FirebaseAppIndexingInvalidArgumentException("Custom Action objects are not allowed. Please use the 'Actions' or 'ActionBuilder' class for creating Action objects."));
            }
            zzaArr[0] = (zza) aVar;
            zzaArr[0].zzj().zzd(i);
        }
        return this.a.doWrite((TaskApiCall<A, TResult>) new l<A,TResult>(this, zzaArr));
    }

    public final f<Void> a(a aVar) {
        return a(2, aVar);
    }
}
