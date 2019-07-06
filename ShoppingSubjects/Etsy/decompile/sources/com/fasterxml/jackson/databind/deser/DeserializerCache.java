package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public final class DeserializerCache implements Serializable {
    private static final long serialVersionUID = 1;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _cachedDeserializers = new ConcurrentHashMap<>(64, 0.75f, 2);
    protected final HashMap<JavaType, JsonDeserializer<Object>> _incompleteDeserializers = new HashMap<>(8);

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        this._incompleteDeserializers.clear();
        return this;
    }

    public int cachedDeserializersCount() {
        return this._cachedDeserializers.size();
    }

    public void flushCachedDeserializers() {
        this._cachedDeserializers.clear();
    }

    public JsonDeserializer<Object> findValueDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> _findCachedDeserializer = _findCachedDeserializer(javaType);
        if (_findCachedDeserializer != null) {
            return _findCachedDeserializer;
        }
        JsonDeserializer<Object> _createAndCacheValueDeserializer = _createAndCacheValueDeserializer(deserializationContext, deserializerFactory, javaType);
        if (_createAndCacheValueDeserializer == null) {
            _createAndCacheValueDeserializer = _handleUnknownValueDeserializer(javaType);
        }
        return _createAndCacheValueDeserializer;
    }

    public KeyDeserializer findKeyDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        KeyDeserializer createKeyDeserializer = deserializerFactory.createKeyDeserializer(deserializationContext, javaType);
        if (createKeyDeserializer == null) {
            return _handleUnknownKeyDeserializer(javaType);
        }
        if (createKeyDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) createKeyDeserializer).resolve(deserializationContext);
        }
        return createKeyDeserializer;
    }

    public boolean hasValueDeserializerFor(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) {
        JsonDeserializer _findCachedDeserializer = _findCachedDeserializer(javaType);
        boolean z = false;
        if (_findCachedDeserializer == null) {
            try {
                _findCachedDeserializer = _createAndCacheValueDeserializer(deserializationContext, deserializerFactory, javaType);
            } catch (Exception unused) {
                return false;
            }
        }
        if (_findCachedDeserializer != null) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findCachedDeserializer(JavaType javaType) {
        if (javaType != null) {
            return (JsonDeserializer) this._cachedDeserializers.get(javaType);
        }
        throw new IllegalArgumentException("Null JavaType passed");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0033, code lost:
        return r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> _createAndCacheValueDeserializer(com.fasterxml.jackson.databind.DeserializationContext r4, com.fasterxml.jackson.databind.deser.DeserializerFactory r5, com.fasterxml.jackson.databind.JavaType r6) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r3 = this;
            java.util.HashMap<com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r0 = r3._incompleteDeserializers
            monitor-enter(r0)
            com.fasterxml.jackson.databind.JsonDeserializer r1 = r3._findCachedDeserializer(r6)     // Catch:{ all -> 0x0045 }
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            return r1
        L_0x000b:
            java.util.HashMap<com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r1 = r3._incompleteDeserializers     // Catch:{ all -> 0x0045 }
            int r1 = r1.size()     // Catch:{ all -> 0x0045 }
            if (r1 <= 0) goto L_0x001f
            java.util.HashMap<com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r2 = r3._incompleteDeserializers     // Catch:{ all -> 0x0045 }
            java.lang.Object r2 = r2.get(r6)     // Catch:{ all -> 0x0045 }
            com.fasterxml.jackson.databind.JsonDeserializer r2 = (com.fasterxml.jackson.databind.JsonDeserializer) r2     // Catch:{ all -> 0x0045 }
            if (r2 == 0) goto L_0x001f
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            return r2
        L_0x001f:
            com.fasterxml.jackson.databind.JsonDeserializer r4 = r3._createAndCache2(r4, r5, r6)     // Catch:{ all -> 0x0034 }
            if (r1 != 0) goto L_0x0032
            java.util.HashMap<com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r5 = r3._incompleteDeserializers     // Catch:{ all -> 0x0045 }
            int r5 = r5.size()     // Catch:{ all -> 0x0045 }
            if (r5 <= 0) goto L_0x0032
            java.util.HashMap<com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r5 = r3._incompleteDeserializers     // Catch:{ all -> 0x0045 }
            r5.clear()     // Catch:{ all -> 0x0045 }
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            return r4
        L_0x0034:
            r4 = move-exception
            if (r1 != 0) goto L_0x0044
            java.util.HashMap<com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r5 = r3._incompleteDeserializers     // Catch:{ all -> 0x0045 }
            int r5 = r5.size()     // Catch:{ all -> 0x0045 }
            if (r5 <= 0) goto L_0x0044
            java.util.HashMap<com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object>> r5 = r3._incompleteDeserializers     // Catch:{ all -> 0x0045 }
            r5.clear()     // Catch:{ all -> 0x0045 }
        L_0x0044:
            throw r4     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0045 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.DeserializerCache._createAndCacheValueDeserializer(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.deser.DeserializerFactory, com.fasterxml.jackson.databind.JavaType):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _createAndCache2(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        try {
            JsonDeserializer<Object> _createDeserializer = _createDeserializer(deserializationContext, deserializerFactory, javaType);
            if (_createDeserializer == null) {
                return null;
            }
            boolean z = _createDeserializer instanceof ResolvableDeserializer;
            boolean isCachable = _createDeserializer.isCachable();
            if (z) {
                this._incompleteDeserializers.put(javaType, _createDeserializer);
                ((ResolvableDeserializer) _createDeserializer).resolve(deserializationContext);
                this._incompleteDeserializers.remove(javaType);
            }
            if (isCachable) {
                this._cachedDeserializers.put(javaType, _createDeserializer);
            }
            return _createDeserializer;
        } catch (IllegalArgumentException e) {
            throw new JsonMappingException(e.getMessage(), null, e);
        }
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _createDeserializer(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        if (javaType.isAbstract() || javaType.isMapLikeType() || javaType.isCollectionLikeType()) {
            javaType = deserializerFactory.mapAbstractType(config, javaType);
        }
        BeanDescription introspect = config.introspect(javaType);
        JsonDeserializer<Object> findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, introspect.getClassInfo());
        if (findDeserializerFromAnnotation != null) {
            return findDeserializerFromAnnotation;
        }
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext, introspect.getClassInfo(), javaType);
        if (modifyTypeByAnnotation != javaType) {
            introspect = config.introspect(modifyTypeByAnnotation);
            javaType = modifyTypeByAnnotation;
        }
        Class findPOJOBuilder = introspect.findPOJOBuilder();
        if (findPOJOBuilder != null) {
            return deserializerFactory.createBuilderBasedDeserializer(deserializationContext, javaType, introspect, findPOJOBuilder);
        }
        Converter findDeserializationConverter = introspect.findDeserializationConverter();
        if (findDeserializationConverter == null) {
            return _createDeserializer2(deserializationContext, deserializerFactory, javaType, introspect);
        }
        JavaType inputType = findDeserializationConverter.getInputType(deserializationContext.getTypeFactory());
        return new StdDelegatingDeserializer(findDeserializationConverter, inputType, _createDeserializer2(deserializationContext, deserializerFactory, inputType, introspect));
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _createDeserializer2(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        if (javaType.isEnumType()) {
            return deserializerFactory.createEnumDeserializer(deserializationContext, javaType, beanDescription);
        }
        if (javaType.isContainerType()) {
            if (javaType.isArrayType()) {
                return deserializerFactory.createArrayDeserializer(deserializationContext, (ArrayType) javaType, beanDescription);
            }
            if (javaType.isMapLikeType()) {
                MapLikeType mapLikeType = (MapLikeType) javaType;
                if (mapLikeType.isTrueMapType()) {
                    return deserializerFactory.createMapDeserializer(deserializationContext, (MapType) mapLikeType, beanDescription);
                }
                return deserializerFactory.createMapLikeDeserializer(deserializationContext, mapLikeType, beanDescription);
            } else if (javaType.isCollectionLikeType()) {
                Value findExpectedFormat = beanDescription.findExpectedFormat(null);
                if (findExpectedFormat == null || findExpectedFormat.getShape() != Shape.OBJECT) {
                    CollectionLikeType collectionLikeType = (CollectionLikeType) javaType;
                    if (collectionLikeType.isTrueCollectionType()) {
                        return deserializerFactory.createCollectionDeserializer(deserializationContext, (CollectionType) collectionLikeType, beanDescription);
                    }
                    return deserializerFactory.createCollectionLikeDeserializer(deserializationContext, collectionLikeType, beanDescription);
                }
            }
        }
        if (JsonNode.class.isAssignableFrom(javaType.getRawClass())) {
            return deserializerFactory.createTreeDeserializer(config, javaType, beanDescription);
        }
        return deserializerFactory.createBeanDeserializer(deserializationContext, javaType, beanDescription);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        Object findDeserializer = deserializationContext.getAnnotationIntrospector().findDeserializer(annotated);
        if (findDeserializer == null) {
            return null;
        }
        return findConvertingDeserializer(deserializationContext, annotated, deserializationContext.deserializerInstance(annotated, findDeserializer));
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext deserializationContext, Annotated annotated, JsonDeserializer<Object> jsonDeserializer) throws JsonMappingException {
        Converter findConverter = findConverter(deserializationContext, annotated);
        if (findConverter == null) {
            return jsonDeserializer;
        }
        return new StdDelegatingDeserializer(findConverter, findConverter.getInputType(deserializationContext.getTypeFactory()), jsonDeserializer);
    }

    /* access modifiers changed from: protected */
    public Converter<Object, Object> findConverter(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        Object findDeserializationConverter = deserializationContext.getAnnotationIntrospector().findDeserializationConverter(annotated);
        if (findDeserializationConverter == null) {
            return null;
        }
        return deserializationContext.converterInstance(annotated, findDeserializationConverter);
    }

    private JavaType modifyTypeByAnnotation(DeserializationContext deserializationContext, Annotated annotated, JavaType javaType) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        Class findDeserializationType = annotationIntrospector.findDeserializationType(annotated, javaType);
        JsonDeserializer jsonDeserializer = null;
        if (findDeserializationType != null) {
            try {
                javaType = javaType.narrowBy(findDeserializationType);
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to narrow type ");
                sb.append(javaType);
                sb.append(" with concrete-type annotation (value ");
                sb.append(findDeserializationType.getName());
                sb.append("), method '");
                sb.append(annotated.getName());
                sb.append("': ");
                sb.append(e.getMessage());
                throw new JsonMappingException(sb.toString(), null, e);
            }
        }
        if (!javaType.isContainerType()) {
            return javaType;
        }
        Class findDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated, javaType.getKeyType());
        if (findDeserializationKeyType != null) {
            if (!(javaType instanceof MapLikeType)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Illegal key-type annotation: type ");
                sb2.append(javaType);
                sb2.append(" is not a Map(-like) type");
                throw new JsonMappingException(sb2.toString());
            }
            try {
                javaType = ((MapLikeType) javaType).narrowKey(findDeserializationKeyType);
            } catch (IllegalArgumentException e2) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Failed to narrow key type ");
                sb3.append(javaType);
                sb3.append(" with key-type annotation (");
                sb3.append(findDeserializationKeyType.getName());
                sb3.append("): ");
                sb3.append(e2.getMessage());
                throw new JsonMappingException(sb3.toString(), null, e2);
            }
        }
        JavaType keyType = javaType.getKeyType();
        if (keyType != null && keyType.getValueHandler() == null) {
            Object findKeyDeserializer = annotationIntrospector.findKeyDeserializer(annotated);
            if (findKeyDeserializer != null) {
                KeyDeserializer keyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotated, findKeyDeserializer);
                if (keyDeserializerInstance != null) {
                    javaType = ((MapLikeType) javaType).withKeyValueHandler(keyDeserializerInstance);
                    javaType.getKeyType();
                }
            }
        }
        Class findDeserializationContentType = annotationIntrospector.findDeserializationContentType(annotated, javaType.getContentType());
        if (findDeserializationContentType != null) {
            try {
                javaType = javaType.narrowContentsBy(findDeserializationContentType);
            } catch (IllegalArgumentException e3) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Failed to narrow content type ");
                sb4.append(javaType);
                sb4.append(" with content-type annotation (");
                sb4.append(findDeserializationContentType.getName());
                sb4.append("): ");
                sb4.append(e3.getMessage());
                throw new JsonMappingException(sb4.toString(), null, e3);
            }
        }
        if (javaType.getContentType().getValueHandler() != null) {
            return javaType;
        }
        Object findContentDeserializer = annotationIntrospector.findContentDeserializer(annotated);
        if (findContentDeserializer == null) {
            return javaType;
        }
        if (findContentDeserializer instanceof JsonDeserializer) {
            JsonDeserializer jsonDeserializer2 = (JsonDeserializer) findContentDeserializer;
        } else {
            Class _verifyAsClass = _verifyAsClass(findContentDeserializer, "findContentDeserializer", None.class);
            if (_verifyAsClass != null) {
                jsonDeserializer = deserializationContext.deserializerInstance(annotated, _verifyAsClass);
            }
        }
        return jsonDeserializer != null ? javaType.withContentValueHandler(jsonDeserializer) : javaType;
    }

    private Class<?> _verifyAsClass(Object obj, String str, Class<?> cls) {
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

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _handleUnknownValueDeserializer(JavaType javaType) throws JsonMappingException {
        if (!ClassUtil.isConcrete(javaType.getRawClass())) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can not find a Value deserializer for abstract type ");
            sb.append(javaType);
            throw new JsonMappingException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Can not find a Value deserializer for type ");
        sb2.append(javaType);
        throw new JsonMappingException(sb2.toString());
    }

    /* access modifiers changed from: protected */
    public KeyDeserializer _handleUnknownKeyDeserializer(JavaType javaType) throws JsonMappingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Can not find a (Map) Key deserializer for type ");
        sb.append(javaType);
        throw new JsonMappingException(sb.toString());
    }
}
