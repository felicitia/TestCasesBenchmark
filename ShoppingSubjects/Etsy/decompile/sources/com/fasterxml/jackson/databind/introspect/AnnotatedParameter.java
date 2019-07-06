package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.type.TypeFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedParameter extends AnnotatedMember {
    private static final long serialVersionUID = 1;
    protected final int _index;
    protected final AnnotatedWithParams _owner;
    protected final Type _type;

    public AnnotatedElement getAnnotated() {
        return null;
    }

    public String getName() {
        return "";
    }

    public AnnotatedParameter(AnnotatedWithParams annotatedWithParams, Type type, AnnotationMap annotationMap, int i) {
        super(annotationMap);
        this._owner = annotatedWithParams;
        this._type = type;
        this._index = i;
    }

    public AnnotatedParameter withAnnotations(AnnotationMap annotationMap) {
        if (annotationMap == this._annotations) {
            return this;
        }
        return this._owner.replaceParameterAnnotations(this._index, annotationMap);
    }

    public int getModifiers() {
        return this._owner.getModifiers();
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        if (this._annotations == null) {
            return null;
        }
        return this._annotations.get(cls);
    }

    public Type getGenericType() {
        return this._type;
    }

    public Class<?> getRawType() {
        if (this._type instanceof Class) {
            return (Class) this._type;
        }
        return TypeFactory.defaultInstance().constructType(this._type).getRawClass();
    }

    public Class<?> getDeclaringClass() {
        return this._owner.getDeclaringClass();
    }

    public Member getMember() {
        return this._owner.getMember();
    }

    public void setValue(Object obj, Object obj2) throws UnsupportedOperationException {
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot call setValue() on constructor parameter of ");
        sb.append(getDeclaringClass().getName());
        throw new UnsupportedOperationException(sb.toString());
    }

    public Object getValue(Object obj) throws UnsupportedOperationException {
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot call getValue() on constructor parameter of ");
        sb.append(getDeclaringClass().getName());
        throw new UnsupportedOperationException(sb.toString());
    }

    public Type getParameterType() {
        return this._type;
    }

    public AnnotatedWithParams getOwner() {
        return this._owner;
    }

    public int getIndex() {
        return this._index;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[parameter #");
        sb.append(getIndex());
        sb.append(", annotations: ");
        sb.append(this._annotations);
        sb.append("]");
        return sb.toString();
    }
}
