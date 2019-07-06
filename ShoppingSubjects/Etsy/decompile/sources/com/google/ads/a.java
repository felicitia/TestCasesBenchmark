package com.google.ads;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.android.gms.ads.d;

@Deprecated
public final class a {
    public static final a a = new a(-1, -2, "mb");
    public static final a b = new a(320, 50, "mb");
    public static final a c = new a(300, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, "as");
    public static final a d = new a(468, 60, "as");
    public static final a e = new a(728, 90, "as");
    public static final a f = new a(160, 600, "as");
    private final d g;

    private a(int i, int i2, String str) {
        this(new d(i, i2));
    }

    public a(d dVar) {
        this.g = dVar;
    }

    public final int a() {
        return this.g.b();
    }

    public final int b() {
        return this.g.a();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        return this.g.equals(((a) obj).g);
    }

    public final int hashCode() {
        return this.g.hashCode();
    }

    public final String toString() {
        return this.g.toString();
    }
}
