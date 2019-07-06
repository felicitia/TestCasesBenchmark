package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.d;
import com.google.android.gms.location.zzal;
import java.util.List;

public final class c implements d {
    private final PendingResult<Status> a(GoogleApiClient googleApiClient, zzal zzal) {
        return googleApiClient.execute(new e(this, googleApiClient, zzal));
    }

    public final PendingResult<Status> a(GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        return a(googleApiClient, zzal.zza(pendingIntent));
    }

    public final PendingResult<Status> a(GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent) {
        return googleApiClient.execute(new d(this, googleApiClient, geofencingRequest, pendingIntent));
    }

    public final PendingResult<Status> a(GoogleApiClient googleApiClient, List<String> list) {
        return a(googleApiClient, zzal.zza(list));
    }
}
