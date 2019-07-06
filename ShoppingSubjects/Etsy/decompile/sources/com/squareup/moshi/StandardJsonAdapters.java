package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.a;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class StandardJsonAdapters {
    public static final a a = new a() {
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, m mVar) {
            if (!set.isEmpty()) {
                return null;
            }
            if (type == Boolean.TYPE) {
                return StandardJsonAdapters.b;
            }
            if (type == Byte.TYPE) {
                return StandardJsonAdapters.c;
            }
            if (type == Character.TYPE) {
                return StandardJsonAdapters.d;
            }
            if (type == Double.TYPE) {
                return StandardJsonAdapters.e;
            }
            if (type == Float.TYPE) {
                return StandardJsonAdapters.f;
            }
            if (type == Integer.TYPE) {
                return StandardJsonAdapters.g;
            }
            if (type == Long.TYPE) {
                return StandardJsonAdapters.h;
            }
            if (type == Short.TYPE) {
                return StandardJsonAdapters.i;
            }
            if (type == Boolean.class) {
                return StandardJsonAdapters.b.nullSafe();
            }
            if (type == Byte.class) {
                return StandardJsonAdapters.c.nullSafe();
            }
            if (type == Character.class) {
                return StandardJsonAdapters.d.nullSafe();
            }
            if (type == Double.class) {
                return StandardJsonAdapters.e.nullSafe();
            }
            if (type == Float.class) {
                return StandardJsonAdapters.f.nullSafe();
            }
            if (type == Integer.class) {
                return StandardJsonAdapters.g.nullSafe();
            }
            if (type == Long.class) {
                return StandardJsonAdapters.h.nullSafe();
            }
            if (type == Short.class) {
                return StandardJsonAdapters.i.nullSafe();
            }
            if (type == String.class) {
                return StandardJsonAdapters.j.nullSafe();
            }
            if (type == Object.class) {
                return new ObjectJsonAdapter(mVar).nullSafe();
            }
            Class d = o.d(type);
            e eVar = (e) d.getAnnotation(e.class);
            if (eVar != null && eVar.a()) {
                return StandardJsonAdapters.a(mVar, type, d);
            }
            if (d.isEnum()) {
                return new EnumJsonAdapter(d).nullSafe();
            }
            return null;
        }
    };
    static final JsonAdapter<Boolean> b = new JsonAdapter<Boolean>() {
        public String toString() {
            return "JsonAdapter(Boolean)";
        }

        /* renamed from: a */
        public Boolean fromJson(JsonReader jsonReader) throws IOException {
            return Boolean.valueOf(jsonReader.l());
        }

        /* renamed from: a */
        public void toJson(l lVar, Boolean bool) throws IOException {
            lVar.a(bool.booleanValue());
        }
    };
    static final JsonAdapter<Byte> c = new JsonAdapter<Byte>() {
        public String toString() {
            return "JsonAdapter(Byte)";
        }

        /* renamed from: a */
        public Byte fromJson(JsonReader jsonReader) throws IOException {
            return Byte.valueOf((byte) StandardJsonAdapters.a(jsonReader, "a byte", -128, 255));
        }

        /* renamed from: a */
        public void toJson(l lVar, Byte b) throws IOException {
            lVar.a((long) (b.intValue() & 255));
        }
    };
    static final JsonAdapter<Character> d = new JsonAdapter<Character>() {
        public String toString() {
            return "JsonAdapter(Character)";
        }

        /* renamed from: a */
        public Character fromJson(JsonReader jsonReader) throws IOException {
            String k = jsonReader.k();
            if (k.length() <= 1) {
                return Character.valueOf(k.charAt(0));
            }
            StringBuilder sb = new StringBuilder();
            sb.append('\"');
            sb.append(k);
            sb.append('\"');
            throw new JsonDataException(String.format("Expected %s but was %s at path %s", new Object[]{"a char", sb.toString(), jsonReader.s()}));
        }

        /* renamed from: a */
        public void toJson(l lVar, Character ch) throws IOException {
            lVar.c(ch.toString());
        }
    };
    static final JsonAdapter<Double> e = new JsonAdapter<Double>() {
        public String toString() {
            return "JsonAdapter(Double)";
        }

        /* renamed from: a */
        public Double fromJson(JsonReader jsonReader) throws IOException {
            return Double.valueOf(jsonReader.n());
        }

        /* renamed from: a */
        public void toJson(l lVar, Double d) throws IOException {
            lVar.a(d.doubleValue());
        }
    };
    static final JsonAdapter<Float> f = new JsonAdapter<Float>() {
        public String toString() {
            return "JsonAdapter(Float)";
        }

        /* renamed from: a */
        public Float fromJson(JsonReader jsonReader) throws IOException {
            float n = (float) jsonReader.n();
            if (jsonReader.a() || !Float.isInfinite(n)) {
                return Float.valueOf(n);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("JSON forbids NaN and infinities: ");
            sb.append(n);
            sb.append(" at path ");
            sb.append(jsonReader.s());
            throw new JsonDataException(sb.toString());
        }

        /* renamed from: a */
        public void toJson(l lVar, Float f) throws IOException {
            if (f == null) {
                throw new NullPointerException();
            }
            lVar.a((Number) f);
        }
    };
    static final JsonAdapter<Integer> g = new JsonAdapter<Integer>() {
        public String toString() {
            return "JsonAdapter(Integer)";
        }

        /* renamed from: a */
        public Integer fromJson(JsonReader jsonReader) throws IOException {
            return Integer.valueOf(jsonReader.p());
        }

        /* renamed from: a */
        public void toJson(l lVar, Integer num) throws IOException {
            lVar.a((long) num.intValue());
        }
    };
    static final JsonAdapter<Long> h = new JsonAdapter<Long>() {
        public String toString() {
            return "JsonAdapter(Long)";
        }

        /* renamed from: a */
        public Long fromJson(JsonReader jsonReader) throws IOException {
            return Long.valueOf(jsonReader.o());
        }

        /* renamed from: a */
        public void toJson(l lVar, Long l) throws IOException {
            lVar.a(l.longValue());
        }
    };
    static final JsonAdapter<Short> i = new JsonAdapter<Short>() {
        public String toString() {
            return "JsonAdapter(Short)";
        }

        /* renamed from: a */
        public Short fromJson(JsonReader jsonReader) throws IOException {
            return Short.valueOf((short) StandardJsonAdapters.a(jsonReader, "a short", -32768, 32767));
        }

        /* renamed from: a */
        public void toJson(l lVar, Short sh) throws IOException {
            lVar.a((long) sh.intValue());
        }
    };
    static final JsonAdapter<String> j = new JsonAdapter<String>() {
        public String toString() {
            return "JsonAdapter(String)";
        }

        /* renamed from: a */
        public String fromJson(JsonReader jsonReader) throws IOException {
            return jsonReader.k();
        }

        /* renamed from: a */
        public void toJson(l lVar, String str) throws IOException {
            lVar.c(str);
        }
    };

    static final class EnumJsonAdapter<T extends Enum<T>> extends JsonAdapter<T> {
        private final T[] constants;
        private final Class<T> enumType;
        private final String[] nameStrings;
        private final JsonReader.a options;

        EnumJsonAdapter(Class<T> cls) {
            this.enumType = cls;
            try {
                this.constants = (Enum[]) cls.getEnumConstants();
                this.nameStrings = new String[this.constants.length];
                for (int i = 0; i < this.constants.length; i++) {
                    T t = this.constants[i];
                    d dVar = (d) cls.getField(t.name()).getAnnotation(d.class);
                    this.nameStrings[i] = dVar != null ? dVar.a() : t.name();
                }
                this.options = JsonReader.a.a(this.nameStrings);
            } catch (NoSuchFieldException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Missing field in ");
                sb.append(cls.getName());
                throw new AssertionError(sb.toString(), e);
            }
        }

        /* renamed from: a */
        public T fromJson(JsonReader jsonReader) throws IOException {
            int b = jsonReader.b(this.options);
            if (b != -1) {
                return this.constants[b];
            }
            String k = jsonReader.k();
            StringBuilder sb = new StringBuilder();
            sb.append("Expected one of ");
            sb.append(Arrays.asList(this.nameStrings));
            sb.append(" but was ");
            sb.append(k);
            sb.append(" at path ");
            sb.append(jsonReader.s());
            throw new JsonDataException(sb.toString());
        }

        /* renamed from: a */
        public void toJson(l lVar, T t) throws IOException {
            lVar.c(this.nameStrings[t.ordinal()]);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("JsonAdapter(");
            sb.append(this.enumType.getName());
            sb.append(")");
            return sb.toString();
        }
    }

    static final class ObjectJsonAdapter extends JsonAdapter<Object> {
        private final JsonAdapter<Boolean> booleanAdapter;
        private final JsonAdapter<Double> doubleAdapter;
        private final JsonAdapter<List> listJsonAdapter;
        private final JsonAdapter<Map> mapAdapter;
        private final m moshi;
        private final JsonAdapter<String> stringAdapter;

        public String toString() {
            return "JsonAdapter(Object)";
        }

        ObjectJsonAdapter(m mVar) {
            this.moshi = mVar;
            this.listJsonAdapter = mVar.a(List.class);
            this.mapAdapter = mVar.a(Map.class);
            this.stringAdapter = mVar.a(String.class);
            this.doubleAdapter = mVar.a(Double.class);
            this.booleanAdapter = mVar.a(Boolean.class);
        }

        public Object fromJson(JsonReader jsonReader) throws IOException {
            switch (jsonReader.h()) {
                case BEGIN_ARRAY:
                    return this.listJsonAdapter.fromJson(jsonReader);
                case BEGIN_OBJECT:
                    return this.mapAdapter.fromJson(jsonReader);
                case STRING:
                    return this.stringAdapter.fromJson(jsonReader);
                case NUMBER:
                    return this.doubleAdapter.fromJson(jsonReader);
                case BOOLEAN:
                    return this.booleanAdapter.fromJson(jsonReader);
                case NULL:
                    return jsonReader.m();
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Expected a value but was ");
                    sb.append(jsonReader.h());
                    sb.append(" at path ");
                    sb.append(jsonReader.s());
                    throw new IllegalStateException(sb.toString());
            }
        }

        public void toJson(l lVar, Object obj) throws IOException {
            Class<Object> cls = obj.getClass();
            if (cls == Object.class) {
                lVar.c();
                lVar.d();
                return;
            }
            this.moshi.a(a(cls), com.squareup.moshi.a.a.a).toJson(lVar, obj);
        }

        private Class<?> a(Class<?> cls) {
            if (Map.class.isAssignableFrom(cls)) {
                return Map.class;
            }
            return Collection.class.isAssignableFrom(cls) ? Collection.class : cls;
        }
    }

    static int a(JsonReader jsonReader, String str, int i2, int i3) throws IOException {
        int p = jsonReader.p();
        if (p >= i2 && p <= i3) {
            return p;
        }
        throw new JsonDataException(String.format("Expected %s but was %s at path %s", new Object[]{str, Integer.valueOf(p), jsonReader.s()}));
    }

    static JsonAdapter<?> a(m mVar, Type type, Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.getName().replace("$", "_"));
        sb.append("JsonAdapter");
        try {
            Class cls2 = Class.forName(sb.toString(), true, cls.getClassLoader());
            if (type instanceof ParameterizedType) {
                Constructor declaredConstructor = cls2.getDeclaredConstructor(new Class[]{m.class, Type[].class});
                declaredConstructor.setAccessible(true);
                return (JsonAdapter) declaredConstructor.newInstance(new Object[]{mVar, ((ParameterizedType) type).getActualTypeArguments()});
            }
            Constructor declaredConstructor2 = cls2.getDeclaredConstructor(new Class[]{m.class});
            declaredConstructor2.setAccessible(true);
            return (JsonAdapter) declaredConstructor2.newInstance(new Object[]{mVar});
        } catch (ClassNotFoundException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to find the generated JsonAdapter class for ");
            sb2.append(cls);
            throw new RuntimeException(sb2.toString(), e2);
        } catch (NoSuchMethodException e3) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Failed to find the generated JsonAdapter constructor for ");
            sb3.append(cls);
            throw new RuntimeException(sb3.toString(), e3);
        } catch (IllegalAccessException e4) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Failed to access the generated JsonAdapter for ");
            sb4.append(cls);
            throw new RuntimeException(sb4.toString(), e4);
        } catch (InvocationTargetException e5) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Failed to construct the generated JsonAdapter for ");
            sb5.append(cls);
            throw new RuntimeException(sb5.toString(), e5);
        } catch (InstantiationException e6) {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Failed to instantiate the generated JsonAdapter for ");
            sb6.append(cls);
            throw new RuntimeException(sb6.toString(), e6);
        }
    }
}
