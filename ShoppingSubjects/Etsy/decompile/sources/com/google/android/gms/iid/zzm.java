package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.gcm.a;
import com.google.android.gms.internal.gcm.zzd;

public final class zzm extends zzd implements zzl {
    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.iid.IMessengerCompat");
    }

    public final void send(Message message) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        a.a(obtainAndWriteInterfaceToken, message);
        transactOneway(1, obtainAndWriteInterfaceToken);
    }
}
