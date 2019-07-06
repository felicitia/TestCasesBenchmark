package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.a;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

abstract class CollectionJsonAdapter<C extends Collection<T>, T> extends JsonAdapter<C> {
    public static final a FACTORY = new a() {
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, m mVar) {
            Class<Set> d = o.d(type);
            if (!set.isEmpty()) {
                return null;
            }
            if (d == List.class || d == Collection.class) {
                return CollectionJsonAdapter.a(type, mVar).nullSafe();
            }
            if (d == Set.class) {
                return CollectionJsonAdapter.b(type, mVar).nullSafe();
            }
            return null;
        }
    };
    private final JsonAdapter<T> elementAdapter;

    /* access modifiers changed from: 0000 */
    public abstract C a();

    private CollectionJsonAdapter(JsonAdapter<T> jsonAdapter) {
        this.elementAdapter = jsonAdapter;
    }

    static <T> JsonAdapter<Collection<T>> a(Type type, m mVar) {
        return new CollectionJsonAdapter<Collection<T>, T>(mVar.a(o.a(type, Collection.class))) {
            public /* synthetic */ Object fromJson(JsonReader jsonReader) throws IOException {
                return CollectionJsonAdapter.super.fromJson(jsonReader);
            }

            public /* synthetic */ void toJson(l lVar, Object obj) throws IOException {
                CollectionJsonAdapter.super.toJson(lVar, (Collection) obj);
            }

            /* access modifiers changed from: 0000 */
            public Collection<T> a() {
                return new ArrayList();
            }
        };
    }

    static <T> JsonAdapter<Set<T>> b(Type type, m mVar) {
        return new CollectionJsonAdapter<Set<T>, T>(mVar.a(o.a(type, Collection.class))) {
            public /* synthetic */ Object fromJson(JsonReader jsonReader) throws IOException {
                return CollectionJsonAdapter.super.fromJson(jsonReader);
            }

            public /* synthetic */ void toJson(l lVar, Object obj) throws IOException {
                CollectionJsonAdapter.super.toJson(lVar, (Set) obj);
            }

            /* access modifiers changed from: 0000 */
            /* renamed from: b */
            public Set<T> a() {
                return new LinkedHashSet();
            }
        };
    }

    /* renamed from: a */
    public C fromJson(JsonReader jsonReader) throws IOException {
        C a = a();
        jsonReader.c();
        while (jsonReader.g()) {
            a.add(this.elementAdapter.fromJson(jsonReader));
        }
        jsonReader.d();
        return a;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=C, code=C<java.lang.Object>, for r4v0, types: [C, C<java.lang.Object>, java.util.Collection] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void toJson(com.squareup.moshi.l r3, C<java.lang.Object> r4) throws java.io.IOException {
        /*
            r2 = this;
            r3.a()
            java.util.Iterator r4 = r4.iterator()
        L_0x0007:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0017
            java.lang.Object r0 = r4.next()
            com.squareup.moshi.JsonAdapter<T> r1 = r2.elementAdapter
            r1.toJson(r3, r0)
            goto L_0x0007
        L_0x0017:
            r3.b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.CollectionJsonAdapter.toJson(com.squareup.moshi.l, java.util.Collection):void");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.elementAdapter);
        sb.append(".collection()");
        return sb.toString();
    }
}
