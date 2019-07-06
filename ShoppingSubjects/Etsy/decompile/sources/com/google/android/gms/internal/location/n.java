package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.g;
import com.google.android.gms.location.zzal;

public final class n extends v {
    private final g b;

    public n(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, ClientSettings clientSettings) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, str, clientSettings);
        this.b = new g(context, this.a);
    }

    public final Location a() throws RemoteException {
        return this.b.a();
    }

    public final void a(ListenerKey<g> listenerKey, zzaj zzaj) throws RemoteException {
        this.b.a(listenerKey, zzaj);
    }

    public final void a(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, ResultHolder<Status> resultHolder) throws RemoteException {
        checkConnected();
        Preconditions.checkNotNull(geofencingRequest, "geofencingRequest can't be null.");
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzao) getService()).zza(geofencingRequest, pendingIntent, (zzam) new o(resultHolder));
    }

    public final void a(LocationRequest locationRequest, PendingIntent pendingIntent, zzaj zzaj) throws RemoteException {
        this.b.a(locationRequest, pendingIntent, zzaj);
    }

    public final void a(LocationRequest locationRequest, ListenerHolder<g> listenerHolder, zzaj zzaj) throws RemoteException {
        synchronized (this.b) {
            this.b.a(locationRequest, listenerHolder, zzaj);
        }
    }

    public final void a(zzal zzal, ResultHolder<Status> resultHolder) throws RemoteException {
        checkConnected();
        Preconditions.checkNotNull(zzal, "removeGeofencingRequest can't be null.");
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzao) getService()).zza(zzal, (zzam) new p(resultHolder));
    }

    public final void disconnect() {
        synchronized (this.b) {
            if (isConnected()) {
                try {
                    this.b.b();
                    this.b.c();
                } catch (Exception e) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
                }
            }
            super.disconnect();
        }
    }
}
