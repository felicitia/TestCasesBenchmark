package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Array;
import java.lang.reflect.Type;

public final class ArrayType extends TypeBase {
    private static final long serialVersionUID = 9040058063449087477L;
    protected final JavaType _componentType;
    protected final Object _emptyArray;

    public int containedTypeCount() {
        return 1;
    }

    public String containedTypeName(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    public boolean isAbstract() {
        return false;
    }

    public boolean isArrayType() {
        return true;
    }

    public boolean isConcrete() {
        return true;
    }

    public boolean isContainerType() {
        return true;
    }

    private ArrayType(JavaType javaType, Object obj, Object obj2, Object obj3, boolean z) {
        super(obj.getClass(), javaType.hashCode(), obj2, obj3, z);
        this._componentType = javaType;
        this._emptyArray = obj;
    }

    public static ArrayType construct(JavaType javaType, Object obj, Object obj2) {
        ArrayType arrayType = new ArrayType(javaType, Array.newInstance(javaType.getRawClass(), 0), null, null, false);
        return arrayType;
    }

    public ArrayType withTypeHandler(Object obj) {
        if (obj == this._typeHandler) {
            return this;
        }
        ArrayType arrayType = new ArrayType(this._componentType, this._emptyArray, this._valueHandler, obj, this._asStatic);
        return arrayType;
    }

    public ArrayType withContentTypeHandler(Object obj) {
        if (obj == this._componentType.getTypeHandler()) {
            return this;
        }
        ArrayType arrayType = new ArrayType(this._componentType.withTypeHandler(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
        return arrayType;
    }

    public ArrayType withValueHandler(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        ArrayType arrayType = new ArrayType(this._componentType, this._emptyArray, obj, this._typeHandler, this._asStatic);
        return arrayType;
    }

    public ArrayType withContentValueHandler(Object obj) {
        if (obj == this._componentType.getValueHandler()) {
            return this;
        }
        ArrayType arrayType = new ArrayType(this._componentType.withValueHandler(obj), this._emptyArray, this._valueHandler, this._typeHandler, this._asStatic);
        return arrayType;
    }

    public ArrayType withStaticTyping() {
        if (this._asStatic) {
            return this;
        }
        ArrayType arrayType = new ArrayType(this._componentType.withStaticTyping(), this._emptyArray, this._valueHandler, this._typeHandler, true);
        return arrayType;
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        return this._class.getName();
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        if (!cls.isArray()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Incompatible narrowing operation: trying to narrow ");
            sb.append(toString());
            sb.append(" to class ");
            sb.append(cls.getName());
            throw new IllegalArgumentException(sb.toString());
        }
        return construct(TypeFactory.defaultInstance().constructType((Type) cls.getComponentType()), this._valueHandler, this._typeHandler);
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        if (cls == this._componentType.getRawClass()) {
            return this;
        }
        return construct(this._componentType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    public JavaType widenContentsBy(Class<?> cls) {
        if (cls == this._componentType.getRawClass()) {
            return this;
        }
        return construct(this._componentType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    public boolean hasGenericTypes() {
        return this._componentType.hasGenericTypes();
    }

    public JavaType getContentType() {
        return this._componentType;
    }

    public JavaType containedType(int i) {
        if (i == 0) {
            return this._componentType;
        }
        return null;
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        sb.append('[');
        return this._componentType.getGenericSignature(sb);
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        sb.append('[');
        return this._componentType.getErasedSignature(sb);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[array type, component type: ");
        sb.append(this._componentType);
        sb.append("]");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this._componentType.equals(((ArrayType) obj)._componentType);
    }
}
