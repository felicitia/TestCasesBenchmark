package com.google.android.gms.maps.model;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;

public final class CustomCap extends Cap {
    public final a bitmapDescriptor;
    public final float refWidth;

    public CustomCap(@NonNull a aVar) {
        this(aVar, 10.0f);
    }

    public CustomCap(@NonNull a aVar, float f) {
        a aVar2 = (a) Preconditions.checkNotNull(aVar, "bitmapDescriptor must not be null");
        String str = "refWidth must be positive";
        if (f <= 0.0f) {
            throw new IllegalArgumentException(str);
        }
        super(aVar2, f);
        this.bitmapDescriptor = aVar;
        this.refWidth = f;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.bitmapDescriptor);
        float f = this.refWidth;
        StringBuilder sb = new StringBuilder(55 + String.valueOf(valueOf).length());
        sb.append("[CustomCap: bitmapDescriptor=");
        sb.append(valueOf);
        sb.append(" refWidth=");
        sb.append(f);
        sb.append("]");
        return sb.toString();
    }
}
