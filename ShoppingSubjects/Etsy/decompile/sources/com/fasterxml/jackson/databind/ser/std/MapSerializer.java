package com.fasterxml.jackson.databind.ser.std;

import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

@JacksonStdImpl
public class MapSerializer extends ContainerSerializer<Map<?, ?>> implements ContextualSerializer {
    protected static final JavaType UNSPECIFIED_TYPE = TypeFactory.unknownType();
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final HashSet<String> _ignoredEntries;
    protected JsonSerializer<Object> _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;

    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2) {
        super(Map.class, false);
        this._ignoredEntries = hashSet;
        this._keyType = javaType;
        this._valueType = javaType2;
        this._valueTypeIsStatic = z;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = PropertySerializerMap.emptyMap();
        this._property = null;
    }

    protected MapSerializer(MapSerializer mapSerializer, BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, HashSet<String> hashSet) {
        super(Map.class, false);
        this._ignoredEntries = hashSet;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = mapSerializer._valueTypeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = mapSerializer._dynamicValueSerializers;
        this._property = beanProperty;
    }

    protected MapSerializer(MapSerializer mapSerializer, TypeSerializer typeSerializer) {
        super(Map.class, false);
        this._ignoredEntries = mapSerializer._ignoredEntries;
        this._keyType = mapSerializer._keyType;
        this._valueType = mapSerializer._valueType;
        this._valueTypeIsStatic = mapSerializer._valueTypeIsStatic;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = mapSerializer._keySerializer;
        this._valueSerializer = mapSerializer._valueSerializer;
        this._dynamicValueSerializers = mapSerializer._dynamicValueSerializers;
        this._property = mapSerializer._property;
    }

    public MapSerializer _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new MapSerializer(this, typeSerializer);
    }

    public MapSerializer withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, JsonSerializer<?> jsonSerializer2, HashSet<String> hashSet) {
        MapSerializer mapSerializer = new MapSerializer(this, beanProperty, jsonSerializer, jsonSerializer2, hashSet);
        return mapSerializer;
    }

    public static MapSerializer construct(String[] strArr, JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2) {
        JavaType javaType2;
        JavaType javaType3;
        HashSet set = toSet(strArr);
        if (javaType == null) {
            javaType3 = UNSPECIFIED_TYPE;
            javaType2 = javaType3;
        } else {
            javaType3 = javaType.getKeyType();
            javaType2 = javaType.getContentType();
        }
        if (!z) {
            z = javaType2 != null && javaType2.isFinal();
        }
        MapSerializer mapSerializer = new MapSerializer(set, javaType3, javaType2, z, typeSerializer, jsonSerializer, jsonSerializer2);
        return mapSerializer;
    }

    private static HashSet<String> toSet(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        HashSet<String> hashSet = new HashSet<>(strArr.length);
        for (String add : strArr) {
            hashSet.add(add);
        }
        return hashSet;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r7, com.fasterxml.jackson.databind.BeanProperty r8) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r6 = this;
            r0 = 0
            if (r8 == 0) goto L_0x0024
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r1 = r8.getMember()
            if (r1 == 0) goto L_0x0024
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r7.getAnnotationIntrospector()
            java.lang.Object r3 = r2.findKeySerializer(r1)
            if (r3 == 0) goto L_0x0018
            com.fasterxml.jackson.databind.JsonSerializer r3 = r7.serializerInstance(r1, r3)
            goto L_0x0019
        L_0x0018:
            r3 = r0
        L_0x0019:
            java.lang.Object r2 = r2.findContentSerializer(r1)
            if (r2 == 0) goto L_0x0025
            com.fasterxml.jackson.databind.JsonSerializer r0 = r7.serializerInstance(r1, r2)
            goto L_0x0025
        L_0x0024:
            r3 = r0
        L_0x0025:
            if (r0 != 0) goto L_0x0029
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r0 = r6._valueSerializer
        L_0x0029:
            com.fasterxml.jackson.databind.JsonSerializer r0 = r6.findConvertingContentSerializer(r7, r8, r0)
            if (r0 != 0) goto L_0x0040
            boolean r1 = r6._valueTypeIsStatic
            if (r1 != 0) goto L_0x0039
            boolean r1 = r6.hasContentTypeAnnotation(r7, r8)
            if (r1 == 0) goto L_0x004a
        L_0x0039:
            com.fasterxml.jackson.databind.JavaType r0 = r6._valueType
            com.fasterxml.jackson.databind.JsonSerializer r0 = r7.findValueSerializer(r0, r8)
            goto L_0x004a
        L_0x0040:
            boolean r1 = r0 instanceof com.fasterxml.jackson.databind.ser.ContextualSerializer
            if (r1 == 0) goto L_0x004a
            com.fasterxml.jackson.databind.ser.ContextualSerializer r0 = (com.fasterxml.jackson.databind.ser.ContextualSerializer) r0
            com.fasterxml.jackson.databind.JsonSerializer r0 = r0.createContextual(r7, r8)
        L_0x004a:
            if (r3 != 0) goto L_0x004e
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r3 = r6._keySerializer
        L_0x004e:
            if (r3 != 0) goto L_0x0057
            com.fasterxml.jackson.databind.JavaType r1 = r6._keyType
            com.fasterxml.jackson.databind.JsonSerializer r3 = r7.findKeySerializer(r1, r8)
            goto L_0x0061
        L_0x0057:
            boolean r1 = r3 instanceof com.fasterxml.jackson.databind.ser.ContextualSerializer
            if (r1 == 0) goto L_0x0061
            com.fasterxml.jackson.databind.ser.ContextualSerializer r3 = (com.fasterxml.jackson.databind.ser.ContextualSerializer) r3
            com.fasterxml.jackson.databind.JsonSerializer r3 = r3.createContextual(r7, r8)
        L_0x0061:
            java.util.HashSet<java.lang.String> r1 = r6._ignoredEntries
            com.fasterxml.jackson.databind.AnnotationIntrospector r7 = r7.getAnnotationIntrospector()
            if (r7 == 0) goto L_0x008f
            if (r8 == 0) goto L_0x008f
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r2 = r8.getMember()
            java.lang.String[] r7 = r7.findPropertiesToIgnore(r2)
            if (r7 == 0) goto L_0x008f
            if (r1 != 0) goto L_0x007d
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            goto L_0x0083
        L_0x007d:
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>(r1)
            r1 = r2
        L_0x0083:
            int r2 = r7.length
            r4 = 0
        L_0x0085:
            if (r4 >= r2) goto L_0x008f
            r5 = r7[r4]
            r1.add(r5)
            int r4 = r4 + 1
            goto L_0x0085
        L_0x008f:
            com.fasterxml.jackson.databind.ser.std.MapSerializer r7 = r6.withResolved(r8, r3, r0, r1)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.MapSerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    public JavaType getContentType() {
        return this._valueType;
    }

    public JsonSerializer<?> getContentSerializer() {
        return this._valueSerializer;
    }

    public boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public boolean hasSingleElement(Map<?, ?> map) {
        return map.size() == 1;
    }

    public JsonSerializer<?> getKeySerializer() {
        return this._keySerializer;
    }

    public void serialize(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartObject();
        if (!map.isEmpty()) {
            if (serializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                map = _orderEntries(map);
            }
            if (this._valueSerializer != null) {
                serializeFieldsUsing(map, jsonGenerator, serializerProvider, this._valueSerializer);
            } else {
                serializeFields(map, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    public void serializeWithType(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForObject(map, jsonGenerator);
        if (!map.isEmpty()) {
            if (serializerProvider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                map = _orderEntries(map);
            }
            if (this._valueSerializer != null) {
                serializeFieldsUsing(map, jsonGenerator, serializerProvider, this._valueSerializer);
            } else {
                serializeFields(map, jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffixForObject(map, jsonGenerator);
    }

    public void serializeFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer _findAndAddDynamic;
        if (this._valueTypeSerializer != null) {
            serializeTypedFields(map, jsonGenerator, serializerProvider);
            return;
        }
        JsonSerializer<Object> jsonSerializer = this._keySerializer;
        HashSet<String> hashSet = this._ignoredEntries;
        boolean z = !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator, serializerProvider);
            } else if ((!z || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer.serialize(key, jsonGenerator, serializerProvider);
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                Class cls = value.getClass();
                JsonSerializer serializerFor = propertySerializerMap.serializerFor(cls);
                if (serializerFor == null) {
                    if (this._valueType.hasGenericTypes()) {
                        _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._valueType, cls), serializerProvider);
                    } else {
                        _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                    }
                    serializerFor = _findAndAddDynamic;
                    propertySerializerMap = this._dynamicValueSerializers;
                }
                try {
                    serializerFor.serialize(value, jsonGenerator, serializerProvider);
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(key);
                    wrapAndThrow(serializerProvider, (Throwable) e, (Object) map, sb.toString());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeFieldsUsing(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        JsonSerializer<Object> jsonSerializer2 = this._keySerializer;
        HashSet<String> hashSet = this._ignoredEntries;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        boolean z = !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator, serializerProvider);
            } else if ((!z || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer2.serialize(key, jsonGenerator, serializerProvider);
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else if (typeSerializer == null) {
                try {
                    jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(key);
                    wrapAndThrow(serializerProvider, (Throwable) e, (Object) map, sb.toString());
                }
            } else {
                jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, typeSerializer);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void serializeTypedFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> jsonSerializer = this._keySerializer;
        HashSet<String> hashSet = this._ignoredEntries;
        boolean z = !serializerProvider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        Class cls = null;
        JsonSerializer jsonSerializer2 = null;
        for (Entry entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.findNullKeySerializer(this._keyType, this._property).serialize(null, jsonGenerator, serializerProvider);
            } else if ((!z || value != null) && (hashSet == null || !hashSet.contains(key))) {
                jsonSerializer.serialize(key, jsonGenerator, serializerProvider);
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                Class cls2 = value.getClass();
                if (cls2 != cls) {
                    jsonSerializer2 = serializerProvider.findValueSerializer(cls2, this._property);
                    cls = cls2;
                }
                try {
                    jsonSerializer2.serializeWithType(value, jsonGenerator, serializerProvider, this._valueTypeSerializer);
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("");
                    sb.append(key);
                    wrapAndThrow(serializerProvider, (Throwable) e, (Object) map, sb.toString());
                }
            }
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode(ResponseConstants.OBJECT, true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonMapFormatVisitor expectMapFormat = jsonFormatVisitorWrapper == null ? null : jsonFormatVisitorWrapper.expectMapFormat(javaType);
        if (expectMapFormat != null) {
            expectMapFormat.keyFormat(this._keySerializer, this._keyType);
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = _findAndAddDynamic(this._dynamicValueSerializers, this._valueType, jsonFormatVisitorWrapper.getProvider());
            }
            expectMapFormat.valueFormat(jsonSerializer, this._valueType);
        }
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(cls, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSerializer.map) {
            this._dynamicValueSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(javaType, serializerProvider, this._property);
        if (propertySerializerMap != findAndAddSerializer.map) {
            this._dynamicValueSerializers = findAndAddSerializer.map;
        }
        return findAndAddSerializer.serializer;
    }

    /* access modifiers changed from: protected */
    public Map<?, ?> _orderEntries(Map<?, ?> map) {
        if (map instanceof SortedMap) {
            return map;
        }
        return new TreeMap(map);
    }
}
