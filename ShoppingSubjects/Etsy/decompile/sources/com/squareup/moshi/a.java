package com.squareup.moshi;

import com.squareup.moshi.JsonReader.Token;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: AdapterMethodsFactory */
final class a implements com.squareup.moshi.JsonAdapter.a {
    private final List<C0175a> a;
    private final List<C0175a> b;

    /* renamed from: com.squareup.moshi.a$a reason: collision with other inner class name */
    /* compiled from: AdapterMethodsFactory */
    static abstract class C0175a {
        final Type f;
        final Set<? extends Annotation> g;
        final Object h;
        final Method i;
        final int j;
        final JsonAdapter<?>[] k;
        final boolean l;

        C0175a(Type type, Set<? extends Annotation> set, Object obj, Method method, int i2, int i3, boolean z) {
            this.f = com.squareup.moshi.a.a.a(type);
            this.g = set;
            this.h = obj;
            this.i = method;
            this.j = i3;
            this.k = new JsonAdapter[(i2 - i3)];
            this.l = z;
        }

        public void a(m mVar, com.squareup.moshi.JsonAdapter.a aVar) {
            JsonAdapter<?> jsonAdapter;
            if (this.k.length > 0) {
                Type[] genericParameterTypes = this.i.getGenericParameterTypes();
                Annotation[][] parameterAnnotations = this.i.getParameterAnnotations();
                int length = genericParameterTypes.length;
                for (int i2 = this.j; i2 < length; i2++) {
                    Type type = ((ParameterizedType) genericParameterTypes[i2]).getActualTypeArguments()[0];
                    Set a = com.squareup.moshi.a.a.a(parameterAnnotations[i2]);
                    JsonAdapter<?>[] jsonAdapterArr = this.k;
                    int i3 = i2 - this.j;
                    if (!o.a(this.f, type) || !this.g.equals(a)) {
                        jsonAdapter = mVar.a(type, a);
                    } else {
                        jsonAdapter = mVar.a(aVar, type, a);
                    }
                    jsonAdapterArr[i3] = jsonAdapter;
                }
            }
        }

        public void a(m mVar, l lVar, Object obj) throws IOException, InvocationTargetException {
            throw new AssertionError();
        }

