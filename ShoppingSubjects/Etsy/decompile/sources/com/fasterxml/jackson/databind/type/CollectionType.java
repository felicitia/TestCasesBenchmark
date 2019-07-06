package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public final class CollectionType extends CollectionLikeType {
    private static final long serialVersionUID = -7834910259750909424L;

    private CollectionType(Class<?> cls, JavaType javaType, Object obj, Object obj2, boolean z) {
        super(cls, javaType, obj, obj2, z);
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        CollectionType collectionType = new CollectionType(cls, this._elementType, null, null, this._asStatic);
        return collectionType;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        if (cls == this._elementType.getRawClass()) {
            return this;
        }
        CollectionType collectionType = new CollectionType(this._class, this._elementType.narrowBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionType;
    }

    public JavaType widenContentsBy(Class<?> cls) {
        if (cls == this._elementType.getRawClass()) {
            return this;
        }
        CollectionType collectionType = new CollectionType(this._class, this._elementType.widenBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionType;
    }

    public static CollectionType construct(Class<?> cls, JavaType javaType) {
        CollectionType collectionType = new CollectionType(cls, javaType, null, null, false);
        return collectionType;
    }

    public CollectionType withTypeHandler(Object obj) {
        CollectionType collectionType = new CollectionType(this._class, this._elementType, this._valueHandler, obj, this._asStatic);
        return collectionType;
    }

    public CollectionType withContentTypeHandler(Object obj) {
        CollectionType collectionType = new CollectionType(this._class, this._elementType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionType;
    }

    public CollectionType withValueHandler(Object obj) {
        CollectionType collectionType = new CollectionType(this._class, this._elementType, obj, this._typeHandler, this._asStatic);
        return collectionType;
    }

    public CollectionType withContentValueHandler(Object obj) {
        CollectionType collectionType = new CollectionType(this._class, this._elementType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return collectionType;
    }

    public CollectionType withStaticTyping() {
        if (this._asStatic) {
            return this;
        }
        CollectionType collectionType = new CollectionType(this._class, this._elementType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
        return collectionType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[collection type; class ");
        sb.append(this._class.getName());
        sb.append(", contains ");
        sb.append(this._elementType);
        sb.append("]");
        return sb.toString();
    }
}
