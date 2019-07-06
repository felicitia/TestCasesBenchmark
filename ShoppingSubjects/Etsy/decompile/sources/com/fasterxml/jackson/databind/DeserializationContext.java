package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualKeyDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.deser.DeserializerCache;
import com.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.fasterxml.jackson.databind.deser.impl.ReadableObjectId;
import com.fasterxml.jackson.databind.deser.impl.TypeWrappedDeserializer;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LinkedNode;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public abstract class DeserializationContext extends DatabindContext implements Serializable {
    private static final int MAX_ERROR_STR_LEN = 500;
    private static final long serialVersionUID = -7727373309391091315L;
    protected transient ArrayBuilders _arrayBuilders;
    protected final DeserializerCache _cache;
    protected final DeserializationConfig _config;
    protected transient DateFormat _dateFormat;
    protected final DeserializerFactory _factory;
    protected final int _featureFlags;
    protected final InjectableValues _injectableValues;
    protected transient ObjectBuffer _objectBuffer;
    protected transient JsonParser _parser;
    protected final Class<?> _view;

    public abstract JsonDeserializer<Object> deserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    public abstract ReadableObjectId findObjectId(Object obj, ObjectIdGenerator<?> objectIdGenerator);

    public abstract KeyDeserializer keyDeserializerInstance(Annotated annotated, Object obj) throws JsonMappingException;

    protected DeserializationContext(DeserializerFactory deserializerFactory) {
        this(deserializerFactory, (DeserializerCache) null);
    }

    protected DeserializationContext(DeserializerFactory deserializerFactory, DeserializerCache deserializerCache) {
        if (deserializerFactory == null) {
            throw new IllegalArgumentException("Can not pass null DeserializerFactory");
        }
        this._factory = deserializerFactory;
        if (deserializerCache == null) {
            deserializerCache = new DeserializerCache();
        }
        this._cache = deserializerCache;
        this._featureFlags = 0;
        this._config = null;
        this._injectableValues = null;
        this._view = null;
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializerFactory deserializerFactory) {
        this._cache = deserializationContext._cache;
        this._factory = deserializerFactory;
        this._config = deserializationContext._config;
        this._featureFlags = deserializationContext._featureFlags;
        this._view = deserializationContext._view;
        this._parser = deserializationContext._parser;
        this._injectableValues = deserializationContext._injectableValues;
    }

    protected DeserializationContext(DeserializationContext deserializationContext, DeserializationConfig deserializationConfig, JsonParser jsonParser, InjectableValues injectableValues) {
        this._cache = deserializationContext._cache;
        this._factory = deserializationContext._factory;
        this._config = deserializationConfig;
        this._featureFlags = deserializationConfig.getDeserializationFeatures();
        this._view = deserializationConfig.getActiveView();
        this._parser = jsonParser;
        this._injectableValues = injectableValues;
    }

    public DeserializationConfig getConfig() {
        return this._config;
    }

    public final Class<?> getActiveView() {
        return this._view;
    }

    public final AnnotationIntrospector getAnnotationIntrospector() {
        return this._config.getAnnotationIntrospector();
    }

    public final TypeFactory getTypeFactory() {
        return this._config.getTypeFactory();
    }

    public DeserializerFactory getFactory() {
        return this._factory;
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (deserializationFeature.getMask() & this._featureFlags) != 0;
    }

    public final JsonParser getParser() {
        return this._parser;
    }

    public final Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) {
        if (this._injectableValues != null) {
            return this._injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("No 'injectableValues' configured, can not inject value with id [");
        sb.append(obj);
        sb.append("]");
        throw new IllegalStateException(sb.toString());
    }

    public final Base64Variant getBase64Variant() {
        return this._config.getBase64Variant();
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._config.getNodeFactory();
    }

    public Locale getLocale() {
        return this._config.getLocale();
    }

    public TimeZone getTimeZone() {
        return this._config.getTimeZone();
    }

    public boolean hasValueDeserializerFor(JavaType javaType) {
        return this._cache.hasValueDeserializerFor(this, this._factory, javaType);
    }

    public final JsonDeserializer<Object> findContextualValueDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        JsonDeserializer<Object> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        return (findValueDeserializer == null || !(findValueDeserializer instanceof ContextualDeserializer)) ? findValueDeserializer : ((ContextualDeserializer) findValueDeserializer).createContextual(this, beanProperty);
    }

    public final JsonDeserializer<Object> findRootValueDeserializer(JavaType javaType) throws JsonMappingException {
        JsonDeserializer<Object> findValueDeserializer = this._cache.findValueDeserializer(this, this._factory, javaType);
        if (findValueDeserializer == null) {
            return null;
        }
        if (findValueDeserializer instanceof ContextualDeserializer) {
            findValueDeserializer = ((ContextualDeserializer) findValueDeserializer).createContextual(this, null);
        }
        TypeDeserializer findTypeDeserializer = this._factory.findTypeDeserializer(this._config, javaType);
        return findTypeDeserializer != null ? new TypeWrappedDeserializer(findTypeDeserializer.forProperty(null), findValueDeserializer) : findValueDeserializer;
    }

    public final KeyDeserializer findKeyDeserializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        KeyDeserializer findKeyDeserializer = this._cache.findKeyDeserializer(this, this._factory, javaType);
        return findKeyDeserializer instanceof ContextualKeyDeserializer ? ((ContextualKeyDeserializer) findKeyDeserializer).createContextual(this, beanProperty) : findKeyDeserializer;
    }

    public final JavaType constructType(Class<?> cls) {
        return this._config.constructType(cls);
    }

    public Class<?> findClass(String str) throws ClassNotFoundException {
        return ClassUtil.findClass(str);
    }

    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer = this._objectBuffer;
        if (objectBuffer == null) {
            return new ObjectBuffer();
        }
        this._objectBuffer = null;
        return objectBuffer;
    }

    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        if (this._objectBuffer == null || objectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer;
        }
    }

    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    public Date parseDate(String str) throws IllegalArgumentException {
        try {
            return getDateFormat().parse(str);
        } catch (ParseException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to parse Date value '");
            sb.append(str);
            sb.append("': ");
            sb.append(e.getMessage());
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public Calendar constructCalendar(Date date) {
        Calendar instance = Calendar.getInstance(getTimeZone());
        instance.setTime(date);
        return instance;
    }

    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException, JsonProcessingException {
        LinkedNode problemHandlers = this._config.getProblemHandlers();
        if (problemHandlers != null) {
            while (problemHandlers != null) {
                if (((DeserializationProblemHandler) problemHandlers.value()).handleUnknownProperty(this, jsonParser, jsonDeserializer, obj, str)) {
                    return true;
                }
                problemHandlers = problemHandlers.next();
            }
        }
        return false;
    }

    public void reportUnknownProperty(Object obj, String str, JsonDeserializer<?> jsonDeserializer) throws JsonMappingException {
        Collection collection;
        if (isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)) {
            if (jsonDeserializer == null) {
                collection = null;
            } else {
                collection = jsonDeserializer.getKnownPropertyNames();
            }
            throw UnrecognizedPropertyException.from(this._parser, obj, str, collection);
        }
    }

    public JsonMappingException mappingException(Class<?> cls) {
        return mappingException(cls, this._parser.getCurrentToken());
    }

    public JsonMappingException mappingException(Class<?> cls, JsonToken jsonToken) {
        String _calcName = _calcName(cls);
        JsonParser jsonParser = this._parser;
        StringBuilder sb = new StringBuilder();
        sb.append("Can not deserialize instance of ");
        sb.append(_calcName);
        sb.append(" out of ");
        sb.append(jsonToken);
        sb.append(" token");
        return JsonMappingException.from(jsonParser, sb.toString());
    }

    public JsonMappingException mappingException(String str) {
        return JsonMappingException.from(getParser(), str);
    }

    public JsonMappingException instantiationException(Class<?> cls, Throwable th) {
        JsonParser jsonParser = this._parser;
        StringBuilder sb = new StringBuilder();
        sb.append("Can not construct instance of ");
        sb.append(cls.getName());
        sb.append(", problem: ");
        sb.append(th.getMessage());
        return JsonMappingException.from(jsonParser, sb.toString(), th);
    }

    public JsonMappingException instantiationException(Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        StringBuilder sb = new StringBuilder();
        sb.append("Can not construct instance of ");
        sb.append(cls.getName());
        sb.append(", problem: ");
        sb.append(str);
        return JsonMappingException.from(jsonParser, sb.toString());
    }

    @Deprecated
    public JsonMappingException weirdStringException(Class<?> cls, String str) {
        return weirdStringException(null, cls, str);
    }

    public JsonMappingException weirdStringException(String str, Class<?> cls, String str2) {
        JsonParser jsonParser = this._parser;
        StringBuilder sb = new StringBuilder();
        sb.append("Can not construct instance of ");
        sb.append(cls.getName());
        sb.append(" from String value '");
        sb.append(_valueDesc());
        sb.append("': ");
        sb.append(str2);
        return InvalidFormatException.from(jsonParser, sb.toString(), str, cls);
    }

    @Deprecated
    public JsonMappingException weirdNumberException(Class<?> cls, String str) {
        return weirdStringException(null, cls, str);
    }

    public JsonMappingException weirdNumberException(Number number, Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        StringBuilder sb = new StringBuilder();
        sb.append("Can not construct instance of ");
        sb.append(cls.getName());
        sb.append(" from number value (");
        sb.append(_valueDesc());
        sb.append("): ");
        sb.append(str);
        return InvalidFormatException.from(jsonParser, sb.toString(), null, cls);
    }

    public JsonMappingException weirdKeyException(Class<?> cls, String str, String str2) {
        JsonParser jsonParser = this._parser;
        StringBuilder sb = new StringBuilder();
        sb.append("Can not construct Map key of type ");
        sb.append(cls.getName());
        sb.append(" from String \"");
        sb.append(_desc(str));
        sb.append("\": ");
        sb.append(str2);
        return InvalidFormatException.from(jsonParser, sb.toString(), str, cls);
    }

    public JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected token (");
        sb.append(jsonParser.getCurrentToken());
        sb.append("), expected ");
        sb.append(jsonToken);
        sb.append(": ");
        sb.append(str);
        return JsonMappingException.from(jsonParser, sb.toString());
    }

    public JsonMappingException unknownTypeException(JavaType javaType, String str) {
        JsonParser jsonParser = this._parser;
        StringBuilder sb = new StringBuilder();
        sb.append("Could not resolve type id '");
        sb.append(str);
        sb.append("' into a subtype of ");
        sb.append(javaType);
        return JsonMappingException.from(jsonParser, sb.toString());
    }

    public JsonMappingException endOfInputException(Class<?> cls) {
        JsonParser jsonParser = this._parser;
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected end-of-input when trying to deserialize a ");
        sb.append(cls.getName());
        return JsonMappingException.from(jsonParser, sb.toString());
    }

    /* access modifiers changed from: protected */
    public DateFormat getDateFormat() {
        if (this._dateFormat != null) {
            return this._dateFormat;
        }
        DateFormat dateFormat = (DateFormat) this._config.getDateFormat().clone();
        this._dateFormat = dateFormat;
        return dateFormat;
    }

    /* access modifiers changed from: protected */
    public String determineClassName(Object obj) {
        return ClassUtil.getClassDescription(obj);
    }

    /* access modifiers changed from: protected */
    public String _calcName(Class<?> cls) {
        if (!cls.isArray()) {
            return cls.getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(_calcName(cls.getComponentType()));
        sb.append("[]");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String _valueDesc() {
        try {
            return _desc(this._parser.getText());
        } catch (Exception unused) {
            return "[N/A]";
        }
    }

    /* access modifiers changed from: protected */
    public String _desc(String str) {
        if (str.length() <= 500) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, 500));
        sb.append("]...[");
        sb.append(str.substring(str.length() - 500));
        return sb.toString();
    }
}
