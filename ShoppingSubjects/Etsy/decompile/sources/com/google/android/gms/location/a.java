package com.google.android.gms.location;

import android.app.PendingIntent;
import android.location.Location;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

@Deprecated
public interface a {
    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    Location a(GoogleApiClient googleApiClient);

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    PendingResult<Status> a(GoogleApiClient googleApiClient, LocationRequest locationRequest, PendingIntent pendingIntent);

    @RequiresPermission(anyOf = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"})
    PendingResult<Status> a(GoogleApiClient googleApiClient, LocationRequest locationRequest, g gVar);

    PendingResult<Status> a(GoogleApiClient googleApiClient, g gVar);
}
