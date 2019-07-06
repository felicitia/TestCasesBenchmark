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
import java.util.List;

@JacksonStdImpl
public final class IndexedStringListSerializer extends StaticListSerializerBase<List<String>> implements ContextualSerializer {
    public static final IndexedStringListSerializer instance = new IndexedStringListSerializer();
    protected final JsonSerializer<String> _serializer;

    protected IndexedStringListSerializer() {
        this(null);
    }

    public IndexedStringListSerializer(JsonSerializer<?> jsonSerializer) {
        super(List.class);
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
            com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer r5 = new com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer
            r5.<init>(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public void serialize(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        int size = list.size();
        if (size != 1 || !serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
            jsonGenerator.writeStartArray();
            if (this._serializer == null) {
                serializeContents(list, jsonGenerator, serializerProvider, size);
            } else {
                serializeUsingCustom(list, jsonGenerator, serializerProvider, size);
            }
            jsonGenerator.writeEndArray();
            return;
        }
        _serializeUnwrapped(list, jsonGenerator, serializerProvider);
    }

    private final void _serializeUnwrapped(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider, 1);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider, 1);
        }
    }

    public void serializeWithType(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        int size = list.size();
        typeSerializer.writeTypePrefixForArray(list, jsonGenerator);
        if (this._serializer == null) {
            serializeContents(list, jsonGenerator, serializerProvider, size);
        } else {
            serializeUsingCustom(list, jsonGenerator, serializerProvider, size);
        }
        typeSerializer.writeTypeSuffixForArray(list, jsonGenerator);
    }

    private final void serializeContents(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, int i) throws IOException, JsonGenerationException {
        int i2 = 0;
        while (i2 < i) {
            try {
                String str = (String) list.get(i2);
                if (str == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonGenerator.writeString(str);
                }
                i2++;
            } catch (Exception e) {
                wrapAndThrow(serializerProvider, (Throwable) e, (Object) list, i2);
                return;
            }
        }
    }

    private final void serializeUsingCustom(List<String> list, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, int i) throws IOException, JsonGenerationException {
        try {
            JsonSerializer<String> jsonSerializer = this._serializer;
            for (int i2 = 0; i2 < i; i2++) {
                String str = (String) list.get(i2);
                if (str == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    jsonSerializer.serialize(str, jsonGenerator, serializerProvider);
                }
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, (Throwable) e, (Object) list, 0);
        }
    }
}
