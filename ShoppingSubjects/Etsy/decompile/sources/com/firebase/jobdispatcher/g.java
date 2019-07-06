package com.firebase.jobdispatcher;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: GooglePlayJobCallback */
final class g implements l {
    private final IBinder a;

    public g(IBinder iBinder) {
        this.a = iBinder;
    }

    public void a(int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.gcm.INetworkTaskCallback");
            obtain.writeInt(i);
            this.a.transact(2, obtain, obtain2, 0);
            obtain2.readException();
            obtain.recycle();
            obtain2.recycle();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            obtain.recycle();
            obtain2.recycle();
            throw th;
        }
    }
}
