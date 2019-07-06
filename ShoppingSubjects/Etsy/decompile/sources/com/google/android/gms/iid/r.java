package com.google.android.gms.iid;

import android.util.Base64;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import java.security.KeyPair;

final class r {
    private final KeyPair a;
    /* access modifiers changed from: private */
    public final long b;

    @VisibleForTesting
    r(KeyPair keyPair, long j) {
        this.a = keyPair;
        this.b = j;
    }

    /* access modifiers changed from: private */
    public final String b() {
        return Base64.encodeToString(this.a.getPublic().getEncoded(), 11);
    }

    /* access modifiers changed from: private */
    public final String c() {
        return Base64.encodeToString(this.a.getPrivate().getEncoded(), 11);
    }

    /* access modifiers changed from: 0000 */
    public final KeyPair a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        return this.b == rVar.b && this.a.getPublic().equals(rVar.a.getPublic()) && this.a.getPrivate().equals(rVar.a.getPrivate());
    }

    public final int hashCode() {
        return Objects.hashCode(this.a.getPublic(), this.a.getPrivate(), Long.valueOf(this.b));
    }
}
