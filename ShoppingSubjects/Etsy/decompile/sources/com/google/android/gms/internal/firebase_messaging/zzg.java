package com.google.android.gms.internal.firebase_messaging;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzg extends zza implements zze {
    zzg(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.iid.IMessengerCompat");
    }

    public final void send(Message message) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        a.a(obtainAndWriteInterfaceToken, message);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
