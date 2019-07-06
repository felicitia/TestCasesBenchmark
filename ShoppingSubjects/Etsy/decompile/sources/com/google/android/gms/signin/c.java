package com.google.android.gms.signin;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;

public final class c implements Optional {
    public static final c a = new a().a();
    private final boolean b;
    private final boolean c;
    private final String d;
    private final boolean e;
    private final String f;
    private final boolean g;
    private final Long h;
    private final Long i;

    public static final class a {
        private boolean a;
        private boolean b;
        private String c;
        private boolean d;
        private String e;
        private boolean f;
        private Long g;
        private Long h;

        public final c a() {
            c cVar = new c(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
            return cVar;
        }
    }

    private c(boolean z, boolean z2, String str, boolean z3, String str2, boolean z4, Long l, Long l2) {
        this.b = z;
        this.c = z2;
        this.d = str;
        this.e = z3;
        this.g = z4;
        this.f = str2;
        this.h = l;
        this.i = l2;
    }

    public final boolean a() {
        return this.b;
    }

    public final boolean b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }

    public final boolean d() {
        return this.e;
    }

    @Nullable
    public final String e() {
        return this.f;
    }

    public final boolean f() {
        return this.g;
    }

    @Nullable
    public final Long g() {
        return this.h;
    }

    @Nullable
    public final Long h() {
        return this.i;
    }
}
