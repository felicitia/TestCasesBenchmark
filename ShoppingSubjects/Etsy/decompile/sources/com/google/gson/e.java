package com.google.gson;

import com.google.gson.internal.a.d;
import com.google.gson.internal.a.g;
import com.google.gson.internal.a.h;
import com.google.gson.internal.a.i;
import com.google.gson.internal.a.j;
import com.google.gson.internal.a.k;
import com.google.gson.internal.a.n;
import com.google.gson.internal.b;
import com.google.gson.internal.c;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: Gson */
public final class e {
    private static final com.google.gson.a.a<?> a = com.google.gson.a.a.b(Object.class);
    private final ThreadLocal<Map<com.google.gson.a.a<?>, a<?>>> b;
    private final Map<com.google.gson.a.a<?>, q<?>> c;
    private final List<r> d;
    private final b e;
    private final c f;
    private final d g;
    private final boolean h;
    private final boolean i;
    private final boolean j;
    private final boolean k;
    private final boolean l;
    private final d m;

    /* compiled from: Gson */
    static class a<T> extends q<T> {
        private q<T> a;

        a() {
        }

        public void a(q<T> qVar) {
            if (this.a != null) {
                throw new AssertionError();
            }
            this.a = qVar;
        }

        public T b(com.google.gson.stream.a aVar) throws IOException {
            if (this.a != null) {
                return this.a.b(aVar);
            }
            throw new IllegalStateException();
        }

        public void a(com.google.gson.stream.b bVar, T t) throws IOException {
            if (this.a == null) {
                throw new IllegalStateException();
            }
            this.a.a(bVar, t);
        }
    }

    public e() {
        this(c.a, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, Collections.emptyList());
    }

    e(c cVar, d dVar, Map<Type, g<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, LongSerializationPolicy longSerializationPolicy, List<r> list) {
        this.b = new ThreadLocal<>();
        this.c = new ConcurrentHashMap();
        this.e = new b(map);
        this.f = cVar;
        this.g = dVar;
        this.h = z;
        this.j = z3;
        this.i = z4;
        this.k = z5;
        this.l = z6;
        ArrayList arrayList = new ArrayList();
        arrayList.add(n.Y);
        arrayList.add(h.a);
        arrayList.add(cVar);
        arrayList.addAll(list);
        arrayList.add(n.D);
        arrayList.add(n.m);
        arrayList.add(n.g);
        arrayList.add(n.i);
        arrayList.add(n.k);
        q a2 = a(longSerializationPolicy);
        arrayList.add(n.a(Long.TYPE, Long.class, a2));
        arrayList.add(n.a(Double.TYPE, Double.class, a(z7)));
        arrayList.add(n.a(Float.TYPE, Float.class, b(z7)));
        arrayList.add(n.x);
        arrayList.add(n.o);
        arrayList.add(n.q);
        arrayList.add(n.a(AtomicLong.class, a(a2)));
        arrayList.add(n.a(AtomicLongArray.class, b(a2)));
        arrayList.add(n.s);
        arrayList.add(n.z);
        arrayList.add(n.F);
        arrayList.add(n.H);
        arrayList.add(n.a(BigDecimal.class, n.B));
        arrayList.add(n.a(BigInteger.class, n.C));
        arrayList.add(n.J);
        arrayList.add(n.L);
        arrayList.add(n.P);
        arrayList.add(n.R);
        arrayList.add(n.W);
        arrayList.add(n.N);
        arrayList.add(n.d);
        arrayList.add(com.google.gson.internal.a.c.a);
        arrayList.add(n.U);
        arrayList.add(k.a);
        arrayList.add(j.a);
        arrayList.add(n.S);
        arrayList.add(com.google.gson.internal.a.a.a);
        arrayList.add(n.b);
        arrayList.add(new com.google.gson.internal.a.b(this.e));
        arrayList.add(new g(this.e, z2));
        this.m = new d(this.e);
        arrayList.add(this.m);
        arrayList.add(n.Z);
        arrayList.add(new i(this.e, dVar, cVar, this.m));
        this.d = Collections.unmodifiableList(arrayList);
    }

