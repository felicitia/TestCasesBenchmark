package com.lyft.android.scissors;

import com.nostra13.universalimageloader.core.display.BitmapDisplayer;

/* compiled from: UILFillViewportDisplayer */
class l implements BitmapDisplayer {
    private final int a;
    private final int b;

    public l(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public static BitmapDisplayer a(int i, int i2) {
        return new l(i, i2);
    }
}
