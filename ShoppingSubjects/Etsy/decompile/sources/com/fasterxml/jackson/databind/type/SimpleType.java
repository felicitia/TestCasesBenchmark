package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;
import java.util.Map;

public final class SimpleType extends TypeBase {
    private static final long serialVersionUID = -800374828948534376L;
    protected final String[] _typeNames;
    protected final JavaType[] _typeParameters;

    public boolean isContainerType() {
        return false;
    }

    protected SimpleType(Class<?> cls) {
        this(cls, null, null, null, null, false);
    }

    protected SimpleType(Class<?> cls, String[] strArr, JavaType[] javaTypeArr, Object obj, Object obj2, boolean z) {
        super(cls, 0, obj, obj2, z);
        if (strArr == null || strArr.length == 0) {
            this._typeNames = null;
            this._typeParameters = null;
            return;
        }
        this._typeNames = strArr;
        this._typeParameters = javaTypeArr;
    }

    public static SimpleType constructUnsafe(Class<?> cls) {
        SimpleType simpleType = new SimpleType(cls, null, null, null, null, false);
        return simpleType;
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        SimpleType simpleType = new SimpleType(cls, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, this._asStatic);
        return simpleType;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
    }

    public JavaType widenContentsBy(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
    }

    public static SimpleType construct(Class<?> cls) {
        if (Map.class.isAssignableFrom(cls)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Can not construct SimpleType for a Map (class: ");
            sb.append(cls.getName());
            sb.append(")");
            throw new IllegalArgumentException(sb.toString());
        } else if (Collection.class.isAssignableFrom(cls)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Can not construct SimpleType for a Collection (class: ");
            sb2.append(cls.getName());
            sb2.append(")");
            throw new IllegalArgumentException(sb2.toString());
        } else if (!cls.isArray()) {
            return new SimpleType(cls);
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Can not construct SimpleType for an array (class: ");
            sb3.append(cls.getName());
            sb3.append(")");
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    public SimpleType withTypeHandler(Object obj) {
        SimpleType simpleType = new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, obj, this._asStatic);
        return simpleType;
    }

    public JavaType withContentTypeHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
    }

    public SimpleType withValueHandler(Object obj) {
        if (obj == this._valueHandler) {
            return this;
        }
        SimpleType simpleType = new SimpleType(this._class, this._typeNames, this._typeParameters, obj, this._typeHandler, this._asStatic);
        return simpleType;
    }

    public SimpleType withContentValueHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    public SimpleType withStaticTyping() {
        if (this._asStatic) {
            return this;
        }
        SimpleType simpleType = new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler, this._asStatic);
        return simpleType;
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        JavaType[] javaTypeArr;
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._typeParameters != null && this._typeParameters.length > 0) {
            sb.append('<');
            boolean z = true;
            for (JavaType javaType : this._typeParameters) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                sb.append(javaType.toCanonical());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    public int containedTypeCount() {
        if (this._typeParameters == null) {
            return 0;
        }
        return this._typeParameters.length;
    }

    public JavaType containedType(int i) {
        if (i < 0 || this._typeParameters == null || i >= this._typeParameters.length) {
            return null;
        }
        return this._typeParameters[i];
    }

    public String containedTypeName(int i) {
        if (i < 0 || this._typeNames == null || i >= this._typeNames.length) {
            return null;
        }
        return this._typeNames[i];
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        if (this._typeParameters != null) {
            sb.append('<');
            for (JavaType genericSignature : this._typeParameters) {
                sb = genericSignature.getGenericSignature(sb);
            }
            sb.append('>');
        }
        sb.append(';');
        return sb;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ");
        sb.append(buildCanonicalName());
        sb.append(']');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        SimpleType simpleType = (SimpleType) obj;
        if (simpleType._class != this._class) {
            return false;
        }
        JavaType[] javaTypeArr = this._typeParameters;
        JavaType[] javaTypeArr2 = simpleType._typeParameters;
        if (javaTypeArr == null) {
            if (!(javaTypeArr2 == null || javaTypeArr2.length == 0)) {
                z = false;
            }
            return z;
        } else if (javaTypeArr2 == null || javaTypeArr.length != javaTypeArr2.length) {
            return false;
        } else {
            int length = javaTypeArr.length;
            for (int i = 0; i < length; i++) {
                if (!javaTypeArr[i].equals(javaTypeArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
