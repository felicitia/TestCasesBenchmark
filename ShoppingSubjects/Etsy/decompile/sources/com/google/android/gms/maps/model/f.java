package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzag;

final class f extends zzag {
    private final /* synthetic */ d a;

    f(TileOverlayOptions tileOverlayOptions, d dVar) {
        this.a = dVar;
    }

    public final Tile getTile(int i, int i2, int i3) {
        return this.a.a(i, i2, i3);
    }
}
