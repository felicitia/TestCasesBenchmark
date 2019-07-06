package io.reactivex.e;

import io.reactivex.internal.functions.a;
import java.util.concurrent.TimeUnit;

/* compiled from: Timed */
public final class b<T> {
    final T a;
    final long b;
    final TimeUnit c;

    public b(T t, long j, TimeUnit timeUnit) {
        this.a = t;
        this.b = j;
        this.c = (TimeUnit) a.a(timeUnit, "unit is null");
    }

    public T a() {
        return this.a;
    }

    public long b() {
        return this.b;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (a.a((Object) this.a, (Object) bVar.a) && this.b == bVar.b && a.a((Object) this.c, (Object) bVar.c)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return ((((this.a != null ? this.a.hashCode() : 0) * 31) + ((int) ((this.b >>> 31) ^ this.b))) * 31) + this.c.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Timed[time=");
        sb.append(this.b);
        sb.append(", unit=");
        sb.append(this.c);
        sb.append(", value=");
        sb.append(this.a);
        sb.append("]");
        return sb.toString();
    }
}
