package com.google.android.gms.internal.auth;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

public interface zzba extends IInterface {
    void zzd(Status status, Credential credential) throws RemoteException;

    void zzd(Status status, String str) throws RemoteException;

    void zze(Status status) throws RemoteException;
}
