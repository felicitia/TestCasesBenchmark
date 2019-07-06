package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.Annotations;
import java.io.IOException;
import java.lang.annotation.Annotation;

public class CreatorProperty extends SettableBeanProperty {
    private static final long serialVersionUID = 1;
    protected final AnnotatedParameter _annotated;
    protected final int _creatorIndex;
    protected final Object _injectableValueId;

    public Object setAndReturn(Object obj, Object obj2) throws IOException {
        return obj;
    }

    @Deprecated
    public CreatorProperty(String str, JavaType javaType, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedParameter annotatedParameter, int i, Object obj) {
        this(str, javaType, null, typeDeserializer, annotations, annotatedParameter, i, obj, true);
    }

    public CreatorProperty(String str, JavaType javaType, PropertyName propertyName, TypeDeserializer typeDeserializer, Annotations annotations, AnnotatedParameter annotatedParameter, int i, Object obj, boolean z) {
        super(str, javaType, propertyName, typeDeserializer, annotations, z);
        this._annotated = annotatedParameter;
        this._creatorIndex = i;
        this._injectableValueId = obj;
    }

    protected CreatorProperty(CreatorProperty creatorProperty, String str) {
        super((SettableBeanProperty) creatorProperty, str);
        this._annotated = creatorProperty._annotated;
        this._creatorIndex = creatorProperty._creatorIndex;
        this._injectableValueId = creatorProperty._injectableValueId;
    }

    protected CreatorProperty(CreatorProperty creatorProperty, JsonDeserializer<?> jsonDeserializer) {
        super((SettableBeanProperty) creatorProperty, jsonDeserializer);
        this._annotated = creatorProperty._annotated;
        this._creatorIndex = creatorProperty._creatorIndex;
        this._injectableValueId = creatorProperty._injectableValueId;
    }

    public CreatorProperty withName(String str) {
        return new CreatorProperty(this, str);
    }

    public CreatorProperty withValueDeserializer(JsonDeserializer<?> jsonDeserializer) {
        return new CreatorProperty(this, jsonDeserializer);
    }

    public Object findInjectableValue(DeserializationContext deserializationContext, Object obj) {
        if (this._injectableValueId != null) {
            return deserializationContext.findInjectableValue(this._injectableValueId, this, obj);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Property '");
        sb.append(getName());
        sb.append("' (type ");
        sb.append(getClass().getName());
        sb.append(") has no injectable value id configured");
        throw new IllegalStateException(sb.toString());
    }

    public void inject(DeserializationContext deserializationContext, Object obj) throws IOException {
        set(obj, findInjectableValue(deserializationContext, obj));
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        if (this._annotated == null) {
            return null;
        }
        return this._annotated.getAnnotation(cls);
    }

    public AnnotatedMember getMember() {
        return this._annotated;
    }

    public int getCreatorIndex() {
        return this._creatorIndex;
    }

    public void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        set(obj, deserialize(jsonParser, deserializationContext));
    }

    public Object deserializeSetAndReturn(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        return setAndReturn(obj, deserialize(jsonParser, deserializationContext));
    }

    public void set(Object obj, Object obj2) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Method should never be called on a ");
        sb.append(getClass().getName());
        throw new IllegalStateException(sb.toString());
    }

    public Object getInjectableValueId() {
        return this._injectableValueId;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[creator property, name '");
        sb.append(getName());
        sb.append("'; inject id '");
        sb.append(this._injectableValueId);
        sb.append("']");
        return sb.toString();
    }
}
