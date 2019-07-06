package kotlin.collections;

import kotlin.jvm.internal.p;

/* compiled from: IndexedValue.kt */
public final class ac<T> {
    private final int a;
    private final T b;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ac) {
            ac acVar = (ac) obj;
            return (this.a == acVar.a) && p.a((Object) this.b, (Object) acVar.b);
        }
    }

    public int hashCode() {
        int i = this.a * 31;
        T t = this.b;
        return i + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IndexedValue(index=");
        sb.append(this.a);
        sb.append(", value=");
        sb.append(this.b);
        sb.append(")");
        return sb.toString();
    }

    public final int a() {
        return this.a;
    }

    public final T b() {
        return this.b;
    }
}
