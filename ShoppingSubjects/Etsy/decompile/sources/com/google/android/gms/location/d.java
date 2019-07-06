package com.google.android.gms.location;

import android.app.PendingIntent;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.List;

@Deprecated
public interface d {
    PendingResult<Status> a(GoogleApiClient googleApiClient, PendingIntent pendingIntent);

    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    PendingResult<Status> a(GoogleApiClient googleApiClient, GeofencingRequest geofencingRequest, PendingIntent pendingIntent);

    PendingResult<Status> a(GoogleApiClient googleApiClient, List<String> list);
}
