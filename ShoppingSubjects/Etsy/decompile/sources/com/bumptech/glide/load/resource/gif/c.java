package com.bumptech.glide.load.resource.gif;

import com.bumptech.glide.g.h;
import com.bumptech.glide.load.resource.drawable.a;

/* compiled from: GifDrawableResource */
public class c extends a<GifDrawable> {
    public c(GifDrawable gifDrawable) {
        super(gifDrawable);
    }

    public int c() {
        return ((GifDrawable) this.a).getData().length + h.a(((GifDrawable) this.a).getFirstFrame());
    }

    public void d() {
        ((GifDrawable) this.a).stop();
        ((GifDrawable) this.a).recycle();
    }
}
