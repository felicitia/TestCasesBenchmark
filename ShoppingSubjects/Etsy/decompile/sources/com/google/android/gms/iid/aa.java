package com.google.android.gms.iid;

import android.os.IBinder;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

final class aa {
    final Messenger a;
    final MessengerCompat b;

    aa(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.a = new Messenger(iBinder);
            this.b = null;
        } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
            this.b = new MessengerCompat(iBinder);
            this.a = null;
        } else {
            String str = "MessengerIpcClient";
            String str2 = "Invalid interface descriptor: ";
            String valueOf = String.valueOf(interfaceDescriptor);
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            throw new RemoteException();
        }
    }
}
