package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements ContextualDeserializer {
    private static final long serialVersionUID = 1;
    protected final JavaType _collectionType;
    protected final JsonDeserializer<Object> _delegateDeserializer;
    protected final JsonDeserializer<String> _valueDeserializer;
    protected final ValueInstantiator _valueInstantiator;

    public StringCollectionDeserializer(JavaType javaType, JsonDeserializer<?> jsonDeserializer, ValueInstantiator valueInstantiator) {
        this(javaType, valueInstantiator, null, jsonDeserializer);
    }

    protected StringCollectionDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        super(javaType.getRawClass());
        this._collectionType = javaType;
        this._valueDeserializer = jsonDeserializer2;
        this._valueInstantiator = valueInstantiator;
        this._delegateDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: protected */
    public StringCollectionDeserializer withResolved(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        if (this._valueDeserializer == jsonDeserializer2 && this._delegateDeserializer == jsonDeserializer) {
            return this;
        }
        return new StringCollectionDeserializer(this._collectionType, this._valueInstantiator, jsonDeserializer, jsonDeserializer2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonDeserializer<?> createContextual(com.fasterxml.jackson.databind.DeserializationContext r5, com.fasterxml.jackson.databind.BeanProperty r6) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r4 = this;
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r4._valueInstantiator
            r1 = 0
            if (r0 == 0) goto L_0x001c
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r4._valueInstantiator
            com.fasterxml.jackson.databind.introspect.AnnotatedWithParams r0 = r0.getDelegateCreator()
            if (r0 == 0) goto L_0x001c
            com.fasterxml.jackson.databind.deser.ValueInstantiator r0 = r4._valueInstantiator
            com.fasterxml.jackson.databind.DeserializationConfig r2 = r5.getConfig()
            com.fasterxml.jackson.databind.JavaType r0 = r0.getDelegateType(r2)
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r4.findDeserializer(r5, r0, r6)
            goto L_0x001d
        L_0x001c:
            r0 = r1
        L_0x001d:
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r2 = r4._valueDeserializer
            if (r2 != 0) goto L_0x0034
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r4.findConvertingContentDeserializer(r5, r6, r2)
            if (r2 != 0) goto L_0x0032
            com.fasterxml.jackson.databind.JavaType r2 = r4._collectionType
            com.fasterxml.jackson.databind.JavaType r2 = r2.getContentType()
            com.fasterxml.jackson.databind.JsonDeserializer r5 = r5.findContextualValueDeserializer(r2, r6)
            goto L_0x003e
        L_0x0032:
            r5 = r2
            goto L_0x003e
        L_0x0034:
            boolean r3 = r2 instanceof com.fasterxml.jackson.databind.deser.ContextualDeserializer
            if (r3 == 0) goto L_0x0032
            com.fasterxml.jackson.databind.deser.ContextualDeserializer r2 = (com.fasterxml.jackson.databind.deser.ContextualDeserializer) r2
            com.fasterxml.jackson.databind.JsonDeserializer r5 = r2.createContextual(r5, r6)
        L_0x003e:
            boolean r6 = r4.isDefaultDeserializer(r5)
            if (r6 == 0) goto L_0x0045
            r5 = r1
        L_0x0045:
            com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer r5 = r4.withResolved(r0, r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer.createContextual(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public JavaType getContentType() {
        return this._collectionType.getContentType();
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (this._delegateDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        return deserialize(jsonParser, deserializationContext, (Collection) this._valueInstantiator.createUsingDefault(deserializationContext));
    }

    public Collection<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException, JsonProcessingException {
        if (!jsonParser.isExpectedStartArrayToken()) {
            return handleNonArray(jsonParser, deserializationContext, collection);
        }
        if (this._valueDeserializer != null) {
            return deserializeUsingCustom(jsonParser, deserializationContext, collection, this._valueDeserializer);
        }
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            collection.add(nextToken == JsonToken.VALUE_NULL ? null : _parseString(jsonParser, deserializationContext));
        }
    }

    private Collection<String> deserializeUsingCustom(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection, JsonDeserializer<String> jsonDeserializer) throws IOException, JsonProcessingException {
        String str;
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken == JsonToken.END_ARRAY) {
                return collection;
            }
            if (nextToken == JsonToken.VALUE_NULL) {
                str = null;
            } else {
                str = (String) jsonDeserializer.deserialize(jsonParser, deserializationContext);
            }
            collection.add(str);
        }
    }

    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jsonParser, deserializationContext);
    }

    private final Collection<String> handleNonArray(JsonParser jsonParser, DeserializationContext deserializationContext, Collection<String> collection) throws IOException, JsonProcessingException {
        if (!deserializationContext.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            throw deserializationContext.mappingException(this._collectionType.getRawClass());
        }
        JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
        String str = jsonParser.getCurrentToken() == JsonToken.VALUE_NULL ? null : jsonDeserializer == null ? _parseString(jsonParser, deserializationContext) : (String) jsonDeserializer.deserialize(jsonParser, deserializationContext);
        collection.add(str);
        return collection;
    }
}
