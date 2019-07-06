package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;

public class RootNameLookup implements Serializable {
    private static final long serialVersionUID = 1;
    protected LRUMap<ClassKey, SerializedString> _rootNames;

    public SerializedString findRootName(JavaType javaType, MapperConfig<?> mapperConfig) {
        return findRootName(javaType.getRawClass(), mapperConfig);
    }

    public synchronized SerializedString findRootName(Class<?> cls, MapperConfig<?> mapperConfig) {
        String str;
        ClassKey classKey = new ClassKey(cls);
        if (this._rootNames == null) {
            this._rootNames = new LRUMap<>(20, 200);
        } else {
            SerializedString serializedString = (SerializedString) this._rootNames.get(classKey);
            if (serializedString != null) {
                return serializedString;
            }
        }
        PropertyName findRootName = mapperConfig.getAnnotationIntrospector().findRootName(mapperConfig.introspectClassAnnotations(cls).getClassInfo());
        if (findRootName != null) {
            if (findRootName.hasSimpleName()) {
                str = findRootName.getSimpleName();
                SerializedString serializedString2 = new SerializedString(str);
                this._rootNames.put(classKey, serializedString2);
                return serializedString2;
            }
        }
        str = cls.getSimpleName();
        SerializedString serializedString22 = new SerializedString(str);
        this._rootNames.put(classKey, serializedString22);
        return serializedString22;
    }
}
