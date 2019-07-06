package com.google.android.gms.internal.location;

import android.os.DeadObjectException;
import android.os.IInterface;

final class w implements q<zzao> {
    private final /* synthetic */ v a;

    w(v vVar) {
        this.a = vVar;
    }

    public final /* synthetic */ IInterface a() throws DeadObjectException {
        return (zzao) this.a.getService();
    }

    public final void b() {
        this.a.checkConnected();
    }
}
