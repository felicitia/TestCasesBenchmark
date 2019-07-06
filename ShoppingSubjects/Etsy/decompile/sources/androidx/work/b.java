package androidx.work;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

/* compiled from: Constraints */
public final class b {
    public static final b a = new a().a();
    private NetworkType b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    @Nullable
    private c g;

    /* compiled from: Constraints */
    public static final class a {
        boolean a = false;
        boolean b = false;
        NetworkType c = NetworkType.NOT_REQUIRED;
        boolean d = false;
        boolean e = false;
        c f = new c();

        @NonNull
        public a a(@NonNull NetworkType networkType) {
            this.c = networkType;
            return this;
        }

        @NonNull
        public b a() {
            return new b(this);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public b() {
    }

    b(a aVar) {
        this.c = aVar.a;
        this.d = VERSION.SDK_INT >= 23 && aVar.b;
        this.b = aVar.c;
        this.e = aVar.d;
        this.f = aVar.e;
        this.g = VERSION.SDK_INT >= 24 ? aVar.f : new c();
    }

    public b(@NonNull b bVar) {
        this.c = bVar.c;
        this.d = bVar.d;
        this.b = bVar.b;
        this.e = bVar.e;
        this.f = bVar.f;
        this.g = bVar.g;
    }

    @NonNull
    public NetworkType a() {
        return this.b;
    }

    public void a(@NonNull NetworkType networkType) {
        this.b = networkType;
    }

    public boolean b() {
        return this.c;
    }

    public void a(boolean z) {
        this.c = z;
    }

    @RequiresApi(23)
    public boolean c() {
        return this.d;
    }

    @RequiresApi(23)
    public void b(boolean z) {
        this.d = z;
    }

    public boolean d() {
        return this.e;
    }

    public void c(boolean z) {
        this.e = z;
    }

    public boolean e() {
        return this.f;
    }

    public void d(boolean z) {
        this.f = z;
    }

    @RequiresApi(24)
    public void a(@Nullable c cVar) {
        this.g = cVar;
    }

    @Nullable
    @RequiresApi(24)
    public c f() {
        return this.g;
    }

    @RequiresApi(24)
    public boolean g() {
        return this.g != null && this.g.a() > 0;
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
        if (!(this.b == bVar.b && this.c == bVar.c && this.d == bVar.d && this.e == bVar.e && this.f == bVar.f && (this.g == null ? bVar.g == null : this.g.equals(bVar.g)))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (31 * ((((((((this.b.hashCode() * 31) + (this.c ? 1 : 0)) * 31) + (this.d ? 1 : 0)) * 31) + (this.e ? 1 : 0)) * 31) + (this.f ? 1 : 0))) + (this.g != null ? this.g.hashCode() : 0);
    }
}
