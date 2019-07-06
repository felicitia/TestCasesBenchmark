package com.google.android.gms.maps;

import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzd;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class c {
    private final IGoogleMapDelegate a;

    public interface a {
        void a();

        void b();
    }

    public interface b {
        View a(com.google.android.gms.maps.model.c cVar);

        View b(com.google.android.gms.maps.model.c cVar);
    }

    @Deprecated
    /* renamed from: com.google.android.gms.maps.c$c reason: collision with other inner class name */
    public interface C0141c {
        void a(CameraPosition cameraPosition);
    }

    public interface d {
        void a(com.google.android.gms.maps.model.c cVar);
    }

    public interface e {
        void a(LatLng latLng);
    }

    public interface f {
        boolean a(com.google.android.gms.maps.model.c cVar);
    }

    private static final class g extends zzd {
        private final a a;

        g(a aVar) {
            this.a = aVar;
        }

        public final void onCancel() {
            this.a.b();
        }

        public final void onFinish() {
            this.a.a();
        }
    }

    public c(IGoogleMapDelegate iGoogleMapDelegate) {
        this.a = (IGoogleMapDelegate) Preconditions.checkNotNull(iGoogleMapDelegate);
    }

    public final CameraPosition a() {
        try {
            return this.a.getCameraPosition();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final com.google.android.gms.maps.model.c a(MarkerOptions markerOptions) {
        try {
            zzt addMarker = this.a.addMarker(markerOptions);
            if (addMarker != null) {
                return new com.google.android.gms.maps.model.c(addMarker);
            }
            return null;
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final void a(int i, int i2, int i3, int i4) {
        try {
            this.a.setPadding(i, i2, i3, i4);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final void a(a aVar) {
        try {
            this.a.moveCamera(aVar.a());
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final void a(a aVar, a aVar2) {
        try {
            this.a.animateCameraWithCallback(aVar.a(), aVar2 == null ? null : new g(aVar2));
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final void a(b bVar) {
        if (bVar == null) {
            try {
                this.a.setInfoWindowAdapter(null);
            } catch (RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        } else {
            this.a.setInfoWindowAdapter(new r(this, bVar));
        }
    }

    @Deprecated
    public final void a(@Nullable C0141c cVar) {
        if (cVar == null) {
            try {
                this.a.setOnCameraChangeListener(null);
            } catch (RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        } else {
            this.a.setOnCameraChangeListener(new s(this, cVar));
        }
    }

    public final void a(@Nullable d dVar) {
        if (dVar == null) {
            try {
                this.a.setOnInfoWindowClickListener(null);
            } catch (RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        } else {
            this.a.setOnInfoWindowClickListener(new q(this, dVar));
        }
    }

    public final void a(@Nullable e eVar) {
        if (eVar == null) {
            try {
                this.a.setOnMapClickListener(null);
            } catch (RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        } else {
            this.a.setOnMapClickListener(new t(this, eVar));
        }
    }

    public final void a(@Nullable f fVar) {
        if (fVar == null) {
            try {
                this.a.setOnMarkerClickListener(null);
            } catch (RemoteException e2) {
                throw new RuntimeRemoteException(e2);
            }
        } else {
            this.a.setOnMarkerClickListener(new p(this, fVar));
        }
    }

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    public final void a(boolean z) {
        try {
            this.a.setMyLocationEnabled(z);
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final void b() {
        try {
            this.a.clear();
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final void b(a aVar) {
        try {
            this.a.animateCamera(aVar.a());
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }

    public final g c() {
        try {
            return new g(this.a.getProjection());
        } catch (RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}
