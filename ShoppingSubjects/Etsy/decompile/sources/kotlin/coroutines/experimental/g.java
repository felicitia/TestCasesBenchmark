package kotlin.coroutines.experimental;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.coroutines.experimental.jvm.internal.a;
import kotlin.h;
import kotlin.jvm.internal.p;

/* compiled from: SequenceBuilder.kt */
final class g<T> extends f<T> implements Iterator<T>, b<h> {
    private int a;
    private T b;
    private Iterator<? extends T> c;
    private b<? super h> d;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void a(b<? super h> bVar) {
        this.d = bVar;
    }

    public boolean hasNext() {
        while (true) {
            switch (this.a) {
                case 0:
                    break;
                case 1:
                    Iterator<? extends T> it = this.c;
                    if (it == null) {
                        p.a();
                    }
                    if (!it.hasNext()) {
                        this.c = null;
                        break;
                    } else {
                        this.a = 2;
                        return true;
                    }
                case 2:
                case 3:
                    return true;
                case 4:
                    return false;
                default:
                    throw b();
            }
            this.a = 5;
            b<? super h> bVar = this.d;
            if (bVar == null) {
                p.a();
            }
            this.d = null;
            bVar.resume(h.a);
        }
    }

    public T next() {
        switch (this.a) {
            case 0:
            case 1:
                return a();
            case 2:
                this.a = 1;
                Iterator<? extends T> it = this.c;
                if (it == null) {
                    p.a();
                }
                return it.next();
            case 3:
                this.a = 0;
                T t = this.b;
                this.b = null;
                return t;
            default:
                throw b();
        }
    }

    private final T a() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    private final Throwable b() {
        switch (this.a) {
            case 4:
                return new NoSuchElementException();
            case 5:
                return new IllegalStateException("Iterator has failed.");
            default:
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected state of the iterator: ");
                sb.append(this.a);
                return new IllegalStateException(sb.toString());
        }
    }

    public Object a(T t, b<? super h> bVar) {
        this.b = t;
        this.a = 3;
        a(a.a(bVar));
        return kotlin.coroutines.experimental.a.a.a();
    }

    /* renamed from: a */
    public void resume(h hVar) {
        p.b(hVar, ResponseConstants.VALUE);
        this.a = 4;
    }

    public void resumeWithException(Throwable th) {
        p.b(th, "exception");
        throw th;
    }

    public d getContext() {
        return e.a;
    }
}
