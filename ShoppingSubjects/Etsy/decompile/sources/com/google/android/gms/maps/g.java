package com.google.android.gms.maps;

import android.graphics.Point;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.VisibleRegion;

public final class g {
    private final IProjectionDelegate a;

    g(IProjectionDelegate iProjectionDelegate) {
        this.a = iProjectionDelegate;
    }

    public final Point a(LatLng latLng) {
        try {
            return (Point) ObjectWrapper.unwrap(this.a.toScreenLocation(latLng));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final LatLng a(Point point) {
        try {
            return this.a.fromScreenLocation(ObjectWrapper.wrap(point));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final VisibleRegion a() {
        try {
            return this.a.getVisibleRegion();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
