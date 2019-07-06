package com.google.android.gms.maps.model;

import android.os.RemoteException;
import com.google.android.gms.internal.maps.zzaf;

final class e implements d {
    private final zzaf b = this.c.zzeh;
    private final /* synthetic */ TileOverlayOptions c;

    e(TileOverlayOptions tileOverlayOptions) {
        this.c = tileOverlayOptions;
    }

    public final Tile a(int i, int i2, int i3) {
        try {
            return this.b.getTile(i, i2, i3);
        } catch (RemoteException unused) {
            return null;
        }
    }
}
