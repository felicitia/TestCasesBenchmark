package com.google.android.gms.auth.api;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.internal.auth.c;

public final class d {
    public static final Api<f> a = new Api<>("Auth.PROXY_API", c, b);
    private static final ClientKey<c> b = new ClientKey<>();
    private static final AbstractClientBuilder<c, f> c = new e();
}
