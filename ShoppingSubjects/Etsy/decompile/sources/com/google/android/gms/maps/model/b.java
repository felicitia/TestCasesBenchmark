package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zze;

public final class b {
    private static zze a;

    private static zze a() {
        return (zze) Preconditions.checkNotNull(a, "IBitmapDescriptorFactory is not initialized");
    }

    public static a a(int i) {
        try {
            return new a(a().zza(i));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static void a(zze zze) {
        if (a == null) {
            a = (zze) Preconditions.checkNotNull(zze);
        }
    }
}
