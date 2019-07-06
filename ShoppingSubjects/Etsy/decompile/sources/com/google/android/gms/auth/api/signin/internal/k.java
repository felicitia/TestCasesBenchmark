package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.a;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;

abstract class k<R extends Result> extends ApiMethodImpl<R, e> {
    public k(GoogleApiClient googleApiClient) {
        super(a.e, googleApiClient);
    }
}
