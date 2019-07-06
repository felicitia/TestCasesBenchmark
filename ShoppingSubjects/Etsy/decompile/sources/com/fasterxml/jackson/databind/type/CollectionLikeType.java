package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;

public class CollectionLikeType extends TypeBase {
    private static final long serialVersionUID = 4611641304150899138L;
    protected final JavaType _elementType;

    public int containedTypeCount() {
        return 1;
    }

    public String containedTypeName(int i) {
        if (i == 0) {
            return "E";
        }
        return null;
    }

    public boolean isCollectionLikeType() {
        return true;
    }

    public boolean isContainerType() {
        return true;
    }

    protected CollectionLikeType(Class<?> cls, JavaType javaType, Object obj, Object obj2, boolean z) {
        super(cls, javaType.hashCode(), obj, obj2, z);
        this._elementType = javaType;
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        CollectionLikeType collectionLikeType = new CollectionLikeType(cls, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
        return collectionLikeType;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        if (cls == this._elementType.getRawClass()) {
            return this;
        }
        CollectionLikeType collectionLikeType = new CollectionLikeType(this._class, this._elementType.narrowBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionLikeType;
    }

    public JavaType widenContentsBy(Class<?> cls) {
        if (cls == this._elementType.getRawClass()) {
            return this;
        }
        CollectionLikeType collectionLikeType = new CollectionLikeType(this._class, this._elementType.widenBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionLikeType;
    }

    public static CollectionLikeType construct(Class<?> cls, JavaType javaType) {
        CollectionLikeType collectionLikeType = new CollectionLikeType(cls, javaType, null, null, false);
        return collectionLikeType;
    }

    public CollectionLikeType withTypeHandler(Object obj) {
        CollectionLikeType collectionLikeType = new CollectionLikeType(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
        return collectionLikeType;
    }

    public CollectionLikeType withContentTypeHandler(Object obj) {
        CollectionLikeType collectionLikeType = new CollectionLikeType(this._class, this._elementType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionLikeType;
    }

    public CollectionLikeType withValueHandler(Object obj) {
        CollectionLikeType collectionLikeType = new CollectionLikeType(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
        return collectionLikeType;
    }

    public CollectionLikeType withContentValueHandler(Object obj) {
        CollectionLikeType collectionLikeType = new CollectionLikeType(this._class, this._elementType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionLikeType;
    }

    public CollectionLikeType withStaticTyping() {
        if (this._asStatic) {
            return this;
        }
        CollectionLikeType collectionLikeType = new CollectionLikeType(this._class, this._elementType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
        return collectionLikeType;
    }

    public JavaType getContentType() {
        return this._elementType;
    }

    public JavaType containedType(int i) {
        if (i == 0) {
            return this._elementType;
        }
        return null;
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        sb.append('<');
        this._elementType.getGenericSignature(sb);
        sb.append(">;");
        return sb;
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._elementType != null) {
            sb.append('<');
            sb.append(this._elementType.toCanonical());
            sb.append('>');
        }
        return sb.toString();
    }

    public boolean isTrueCollectionType() {
        return Collection.class.isAssignableFrom(this._class);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        CollectionLikeType collectionLikeType = (CollectionLikeType) obj;
        if (this._class != collectionLikeType._class || !this._elementType.equals(collectionLikeType._elementType)) {
            z = false;
        }
        return z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[collection-like type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }
}
