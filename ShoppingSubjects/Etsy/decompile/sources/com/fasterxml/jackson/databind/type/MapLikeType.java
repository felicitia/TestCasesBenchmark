package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;
import java.util.Map;

public class MapLikeType extends TypeBase {
    private static final long serialVersionUID = 416067702302823522L;
    protected final JavaType _keyType;
    protected final JavaType _valueType;

    public int containedTypeCount() {
        return 2;
    }

    public String containedTypeName(int i) {
        if (i == 0) {
            return "K";
        }
        if (i == 1) {
            return "V";
        }
        return null;
    }

    public boolean isContainerType() {
        return true;
    }

    public boolean isMapLikeType() {
        return true;
    }

    protected MapLikeType(Class<?> cls, JavaType javaType, JavaType javaType2, Object obj, Object obj2, boolean z) {
        super(cls, javaType.hashCode() ^ javaType2.hashCode(), obj, obj2, z);
        this._keyType = javaType;
        this._valueType = javaType2;
    }

    public static MapLikeType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        MapLikeType mapLikeType = new MapLikeType(cls, javaType, javaType2, null, null, false);
        return mapLikeType;
    }

    /* access modifiers changed from: protected */
    public JavaType _narrow(Class<?> cls) {
        MapLikeType mapLikeType = new MapLikeType(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public JavaType narrowContentsBy(Class<?> cls) {
        if (cls == this._valueType.getRawClass()) {
            return this;
        }
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType, this._valueType.narrowBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public JavaType widenContentsBy(Class<?> cls) {
        if (cls == this._valueType.getRawClass()) {
            return this;
        }
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType, this._valueType.widenBy(cls), this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public JavaType narrowKey(Class<?> cls) {
        if (cls == this._keyType.getRawClass()) {
            return this;
        }
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType.narrowBy(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public JavaType widenKey(Class<?> cls) {
        if (cls == this._keyType.getRawClass()) {
            return this;
        }
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType.widenBy(cls), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withTypeHandler(Object obj) {
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withContentTypeHandler(Object obj) {
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType, this._valueType.withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withValueHandler(Object obj) {
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withContentValueHandler(Object obj) {
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType, this._valueType.withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withStaticTyping() {
        if (this._asStatic) {
            return this;
        }
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType, this._valueType.withStaticTyping(), this._valueHandler, this._typeHandler, true);
        return mapLikeType;
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._keyType != null) {
            sb.append('<');
            sb.append(this._keyType.toCanonical());
            sb.append(',');
            sb.append(this._valueType.toCanonical());
            sb.append('>');
        }
        return sb.toString();
    }

    public JavaType getKeyType() {
        return this._keyType;
    }

    public JavaType getContentType() {
        return this._valueType;
    }

    public JavaType containedType(int i) {
        if (i == 0) {
            return this._keyType;
        }
        if (i == 1) {
            return this._valueType;
        }
        return null;
    }

    public StringBuilder getErasedSignature(StringBuilder sb) {
        return _classSignature(this._class, sb, true);
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        sb.append('<');
        this._keyType.getGenericSignature(sb);
        this._valueType.getGenericSignature(sb);
        sb.append(">;");
        return sb;
    }

    public MapLikeType withKeyTypeHandler(Object obj) {
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType.withTypeHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public MapLikeType withKeyValueHandler(Object obj) {
        MapLikeType mapLikeType = new MapLikeType(this._class, this._keyType.withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
        return mapLikeType;
    }

    public boolean isTrueMapType() {
        return Map.class.isAssignableFrom(this._class);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[map-like type; class ");
        sb.append(this._class.getName());
        sb.append(", ");
        sb.append(this._keyType);
        sb.append(" -> ");
        sb.append(this._valueType);
        sb.append("]");
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
        MapLikeType mapLikeType = (MapLikeType) obj;
        if (this._class != mapLikeType._class || !this._keyType.equals(mapLikeType._keyType) || !this._valueType.equals(mapLikeType._valueType)) {
            z = false;
        }
        return z;
    }
}
