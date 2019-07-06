package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public final class AnnotatedMethod extends AnnotatedWithParams implements Serializable {
    private static final long serialVersionUID = 1;
    protected final transient Method _method;
    protected Class<?>[] _paramClasses;
    protected Serialization _serialization;

    private static final class Serialization implements Serializable {
        private static final long serialVersionUID = 1;
        protected Class<?>[] args;
        protected Class<?> clazz;
        protected String name;

        public Serialization(Method method) {
            this.clazz = method.getDeclaringClass();
            this.name = method.getName();
            this.args = method.getParameterTypes();
        }
    }

    public AnnotatedMethod(Method method, AnnotationMap annotationMap, AnnotationMap[] annotationMapArr) {
        super(annotationMap, annotationMapArr);
        if (method == null) {
            throw new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
        }
        this._method = method;
    }

    protected AnnotatedMethod(Serialization serialization) {
        super(null, null);
        this._method = null;
        this._serialization = serialization;
    }

    public AnnotatedMethod withMethod(Method method) {
        return new AnnotatedMethod(method, this._annotations, this._paramAnnotations);
    }

    public AnnotatedMethod withAnnotations(AnnotationMap annotationMap) {
        return new AnnotatedMethod(this._method, annotationMap, this._paramAnnotations);
    }

    public Method getAnnotated() {
        return this._method;
    }

    public int getModifiers() {
        return this._method.getModifiers();
    }

    public String getName() {
        return this._method.getName();
    }

    public Type getGenericType() {
        return this._method.getGenericReturnType();
    }

    public Class<?> getRawType() {
        return this._method.getReturnType();
    }

    public JavaType getType(TypeBindings typeBindings) {
        return getType(typeBindings, this._method.getTypeParameters());
    }

    public final Object call() throws Exception {
        return this._method.invoke(null, new Object[0]);
    }

    public final Object call(Object[] objArr) throws Exception {
        return this._method.invoke(null, objArr);
    }

    public final Object call1(Object obj) throws Exception {
        return this._method.invoke(null, new Object[]{obj});
    }

    public Class<?> getDeclaringClass() {
        return this._method.getDeclaringClass();
    }

    public Method getMember() {
        return this._method;
    }

    public void setValue(Object obj, Object obj2) throws IllegalArgumentException {
        try {
            this._method.invoke(obj, new Object[]{obj2});
        } catch (IllegalAccessException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to setValue() with method ");
            sb.append(getFullName());
            sb.append(": ");
            sb.append(e.getMessage());
            throw new IllegalArgumentException(sb.toString(), e);
        } catch (InvocationTargetException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to setValue() with method ");
            sb2.append(getFullName());
            sb2.append(": ");
            sb2.append(e2.getMessage());
            throw new IllegalArgumentException(sb2.toString(), e2);
        }
    }

    public Object getValue(Object obj) throws IllegalArgumentException {
        try {
            return this._method.invoke(obj, new Object[0]);
        } catch (IllegalAccessException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to getValue() with method ");
            sb.append(getFullName());
            sb.append(": ");
            sb.append(e.getMessage());
            throw new IllegalArgumentException(sb.toString(), e);
        } catch (InvocationTargetException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to getValue() with method ");
            sb2.append(getFullName());
            sb2.append(": ");
            sb2.append(e2.getMessage());
            throw new IllegalArgumentException(sb2.toString(), e2);
        }
    }

    public int getParameterCount() {
        return getRawParameterTypes().length;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(getDeclaringClass().getName());
        sb.append("#");
        sb.append(getName());
        sb.append("(");
        sb.append(getParameterCount());
        sb.append(" params)");
        return sb.toString();
    }

    public Class<?>[] getRawParameterTypes() {
        if (this._paramClasses == null) {
            this._paramClasses = this._method.getParameterTypes();
        }
        return this._paramClasses;
    }

    public Type[] getGenericParameterTypes() {
        return this._method.getGenericParameterTypes();
    }

    public Class<?> getRawParameterType(int i) {
        Class<?>[] rawParameterTypes = getRawParameterTypes();
        if (i >= rawParameterTypes.length) {
            return null;
        }
        return rawParameterTypes[i];
    }

    public Type getGenericParameterType(int i) {
        Type[] genericParameterTypes = this._method.getGenericParameterTypes();
        if (i >= genericParameterTypes.length) {
            return null;
        }
        return genericParameterTypes[i];
    }

    public Class<?> getRawReturnType() {
        return this._method.getReturnType();
    }

    public Type getGenericReturnType() {
        return this._method.getGenericReturnType();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[method ");
        sb.append(getFullName());
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public Object writeReplace() {
        return new AnnotatedMethod(new Serialization(this._method));
    }

    /* access modifiers changed from: 0000 */
    public Object readResolve() {
        Class<?> cls = this._serialization.clazz;
        try {
            Method declaredMethod = cls.getDeclaredMethod(this._serialization.name, this._serialization.args);
            if (!declaredMethod.isAccessible()) {
                ClassUtil.checkAndFixAccess(declaredMethod);
            }
            return new AnnotatedMethod(declaredMethod, null, null);
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
