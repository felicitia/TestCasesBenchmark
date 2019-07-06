package com.bumptech.glide.load.b;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.bumptech.glide.g.e;
import com.bumptech.glide.g.h;
import java.util.Queue;

/* compiled from: ModelCache */
public class k<A, B> {
    private final e<a<A>, B> a;

    /* compiled from: ModelCache */
    static final class a<A> {
        private static final Queue<a<?>> a = h.a(0);
        private int b;
        private int c;
        private A d;

        static <A> a<A> a(A a2, int i, int i2) {
            a<A> aVar = (a) a.poll();
            if (aVar == null) {
                aVar = new a<>();
            }
            aVar.b(a2, i, i2);
            return aVar;
        }

        private a() {
        }

        private void b(A a2, int i, int i2) {
            this.d = a2;
            this.c = i;
            this.b = i2;
        }

        public void a() {
            a.offer(this);
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.c == aVar.c && this.b == aVar.b && this.d.equals(aVar.d)) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return (31 * ((this.b * 31) + this.c)) + this.d.hashCode();
        }
    }

    public k() {
        this(Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }

    public k(int i) {
        this.a = new e<a<A>, B>(i) {
            /* access modifiers changed from: protected */
            public void a(a<A> aVar, B b) {
                aVar.a();
            }
        };
    }

    public B a(A a2, int i, int i2) {
        a a3 = a.a(a2, i, i2);
        B b = this.a.b(a3);
        a3.a();
        return b;
    }

    public void a(A a2, int i, int i2, B b) {
        this.a.b(a.a(a2, i, i2), b);
    }
}
