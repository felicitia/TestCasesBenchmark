package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.util.VisibleForTesting;

public class a {
    @VisibleForTesting
    private static int a = 31;
    private int b = 1;

    public int a() {
        return this.b;
    }

    public a a(Object obj) {
        this.b = (a * this.b) + (obj == null ? 0 : obj.hashCode());
        return this;
    }

    public a a(boolean z) {
        this.b = (a * this.b) + (z ? 1 : 0);
        return this;
    }
}
