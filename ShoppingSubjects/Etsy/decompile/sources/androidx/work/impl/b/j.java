package androidx.work.impl.b;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.BackoffPolicy;
import androidx.work.Data;
import androidx.work.OverwritingInputMerger;
import androidx.work.State;
import androidx.work.b;
import androidx.work.e;
import java.util.List;
import org.apache.commons.math3.geometry.VectorFormat;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: WorkSpec */
public class j {
    public static final android.arch.core.a.a<List<Object>, List<Object>> q = new android.arch.core.a.a<List<Object>, List<Object>>() {
    };
    @NonNull
    public String a;
    @NonNull
    public State b = State.ENQUEUED;
    @NonNull
    public String c;
    public String d = OverwritingInputMerger.class.getName();
    @NonNull
    public Data e = Data.a;
    @NonNull
    public Data f = Data.a;
    public long g;
    public long h;
    public long i;
    @NonNull
    public b j = b.a;
    public int k;
    @NonNull
    public BackoffPolicy l = BackoffPolicy.EXPONENTIAL;
    public long m = 30000;
    public long n;
    public long o;
    public long p = -1;

    /* compiled from: WorkSpec */
    public static class a {
        public String a;
        public State b;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (this.b != aVar.b) {
                return false;
            }
            return this.a.equals(aVar.a);
        }

        public int hashCode() {
            return (31 * this.a.hashCode()) + this.b.hashCode();
        }
    }

    public j(@NonNull String str, @NonNull String str2) {
        this.a = str;
        this.c = str2;
    }

    public j(@NonNull j jVar) {
        this.a = jVar.a;
        this.c = jVar.c;
        this.b = jVar.b;
        this.d = jVar.d;
        this.e = new Data(jVar.e);
        this.f = new Data(jVar.f);
        this.g = jVar.g;
        this.h = jVar.h;
        this.i = jVar.i;
        this.j = new b(jVar.j);
        this.k = jVar.k;
        this.l = jVar.l;
        this.m = jVar.m;
        this.n = jVar.n;
        this.o = jVar.o;
        this.p = jVar.p;
    }

    public boolean a() {
        return this.h != 0;
    }

    public boolean b() {
        return this.b == State.ENQUEUED && this.k > 0;
    }

    public void a(long j2) {
        if (j2 < 900000) {
            e.d("WorkSpec", String.format("Interval duration lesser than minimum allowed value; Changed to %s", new Object[]{Long.valueOf(900000)}), new Throwable[0]);
            j2 = 900000;
        }
        a(j2, j2);
    }

    public void a(long j2, long j3) {
        if (j2 < 900000) {
            e.d("WorkSpec", String.format("Interval duration lesser than minimum allowed value; Changed to %s", new Object[]{Long.valueOf(900000)}), new Throwable[0]);
            j2 = 900000;
        }
        if (j3 < 300000) {
            e.d("WorkSpec", String.format("Flex duration lesser than minimum allowed value; Changed to %s", new Object[]{Long.valueOf(300000)}), new Throwable[0]);
            j3 = 300000;
        }
        if (j3 > j2) {
            e.d("WorkSpec", String.format("Flex duration greater than interval duration; Changed to %s", new Object[]{Long.valueOf(j2)}), new Throwable[0]);
            j3 = j2;
        }
        this.h = j2;
        this.i = j3;
    }

    public long c() {
        long j2;
        if (b()) {
            if (this.l == BackoffPolicy.LINEAR) {
                j2 = this.m * ((long) this.k);
            } else {
                j2 = (long) Math.scalb((float) this.m, this.k - 1);
            }
            return this.n + Math.min(18000000, j2);
        } else if (a()) {
            return (this.n + this.h) - this.i;
        } else {
            return this.n + this.g;
        }
    }

    public boolean d() {
        return !b.a.equals(this.j);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        j jVar = (j) obj;
        if (this.g != jVar.g || this.h != jVar.h || this.i != jVar.i || this.k != jVar.k || this.m != jVar.m || this.n != jVar.n || this.o != jVar.o || this.p != jVar.p || !this.a.equals(jVar.a) || this.b != jVar.b || !this.c.equals(jVar.c)) {
            return false;
        }
        if (this.d == null ? jVar.d != null : !this.d.equals(jVar.d)) {
            return false;
        }
        if (!this.e.equals(jVar.e) || !this.f.equals(jVar.f) || !this.j.equals(jVar.j)) {
            return false;
        }
        if (this.l != jVar.l) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (31 * ((((((((((((((((((((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + (this.d != null ? this.d.hashCode() : 0)) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + ((int) (this.g ^ (this.g >>> 32)))) * 31) + ((int) (this.h ^ (this.h >>> 32)))) * 31) + ((int) (this.i ^ (this.i >>> 32)))) * 31) + this.j.hashCode()) * 31) + this.k) * 31) + this.l.hashCode()) * 31) + ((int) (this.m ^ (this.m >>> 32)))) * 31) + ((int) (this.n ^ (this.n >>> 32)))) * 31) + ((int) (this.o ^ (this.o >>> 32))))) + ((int) (this.p ^ (this.p >>> 32)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{WorkSpec: ");
        sb.append(this.a);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
