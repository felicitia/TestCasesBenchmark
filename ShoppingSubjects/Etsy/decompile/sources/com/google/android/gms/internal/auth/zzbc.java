package com.google.android.gms.internal.auth;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

public interface zzbc extends IInterface {
    void zzd(zzba zzba) throws RemoteException;

    void zzd(zzba zzba, CredentialRequest credentialRequest) throws RemoteException;

    void zzd(zzba zzba, zzay zzay) throws RemoteException;

    void zzd(zzba zzba, zzbe zzbe) throws RemoteException;
}
