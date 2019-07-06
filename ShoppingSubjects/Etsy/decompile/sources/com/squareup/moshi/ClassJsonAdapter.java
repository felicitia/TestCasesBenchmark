package com.squareup.moshi;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

final class ClassJsonAdapter<T> extends JsonAdapter<T> {
    public static final com.squareup.moshi.JsonAdapter.a FACTORY = new com.squareup.moshi.JsonAdapter.a() {
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, m mVar) {
            if (!(type instanceof Class) && !(type instanceof ParameterizedType)) {
                return null;
            }
            Class d = o.d(type);
            if (d.isInterface() || d.isEnum()) {
                return null;
            }
            if (com.squareup.moshi.a.a.a(d) && !o.g(d)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Platform ");
                sb.append(com.squareup.moshi.a.a.a(type, set));
                sb.append(" requires explicit JsonAdapter to be registered");
                throw new IllegalArgumentException(sb.toString());
            } else if (!set.isEmpty()) {
                return null;
            } else {
                if (d.isAnonymousClass()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Cannot serialize anonymous class ");
                    sb2.append(d.getName());
                    throw new IllegalArgumentException(sb2.toString());
                } else if (d.isLocalClass()) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Cannot serialize local class ");
                    sb3.append(d.getName());
                    throw new IllegalArgumentException(sb3.toString());
                } else if (d.getEnclosingClass() != null && !Modifier.isStatic(d.getModifiers())) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Cannot serialize non-static nested class ");
                    sb4.append(d.getName());
                    throw new IllegalArgumentException(sb4.toString());
                } else if (Modifier.isAbstract(d.getModifiers())) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Cannot serialize abstract class ");
                    sb5.append(d.getName());
                    throw new IllegalArgumentException(sb5.toString());
                } else {
                    b a = b.a(d);
                    TreeMap treeMap = new TreeMap();
                    while (type != Object.class) {
                        a(mVar, type, (Map<String, a<?>>) treeMap);
                        type = o.e(type);
                    }
                    return new ClassJsonAdapter(a, treeMap).nullSafe();
                }
            }
        }

        private void a(m mVar, Type type, Map<String, a<?>> map) {
            Class d = o.d(type);
            boolean a = com.squareup.moshi.a.a.a(d);
            Field[] declaredFields = d.getDeclaredFields();
            int length = declaredFields.length;
            for (int i = 0; i < length; i++) {
                Field field = declaredFields[i];
                if (a(a, field.getModifiers())) {
                    JsonAdapter a2 = mVar.a(com.squareup.moshi.a.a.a(type, d, field.getGenericType()), com.squareup.moshi.a.a.a((AnnotatedElement) field));
                    field.setAccessible(true);
                    d dVar = (d) field.getAnnotation(d.class);
                    String a3 = dVar != null ? dVar.a() : field.getName();
                    a aVar = new a(a3, field, a2);
                    a aVar2 = (a) map.put(a3, aVar);
                    if (aVar2 != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Conflicting fields:\n    ");
                        sb.append(aVar2.b);
                        sb.append("\n    ");
                        sb.append(aVar.b);
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
            }
        }

        private boolean a(boolean z, int i) {
            boolean z2 = false;
            if (Modifier.isStatic(i) || Modifier.isTransient(i)) {
                return false;
            }
            if (Modifier.isPublic(i) || Modifier.isProtected(i) || !z) {
                z2 = true;
            }
            return z2;
        }
    };
    private final b<T> classFactory;
    private final a<?>[] fieldsArray;
    private final com.squareup.moshi.JsonReader.a options;

    static class a<T> {
        final String a;
        final Field b;
        final JsonAdapter<T> c;

        a(String str, Field field, JsonAdapter<T> jsonAdapter) {
            this.a = str;
            this.b = field;
            this.c = jsonAdapter;
        }

        /* access modifiers changed from: 0000 */
        public void a(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException {
            this.b.set(obj, this.c.fromJson(jsonReader));
        }

        /* access modifiers changed from: 0000 */
        public void a(l lVar, Object obj) throws IllegalAccessException, IOException {
            this.c.toJson(lVar, this.b.get(obj));
        }
    }

    ClassJsonAdapter(b<T> bVar, Map<String, a<?>> map) {
        this.classFactory = bVar;
        this.fieldsArray = (a[]) map.values().toArray(new a[map.size()]);
        this.options = com.squareup.moshi.JsonReader.a.a((String[]) map.keySet().toArray(new String[map.size()]));
    }

    public T fromJson(JsonReader jsonReader) throws IOException {
        try {
            T a2 = this.classFactory.a();
            try {
                jsonReader.e();
                while (jsonReader.g()) {
                    int a3 = jsonReader.a(this.options);
                    if (a3 == -1) {
                        jsonReader.j();
                        jsonReader.q();
                    } else {
                        this.fieldsArray[a3].a(jsonReader, (Object) a2);
                    }
                }
                jsonReader.f();
                return a2;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            Throwable targetException = e2.getTargetException();
            if (targetException instanceof RuntimeException) {
                throw ((RuntimeException) targetException);
            } else if (targetException instanceof Error) {
                throw ((Error) targetException);
            } else {
                throw new RuntimeException(targetException);
            }
        } catch (IllegalAccessException unused2) {
            throw new AssertionError();
        }
    }

    public void toJson(l lVar, T t) throws IOException {
        a<?>[] aVarArr;
        try {
            lVar.c();
            for (a<?> aVar : this.fieldsArray) {
                lVar.b(aVar.a);
                aVar.a(lVar, (Object) t);
            }
            lVar.d();
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JsonAdapter(");
        sb.append(this.classFactory);
        sb.append(")");
        return sb.toString();
    }
}
