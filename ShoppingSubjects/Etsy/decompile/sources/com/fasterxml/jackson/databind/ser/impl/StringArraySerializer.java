package com.fasterxml.jackson.databind.ser.impl;

import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;

@JacksonStdImpl
public class StringArraySerializer extends ArraySerializerBase<String[]> implements ContextualSerializer {
    private static final JavaType VALUE_TYPE = TypeFactory.defaultInstance().uncheckedSimpleType(String.class);
    public static final StringArraySerializer instance = new StringArraySerializer();
    protected final JsonSerializer<Object> _elementSerializer;

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return this;
    }

    protected StringArraySerializer() {
        super(String[].class, (BeanProperty) null);
        this._elementSerializer = null;
    }

    public StringArraySerializer(StringArraySerializer stringArraySerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        super((ArraySerializerBase<?>) stringArraySerializer, beanProperty);
        this._elementSerializer = jsonSerializer;
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
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r1 = r3._elementSerializer
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
            boolean r1 = r3.isDefaultSerializer(r4)
            if (r1 == 0) goto L_0x003d
            r4 = r0
        L_0x003d:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r0 = r3._elementSerializer
            if (r4 != r0) goto L_0x0042
            return r3
        L_0x0042:
            com.fasterxml.jackson.databind.ser.impl.StringArraySerializer r0 = new com.fasterxml.jackson.databind.ser.impl.StringArraySerializer
            r0.<init>(r3, r5, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.impl.StringArraySerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public JavaType getContentType() {
        return VALUE_TYPE;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._elementSerializer;
    }

    public boolean isEmpty(String[] strArr) {
        return strArr == null || strArr.length == 0;
    }

    public boolean hasSingleElement(String[] strArr) {
        return strArr.length == 1;
    }

    public void serializeContents(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        int length = strArr.length;
        if (length != 0) {
            if (this._elementSerializer != null) {
                serializeContentsSlow(strArr, jsonGenerator, serializerProvider, this._elementSerializer);
                return;
            }
            for (int i = 0; i < length; i++) {
                if (strArr[i] == null) {
                    jsonGenerator.writeNull();
                } else {
                    jsonGenerator.writeString(strArr[i]);
                }
            }
        }
    }

    private void serializeContentsSlow(String[] strArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (strArr[i] == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                jsonSerializer.serialize(strArr[i], jsonGenerator, serializerProvider);
            }
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        createSchemaNode.put(ResponseConstants.ITEMS, (JsonNode) createSchemaNode("string"));
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        if (jsonFormatVisitorWrapper != null) {
            JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType);
            if (expectArrayFormat != null) {
                expectArrayFormat.itemsFormat(JsonFormatTypes.STRING);
            }
        }
    }
}
