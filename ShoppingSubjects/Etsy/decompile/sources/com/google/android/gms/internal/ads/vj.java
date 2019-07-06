package com.google.android.gms.internal.ads;

import java.security.SecureRandom;

final class vj extends ThreadLocal<SecureRandom> {
    vj() {
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object initialValue() {
        return vi.b();
    }
}
