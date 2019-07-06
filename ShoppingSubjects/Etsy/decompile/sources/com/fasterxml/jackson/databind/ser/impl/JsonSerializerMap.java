package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.SerializerCache.TypeKey;
import java.util.Map;
import java.util.Map.Entry;

public class JsonSerializerMap {
    private final a[] _buckets;
    private final int _size;

    private static final class a {
        public final TypeKey a;
        public final JsonSerializer<Object> b;
        public final a c;

        public a(a aVar, TypeKey typeKey, JsonSerializer<Object> jsonSerializer) {
            this.c = aVar;
            this.a = typeKey;
            this.b = jsonSerializer;
        }
    }

    private static final int findSize(int i) {
        int i2 = 8;
        while (i2 < (i <= 64 ? i + i : i + (i >> 2))) {
            i2 += i2;
        }
        return i2;
    }

    public JsonSerializerMap(Map<TypeKey, JsonSerializer<Object>> map) {
        int findSize = findSize(map.size());
        this._size = findSize;
        int i = findSize - 1;
        a[] aVarArr = new a[findSize];
        for (Entry entry : map.entrySet()) {
            TypeKey typeKey = (TypeKey) entry.getKey();
            int hashCode = typeKey.hashCode() & i;
            aVarArr[hashCode] = new a(aVarArr[hashCode], typeKey, (JsonSerializer) entry.getValue());
        }
        this._buckets = aVarArr;
    }

    public int size() {
        return this._size;
    }

    public JsonSerializer<Object> find(TypeKey typeKey) {
        a aVar = this._buckets[typeKey.hashCode() & (this._buckets.length - 1)];
        if (aVar == null) {
            return null;
        }
        if (typeKey.equals(aVar.a)) {
            return aVar.b;
        }
        do {
            aVar = aVar.c;
            if (aVar == null) {
                return null;
            }
        } while (!typeKey.equals(aVar.a));
        return aVar.b;
    }
}
