package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedField extends AnnotatedMember implements Serializable {
    private static final long serialVersionUID = 7364428299211355871L;
    protected final transient Field _field;
    protected Serialization _serialization;

    private static final class Serialization implements Serializable {
        private static final long serialVersionUID = 1;
        protected Class<?> clazz;
        protected String name;

        public Serialization(Field field) {
            this.clazz = field.getDeclaringClass();
            this.name = field.getName();
        }
    }

    public AnnotatedField(Field field, AnnotationMap annotationMap) {
        super(annotationMap);
        this._field = field;
    }

    public AnnotatedField withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedField(this._field, annotationMap);
    }

    protected AnnotatedField(Serialization serialization) {
        super(null);
        this._field = null;
        this._serialization = serialization;
    }

    public Field getAnnotated() {
        return this._field;
    }

    public int getModifiers() {
        return this._field.getModifiers();
    }

    public String getName() {
        return this._field.getName();
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        if (this._annotations == null) {
            return null;
        }
        return this._annotations.get(cls);
    }

    public Type getGenericType() {
        return this._field.getGenericType();
    }

    public Class<?> getRawType() {
        return this._field.getType();
    }

    public Class<?> getDeclaringClass() {
        return this._field.getDeclaringClass();
    }

    public Member getMember() {
        return this._field;
    }

    public void setValue(Object obj, Object obj2) throws IllegalArgumentException {
        try {
            this._field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to setValue() for field ");
            sb.append(getFullName());
            sb.append(": ");
            sb.append(e.getMessage());
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    public Object getValue(Object obj) throws IllegalArgumentException {
        try {
            return this._field.get(obj);
        } catch (IllegalAccessException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to getValue() for field ");
            sb.append(getFullName());
            sb.append(": ");
            sb.append(e.getMessage());
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDeclaringClass().getName());
        sb.append("#");
        sb.append(getName());
        return sb.toString();
    }

    public int getAnnotationCount() {
        return this._annotations.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[field ");
        sb.append(getFullName());
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new AnnotatedField(new Serialization(this._field));
    }

    /* access modifiers changed from: 0000 */
    public Object readResolve() {
        Class<?> cls = this._serialization.clazz;
        try {
            Field declaredField = cls.getDeclaredField(this._serialization.name);
            if (!declaredField.isAccessible()) {
                ClassUtil.checkAndFixAccess(declaredField);
            }
            return new AnnotatedField(declaredField, null);
        } catch (Exception unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not find method '");
            sb.append(this._serialization.name);
            sb.append("' from Class '");
            sb.append(cls.getName());
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
