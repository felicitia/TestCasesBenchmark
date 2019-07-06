package com.squareup.moshi;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: Moshi */
public final class m {
    static final List<com.squareup.moshi.JsonAdapter.a> a = new ArrayList(5);
    private final List<com.squareup.moshi.JsonAdapter.a> b;
    private final ThreadLocal<List<b<?>>> c = new ThreadLocal<>();
    private final Map<Object, JsonAdapter<?>> d = new LinkedHashMap();

    /* compiled from: Moshi */
    public static final class a {
        final List<com.squareup.moshi.JsonAdapter.a> a = new ArrayList();

        public a a(com.squareup.moshi.JsonAdapter.a aVar) {
            if (aVar == null) {
                throw new IllegalArgumentException("factory == null");
            }
            this.a.add(aVar);
            return this;
        }

        public a a(Object obj) {
            if (obj != null) {
                return a((com.squareup.moshi.JsonAdapter.a) a.a(obj));
            }
            throw new IllegalArgumentException("adapter == null");
        }

        public m a() {
            return new m(this);
        }
    }

    /* compiled from: Moshi */
    private static class b<T> extends JsonAdapter<T> {
        Object a;
        private JsonAdapter<T> b;

        b(Object obj) {
            this.a = obj;
        }

        /* access modifiers changed from: 0000 */
        public void a(JsonAdapter<T> jsonAdapter) {
            this.b = jsonAdapter;
            this.a = null;
        }

        public T fromJson(JsonReader jsonReader) throws IOException {
            if (this.b != null) {
                return this.b.fromJson(jsonReader);
            }
            throw new IllegalStateException("Type adapter isn't ready");
        }

        public void toJson(l lVar, T t) throws IOException {
            if (this.b == null) {
                throw new IllegalStateException("Type adapter isn't ready");
            }
            this.b.toJson(lVar, t);
        }
    }

    static {
        a.add(StandardJsonAdapters.a);
        a.add(CollectionJsonAdapter.FACTORY);
        a.add(MapJsonAdapter.FACTORY);
        a.add(ArrayJsonAdapter.FACTORY);
        a.add(ClassJsonAdapter.FACTORY);
    }

    m(a aVar) {
        ArrayList arrayList = new ArrayList(aVar.a.size() + a.size());
        arrayList.addAll(aVar.a);
        arrayList.addAll(a);
        this.b = Collections.unmodifiableList(arrayList);
    }

    public <T> JsonAdapter<T> a(Type type) {
        return a(type, com.squareup.moshi.a.a.a);
    }

    public <T> JsonAdapter<T> a(Class<T> cls) {
        return a(cls, com.squareup.moshi.a.a.a);
    }

