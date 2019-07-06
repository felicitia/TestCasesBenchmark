package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonDeserializer.None;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.util.LinkedHashMap;

public abstract class DefaultDeserializationContext extends DeserializationContext implements Serializable {
    private static final long serialVersionUID = 1;
    protected transient LinkedHashMap<IdKey, ReadableObjectId> _objectIds;

    public static final class Impl extends DefaultDeserializationContext {
        private static final long serialVersionUID = 1;

        public Impl(DeserializerFactory deserializerFactory) {
            super(deserializerFactory, (DeserializerCache) null);
        }

        protected Impl(Impl impl, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
            super(impl, deserializationConfig, jsonParser, injectableValues);
        }

        protected Impl(Impl impl, DeserializerFactory deserializerFactory) {
            super((DefaultDeserializationContext) impl, deserializerFactory);
        }

        public DefaultDeserializationContext createInstance(DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
            return new Impl(this, deserializationConfig, jsonParser, injectableValues);
        }

        public DefaultDeserializationContext with(DeserializerFactory deserializerFactory) {
            return new Impl(this, deserializerFactory);
        }
    }

    public abstract DefaultDeserializationContext createInstance(DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues);

    public abstract DefaultDeserializationContext with(DeserializerFactory deserializerFactory);

    protected DefaultDeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        super(deserializerFactory, deserializerCache);
    }

    protected DefaultDeserializationContext(DefaultDeserializationContext defaultDeserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        super(defaultDeserializationContext, deserializationConfig, jsonParser, injectableValues);
    }

    protected DefaultDeserializationContext(DefaultDeserializationContext defaultDeserializationContext, DeserializerFactory deserializerFactory) {
        super((DeserializationContext) defaultDeserializationContext, deserializerFactory);
    }

    public ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator) {
        IdKey key = objectIdGenerator.key(obj);
        if (this._objectIds == null) {
            this._objectIds = new LinkedHashMap<>();
        } else {
            ReadableObjectId readableObjectId = (ReadableObjectId) this._objectIds.get(key);
            if (readableObjectId != null) {
                return readableObjectId;
            }
        }
        ReadableObjectId readableObjectId2 = new ReadableObjectId(obj);
        this._objectIds.put(key, readableObjectId2);
        return readableObjectId2;
    }

    public JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException {
        JsonDeserializer jsonDeserializer;
        JsonDeserializer jsonDeserializer2 = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof JsonDeserializer) {
            jsonDeserializer = (JsonDeserializer) obj;
        } else if (!(obj instanceof Class)) {
            StringBuilder sb = new StringBuilder();
            sb.append("AnnotationIntrospector returned deserializer definition of type ");
            sb.append(obj.getClass().getName());
            sb.append("; expected type JsonDeserializer or Class<JsonDeserializer> instead");
            throw new IllegalStateException(sb.toString());
        } else {
            Class<NoClass> cls = (Class) obj;
            if (cls == None.class || cls == NoClass.class) {
                return null;
            }
            if (!JsonDeserializer.class.isAssignableFrom(cls)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("AnnotationIntrospector returned Class ");
                sb2.append(cls.getName());
                sb2.append("; expected Class<JsonDeserializer>");
                throw new IllegalStateException(sb2.toString());
            }
            HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
            if (handlerInstantiator != null) {
                jsonDeserializer2 = handlerInstantiator.deserializerInstance(this._config, annotated, cls);
            }
            jsonDeserializer = jsonDeserializer2 == null ? (JsonDeserializer) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers()) : jsonDeserializer2;
        }
        if (jsonDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) jsonDeserializer).resolve(this);
        }
        return jsonDeserializer;
    }

    public final KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException {
        KeyDeserializer keyDeserializer;
        KeyDeserializer keyDeserializer2 = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof KeyDeserializer) {
            keyDeserializer = (KeyDeserializer) obj;
        } else if (!(obj instanceof Class)) {
            StringBuilder sb = new StringBuilder();
            sb.append("AnnotationIntrospector returned key deserializer definition of type ");
            sb.append(obj.getClass().getName());
            sb.append("; expected type KeyDeserializer or Class<KeyDeserializer> instead");
            throw new IllegalStateException(sb.toString());
        } else {
            Class<NoClass> cls = (Class) obj;
            if (cls == KeyDeserializer.None.class || cls == NoClass.class) {
                return null;
            }
            if (!KeyDeserializer.class.isAssignableFrom(cls)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("AnnotationIntrospector returned Class ");
                sb2.append(cls.getName());
                sb2.append("; expected Class<KeyDeserializer>");
                throw new IllegalStateException(sb2.toString());
            }
            HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
            if (handlerInstantiator != null) {
                keyDeserializer2 = handlerInstantiator.keyDeserializerInstance(this._config, annotated, cls);
            }
            keyDeserializer = keyDeserializer2 == null ? (KeyDeserializer) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers()) : keyDeserializer2;
        }
        if (keyDeserializer instanceof ResolvableDeserializer) {
            ((ResolvableDeserializer) keyDeserializer).resolve(this);
        }
        return keyDeserializer;
    }
}
