package com.google.android.gms.location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.location.c;
import com.google.android.gms.internal.location.n;
import com.google.android.gms.internal.location.r;
import com.google.android.gms.internal.location.x;

public class h {
    public static final Api<NoOptions> a = new Api<>("LocationServices.API", f, e);
    @Deprecated
    public static final a b = new x();
    @Deprecated
    public static final d c = new c();
    @Deprecated
    public static final j d = new r();
    private static final ClientKey<n> e = new ClientKey<>();
    private static final AbstractClientBuilder<n, NoOptions> f = new k();

    public static abstract class a<R extends Result> extends ApiMethodImpl<R, n> {
        public a(GoogleApiClient googleApiClient) {
            super(h.a, googleApiClient);
        }
    }

    public static n a(GoogleApiClient googleApiClient) {
        boolean z = false;
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        n nVar = (n) googleApiClient.getClient(e);
        if (nVar != null) {
            z = true;
        }
        Preconditions.checkState(z, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
        return nVar;
    }
}
