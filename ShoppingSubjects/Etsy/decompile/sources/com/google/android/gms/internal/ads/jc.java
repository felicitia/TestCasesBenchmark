package com.google.android.gms.internal.ads;

import android.graphics.Bitmap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@bu
public final class jc {
    private Map<Integer, Bitmap> a = new ConcurrentHashMap();
    private AtomicInteger b = new AtomicInteger(0);

    public final int a(Bitmap bitmap) {
        if (bitmap == null) {
            gv.b("Bitmap is null. Skipping putting into the Memory Map.");
            return -1;
        }
        int andIncrement = this.b.getAndIncrement();
        this.a.put(Integer.valueOf(andIncrement), bitmap);
        return andIncrement;
    }

    public final Bitmap a(Integer num) {
        return (Bitmap) this.a.get(num);
    }

    public final void b(Integer num) {
        this.a.remove(num);
    }
}
