package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.NoClass;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JacksonDeserializers;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.PrintStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public abstract class BasicDeserializerFactory extends DeserializerFactory implements Serializable {
    private static final Class<?> CLASS_CHAR_BUFFER = CharSequence.class;
    private static final Class<?> CLASS_ITERABLE = Iterable.class;
    private static final Class<?> CLASS_OBJECT = Object.class;
    private static final Class<?> CLASS_STRING = String.class;
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks = new HashMap<>();
    static final HashMap<String, Class<? extends Map>> _mapFallbacks = new HashMap<>();
    protected final DeserializerFactoryConfig _factoryConfig;

    /* access modifiers changed from: protected */
    public abstract DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig);

    static {
        _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
        _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        _mapFallbacks.put("java.util.NavigableMap", TreeMap.class);
        try {
            _mapFallbacks.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
        } catch (Throwable th) {
            PrintStream printStream = System.err;
            StringBuilder sb = new StringBuilder();
            sb.append("Problems with (optional) types: ");
            sb.append(th);
            printStream.println(sb.toString());
        }
        _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
        _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
    }

    protected BasicDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        this._factoryConfig = deserializerFactoryConfig;
    }

    public DeserializerFactoryConfig getFactoryConfig() {
        return this._factoryConfig;
    }

    public final DeserializerFactory withAdditionalDeserializers(Deserializers deserializers) {
        return withConfig(this._factoryConfig.withAdditionalDeserializers(deserializers));
    }

    public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
        return withConfig(this._factoryConfig.withAdditionalKeyDeserializers(keyDeserializers));
    }

    public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        return withConfig(this._factoryConfig.withDeserializerModifier(beanDeserializerModifier));
    }

    public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
        return withConfig(this._factoryConfig.withAbstractTypeResolver(abstractTypeResolver));
    }

    public final DeserializerFactory withValueInstantiators(ValueInstantiators valueInstantiators) {
        return withConfig(this._factoryConfig.withValueInstantiators(valueInstantiators));
    }

    public JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        JavaType _mapAbstractType2;
        while (true) {
            _mapAbstractType2 = _mapAbstractType2(deserializationConfig, javaType);
            if (_mapAbstractType2 == null) {
                return javaType;
            }
            Class rawClass = javaType.getRawClass();
            Class rawClass2 = _mapAbstractType2.getRawClass();
            if (rawClass == rawClass2 || !rawClass.isAssignableFrom(rawClass2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid abstract type resolution from ");
                sb.append(javaType);
                sb.append(" to ");
                sb.append(_mapAbstractType2);
                sb.append(": latter is not a subtype of former");
            } else {
                javaType = _mapAbstractType2;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Invalid abstract type resolution from ");
        sb2.append(javaType);
        sb2.append(" to ");
        sb2.append(_mapAbstractType2);
        sb2.append(": latter is not a subtype of former");
        throw new IllegalArgumentException(sb2.toString());
    }

    private JavaType _mapAbstractType2(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        Class rawClass = javaType.getRawClass();
        if (this._factoryConfig.hasAbstractTypeResolvers()) {
            for (AbstractTypeResolver findTypeMapping : this._factoryConfig.abstractTypeResolvers()) {
                JavaType findTypeMapping2 = findTypeMapping.findTypeMapping(deserializationConfig, javaType);
                if (findTypeMapping2 != null && findTypeMapping2.getRawClass() != rawClass) {
                    return findTypeMapping2;
                }
            }
        }
        return null;
    }

    public ValueInstantiator findValueInstantiator(DeserializationContext deserializationContext, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        AnnotatedClass classInfo = beanDescription.getClassInfo();
        Object findValueInstantiator = deserializationContext.getAnnotationIntrospector().findValueInstantiator(classInfo);
        ValueInstantiator _valueInstantiatorInstance = findValueInstantiator != null ? _valueInstantiatorInstance(config, classInfo, findValueInstantiator) : null;
        if (_valueInstantiatorInstance == null) {
            _valueInstantiatorInstance = _findStdValueInstantiator(config, beanDescription);
            if (_valueInstantiatorInstance == null) {
                _valueInstantiatorInstance = _constructDefaultValueInstantiator(deserializationContext, beanDescription);
            }
        }
        if (this._factoryConfig.hasValueInstantiators()) {
            for (ValueInstantiators valueInstantiators : this._factoryConfig.valueInstantiators()) {
                _valueInstantiatorInstance = valueInstantiators.findValueInstantiator(config, beanDescription, _valueInstantiatorInstance);
                if (_valueInstantiatorInstance == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Broken registered ValueInstantiators (of type ");
                    sb.append(valueInstantiators.getClass().getName());
                    sb.append("): returned null ValueInstantiator");
                    throw new JsonMappingException(sb.toString());
                }
            }
        }
        if (_valueInstantiatorInstance.getIncompleteParameter() == null) {
            return _valueInstantiatorInstance;
        }
        AnnotatedParameter incompleteParameter = _valueInstantiatorInstance.getIncompleteParameter();
        AnnotatedWithParams owner = incompleteParameter.getOwner();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Argument #");
        sb2.append(incompleteParameter.getIndex());
        sb2.append(" of constructor ");
        sb2.append(owner);
        sb2.append(" has no property name annotation; must have name when multiple-paramater constructor annotated as Creator");
        throw new IllegalArgumentException(sb2.toString());
    }

    private ValueInstantiator _findStdValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        return JacksonDeserializers.findValueInstantiator(deserializationConfig, beanDescription);
    }

    /* access modifiers changed from: protected */
    public ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext deserializationContext, BeanDescription beanDescription) throws JsonMappingException {
        CreatorCollector creatorCollector = new CreatorCollector(beanDescription, deserializationContext.canOverrideAccessModifiers());
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        DeserializationConfig config = deserializationContext.getConfig();
        VisibilityChecker findAutoDetectVisibility = annotationIntrospector.findAutoDetectVisibility(beanDescription.getClassInfo(), config.getDefaultVisibilityChecker());
        _addDeserializerFactoryMethods(deserializationContext, beanDescription, findAutoDetectVisibility, annotationIntrospector, creatorCollector);
        if (beanDescription.getType().isConcrete()) {
            _addDeserializerConstructors(deserializationContext, beanDescription, findAutoDetectVisibility, annotationIntrospector, creatorCollector);
        }
        return creatorCollector.constructValueInstantiator(config);
    }

    public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig deserializationConfig, Annotated annotated, Object obj) throws JsonMappingException {
        if (obj == null) {
            return null;
        }
        if (obj instanceof ValueInstantiator) {
            return (ValueInstantiator) obj;
        }
        if (!(obj instanceof Class)) {
            StringBuilder sb = new StringBuilder();
            sb.append("AnnotationIntrospector returned key deserializer definition of type ");
            sb.append(obj.getClass().getName());
            sb.append("; expected type KeyDeserializer or Class<KeyDeserializer> instead");
            throw new IllegalStateException(sb.toString());
        }
        Class<NoClass> cls = (Class) obj;
        if (cls == NoClass.class) {
            return null;
        }
        if (!ValueInstantiator.class.isAssignableFrom(cls)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("AnnotationIntrospector returned Class ");
            sb2.append(cls.getName());
            sb2.append("; expected Class<ValueInstantiator>");
            throw new IllegalStateException(sb2.toString());
        }
        HandlerInstantiator handlerInstantiator = deserializationConfig.getHandlerInstantiator();
        if (handlerInstantiator != null) {
            ValueInstantiator valueInstantiatorInstance = handlerInstantiator.valueInstantiatorInstance(deserializationConfig, annotated, cls);
            if (valueInstantiatorInstance != null) {
                return valueInstantiatorInstance;
            }
        }
        return (ValueInstantiator) ClassUtil.createInstance(cls, deserializationConfig.canOverrideAccessModifiers());
    }

    /* access modifiers changed from: protected */
    public void _addDeserializerConstructors(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector) throws JsonMappingException {
        VisibilityChecker<?> visibilityChecker2;
        boolean z;
        PropertyName propertyName;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        CreatorCollector creatorCollector2 = creatorCollector;
        AnnotatedConstructor findDefaultConstructor = beanDescription.findDefaultConstructor();
        if (findDefaultConstructor != null && (!creatorCollector.hasDefaultCreator() || annotationIntrospector2.hasCreatorAnnotation(findDefaultConstructor))) {
            creatorCollector2.setDefaultCreator(findDefaultConstructor);
        }
        AnnotatedConstructor annotatedConstructor = null;
        String[] strArr = null;
        for (BeanPropertyDefinition beanPropertyDefinition : beanDescription.findProperties()) {
            if (beanPropertyDefinition.getConstructorParameter() != null) {
                AnnotatedParameter constructorParameter = beanPropertyDefinition.getConstructorParameter();
                AnnotatedWithParams owner = constructorParameter.getOwner();
                if (owner instanceof AnnotatedConstructor) {
                    if (annotatedConstructor == null) {
                        annotatedConstructor = (AnnotatedConstructor) owner;
                        strArr = new String[annotatedConstructor.getParameterCount()];
                    }
                    strArr[constructorParameter.getIndex()] = beanPropertyDefinition.getName();
                }
            }
        }
        for (AnnotatedConstructor annotatedConstructor2 : beanDescription.getConstructors()) {
            int parameterCount = annotatedConstructor2.getParameterCount();
            int i = 0;
            if (annotationIntrospector2.hasCreatorAnnotation(annotatedConstructor2) || annotatedConstructor2 == annotatedConstructor) {
                visibilityChecker2 = visibilityChecker;
                z = true;
            } else {
                visibilityChecker2 = visibilityChecker;
                z = false;
            }
            boolean isCreatorVisible = visibilityChecker2.isCreatorVisible((AnnotatedMember) annotatedConstructor2);
            if (parameterCount == 1) {
                _handleSingleArgumentConstructor(deserializationContext, beanDescription, visibilityChecker2, annotationIntrospector2, creatorCollector2, annotatedConstructor2, z, isCreatorVisible, annotatedConstructor2 == annotatedConstructor ? strArr[0] : null);
            } else if (z || isCreatorVisible) {
                CreatorProperty[] creatorPropertyArr = new CreatorProperty[parameterCount];
                int i2 = 0;
                int i3 = 0;
                AnnotatedParameter annotatedParameter = null;
                while (i < parameterCount) {
                    AnnotatedParameter parameter = annotatedConstructor2.getParameter(i);
                    String str = annotatedConstructor2 == annotatedConstructor ? strArr[i] : null;
                    if (str == null) {
                        if (parameter == null) {
                            propertyName = null;
                        } else {
                            propertyName = annotationIntrospector2.findNameForDeserialization(parameter);
                        }
                        if (propertyName == null) {
                            str = null;
                        } else {
                            str = propertyName.getSimpleName();
                        }
                    }
                    Object findInjectableValueId = annotationIntrospector2.findInjectableValueId(parameter);
                    if (str != null && str.length() > 0) {
                        i2++;
                        creatorPropertyArr[i] = constructCreatorProperty(deserializationContext, beanDescription, str, i, parameter, findInjectableValueId);
                    } else if (findInjectableValueId != null) {
                        i3++;
                        creatorPropertyArr[i] = constructCreatorProperty(deserializationContext, beanDescription, str, i, parameter, findInjectableValueId);
                    } else if (annotatedParameter == null) {
                        annotatedParameter = parameter;
                    }
                    i++;
                }
                if (z || i2 > 0 || i3 > 0) {
                    if (i2 + i3 == parameterCount) {
                        creatorCollector2.addPropertyCreator(annotatedConstructor2, creatorPropertyArr);
                    } else if (i2 == 0 && i3 + 1 == parameterCount) {
                        creatorCollector2.addDelegatingCreator(annotatedConstructor2, creatorPropertyArr);
                    } else {
                        creatorCollector2.addIncompeteParameter(annotatedParameter);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean _handleSingleArgumentConstructor(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedConstructor annotatedConstructor, boolean z, boolean z2, String str) throws JsonMappingException {
        String str2;
        PropertyName propertyName;
        String str3;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        CreatorCollector creatorCollector2 = creatorCollector;
        AnnotatedConstructor annotatedConstructor2 = annotatedConstructor;
        AnnotatedParameter parameter = annotatedConstructor2.getParameter(0);
        if (str == null) {
            if (parameter == null) {
                propertyName = null;
            } else {
                propertyName = annotationIntrospector2.findNameForDeserialization(parameter);
            }
            if (propertyName == null) {
                str3 = null;
            } else {
                str3 = propertyName.getSimpleName();
            }
            str2 = str3;
        } else {
            str2 = str;
        }
        Object findInjectableValueId = annotationIntrospector2.findInjectableValueId(parameter);
        if (findInjectableValueId != null || (str2 != null && str2.length() > 0)) {
            creatorCollector2.addPropertyCreator(annotatedConstructor2, new CreatorProperty[]{constructCreatorProperty(deserializationContext, beanDescription, str2, 0, parameter, findInjectableValueId)});
            return true;
        }
        Class<Double> rawParameterType = annotatedConstructor2.getRawParameterType(0);
        if (rawParameterType == String.class) {
            if (z || z2) {
                creatorCollector.addStringCreator(annotatedConstructor);
            }
            return true;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            if (z || z2) {
                creatorCollector.addIntCreator(annotatedConstructor);
            }
            return true;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            if (z || z2) {
                creatorCollector.addLongCreator(annotatedConstructor);
            }
            return true;
        } else if (rawParameterType == Double.TYPE || rawParameterType == Double.class) {
            if (z || z2) {
                creatorCollector.addDoubleCreator(annotatedConstructor);
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            creatorCollector2.addDelegatingCreator(annotatedConstructor2, null);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void _addDeserializerFactoryMethods(DeserializationContext deserializationContext, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector) throws JsonMappingException {
        PropertyName propertyName;
        String str;
        AnnotatedParameter annotatedParameter;
        PropertyName propertyName2;
        String str2;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        CreatorCollector creatorCollector2 = creatorCollector;
        DeserializationConfig config = deserializationContext.getConfig();
        for (AnnotatedMethod annotatedMethod : beanDescription.getFactoryMethods()) {
            boolean hasCreatorAnnotation = annotationIntrospector2.hasCreatorAnnotation(annotatedMethod);
            int parameterCount = annotatedMethod.getParameterCount();
            if (parameterCount != 0) {
                if (parameterCount == 1) {
                    AnnotatedParameter parameter = annotatedMethod.getParameter(0);
                    if (parameter == null) {
                        propertyName2 = null;
                    } else {
                        propertyName2 = annotationIntrospector2.findNameForDeserialization(parameter);
                    }
                    if (propertyName2 == null) {
                        str2 = null;
                    } else {
                        str2 = propertyName2.getSimpleName();
                    }
                    if (annotationIntrospector2.findInjectableValueId(parameter) == null && (str2 == null || str2.length() == 0)) {
                        _handleSingleArgumentFactory(config, beanDescription, visibilityChecker, annotationIntrospector2, creatorCollector2, annotatedMethod, hasCreatorAnnotation);
                    }
                } else if (!annotationIntrospector2.hasCreatorAnnotation(annotatedMethod)) {
                    continue;
                }
                CreatorProperty[] creatorPropertyArr = new CreatorProperty[parameterCount];
                int i = 0;
                int i2 = 0;
                AnnotatedParameter annotatedParameter2 = null;
                for (int i3 = 0; i3 < parameterCount; i3++) {
                    AnnotatedParameter parameter2 = annotatedMethod.getParameter(i3);
                    if (parameter2 == null) {
                        propertyName = null;
                    } else {
                        propertyName = annotationIntrospector2.findNameForDeserialization(parameter2);
                    }
                    if (propertyName == null) {
                        str = null;
                    } else {
                        str = propertyName.getSimpleName();
                    }
                    Object findInjectableValueId = annotationIntrospector2.findInjectableValueId(parameter2);
                    if (str == null || str.length() <= 0) {
                        String str3 = str;
                        AnnotatedParameter annotatedParameter3 = parameter2;
                        annotatedParameter = annotatedParameter2;
                        if (findInjectableValueId != null) {
                            i2++;
                            creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, str3, i3, annotatedParameter3, findInjectableValueId);
                        } else if (annotatedParameter == null) {
                            annotatedParameter2 = annotatedParameter3;
                        }
                    } else {
                        i++;
                        annotatedParameter = annotatedParameter2;
                        creatorPropertyArr[i3] = constructCreatorProperty(deserializationContext, beanDescription, str, i3, parameter2, findInjectableValueId);
                    }
                    annotatedParameter2 = annotatedParameter;
                }
                AnnotatedParameter annotatedParameter4 = annotatedParameter2;
                if (hasCreatorAnnotation || i > 0 || i2 > 0) {
                    if (i + i2 == parameterCount) {
                        creatorCollector2.addPropertyCreator(annotatedMethod, creatorPropertyArr);
                    } else if (i == 0 && i2 + 1 == parameterCount) {
                        creatorCollector2.addDelegatingCreator(annotatedMethod, creatorPropertyArr);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Argument #");
                        sb.append(annotatedParameter4.getIndex());
                        sb.append(" of factory method ");
                        sb.append(annotatedMethod);
                        sb.append(" has no property name annotation; must have name when multiple-paramater constructor annotated as Creator");
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
            } else if (hasCreatorAnnotation) {
                creatorCollector2.setDefaultCreator(annotatedMethod);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean _handleSingleArgumentFactory(DeserializationConfig deserializationConfig, BeanDescription beanDescription, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector annotationIntrospector, CreatorCollector creatorCollector, AnnotatedMethod annotatedMethod, boolean z) throws JsonMappingException {
        Class<Boolean> rawParameterType = annotatedMethod.getRawParameterType(0);
        if (rawParameterType == String.class) {
            if (z || visibilityChecker.isCreatorVisible((AnnotatedMember) annotatedMethod)) {
                creatorCollector.addStringCreator(annotatedMethod);
            }
            return true;
        } else if (rawParameterType == Integer.TYPE || rawParameterType == Integer.class) {
            if (z || visibilityChecker.isCreatorVisible((AnnotatedMember) annotatedMethod)) {
                creatorCollector.addIntCreator(annotatedMethod);
            }
            return true;
        } else if (rawParameterType == Long.TYPE || rawParameterType == Long.class) {
            if (z || visibilityChecker.isCreatorVisible((AnnotatedMember) annotatedMethod)) {
                creatorCollector.addLongCreator(annotatedMethod);
            }
            return true;
        } else if (rawParameterType == Double.TYPE || rawParameterType == Double.class) {
            if (z || visibilityChecker.isCreatorVisible((AnnotatedMember) annotatedMethod)) {
                creatorCollector.addDoubleCreator(annotatedMethod);
            }
            return true;
        } else if (rawParameterType == Boolean.TYPE || rawParameterType == Boolean.class) {
            if (z || visibilityChecker.isCreatorVisible((AnnotatedMember) annotatedMethod)) {
                creatorCollector.addBooleanCreator(annotatedMethod);
            }
            return true;
        } else if (!annotationIntrospector.hasCreatorAnnotation(annotatedMethod)) {
            return false;
        } else {
            creatorCollector.addDelegatingCreator(annotatedMethod, null);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public CreatorProperty constructCreatorProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, String str, int i, AnnotatedParameter annotatedParameter, Object obj) throws JsonMappingException {
        Boolean bool;
        boolean booleanValue;
        DeserializationContext deserializationContext2 = deserializationContext;
        AnnotatedParameter annotatedParameter2 = annotatedParameter;
        DeserializationConfig config = deserializationContext2.getConfig();
        AnnotationIntrospector annotationIntrospector = deserializationContext2.getAnnotationIntrospector();
        if (annotationIntrospector == null) {
            bool = null;
        } else {
            bool = annotationIntrospector.hasRequiredMarker(annotatedParameter2);
        }
        if (bool == null) {
            booleanValue = false;
        } else {
            booleanValue = bool.booleanValue();
        }
        boolean z = booleanValue;
        JavaType constructType = config.getTypeFactory().constructType(annotatedParameter.getParameterType(), beanDescription.bindingsForBeanType());
        Std std = new Std(str, constructType, annotationIntrospector.findWrapperName(annotatedParameter2), beanDescription.getClassAnnotations(), annotatedParameter2, z);
        JavaType resolveType = resolveType(deserializationContext2, beanDescription, constructType, annotatedParameter2);
        if (resolveType != constructType) {
            std = std.withType(resolveType);
        }
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext2, annotatedParameter2);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext2, annotatedParameter2, resolveType);
        TypeDeserializer typeDeserializer = (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, modifyTypeByAnnotation);
        }
        CreatorProperty creatorProperty = new CreatorProperty(str, modifyTypeByAnnotation, std.getWrapperName(), typeDeserializer, beanDescription.getClassAnnotations(), annotatedParameter2, i, obj, std.isRequired());
        return findDeserializerFromAnnotation != null ? creatorProperty.withValueDeserializer(findDeserializerFromAnnotation) : creatorProperty;
    }

    public JsonDeserializer<?> createArrayDeserializer(DeserializationContext deserializationContext, ArrayType arrayType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        JavaType contentType = arrayType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> _findCustomArrayDeserializer = _findCustomArrayDeserializer(arrayType, config, beanDescription, typeDeserializer2, jsonDeserializer);
        if (_findCustomArrayDeserializer == null) {
            if (jsonDeserializer == null) {
                Class<String> rawClass = contentType.getRawClass();
                if (contentType.isPrimitive()) {
                    return PrimitiveArrayDeserializers.forType(rawClass);
                }
                if (rawClass == String.class) {
                    return StringArrayDeserializer.instance;
                }
            }
            if (_findCustomArrayDeserializer == null) {
                _findCustomArrayDeserializer = new ObjectArrayDeserializer<>(arrayType, jsonDeserializer, typeDeserializer2);
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyArrayDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomArrayDeserializer = modifyArrayDeserializer.modifyArrayDeserializer(config, arrayType, beanDescription, _findCustomArrayDeserializer);
            }
        }
        return _findCustomArrayDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType arrayType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findArrayDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findArrayDeserializer2 = findArrayDeserializer.findArrayDeserializer(arrayType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findArrayDeserializer2 != null) {
                return findArrayDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext deserializationContext, CollectionType collectionType, BeanDescription beanDescription) throws JsonMappingException {
        CollectionType collectionType2;
        JavaType contentType = collectionType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        JsonDeserializer<?> _findCustomCollectionDeserializer = _findCustomCollectionDeserializer(collectionType, config, beanDescription, typeDeserializer2, jsonDeserializer);
        if (_findCustomCollectionDeserializer == null) {
            Class rawClass = collectionType.getRawClass();
            if (jsonDeserializer == null && EnumSet.class.isAssignableFrom(rawClass)) {
                _findCustomCollectionDeserializer = new EnumSetDeserializer<>(contentType, null);
            }
        }
        if (_findCustomCollectionDeserializer == null) {
            if (collectionType.isInterface() || collectionType.isAbstract()) {
                CollectionType _mapAbstractCollectionType = _mapAbstractCollectionType(collectionType, config);
                if (_mapAbstractCollectionType == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Can not find a deserializer for non-concrete Collection type ");
                    sb.append(collectionType);
                    throw new IllegalArgumentException(sb.toString());
                }
                collectionType2 = _mapAbstractCollectionType;
                beanDescription = config.introspectForCreation(_mapAbstractCollectionType);
            } else {
                collectionType2 = collectionType;
            }
            ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
            if (!findValueInstantiator.canCreateUsingDefault() && collectionType2.getRawClass() == ArrayBlockingQueue.class) {
                ArrayBlockingQueueDeserializer arrayBlockingQueueDeserializer = new ArrayBlockingQueueDeserializer(collectionType2, jsonDeserializer, typeDeserializer2, findValueInstantiator, null);
                return arrayBlockingQueueDeserializer;
            } else if (contentType.getRawClass() == String.class) {
                _findCustomCollectionDeserializer = new StringCollectionDeserializer<>(collectionType2, jsonDeserializer, findValueInstantiator);
            } else {
                _findCustomCollectionDeserializer = new CollectionDeserializer<>(collectionType2, jsonDeserializer, typeDeserializer2, findValueInstantiator);
            }
        } else {
            collectionType2 = collectionType;
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyCollectionDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomCollectionDeserializer = modifyCollectionDeserializer.modifyCollectionDeserializer(config, collectionType2, beanDescription, _findCustomCollectionDeserializer);
            }
        }
        return _findCustomCollectionDeserializer;
    }

    /* access modifiers changed from: protected */
    public CollectionType _mapAbstractCollectionType(JavaType javaType, DeserializationConfig deserializationConfig) {
        Class cls = (Class) _collectionFallbacks.get(javaType.getRawClass().getName());
        if (cls == null) {
            return null;
        }
        return (CollectionType) deserializationConfig.constructSpecializedType(javaType, cls);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType collectionType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findCollectionDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionDeserializer2 = findCollectionDeserializer.findCollectionDeserializer(collectionType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findCollectionDeserializer2 != null) {
                return findCollectionDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext deserializationContext, CollectionLikeType collectionLikeType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType contentType = collectionLikeType.getContentType();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = deserializationContext.getConfig();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        JsonDeserializer<?> _findCustomCollectionLikeDeserializer = _findCustomCollectionLikeDeserializer(collectionLikeType, config, beanDescription, typeDeserializer == null ? findTypeDeserializer(config, contentType) : typeDeserializer, jsonDeserializer);
        if (_findCustomCollectionLikeDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyCollectionLikeDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomCollectionLikeDeserializer = modifyCollectionLikeDeserializer.modifyCollectionLikeDeserializer(config, collectionLikeType, beanDescription, _findCustomCollectionLikeDeserializer);
            }
        }
        return _findCustomCollectionLikeDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType collectionLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findCollectionLikeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findCollectionLikeDeserializer2 = findCollectionLikeDeserializer.findCollectionLikeDeserializer(collectionLikeType, deserializationConfig, beanDescription, typeDeserializer, jsonDeserializer);
            if (findCollectionLikeDeserializer2 != null) {
                return findCollectionLikeDeserializer2;
            }
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.fasterxml.jackson.databind.JsonDeserializer] */
    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v4, types: [com.fasterxml.jackson.databind.JsonDeserializer<?>] */
    /* JADX WARNING: type inference failed for: r0v5, types: [com.fasterxml.jackson.databind.JsonDeserializer] */
    /* JADX WARNING: type inference failed for: r0v6, types: [com.fasterxml.jackson.databind.JsonDeserializer] */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r11v0, types: [com.fasterxml.jackson.databind.deser.std.MapDeserializer] */
    /* JADX WARNING: type inference failed for: r0v14 */
    /* JADX WARNING: type inference failed for: r0v27, types: [com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer] */
    /* JADX WARNING: type inference failed for: r0v29 */
    /* JADX WARNING: type inference failed for: r0v30 */
    /* JADX WARNING: type inference failed for: r0v31 */
    /* JADX WARNING: type inference failed for: r0v32 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v7
      assigns: []
      uses: []
      mth insns count: 90
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
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00d3  */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonDeserializer<?> createMapDeserializer(com.fasterxml.jackson.databind.DeserializationContext r18, com.fasterxml.jackson.databind.type.MapType r19, com.fasterxml.jackson.databind.BeanDescription r20) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r17 = this;
            r7 = r17
            r8 = r19
            com.fasterxml.jackson.databind.DeserializationConfig r9 = r18.getConfig()
            com.fasterxml.jackson.databind.JavaType r10 = r19.getKeyType()
            com.fasterxml.jackson.databind.JavaType r0 = r19.getContentType()
            java.lang.Object r1 = r0.getValueHandler()
            r15 = r1
            com.fasterxml.jackson.databind.JsonDeserializer r15 = (com.fasterxml.jackson.databind.JsonDeserializer) r15
            java.lang.Object r1 = r10.getValueHandler()
            r14 = r1
            com.fasterxml.jackson.databind.KeyDeserializer r14 = (com.fasterxml.jackson.databind.KeyDeserializer) r14
            java.lang.Object r1 = r0.getTypeHandler()
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r1 = (com.fasterxml.jackson.databind.jsontype.TypeDeserializer) r1
            if (r1 != 0) goto L_0x002c
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r0 = r7.findTypeDeserializer(r9, r0)
            r13 = r0
            goto L_0x002d
        L_0x002c:
            r13 = r1
        L_0x002d:
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r20
            r4 = r14
            r5 = r13
            r6 = r15
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r0._findCustomMapDeserializer(r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x00c9
            java.lang.Class r1 = r19.getRawClass()
            java.lang.Class<java.util.EnumMap> r2 = java.util.EnumMap.class
            boolean r2 = r2.isAssignableFrom(r1)
            if (r2 == 0) goto L_0x0063
            java.lang.Class r0 = r10.getRawClass()
            if (r0 == 0) goto L_0x005b
            boolean r0 = r0.isEnum()
            if (r0 != 0) goto L_0x0054
            goto L_0x005b
        L_0x0054:
            com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer r0 = new com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer
            r2 = 0
            r0.<init>(r8, r2, r15, r13)
            goto L_0x0063
        L_0x005b:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Can not construct EnumMap; generic (key) type not available"
            r0.<init>(r1)
            throw r0
        L_0x0063:
            if (r0 != 0) goto L_0x00c9
            boolean r0 = r19.isInterface()
            if (r0 != 0) goto L_0x0077
            boolean r0 = r19.isAbstract()
            if (r0 == 0) goto L_0x0072
            goto L_0x0077
        L_0x0072:
            r0 = r18
            r1 = r20
            goto L_0x00a9
        L_0x0077:
            java.util.HashMap<java.lang.String, java.lang.Class<? extends java.util.Map>> r0 = _mapFallbacks
            java.lang.String r1 = r1.getName()
            java.lang.Object r0 = r0.get(r1)
            java.lang.Class r0 = (java.lang.Class) r0
            if (r0 != 0) goto L_0x009c
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Can not find a deserializer for non-concrete Map type "
            r1.append(r2)
            r1.append(r8)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x009c:
            com.fasterxml.jackson.databind.JavaType r0 = r9.constructSpecializedType(r8, r0)
            com.fasterxml.jackson.databind.type.MapType r0 = (com.fasterxml.jackson.databind.type.MapType) r0
            com.fasterxml.jackson.databind.BeanDescription r1 = r9.introspectForCreation(r0)
            r8 = r0
            r0 = r18
        L_0x00a9:
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r7.findValueInstantiator(r0, r1)
            com.fasterxml.jackson.databind.deser.std.MapDeserializer r2 = new com.fasterxml.jackson.databind.deser.std.MapDeserializer
            r11 = r2
            r12 = r8
            r3 = r13
            r13 = r0
            r16 = r3
            r11.<init>(r12, r13, r14, r15, r16)
            com.fasterxml.jackson.databind.AnnotationIntrospector r0 = r9.getAnnotationIntrospector()
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r3 = r1.getClassInfo()
            java.lang.String[] r0 = r0.findPropertiesToIgnore(r3)
            r2.setIgnorableProperties(r0)
            r0 = r2
            goto L_0x00cb
        L_0x00c9:
            r1 = r20
        L_0x00cb:
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r2 = r7._factoryConfig
            boolean r2 = r2.hasDeserializerModifiers()
            if (r2 == 0) goto L_0x00ee
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r2 = r7._factoryConfig
            java.lang.Iterable r2 = r2.deserializerModifiers()
            java.util.Iterator r2 = r2.iterator()
        L_0x00dd:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00ee
            java.lang.Object r3 = r2.next()
            com.fasterxml.jackson.databind.deser.BeanDeserializerModifier r3 = (com.fasterxml.jackson.databind.deser.BeanDeserializerModifier) r3
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r3.modifyMapDeserializer(r9, r8, r1, r0)
            goto L_0x00dd
        L_0x00ee:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory.createMapDeserializer(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.type.MapType, com.fasterxml.jackson.databind.BeanDescription):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext deserializationContext, MapLikeType mapLikeType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType keyType = mapLikeType.getKeyType();
        JavaType contentType = mapLikeType.getContentType();
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer jsonDeserializer = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDeserializer = (KeyDeserializer) keyType.getValueHandler();
        TypeDeserializer typeDeserializer = (TypeDeserializer) contentType.getTypeHandler();
        if (typeDeserializer == null) {
            typeDeserializer = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> _findCustomMapLikeDeserializer = _findCustomMapLikeDeserializer(mapLikeType, config, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
        if (_findCustomMapLikeDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyMapLikeDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomMapLikeDeserializer = modifyMapLikeDeserializer.modifyMapLikeDeserializer(config, mapLikeType, beanDescription, _findCustomMapLikeDeserializer);
            }
        }
        return _findCustomMapLikeDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomMapDeserializer(MapType mapType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findMapDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapDeserializer2 = findMapDeserializer.findMapDeserializer(mapType, deserializationConfig, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapDeserializer2 != null) {
                return findMapDeserializer2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType mapLikeType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, KeyDeserializer keyDeserializer, TypeDeserializer typeDeserializer, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        for (Deserializers findMapLikeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findMapLikeDeserializer2 = findMapLikeDeserializer.findMapLikeDeserializer(mapLikeType, deserializationConfig, beanDescription, keyDeserializer, typeDeserializer, jsonDeserializer);
            if (findMapLikeDeserializer2 != null) {
                return findMapLikeDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createEnumDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        Class rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomEnumDeserializer = _findCustomEnumDeserializer(rawClass, config, beanDescription);
        if (_findCustomEnumDeserializer == null) {
            Iterator it = beanDescription.getFactoryMethods().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AnnotatedMethod annotatedMethod = (AnnotatedMethod) it.next();
                if (deserializationContext.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                    if (annotatedMethod.getParameterCount() != 1 || !annotatedMethod.getRawReturnType().isAssignableFrom(rawClass)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unsuitable method (");
                        sb.append(annotatedMethod);
                        sb.append(") decorated with @JsonCreator (for Enum type ");
                        sb.append(rawClass.getName());
                        sb.append(")");
                        throw new IllegalArgumentException(sb.toString());
                    }
                    _findCustomEnumDeserializer = EnumDeserializer.deserializerForCreator(config, rawClass, annotatedMethod);
                }
            }
            if (_findCustomEnumDeserializer == null) {
                _findCustomEnumDeserializer = new EnumDeserializer<>(constructEnumResolver(rawClass, config, beanDescription.findJsonValueMethod()));
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyEnumDeserializer : this._factoryConfig.deserializerModifiers()) {
                _findCustomEnumDeserializer = modifyEnumDeserializer.modifyEnumDeserializer(config, javaType, beanDescription, _findCustomEnumDeserializer);
            }
        }
        return _findCustomEnumDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        for (Deserializers findEnumDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findEnumDeserializer2 = findEnumDeserializer.findEnumDeserializer(cls, deserializationConfig, beanDescription);
            if (findEnumDeserializer2 != null) {
                return findEnumDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        Class rawClass = javaType.getRawClass();
        JsonDeserializer<?> _findCustomTreeNodeDeserializer = _findCustomTreeNodeDeserializer(rawClass, deserializationConfig, beanDescription);
        if (_findCustomTreeNodeDeserializer != null) {
            return _findCustomTreeNodeDeserializer;
        }
        return JsonNodeDeserializer.getDeserializer(rawClass);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> cls, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        for (Deserializers findTreeNodeDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> findTreeNodeDeserializer2 = findTreeNodeDeserializer.findTreeNodeDeserializer(cls, deserializationConfig, beanDescription);
            if (findTreeNodeDeserializer2 != null) {
                return findTreeNodeDeserializer2;
            }
        }
        return null;
    }

    public TypeDeserializer findTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException {
        AnnotatedClass classInfo = deserializationConfig.introspectClassAnnotations(javaType.getRawClass()).getClassInfo();
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findTypeResolver = annotationIntrospector.findTypeResolver(deserializationConfig, classInfo, javaType);
        Collection collection = null;
        if (findTypeResolver == null) {
            findTypeResolver = deserializationConfig.getDefaultTyper(javaType);
            if (findTypeResolver == null) {
                return null;
            }
        } else {
            collection = deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(classInfo, (MapperConfig<?>) deserializationConfig, annotationIntrospector);
        }
        if (findTypeResolver.getDefaultImpl() == null && javaType.isAbstract()) {
            JavaType mapAbstractType = mapAbstractType(deserializationConfig, javaType);
            if (!(mapAbstractType == null || mapAbstractType.getRawClass() == javaType.getRawClass())) {
                findTypeResolver = findTypeResolver.defaultImpl(mapAbstractType.getRawClass());
            }
        }
        return findTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, collection);
    }

    public KeyDeserializer createKeyDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        KeyDeserializer keyDeserializer = null;
        if (this._factoryConfig.hasKeyDeserializers()) {
            BeanDescription introspectClassAnnotations = config.introspectClassAnnotations(javaType.getRawClass());
            for (KeyDeserializers findKeyDeserializer : this._factoryConfig.keyDeserializers()) {
                keyDeserializer = findKeyDeserializer.findKeyDeserializer(javaType, config, introspectClassAnnotations);
                if (keyDeserializer != null) {
                    break;
                }
            }
        }
        if (keyDeserializer == null) {
            if (javaType.isEnumType()) {
                return _createEnumKeyDeserializer(deserializationContext, javaType);
            }
            keyDeserializer = StdKeyDeserializers.findStringBasedKeyDeserializer(config, javaType);
        }
        if (keyDeserializer != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyKeyDeserializer : this._factoryConfig.deserializerModifiers()) {
                keyDeserializer = modifyKeyDeserializer.modifyKeyDeserializer(config, javaType, keyDeserializer);
            }
        }
        return keyDeserializer;
    }

    private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDescription introspect = config.introspect(javaType);
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, introspect.getClassInfo());
        if (findDeserializerFromAnnotation != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType, findDeserializerFromAnnotation);
        }
        Class rawClass = javaType.getRawClass();
        if (_findCustomEnumDeserializer(rawClass, config, introspect) != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, javaType, findDeserializerFromAnnotation);
        }
        EnumResolver constructEnumResolver = constructEnumResolver(rawClass, config, introspect.findJsonValueMethod());
        for (AnnotatedMethod annotatedMethod : introspect.getFactoryMethods()) {
            if (config.getAnnotationIntrospector().hasCreatorAnnotation(annotatedMethod)) {
                if (annotatedMethod.getParameterCount() != 1 || !annotatedMethod.getRawReturnType().isAssignableFrom(rawClass)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unsuitable method (");
                    sb.append(annotatedMethod);
                    sb.append(") decorated with @JsonCreator (for Enum type ");
                    sb.append(rawClass.getName());
                    sb.append(")");
                    throw new IllegalArgumentException(sb.toString());
                } else if (annotatedMethod.getGenericParameterType(0) != String.class) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Parameter #0 type for factory method (");
                    sb2.append(annotatedMethod);
                    sb2.append(") not suitable, must be java.lang.String");
                    throw new IllegalArgumentException(sb2.toString());
                } else {
                    if (config.canOverrideAccessModifiers()) {
                        ClassUtil.checkAndFixAccess(annotatedMethod.getMember());
                    }
                    return StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver, annotatedMethod);
                }
            }
        }
        return StdKeyDeserializers.constructEnumKeyDeserializer(constructEnumResolver);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyTypeResolver = annotationIntrospector.findPropertyTypeResolver(deserializationConfig, annotatedMember, javaType);
        if (findPropertyTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, javaType);
        }
        return findPropertyTypeResolver.buildTypeDeserializer(deserializationConfig, javaType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector, javaType));
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationConfig.getAnnotationIntrospector();
        TypeResolverBuilder findPropertyContentTypeResolver = annotationIntrospector.findPropertyContentTypeResolver(deserializationConfig, annotatedMember, javaType);
        JavaType contentType = javaType.getContentType();
        if (findPropertyContentTypeResolver == null) {
            return findTypeDeserializer(deserializationConfig, contentType);
        }
        return findPropertyContentTypeResolver.buildTypeDeserializer(deserializationConfig, contentType, deserializationConfig.getSubtypeResolver().collectAndResolveSubtypes(annotatedMember, deserializationConfig, annotationIntrospector, contentType));
    }

    public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        Class<?> rawClass = javaType.getRawClass();
        String name = rawClass.getName();
        if (rawClass.isPrimitive() || name.startsWith("java.")) {
            if (rawClass == CLASS_OBJECT) {
                return UntypedObjectDeserializer.instance;
            }
            if (rawClass == CLASS_STRING || rawClass == CLASS_CHAR_BUFFER) {
                return StringDeserializer.instance;
            }
            if (rawClass == CLASS_ITERABLE) {
                return createCollectionDeserializer(deserializationContext, deserializationContext.getTypeFactory().constructCollectionType(Collection.class, javaType.containedTypeCount() > 0 ? javaType.containedType(0) : TypeFactory.unknownType()), beanDescription);
            }
            JsonDeserializer<?> find = NumberDeserializers.find(rawClass, name);
            if (find == null) {
                find = DateDeserializers.find(rawClass, name);
                if (find == null) {
                    find = JdkDeserializers.find(rawClass, name);
                }
            }
            return find;
        } else if (name.startsWith("com.fasterxml.")) {
            return JacksonDeserializers.find(rawClass);
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext deserializationContext, Annotated annotated) throws JsonMappingException {
        Object findDeserializer = deserializationContext.getAnnotationIntrospector().findDeserializer(annotated);
        if (findDeserializer == null) {
            return null;
        }
        return deserializationContext.deserializerInstance(annotated, findDeserializer);
    }

    /* access modifiers changed from: protected */
    public <T extends JavaType> T modifyTypeByAnnotation(DeserializationContext deserializationContext, Annotated annotated, T t) throws JsonMappingException {
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        Class findDeserializationType = annotationIntrospector.findDeserializationType(annotated, t);
        if (findDeserializationType != null) {
            try {
                t = t.narrowBy(findDeserializationType);
            } catch (IllegalArgumentException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to narrow type ");
                sb.append(t);
                sb.append(" with concrete-type annotation (value ");
                sb.append(findDeserializationType.getName());
                sb.append("), method '");
                sb.append(annotated.getName());
                sb.append("': ");
                sb.append(e.getMessage());
                throw new JsonMappingException(sb.toString(), null, e);
            }
        }
        if (!t.isContainerType()) {
            return t;
        }
        Class findDeserializationKeyType = annotationIntrospector.findDeserializationKeyType(annotated, t.getKeyType());
        if (findDeserializationKeyType != null) {
            if (!(t instanceof MapLikeType)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Illegal key-type annotation: type ");
                sb2.append(t);
                sb2.append(" is not a Map(-like) type");
                throw new JsonMappingException(sb2.toString());
            }
            try {
                t = ((MapLikeType) t).narrowKey(findDeserializationKeyType);
            } catch (IllegalArgumentException e2) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Failed to narrow key type ");
                sb3.append(t);
                sb3.append(" with key-type annotation (");
                sb3.append(findDeserializationKeyType.getName());
                sb3.append("): ");
                sb3.append(e2.getMessage());
                throw new JsonMappingException(sb3.toString(), null, e2);
            }
        }
        JavaType keyType = t.getKeyType();
        if (keyType != null && keyType.getValueHandler() == null) {
            KeyDeserializer keyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotated, annotationIntrospector.findKeyDeserializer(annotated));
            if (keyDeserializerInstance != null) {
                t = ((MapLikeType) t).withKeyValueHandler(keyDeserializerInstance);
                t.getKeyType();
            }
        }
        Class findDeserializationContentType = annotationIntrospector.findDeserializationContentType(annotated, t.getContentType());
        if (findDeserializationContentType != null) {
            try {
                t = t.narrowContentsBy(findDeserializationContentType);
            } catch (IllegalArgumentException e3) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Failed to narrow content type ");
                sb4.append(t);
                sb4.append(" with content-type annotation (");
                sb4.append(findDeserializationContentType.getName());
                sb4.append("): ");
                sb4.append(e3.getMessage());
                throw new JsonMappingException(sb4.toString(), null, e3);
            }
        }
        if (t.getContentType().getValueHandler() != null) {
            return t;
        }
        JsonDeserializer deserializerInstance = deserializationContext.deserializerInstance(annotated, annotationIntrospector.findContentDeserializer(annotated));
        return deserializerInstance != null ? t.withContentValueHandler(deserializerInstance) : t;
    }

    /* access modifiers changed from: protected */
    public JavaType resolveType(DeserializationContext deserializationContext, BeanDescription beanDescription, JavaType javaType, AnnotatedMember annotatedMember) throws JsonMappingException {
        TypeDeserializer typeDeserializer;
        if (javaType.isContainerType()) {
            AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
            if (javaType.getKeyType() != null) {
                KeyDeserializer keyDeserializerInstance = deserializationContext.keyDeserializerInstance(annotatedMember, annotationIntrospector.findKeyDeserializer(annotatedMember));
                if (keyDeserializerInstance != null) {
                    javaType = ((MapLikeType) javaType).withKeyValueHandler(keyDeserializerInstance);
                    javaType.getKeyType();
                }
            }
            JsonDeserializer deserializerInstance = deserializationContext.deserializerInstance(annotatedMember, annotationIntrospector.findContentDeserializer(annotatedMember));
            if (deserializerInstance != null) {
                javaType = javaType.withContentValueHandler(deserializerInstance);
            }
            if (annotatedMember instanceof AnnotatedMember) {
                TypeDeserializer findPropertyContentTypeDeserializer = findPropertyContentTypeDeserializer(deserializationContext.getConfig(), javaType, annotatedMember);
                if (findPropertyContentTypeDeserializer != null) {
                    javaType = javaType.withContentTypeHandler(findPropertyContentTypeDeserializer);
                }
            }
        }
        if (annotatedMember instanceof AnnotatedMember) {
            typeDeserializer = findPropertyTypeDeserializer(deserializationContext.getConfig(), javaType, annotatedMember);
        } else {
            typeDeserializer = findTypeDeserializer(deserializationContext.getConfig(), javaType);
        }
        return typeDeserializer != null ? javaType.withTypeHandler(typeDeserializer) : javaType;
    }

    /* access modifiers changed from: protected */
    public EnumResolver<?> constructEnumResolver(Class<?> cls, DeserializationConfig deserializationConfig, AnnotatedMethod annotatedMethod) {
        if (annotatedMethod != null) {
            Method annotated = annotatedMethod.getAnnotated();
            if (deserializationConfig.canOverrideAccessModifiers()) {
                ClassUtil.checkAndFixAccess(annotated);
            }
            return EnumResolver.constructUnsafeUsingMethod(cls, annotated);
        } else if (deserializationConfig.isEnabled(DeserializationFeature.READ_ENUMS_USING_TO_STRING)) {
            return EnumResolver.constructUnsafeUsingToString(cls);
        } else {
            return EnumResolver.constructUnsafe(cls, deserializationConfig.getAnnotationIntrospector());
        }
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _findJsonValueFor(DeserializationConfig deserializationConfig, JavaType javaType) {
        if (javaType == null) {
            return null;
        }
        return deserializationConfig.introspect(javaType).findJsonValueMethod();
    }
}
