package com.lyft.android.scissors;

import com.squareup.picasso.Transformation;

/* compiled from: PicassoFillViewportTransformation */
class g implements Transformation {
    private final int a;
    private final int b;

    public g(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public static Transformation a(int i, int i2) {
        return new g(i, i2);
    }
}
