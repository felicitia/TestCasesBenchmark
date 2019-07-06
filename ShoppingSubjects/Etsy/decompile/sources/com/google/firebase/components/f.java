package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import org.apache.commons.math3.geometry.VectorFormat;

@KeepForSdk
public final class f {
    private final Class<?> a;
    private final int b;
    private final int c;

    private f(Class<?> cls, int i, int i2) {
        this.a = (Class) Preconditions.checkNotNull(cls, "Null dependency anInterface.");
        this.b = i;
        this.c = i2;
    }

    @KeepForSdk
    public static f a(Class<?> cls) {
        return new f(cls, 1, 0);
    }

    public final Class<?> a() {
        return this.a;
    }

    public final boolean b() {
        return this.b == 1;
    }

    public final boolean c() {
        return this.c == 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (this.a == fVar.a && this.b == fVar.b && this.c == fVar.c) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.a.hashCode() ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Dependency{anInterface=");
        sb.append(this.a);
        sb.append(", required=");
        boolean z = false;
        sb.append(this.b == 1);
        sb.append(", direct=");
        if (this.c == 0) {
            z = true;
        }
        sb.append(z);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
