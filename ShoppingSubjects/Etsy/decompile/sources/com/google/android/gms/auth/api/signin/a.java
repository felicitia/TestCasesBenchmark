package com.google.android.gms.auth.api.signin;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public interface a {
    Intent a(GoogleApiClient googleApiClient);

    c a(Intent intent);

    PendingResult<Status> b(GoogleApiClient googleApiClient);

    PendingResult<Status> c(GoogleApiClient googleApiClient);
}
