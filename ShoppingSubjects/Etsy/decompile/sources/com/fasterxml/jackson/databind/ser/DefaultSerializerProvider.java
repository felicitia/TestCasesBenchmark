package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.JsonSerializer.None;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.IdentityHashMap;

public abstract class DefaultSerializerProvider extends SerializerProvider implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient ArrayList<ObjectIdGenerator<?>> _objectIdGenerators;
    protected transient IdentityHashMap<Object, WritableObjectId> _seenObjectIds;

    public static final class Impl extends DefaultSerializerProvider {
        private static final long serialVersionUID = 1;

        public Impl() {
        }

        protected Impl(SerializerProvider serializerProvider, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
            super(serializerProvider, serializationConfig, serializerFactory);
        }

        public Impl createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
            return new Impl(this, serializationConfig, serializerFactory);
        }
    }

    public abstract DefaultSerializerProvider createInstance(SerializationConfig serializationConfig, SerializerFactory serializerFactory);

    protected DefaultSerializerProvider() {
    }

    protected DefaultSerializerProvider(SerializerProvider serializerProvider, SerializationConfig serializationConfig, SerializerFactory serializerFactory) {
        super(serializerProvider, serializationConfig, serializerFactory);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004b A[Catch:{ IOException -> 0x007a, Exception -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void serializeValue(com.fasterxml.jackson.core.JsonGenerator r6, java.lang.Object r7) throws java.io.IOException, com.fasterxml.jackson.core.JsonGenerationException {
        /*
            r5 = this;
            r0 = 1
            r1 = 0
            if (r7 != 0) goto L_0x000b
            com.fasterxml.jackson.databind.JsonSerializer r0 = r5.getDefaultNullValueSerializer()
            r2 = r0
        L_0x0009:
            r0 = r1
            goto L_0x0046
        L_0x000b:
            java.lang.Class r2 = r7.getClass()
            r3 = 0
            com.fasterxml.jackson.databind.JsonSerializer r2 = r5.findTypedValueSerializer(r2, r0, r3)
            com.fasterxml.jackson.databind.SerializationConfig r3 = r5._config
            java.lang.String r3 = r3.getRootName()
            if (r3 != 0) goto L_0x0039
            com.fasterxml.jackson.databind.SerializationConfig r0 = r5._config
            com.fasterxml.jackson.databind.SerializationFeature r1 = com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE
            boolean r0 = r0.isEnabled(r1)
            if (r0 == 0) goto L_0x0046
            r6.writeStartObject()
            com.fasterxml.jackson.databind.util.RootNameLookup r1 = r5._rootNames
            java.lang.Class r3 = r7.getClass()
            com.fasterxml.jackson.databind.SerializationConfig r4 = r5._config
            com.fasterxml.jackson.core.io.SerializedString r1 = r1.findRootName(r3, r4)
            r6.writeFieldName(r1)
            goto L_0x0046
        L_0x0039:
            int r4 = r3.length()
            if (r4 != 0) goto L_0x0040
            goto L_0x0009
        L_0x0040:
            r6.writeStartObject()
            r6.writeFieldName(r3)
        L_0x0046:
            r2.serialize(r7, r6, r5)     // Catch:{ IOException -> 0x007a, Exception -> 0x004f }
            if (r0 == 0) goto L_0x004e
            r6.writeEndObject()     // Catch:{ IOException -> 0x007a, Exception -> 0x004f }
        L_0x004e:
            return
        L_0x004f:
            r6 = move-exception
            java.lang.String r7 = r6.getMessage()
            if (r7 != 0) goto L_0x0074
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "[no message for "
            r7.append(r0)
            java.lang.Class r0 = r6.getClass()
            java.lang.String r0 = r0.getName()
            r7.append(r0)
            java.lang.String r0 = "]"
            r7.append(r0)
            java.lang.String r7 = r7.toString()
        L_0x0074:
            com.fasterxml.jackson.databind.JsonMappingException r0 = new com.fasterxml.jackson.databind.JsonMappingException
            r0.<init>(r7, r6)
            throw r0
        L_0x007a:
            r6 = move-exception
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(com.fasterxml.jackson.core.JsonGenerator, java.lang.Object):void");
    }

    public void serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType) throws IOException, JsonGenerationException {
        JsonSerializer jsonSerializer;
        boolean z;
        if (obj == null) {
            jsonSerializer = getDefaultNullValueSerializer();
            z = false;
        } else {
            if (!javaType.getRawClass().isAssignableFrom(obj.getClass())) {
                _reportIncompatibleRootType(obj, javaType);
            }
            JsonSerializer findTypedValueSerializer = findTypedValueSerializer(javaType, true, (BeanProperty) null);
            boolean isEnabled = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
            if (isEnabled) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName((SerializableString) this._rootNames.findRootName(javaType, (MapperConfig<?>) this._config));
            }
            jsonSerializer = findTypedValueSerializer;
            z = isEnabled;
        }
        try {
            jsonSerializer.serialize(obj, jsonGenerator, this);
            if (z) {
                jsonGenerator.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("[no message for ");
                sb.append(e2.getClass().getName());
                sb.append("]");
                message = sb.toString();
            }
            throw new JsonMappingException(message, (Throwable) e2);
        }
    }

    public void serializeValue(JsonGenerator jsonGenerator, Object obj, JavaType javaType, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        boolean z;
        JsonSerializer<Object> jsonSerializer2;
        if (obj == null) {
            jsonSerializer2 = getDefaultNullValueSerializer();
            z = false;
        } else {
            if (javaType != null && !javaType.getRawClass().isAssignableFrom(obj.getClass())) {
                _reportIncompatibleRootType(obj, javaType);
            }
            if (jsonSerializer == null) {
                jsonSerializer = findTypedValueSerializer(javaType, true, (BeanProperty) null);
            }
            boolean isEnabled = this._config.isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
            if (isEnabled) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName((SerializableString) this._rootNames.findRootName(javaType, (MapperConfig<?>) this._config));
            }
            jsonSerializer2 = jsonSerializer;
            z = isEnabled;
        }
        try {
            jsonSerializer2.serialize(obj, jsonGenerator, this);
            if (z) {
                jsonGenerator.writeEndObject();
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            String message = e2.getMessage();
            if (message == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("[no message for ");
                sb.append(e2.getClass().getName());
                sb.append("]");
                message = sb.toString();
            }
            throw new JsonMappingException(message, (Throwable) e2);
        }
    }

    public JsonSchema generateJsonSchema(Class<?> cls) throws JsonMappingException {
        if (cls == null) {
            throw new IllegalArgumentException("A class must be provided");
        }
        JsonSerializer findValueSerializer = findValueSerializer(cls, (BeanProperty) null);
        JsonNode schema = findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(this, null) : JsonSchema.getDefaultSchemaNode();
        if (schema instanceof ObjectNode) {
            return new JsonSchema((ObjectNode) schema);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Class ");
        sb.append(cls.getName());
        sb.append(" would not be serialized as a JSON object and therefore has no schema");
        throw new IllegalArgumentException(sb.toString());
    }

    public void acceptJsonFormatVisitor(JavaType javaType, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        if (javaType == null) {
            throw new IllegalArgumentException("A class must be provided");
        }
        jsonFormatVisitorWrapper.setProvider(this);
        findValueSerializer(javaType, (BeanProperty) null).acceptJsonFormatVisitor(jsonFormatVisitorWrapper, javaType);
    }

    public boolean hasSerializerFor(Class<?> cls) {
        boolean z = false;
        try {
            if (_findExplicitUntypedSerializer(cls) != null) {
                z = true;
            }
            return z;
        } catch (JsonMappingException unused) {
            return false;
        }
    }

    public int cachedSerializersCount() {
        return this._serializerCache.size();
    }

    public void flushCachedSerializers() {
        this._serializerCache.flush();
    }

    public WritableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator) {
        if (this._seenObjectIds == null) {
            this._seenObjectIds = new IdentityHashMap<>();
        } else {
            WritableObjectId writableObjectId = (WritableObjectId) this._seenObjectIds.get(obj);
            if (writableObjectId != null) {
                return writableObjectId;
            }
        }
        ObjectIdGenerator objectIdGenerator2 = null;
        if (this._objectIdGenerators != null) {
            int i = 0;
            int size = this._objectIdGenerators.size();
            while (true) {
                if (i >= size) {
                    break;
                }
                ObjectIdGenerator objectIdGenerator3 = (ObjectIdGenerator) this._objectIdGenerators.get(i);
                if (objectIdGenerator3.canUseFor(objectIdGenerator)) {
                    objectIdGenerator2 = objectIdGenerator3;
                    break;
                }
                i++;
            }
        } else {
            this._objectIdGenerators = new ArrayList<>(8);
        }
        if (objectIdGenerator2 == null) {
            objectIdGenerator2 = objectIdGenerator.newForSerialization(this);
            this._objectIdGenerators.add(objectIdGenerator2);
        }
        WritableObjectId writableObjectId2 = new WritableObjectId(objectIdGenerator2);
        this._seenObjectIds.put(obj, writableObjectId2);
        return writableObjectId2;
    }

    public JsonSerializer<Object> serializerInstance(Annotated annotated, Object obj) throws JsonMappingException {
        JsonSerializer jsonSerializer;
        JsonSerializer jsonSerializer2 = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof JsonSerializer) {
            jsonSerializer = (JsonSerializer) obj;
        } else if (!(obj instanceof Class)) {
            StringBuilder sb = new StringBuilder();
            sb.append("AnnotationIntrospector returned serializer definition of type ");
            sb.append(obj.getClass().getName());
            sb.append("; expected type JsonSerializer or Class<JsonSerializer> instead");
            throw new IllegalStateException(sb.toString());
        } else {
            Class<NoClass> cls = (Class) obj;
            if (cls == None.class || cls == NoClass.class) {
                return null;
            }
            if (!JsonSerializer.class.isAssignableFrom(cls)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("AnnotationIntrospector returned Class ");
                sb2.append(cls.getName());
                sb2.append("; expected Class<JsonSerializer>");
                throw new IllegalStateException(sb2.toString());
            }
            HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
            if (handlerInstantiator != null) {
                jsonSerializer2 = handlerInstantiator.serializerInstance(this._config, annotated, cls);
            }
            jsonSerializer = jsonSerializer2 == null ? (JsonSerializer) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers()) : jsonSerializer2;
        }
        return _handleResolvable(jsonSerializer);
    }
}
