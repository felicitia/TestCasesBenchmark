package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers.AtomicReferenceDeserializer;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class BeanDeserializerFactory extends BasicDeserializerFactory implements Serializable {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    private static final Class<?>[] NO_VIEWS = new Class[0];
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());
    private static final long serialVersionUID = 1;

    public BeanDeserializerFactory(DeserializerFactoryConfig deserializerFactoryConfig) {
        super(deserializerFactoryConfig);
    }

    public DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig) {
        if (this._factoryConfig == deserializerFactoryConfig) {
            return this;
        }
        if (getClass() == BeanDeserializerFactory.class) {
            return new BeanDeserializerFactory(deserializerFactoryConfig);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Subtype of BeanDeserializerFactory (");
        sb.append(getClass().getName());
        sb.append(") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with ");
        sb.append("additional deserializer definitions");
        throw new IllegalStateException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        for (Deserializers findBeanDeserializer : this._factoryConfig.deserializers()) {
            JsonDeserializer<Object> findBeanDeserializer2 = findBeanDeserializer.findBeanDeserializer(javaType, deserializationConfig, beanDescription);
            if (findBeanDeserializer2 != null) {
                return findBeanDeserializer2;
            }
        }
        return null;
    }

    public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        JsonDeserializer<Object> _findCustomBeanDeserializer = _findCustomBeanDeserializer(javaType, config, beanDescription);
        if (_findCustomBeanDeserializer != null) {
            return _findCustomBeanDeserializer;
        }
        if (javaType.isThrowable()) {
            return buildThrowableDeserializer(deserializationContext, javaType, beanDescription);
        }
        if (javaType.isAbstract()) {
            JavaType materializeAbstractType = materializeAbstractType(deserializationContext, javaType, beanDescription);
            if (materializeAbstractType != null) {
                return buildBeanDeserializer(deserializationContext, materializeAbstractType, config.introspect(materializeAbstractType));
            }
        }
        JsonDeserializer<Object> findStdDeserializer = findStdDeserializer(deserializationContext, javaType, beanDescription);
        if (findStdDeserializer != null) {
            return findStdDeserializer;
        }
        if (!isPotentialBeanType(javaType.getRawClass())) {
            return null;
        }
        return buildBeanDeserializer(deserializationContext, javaType, beanDescription);
    }

    public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription, Class<?> cls) throws JsonMappingException {
        return buildBuilderBasedDeserializer(deserializationContext, javaType, deserializationContext.getConfig().introspectForBuilder(deserializationContext.constructType(cls)));
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> findStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType javaType2;
        JsonDeserializer<?> findDefaultDeserializer = findDefaultDeserializer(deserializationContext, javaType, beanDescription);
        if (findDefaultDeserializer != null) {
            return findDefaultDeserializer;
        }
        if (!AtomicReference.class.isAssignableFrom(javaType.getRawClass())) {
            return findOptionalStdDeserializer(deserializationContext, javaType, beanDescription);
        }
        JavaType[] findTypeParameters = deserializationContext.getTypeFactory().findTypeParameters(javaType, AtomicReference.class);
        if (findTypeParameters == null || findTypeParameters.length < 1) {
            javaType2 = TypeFactory.unknownType();
        } else {
            javaType2 = findTypeParameters[0];
        }
        return new AtomicReferenceDeserializer(javaType2);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> findOptionalStdDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        return OptionalHandlerFactory.instance.findDeserializer(javaType, deserializationContext.getConfig(), beanDescription);
    }

    /* access modifiers changed from: protected */
    public JavaType materializeAbstractType(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JavaType type = beanDescription.getType();
        for (AbstractTypeResolver resolveAbstractType : this._factoryConfig.abstractTypeResolvers()) {
            JavaType resolveAbstractType2 = resolveAbstractType.resolveAbstractType(deserializationContext.getConfig(), type);
            if (resolveAbstractType2 != null) {
                return resolveAbstractType2;
            }
        }
        return null;
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        JsonDeserializer<Object> jsonDeserializer;
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        DeserializationConfig config = deserializationContext.getConfig();
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
            }
        }
        if (!javaType.isAbstract() || findValueInstantiator.canInstantiate()) {
            jsonDeserializer = constructBeanDeserializerBuilder.build();
        } else {
            jsonDeserializer = constructBeanDeserializerBuilder.buildAbstract();
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                jsonDeserializer = modifyDeserializer.modifyDeserializer(config, beanDescription, jsonDeserializer);
            }
        }
        return jsonDeserializer;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        String str;
        ValueInstantiator findValueInstantiator = findValueInstantiator(deserializationContext, beanDescription);
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator);
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addObjectIdReader(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addReferenceProperties(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        addInjectables(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        Value findPOJOBuilderConfig = beanDescription.findPOJOBuilderConfig();
        if (findPOJOBuilderConfig == null) {
            str = "build";
        } else {
            str = findPOJOBuilderConfig.buildMethodName;
        }
        AnnotatedMethod findMethod = beanDescription.findMethod(str, null);
        if (findMethod != null && config.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(findMethod.getMember());
        }
        constructBeanDeserializerBuilder.setPOJOBuilder(findMethod, findPOJOBuilderConfig);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer<Object> buildBuilderBased = constructBeanDeserializerBuilder.buildBuilderBased(javaType, str);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                buildBuilderBased = modifyDeserializer.modifyDeserializer(config, beanDescription, buildBuilderBased);
            }
        }
        return buildBuilderBased;
    }

    /* access modifiers changed from: protected */
    public void addObjectIdReader(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        JavaType javaType;
        SettableBeanProperty settableBeanProperty;
        ObjectIdGenerator objectIdGenerator;
        ObjectIdInfo objectIdInfo = beanDescription.getObjectIdInfo();
        if (objectIdInfo != null) {
            Class<PropertyGenerator> generatorType = objectIdInfo.getGeneratorType();
            if (generatorType == PropertyGenerator.class) {
                String propertyName = objectIdInfo.getPropertyName();
                settableBeanProperty = beanDeserializerBuilder.findProperty(propertyName);
                if (settableBeanProperty == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid Object Id definition for ");
                    sb.append(beanDescription.getBeanClass().getName());
                    sb.append(": can not find property with name '");
                    sb.append(propertyName);
                    sb.append("'");
                    throw new IllegalArgumentException(sb.toString());
                }
                javaType = settableBeanProperty.getType();
                objectIdGenerator = new PropertyBasedObjectIdGenerator(objectIdInfo.getScope());
            } else {
                settableBeanProperty = null;
                JavaType javaType2 = deserializationContext.getTypeFactory().findTypeParameters(deserializationContext.constructType(generatorType), ObjectIdGenerator.class)[0];
                objectIdGenerator = deserializationContext.objectIdGeneratorInstance(beanDescription.getClassInfo(), objectIdInfo);
                javaType = javaType2;
            }
            beanDeserializerBuilder.setObjectIdReader(ObjectIdReader.construct(javaType, objectIdInfo.getPropertyName(), objectIdGenerator, deserializationContext.findRootValueDeserializer(javaType), settableBeanProperty));
        }
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext deserializationContext, JavaType javaType, BeanDescription beanDescription) throws JsonMappingException {
        DeserializationConfig config = deserializationContext.getConfig();
        BeanDeserializerBuilder constructBeanDeserializerBuilder = constructBeanDeserializerBuilder(deserializationContext, beanDescription);
        constructBeanDeserializerBuilder.setValueInstantiator(findValueInstantiator(deserializationContext, beanDescription));
        addBeanProps(deserializationContext, beanDescription, constructBeanDeserializerBuilder);
        AnnotatedMethod findMethod = beanDescription.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (findMethod != null) {
            SettableBeanProperty constructSettableProperty = constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), findMethod, "cause"), findMethod.getGenericParameterType(0));
            if (constructSettableProperty != null) {
                constructBeanDeserializerBuilder.addOrReplaceProperty(constructSettableProperty, true);
            }
        }
        constructBeanDeserializerBuilder.addIgnorable("localizedMessage");
        constructBeanDeserializerBuilder.addIgnorable("suppressed");
        constructBeanDeserializerBuilder.addIgnorable("message");
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateBuilder : this._factoryConfig.deserializerModifiers()) {
                constructBeanDeserializerBuilder = updateBuilder.updateBuilder(config, beanDescription, constructBeanDeserializerBuilder);
            }
        }
        JsonDeserializer build = constructBeanDeserializerBuilder.build();
        if (build instanceof BeanDeserializer) {
            build = new ThrowableDeserializer((BeanDeserializer) build);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier modifyDeserializer : this._factoryConfig.deserializerModifiers()) {
                build = modifyDeserializer.modifyDeserializer(config, beanDescription, build);
            }
        }
        return build;
    }

    /* access modifiers changed from: protected */
    public BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext deserializationContext, BeanDescription beanDescription) {
        return new BeanDeserializerBuilder(beanDescription, deserializationContext.getConfig());
    }

    /* access modifiers changed from: protected */
    public void addBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        SettableBeanProperty[] fromObjectArguments = beanDeserializerBuilder.getValueInstantiator().getFromObjectArguments(deserializationContext.getConfig());
        AnnotationIntrospector annotationIntrospector = deserializationContext.getAnnotationIntrospector();
        Boolean findIgnoreUnknownProperties = annotationIntrospector.findIgnoreUnknownProperties(beanDescription.getClassInfo());
        if (findIgnoreUnknownProperties != null) {
            beanDeserializerBuilder.setIgnoreUnknownProperties(findIgnoreUnknownProperties.booleanValue());
        }
        HashSet<String> arrayToSet = ArrayBuilders.arrayToSet(annotationIntrospector.findPropertiesToIgnore(beanDescription.getClassInfo()));
        for (String addIgnorable : arrayToSet) {
            beanDeserializerBuilder.addIgnorable(addIgnorable);
        }
        AnnotatedMethod findAnySetter = beanDescription.findAnySetter();
        if (findAnySetter != null) {
            beanDeserializerBuilder.setAnySetter(constructAnySetter(deserializationContext, beanDescription, findAnySetter));
        }
        if (findAnySetter == null) {
            Set<String> ignoredPropertyNames = beanDescription.getIgnoredPropertyNames();
            if (ignoredPropertyNames != null) {
                for (String addIgnorable2 : ignoredPropertyNames) {
                    beanDeserializerBuilder.addIgnorable(addIgnorable2);
                }
            }
        }
        boolean z = deserializationContext.isEnabled(MapperFeature.USE_GETTERS_AS_SETTERS) && deserializationContext.isEnabled(MapperFeature.AUTO_DETECT_GETTERS);
        List<BeanPropertyDefinition> filterBeanProps = filterBeanProps(deserializationContext, beanDescription, beanDeserializerBuilder, beanDescription.findProperties(), arrayToSet);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier updateProperties : this._factoryConfig.deserializerModifiers()) {
                filterBeanProps = updateProperties.updateProperties(deserializationContext.getConfig(), beanDescription, filterBeanProps);
            }
        }
        for (BeanPropertyDefinition beanPropertyDefinition : filterBeanProps) {
            SettableBeanProperty settableBeanProperty = null;
            if (beanPropertyDefinition.hasConstructorParameter()) {
                String name = beanPropertyDefinition.getName();
                if (fromObjectArguments != null) {
                    int length = fromObjectArguments.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        SettableBeanProperty settableBeanProperty2 = fromObjectArguments[i];
                        if (name.equals(settableBeanProperty2.getName())) {
                            settableBeanProperty = settableBeanProperty2;
                            break;
                        }
                        i++;
                    }
                }
                if (settableBeanProperty == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Could not find creator property with name '");
                    sb.append(name);
                    sb.append("' (in class ");
                    sb.append(beanDescription.getBeanClass().getName());
                    sb.append(")");
                    throw deserializationContext.mappingException(sb.toString());
                }
                beanDeserializerBuilder.addCreatorProperty(settableBeanProperty);
            } else {
                if (beanPropertyDefinition.hasSetter()) {
                    settableBeanProperty = constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getSetter().getGenericParameterType(0));
                } else if (beanPropertyDefinition.hasField()) {
                    settableBeanProperty = constructSettableProperty(deserializationContext, beanDescription, beanPropertyDefinition, beanPropertyDefinition.getField().getGenericType());
                } else if (z && beanPropertyDefinition.hasGetter()) {
                    Class rawType = beanPropertyDefinition.getGetter().getRawType();
                    if (Collection.class.isAssignableFrom(rawType) || Map.class.isAssignableFrom(rawType)) {
                        settableBeanProperty = constructSetterlessProperty(deserializationContext, beanDescription, beanPropertyDefinition);
                    }
                }
                if (settableBeanProperty != null) {
                    Class<?>[] findViews = beanPropertyDefinition.findViews();
                    if (findViews == null && !deserializationContext.isEnabled(MapperFeature.DEFAULT_VIEW_INCLUSION)) {
                        findViews = NO_VIEWS;
                    }
                    settableBeanProperty.setViews(findViews);
                    beanDeserializerBuilder.addProperty(settableBeanProperty);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public List<BeanPropertyDefinition> filterBeanProps(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder, List<BeanPropertyDefinition> list, Set<String> set) throws JsonMappingException {
        ArrayList arrayList = new ArrayList(Math.max(4, list.size()));
        HashMap hashMap = new HashMap();
        for (BeanPropertyDefinition beanPropertyDefinition : list) {
            String name = beanPropertyDefinition.getName();
            if (!set.contains(name)) {
                if (!beanPropertyDefinition.hasConstructorParameter()) {
                    Class cls = null;
                    if (beanPropertyDefinition.hasSetter()) {
                        cls = beanPropertyDefinition.getSetter().getRawParameterType(0);
                    } else if (beanPropertyDefinition.hasField()) {
                        cls = beanPropertyDefinition.getField().getRawType();
                    }
                    if (cls != null && isIgnorableType(deserializationContext.getConfig(), beanDescription, cls, hashMap)) {
                        beanDeserializerBuilder.addIgnorable(name);
                    }
                }
                arrayList.add(beanPropertyDefinition);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void addReferenceProperties(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        Type type;
        Map findBackReferenceProperties = beanDescription.findBackReferenceProperties();
        if (findBackReferenceProperties != null) {
            for (Entry entry : findBackReferenceProperties.entrySet()) {
                String str = (String) entry.getKey();
                AnnotatedMember annotatedMember = (AnnotatedMember) entry.getValue();
                if (annotatedMember instanceof AnnotatedMethod) {
                    type = ((AnnotatedMethod) annotatedMember).getGenericParameterType(0);
                } else {
                    type = annotatedMember.getRawType();
                }
                beanDeserializerBuilder.addBackReferenceProperty(str, constructSettableProperty(deserializationContext, beanDescription, SimpleBeanPropertyDefinition.construct(deserializationContext.getConfig(), annotatedMember), type));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addInjectables(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanDeserializerBuilder beanDeserializerBuilder) throws JsonMappingException {
        Map findInjectables = beanDescription.findInjectables();
        if (findInjectables != null) {
            boolean canOverrideAccessModifiers = deserializationContext.canOverrideAccessModifiers();
            for (Entry entry : findInjectables.entrySet()) {
                AnnotatedMember annotatedMember = (AnnotatedMember) entry.getValue();
                if (canOverrideAccessModifiers) {
                    annotatedMember.fixAccess();
                }
                beanDeserializerBuilder.addInjectable(annotatedMember.getName(), beanDescription.resolveType(annotatedMember.getGenericType()), beanDescription.getClassAnnotations(), annotatedMember, entry.getKey());
            }
        }
    }

    /* access modifiers changed from: protected */
    public SettableAnyProperty constructAnySetter(DeserializationContext deserializationContext, BeanDescription beanDescription, AnnotatedMethod annotatedMethod) throws JsonMappingException {
        if (deserializationContext.canOverrideAccessModifiers()) {
            annotatedMethod.fixAccess();
        }
        JavaType resolveType = beanDescription.bindingsForBeanType().resolveType(annotatedMethod.getGenericParameterType(1));
        Std std = new Std(annotatedMethod.getName(), resolveType, null, beanDescription.getClassAnnotations(), annotatedMethod, false);
        JavaType resolveType2 = resolveType(deserializationContext, beanDescription, resolveType, annotatedMethod);
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, annotatedMethod);
        if (findDeserializerFromAnnotation != null) {
            return new SettableAnyProperty((BeanProperty) std, annotatedMethod, resolveType2, findDeserializerFromAnnotation);
        }
        return new SettableAnyProperty((BeanProperty) std, annotatedMethod, modifyTypeByAnnotation(deserializationContext, annotatedMethod, resolveType2), null);
    }

    /* JADX WARNING: type inference failed for: r10v3, types: [com.fasterxml.jackson.databind.deser.SettableBeanProperty] */
    /* JADX WARNING: type inference failed for: r10v4, types: [com.fasterxml.jackson.databind.deser.SettableBeanProperty] */
    /* JADX WARNING: type inference failed for: r10v5, types: [com.fasterxml.jackson.databind.deser.SettableBeanProperty] */
    /* JADX WARNING: type inference failed for: r1v1, types: [com.fasterxml.jackson.databind.deser.impl.FieldProperty] */
    /* JADX WARNING: type inference failed for: r1v2, types: [com.fasterxml.jackson.databind.deser.impl.MethodProperty] */
    /* JADX WARNING: type inference failed for: r10v8 */
    /* JADX WARNING: type inference failed for: r1v3, types: [com.fasterxml.jackson.databind.deser.impl.FieldProperty] */
    /* JADX WARNING: type inference failed for: r1v4, types: [com.fasterxml.jackson.databind.deser.impl.MethodProperty] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v3, types: [com.fasterxml.jackson.databind.deser.impl.FieldProperty]
      assigns: [com.fasterxml.jackson.databind.deser.impl.FieldProperty, com.fasterxml.jackson.databind.deser.impl.MethodProperty]
      uses: [com.fasterxml.jackson.databind.deser.impl.FieldProperty, com.fasterxml.jackson.databind.deser.SettableBeanProperty, com.fasterxml.jackson.databind.deser.impl.MethodProperty]
      mth insns count: 44
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
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at jadx.core.ProcessClass$$Lambda$38/2083670723.accept(Unknown Source)
    	at java.util.ArrayList.forEach(ArrayList.java:1249)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    	at jadx.api.JadxDecompiler$$Lambda$28/1919834117.run(Unknown Source)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.deser.SettableBeanProperty constructSettableProperty(com.fasterxml.jackson.databind.DeserializationContext r10, com.fasterxml.jackson.databind.BeanDescription r11, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r12, java.lang.reflect.Type r13) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r9 = this;
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r7 = r12.getMutator()
            boolean r0 = r10.canOverrideAccessModifiers()
            if (r0 == 0) goto L_0x000d
            r7.fixAccess()
        L_0x000d:
            com.fasterxml.jackson.databind.JavaType r13 = r11.resolveType(r13)
            com.fasterxml.jackson.databind.BeanProperty$Std r8 = new com.fasterxml.jackson.databind.BeanProperty$Std
            java.lang.String r1 = r12.getName()
            com.fasterxml.jackson.databind.PropertyName r3 = r12.getWrapperName()
            com.fasterxml.jackson.databind.util.Annotations r4 = r11.getClassAnnotations()
            boolean r6 = r12.isRequired()
            r0 = r8
            r2 = r13
            r5 = r7
            r0.<init>(r1, r2, r3, r4, r5, r6)
            com.fasterxml.jackson.databind.JavaType r0 = r9.resolveType(r10, r11, r13, r7)
            if (r0 == r13) goto L_0x0032
            r8.withType(r0)
        L_0x0032:
            com.fasterxml.jackson.databind.JsonDeserializer r13 = r9.findDeserializerFromAnnotation(r10, r7)
            com.fasterxml.jackson.databind.JavaType r3 = r9.modifyTypeByAnnotation(r10, r7, r0)
            java.lang.Object r10 = r3.getTypeHandler()
            r4 = r10
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r4 = (com.fasterxml.jackson.databind.jsontype.TypeDeserializer) r4
            boolean r10 = r7 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedMethod
            if (r10 == 0) goto L_0x0054
            com.fasterxml.jackson.databind.deser.impl.MethodProperty r10 = new com.fasterxml.jackson.databind.deser.impl.MethodProperty
            com.fasterxml.jackson.databind.util.Annotations r5 = r11.getClassAnnotations()
            r6 = r7
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r6 = (com.fasterxml.jackson.databind.introspect.AnnotatedMethod) r6
            r1 = r10
            r2 = r12
            r1.<init>(r2, r3, r4, r5, r6)
            goto L_0x0062
        L_0x0054:
            com.fasterxml.jackson.databind.deser.impl.FieldProperty r10 = new com.fasterxml.jackson.databind.deser.impl.FieldProperty
            com.fasterxml.jackson.databind.util.Annotations r5 = r11.getClassAnnotations()
            r6 = r7
            com.fasterxml.jackson.databind.introspect.AnnotatedField r6 = (com.fasterxml.jackson.databind.introspect.AnnotatedField) r6
            r1 = r10
            r2 = r12
            r1.<init>(r2, r3, r4, r5, r6)
        L_0x0062:
            if (r13 == 0) goto L_0x0068
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r10 = r10.withValueDeserializer(r13)
        L_0x0068:
            com.fasterxml.jackson.databind.AnnotationIntrospector$ReferenceProperty r11 = r12.findReferenceType()
            if (r11 == 0) goto L_0x007b
            boolean r12 = r11.isManagedReference()
            if (r12 == 0) goto L_0x007b
            java.lang.String r11 = r11.getName()
            r10.setManagedReferenceName(r11)
        L_0x007b:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerFactory.constructSettableProperty(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanDescription, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition, java.lang.reflect.Type):com.fasterxml.jackson.databind.deser.SettableBeanProperty");
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty constructSetterlessProperty(DeserializationContext deserializationContext, BeanDescription beanDescription, BeanPropertyDefinition beanPropertyDefinition) throws JsonMappingException {
        AnnotatedMethod getter = beanPropertyDefinition.getGetter();
        if (deserializationContext.canOverrideAccessModifiers()) {
            getter.fixAccess();
        }
        JavaType type = getter.getType(beanDescription.bindingsForBeanType());
        JsonDeserializer findDeserializerFromAnnotation = findDeserializerFromAnnotation(deserializationContext, getter);
        JavaType modifyTypeByAnnotation = modifyTypeByAnnotation(deserializationContext, getter, type);
        SetterlessProperty setterlessProperty = new SetterlessProperty(beanPropertyDefinition, modifyTypeByAnnotation, (TypeDeserializer) modifyTypeByAnnotation.getTypeHandler(), beanDescription.getClassAnnotations(), getter);
        return findDeserializerFromAnnotation != null ? setterlessProperty.withValueDeserializer(findDeserializerFromAnnotation) : setterlessProperty;
    }

    /* access modifiers changed from: protected */
    public boolean isPotentialBeanType(Class<?> cls) {
        String canBeABeanType = ClassUtil.canBeABeanType(cls);
        if (canBeABeanType != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can not deserialize Class ");
            sb.append(cls.getName());
            sb.append(" (of type ");
            sb.append(canBeABeanType);
            sb.append(") as a Bean");
            throw new IllegalArgumentException(sb.toString());
        } else if (ClassUtil.isProxyType(cls)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Can not deserialize Proxy class ");
            sb2.append(cls.getName());
            sb2.append(" as a Bean");
            throw new IllegalArgumentException(sb2.toString());
        } else {
            String isLocalType = ClassUtil.isLocalType(cls, true);
            if (isLocalType == null) {
                return true;
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Can not deserialize Class ");
            sb3.append(cls.getName());
            sb3.append(" (of type ");
            sb3.append(isLocalType);
            sb3.append(") as a Bean");
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    /* access modifiers changed from: protected */
    public boolean isIgnorableType(DeserializationConfig deserializationConfig, BeanDescription beanDescription, Class<?> cls, Map<Class<?>, Boolean> map) {
        Boolean bool = (Boolean) map.get(cls);
        if (bool == null) {
            bool = deserializationConfig.getAnnotationIntrospector().isIgnorableType(deserializationConfig.introspectClassAnnotations(cls).getClassInfo());
            if (bool == null) {
                bool = Boolean.FALSE;
            }
        }
        return bool.booleanValue();
    }
}
