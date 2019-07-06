package androidx.work.impl.a.b;

import android.content.Context;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.e;
import androidx.work.impl.a.a;
import java.util.LinkedHashSet;
import java.util.Set;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: ConstraintTracker */
public abstract class d<T> {
    protected final Context a;
    private final Set<a<T>> b = new LinkedHashSet();
    private T c;

    public abstract T c();

    public abstract void d();

    public abstract void e();

    d(Context context) {
        this.a = context.getApplicationContext();
    }

    public void a(a<T> aVar) {
        if (this.b.add(aVar)) {
            if (this.b.size() == 1) {
                this.c = c();
                e.b("ConstraintTracker", String.format("%s: initial state = %s", new Object[]{getClass().getSimpleName(), this.c}), new Throwable[0]);
                d();
            }
            aVar.a(this.c);
        }
    }

    public void b(a<T> aVar) {
        if (this.b.remove(aVar) && this.b.isEmpty()) {
            e();
        }
    }

    public void a(T t) {
        if (this.c != t && (this.c == null || !this.c.equals(t))) {
            this.c = t;
            for (a a2 : this.b) {
                a2.a(this.c);
            }
        }
    }
}
