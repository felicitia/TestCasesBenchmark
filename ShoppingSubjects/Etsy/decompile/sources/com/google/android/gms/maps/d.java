package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.e;
import com.google.android.gms.maps.internal.zze;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.b;

public final class d {
    private static boolean a = false;

    public static synchronized int a(Context context) {
        synchronized (d.class) {
            Preconditions.checkNotNull(context, "Context is null");
            if (a) {
                return 0;
            }
            try {
                zze a2 = e.a(context);
                try {
                    b.a(a2.zzd());
                    b.a(a2.zze());
                    a = true;
                    return 0;
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                }
            } catch (GooglePlayServicesNotAvailableException e2) {
                return e2.errorCode;
            }
        }
    }
}
