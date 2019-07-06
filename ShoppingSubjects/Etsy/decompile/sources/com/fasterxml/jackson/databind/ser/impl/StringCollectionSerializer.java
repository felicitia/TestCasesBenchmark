package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public class StringCollectionSerializer extends StaticListSerializerBase<Collection<String>> implements ContextualSerializer {
    public static final StringCollectionSerializer instance = new StringCollectionSerializer();
    protected final JsonSerializer<String> _serializer;

    protected StringCollectionSerializer() {
        this(null);
    }

    protected StringCollectionSerializer(JsonSerializer<?> jsonSerializer) {
        super(Collection.class);
        this._serializer = jsonSerializer;
    }

    /* access modifiers changed from: protected */
    public JsonNode contentSchema() {
        return createSchemaNode("string", true);
    }

    /* access modifiers changed from: protected */
    public void acceptContentVisitor(JsonArrayFormatVisitor jsonArrayFormatVisitor) throws JsonMappingException {
        jsonArrayFormatVisitor.itemsFormat(JsonFormatTypes.STRING);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0023  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0041 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r4, com.fasterxml.jackson.databind.BeanProperty r5) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r3 = this;
            r0 = 0
            if (r5 == 0) goto L_0x0018
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r1 = r5.getMember()
            if (r1 == 0) goto L_0x0018
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r4.getAnnotationIntrospector()
            java.lang.Object r2 = r2.findContentSerializer(r1)
            if (r2 == 0) goto L_0x0018
            com.fasterxml.jackson.databind.JsonSerializer r1 = r4.serializerInstance(r1, r2)
            goto L_0x0019
        L_0x0018:
            r1 = r0
        L_0x0019:
            if (r1 != 0) goto L_0x001d
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.String> r1 = r3._serializer
        L_0x001d:
            com.fasterxml.jackson.databind.JsonSerializer r1 = r3.findConvertingContentSerializer(r4, r5, r1)
            if (r1 != 0) goto L_0x002a
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            com.fasterxml.jackson.databind.JsonSerializer r4 = r4.findValueSerializer(r1, r5)
            goto L_0x0036
        L_0x002a:
            boolean r2 = r1 instanceof com.fasterxml.jackson.databind.ser.ContextualSerializer
            if (r2 == 0) goto L_0x0035
            com.fasterxml.jackson.databind.ser.ContextualSerializer r1 = (com.fasterxml.jackson.databind.ser.ContextualSerializer) r1
            com.fasterxml.jackson.databind.JsonSerializer r4 = r1.createContextual(r4, r5)
            goto L_0x0036
        L_0x0035:
            r4 = r1
        L_0x0036:
            boolean r5 = r3.isDefaultSerializer(r4)
            if (r5 == 0) goto L_0x003d
            r4 = r0
        L_0x003d:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.String> r5 = r3._serializer
            if (r4 != r5) goto L_0x0042
            return r3
        L_0x0042:
            com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer r5 = new com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer
            r5.<init>(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public void serialize(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (collection.size() != 1 || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
            jsonGenerator.writeStartArray();
            if (this._serializer == null) {
                serializeContents(collection, jsonGenerator, serializerProvider);
            } else {
                serializeUsingCustom(collection, jsonGenerator, serializerProvider);
            }
            jsonGenerator.writeEndArray();
            return;
        }
        _serializeUnwrapped(collection, jsonGenerator, serializerProvider);
    }

    private final void _serializeUnwrapped(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (this._serializer == null) {
            serializeContents(collection, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(collection, jsonGenerator, serializerProvider);
        }
    }

    public void serializeWithType(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForArray(collection, jsonGenerator);
        if (this._serializer == null) {
            serializeContents(collection, jsonGenerator, serializerProvider);
        } else {
            serializeUsingCustom(collection, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForArray(collection, jsonGenerator);
    }

    private final void serializeContents(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (this._serializer != null) {
            serializeUsingCustom(collection, jsonGenerator, serializerProvider);
            return;
        }
        int i = 0;
        for (String str : collection) {
            if (str == null) {
                try {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, (Throwable) e, (Object) collection, i);
                }
            } else {
                jsonGenerator.writeString(str);
            }
            i++;
        }
    }

    private void serializeUsingCustom(Collection<String> collection, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<String> jsonSerializer = this._serializer;
        for (String str : collection) {
            if (str == null) {
                try {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, (Throwable) e, (Object) collection, 0);
                }
            } else {
                jsonSerializer.serialize(str, jsonGenerator, serializerProvider);
            }
        }
    }
}
