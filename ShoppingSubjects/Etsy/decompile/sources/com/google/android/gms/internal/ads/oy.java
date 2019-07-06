package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.overlay.i;

final class oy implements i {
    private nn a;
    private i b;

    public oy(nn nnVar, i iVar) {
        this.a = nnVar;
        this.b = iVar;
    }

    public final void onPause() {
    }

    public final void onResume() {
    }

    public final void zzcb() {
        this.b.zzcb();
        this.a.zzty();
    }

    public final void zzcc() {
        this.b.zzcc();
        this.a.zzno();
    }
}
