package com.google.android.gms.auth.api.signin.internal;

import android.content.Intent;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.a;
import com.google.android.gms.auth.api.signin.c;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class d implements a {
    private static GoogleSignInOptions d(GoogleApiClient googleApiClient) {
        return ((e) googleApiClient.getClient(com.google.android.gms.auth.api.a.b)).a();
    }

    public final Intent a(GoogleApiClient googleApiClient) {
        return f.a(googleApiClient.getContext(), d(googleApiClient));
    }

    public final c a(Intent intent) {
        return f.a(intent);
    }

    public final PendingResult<Status> b(GoogleApiClient googleApiClient) {
        return f.a(googleApiClient, googleApiClient.getContext(), false);
    }

    public final PendingResult<Status> c(GoogleApiClient googleApiClient) {
        return f.b(googleApiClient, googleApiClient.getContext(), false);
    }
}
