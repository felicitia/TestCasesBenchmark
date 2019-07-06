package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class AbstractDeserializer extends JsonDeserializer<Object> implements Serializable {
    private static final long serialVersionUID = -3010349050434697698L;
    protected final boolean _acceptBoolean;
    protected final boolean _acceptDouble;
    protected final boolean _acceptInt;
    protected final boolean _acceptString;
    protected final Map<String, SettableBeanProperty> _backRefProperties;
    protected final JavaType _baseType;
    protected final ObjectIdReader _objectIdReader;

    public boolean isCachable() {
        return true;
    }

    public AbstractDeserializer(BeanDeserializerBuilder beanDeserializerBuilder, BeanDescription beanDescription, Map<String, SettableBeanProperty> map) {
        this._baseType = beanDescription.getType();
        this._objectIdReader = beanDeserializerBuilder.getObjectIdReader();
        this._backRefProperties = map;
        Class rawClass = this._baseType.getRawClass();
        this._acceptString = rawClass.isAssignableFrom(String.class);
        boolean z = true;
        this._acceptBoolean = rawClass == Boolean.TYPE || rawClass.isAssignableFrom(Boolean.class);
        this._acceptInt = rawClass == Integer.TYPE || rawClass.isAssignableFrom(Integer.class);
        if (rawClass != Double.TYPE && !rawClass.isAssignableFrom(Double.class)) {
            z = false;
        }
        this._acceptDouble = z;
    }

    public ObjectIdReader getObjectIdReader() {
        return this._objectIdReader;
    }

    public SettableBeanProperty findBackReference(String str) {
        if (this._backRefProperties == null) {
            return null;
        }
        return (SettableBeanProperty) this._backRefProperties.get(str);
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        if (this._objectIdReader != null) {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken != null && currentToken.isScalarValue()) {
                return _deserializeFromObjectId(jsonParser, deserializationContext);
            }
        }
        Object _deserializeIfNatural = _deserializeIfNatural(jsonParser, deserializationContext);
        if (_deserializeIfNatural != null) {
            return _deserializeIfNatural;
        }
        return typeDeserializer.deserializeTypedFromObject(jsonParser, deserializationContext);
    }

    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        throw deserializationContext.instantiationException(this._baseType.getRawClass(), "abstract types either need to be mapped to concrete types, have custom deserializer, or be instantiated with additional type information");
    }

    /* access modifiers changed from: protected */
    public Object _deserializeIfNatural(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken()) {
            case VALUE_STRING:
                if (this._acceptString) {
                    return jsonParser.getText();
                }
                break;
            case VALUE_NUMBER_INT:
                if (this._acceptInt) {
                    return Integer.valueOf(jsonParser.getIntValue());
                }
                break;
            case VALUE_NUMBER_FLOAT:
                if (this._acceptDouble) {
                    return Double.valueOf(jsonParser.getDoubleValue());
                }
                break;
            case VALUE_TRUE:
                if (this._acceptBoolean) {
                    return Boolean.TRUE;
                }
                break;
            case VALUE_FALSE:
                if (this._acceptBoolean) {
                    return Boolean.FALSE;
                }
                break;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Object _deserializeFromObjectId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object deserialize = this._objectIdReader.deserializer.deserialize(jsonParser, deserializationContext);
        Object obj = deserializationContext.findObjectId(deserialize, this._objectIdReader.generator).item;
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not resolve Object Id [");
        sb.append(deserialize);
        sb.append("] -- unresolved forward-reference?");
        throw new IllegalStateException(sb.toString());
    }
}
