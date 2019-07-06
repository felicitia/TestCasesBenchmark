package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.a;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class MapJsonAdapter<K, V> extends JsonAdapter<Map<K, V>> {
    public static final a FACTORY = new a() {
        public JsonAdapter<?> a(Type type, Set<? extends Annotation> set, m mVar) {
            if (!set.isEmpty()) {
                return null;
            }
            Class<Map> d = o.d(type);
            if (d != Map.class) {
                return null;
            }
            Type[] b = o.b(type, d);
            return new MapJsonAdapter(mVar, b[0], b[1]).nullSafe();
        }
    };
    private final JsonAdapter<K> keyAdapter;
    private final JsonAdapter<V> valueAdapter;

    MapJsonAdapter(m mVar, Type type, Type type2) {
        this.keyAdapter = mVar.a(type);
        this.valueAdapter = mVar.a(type2);
    }

    /* renamed from: a */
    public void toJson(l lVar, Map<K, V> map) throws IOException {
        lVar.c();
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Map key is null at ");
                sb.append(lVar.m());
                throw new JsonDataException(sb.toString());
            }
            lVar.l();
            this.keyAdapter.toJson(lVar, entry.getKey());
            this.valueAdapter.toJson(lVar, entry.getValue());
        }
        lVar.d();
    }

    /* renamed from: a */
    public Map<K, V> fromJson(JsonReader jsonReader) throws IOException {
        LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
        jsonReader.e();
        while (jsonReader.g()) {
            jsonReader.t();
            Object fromJson = this.keyAdapter.fromJson(jsonReader);
            Object fromJson2 = this.valueAdapter.fromJson(jsonReader);
            Object put = linkedHashTreeMap.put(fromJson, fromJson2);
            if (put != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Map key '");
                sb.append(fromJson);
                sb.append("' has multiple values at path ");
                sb.append(jsonReader.s());
                sb.append(": ");
                sb.append(put);
                sb.append(" and ");
                sb.append(fromJson2);
                throw new JsonDataException(sb.toString());
            }
        }
        jsonReader.f();
        return linkedHashTreeMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JsonAdapter(");
        sb.append(this.keyAdapter);
        sb.append("=");
        sb.append(this.valueAdapter);
        sb.append(")");
        return sb.toString();
    }
}