    private q<Number> a(boolean z) {
        if (z) {
            return n.v;
        }
        return new q<Number>() {
            /* renamed from: a */
            public Double b(com.google.gson.stream.a aVar) throws IOException {
                if (aVar.f() != JsonToken.NULL) {
                    return Double.valueOf(aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.google.gson.stream.b bVar, Number number) throws IOException {
                if (number == null) {
                    bVar.f();
                    return;
                }
                e.a(number.doubleValue());
                bVar.a(number);
            }
        };
    }

    private q<Number> b(boolean z) {
        if (z) {
            return n.u;
        }
        return new q<Number>() {
            /* renamed from: a */
            public Float b(com.google.gson.stream.a aVar) throws IOException {
                if (aVar.f() != JsonToken.NULL) {
                    return Float.valueOf((float) aVar.k());
                }
                aVar.j();
                return null;
            }

            public void a(com.google.gson.stream.b bVar, Number number) throws IOException {
                if (number == null) {
                    bVar.f();
                    return;
                }
                e.a((double) number.floatValue());
                bVar.a(number);
            }
        };
    }

    static void a(double d2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            StringBuilder sb = new StringBuilder();
            sb.append(d2);
            sb.append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private static q<Number> a(LongSerializationPolicy longSerializationPolicy) {
        if (longSerializationPolicy == LongSerializationPolicy.DEFAULT) {
            return n.t;
        }
        return new q<Number>() {
            /* renamed from: a */
            public Number b(com.google.gson.stream.a aVar) throws IOException {
                if (aVar.f() != JsonToken.NULL) {
                    return Long.valueOf(aVar.l());
                }
                aVar.j();
                return null;
            }

            public void a(com.google.gson.stream.b bVar, Number number) throws IOException {
                if (number == null) {
                    bVar.f();
                } else {
                    bVar.b(number.toString());
                }
            }
        };
    }

    private static q<AtomicLong> a(final q<Number> qVar) {
        return new q<AtomicLong>() {
            public void a(com.google.gson.stream.b bVar, AtomicLong atomicLong) throws IOException {
                qVar.a(bVar, Long.valueOf(atomicLong.get()));
            }

            /* renamed from: a */
            public AtomicLong b(com.google.gson.stream.a aVar) throws IOException {
                return new AtomicLong(((Number) qVar.b(aVar)).longValue());
            }
        }.a();
    }

    private static q<AtomicLongArray> b(final q<Number> qVar) {
        return new q<AtomicLongArray>() {
            public void a(com.google.gson.stream.b bVar, AtomicLongArray atomicLongArray) throws IOException {
                bVar.b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    qVar.a(bVar, Long.valueOf(atomicLongArray.get(i)));
                }
                bVar.c();
            }

            /* renamed from: a */
            public AtomicLongArray b(com.google.gson.stream.a aVar) throws IOException {
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(Long.valueOf(((Number) qVar.b(aVar)).longValue()));
                }
                aVar.b();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }
        }.a();
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=com.google.gson.a.a<T>, code=com.google.gson.a.a, for r6v0, types: [com.google.gson.a.a, com.google.gson.a.a<T>, java.lang.Object] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.google.gson.q<T> a(com.google.gson.a.a r6) {
        /*
            r5 = this;
            java.util.Map<com.google.gson.a.a<?>, com.google.gson.q<?>> r0 = r5.c
            if (r6 != 0) goto L_0x0007
            com.google.gson.a.a<?> r1 = a
            goto L_0x0008
        L_0x0007:
            r1 = r6
        L_0x0008:
            java.lang.Object r0 = r0.get(r1)
            com.google.gson.q r0 = (com.google.gson.q) r0
            if (r0 == 0) goto L_0x0011
            return r0
        L_0x0011:
            java.lang.ThreadLocal<java.util.Map<com.google.gson.a.a<?>, com.google.gson.e$a<?>>> r0 = r5.b
            java.lang.Object r0 = r0.get()
            java.util.Map r0 = (java.util.Map) r0
            r1 = 0
            if (r0 != 0) goto L_0x0027
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.ThreadLocal<java.util.Map<com.google.gson.a.a<?>, com.google.gson.e$a<?>>> r1 = r5.b
            r1.set(r0)
            r1 = 1
        L_0x0027:
            java.lang.Object r2 = r0.get(r6)
            com.google.gson.e$a r2 = (com.google.gson.e.a) r2
            if (r2 == 0) goto L_0x0030
            return r2
        L_0x0030:
            com.google.gson.e$a r2 = new com.google.gson.e$a     // Catch:{ all -> 0x007a }
            r2.<init>()     // Catch:{ all -> 0x007a }
            r0.put(r6, r2)     // Catch:{ all -> 0x007a }
            java.util.List<com.google.gson.r> r3 = r5.d     // Catch:{ all -> 0x007a }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x007a }
        L_0x003e:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x007a }
            if (r4 == 0) goto L_0x0063
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x007a }
            com.google.gson.r r4 = (com.google.gson.r) r4     // Catch:{ all -> 0x007a }
            com.google.gson.q r4 = r4.a(r5, r6)     // Catch:{ all -> 0x007a }
            if (r4 == 0) goto L_0x003e
            r2.a(r4)     // Catch:{ all -> 0x007a }
            java.util.Map<com.google.gson.a.a<?>, com.google.gson.q<?>> r2 = r5.c     // Catch:{ all -> 0x007a }
            r2.put(r6, r4)     // Catch:{ all -> 0x007a }
            r0.remove(r6)
            if (r1 == 0) goto L_0x0062
            java.lang.ThreadLocal<java.util.Map<com.google.gson.a.a<?>, com.google.gson.e$a<?>>> r6 = r5.b
            r6.remove()
        L_0x0062:
            return r4
        L_0x0063:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x007a }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x007a }
            r3.<init>()     // Catch:{ all -> 0x007a }
            java.lang.String r4 = "GSON cannot handle "
            r3.append(r4)     // Catch:{ all -> 0x007a }
            r3.append(r6)     // Catch:{ all -> 0x007a }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x007a }
            r2.<init>(r3)     // Catch:{ all -> 0x007a }
            throw r2     // Catch:{ all -> 0x007a }
        L_0x007a:
            r2 = move-exception
            r0.remove(r6)
            if (r1 == 0) goto L_0x0085
            java.lang.ThreadLocal<java.util.Map<com.google.gson.a.a<?>, com.google.gson.e$a<?>>> r6 = r5.b
            r6.remove()
        L_0x0085:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.e.a(com.google.gson.a.a):com.google.gson.q");
    }

    public <T> q<T> a(r rVar, com.google.gson.a.a<T> aVar) {
        if (!this.d.contains(rVar)) {
            rVar = this.m;
        }
        boolean z = false;
        for (r rVar2 : this.d) {
            if (z) {
                q<T> a2 = rVar2.a(this, aVar);
                if (a2 != null) {
                    return a2;
                }
            } else if (rVar2 == rVar) {
                z = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("GSON cannot serialize ");
        sb.append(aVar);
        throw new IllegalArgumentException(sb.toString());
    }

    public <T> q<T> a(Class<T> cls) {
        return a(com.google.gson.a.a.b(cls));
    }

    public com.google.gson.stream.b a(Writer writer) throws IOException {
        if (this.j) {
            writer.write(")]}'\n");
        }
        com.google.gson.stream.b bVar = new com.google.gson.stream.b(writer);
        if (this.k) {
            bVar.c("  ");
        }
        bVar.c(this.h);
        return bVar;
    }

    public com.google.gson.stream.a a(Reader reader) {
        com.google.gson.stream.a aVar = new com.google.gson.stream.a(reader);
        aVar.a(this.l);
        return aVar;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{serializeNulls:");
        sb.append(this.h);
        sb.append(",factories:");
        sb.append(this.d);
        sb.append(",instanceCreators:");
        sb.append(this.e);
        sb.append(VectorFormat.DEFAULT_SUFFIX);
        return sb.toString();
    }
}
