package androidx.work.impl.a.a;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import androidx.work.impl.a.b.d;
import androidx.work.impl.b.j;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ConstraintController */
public abstract class c<T> implements androidx.work.impl.a.a<T> {
    private final List<String> a = new ArrayList();
    private T b;
    private d<T> c;
    private a d;

    /* compiled from: ConstraintController */
    public interface a {
        void b(@NonNull List<String> list);

        void c(@NonNull List<String> list);
    }

    /* access modifiers changed from: 0000 */
    public abstract boolean a(@NonNull j jVar);

    /* access modifiers changed from: 0000 */
    public abstract boolean b(@NonNull T t);

    c(d<T> dVar) {
        this.c = dVar;
    }

    public void a(a aVar) {
        if (this.d != aVar) {
            this.d = aVar;
            b();
        }
    }

    public void a(@NonNull List<j> list) {
        this.a.clear();
        for (j jVar : list) {
            if (a(jVar)) {
                this.a.add(jVar.a);
            }
        }
        if (this.a.isEmpty()) {
            this.c.b(this);
        } else {
            this.c.a((androidx.work.impl.a.a<T>) this);
        }
        b();
    }

    public void a() {
        if (!this.a.isEmpty()) {
            this.a.clear();
            this.c.b(this);
        }
    }

    public boolean a(@NonNull String str) {
        return this.b != null && b(this.b) && this.a.contains(str);
    }

    private void b() {
        if (!this.a.isEmpty() && this.d != null) {
            if (this.b == null || b(this.b)) {
                this.d.c(this.a);
            } else {
                this.d.b(this.a);
            }
        }
    }

    public void a(@Nullable T t) {
        this.b = t;
        b();
    }
}
