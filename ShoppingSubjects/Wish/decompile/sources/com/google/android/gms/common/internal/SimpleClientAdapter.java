package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.api.Api.SimpleClient;

public class SimpleClientAdapter<T extends IInterface> extends GmsClient<T> {
    private final SimpleClient<T> zzva;

    /* access modifiers changed from: protected */
    public T createServiceInterface(IBinder iBinder) {
        return this.zzva.createServiceInterface(iBinder);
    }

    public SimpleClient<T> getClient() {
        return this.zzva;
    }

    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }

    /* access modifiers changed from: protected */
    public String getServiceDescriptor() {
        return this.zzva.getServiceDescriptor();
    }

    /* access modifiers changed from: protected */
    public String getStartServiceAction() {
        return this.zzva.getStartServiceAction();
    }

    /* access modifiers changed from: protected */
    public void onSetConnectState(int i, T t) {
        this.zzva.setState(i, t);
    }
}
