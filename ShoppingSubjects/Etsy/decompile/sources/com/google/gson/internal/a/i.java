package com.google.gson.internal.a;

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.d;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.c;
import com.google.gson.internal.e;
import com.google.gson.internal.f;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* compiled from: ReflectiveTypeAdapterFactory */
public final class i implements r {
    private final com.google.gson.internal.b a;
    private final d b;
    private final c c;
    private final d d;

    /* compiled from: ReflectiveTypeAdapterFactory */
    public static final class a<T> extends q<T> {
        private final e<T> a;
        private final Map<String, b> b;

        a(e<T> eVar, Map<String, b> map) {
            this.a = eVar;
            this.b = map;
        }

        public T b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            T a2 = this.a.a();
            try {
                aVar.c();
                while (aVar.e()) {
                    b bVar = (b) this.b.get(aVar.g());
                    if (bVar != null) {
                        if (bVar.j) {
                            bVar.a(aVar, (Object) a2);
                        }
                    }
                    aVar.n();
                }
                aVar.d();
                return a2;
            } catch (IllegalStateException e) {
                throw new JsonSyntaxException((Throwable) e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void a(com.google.gson.stream.b bVar, T t) throws IOException {
            if (t == null) {
                bVar.f();
                return;
            }
            bVar.d();
            try {
                for (b bVar2 : this.b.values()) {
                    if (bVar2.a(t)) {
                        bVar.a(bVar2.h);
                        bVar2.a(bVar, (Object) t);
                    }
                }
                bVar.e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    /* compiled from: ReflectiveTypeAdapterFactory */
    static abstract class b {
        final String h;
        final boolean i;
        final boolean j;

        /* access modifiers changed from: 0000 */
        public abstract void a(com.google.gson.stream.a aVar, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: 0000 */
        public abstract void a(com.google.gson.stream.b bVar, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: 0000 */
        public abstract boolean a(Object obj) throws IOException, IllegalAccessException;

        protected b(String str, boolean z, boolean z2) {
            this.h = str;
            this.i = z;
            this.j = z2;
        }
    }

    public i(com.google.gson.internal.b bVar, d dVar, c cVar, d dVar2) {
        this.a = bVar;
        this.b = dVar;
        this.c = cVar;
        this.d = dVar2;
    }

    public boolean a(Field field, boolean z) {
        return a(field, z, this.c);
    }

    static boolean a(Field field, boolean z, c cVar) {
        return !cVar.a(field.getType(), z) && !cVar.a(field, z);
    }

    private List<String> a(Field field) {
        com.google.gson.annotations.b bVar = (com.google.gson.annotations.b) field.getAnnotation(com.google.gson.annotations.b.class);
        if (bVar == null) {
            return Collections.singletonList(this.b.translateName(field));
        }
        String a2 = bVar.a();
        String[] b2 = bVar.b();
        if (b2.length == 0) {
            return Collections.singletonList(a2);
        }
        ArrayList arrayList = new ArrayList(b2.length + 1);
        arrayList.add(a2);
        for (String add : b2) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public <T> q<T> a(com.google.gson.e eVar, com.google.gson.a.a<T> aVar) {
        Class a2 = aVar.a();
        if (!Object.class.isAssignableFrom(a2)) {
            return null;
        }
        return new a(this.a.a(aVar), a(eVar, aVar, a2));
    }

    private b a(com.google.gson.e eVar, Field field, String str, com.google.gson.a.a<?> aVar, boolean z, boolean z2) {
        final com.google.gson.e eVar2 = eVar;
        final com.google.gson.a.a<?> aVar2 = aVar;
        final boolean a2 = f.a(aVar.a());
        final Field field2 = field;
        JsonAdapter jsonAdapter = (JsonAdapter) field2.getAnnotation(JsonAdapter.class);
        q a3 = jsonAdapter != null ? this.d.a(this.a, eVar2, aVar2, jsonAdapter) : null;
        final boolean z3 = a3 != null;
        if (a3 == null) {
            a3 = eVar2.a(aVar2);
        }
        final q qVar = a3;
        AnonymousClass1 r0 = new b(str, z, z2) {
            /* access modifiers changed from: 0000 */
            public void a(com.google.gson.stream.b bVar, Object obj) throws IOException, IllegalAccessException {
                q qVar;
                Object obj2 = field2.get(obj);
                if (z3) {
                    qVar = qVar;
                } else {
                    qVar = new m(eVar2, qVar, aVar2.b());
                }
                qVar.a(bVar, obj2);
            }

            /* access modifiers changed from: 0000 */
            public void a(com.google.gson.stream.a aVar, Object obj) throws IOException, IllegalAccessException {
                Object b2 = qVar.b(aVar);
                if (b2 != null || !a2) {
                    field2.set(obj, b2);
                }
            }

            public boolean a(Object obj) throws IOException, IllegalAccessException {
                boolean z = false;
                if (!this.i) {
                    return false;
                }
                if (field2.get(obj) != obj) {
                    z = true;
                }
                return z;
            }
        };
        return r0;
    }

    private Map<String, b> a(com.google.gson.e eVar, com.google.gson.a.a<?> aVar, Class<?> cls) {
        i iVar = this;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b2 = aVar.b();
        com.google.gson.a.a<?> aVar2 = aVar;
        Class<?> cls2 = cls;
        while (cls2 != Object.class) {
            Field[] declaredFields = cls2.getDeclaredFields();
            int length = declaredFields.length;
            int i = 0;
            boolean z = 0;
            while (i < length) {
                Field field = declaredFields[i];
                boolean a2 = iVar.a(field, true);
                boolean a3 = iVar.a(field, z);
                if (a2 || a3) {
                    field.setAccessible(true);
                    Type a4 = C$Gson$Types.a(aVar2.b(), cls2, field.getGenericType());
                    List a5 = iVar.a(field);
                    int size = a5.size();
                    int i2 = z;
                    boolean z2 = a2;
                    b bVar = null;
                    boolean z3 = z2;
                    while (i2 < size) {
                        String str = (String) a5.get(i2);
                        boolean z4 = i2 != 0 ? false : z3;
                        i iVar2 = iVar;
                        b bVar2 = bVar;
                        int i3 = i2;
                        int i4 = size;
                        List list = a5;
                        Type type = a4;
                        Field field2 = field;
                        bVar = bVar2 == null ? (b) linkedHashMap.put(str, iVar2.a(eVar, field, str, com.google.gson.a.a.a(a4), z4, a3)) : bVar2;
                        z3 = z4;
                        a4 = type;
                        size = i4;
                        a5 = list;
                        field = field2;
                        iVar = this;
                        i2 = i3 + 1;
                    }
                    b bVar3 = bVar;
                    if (bVar3 != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(b2);
                        sb.append(" declares multiple JSON fields named ");
                        sb.append(bVar3.h);
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
                i++;
                iVar = this;
                z = 0;
            }
            aVar2 = com.google.gson.a.a.a(C$Gson$Types.a(aVar2.b(), cls2, cls2.getGenericSuperclass()));
            cls2 = aVar2.a();
            iVar = this;
        }
        return linkedHashMap;
    }
}
