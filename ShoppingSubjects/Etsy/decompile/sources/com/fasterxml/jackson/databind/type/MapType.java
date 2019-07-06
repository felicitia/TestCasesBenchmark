package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public final class MapType extends MapLikeType {
    private static final long serialVersionUID = -811146779148281500L;

    private MapType(Class<?> cls, JavaType javaType, JavaType javaType2, Object obj, Object obj2, boolean z) {
        super(cls, javaType, javaType2, obj, obj2, z);
    }

    public static MapType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        MapType mapType = new MapType(cls, javaType, javaType2, null, null, false);
        return mapType;
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        MapType mapType = new MapType(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        if (cls == this._valueType.getRawClass()) {
            return this;
        }
        MapType mapType = new MapType(this._class, this._keyType, this._valueType.narrowBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public JavaType widenContentsBy(Class<?> cls) {
        if (cls == this._valueType.getRawClass()) {
            return this;
        }
        MapType mapType = new MapType(this._class, this._keyType, this._valueType.widenBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public JavaType narrowKey(Class<?> cls) {
        if (cls == this._keyType.getRawClass()) {
            return this;
        }
        MapType mapType = new MapType(this._class, this._keyType.narrowBy(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public JavaType widenKey(Class<?> cls) {
        if (cls == this._keyType.getRawClass()) {
            return this;
        }
        MapType mapType = new MapType(this._class, this._keyType.widenBy(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public MapType withTypeHandler(Object obj) {
        MapType mapType = new MapType(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
        return mapType;
    }

    public MapType withContentTypeHandler(Object obj) {
        MapType mapType = new MapType(this._class, this._keyType, this._valueType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public MapType withValueHandler(Object obj) {
        MapType mapType = new MapType(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
        return mapType;
    }

    public MapType withContentValueHandler(Object obj) {
        MapType mapType = new MapType(this._class, this._keyType, this._valueType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public MapType withStaticTyping() {
        if (this._asStatic) {
            return this;
        }
        MapType mapType = new MapType(this._class, this._keyType.withStaticTyping(), this._valueType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
        return mapType;
    }

    public MapType withKeyTypeHandler(Object obj) {
        MapType mapType = new MapType(this._class, this._keyType.withTypeHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public MapType withKeyValueHandler(Object obj) {
        MapType mapType = new MapType(this._class, this._keyType.withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[map type; class ");
        sb.append(this._class.getName());
        sb.append(", ");
        sb.append(this._keyType);
        sb.append(" -> ");
        sb.append(this._valueType);
        sb.append("]");
        return sb.toString();
    }
}
