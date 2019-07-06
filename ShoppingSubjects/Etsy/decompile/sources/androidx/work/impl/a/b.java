package androidx.work.impl.a;

/* compiled from: NetworkState */
public class b {
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;

    public b(boolean z, boolean z2, boolean z3, boolean z4) {
        this.a = z;
        this.b = z2;
        this.c = z3;
        this.d = z4;
    }

    public boolean a() {
        return this.a;
    }

    public boolean b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public boolean d() {
        return this.d;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        if (!(this.a == bVar.a && this.b == bVar.b && this.c == bVar.c && this.d == bVar.d)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = this.a ? 1 : 0;
        if (this.b) {
            i += 16;
        }
        if (this.c) {
            i += 256;
        }
        return this.d ? i + 4096 : i;
    }

    public String toString() {
        return String.format("[ Connected=%b Validated=%b Metered=%b NotRoaming=%b ]", new Object[]{Boolean.valueOf(this.a), Boolean.valueOf(this.b), Boolean.valueOf(this.c), Boolean.valueOf(this.d)});
    }
}
