package com.google.android.gms.maps;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;

public class i {
    private final IStreetViewPanoramaDelegate a;

    public i(@NonNull IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) {
        this.a = (IStreetViewPanoramaDelegate) Preconditions.checkNotNull(iStreetViewPanoramaDelegate, "delegate");
    }
}
