package androidx.work;

import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import androidx.work.impl.b.j;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/* compiled from: WorkRequest */
public abstract class k {
    @NonNull
    private UUID a;
    @NonNull
    private j b;
    @NonNull
    private Set<String> c;

    /* compiled from: WorkRequest */
    public static abstract class a<B extends a, W extends k> {
        boolean a = false;
        UUID b = UUID.randomUUID();
        j c;
        Set<String> d = new HashSet();

        /* access modifiers changed from: 0000 */
        @NonNull
        public abstract B c();

        /* access modifiers changed from: 0000 */
        @NonNull
        public abstract W d();

        a(@NonNull Class<? extends Worker> cls) {
            this.c = new j(this.b.toString(), cls.getName());
            a(cls.getName());
        }

        @NonNull
        public final B a(@NonNull b bVar) {
            this.c.j = bVar;
            return c();
        }

        @NonNull
        public final B a(@NonNull String str) {
            this.d.add(str);
            return c();
        }

        @NonNull
        public final W e() {
            W d2 = d();
            this.b = UUID.randomUUID();
            this.c = new j(this.c);
            this.c.a = this.b.toString();
            return d2;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    protected k(@NonNull UUID uuid, @NonNull j jVar, @NonNull Set<String> set) {
        this.a = uuid;
        this.b = jVar;
        this.c = set;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public String a() {
        return this.a.toString();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public j b() {
        return this.b;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @NonNull
    public Set<String> c() {
        return this.c;
    }
}
