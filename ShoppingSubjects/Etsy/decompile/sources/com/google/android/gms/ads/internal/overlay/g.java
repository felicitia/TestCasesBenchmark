package com.google.android.gms.ads.internal.overlay;

import android.graphics.drawable.Drawable;

final class g implements Runnable {
    private final /* synthetic */ Drawable a;
    private final /* synthetic */ f b;

    g(f fVar, Drawable drawable) {
        this.b = fVar;
        this.a = drawable;
    }

    public final void run() {
        this.b.a.mActivity.getWindow().setBackgroundDrawable(this.a);
    }
}
