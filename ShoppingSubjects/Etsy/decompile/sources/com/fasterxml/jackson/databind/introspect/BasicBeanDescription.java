package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.Converter.None;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BasicBeanDescription extends BeanDescription {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected AnnotatedMember _anyGetter;
    protected AnnotatedMethod _anySetterMethod;
    protected TypeBindings _bindings;
    protected final AnnotatedClass _classInfo;
    protected final MapperConfig<?> _config;
    protected Set<String> _ignoredPropertyNames;
    protected Map<Object, AnnotatedMember> _injectables;
    protected AnnotatedMethod _jsonValueMethod;
    protected ObjectIdInfo _objectIdInfo;
    protected final List<BeanPropertyDefinition> _properties;

    protected BasicBeanDescription(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass, List<BeanPropertyDefinition> list) {
        AnnotationIntrospector annotationIntrospector;
        super(javaType);
        this._config = mapperConfig;
        if (mapperConfig == null) {
            annotationIntrospector = null;
        } else {
            annotationIntrospector = mapperConfig.getAnnotationIntrospector();
        }
        this._annotationIntrospector = annotationIntrospector;
        this._classInfo = annotatedClass;
        this._properties = list;
    }

    protected BasicBeanDescription(POJOPropertiesCollector pOJOPropertiesCollector) {
        this(pOJOPropertiesCollector.getConfig(), pOJOPropertiesCollector.getType(), pOJOPropertiesCollector.getClassDef(), pOJOPropertiesCollector.getProperties());
        this._objectIdInfo = pOJOPropertiesCollector.getObjectIdInfo();
    }

    public static BasicBeanDescription forDeserialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        BasicBeanDescription basicBeanDescription = new BasicBeanDescription(pOJOPropertiesCollector);
        basicBeanDescription._anySetterMethod = pOJOPropertiesCollector.getAnySetterMethod();
        basicBeanDescription._ignoredPropertyNames = pOJOPropertiesCollector.getIgnoredPropertyNames();
        basicBeanDescription._injectables = pOJOPropertiesCollector.getInjectables();
        basicBeanDescription._jsonValueMethod = pOJOPropertiesCollector.getJsonValueMethod();
        return basicBeanDescription;
    }

    public static BasicBeanDescription forSerialization(POJOPropertiesCollector pOJOPropertiesCollector) {
        BasicBeanDescription basicBeanDescription = new BasicBeanDescription(pOJOPropertiesCollector);
        basicBeanDescription._jsonValueMethod = pOJOPropertiesCollector.getJsonValueMethod();
        basicBeanDescription._anyGetter = pOJOPropertiesCollector.getAnyGetter();
        return basicBeanDescription;
    }

    public static BasicBeanDescription forOtherUse(MapperConfig<?> mapperConfig, JavaType javaType, AnnotatedClass annotatedClass) {
        return new BasicBeanDescription(mapperConfig, javaType, annotatedClass, Collections.emptyList());
    }

    public boolean removeProperty(String str) {
        Iterator it = this._properties.iterator();
        while (it.hasNext()) {
            if (((BeanPropertyDefinition) it.next()).getName().equals(str)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public AnnotatedClass getClassInfo() {
        return this._classInfo;
    }

    public ObjectIdInfo getObjectIdInfo() {
        return this._objectIdInfo;
    }

    public List<BeanPropertyDefinition> findProperties() {
        return this._properties;
    }

    public AnnotatedMethod findJsonValueMethod() {
        return this._jsonValueMethod;
    }

    public Set<String> getIgnoredPropertyNames() {
        if (this._ignoredPropertyNames == null) {
            return Collections.emptySet();
        }
        return this._ignoredPropertyNames;
    }

    public boolean hasKnownClassAnnotations() {
        return this._classInfo.hasAnnotations();
    }

    public Annotations getClassAnnotations() {
        return this._classInfo.getAnnotations();
    }

    public TypeBindings bindingsForBeanType() {
        if (this._bindings == null) {
            this._bindings = new TypeBindings(this._config.getTypeFactory(), this._type);
        }
        return this._bindings;
    }

    public JavaType resolveType(Type type) {
        if (type == null) {
            return null;
        }
        return bindingsForBeanType().resolveType(type);
    }

    public AnnotatedConstructor findDefaultConstructor() {
        return this._classInfo.getDefaultConstructor();
    }

    public AnnotatedMethod findAnySetter() throws IllegalArgumentException {
        if (this._anySetterMethod != null) {
            Class<Object> rawParameterType = this._anySetterMethod.getRawParameterType(0);
            if (!(rawParameterType == String.class || rawParameterType == Object.class)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid 'any-setter' annotation on method ");
                sb.append(this._anySetterMethod.getName());
                sb.append("(): first argument not of type String or Object, but ");
                sb.append(rawParameterType.getName());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return this._anySetterMethod;
    }

    public Map<Object, AnnotatedMember> findInjectables() {
        return this._injectables;
    }

    public List<AnnotatedConstructor> getConstructors() {
        return this._classInfo.getConstructors();
    }

    public Object instantiateBean(boolean z) {
        AnnotatedConstructor defaultConstructor = this._classInfo.getDefaultConstructor();
        if (defaultConstructor == null) {
            return null;
        }
        if (z) {
            defaultConstructor.fixAccess();
        }
        try {
            return defaultConstructor.getAnnotated().newInstance(new Object[0]);
        } catch (Exception e) {
            e = e;
            while (e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof Error) {
                throw ((Error) e);
            } else if (e instanceof RuntimeException) {
                throw ((RuntimeException) e);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to instantiate bean of type ");
                sb.append(this._classInfo.getAnnotated().getName());
                sb.append(": (");
                sb.append(e.getClass().getName());
                sb.append(") ");
                sb.append(e.getMessage());
                throw new IllegalArgumentException(sb.toString(), e);
            }
        }
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        return this._classInfo.findMethod(str, clsArr);
    }

    public Value findExpectedFormat(Value value) {
        if (this._annotationIntrospector != null) {
            Value findFormat = this._annotationIntrospector.findFormat((Annotated) this._classInfo);
            if (findFormat != null) {
                return findFormat;
            }
        }
        return value;
    }

    public Converter<Object, Object> findSerializationConverter() {
        if (this._annotationIntrospector == null) {
            return null;
        }
        return _createConverter(this._annotationIntrospector.findSerializationConverter(this._classInfo));
    }

    public Include findSerializationInclusion(Include include) {
        if (this._annotationIntrospector == null) {
            return include;
        }
        return this._annotationIntrospector.findSerializationInclusion(this._classInfo, include);
    }

    public AnnotatedMember findAnyGetter() throws IllegalArgumentException {
        if (this._anyGetter != null) {
            if (!Map.class.isAssignableFrom(this._anyGetter.getRawType())) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid 'any-getter' annotation on method ");
                sb.append(this._anyGetter.getName());
                sb.append("(): return type is not instance of java.util.Map");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        return this._anyGetter;
    }

    public Map<String, AnnotatedMember> findBackReferenceProperties() {
        HashMap hashMap = null;
        for (BeanPropertyDefinition mutator : this._properties) {
            AnnotatedMember mutator2 = mutator.getMutator();
            if (mutator2 != null) {
                ReferenceProperty findReferenceType = this._annotationIntrospector.findReferenceType(mutator2);
                if (findReferenceType != null && findReferenceType.isBackReference()) {
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    String name = findReferenceType.getName();
                    if (hashMap.put(name, mutator2) != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Multiple back-reference properties with name '");
                        sb.append(name);
                        sb.append("'");
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
            }
        }
        return hashMap;
    }

    public List<AnnotatedMethod> getFactoryMethods() {
        List<AnnotatedMethod> staticMethods = this._classInfo.getStaticMethods();
        if (staticMethods.isEmpty()) {
            return staticMethods;
        }
        ArrayList arrayList = new ArrayList();
        for (AnnotatedMethod annotatedMethod : staticMethods) {
            if (isFactoryMethod(annotatedMethod)) {
                arrayList.add(annotatedMethod);
            }
        }
        return arrayList;
    }

    public Constructor<?> findSingleArgConstructor(Class<?>... clsArr) {
        for (AnnotatedConstructor annotatedConstructor : this._classInfo.getConstructors()) {
            if (annotatedConstructor.getParameterCount() == 1) {
                Class<?> rawParameterType = annotatedConstructor.getRawParameterType(0);
                for (Class<?> cls : clsArr) {
                    if (cls == rawParameterType) {
                        return annotatedConstructor.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    public Method findFactoryMethod(Class<?>... clsArr) {
        for (AnnotatedMethod annotatedMethod : this._classInfo.getStaticMethods()) {
            if (isFactoryMethod(annotatedMethod)) {
                Class rawParameterType = annotatedMethod.getRawParameterType(0);
                for (Class<?> isAssignableFrom : clsArr) {
                    if (rawParameterType.isAssignableFrom(isAssignableFrom)) {
                        return annotatedMethod.getAnnotated();
                    }
                }
                continue;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isFactoryMethod(AnnotatedMethod annotatedMethod) {
        if (!getBeanClass().isAssignableFrom(annotatedMethod.getRawReturnType())) {
            return false;
        }
        if (!this._annotationIntrospector.hasCreatorAnnotation(annotatedMethod) && !"valueOf".equals(annotatedMethod.getName())) {
            return false;
        }
        return true;
    }

    public List<String> findCreatorPropertyNames() {
        Object obj;
        int i = 0;
        List<String> list = null;
        while (i < 2) {
            for (AnnotatedWithParams annotatedWithParams : i == 0 ? getConstructors() : getFactoryMethods()) {
                int parameterCount = annotatedWithParams.getParameterCount();
                if (parameterCount >= 1) {
                    PropertyName findNameForDeserialization = this._annotationIntrospector.findNameForDeserialization(annotatedWithParams.getParameter(0));
                    if (findNameForDeserialization != null) {
                        if (list == null) {
                            list = new ArrayList<>();
                        }
                        list.add(findNameForDeserialization.getSimpleName());
                        for (int i2 = 1; i2 < parameterCount; i2++) {
                            PropertyName findNameForDeserialization2 = this._annotationIntrospector.findNameForDeserialization(annotatedWithParams.getParameter(i2));
                            if (findNameForDeserialization2 == null) {
                                obj = null;
                            } else {
                                obj = findNameForDeserialization2.getSimpleName();
                            }
                            list.add(obj);
                        }
                    }
                }
            }
            i++;
        }
        return list == null ? Collections.emptyList() : list;
    }

    public Class<?> findPOJOBuilder() {
        if (this._annotationIntrospector == null) {
            return null;
        }
        return this._annotationIntrospector.findPOJOBuilder(this._classInfo);
    }

    public JsonPOJOBuilder.Value findPOJOBuilderConfig() {
        if (this._annotationIntrospector == null) {
            return null;
        }
        return this._annotationIntrospector.findPOJOBuilderConfig(this._classInfo);
    }

    public Converter<Object, Object> findDeserializationConverter() {
        if (this._annotationIntrospector == null) {
            return null;
        }
        return _createConverter(this._annotationIntrospector.findDeserializationConverter(this._classInfo));
    }

    public LinkedHashMap<String, AnnotatedField> _findPropertyFields(Collection<String> collection, boolean z) {
        LinkedHashMap<String, AnnotatedField> linkedHashMap = new LinkedHashMap<>();
        for (BeanPropertyDefinition beanPropertyDefinition : this._properties) {
            AnnotatedField field = beanPropertyDefinition.getField();
            if (field != null) {
                String name = beanPropertyDefinition.getName();
                if (collection == null || !collection.contains(name)) {
                    linkedHashMap.put(name, field);
                }
            }
        }
        return linkedHashMap;
    }

    public Converter<Object, Object> _createConverter(Object obj) {
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
        HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
        if (handlerInstantiator != null) {
            converter = handlerInstantiator.converterInstance(this._config, this._classInfo, cls);
        }
        if (converter == null) {
            converter = (Converter) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers());
        }
        return converter;
    }
}
