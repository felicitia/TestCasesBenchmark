package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.a;
import com.google.android.gms.location.g;
import com.google.android.gms.location.h;

@VisibleForTesting
public final class x implements a {
    public final Location a(GoogleApiClient googleApiClient) {
        try {
            return h.a(googleApiClient).a();
        } catch (Exception unused) {
            return null;
        }
    }

    public final PendingResult<Status> a(GoogleApiClient googleApiClient, LocationRequest locationRequest, PendingIntent pendingIntent) {
        return googleApiClient.execute(new z(this, googleApiClient, locationRequest, pendingIntent));
    }

    public final PendingResult<Status> a(GoogleApiClient googleApiClient, LocationRequest locationRequest, g gVar) {
        Preconditions.checkNotNull(Looper.myLooper(), "Calling thread must be a prepared Looper thread.");
        return googleApiClient.execute(new y(this, googleApiClient, locationRequest, gVar));
    }

    public final PendingResult<Status> a(GoogleApiClient googleApiClient, g gVar) {
        return googleApiClient.execute(new aa(this, googleApiClient, gVar));
    }
}
