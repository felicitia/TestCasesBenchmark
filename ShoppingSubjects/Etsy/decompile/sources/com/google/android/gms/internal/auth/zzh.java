package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public interface zzh extends IInterface {
    Bundle zzd(Account account) throws RemoteException;

    Bundle zzd(Account account, String str, Bundle bundle) throws RemoteException;

    Bundle zzd(String str) throws RemoteException;

    Bundle zzd(String str, Bundle bundle) throws RemoteException;

    AccountChangeEventsResponse zzd(AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException;
}
