package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class b {
    private static ICameraUpdateFactoryDelegate a;

    public static a a(float f) {
        try {
            return new a(a().zoomBy(f));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static a a(LatLng latLng) {
        try {
            return new a(a().newLatLng(latLng));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static a a(LatLng latLng, float f) {
        try {
            return new a(a().newLatLngZoom(latLng, f));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public static a a(LatLngBounds latLngBounds, int i) {
        try {
            return new a(a().newLatLngBounds(latLngBounds, i));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static ICameraUpdateFactoryDelegate a() {
        return (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(a, "CameraUpdateFactory is not initialized");
    }

    public static void a(ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate) {
        a = (ICameraUpdateFactoryDelegate) Preconditions.checkNotNull(iCameraUpdateFactoryDelegate);
    }
}
