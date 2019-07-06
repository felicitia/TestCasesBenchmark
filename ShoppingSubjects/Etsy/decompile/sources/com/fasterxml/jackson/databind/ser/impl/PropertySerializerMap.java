package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public abstract class PropertySerializerMap {

    public static final class SerializerAndMapResult {
        public final PropertySerializerMap map;
        public final JsonSerializer<Object> serializer;

        public SerializerAndMapResult(JsonSerializer<Object> jsonSerializer, PropertySerializerMap propertySerializerMap) {
            this.serializer = jsonSerializer;
            this.map = propertySerializerMap;
        }
    }

    private static final class a extends PropertySerializerMap {
        private final Class<?> a;
        private final Class<?> b;
        private final JsonSerializer<Object> c;
        private final JsonSerializer<Object> d;

        public a(Class<?> cls, JsonSerializer<Object> jsonSerializer, Class<?> cls2, JsonSerializer<Object> jsonSerializer2) {
            this.a = cls;
            this.c = jsonSerializer;
            this.b = cls2;
            this.d = jsonSerializer2;
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            if (cls == this.a) {
                return this.c;
            }
            if (cls == this.b) {
                return this.d;
            }
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new c(new e[]{new e(this.a, this.c), new e(this.b, this.d)});
        }
    }

    private static final class b extends PropertySerializerMap {
        protected static final b a = new b();

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            return null;
        }

        private b() {
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new d(cls, jsonSerializer);
        }
    }

    private static final class c extends PropertySerializerMap {
        private final e[] a;

        public c(e[] eVarArr) {
            this.a = eVarArr;
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            for (e eVar : this.a) {
                if (eVar.a == cls) {
                    return eVar.b;
                }
            }
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            int length = this.a.length;
            if (length == 8) {
                return this;
            }
            e[] eVarArr = new e[(length + 1)];
            System.arraycopy(this.a, 0, eVarArr, 0, length);
            eVarArr[length] = new e(cls, jsonSerializer);
            return new c(eVarArr);
        }
    }

    private static final class d extends PropertySerializerMap {
        private final Class<?> a;
        private final JsonSerializer<Object> b;

        public d(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this.a = cls;
            this.b = jsonSerializer;
        }

        public JsonSerializer<Object> serializerFor(Class<?> cls) {
            if (cls == this.a) {
                return this.b;
            }
            return null;
        }

        public PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            return new a(this.a, this.b, cls, jsonSerializer);
        }
    }

    private static final class e {
        public final Class<?> a;
        public final JsonSerializer<Object> b;

        public e(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
            this.a = cls;
            this.b = jsonSerializer;
        }
    }

    public abstract PropertySerializerMap newWith(Class<?> cls, JsonSerializer<Object> jsonSerializer);

    public abstract JsonSerializer<Object> serializerFor(Class<?> cls);

    public final SerializerAndMapResult findAndAddSerializer(Class<?> cls, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(cls, beanProperty);
        return new SerializerAndMapResult(findValueSerializer, newWith(cls, findValueSerializer));
    }

    public final SerializerAndMapResult findAndAddSerializer(JavaType javaType, SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(javaType, beanProperty);
        return new SerializerAndMapResult(findValueSerializer, newWith(javaType.getRawClass(), findValueSerializer));
    }

    public static PropertySerializerMap emptyMap() {
        return b.a;
    }
}
