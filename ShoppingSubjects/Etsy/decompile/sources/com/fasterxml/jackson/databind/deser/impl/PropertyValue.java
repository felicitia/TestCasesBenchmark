package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;
import java.util.Map;

public abstract class PropertyValue {
    public final PropertyValue next;
    public final Object value;

    static final class a extends PropertyValue {
        final SettableAnyProperty a;
        final String b;

        public a(PropertyValue propertyValue, Object obj, SettableAnyProperty settableAnyProperty, String str) {
            super(propertyValue, obj);
            this.a = settableAnyProperty;
            this.b = str;
        }

        public void assign(Object obj) throws IOException, JsonProcessingException {
            this.a.set(obj, this.b, this.value);
        }
    }

    static final class b extends PropertyValue {
        final Object a;

        public b(PropertyValue propertyValue, Object obj, Object obj2) {
            super(propertyValue, obj);
            this.a = obj2;
        }

        public void assign(Object obj) throws IOException, JsonProcessingException {
            ((Map) obj).put(this.a, this.value);
        }
    }

    static final class c extends PropertyValue {
        final SettableBeanProperty a;

        public c(PropertyValue propertyValue, Object obj, SettableBeanProperty settableBeanProperty) {
            super(propertyValue, obj);
            this.a = settableBeanProperty;
        }

        public void assign(Object obj) throws IOException, JsonProcessingException {
            this.a.set(obj, this.value);
        }
    }

    public abstract void assign(Object obj) throws IOException, JsonProcessingException;

    protected PropertyValue(PropertyValue propertyValue, Object obj) {
        this.next = propertyValue;
        this.value = obj;
    }
}
