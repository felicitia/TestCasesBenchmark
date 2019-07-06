package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.a;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;

final class ArrayJsonAdapter extends JsonAdapter<Object> {
    public static final a FACTORY = new a() {
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, m mVar) {
            Type f = o.f(type);
            if (f != null && set.isEmpty()) {
                return new ArrayJsonAdapter(o.d(f), mVar.a(f)).nullSafe();
            }
            return null;
        }
    };
    private final JsonAdapter<Object> elementAdapter;
    private final Class<?> elementClass;

    ArrayJsonAdapter(Class<?> cls, JsonAdapter<Object> jsonAdapter) {
        this.elementClass = cls;
        this.elementAdapter = jsonAdapter;
    }

    public Object fromJson(JsonReader jsonReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        jsonReader.c();
        while (jsonReader.g()) {
            arrayList.add(this.elementAdapter.fromJson(jsonReader));
        }
        jsonReader.d();
        Object newInstance = Array.newInstance(this.elementClass, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    public void toJson(l lVar, Object obj) throws IOException {
        lVar.a();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.elementAdapter.toJson(lVar, Array.get(obj, i));
        }
        lVar.b();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.elementAdapter);
        sb.append(".array()");
        return sb.toString();
    }
}
