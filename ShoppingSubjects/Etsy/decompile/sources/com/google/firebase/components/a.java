package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.math3.geometry.VectorFormat;

@KeepForSdk
public final class a<T> {
    private final Set<Class<? super T>> a;
    private final Set<f> b;
    private final int c;
    private final d<T> d;
    private final Set<Class<?>> e;

    @KeepForSdk
    /* renamed from: com.google.firebase.components.a$a reason: collision with other inner class name */
    public static class C0152a<T> {
        private final Set<Class<? super T>> a;
        private final Set<f> b;
        private int c;
        private d<T> d;
        private Set<Class<?>> e;

        /* synthetic */ C0152a(Class cls, Class[] clsArr, byte b2) {
            this(cls, clsArr);
        }

        private C0152a(Class<T> cls, Class<? super T>... clsArr) {
            this.a = new HashSet();
            this.b = new HashSet();
            this.c = 0;
            this.e = new HashSet();
            Preconditions.checkNotNull(cls, "Null interface");
            this.a.add(cls);
            for (Class<? super T> checkNotNull : clsArr) {
                Preconditions.checkNotNull(checkNotNull, "Null interface");
            }
            Collections.addAll(this.a, clsArr);
        }

        @KeepForSdk
        public C0152a<T> a(f fVar) {
            Preconditions.checkNotNull(fVar, "Null dependency");
            Preconditions.checkArgument(!this.a.contains(fVar.a()), "Components are not allowed to depend on interfaces they themselves provide.");
            this.b.add(fVar);
            return this;
        }

        @KeepForSdk
        public C0152a<T> a() {
            return a(1);
        }

        @KeepForSdk
        public C0152a<T> b() {
            return a(2);
        }

        private C0152a<T> a(int i) {
            Preconditions.checkState(this.c == 0, "Instantiation type has already been set.");
            this.c = i;
            return this;
        }

        @KeepForSdk
        public C0152a<T> a(d<T> dVar) {
            this.d = (d) Preconditions.checkNotNull(dVar, "Null factory");
            return this;
        }

        @KeepForSdk
        public a<T> c() {
            Preconditions.checkState(this.d != null, "Missing required property: factory.");
            a aVar = new a(new HashSet(this.a), new HashSet(this.b), this.c, this.d, this.e, 0);
            return aVar;
        }
    }

    static final /* synthetic */ Object a(Object obj) {
        return obj;
    }

    /* synthetic */ a(Set set, Set set2, int i, d dVar, Set set3, byte b2) {
        this(set, set2, i, dVar, set3);
    }

    private a(Set<Class<? super T>> set, Set<f> set2, int i, d<T> dVar, Set<Class<?>> set3) {
        this.a = Collections.unmodifiableSet(set);
        this.b = Collections.unmodifiableSet(set2);
        this.c = i;
        this.d = dVar;
        this.e = Collections.unmodifiableSet(set3);
    }

    public final Set<Class<? super T>> a() {
        return this.a;
    }

    public final Set<f> b() {
        return this.b;
    }

    public final d<T> c() {
        return this.d;
    }

    public final Set<Class<?>> d() {
        return this.e;
    }

    public final boolean e() {
        return this.c == 1;
    }

    public final boolean f() {
        return this.c == 2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Component<");
        sb.append(Arrays.toString(this.a.toArray()));
        sb.append(">{");
        sb.append(this.c);
        sb.append(", deps=");
        sb.append(Arrays.toString(this.b.toArray()));
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }

    @KeepForSdk
    public static <T> C0152a<T> a(Class<T> cls) {
        return new C0152a<>(cls, new Class[0], 0);
    }

    @KeepForSdk
    public static <T> C0152a<T> a(Class<T> cls, Class<? super T>... clsArr) {
        return new C0152a<>(cls, clsArr, 0);
    }

    @KeepForSdk
    @SafeVarargs
    public static <T> a<T> a(T t, Class<T> cls, Class<? super T>... clsArr) {
        return a(cls, clsArr).a((d<T>) new g<T>(t)).c();
    }
}
