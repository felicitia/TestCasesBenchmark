package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {
    private static final long serialVersionUID = 1;

    /* access modifiers changed from: protected */
    public abstract T _deserialize(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    protected FromStringDeserializer(Class<?> cls) {
        super(cls);
    }

    public final T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String valueAsString = jsonParser.getValueAsString();
        if (valueAsString != null) {
            if (valueAsString.length() != 0) {
                String trim = valueAsString.trim();
                if (trim.length() != 0) {
                    try {
                        T _deserialize = _deserialize(trim, deserializationContext);
                        if (_deserialize != null) {
                            return _deserialize;
                        }
                    } catch (IllegalArgumentException unused) {
                    }
                    throw deserializationContext.weirdStringException(trim, this._valueClass, "not a valid textual representation");
                }
            }
            return null;
        } else if (jsonParser.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT) {
            T embeddedObject = jsonParser.getEmbeddedObject();
            if (embeddedObject == null) {
                return null;
            }
            if (this._valueClass.isAssignableFrom(embeddedObject.getClass())) {
                return embeddedObject;
            }
            return _deserializeEmbedded(embeddedObject, deserializationContext);
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    /* access modifiers changed from: protected */
    public T _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Don't know how to convert embedded Object of type ");
        sb.append(obj.getClass().getName());
        sb.append(" into ");
        sb.append(this._valueClass.getName());
        throw deserializationContext.mappingException(sb.toString());
    }
}