    /* JADX INFO: used method not loaded: com.squareup.moshi.a.a.a(java.lang.reflect.Type, java.util.Set):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r1 = (java.util.List) r7.c.get();
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r1 == null) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        r3 = r1.size();
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        if (r4 >= r3) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003e, code lost:
        r5 = (com.squareup.moshi.m.b) r1.get(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004a, code lost:
        if (r5.a.equals(r0) == false) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004c, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004d, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0050, code lost:
        r1 = new java.util.ArrayList();
        r7.c.set(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005a, code lost:
        r3 = new com.squareup.moshi.m.b(r0);
        r1.add(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r4 = r7.b.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0068, code lost:
        if (r2 >= r4) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006a, code lost:
        r5 = ((com.squareup.moshi.JsonAdapter.a) r7.b.get(r2)).a(r8, r9, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0076, code lost:
        if (r5 == null) goto L_0x009c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0078, code lost:
        r3.a(r5);
        r8 = r7.d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007d, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r7.d.put(r0, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0083, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0084, code lost:
        r1.remove(r1.size() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0091, code lost:
        if (r1.isEmpty() == false) goto L_0x0098;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0093, code lost:
        r7.c.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0098, code lost:
        return r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x009c, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x009f, code lost:
        r1.remove(r1.size() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ac, code lost:
        if (r1.isEmpty() == false) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ae, code lost:
        r7.c.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b3, code lost:
        r1 = new java.lang.StringBuilder();
        r1.append("No JsonAdapter for ");
        r1.append(com.squareup.moshi.a.a.a(r8, (java.util.Set) r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00cd, code lost:
        throw new java.lang.IllegalArgumentException(r1.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ce, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00cf, code lost:
        r1.remove(r1.size() - 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00dc, code lost:
        if (r1.isEmpty() != false) goto L_0x00de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00de, code lost:
        r7.c.remove();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e3, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.squareup.moshi.JsonAdapter<T> a(java.lang.reflect.Type r8, java.util.Set<? extends java.lang.annotation.Annotation> r9) {
        /*
            r7 = this;
            if (r8 != 0) goto L_0x000a
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r9 = "type == null"
            r8.<init>(r9)
            throw r8
        L_0x000a:
            if (r9 != 0) goto L_0x0014
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r9 = "annotations == null"
            r8.<init>(r9)
            throw r8
        L_0x0014:
            java.lang.reflect.Type r8 = com.squareup.moshi.a.a.a(r8)
            java.lang.Object r0 = r7.b(r8, r9)
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r1 = r7.d
            monitor-enter(r1)
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r2 = r7.d     // Catch:{ all -> 0x00e4 }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x00e4 }
            com.squareup.moshi.JsonAdapter r2 = (com.squareup.moshi.JsonAdapter) r2     // Catch:{ all -> 0x00e4 }
            if (r2 == 0) goto L_0x002b
            monitor-exit(r1)     // Catch:{ all -> 0x00e4 }
            return r2
        L_0x002b:
            monitor-exit(r1)     // Catch:{ all -> 0x00e4 }
            java.lang.ThreadLocal<java.util.List<com.squareup.moshi.m$b<?>>> r1 = r7.c
            java.lang.Object r1 = r1.get()
            java.util.List r1 = (java.util.List) r1
            r2 = 0
            if (r1 == 0) goto L_0x0050
            int r3 = r1.size()
            r4 = r2
        L_0x003c:
            if (r4 >= r3) goto L_0x005a
            java.lang.Object r5 = r1.get(r4)
            com.squareup.moshi.m$b r5 = (com.squareup.moshi.m.b) r5
            java.lang.Object r6 = r5.a
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x004d
            return r5
        L_0x004d:
            int r4 = r4 + 1
            goto L_0x003c
        L_0x0050:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.ThreadLocal<java.util.List<com.squareup.moshi.m$b<?>>> r3 = r7.c
            r3.set(r1)
        L_0x005a:
            com.squareup.moshi.m$b r3 = new com.squareup.moshi.m$b
            r3.<init>(r0)
            r1.add(r3)
            java.util.List<com.squareup.moshi.JsonAdapter$a> r4 = r7.b     // Catch:{ all -> 0x00ce }
            int r4 = r4.size()     // Catch:{ all -> 0x00ce }
        L_0x0068:
            if (r2 >= r4) goto L_0x009f
            java.util.List<com.squareup.moshi.JsonAdapter$a> r5 = r7.b     // Catch:{ all -> 0x00ce }
            java.lang.Object r5 = r5.get(r2)     // Catch:{ all -> 0x00ce }
            com.squareup.moshi.JsonAdapter$a r5 = (com.squareup.moshi.JsonAdapter.a) r5     // Catch:{ all -> 0x00ce }
            com.squareup.moshi.JsonAdapter r5 = r5.a(r8, r9, r7)     // Catch:{ all -> 0x00ce }
            if (r5 == 0) goto L_0x009c
            r3.a(r5)     // Catch:{ all -> 0x00ce }
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r8 = r7.d     // Catch:{ all -> 0x00ce }
            monitor-enter(r8)     // Catch:{ all -> 0x00ce }
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r9 = r7.d     // Catch:{ all -> 0x0099 }
            r9.put(r0, r5)     // Catch:{ all -> 0x0099 }
            monitor-exit(r8)     // Catch:{ all -> 0x0099 }
            int r8 = r1.size()
            int r8 = r8 + -1
            r1.remove(r8)
            boolean r8 = r1.isEmpty()
            if (r8 == 0) goto L_0x0098
            java.lang.ThreadLocal<java.util.List<com.squareup.moshi.m$b<?>>> r8 = r7.c
            r8.remove()
        L_0x0098:
            return r5
        L_0x0099:
            r9 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0099 }
            throw r9     // Catch:{ all -> 0x00ce }
        L_0x009c:
            int r2 = r2 + 1
            goto L_0x0068
        L_0x009f:
            int r0 = r1.size()
            int r0 = r0 + -1
            r1.remove(r0)
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L_0x00b3
            java.lang.ThreadLocal<java.util.List<com.squareup.moshi.m$b<?>>> r0 = r7.c
            r0.remove()
        L_0x00b3:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "No JsonAdapter for "
            r1.append(r2)
            java.lang.String r8 = com.squareup.moshi.a.a.a(r8, r9)
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>(r8)
            throw r0
        L_0x00ce:
            r8 = move-exception
            int r9 = r1.size()
            int r9 = r9 + -1
            r1.remove(r9)
            boolean r9 = r1.isEmpty()
            if (r9 == 0) goto L_0x00e3
            java.lang.ThreadLocal<java.util.List<com.squareup.moshi.m$b<?>>> r9 = r7.c
            r9.remove()
        L_0x00e3:
            throw r8
        L_0x00e4:
            r8 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00e4 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.m.a(java.lang.reflect.Type, java.util.Set):com.squareup.moshi.JsonAdapter");
    }

    public <T> JsonAdapter<T> a(com.squareup.moshi.JsonAdapter.a aVar, Type type, Set<? extends Annotation> set) {
        if (set == null) {
            throw new NullPointerException("annotations == null");
        }
        Type a2 = com.squareup.moshi.a.a.a(type);
        int indexOf = this.b.indexOf(aVar);
        if (indexOf == -1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to skip past unknown factory ");
            sb.append(aVar);
            throw new IllegalArgumentException(sb.toString());
        }
        int size = this.b.size();
        for (int i = indexOf + 1; i < size; i++) {
            JsonAdapter<T> a3 = ((com.squareup.moshi.JsonAdapter.a) this.b.get(i)).a(a2, set, this);
            if (a3 != null) {
                return a3;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("No next JsonAdapter for ");
        sb2.append(com.squareup.moshi.a.a.a(a2, set));
        throw new IllegalArgumentException(sb2.toString());
    }

    private Object b(Type type, Set<? extends Annotation> set) {
        if (set.isEmpty()) {
            return type;
        }
        return Arrays.asList(new Object[]{type, set});
    }
}
