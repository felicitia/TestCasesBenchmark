package com.google.android.gms.signin;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.a.C0146a;
import com.google.android.gms.signin.internal.a;

final class e extends AbstractClientBuilder<a, C0146a> {
    e() {
    }

    public final /* synthetic */ Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        a aVar = new a(context, looper, false, clientSettings, ((C0146a) obj).a(), connectionCallbacks, onConnectionFailedListener);
        return aVar;
    }
}
