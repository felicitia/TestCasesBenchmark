package com.google.android.gms.auth.api;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.e;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.internal.auth.b;
import com.google.android.gms.internal.auth.d;

public final class a {
    public static final ClientKey<b> a = new ClientKey<>();
    public static final ClientKey<e> b = new ClientKey<>();
    @KeepForSdk
    public static final Api<f> c = d.a;
    public static final Api<C0133a> d = new Api<>("Auth.CREDENTIALS_API", i, a);
    public static final Api<GoogleSignInOptions> e = new Api<>("Auth.GOOGLE_SIGN_IN_API", j, b);
    @KeepForSdk
    public static final com.google.android.gms.auth.api.proxy.a f = new d();
    public static final com.google.android.gms.auth.api.credentials.a g = new com.google.android.gms.internal.auth.a();
    public static final com.google.android.gms.auth.api.signin.a h = new com.google.android.gms.auth.api.signin.internal.d();
    private static final AbstractClientBuilder<b, C0133a> i = new b();
    private static final AbstractClientBuilder<e, GoogleSignInOptions> j = new c();

    @Deprecated
    /* renamed from: com.google.android.gms.auth.api.a$a reason: collision with other inner class name */
    public static class C0133a implements Optional {
        private static final C0133a a = new C0134a().a();
        private final String b = null;
        private final PasswordSpecification c;
        private final boolean d;

        @Deprecated
        /* renamed from: com.google.android.gms.auth.api.a$a$a reason: collision with other inner class name */
        public static class C0134a {
            @NonNull
            protected PasswordSpecification a = PasswordSpecification.zzdg;
            protected Boolean b = Boolean.valueOf(false);

            public C0133a a() {
                return new C0133a(this);
            }
        }

        public C0133a(C0134a aVar) {
            this.c = aVar.a;
            this.d = aVar.b.booleanValue();
        }

        public final Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putString("consumer_package", null);
            bundle.putParcelable("password_specification", this.c);
            bundle.putBoolean("force_save_dialog", this.d);
            return bundle;
        }
    }
}
