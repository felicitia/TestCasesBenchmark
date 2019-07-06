package com.google.android.gms.internal.ads;

import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.common.internal.Objects;

public final class il {
    public final String a;
    public final double b;
    public final int c;
    private final double d;
    private final double e;

    public il(String str, double d2, double d3, double d4, int i) {
        this.a = str;
        this.e = d2;
        this.d = d3;
        this.b = d4;
        this.c = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof il)) {
            return false;
        }
        il ilVar = (il) obj;
        return Objects.equal(this.a, ilVar.a) && this.d == ilVar.d && this.e == ilVar.e && this.c == ilVar.c && Double.compare(this.b, ilVar.b) == 0;
    }

    public final int hashCode() {
        return Objects.hashCode(this.a, Double.valueOf(this.d), Double.valueOf(this.e), Double.valueOf(this.b), Integer.valueOf(this.c));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(ResponseConstants.NAME, this.a).add("minBound", Double.valueOf(this.e)).add("maxBound", Double.valueOf(this.d)).add(ResponseConstants.PERCENT, Double.valueOf(this.b)).add(ResponseConstants.COUNT, Integer.valueOf(this.c)).toString();
    }
}
