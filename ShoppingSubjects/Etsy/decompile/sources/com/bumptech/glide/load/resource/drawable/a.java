package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.engine.i;

/* compiled from: DrawableResource */
public abstract class a<T extends Drawable> implements i<T> {
    protected final T a;

    public a(T t) {
        if (t == null) {
            throw new NullPointerException("Drawable must not be null!");
        }
        this.a = t;
    }

    /* renamed from: a */
    public final T b() {
        return this.a.getConstantState().newDrawable();
    }
}
