package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: SlidingWindow.kt */
final class ak<T> extends d<T> implements RandomAccess {
    /* access modifiers changed from: private */
    public final Object[] b;
    /* access modifiers changed from: private */
    public int c;
    private int d;
    private final int e;

    /* compiled from: SlidingWindow.kt */
    public static final class a extends b<T> {
        final /* synthetic */ ak a;
        private int b;
        private int c;

        a(ak akVar) {
            this.a = akVar;
            this.b = akVar.size();
            this.c = akVar.c;
        }

        /* access modifiers changed from: protected */
        public void a() {
            if (this.b == 0) {
                b();
                return;
            }
            a(this.a.b[this.c]);
            this.c = (this.c + 1) % this.a.c();
            this.b--;
        }
    }

    public ak(int i) {
        this.e = i;
        if (!(this.e >= 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("ring buffer capacity should not be negative but it is ");
            sb.append(this.e);
            throw new IllegalArgumentException(sb.toString().toString());
        }
        this.b = new Object[this.e];
    }

    public final int c() {
        return this.e;
    }

    private void b(int i) {
        this.d = i;
    }

    public int a() {
        return this.d;
    }

    public T get(int i) {
        d.a.a(i, size());
        return this.b[(this.c + i) % c()];
    }

    public final boolean b() {
        return size() == this.e;
    }

    public Iterator<T> iterator() {
        return new a<>(this);
    }

    public <T> T[] toArray(T[] tArr) {
        p.b(tArr, "array");
        if (tArr.length < size()) {
            tArr = Arrays.copyOf(tArr, size());
            p.a((Object) tArr, "java.util.Arrays.copyOf(this, newSize)");
        }
        int size = size();
        int i = 0;
        int i2 = this.c;
        int i3 = 0;
        while (i3 < size && i2 < this.e) {
            tArr[i3] = this.b[i2];
            i3++;
            i2++;
        }
        while (i3 < size) {
            tArr[i3] = this.b[i];
            i3++;
            i++;
        }
        if (tArr.length > size()) {
            tArr[size()] = null;
        }
        if (tArr != null) {
            return tArr;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    public final void a(T t) {
        if (b()) {
            throw new IllegalStateException("ring buffer is full");
        }
        this.b[(this.c + size()) % c()] = t;
        b(size() + 1);
    }

    public final void a(int i) {
        boolean z = true;
        if (!(i >= 0)) {
            StringBuilder sb = new StringBuilder();
            sb.append("n shouldn't be negative but it is ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString().toString());
        }
        if (i > size()) {
            z = false;
        }
        if (!z) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("n shouldn't be greater than the buffer size: n = ");
            sb2.append(i);
            sb2.append(", size = ");
            sb2.append(size());
            throw new IllegalArgumentException(sb2.toString().toString());
        } else if (i > 0) {
            int i2 = this.c;
            int c2 = (i2 + i) % c();
            if (i2 > c2) {
                a(this.b, null, i2, this.e);
                a(this.b, null, 0, c2);
            } else {
                a(this.b, null, i2, c2);
            }
            this.c = c2;
            b(size() - i);
        }
    }

    private final <T> void a(T[] tArr, T t, int i, int i2) {
        while (i < i2) {
            tArr[i] = t;
            i++;
        }
    }
}
