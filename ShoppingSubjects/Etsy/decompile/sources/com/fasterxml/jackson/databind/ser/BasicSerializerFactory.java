package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.impl.IndexedStringListSerializer;
import com.fasterxml.jackson.databind.ser.impl.StringArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.StringCollectionSerializer;
import com.fasterxml.jackson.databind.ser.std.BooleanSerializer;
import com.fasterxml.jackson.databind.ser.std.CalendarSerializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.EnumSerializer;
import com.fasterxml.jackson.databind.ser.std.InetAddressSerializer;
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers.NumberSerializer;
import com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer;
import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlDateSerializer;
import com.fasterxml.jackson.databind.ser.std.SqlTimeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import com.fasterxml.jackson.databind.ser.std.StdContainerSerializers;
import com.fasterxml.jackson.databind.ser.std.StdDelegatingSerializer;
import com.fasterxml.jackson.databind.ser.std.StdJdkSerializers;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fasterxml.jackson.databind.ser.std.TimeZoneSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.ser.std.TokenBufferSerializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.RandomAccess;
import java.util.TimeZone;

public abstract class BasicSerializerFactory extends SerializerFactory implements Serializable {
    protected static final HashMap<String, JsonSerializer<?>> _concrete = new HashMap<>();
    protected static final HashMap<String, Class<? extends JsonSerializer<?>>> _concreteLazy = new HashMap<>();
    protected final SerializerFactoryConfig _factoryConfig;

    public abstract JsonSerializer<Object> createSerializer(SerializerProvider serializerProvider, JavaType javaType) throws JsonMappingException;

    /* access modifiers changed from: protected */
    public abstract Iterable<Serializers> customSerializers();

    public abstract SerializerFactory withConfig(SerializerFactoryConfig serializerFactoryConfig);

    static {
        _concrete.put(String.class.getName(), new StringSerializer());
        ToStringSerializer toStringSerializer = ToStringSerializer.instance;
        _concrete.put(StringBuffer.class.getName(), toStringSerializer);
        _concrete.put(StringBuilder.class.getName(), toStringSerializer);
        _concrete.put(Character.class.getName(), toStringSerializer);
        _concrete.put(Character.TYPE.getName(), toStringSerializer);
        NumberSerializers.addAll(_concrete);
        _concrete.put(Boolean.TYPE.getName(), new BooleanSerializer(true));
        _concrete.put(Boolean.class.getName(), new BooleanSerializer(false));
        NumberSerializer numberSerializer = new NumberSerializer();
        _concrete.put(BigInteger.class.getName(), numberSerializer);
        _concrete.put(BigDecimal.class.getName(), numberSerializer);
        _concrete.put(Calendar.class.getName(), CalendarSerializer.instance);
        DateSerializer dateSerializer = DateSerializer.instance;
        _concrete.put(Date.class.getName(), dateSerializer);
        _concrete.put(Timestamp.class.getName(), dateSerializer);
        _concreteLazy.put(java.sql.Date.class.getName(), SqlDateSerializer.class);
        _concreteLazy.put(Time.class.getName(), SqlTimeSerializer.class);
        for (Entry entry : StdJdkSerializers.all()) {
            Object value = entry.getValue();
            if (value instanceof JsonSerializer) {
                _concrete.put(((Class) entry.getKey()).getName(), (JsonSerializer) value);
            } else if (value instanceof Class) {
                _concreteLazy.put(((Class) entry.getKey()).getName(), (Class) value);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Internal error: unrecognized value of type ");
                sb.append(entry.getClass().getName());
                throw new IllegalStateException(sb.toString());
            }
        }
        _concreteLazy.put(TokenBuffer.class.getName(), TokenBufferSerializer.class);
    }

    protected BasicSerializerFactory(SerializerFactoryConfig serializerFactoryConfig) {
        if (serializerFactoryConfig == null) {
            serializerFactoryConfig = new SerializerFactoryConfig();
        }
        this._factoryConfig = serializerFactoryConfig;
    }

    public SerializerFactoryConfig getFactoryConfig() {
        return this._factoryConfig;
    }

    public final SerializerFactory withAdditionalSerializers(Serializers serializers) {
        return withConfig(this._factoryConfig.withAdditionalSerializers(serializers));
    }

