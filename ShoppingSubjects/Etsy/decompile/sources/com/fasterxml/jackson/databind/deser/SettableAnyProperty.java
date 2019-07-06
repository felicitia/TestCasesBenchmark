package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.io.IOException;
import java.lang.reflect.Method;

public final class SettableAnyProperty {
    protected final BeanProperty _property;
    protected final Method _setter;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;

    public SettableAnyProperty(BeanProperty beanProperty, AnnotatedMethod annotatedMethod, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        this(beanProperty, annotatedMethod.getAnnotated(), javaType, jsonDeserializer);
    }

    public SettableAnyProperty(BeanProperty beanProperty, Method method, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        this._property = beanProperty;
        this._type = javaType;
        this._setter = method;
        this._valueDeserializer = jsonDeserializer;
    }

    public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        return new SettableAnyProperty(this._property, this._setter, this._type, jsonDeserializer);
    }

    public BeanProperty getProperty() {
        return this._property;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public JavaType getType() {
        return this._type;
    }

    public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        set(obj, str, deserialize(jsonParser, deserializationContext));
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return this._valueDeserializer.deserialize(jsonParser, deserializationContext);
    }

    public final void set(Object obj, String str, Object obj2) throws IOException {
        try {
            this._setter.invoke(obj, new Object[]{str, obj2});
        } catch (Exception e) {
            _throwAsIOE(e, str, obj2);
        }
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.Throwable] */
    /* JADX WARNING: type inference failed for: r4v2, types: [java.lang.Throwable] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void _throwAsIOE(java.lang.Exception r4, java.lang.String r5, java.lang.Object r6) throws java.io.IOException {
        /*
            r3 = this;
            boolean r0 = r4 instanceof java.lang.IllegalArgumentException
            r1 = 0
            if (r0 == 0) goto L_0x0069
            if (r6 != 0) goto L_0x000a
            java.lang.String r6 = "[NULL]"
            goto L_0x0012
        L_0x000a:
            java.lang.Class r6 = r6.getClass()
            java.lang.String r6 = r6.getName()
        L_0x0012:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Problem deserializing \"any\" property '"
            r0.<init>(r2)
            r0.append(r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r2 = "' of class "
            r5.append(r2)
            java.lang.String r2 = r3.getClassName()
            r5.append(r2)
            java.lang.String r2 = " (expected type: "
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            r0.append(r5)
            com.fasterxml.jackson.databind.JavaType r5 = r3._type
            r0.append(r5)
            java.lang.String r5 = "; actual type: "
            r0.append(r5)
            r0.append(r6)
            java.lang.String r5 = ")"
            r0.append(r5)
            java.lang.String r5 = r4.getMessage()
            if (r5 == 0) goto L_0x005a
            java.lang.String r6 = ", problem: "
            r0.append(r6)
            r0.append(r5)
            goto L_0x005f
        L_0x005a:
            java.lang.String r5 = " (no error message provided)"
            r0.append(r5)
        L_0x005f:
            com.fasterxml.jackson.databind.JsonMappingException r5 = new com.fasterxml.jackson.databind.JsonMappingException
            java.lang.String r6 = r0.toString()
            r5.<init>(r6, r1, r4)
            throw r5
        L_0x0069:
            boolean r5 = r4 instanceof java.io.IOException
            if (r5 == 0) goto L_0x0070
            java.io.IOException r4 = (java.io.IOException) r4
            throw r4
        L_0x0070:
            boolean r5 = r4 instanceof java.lang.RuntimeException
            if (r5 == 0) goto L_0x0077
            java.lang.RuntimeException r4 = (java.lang.RuntimeException) r4
            throw r4
        L_0x0077:
            java.lang.Throwable r5 = r4.getCause()
            if (r5 == 0) goto L_0x0082
            java.lang.Throwable r4 = r4.getCause()
            goto L_0x0077
        L_0x0082:
            com.fasterxml.jackson.databind.JsonMappingException r5 = new com.fasterxml.jackson.databind.JsonMappingException
            java.lang.String r6 = r4.getMessage()
            r5.<init>(r6, r1, r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.SettableAnyProperty._throwAsIOE(java.lang.Exception, java.lang.String, java.lang.Object):void");
    }

    private String getClassName() {
        return this._setter.getDeclaringClass().getName();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[any property on class ");
        sb.append(getClassName());
        sb.append("]");
        return sb.toString();
    }
}
