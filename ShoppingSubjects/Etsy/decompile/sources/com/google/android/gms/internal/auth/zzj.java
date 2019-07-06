package com.google.android.gms.internal.auth;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsRequest;
import com.google.android.gms.auth.AccountChangeEventsResponse;

public final class zzj extends zzd implements zzh {
    zzj(IBinder iBinder) {
        super(iBinder, "com.google.android.auth.IAuthManagerService");
    }

    public final Bundle zzd(Account account) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (Parcelable) account);
        Parcel transactAndReadException = transactAndReadException(7, obtainAndWriteInterfaceToken);
        Bundle bundle = (Bundle) e.a(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }

    public final Bundle zzd(Account account, String str, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (Parcelable) account);
        obtainAndWriteInterfaceToken.writeString(str);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        Parcel transactAndReadException = transactAndReadException(5, obtainAndWriteInterfaceToken);
        Bundle bundle2 = (Bundle) e.a(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle2;
    }

    public final Bundle zzd(String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        Parcel transactAndReadException = transactAndReadException(8, obtainAndWriteInterfaceToken);
        Bundle bundle = (Bundle) e.a(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle;
    }

    public final Bundle zzd(String str, Bundle bundle) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        obtainAndWriteInterfaceToken.writeString(str);
        e.a(obtainAndWriteInterfaceToken, (Parcelable) bundle);
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
        Bundle bundle2 = (Bundle) e.a(transactAndReadException, Bundle.CREATOR);
        transactAndReadException.recycle();
        return bundle2;
    }

    public final AccountChangeEventsResponse zzd(AccountChangeEventsRequest accountChangeEventsRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        e.a(obtainAndWriteInterfaceToken, (Parcelable) accountChangeEventsRequest);
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken);
        AccountChangeEventsResponse accountChangeEventsResponse = (AccountChangeEventsResponse) e.a(transactAndReadException, AccountChangeEventsResponse.CREATOR);
        transactAndReadException.recycle();
        return accountChangeEventsResponse;
    }
}
