package com.bumptech.glide.load.engine.bitmap_recycle;

import com.bumptech.glide.load.engine.bitmap_recycle.h;
import java.util.Queue;

/* compiled from: BaseKeyPool */
abstract class b<T extends h> {
    private final Queue<T> a = com.bumptech.glide.g.h.a(20);

    /* access modifiers changed from: protected */
    public abstract T b();

    b() {
    }

    /* access modifiers changed from: protected */
    public T c() {
        T t = (h) this.a.poll();
        return t == null ? b() : t;
    }

    public void a(T t) {
        if (this.a.size() < 20) {
            this.a.offer(t);
        }
    }
}
