package com.google.android.gms.internal.ads;

import java.util.Arrays;

final class aat {
    final int a;
    final byte[] b;

    aat(int i, byte[] bArr) {
        this.a = i;
        this.b = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof aat)) {
            return false;
        }
        aat aat = (aat) obj;
        return this.a == aat.a && Arrays.equals(this.b, aat.b);
    }

    public final int hashCode() {
        return ((527 + this.a) * 31) + Arrays.hashCode(this.b);
    }
}
