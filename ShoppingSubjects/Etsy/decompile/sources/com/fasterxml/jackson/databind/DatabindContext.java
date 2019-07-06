package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.Converter.None;
import java.lang.reflect.Type;

public abstract class DatabindContext {
    public abstract Class<?> getActiveView();

    public abstract AnnotationIntrospector getAnnotationIntrospector();

    public abstract MapperConfig<?> getConfig();

    public abstract TypeFactory getTypeFactory();

    public final boolean isEnabled(MapperFeature mapperFeature) {
        return getConfig().isEnabled(mapperFeature);
    }

    public final boolean canOverrideAccessModifiers() {
        return getConfig().canOverrideAccessModifiers();
    }

    public JavaType constructType(Type type) {
        return getTypeFactory().constructType(type);
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        return getConfig().constructSpecializedType(javaType, cls);
    }

    public ObjectIdGenerator<?> objectIdGeneratorInstance(Annotated annotated, ObjectIdInfo objectIdInfo) throws JsonMappingException {
        ObjectIdGenerator objectIdGenerator;
        Class generatorType = objectIdInfo.getGeneratorType();
        MapperConfig config = getConfig();
        HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        if (handlerInstantiator == null) {
            objectIdGenerator = null;
        } else {
            objectIdGenerator = handlerInstantiator.objectIdGeneratorInstance(config, annotated, generatorType);
        }
        if (objectIdGenerator == null) {
            objectIdGenerator = (ObjectIdGenerator) ClassUtil.createInstance(generatorType, config.canOverrideAccessModifiers());
        }
        return objectIdGenerator.forScope(objectIdInfo.getScope());
    }

    public Converter<Object, Object> converterInstance(Annotated annotated, Object obj) throws JsonMappingException {
        Converter<Object, Object> converter = null;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Converter) {
            return (Converter) obj;
        }
        if (!(obj instanceof Class)) {
            StringBuilder sb = new StringBuilder();
            sb.append("AnnotationIntrospector returned Converter definition of type ");
            sb.append(obj.getClass().getName());
            sb.append("; expected type Converter or Class<Converter> instead");
            throw new IllegalStateException(sb.toString());
        }
        Class<NoClass> cls = (Class) obj;
        if (cls == None.class || cls == NoClass.class) {
            return null;
        }
        if (!Converter.class.isAssignableFrom(cls)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("AnnotationIntrospector returned Class ");
            sb2.append(cls.getName());
            sb2.append("; expected Class<Converter>");
            throw new IllegalStateException(sb2.toString());
        }
        MapperConfig config = getConfig();
        HandlerInstantiator handlerInstantiator = config.getHandlerInstantiator();
        if (handlerInstantiator != null) {
            converter = handlerInstantiator.converterInstance(config, annotated, cls);
        }
        if (converter == null) {
            converter = (Converter) ClassUtil.createInstance(cls, config.canOverrideAccessModifiers());
        }
        return converter;
    }
}
