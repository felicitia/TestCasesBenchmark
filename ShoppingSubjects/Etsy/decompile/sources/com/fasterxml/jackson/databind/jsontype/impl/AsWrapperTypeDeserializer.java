package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;

public class AsWrapperTypeDeserializer extends TypeDeserializerBase implements Serializable {
    private static final long serialVersionUID = 5345570420394408290L;

    public AsWrapperTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, String str, boolean z, Class<?> cls) {
        super(javaType, typeIdResolver, str, z, null);
    }

    protected AsWrapperTypeDeserializer(AsWrapperTypeDeserializer asWrapperTypeDeserializer, BeanProperty beanProperty) {
        super(asWrapperTypeDeserializer, beanProperty);
    }

    public TypeDeserializer forProperty(BeanProperty beanProperty) {
        if (beanProperty == this._property) {
            return this;
        }
        return new AsWrapperTypeDeserializer(this, beanProperty);
    }

    public As getTypeInclusion() {
        return As.WRAPPER_OBJECT;
    }

    public Object deserializeTypedFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public Object deserializeTypedFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public Object deserializeTypedFromScalar(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    public Object deserializeTypedFromAny(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        return _deserialize(jsonParser, deserializationContext);
    }

    private final Object _deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
            JsonToken jsonToken = JsonToken.START_OBJECT;
            StringBuilder sb = new StringBuilder();
            sb.append("need JSON Object to contain As.WRAPPER_OBJECT type information for class ");
            sb.append(baseTypeName());
            throw deserializationContext.wrongTokenException(jsonParser, jsonToken, sb.toString());
        } else if (jsonParser.nextToken() != JsonToken.FIELD_NAME) {
            JsonToken jsonToken2 = JsonToken.FIELD_NAME;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("need JSON String that contains type id (for subtype of ");
            sb2.append(baseTypeName());
            sb2.append(")");
            throw deserializationContext.wrongTokenException(jsonParser, jsonToken2, sb2.toString());
        } else {
            String text = jsonParser.getText();
            JsonDeserializer _findDeserializer = _findDeserializer(deserializationContext, text);
            jsonParser.nextToken();
            if (this._typeIdVisible && jsonParser.getCurrentToken() == JsonToken.START_OBJECT) {
                TokenBuffer tokenBuffer = new TokenBuffer(null);
                tokenBuffer.writeStartObject();
                tokenBuffer.writeFieldName(this._typePropertyName);
                tokenBuffer.writeString(text);
                jsonParser = JsonParserSequence.createFlattened(tokenBuffer.asParser(jsonParser), jsonParser);
                jsonParser.nextToken();
            }
            Object deserialize = _findDeserializer.deserialize(jsonParser, deserializationContext);
            if (jsonParser.nextToken() == JsonToken.END_OBJECT) {
                return deserialize;
            }
            throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_OBJECT, "expected closing END_OBJECT after type information and deserialized value");
        }
    }
}
