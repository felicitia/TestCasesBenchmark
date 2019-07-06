package com.google.android.gms.internal.auth;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzo;
import com.google.android.gms.auth.api.accounttransfer.zzw;
import com.google.android.gms.common.api.Status;

public interface zzaa extends IInterface {
    void onFailure(Status status) throws RemoteException;

    void zzd(DeviceMetaData deviceMetaData) throws RemoteException;

    void zzd(Status status, zzo zzo) throws RemoteException;

    void zzd(Status status, zzw zzw) throws RemoteException;

    void zzd(byte[] bArr) throws RemoteException;

    void zze(Status status) throws RemoteException;

    void zzi() throws RemoteException;
}
