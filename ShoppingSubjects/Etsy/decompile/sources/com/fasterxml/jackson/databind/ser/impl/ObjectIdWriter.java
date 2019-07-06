package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;

public final class ObjectIdWriter {
    public final boolean alwaysAsId;
    public final ObjectIdGenerator<?> generator;
    public final JavaType idType;
    public final SerializedString propertyName;
    public final JsonSerializer<Object> serializer;

    protected ObjectIdWriter(JavaType javaType, SerializedString serializedString, ObjectIdGenerator<?> objectIdGenerator, JsonSerializer<?> jsonSerializer, boolean z) {
        this.idType = javaType;
        this.propertyName = serializedString;
        this.generator = objectIdGenerator;
        this.serializer = jsonSerializer;
        this.alwaysAsId = z;
    }

    public static ObjectIdWriter construct(JavaType javaType, String str, ObjectIdGenerator<?> objectIdGenerator, boolean z) {
        ObjectIdWriter objectIdWriter = new ObjectIdWriter(javaType, str == null ? null : new SerializedString(str), objectIdGenerator, null, z);
        return objectIdWriter;
    }

    public ObjectIdWriter withSerializer(JsonSerializer<?> jsonSerializer) {
        ObjectIdWriter objectIdWriter = new ObjectIdWriter(this.idType, this.propertyName, this.generator, jsonSerializer, this.alwaysAsId);
        return objectIdWriter;
    }

    public ObjectIdWriter withAlwaysAsId(boolean z) {
        if (z == this.alwaysAsId) {
            return this;
        }
        ObjectIdWriter objectIdWriter = new ObjectIdWriter(this.idType, this.propertyName, this.generator, this.serializer, z);
        return objectIdWriter;
    }
}