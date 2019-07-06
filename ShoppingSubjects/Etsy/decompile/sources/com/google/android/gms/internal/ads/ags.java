package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;

public final class ags {
    final long a;
    final String b;
    final int c;

    ags(long j, String str, int i) {
        this.a = j;
        this.b = str;
        this.c = i;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof ags)) {
            return false;
        }
        ags ags = (ags) obj;
        if (ags.a == this.a && ags.c == this.c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (int) this.a;
    }
}
