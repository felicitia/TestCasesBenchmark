package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.tasks.g;

@KeepForSdk
public abstract class UnregisterListenerMethod<A extends AnyClient, L> {
    private final ListenerKey<L> zzlj;

    @KeepForSdk
    protected UnregisterListenerMethod(ListenerKey<L> listenerKey) {
        this.zzlj = listenerKey;
    }

    @KeepForSdk
    public ListenerKey<L> getListenerKey() {
        return this.zzlj;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract void unregisterListener(A a, g<Boolean> gVar) throws RemoteException;
}