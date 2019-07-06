package com.google.android.gms.maps.model;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzt;

public final class c {
    private final zzt a;

    public c(zzt zzt) {
        this.a = (zzt) Preconditions.checkNotNull(zzt);
    }

    public final String a() {
        try {
            return this.a.getId();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void a(@Nullable a aVar) {
        if (aVar == null) {
            try {
                this.a.zzg(null);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        } else {
            this.a.zzg(aVar.a());
        }
    }

    public final LatLng b() {
        try {
            return this.a.getPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final String c() {
        try {
            return this.a.getSnippet();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void d() {
        try {
            this.a.showInfoWindow();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void e() {
        try {
            this.a.hideInfoWindow();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        try {
            return this.a.zzj(((c) obj).a);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean f() {
        try {
            return this.a.isInfoWindowShown();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int hashCode() {
        try {
            return this.a.zzi();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