        public Object a(m mVar, JsonReader jsonReader) throws IOException, InvocationTargetException {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        public Object a(Object obj) throws InvocationTargetException {
            Object[] objArr = new Object[(this.k.length + 1)];
            objArr[0] = obj;
            System.arraycopy(this.k, 0, objArr, 1, this.k.length);
            try {
                return this.i.invoke(this.h, objArr);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }

        /* access modifiers changed from: protected */
        public Object a(Object obj, Object obj2) throws InvocationTargetException {
            Object[] objArr = new Object[(this.k.length + 2)];
            objArr[0] = obj;
            objArr[1] = obj2;
            System.arraycopy(this.k, 0, objArr, 2, this.k.length);
            try {
                return this.i.invoke(this.h, objArr);
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        }
    }

    a(List<C0175a> list, List<C0175a> list2) {
        this.a = list;
        this.b = list2;
    }

    public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, m mVar) {
        final C0175a a2 = a(this.a, type, set);
        final C0175a a3 = a(this.b, type, set);
        JsonAdapter jsonAdapter = null;
        if (a2 == null && a3 == null) {
            return null;
        }
        if (a2 == null || a3 == null) {
            try {
                jsonAdapter = mVar.a(this, type, set);
            } catch (IllegalArgumentException unused) {
                String str = a2 == null ? "@ToJson" : "@FromJson";
                StringBuilder sb = new StringBuilder();
                sb.append("No ");
                sb.append(str);
                sb.append(" adapter for ");
                sb.append(com.squareup.moshi.a.a.a(type, set));
                throw new IllegalArgumentException(sb.toString());
            }
        }
        final JsonAdapter jsonAdapter2 = jsonAdapter;
        if (a2 != null) {
            a2.a(mVar, (com.squareup.moshi.JsonAdapter.a) this);
        }
        if (a3 != null) {
            a3.a(mVar, (com.squareup.moshi.JsonAdapter.a) this);
        }
        final m mVar2 = mVar;
        final Set<? extends Annotation> set2 = set;
        final Type type2 = type;
        AnonymousClass1 r1 = new JsonAdapter<Object>() {
            public void toJson(l lVar, Object obj) throws IOException {
                if (a2 == null) {
                    jsonAdapter2.toJson(lVar, obj);
                } else if (a2.l || obj != null) {
                    try {
                        a2.a(mVar2, lVar, obj);
                    } catch (InvocationTargetException e2) {
                        Throwable cause = e2.getCause();
                        if (cause instanceof IOException) {
                            throw ((IOException) cause);
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(cause);
                        sb.append(" at ");
                        sb.append(lVar.m());
                        throw new JsonDataException(sb.toString(), cause);
                    }
                } else {
                    lVar.e();
                }
            }

            public Object fromJson(JsonReader jsonReader) throws IOException {
                if (a3 == null) {
                    return jsonAdapter2.fromJson(jsonReader);
                }
                if (a3.l || jsonReader.h() != Token.NULL) {
                    try {
                        return a3.a(mVar2, jsonReader);
                    } catch (InvocationTargetException e2) {
                        Throwable cause = e2.getCause();
                        if (cause instanceof IOException) {
                            throw ((IOException) cause);
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(cause);
                        sb.append(" at ");
                        sb.append(jsonReader.s());
                        throw new JsonDataException(sb.toString(), cause);
                    }
                } else {
                    jsonReader.m();
                    return null;
                }
            }

            public String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append("JsonAdapter");
                sb.append(set2);
                sb.append("(");
                sb.append(type2);
                sb.append(")");
                return sb.toString();
            }
        };
        return r1;
    }

    public static a a(Object obj) {
        Method[] declaredMethods;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Class<Object> cls = obj.getClass(); cls != Object.class; cls = cls.getSuperclass()) {
            for (Method method : cls.getDeclaredMethods()) {
                if (method.isAnnotationPresent(n.class)) {
                    C0175a a2 = a(obj, method);
                    C0175a a3 = a((List<C0175a>) arrayList, a2.f, a2.g);
                    if (a3 != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Conflicting @ToJson methods:\n    ");
                        sb.append(a3.i);
                        sb.append("\n    ");
                        sb.append(a2.i);
                        throw new IllegalArgumentException(sb.toString());
                    }
                    arrayList.add(a2);
                }
                if (method.isAnnotationPresent(c.class)) {
                    C0175a b2 = b(obj, method);
                    C0175a a4 = a((List<C0175a>) arrayList2, b2.f, b2.g);
                    if (a4 != null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Conflicting @FromJson methods:\n    ");
                        sb2.append(a4.i);
                        sb2.append("\n    ");
                        sb2.append(b2.i);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                    arrayList2.add(b2);
                }
            }
        }
        if (!arrayList.isEmpty() || !arrayList2.isEmpty()) {
            return new a(arrayList, arrayList2);
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append("Expected at least one @ToJson or @FromJson method on ");
        sb3.append(obj.getClass().getName());
        throw new IllegalArgumentException(sb3.toString());
    }

    static C0175a a(Object obj, Method method) {
        method.setAccessible(true);
        final Type genericReturnType = method.getGenericReturnType();
        final Type[] genericParameterTypes = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (genericParameterTypes.length >= 2 && genericParameterTypes[0] == l.class && genericReturnType == Void.TYPE && a(2, genericParameterTypes)) {
            AnonymousClass2 r0 = new C0175a(genericParameterTypes[1], com.squareup.moshi.a.a.a(parameterAnnotations[1]), obj, method, genericParameterTypes.length, 2, true) {
                public void a(m mVar, l lVar, Object obj) throws IOException, InvocationTargetException {
                    a((Object) lVar, obj);
                }
            };
            return r0;
        } else if (genericParameterTypes.length != 1 || genericReturnType == Void.TYPE) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected signature for ");
            sb.append(method);
            sb.append(".\n@ToJson method signatures may have one of the following structures:\n    <any access modifier> void toJson(JsonWriter writer, T value) throws <any>;\n    <any access modifier> void toJson(JsonWriter writer, T value, JsonAdapter<any> delegate, <any more delegates>) throws <any>;\n    <any access modifier> R toJson(T value) throws <any>;\n");
            throw new IllegalArgumentException(sb.toString());
        } else {
            final Set a2 = com.squareup.moshi.a.a.a((AnnotatedElement) method);
            final Set a3 = com.squareup.moshi.a.a.a(parameterAnnotations[0]);
            Set set = a3;
            Object obj2 = obj;
            Method method2 = method;
            AnonymousClass3 r02 = new C0175a(genericParameterTypes[0], set, obj2, method2, genericParameterTypes.length, 1, com.squareup.moshi.a.a.b(parameterAnnotations[0])) {
                private JsonAdapter<Object> e;

                public void a(m mVar, com.squareup.moshi.JsonAdapter.a aVar) {
                    JsonAdapter<Object> jsonAdapter;
                    super.a(mVar, aVar);
                    if (!o.a(genericParameterTypes[0], genericReturnType) || !a3.equals(a2)) {
                        jsonAdapter = mVar.a(genericReturnType, a2);
                    } else {
                        jsonAdapter = mVar.a(aVar, genericReturnType, a2);
                    }
                    this.e = jsonAdapter;
                }

                public void a(m mVar, l lVar, Object obj) throws IOException, InvocationTargetException {
                    this.e.toJson(lVar, a(obj));
                }
            };
            return r02;
        }
    }

    private static boolean a(int i, Type[] typeArr) {
        int length = typeArr.length;
        while (i < length) {
            if (!(typeArr[i] instanceof ParameterizedType) || typeArr[i].getRawType() != JsonAdapter.class) {
                return false;
            }
            i++;
        }
        return true;
    }

    static C0175a b(Object obj, Method method) {
        method.setAccessible(true);
        final Type genericReturnType = method.getGenericReturnType();
        final Set a2 = com.squareup.moshi.a.a.a((AnnotatedElement) method);
        final Type[] genericParameterTypes = method.getGenericParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (genericParameterTypes.length >= 1 && genericParameterTypes[0] == JsonReader.class && genericReturnType != Void.TYPE && a(1, genericParameterTypes)) {
            AnonymousClass4 r1 = new C0175a(genericReturnType, a2, obj, method, genericParameterTypes.length, 1, true) {
                public Object a(m mVar, JsonReader jsonReader) throws IOException, InvocationTargetException {
                    return a(jsonReader);
                }
            };
            return r1;
        } else if (genericParameterTypes.length != 1 || genericReturnType == Void.TYPE) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected signature for ");
            sb.append(method);
            sb.append(".\n@FromJson method signatures may have one of the following structures:\n    <any access modifier> R fromJson(JsonReader jsonReader) throws <any>;\n    <any access modifier> R fromJson(JsonReader jsonReader, JsonAdapter<any> delegate, <any more delegates>) throws <any>;\n    <any access modifier> R fromJson(T value) throws <any>;\n");
            throw new IllegalArgumentException(sb.toString());
        } else {
            final Set a3 = com.squareup.moshi.a.a.a(parameterAnnotations[0]);
            Type type = genericReturnType;
            Set set = a2;
            Object obj2 = obj;
            Method method2 = method;
            AnonymousClass5 r12 = new C0175a(type, set, obj2, method2, genericParameterTypes.length, 1, com.squareup.moshi.a.a.b(parameterAnnotations[0])) {
                JsonAdapter<Object> a;

                public void a(m mVar, com.squareup.moshi.JsonAdapter.a aVar) {
                    JsonAdapter<Object> jsonAdapter;
                    super.a(mVar, aVar);
                    if (!o.a(genericParameterTypes[0], genericReturnType) || !a3.equals(a2)) {
                        jsonAdapter = mVar.a(genericParameterTypes[0], a3);
                    } else {
                        jsonAdapter = mVar.a(aVar, genericParameterTypes[0], a3);
                    }
                    this.a = jsonAdapter;
                }

                public Object a(m mVar, JsonReader jsonReader) throws IOException, InvocationTargetException {
                    return a(this.a.fromJson(jsonReader));
                }
            };
            return r12;
        }
    }

    private static C0175a a(List<C0175a> list, Type type, Set<? extends Annotation> set) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            C0175a aVar = (C0175a) list.get(i);
            if (o.a(aVar.f, type) && aVar.g.equals(set)) {
                return aVar;
            }
        }
        return null;
    }
}
