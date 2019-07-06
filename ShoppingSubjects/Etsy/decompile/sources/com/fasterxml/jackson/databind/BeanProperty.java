package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.Named;
import java.lang.annotation.Annotation;

public interface BeanProperty extends Named {

    public static class Std implements BeanProperty {
        protected final Annotations _contextAnnotations;
        protected final boolean _isRequired;
        protected final AnnotatedMember _member;
        protected final String _name;
        protected final JavaType _type;
        protected final PropertyName _wrapperName;

        public Std(String str, JavaType javaType, PropertyName propertyName, Annotations annotations, AnnotatedMember annotatedMember, boolean z) {
            this._name = str;
            this._type = javaType;
            this._wrapperName = propertyName;
            this._isRequired = z;
            this._member = annotatedMember;
            this._contextAnnotations = annotations;
        }

        public Std withType(JavaType javaType) {
            Std std = new Std(this._name, javaType, this._wrapperName, this._contextAnnotations, this._member, this._isRequired);
            return std;
        }

        public <A extends Annotation> A getAnnotation(Class<A> cls) {
            if (this._member == null) {
                return null;
            }
            return this._member.getAnnotation(cls);
        }

        public <A extends Annotation> A getContextAnnotation(Class<A> cls) {
            if (this._contextAnnotations == null) {
                return null;
            }
            return this._contextAnnotations.get(cls);
        }

        public String getName() {
            return this._name;
        }

        public JavaType getType() {
            return this._type;
        }

        public PropertyName getWrapperName() {
            return this._wrapperName;
        }

        public boolean isRequired() {
            return this._isRequired;
        }

        public AnnotatedMember getMember() {
            return this._member;
        }

        public void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor) {
            StringBuilder sb = new StringBuilder();
            sb.append("Instances of ");
            sb.append(getClass().getName());
            sb.append(" should not get visited");
            throw new UnsupportedOperationException(sb.toString());
        }
    }

    void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor) throws JsonMappingException;

    <A extends Annotation> A getAnnotation(Class<A> cls);

    <A extends Annotation> A getContextAnnotation(Class<A> cls);

    AnnotatedMember getMember();

    String getName();

    JavaType getType();

    PropertyName getWrapperName();

    boolean isRequired();
}
