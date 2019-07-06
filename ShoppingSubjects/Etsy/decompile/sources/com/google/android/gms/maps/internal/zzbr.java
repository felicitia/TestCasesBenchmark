package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.internal.maps.a;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;

public final class zzbr extends zza implements IProjectionDelegate {
    zzbr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IProjectionDelegate");
    }

    public final LatLng fromScreenLocation(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        a.a(obtainAndWriteInterfaceToken, (IInterface) iObjectWrapper);
        Parcel transactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken);
        LatLng latLng = (LatLng) a.a(transactAndReadException, LatLng.CREATOR);
        transactAndReadException.recycle();
        return latLng;
    }

    public final VisibleRegion getVisibleRegion() throws RemoteException {
        Parcel transactAndReadException = transactAndReadException(3, obtainAndWriteInterfaceToken());
        VisibleRegion visibleRegion = (VisibleRegion) a.a(transactAndReadException, VisibleRegion.CREATOR);
        transactAndReadException.recycle();
        return visibleRegion;
    }

    public final IObjectWrapper toScreenLocation(LatLng latLng) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        a.a(obtainAndWriteInterfaceToken, (Parcelable) latLng);
        Parcel transactAndReadException = transactAndReadException(2, obtainAndWriteInterfaceToken);
        IObjectWrapper asInterface = Stub.asInterface(transactAndReadException.readStrongBinder());
        transactAndReadException.recycle();
        return asInterface;
    }
}
