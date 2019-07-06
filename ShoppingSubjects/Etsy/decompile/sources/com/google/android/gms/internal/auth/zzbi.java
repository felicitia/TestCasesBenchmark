package com.google.android.gms.internal.auth;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

public interface zzbi extends IInterface {
    void zzd(ProxyResponse proxyResponse) throws RemoteException;

    void zzf(String str) throws RemoteException;
}
