package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import java.util.Map;

public class AnyGetterWriter {
    protected final AnnotatedMember _accessor;
    protected final BeanProperty _property;
    protected MapSerializer _serializer;

    public AnyGetterWriter(BeanProperty beanProperty, AnnotatedMember annotatedMember, MapSerializer mapSerializer) {
        this._accessor = annotatedMember;
        this._property = beanProperty;
        this._serializer = mapSerializer;
    }

    public void getAndSerialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object value = this._accessor.getValue(obj);
        if (value != null) {
            if (!(value instanceof Map)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Value returned by 'any-getter' (");
                sb.append(this._accessor.getName());
                sb.append("()) not java.util.Map but ");
                sb.append(value.getClass().getName());
                throw new JsonMappingException(sb.toString());
            }
            this._serializer.serializeFields((Map) value, jsonGenerator, serializerProvider);
        }
    }

    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        this._serializer = (MapSerializer) this._serializer.createContextual(serializerProvider, this._property);
    }
}