    public final SerializerFactory withAdditionalKeySerializers(Serializers serializers) {
        return withConfig(this._factoryConfig.withAdditionalKeySerializers(serializers));
    }

    public final SerializerFactory withSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
        return withConfig(this._factoryConfig.withSerializerModifier(beanSerializerModifier));
    }

    public JsonSerializer<Object> createKeySerializer(SerializationConfig serializationConfig, JavaType javaType, JsonSerializer<Object> jsonSerializer) {
        BeanDescription introspectClassAnnotations = serializationConfig.introspectClassAnnotations(javaType.getRawClass());
        JsonSerializer<Object> jsonSerializer2 = null;
        if (this._factoryConfig.hasKeySerializers()) {
            for (Serializers findSerializer : this._factoryConfig.keySerializers()) {
                jsonSerializer2 = findSerializer.findSerializer(serializationConfig, javaType, introspectClassAnnotations);
                if (jsonSerializer2 != null) {
                    break;
                }
            }
        }
        if (jsonSerializer2 != null) {
            jsonSerializer = jsonSerializer2;
        } else if (jsonSerializer == null) {
            jsonSerializer = StdKeySerializers.getStdKeySerializer(javaType);
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier modifyKeySerializer : this._factoryConfig.serializerModifiers()) {
                jsonSerializer = modifyKeySerializer.modifyKeySerializer(serializationConfig, javaType, introspectClassAnnotations, jsonSerializer);
            }
        }
        return jsonSerializer;
    }

    public TypeSerializer createTypeSerializer(SerializationConfig serializationConfig, JavaType javaType) {
        Collection collection;
        AnnotatedClass classInfo = serializationConfig.introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findTypeResolver = annotationIntrospector.findTypeResolver(serializationConfig, classInfo, javaType);
        if (findTypeResolver == null) {
            findTypeResolver = serializationConfig.getDefaultTyper(javaType);
            collection = null;
        } else {
            collection = serializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, (MapperConfig<?>) serializationConfig, annotationIntrospector);
        }
        if (findTypeResolver == null) {
            return null;
        }
        return findTypeResolver.buildTypeSerializer(serializationConfig, javaType, collection);
    }

    public final JsonSerializer<?> getNullSerializer() {
        return NullSerializer.instance;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByLookup(JavaType javaType, SerializationConfig serializationConfig, BeanDescription beanDescription, boolean z) {
        String name = javaType.getRawClass().getName();
        JsonSerializer<?> jsonSerializer = (JsonSerializer) _concrete.get(name);
        if (jsonSerializer == null) {
            Class cls = (Class) _concreteLazy.get(name);
            if (cls != null) {
                try {
                    return (JsonSerializer) cls.newInstance();
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to instantiate standard serializer (of type ");
                    sb.append(cls.getName());
                    sb.append("): ");
                    sb.append(e.getMessage());
                    throw new IllegalStateException(sb.toString(), e);
                }
            }
        }
        return jsonSerializer;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByAnnotations(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        if (JsonSerializable.class.isAssignableFrom(javaType.getRawClass())) {
            return SerializableSerializer.instance;
        }
        AnnotatedMethod findJsonValueMethod = beanDescription.findJsonValueMethod();
        if (findJsonValueMethod == null) {
            return null;
        }
        Method annotated = findJsonValueMethod.getAnnotated();
        if (serializerProvider.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(annotated);
        }
        return new JsonValueSerializer(annotated, findSerializerFromAnnotation(serializerProvider, findJsonValueMethod));
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByPrimaryType(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        Class rawClass = javaType.getRawClass();
        if (InetAddress.class.isAssignableFrom(rawClass)) {
            return InetAddressSerializer.instance;
        }
        if (TimeZone.class.isAssignableFrom(rawClass)) {
            return TimeZoneSerializer.instance;
        }
        if (Charset.class.isAssignableFrom(rawClass)) {
            return ToStringSerializer.instance;
        }
        JsonSerializer<?> findOptionalStdSerializer = findOptionalStdSerializer(serializerProvider, javaType, beanDescription, z);
        if (findOptionalStdSerializer != null) {
            return findOptionalStdSerializer;
        }
        if (Number.class.isAssignableFrom(rawClass)) {
            return NumberSerializer.instance;
        }
        if (Enum.class.isAssignableFrom(rawClass)) {
            return buildEnumSerializer(serializerProvider.getConfig(), javaType, beanDescription);
        }
        if (Calendar.class.isAssignableFrom(rawClass)) {
            return CalendarSerializer.instance;
        }
        if (Date.class.isAssignableFrom(rawClass)) {
            return DateSerializer.instance;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> findOptionalStdSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        return OptionalHandlerFactory.instance.findSerializer(serializerProvider.getConfig(), javaType, beanDescription);
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<?> findSerializerByAddonType(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        Class rawClass = javaType.getRawClass();
        if (Iterator.class.isAssignableFrom(rawClass)) {
            return buildIteratorSerializer(serializationConfig, javaType, beanDescription, z);
        }
        if (Iterable.class.isAssignableFrom(rawClass)) {
            return buildIterableSerializer(serializationConfig, javaType, beanDescription, z);
        }
        if (CharSequence.class.isAssignableFrom(rawClass)) {
            return ToStringSerializer.instance;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> findSerializerFromAnnotation(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findSerializer = serializerProvider.getAnnotationIntrospector().findSerializer(annotated);
        if (findSerializer == null) {
            return null;
        }
        return findConvertingSerializer(serializerProvider, annotated, serializerProvider.serializerInstance(annotated, findSerializer));
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> findConvertingSerializer(SerializerProvider serializerProvider, Annotated annotated, JsonSerializer<?> jsonSerializer) throws JsonMappingException {
        Converter findConverter = findConverter(serializerProvider, annotated);
        if (findConverter == null) {
            return jsonSerializer;
        }
        return new StdDelegatingSerializer(findConverter, findConverter.getOutputType(serializerProvider.getTypeFactory()), jsonSerializer);
    }

    /* access modifiers changed from: protected */
    public Converter<Object, Object> findConverter(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findSerializationConverter = serializerProvider.getAnnotationIntrospector().findSerializationConverter(annotated);
        if (findSerializationConverter == null) {
            return null;
        }
        return serializerProvider.converterInstance(annotated, findSerializationConverter);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final JsonSerializer<?> buildContainerSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, BeanProperty beanProperty, boolean z) throws JsonMappingException {
        return buildContainerSerializer(serializerProvider, javaType, beanDescription, z);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildContainerSerializer(SerializerProvider serializerProvider, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        SerializationConfig config = serializerProvider.getConfig();
        if (!z && javaType.useStaticType() && (!javaType.isContainerType() || javaType.getContentType().getRawClass() != Object.class)) {
            z = true;
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(config, javaType.getContentType());
        if (createTypeSerializer != null) {
            z = false;
        }
        boolean z2 = z;
        JsonSerializer _findContentSerializer = _findContentSerializer(serializerProvider, beanDescription.getClassInfo());
        if (javaType.isMapLikeType()) {
            MapLikeType mapLikeType = (MapLikeType) javaType;
            JsonSerializer _findKeySerializer = _findKeySerializer(serializerProvider, beanDescription.getClassInfo());
            if (mapLikeType.isTrueMapType()) {
                return buildMapSerializer(config, (MapType) mapLikeType, beanDescription, z2, _findKeySerializer, createTypeSerializer, _findContentSerializer);
            }
            for (Serializers findMapLikeSerializer : customSerializers()) {
                JsonSerializer<?> findMapLikeSerializer2 = findMapLikeSerializer.findMapLikeSerializer(config, mapLikeType, beanDescription, _findKeySerializer, createTypeSerializer, _findContentSerializer);
                if (findMapLikeSerializer2 != null) {
                    if (this._factoryConfig.hasSerializerModifiers()) {
                        for (BeanSerializerModifier modifyMapLikeSerializer : this._factoryConfig.serializerModifiers()) {
                            findMapLikeSerializer2 = modifyMapLikeSerializer.modifyMapLikeSerializer(config, mapLikeType, beanDescription, findMapLikeSerializer2);
                        }
                    }
                    return findMapLikeSerializer2;
                }
            }
            return null;
        } else if (javaType.isCollectionLikeType()) {
            CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
            if (collectionLikeType.isTrueCollectionType()) {
                return buildCollectionSerializer(config, (CollectionType) collectionLikeType, beanDescription, z2, createTypeSerializer, _findContentSerializer);
            }
            for (Serializers findCollectionLikeSerializer : customSerializers()) {
                JsonSerializer<?> findCollectionLikeSerializer2 = findCollectionLikeSerializer.findCollectionLikeSerializer(config, collectionLikeType, beanDescription, createTypeSerializer, _findContentSerializer);
                if (findCollectionLikeSerializer2 != null) {
                    if (this._factoryConfig.hasSerializerModifiers()) {
                        for (BeanSerializerModifier modifyCollectionLikeSerializer : this._factoryConfig.serializerModifiers()) {
                            findCollectionLikeSerializer2 = modifyCollectionLikeSerializer.modifyCollectionLikeSerializer(config, collectionLikeType, beanDescription, findCollectionLikeSerializer2);
                        }
                    }
                    return findCollectionLikeSerializer2;
                }
            }
            return null;
        } else if (!javaType.isArrayType()) {
            return null;
        } else {
            return buildArraySerializer(config, (ArrayType) javaType, beanDescription, z2, createTypeSerializer, _findContentSerializer);
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final JsonSerializer<?> buildCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, BeanProperty beanProperty, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) throws JsonMappingException {
        return buildCollectionSerializer(serializationConfig, collectionType, beanDescription, z, typeSerializer, jsonSerializer);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildCollectionSerializer(SerializationConfig serializationConfig, CollectionType collectionType, BeanDescription beanDescription, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) throws JsonMappingException {
        JsonSerializer jsonSerializer2 = null;
        for (Serializers findCollectionSerializer : customSerializers()) {
            jsonSerializer2 = findCollectionSerializer.findCollectionSerializer(serializationConfig, collectionType, beanDescription, typeSerializer, jsonSerializer);
            if (jsonSerializer2 != null) {
                break;
            }
        }
        if (jsonSerializer2 == null) {
            Value findExpectedFormat = beanDescription.findExpectedFormat(null);
            if (findExpectedFormat != null && findExpectedFormat.getShape() == Shape.OBJECT) {
                return null;
            }
            Class rawClass = collectionType.getRawClass();
            if (EnumSet.class.isAssignableFrom(rawClass)) {
                JavaType contentType = collectionType.getContentType();
                if (!contentType.isEnumType()) {
                    contentType = null;
                }
                jsonSerializer2 = StdContainerSerializers.enumSetSerializer(contentType);
            } else {
                Class<String> rawClass2 = collectionType.getContentType().getRawClass();
                if (isIndexedList(rawClass)) {
                    if (rawClass2 != String.class) {
                        jsonSerializer2 = StdContainerSerializers.indexedListSerializer(collectionType.getContentType(), z, typeSerializer, jsonSerializer);
                    } else if (jsonSerializer == null || ClassUtil.isJacksonStdImpl((Object) jsonSerializer)) {
                        jsonSerializer2 = IndexedStringListSerializer.instance;
                    }
                } else if (rawClass2 == String.class && (jsonSerializer == null || ClassUtil.isJacksonStdImpl((Object) jsonSerializer))) {
                    jsonSerializer2 = StringCollectionSerializer.instance;
                }
                if (jsonSerializer2 == null) {
                    jsonSerializer2 = StdContainerSerializers.collectionSerializer(collectionType.getContentType(), z, typeSerializer, jsonSerializer);
                }
            }
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier modifyCollectionSerializer : this._factoryConfig.serializerModifiers()) {
                jsonSerializer2 = modifyCollectionSerializer.modifyCollectionSerializer(serializationConfig, collectionType, beanDescription, jsonSerializer2);
            }
        }
        return jsonSerializer2;
    }

    /* access modifiers changed from: protected */
    public boolean isIndexedList(Class<?> cls) {
        return RandomAccess.class.isAssignableFrom(cls);
    }

    /* JADX WARNING: type inference failed for: r3v0 */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v4, types: [com.fasterxml.jackson.databind.JsonSerializer<?>] */
    /* JADX WARNING: type inference failed for: r3v5, types: [com.fasterxml.jackson.databind.JsonSerializer] */
    /* JADX WARNING: type inference failed for: r3v6, types: [com.fasterxml.jackson.databind.JsonSerializer] */
    /* JADX WARNING: type inference failed for: r3v9, types: [com.fasterxml.jackson.databind.ser.std.MapSerializer] */
    /* JADX WARNING: type inference failed for: r3v11, types: [com.fasterxml.jackson.databind.ser.std.EnumMapSerializer] */
    /* JADX WARNING: type inference failed for: r3v13, types: [com.fasterxml.jackson.databind.JsonSerializer] */
    /* JADX WARNING: type inference failed for: r3v14 */
    /* JADX WARNING: type inference failed for: r3v15 */
    /* JADX WARNING: type inference failed for: r3v16 */
    /* JADX WARNING: type inference failed for: r3v17 */
    /* JADX WARNING: type inference failed for: r3v18 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r3v1
      assigns: []
      uses: []
      mth insns count: 64
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at jadx.core.dex.visitors.typeinference.TypeSearch$$Lambda$100/183835416.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at jadx.core.dex.visitors.DepthTraversal$$Lambda$33/170174037.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonSerializer<?> buildMapSerializer(com.fasterxml.jackson.databind.SerializationConfig r12, com.fasterxml.jackson.databind.type.MapType r13, com.fasterxml.jackson.databind.BeanDescription r14, boolean r15, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r16, com.fasterxml.jackson.databind.jsontype.TypeSerializer r17, com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r18) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r11 = this;
            r0 = r11
            java.lang.Iterable r1 = r0.customSerializers()
            java.util.Iterator r1 = r1.iterator()
            r2 = 0
            r3 = r2
        L_0x000b:
            boolean r4 = r1.hasNext()
            if (r4 == 0) goto L_0x0027
            java.lang.Object r3 = r1.next()
            r4 = r3
            com.fasterxml.jackson.databind.ser.Serializers r4 = (com.fasterxml.jackson.databind.ser.Serializers) r4
            r5 = r12
            r6 = r13
            r7 = r14
            r8 = r16
            r9 = r17
            r10 = r18
            com.fasterxml.jackson.databind.JsonSerializer r3 = r4.findMapSerializer(r5, r6, r7, r8, r9, r10)
            if (r3 == 0) goto L_0x000b
        L_0x0027:
            if (r3 != 0) goto L_0x0074
            java.lang.Class<java.util.EnumMap> r1 = java.util.EnumMap.class
            java.lang.Class r3 = r13.getRawClass()
            boolean r1 = r1.isAssignableFrom(r3)
            if (r1 == 0) goto L_0x005c
            com.fasterxml.jackson.databind.JavaType r1 = r13.getKeyType()
            boolean r3 = r1.isEnumType()
            if (r3 == 0) goto L_0x004b
            java.lang.Class r1 = r1.getRawClass()
            com.fasterxml.jackson.databind.AnnotationIntrospector r2 = r12.getAnnotationIntrospector()
            com.fasterxml.jackson.databind.util.EnumValues r2 = com.fasterxml.jackson.databind.util.EnumValues.construct(r1, r2)
        L_0x004b:
            r6 = r2
            com.fasterxml.jackson.databind.ser.std.EnumMapSerializer r1 = new com.fasterxml.jackson.databind.ser.std.EnumMapSerializer
            com.fasterxml.jackson.databind.JavaType r4 = r13.getContentType()
            r3 = r1
            r5 = r15
            r7 = r17
            r8 = r18
            r3.<init>(r4, r5, r6, r7, r8)
            goto L_0x0074
        L_0x005c:
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r12.getAnnotationIntrospector()
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r2 = r14.getClassInfo()
            java.lang.String[] r3 = r1.findPropertiesToIgnore(r2)
            r4 = r13
            r5 = r15
            r6 = r17
            r7 = r16
            r8 = r18
            com.fasterxml.jackson.databind.ser.std.MapSerializer r3 = com.fasterxml.jackson.databind.ser.std.MapSerializer.construct(r3, r4, r5, r6, r7, r8)
        L_0x0074:
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r0._factoryConfig
            boolean r1 = r1.hasSerializerModifiers()
            if (r1 == 0) goto L_0x009a
            com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig r1 = r0._factoryConfig
            java.lang.Iterable r1 = r1.serializerModifiers()
            java.util.Iterator r1 = r1.iterator()
        L_0x0086:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x009a
            java.lang.Object r2 = r1.next()
            com.fasterxml.jackson.databind.ser.BeanSerializerModifier r2 = (com.fasterxml.jackson.databind.ser.BeanSerializerModifier) r2
            r4 = r12
            r5 = r13
            r6 = r14
            com.fasterxml.jackson.databind.JsonSerializer r3 = r2.modifyMapSerializer(r4, r5, r6, r3)
            goto L_0x0086
        L_0x009a:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.BasicSerializerFactory.buildMapSerializer(com.fasterxml.jackson.databind.SerializationConfig, com.fasterxml.jackson.databind.type.MapType, com.fasterxml.jackson.databind.BeanDescription, boolean, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.JsonSerializer):com.fasterxml.jackson.databind.JsonSerializer");
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildArraySerializer(SerializationConfig serializationConfig, ArrayType arrayType, BeanDescription beanDescription, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) throws JsonMappingException {
        JsonSerializer<?> jsonSerializer2 = null;
        for (Serializers findArraySerializer : customSerializers()) {
            jsonSerializer2 = findArraySerializer.findArraySerializer(serializationConfig, arrayType, beanDescription, typeSerializer, jsonSerializer);
            if (jsonSerializer2 != null) {
                break;
            }
        }
        if (jsonSerializer2 == null) {
            Class<String[]> rawClass = arrayType.getRawClass();
            if (jsonSerializer == null || ClassUtil.isJacksonStdImpl((Object) jsonSerializer)) {
                if (String[].class == rawClass) {
                    jsonSerializer2 = StringArraySerializer.instance;
                } else {
                    jsonSerializer2 = StdArraySerializers.findStandardImpl(rawClass);
                }
            }
            if (jsonSerializer2 == null) {
                jsonSerializer2 = new ObjectArraySerializer<>(arrayType.getContentType(), z, typeSerializer, jsonSerializer);
            }
        }
        if (this._factoryConfig.hasSerializerModifiers()) {
            for (BeanSerializerModifier modifyArraySerializer : this._factoryConfig.serializerModifiers()) {
                jsonSerializer2 = modifyArraySerializer.modifyArraySerializer(serializationConfig, arrayType, beanDescription, jsonSerializer2);
            }
        }
        return jsonSerializer2;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildIteratorSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        JavaType containedType = javaType.containedType(0);
        if (containedType == null) {
            containedType = TypeFactory.unknownType();
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, containedType);
        return StdContainerSerializers.iteratorSerializer(containedType, usesStaticTyping(serializationConfig, beanDescription, createTypeSerializer), createTypeSerializer);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildIterableSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription, boolean z) throws JsonMappingException {
        JavaType containedType = javaType.containedType(0);
        if (containedType == null) {
            containedType = TypeFactory.unknownType();
        }
        TypeSerializer createTypeSerializer = createTypeSerializer(serializationConfig, containedType);
        return StdContainerSerializers.iterableSerializer(containedType, usesStaticTyping(serializationConfig, beanDescription, createTypeSerializer), createTypeSerializer);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> buildEnumSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        Value findExpectedFormat = beanDescription.findExpectedFormat(null);
        if (findExpectedFormat == null || findExpectedFormat.getShape() != Shape.OBJECT) {
            JsonSerializer<?> construct = EnumSerializer.construct(javaType.getRawClass(), serializationConfig, beanDescription, findExpectedFormat);
            if (this._factoryConfig.hasSerializerModifiers()) {
                for (BeanSerializerModifier modifyEnumSerializer : this._factoryConfig.serializerModifiers()) {
                    construct = modifyEnumSerializer.modifyEnumSerializer(serializationConfig, javaType, beanDescription, construct);
                }
            }
            return construct;
        }
        ((BasicBeanDescription) beanDescription).removeProperty("declaringClass");
        return null;
    }

    /* access modifiers changed from: protected */
    public <T extends JavaType> T modifyTypeByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        Class findSerializationType = serializationConfig.getAnnotationIntrospector().findSerializationType(annotated);
        if (findSerializationType != null) {
            try {
                t = t.widenBy(findSerializationType);
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to widen type ");
                sb.append(t);
                sb.append(" with concrete-type annotation (value ");
                sb.append(findSerializationType.getName());
                sb.append("), method '");
                sb.append(annotated.getName());
                sb.append("': ");
                sb.append(e.getMessage());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return modifySecondaryTypesByAnnotation(serializationConfig, annotated, t);
    }

    protected static <T extends JavaType> T modifySecondaryTypesByAnnotation(SerializationConfig serializationConfig, Annotated annotated, T t) {
        AnnotationIntrospector annotationIntrospector = serializationConfig.getAnnotationIntrospector();
        if (!t.isContainerType()) {
            return t;
        }
        Class findSerializationKeyType = annotationIntrospector.findSerializationKeyType(annotated, t.getKeyType());
        if (findSerializationKeyType != null) {
            if (!(t instanceof MapType)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Illegal key-type annotation: type ");
                sb.append(t);
                sb.append(" is not a Map type");
                throw new IllegalArgumentException(sb.toString());
            }
            try {
                t = ((MapType) t).widenKey(findSerializationKeyType);
            } catch (IllegalArgumentException e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Failed to narrow key type ");
                sb2.append(t);
                sb2.append(" with key-type annotation (");
                sb2.append(findSerializationKeyType.getName());
                sb2.append("): ");
                sb2.append(e.getMessage());
                throw new IllegalArgumentException(sb2.toString());
            }
        }
        Class findSerializationContentType = annotationIntrospector.findSerializationContentType(annotated, t.getContentType());
        if (findSerializationContentType == null) {
            return t;
        }
        try {
            return t.widenContentsBy(findSerializationContentType);
        } catch (IllegalArgumentException e2) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Failed to narrow content type ");
            sb3.append(t);
            sb3.append(" with content-type annotation (");
            sb3.append(findSerializationContentType.getName());
            sb3.append("): ");
            sb3.append(e2.getMessage());
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findKeySerializer(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findKeySerializer = serializerProvider.getAnnotationIntrospector().findKeySerializer(annotated);
        if (findKeySerializer != null) {
            return serializerProvider.serializerInstance(annotated, findKeySerializer);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findContentSerializer(SerializerProvider serializerProvider, Annotated annotated) throws JsonMappingException {
        Object findContentSerializer = serializerProvider.getAnnotationIntrospector().findContentSerializer(annotated);
        if (findContentSerializer != null) {
            return serializerProvider.serializerInstance(annotated, findContentSerializer);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public final boolean usesStaticTyping(SerializationConfig serializationConfig, BeanDescription beanDescription, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return usesStaticTyping(serializationConfig, beanDescription, typeSerializer);
    }

    /* access modifiers changed from: protected */
    public boolean usesStaticTyping(SerializationConfig serializationConfig, BeanDescription beanDescription, TypeSerializer typeSerializer) {
        boolean z = false;
        if (typeSerializer != null) {
            return false;
        }
        Typing findSerializationTyping = serializationConfig.getAnnotationIntrospector().findSerializationTyping(beanDescription.getClassInfo());
        if (findSerializationTyping == null) {
            return serializationConfig.isEnabled(MapperFeature.USE_STATIC_TYPING);
        }
        if (findSerializationTyping == Typing.STATIC) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public Class<?> _verifyAsClass(Object obj, String str, Class<?> cls) {
        if (obj == null) {
            return null;
        }
        if (!(obj instanceof Class)) {
            StringBuilder sb = new StringBuilder();
            sb.append("AnnotationIntrospector.");
            sb.append(str);
            sb.append("() returned value of type ");
            sb.append(obj.getClass().getName());
            sb.append(": expected type JsonSerializer or Class<JsonSerializer> instead");
            throw new IllegalStateException(sb.toString());
        }
        Class<NoClass> cls2 = (Class) obj;
        if (cls2 == cls || cls2 == NoClass.class) {
            return null;
        }
        return cls2;
    }
}
