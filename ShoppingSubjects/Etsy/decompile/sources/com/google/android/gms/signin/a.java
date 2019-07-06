package com.google.android.gms.signin;

import android.os.Bundle;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Scope;

public final class a {
    public static final ClientKey<com.google.android.gms.signin.internal.a> a = new ClientKey<>();
    public static final ClientKey<com.google.android.gms.signin.internal.a> b = new ClientKey<>();
    public static final AbstractClientBuilder<com.google.android.gms.signin.internal.a, c> c = new d();
    public static final Scope d = new Scope(Scopes.PROFILE);
    public static final Scope e = new Scope("email");
    public static final Api<c> f = new Api<>("SignIn.API", c, a);
    public static final Api<C0146a> g = new Api<>("SignIn.INTERNAL_API", h, b);
    private static final AbstractClientBuilder<com.google.android.gms.signin.internal.a, C0146a> h = new e();

    /* renamed from: com.google.android.gms.signin.a$a reason: collision with other inner class name */
    public static class C0146a implements HasOptions {
        private final Bundle a;

        public Bundle a() {
            return this.a;
        }
    }
}
