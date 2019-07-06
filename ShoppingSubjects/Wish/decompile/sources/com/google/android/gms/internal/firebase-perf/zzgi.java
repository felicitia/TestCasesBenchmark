package com.google.android.gms.internal.firebase-perf;

import java.util.Arrays;

final class zzgi {
    final int tag;
    final byte[] zzhw;

    zzgi(int i, byte[] bArr) {
        this.tag = i;
        this.zzhw = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgi)) {
            return false;
        }
        zzgi zzgi = (zzgi) obj;
        return this.tag == zzgi.tag && Arrays.equals(this.zzhw, zzgi.zzhw);
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzhw);
    }
}
