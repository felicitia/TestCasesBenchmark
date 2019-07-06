package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

final class d {
    private final Messenger a;
    private final zzi b;

    d(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.a = new Messenger(iBinder);
            this.b = null;
        } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
            this.b = new zzi(iBinder);
            this.a = null;
        } else {
            String str = "MessengerIpcClient";
            String str2 = "Invalid interface descriptor: ";
            String valueOf = String.valueOf(interfaceDescriptor);
            Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            throw new RemoteException();
        }
    }

    /* access modifiers changed from: 0000 */
    public final void a(Message message) throws RemoteException {
        if (this.a != null) {
            this.a.send(message);
        } else if (this.b != null) {
            this.b.send(message);
        } else {
            throw new IllegalStateException("Both messengers are null");
        }
    }
}
