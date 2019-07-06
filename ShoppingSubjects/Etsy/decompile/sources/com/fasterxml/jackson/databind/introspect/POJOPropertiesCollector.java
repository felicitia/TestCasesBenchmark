package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class POJOPropertiesCollector {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected LinkedList<AnnotatedMember> _anyGetters;
    protected LinkedList<AnnotatedMethod> _anySetters;
    protected final AnnotatedClass _classDef;
    protected final MapperConfig<?> _config;
    protected LinkedList<POJOPropertyBuilder> _creatorProperties;
    protected final boolean _forSerialization;
    protected HashSet<String> _ignoredPropertyNames;
    protected LinkedHashMap<Object, AnnotatedMember> _injectables;
    protected LinkedList<AnnotatedMethod> _jsonValueGetters;
    protected final String _mutatorPrefix;
    protected final LinkedHashMap<String, POJOPropertyBuilder> _properties = new LinkedHashMap<>();
    protected final JavaType _type;
    protected final VisibilityChecker<?> _visibilityChecker;

    protected POJOPropertiesCollector(MapperConfig<?> mapperConfig, boolean z, JavaType javaType, AnnotatedClass annotatedClass, String str) {
        AnnotationIntrospector annotationIntrospector = null;
        this._creatorProperties = null;
        this._anyGetters = null;
        this._anySetters = null;
        this._jsonValueGetters = null;
        this._config = mapperConfig;
        this._forSerialization = z;
        this._type = javaType;
        this._classDef = annotatedClass;
        if (str == null) {
            str = "set";
        }
        this._mutatorPrefix = str;
        if (mapperConfig.isAnnotationProcessingEnabled()) {
            annotationIntrospector = this._config.getAnnotationIntrospector();
        }
        this._annotationIntrospector = annotationIntrospector;
        if (this._annotationIntrospector == null) {
            this._visibilityChecker = this._config.getDefaultVisibilityChecker();
        } else {
            this._visibilityChecker = this._annotationIntrospector.findAutoDetectVisibility(annotatedClass, this._config.getDefaultVisibilityChecker());
        }
    }

    public MapperConfig<?> getConfig() {
        return this._config;
    }

    public JavaType getType() {
        return this._type;
    }

    public AnnotatedClass getClassDef() {
        return this._classDef;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    public List<BeanPropertyDefinition> getProperties() {
        return new ArrayList(this._properties.values());
    }

    public Map<Object, AnnotatedMember> getInjectables() {
        return this._injectables;
    }

    public AnnotatedMethod getJsonValueMethod() {
        if (this._jsonValueGetters == null) {
            return null;
        }
        if (this._jsonValueGetters.size() > 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Multiple value properties defined (");
            sb.append(this._jsonValueGetters.get(0));
            sb.append(" vs ");
            sb.append(this._jsonValueGetters.get(1));
            sb.append(")");
            reportProblem(sb.toString());
        }
        return (AnnotatedMethod) this._jsonValueGetters.get(0);
    }

    public AnnotatedMember getAnyGetter() {
        if (this._anyGetters == null) {
            return null;
        }
        if (this._anyGetters.size() > 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Multiple 'any-getters' defined (");
            sb.append(this._anyGetters.get(0));
            sb.append(" vs ");
            sb.append(this._anyGetters.get(1));
            sb.append(")");
            reportProblem(sb.toString());
        }
        return (AnnotatedMember) this._anyGetters.getFirst();
    }

    public AnnotatedMethod getAnySetterMethod() {
        if (this._anySetters == null) {
            return null;
        }
        if (this._anySetters.size() > 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Multiple 'any-setters' defined (");
            sb.append(this._anySetters.get(0));
            sb.append(" vs ");
            sb.append(this._anySetters.get(1));
            sb.append(")");
            reportProblem(sb.toString());
        }
        return (AnnotatedMethod) this._anySetters.getFirst();
    }

    public Set<String> getIgnoredPropertyNames() {
        return this._ignoredPropertyNames;
    }

    public ObjectIdInfo getObjectIdInfo() {
        if (this._annotationIntrospector == null) {
            return null;
        }
        ObjectIdInfo findObjectIdInfo = this._annotationIntrospector.findObjectIdInfo(this._classDef);
        if (findObjectIdInfo != null) {
            findObjectIdInfo = this._annotationIntrospector.findObjectReferenceInfo(this._classDef, findObjectIdInfo);
        }
        return findObjectIdInfo;
    }

    public Class<?> findPOJOBuilderClass() {
        return this._annotationIntrospector.findPOJOBuilder(this._classDef);
    }

    /* access modifiers changed from: protected */
    public Map<String, POJOPropertyBuilder> getPropertyMap() {
        return this._properties;
    }

    public POJOPropertiesCollector collect() {
        this._properties.clear();
        _addFields();
        _addMethods();
        _addCreators();
        _addInjectables();
        _removeUnwantedProperties();
        _renameProperties();
        PropertyNamingStrategy _findNamingStrategy = _findNamingStrategy();
        if (_findNamingStrategy != null) {
            _renameUsing(_findNamingStrategy);
        }
        for (POJOPropertyBuilder trimByVisibility : this._properties.values()) {
            trimByVisibility.trimByVisibility();
        }
        for (POJOPropertyBuilder mergeAnnotations : this._properties.values()) {
            mergeAnnotations.mergeAnnotations(this._forSerialization);
        }
        if (this._config.isEnabled(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME)) {
            _renameWithWrappers();
        }
        _sortProperties();
        return this;
    }

    /* access modifiers changed from: protected */
    public void _sortProperties() {
        Boolean bool;
        boolean z;
        Map map;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        String[] strArr = null;
        if (annotationIntrospector == null) {
            bool = null;
        } else {
            bool = annotationIntrospector.findSerializationSortAlphabetically(this._classDef);
        }
        if (bool == null) {
            z = this._config.shouldSortPropertiesAlphabetically();
        } else {
            z = bool.booleanValue();
        }
        if (annotationIntrospector != null) {
            strArr = annotationIntrospector.findSerializationPropertyOrder(this._classDef);
        }
        if (z || this._creatorProperties != null || strArr != null) {
            int size = this._properties.size();
            if (z) {
                map = new TreeMap();
            } else {
                map = new LinkedHashMap(size + size);
            }
            for (POJOPropertyBuilder pOJOPropertyBuilder : this._properties.values()) {
                map.put(pOJOPropertyBuilder.getName(), pOJOPropertyBuilder);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(size + size);
            if (strArr != null) {
                for (String str : strArr) {
                    POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) map.get(str);
                    if (pOJOPropertyBuilder2 == null) {
                        Iterator it = this._properties.values().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            POJOPropertyBuilder pOJOPropertyBuilder3 = (POJOPropertyBuilder) it.next();
                            if (str.equals(pOJOPropertyBuilder3.getInternalName())) {
                                str = pOJOPropertyBuilder3.getName();
                                pOJOPropertyBuilder2 = pOJOPropertyBuilder3;
                                break;
                            }
                        }
                    }
                    if (pOJOPropertyBuilder2 != null) {
                        linkedHashMap.put(str, pOJOPropertyBuilder2);
                    }
                }
            }
            if (this._creatorProperties != null) {
                Iterator it2 = this._creatorProperties.iterator();
                while (it2.hasNext()) {
                    POJOPropertyBuilder pOJOPropertyBuilder4 = (POJOPropertyBuilder) it2.next();
                    linkedHashMap.put(pOJOPropertyBuilder4.getName(), pOJOPropertyBuilder4);
                }
            }
            linkedHashMap.putAll(map);
            this._properties.clear();
            this._properties.putAll(linkedHashMap);
        }
    }

    /* access modifiers changed from: protected */
    public void _addFields() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        boolean z = !this._forSerialization && !this._config.isEnabled(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS);
        for (AnnotatedField annotatedField : this._classDef.fields()) {
            String name = annotatedField.getName();
            String str = null;
            if (annotationIntrospector != null) {
                if (this._forSerialization) {
                    PropertyName findNameForSerialization = annotationIntrospector.findNameForSerialization(annotatedField);
                    if (findNameForSerialization != null) {
                        str = findNameForSerialization.getSimpleName();
                    }
                } else {
                    PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(annotatedField);
                    if (findNameForDeserialization != null) {
                        str = findNameForDeserialization.getSimpleName();
                    }
                }
            }
            if ("".equals(str)) {
                str = name;
            }
            boolean z2 = str != null;
            if (!z2) {
                z2 = this._visibilityChecker.isFieldVisible(annotatedField);
            }
            boolean z3 = annotationIntrospector != null && annotationIntrospector.hasIgnoreMarker(annotatedField);
            if (!z || str != null || z3 || !Modifier.isFinal(annotatedField.getModifiers())) {
                _property(name).addField(annotatedField, str, z2, z3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addCreators() {
        String str;
        String str2;
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector != null) {
            for (AnnotatedConstructor annotatedConstructor : this._classDef.getConstructors()) {
                if (this._creatorProperties == null) {
                    this._creatorProperties = new LinkedList<>();
                }
                int parameterCount = annotatedConstructor.getParameterCount();
                for (int i = 0; i < parameterCount; i++) {
                    AnnotatedParameter parameter = annotatedConstructor.getParameter(i);
                    PropertyName findNameForDeserialization = annotationIntrospector.findNameForDeserialization(parameter);
                    if (findNameForDeserialization == null) {
                        str2 = null;
                    } else {
                        str2 = findNameForDeserialization.getSimpleName();
                    }
                    if (str2 != null) {
                        POJOPropertyBuilder _property = _property(str2);
                        _property.addCtor(parameter, str2, true, false);
                        this._creatorProperties.add(_property);
                    }
                }
            }
            for (AnnotatedMethod annotatedMethod : this._classDef.getStaticMethods()) {
                if (this._creatorProperties == null) {
                    this._creatorProperties = new LinkedList<>();
                }
                int parameterCount2 = annotatedMethod.getParameterCount();
                for (int i2 = 0; i2 < parameterCount2; i2++) {
                    AnnotatedParameter parameter2 = annotatedMethod.getParameter(i2);
                    PropertyName findNameForDeserialization2 = annotationIntrospector.findNameForDeserialization(parameter2);
                    if (findNameForDeserialization2 == null) {
                        str = null;
                    } else {
                        str = findNameForDeserialization2.getSimpleName();
                    }
                    if (str != null) {
                        POJOPropertyBuilder _property2 = _property(str);
                        _property2.addCtor(parameter2, str, true, false);
                        this._creatorProperties.add(_property2);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMethods() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
            int parameterCount = annotatedMethod.getParameterCount();
            if (parameterCount == 0) {
                _addGetterMethod(annotatedMethod, annotationIntrospector);
            } else if (parameterCount == 1) {
                _addSetterMethod(annotatedMethod, annotationIntrospector);
            } else if (parameterCount == 2 && annotationIntrospector != null && annotationIntrospector.hasAnySetterAnnotation(annotatedMethod)) {
                if (this._anySetters == null) {
                    this._anySetters = new LinkedList<>();
                }
                this._anySetters.add(annotatedMethod);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addGetterMethod(AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        PropertyName propertyName;
        boolean z;
        String str;
        boolean z2;
        if (annotationIntrospector != null) {
            if (annotationIntrospector.hasAnyGetterAnnotation(annotatedMethod)) {
                if (this._anyGetters == null) {
                    this._anyGetters = new LinkedList<>();
                }
                this._anyGetters.add(annotatedMethod);
                return;
            } else if (annotationIntrospector.hasAsValueAnnotation(annotatedMethod)) {
                if (this._jsonValueGetters == null) {
                    this._jsonValueGetters = new LinkedList<>();
                }
                this._jsonValueGetters.add(annotatedMethod);
                return;
            }
        }
        String str2 = null;
        if (annotationIntrospector == null) {
            propertyName = null;
        } else {
            propertyName = annotationIntrospector.findNameForSerialization(annotatedMethod);
        }
        if (propertyName != null) {
            str2 = propertyName.getSimpleName();
        }
        if (str2 == null) {
            str = BeanUtil.okNameForRegularGetter(annotatedMethod, annotatedMethod.getName());
            if (str == null) {
                str = BeanUtil.okNameForIsGetter(annotatedMethod, annotatedMethod.getName());
                if (str != null) {
                    z = this._visibilityChecker.isIsGetterVisible(annotatedMethod);
                } else {
                    return;
                }
            } else {
                z = this._visibilityChecker.isGetterVisible(annotatedMethod);
            }
        } else {
            str = BeanUtil.okNameForGetter(annotatedMethod);
            if (str == null) {
                str = annotatedMethod.getName();
            }
            if (str2.length() == 0) {
                str2 = str;
            }
            z = true;
        }
        if (annotationIntrospector == null) {
            z2 = false;
        } else {
            z2 = annotationIntrospector.hasIgnoreMarker(annotatedMethod);
        }
        _property(str).addGetter(annotatedMethod, str2, z, z2);
    }

    /* access modifiers changed from: protected */
    public void _addSetterMethod(AnnotatedMethod annotatedMethod, AnnotationIntrospector annotationIntrospector) {
        boolean z;
        String str;
        boolean z2;
        String str2 = null;
        PropertyName findNameForDeserialization = annotationIntrospector == null ? null : annotationIntrospector.findNameForDeserialization(annotatedMethod);
        if (findNameForDeserialization != null) {
            str2 = findNameForDeserialization.getSimpleName();
        }
        if (str2 == null) {
            str = BeanUtil.okNameForMutator(annotatedMethod, this._mutatorPrefix);
            if (str != null) {
                z = this._visibilityChecker.isSetterVisible(annotatedMethod);
            } else {
                return;
            }
        } else {
            str = BeanUtil.okNameForMutator(annotatedMethod, this._mutatorPrefix);
            if (str == null) {
                str = annotatedMethod.getName();
            }
            if (str2.length() == 0) {
                str2 = str;
            }
            z = true;
        }
        if (annotationIntrospector == null) {
            z2 = false;
        } else {
            z2 = annotationIntrospector.hasIgnoreMarker(annotatedMethod);
        }
        _property(str).addSetter(annotatedMethod, str2, z, z2);
    }

    /* access modifiers changed from: protected */
    public void _addInjectables() {
        AnnotationIntrospector annotationIntrospector = this._annotationIntrospector;
        if (annotationIntrospector != null) {
            for (AnnotatedField annotatedField : this._classDef.fields()) {
                _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedField), annotatedField);
            }
            for (AnnotatedMethod annotatedMethod : this._classDef.memberMethods()) {
                if (annotatedMethod.getParameterCount() == 1) {
                    _doAddInjectable(annotationIntrospector.findInjectableValueId(annotatedMethod), annotatedMethod);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _doAddInjectable(Object obj, AnnotatedMember annotatedMember) {
        String str;
        if (obj != null) {
            if (this._injectables == null) {
                this._injectables = new LinkedHashMap<>();
            }
            if (((AnnotatedMember) this._injectables.put(obj, annotatedMember)) != null) {
                if (obj == null) {
                    str = "[null]";
                } else {
                    str = obj.getClass().getName();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Duplicate injectable value with id '");
                sb.append(String.valueOf(obj));
                sb.append("' (of type ");
                sb.append(str);
                sb.append(")");
                throw new IllegalArgumentException(sb.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _removeUnwantedProperties() {
        Iterator it = this._properties.entrySet().iterator();
        boolean z = !this._config.isEnabled(MapperFeature.INFER_PROPERTY_MUTATORS);
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) ((Entry) it.next()).getValue();
            if (!pOJOPropertyBuilder.anyVisible()) {
                it.remove();
            } else {
                if (pOJOPropertyBuilder.anyIgnorals()) {
                    if (!pOJOPropertyBuilder.isExplicitlyIncluded()) {
                        it.remove();
                        _addIgnored(pOJOPropertyBuilder.getName());
                    } else {
                        pOJOPropertyBuilder.removeIgnored();
                        if (!this._forSerialization && !pOJOPropertyBuilder.couldDeserialize()) {
                            _addIgnored(pOJOPropertyBuilder.getName());
                        }
                    }
                }
                pOJOPropertyBuilder.removeNonVisible(z);
            }
        }
    }

    private void _addIgnored(String str) {
        if (!this._forSerialization) {
            if (this._ignoredPropertyNames == null) {
                this._ignoredPropertyNames = new HashSet<>();
            }
            this._ignoredPropertyNames.add(str);
        }
    }

    /* access modifiers changed from: protected */
    public void _renameProperties() {
        Iterator it = this._properties.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) ((Entry) it.next()).getValue();
            String findNewName = pOJOPropertyBuilder.findNewName();
            if (findNewName != null) {
                if (linkedList == null) {
                    linkedList = new LinkedList();
                }
                linkedList.add(pOJOPropertyBuilder.withName(findNewName));
                it.remove();
            }
        }
        if (linkedList != null) {
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) it2.next();
                String name = pOJOPropertyBuilder2.getName();
                POJOPropertyBuilder pOJOPropertyBuilder3 = (POJOPropertyBuilder) this._properties.get(name);
                if (pOJOPropertyBuilder3 == null) {
                    this._properties.put(name, pOJOPropertyBuilder2);
                } else {
                    pOJOPropertyBuilder3.addAll(pOJOPropertyBuilder2);
                }
                if (this._creatorProperties != null) {
                    int i = 0;
                    while (true) {
                        if (i >= this._creatorProperties.size()) {
                            break;
                        } else if (((POJOPropertyBuilder) this._creatorProperties.get(i)).getInternalName() == pOJOPropertyBuilder2.getInternalName()) {
                            this._creatorProperties.set(i, pOJOPropertyBuilder2);
                            break;
                        } else {
                            i++;
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _renameUsing(PropertyNamingStrategy propertyNamingStrategy) {
        POJOPropertyBuilder[] pOJOPropertyBuilderArr = (POJOPropertyBuilder[]) this._properties.values().toArray(new POJOPropertyBuilder[this._properties.size()]);
        this._properties.clear();
        for (POJOPropertyBuilder pOJOPropertyBuilder : pOJOPropertyBuilderArr) {
            String name = pOJOPropertyBuilder.getName();
            if (this._forSerialization) {
                if (pOJOPropertyBuilder.hasGetter()) {
                    name = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), name);
                } else if (pOJOPropertyBuilder.hasField()) {
                    name = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), name);
                }
            } else if (pOJOPropertyBuilder.hasSetter()) {
                name = propertyNamingStrategy.nameForSetterMethod(this._config, pOJOPropertyBuilder.getSetter(), name);
            } else if (pOJOPropertyBuilder.hasConstructorParameter()) {
                name = propertyNamingStrategy.nameForConstructorParameter(this._config, pOJOPropertyBuilder.getConstructorParameter(), name);
            } else if (pOJOPropertyBuilder.hasField()) {
                name = propertyNamingStrategy.nameForField(this._config, pOJOPropertyBuilder.getField(), name);
            } else if (pOJOPropertyBuilder.hasGetter()) {
                name = propertyNamingStrategy.nameForGetterMethod(this._config, pOJOPropertyBuilder.getGetter(), name);
            }
            if (!name.equals(pOJOPropertyBuilder.getName())) {
                pOJOPropertyBuilder = pOJOPropertyBuilder.withName(name);
            }
            POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) this._properties.get(name);
            if (pOJOPropertyBuilder2 == null) {
                this._properties.put(name, pOJOPropertyBuilder);
            } else {
                pOJOPropertyBuilder2.addAll(pOJOPropertyBuilder);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _renameWithWrappers() {
        Iterator it = this._properties.entrySet().iterator();
        LinkedList linkedList = null;
        while (it.hasNext()) {
            POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) ((Entry) it.next()).getValue();
            AnnotatedMember primaryMember = pOJOPropertyBuilder.getPrimaryMember();
            if (primaryMember != null) {
                PropertyName findWrapperName = this._annotationIntrospector.findWrapperName(primaryMember);
                if (findWrapperName != null && findWrapperName.hasSimpleName()) {
                    String simpleName = findWrapperName.getSimpleName();
                    if (!simpleName.equals(pOJOPropertyBuilder.getName())) {
                        if (linkedList == null) {
                            linkedList = new LinkedList();
                        }
                        linkedList.add(pOJOPropertyBuilder.withName(simpleName));
                        it.remove();
                    }
                }
            }
        }
        if (linkedList != null) {
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                POJOPropertyBuilder pOJOPropertyBuilder2 = (POJOPropertyBuilder) it2.next();
                String name = pOJOPropertyBuilder2.getName();
                POJOPropertyBuilder pOJOPropertyBuilder3 = (POJOPropertyBuilder) this._properties.get(name);
                if (pOJOPropertyBuilder3 == null) {
                    this._properties.put(name, pOJOPropertyBuilder2);
                } else {
                    pOJOPropertyBuilder3.addAll(pOJOPropertyBuilder2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void reportProblem(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Problem with definition of ");
        sb.append(this._classDef);
        sb.append(": ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public POJOPropertyBuilder _property(String str) {
        POJOPropertyBuilder pOJOPropertyBuilder = (POJOPropertyBuilder) this._properties.get(str);
        if (pOJOPropertyBuilder != null) {
            return pOJOPropertyBuilder;
        }
        POJOPropertyBuilder pOJOPropertyBuilder2 = new POJOPropertyBuilder(str, this._annotationIntrospector, this._forSerialization);
        this._properties.put(str, pOJOPropertyBuilder2);
        return pOJOPropertyBuilder2;
    }

    private PropertyNamingStrategy _findNamingStrategy() {
        Object findNamingStrategy = this._annotationIntrospector == null ? null : this._annotationIntrospector.findNamingStrategy(this._classDef);
        if (findNamingStrategy == null) {
            return this._config.getPropertyNamingStrategy();
        }
        if (findNamingStrategy instanceof PropertyNamingStrategy) {
            return (PropertyNamingStrategy) findNamingStrategy;
        }
        if (!(findNamingStrategy instanceof Class)) {
            StringBuilder sb = new StringBuilder();
            sb.append("AnnotationIntrospector returned PropertyNamingStrategy definition of type ");
            sb.append(findNamingStrategy.getClass().getName());
            sb.append("; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead");
            throw new IllegalStateException(sb.toString());
        }
        Class cls = (Class) findNamingStrategy;
        if (!PropertyNamingStrategy.class.isAssignableFrom(cls)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("AnnotationIntrospector returned Class ");
            sb2.append(cls.getName());
            sb2.append("; expected Class<PropertyNamingStrategy>");
            throw new IllegalStateException(sb2.toString());
        }
        HandlerInstantiator handlerInstantiator = this._config.getHandlerInstantiator();
        if (handlerInstantiator != null) {
            PropertyNamingStrategy namingStrategyInstance = handlerInstantiator.namingStrategyInstance(this._config, this._classDef, cls);
            if (namingStrategyInstance != null) {
                return namingStrategyInstance;
            }
        }
        return (PropertyNamingStrategy) ClassUtil.createInstance(cls, this._config.canOverrideAccessModifiers());
    }
}
