package com.google.android.gms.internal.ads;

import android.os.RemoteException;

@bu
public class arx {
    public static zzzj a(String str) throws RemoteException {
        try {
            return new zzzp((pq) Class.forName(str, false, arx.class.getClassLoader()).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (Throwable unused) {
            throw new RemoteException();
        }
    }
}
