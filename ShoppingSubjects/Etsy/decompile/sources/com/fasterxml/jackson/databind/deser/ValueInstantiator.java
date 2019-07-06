package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import java.io.IOException;

public abstract class ValueInstantiator {
    public boolean canCreateFromBoolean() {
        return false;
    }

    public boolean canCreateFromDouble() {
        return false;
    }

    public boolean canCreateFromInt() {
        return false;
    }

    public boolean canCreateFromLong() {
        return false;
    }

    public boolean canCreateFromObjectWith() {
        return false;
    }

    public boolean canCreateFromString() {
        return false;
    }

    public boolean canCreateUsingDelegate() {
        return false;
    }

    public AnnotatedWithParams getDefaultCreator() {
        return null;
    }

    public AnnotatedWithParams getDelegateCreator() {
        return null;
    }

    public JavaType getDelegateType(DeserializationConfig deserializationConfig) {
        return null;
    }

    public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig deserializationConfig) {
        return null;
    }

    public AnnotatedParameter getIncompleteParameter() {
        return null;
    }

    public abstract String getValueTypeDesc();

    public AnnotatedWithParams getWithArgsCreator() {
        return null;
    }

    public boolean canInstantiate() {
        return canCreateUsingDefault() || canCreateUsingDelegate() || canCreateFromObjectWith() || canCreateFromString() || canCreateFromInt() || canCreateFromLong() || canCreateFromDouble() || canCreateFromBoolean();
    }

    public boolean canCreateUsingDefault() {
        return getDefaultCreator() != null;
    }

    public Object createUsingDefault(DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Can not instantiate value of type ");
        sb.append(getValueTypeDesc());
        sb.append("; no default creator found");
        throw new JsonMappingException(sb.toString());
    }

    public Object createFromObjectWith(DeserializationContext deserializationContext, Object[] objArr) throws IOException, JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Can not instantiate value of type ");
        sb.append(getValueTypeDesc());
        sb.append(" with arguments");
        throw new JsonMappingException(sb.toString());
    }

    public Object createUsingDelegate(DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Can not instantiate value of type ");
        sb.append(getValueTypeDesc());
        sb.append(" using delegate");
        throw new JsonMappingException(sb.toString());
    }

    public Object createFromString(DeserializationContext deserializationContext, String str) throws IOException, JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Can not instantiate value of type ");
        sb.append(getValueTypeDesc());
        sb.append(" from String value");
        throw new JsonMappingException(sb.toString());
    }

    public Object createFromInt(DeserializationContext deserializationContext, int i) throws IOException, JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Can not instantiate value of type ");
        sb.append(getValueTypeDesc());
        sb.append(" from Integer number (int)");
        throw new JsonMappingException(sb.toString());
    }

    public Object createFromLong(DeserializationContext deserializationContext, long j) throws IOException, JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Can not instantiate value of type ");
        sb.append(getValueTypeDesc());
        sb.append(" from Integer number (long)");
        throw new JsonMappingException(sb.toString());
    }

    public Object createFromDouble(DeserializationContext deserializationContext, double d) throws IOException, JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Can not instantiate value of type ");
        sb.append(getValueTypeDesc());
        sb.append(" from Floating-point number (double)");
        throw new JsonMappingException(sb.toString());
    }

    public Object createFromBoolean(DeserializationContext deserializationContext, boolean z) throws IOException, JsonProcessingException {
        StringBuilder sb = new StringBuilder();
        sb.append("Can not instantiate value of type ");
        sb.append(getValueTypeDesc());
        sb.append(" from Boolean value");
        throw new JsonMappingException(sb.toString());
    }
}
