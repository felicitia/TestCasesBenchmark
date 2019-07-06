package com.fasterxml.jackson.databind.ser.std;

import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map.Entry;

@JacksonStdImpl
public class EnumMapSerializer extends ContainerSerializer<EnumMap<? extends Enum<?>, ?>> implements ContextualSerializer {
    protected final EnumValues _keyEnums;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected final JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final TypeSerializer _valueTypeSerializer;

    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        boolean z2 = false;
        super(EnumMap.class, false);
        this._property = null;
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueType = javaType;
        this._keyEnums = enumValues;
        this._valueTypeSerializer = typeSerializer;
        this._valueSerializer = jsonSerializer;
    }

    public EnumMapSerializer(EnumMapSerializer enumMapSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        super((ContainerSerializer<?>) enumMapSerializer);
        this._property = beanProperty;
        this._staticTyping = enumMapSerializer._staticTyping;
        this._valueType = enumMapSerializer._valueType;
        this._keyEnums = enumMapSerializer._keyEnums;
        this._valueTypeSerializer = enumMapSerializer._valueTypeSerializer;
        this._valueSerializer = jsonSerializer;
    }

    public EnumMapSerializer _withValueTypeSerializer(TypeSerializer typeSerializer) {
        EnumMapSerializer enumMapSerializer = new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, typeSerializer, this._valueSerializer);
        return enumMapSerializer;
    }

    public EnumMapSerializer withValueSerializer(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer) {
        if (this._property == beanProperty && jsonSerializer == this._valueSerializer) {
            return this;
        }
        return new EnumMapSerializer(this, beanProperty, jsonSerializer);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0046 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r3, com.fasterxml.jackson.databind.BeanProperty r4) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r2 = this;
            if (r4 == 0) goto L_0x0017
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r4.getMember()
            if (r0 == 0) goto L_0x0017
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r3.getAnnotationIntrospector()
            java.lang.Object r1 = r1.findContentSerializer(r0)
            if (r1 == 0) goto L_0x0017
            com.fasterxml.jackson.databind.JsonSerializer r0 = r3.serializerInstance(r0, r1)
            goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            if (r0 != 0) goto L_0x001c
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r0 = r2._valueSerializer
        L_0x001c:
            com.fasterxml.jackson.databind.JsonSerializer r0 = r2.findConvertingContentSerializer(r3, r4, r0)
            if (r0 != 0) goto L_0x0031
            boolean r1 = r2._staticTyping
            if (r1 == 0) goto L_0x003d
            com.fasterxml.jackson.databind.JavaType r0 = r2._valueType
            com.fasterxml.jackson.databind.JsonSerializer r3 = r3.findValueSerializer(r0, r4)
            com.fasterxml.jackson.databind.ser.std.EnumMapSerializer r3 = r2.withValueSerializer(r4, r3)
            return r3
        L_0x0031:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r1 = r2._valueSerializer
            boolean r1 = r1 instanceof com.fasterxml.jackson.databind.ser.ContextualSerializer
            if (r1 == 0) goto L_0x003d
            com.fasterxml.jackson.databind.ser.ContextualSerializer r0 = (com.fasterxml.jackson.databind.ser.ContextualSerializer) r0
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.createContextual(r3, r4)
        L_0x003d:
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r3 = r2._valueSerializer
            if (r0 == r3) goto L_0x0046
            com.fasterxml.jackson.databind.ser.std.EnumMapSerializer r3 = r2.withValueSerializer(r4, r0)
            return r3
        L_0x0046:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.EnumMapSerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public JavaType getContentType() {
        return this._valueType;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._valueSerializer;
    }

    public boolean isEmpty(EnumMap<? extends Enum<?>, ?> enumMap) {
        return enumMap == null || enumMap.isEmpty();
    }

    public boolean hasSingleElement(EnumMap<? extends Enum<?>, ?> enumMap) {
        return enumMap.size() == 1;
    }

    public void serialize(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartObject();
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeWithType(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForObject(enumMap, jsonGenerator);
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(enumMap, jsonGenerator);
    }

    /* access modifiers changed from: protected */
    public void serializeContents(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        if (this._valueSerializer != null) {
            serializeContentsUsing(enumMap, jsonGenerator, serializerProvider, this._valueSerializer);
            return;
        }
        EnumValues enumValues = this._keyEnums;
        boolean z = !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        Class cls = null;
        JsonSerializer jsonSerializer = null;
        for (Entry entry : enumMap.entrySet()) {
            Object value = entry.getValue();
            if (!z || value != null) {
                Enum enumR = (Enum) entry.getKey();
                if (enumValues == null) {
                    enumValues = ((EnumSerializer) ((StdSerializer) serializerProvider.findValueSerializer(enumR.getDeclaringClass(), this._property))).getEnumValues();
                }
                jsonGenerator.writeFieldName((SerializableString) enumValues.serializedValueFor(enumR));
                if (value == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    Class cls2 = value.getClass();
                    if (cls2 != cls) {
                        jsonSerializer = serializerProvider.findValueSerializer(cls2, this._property);
                        cls = cls2;
                    }
                    if (typeSerializer == null) {
                        try {
                            jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                        } catch (Exception e) {
                            wrapAndThrow(serializerProvider, (Throwable) e, (Object) enumMap, ((Enum) entry.getKey()).name());
                        }
                    } else {
                        jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, typeSerializer);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeContentsUsing(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        EnumValues enumValues = this._keyEnums;
        boolean z = !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        for (Entry entry : enumMap.entrySet()) {
            Object value = entry.getValue();
            if (!z || value != null) {
                Enum enumR = (Enum) entry.getKey();
                if (enumValues == null) {
                    enumValues = ((EnumSerializer) ((StdSerializer) serializerProvider.findValueSerializer(enumR.getDeclaringClass(), this._property))).getEnumValues();
                }
                jsonGenerator.writeFieldName((SerializableString) enumValues.serializedValueFor(enumR));
                if (value == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else if (typeSerializer == null) {
                    try {
                        jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                    } catch (Exception e) {
                        wrapAndThrow(serializerProvider, (Throwable) e, (Object) enumMap, ((Enum) entry.getKey()).name());
                    }
                } else {
                    jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, typeSerializer);
                }
            }
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        Enum[] enumArr;
        ObjectNode createSchemaNode = createSchemaNode(ResponseConstants.OBJECT, true);
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length == 2) {
                JavaType constructType = serializerProvider.constructType(actualTypeArguments[0]);
                JavaType constructType2 = serializerProvider.constructType(actualTypeArguments[1]);
                ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
                for (Enum enumR : (Enum[]) constructType.getRawClass().getEnumConstants()) {
                    JsonSerializer findValueSerializer = serializerProvider.findValueSerializer(constructType2.getRawClass(), this._property);
                    objectNode.put(serializerProvider.getConfig().getAnnotationIntrospector().findEnumValue(enumR), findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(serializerProvider, null) : JsonSchema.getDefaultSchemaNode());
                }
                createSchemaNode.put("properties", (JsonNode) objectNode);
            }
        }
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonSerializer jsonSerializer;
        if (jsonFormatVisitorWrapper != null) {
            JsonObjectFormatVisitor expectObjectFormat = jsonFormatVisitorWrapper.expectObjectFormat(javaType);
            if (expectObjectFormat != null) {
                JavaType containedType = javaType.containedType(1);
                JsonSerializer<Object> jsonSerializer2 = this._valueSerializer;
                if (jsonSerializer2 == null && containedType != null) {
                    jsonSerializer2 = jsonFormatVisitorWrapper.getProvider().findValueSerializer(containedType, this._property);
                }
                if (containedType == null) {
                    containedType = jsonFormatVisitorWrapper.getProvider().constructType(Object.class);
                }
                EnumValues enumValues = this._keyEnums;
                if (enumValues == null) {
                    JavaType containedType2 = javaType.containedType(0);
                    if (containedType2 == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Can not resolve Enum type of EnumMap: ");
                        sb.append(javaType);
                        throw new IllegalStateException(sb.toString());
                    }
                    if (containedType2 == null) {
                        jsonSerializer = null;
                    } else {
                        jsonSerializer = jsonFormatVisitorWrapper.getProvider().findValueSerializer(containedType2, this._property);
                    }
                    if (!(jsonSerializer instanceof EnumSerializer)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Can not resolve Enum type of EnumMap: ");
                        sb2.append(javaType);
                        throw new IllegalStateException(sb2.toString());
                    }
                    enumValues = ((EnumSerializer) jsonSerializer).getEnumValues();
                }
                for (Entry entry : enumValues.internalMap().entrySet()) {
                    String value = ((SerializedString) entry.getValue()).getValue();
                    if (jsonSerializer2 == null) {
                        jsonSerializer2 = jsonFormatVisitorWrapper.getProvider().findValueSerializer(entry.getKey().getClass(), this._property);
                    }
                    expectObjectFormat.property(value, jsonSerializer2, containedType);
                }
            }
        }
    }
}
