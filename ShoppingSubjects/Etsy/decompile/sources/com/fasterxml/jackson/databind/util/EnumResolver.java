package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import java.io.Serializable;
import java.lang.Enum;
import java.lang.reflect.Method;
import java.util.HashMap;

public class EnumResolver<T extends Enum<T>> implements Serializable {
    private static final long serialVersionUID = 1;
    protected final Class<T> _enumClass;
    protected final T[] _enums;
    protected final HashMap<String, T> _enumsById;

    protected EnumResolver(Class<T> cls, T[] tArr, HashMap<String, T> hashMap) {
        this._enumClass = cls;
        this._enums = tArr;
        this._enumsById = hashMap;
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructFor(Class<ET> cls, AnnotationIntrospector annotationIntrospector) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        if (enumArr == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("No enum constants for class ");
            sb.append(cls.getName());
            throw new IllegalArgumentException(sb.toString());
        }
        HashMap hashMap = new HashMap();
        for (Enum enumR : enumArr) {
            hashMap.put(annotationIntrospector.findEnumValue(enumR), enumR);
        }
        return new EnumResolver<>(cls, enumArr, hashMap);
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructUsingToString(Class<ET> cls) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int length = enumArr.length;
        while (true) {
            length--;
            if (length < 0) {
                return new EnumResolver<>(cls, enumArr, hashMap);
            }
            Enum enumR = enumArr[length];
            hashMap.put(enumR.toString(), enumR);
        }
    }

    public static <ET extends Enum<ET>> EnumResolver<ET> constructUsingMethod(Class<ET> cls, Method method) {
        Enum[] enumArr = (Enum[]) cls.getEnumConstants();
        HashMap hashMap = new HashMap();
        int length = enumArr.length;
        while (true) {
            length--;
            if (length < 0) {
                return new EnumResolver<>(cls, enumArr, hashMap);
            }
            Enum enumR = enumArr[length];
            try {
                Object invoke = method.invoke(enumR, new Object[0]);
                if (invoke != null) {
                    hashMap.put(invoke.toString(), enumR);
                }
            } catch (Exception e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to access @JsonValue of Enum value ");
                sb.append(enumR);
                sb.append(": ");
                sb.append(e.getMessage());
                throw new IllegalArgumentException(sb.toString());
            }
        }
    }

    public static EnumResolver<?> constructUnsafe(Class<?> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFor(cls, annotationIntrospector);
    }

    public static EnumResolver<?> constructUnsafeUsingToString(Class<?> cls) {
        return constructUsingToString(cls);
    }

    public static EnumResolver<?> constructUnsafeUsingMethod(Class<?> cls, Method method) {
        return constructUsingMethod(cls, method);
    }

    public T findEnum(String str) {
        return (Enum) this._enumsById.get(str);
    }

    public T getEnum(int i) {
        if (i < 0 || i >= this._enums.length) {
            return null;
        }
        return this._enums[i];
    }

    public Class<T> getEnumClass() {
        return this._enumClass;
    }

    public int lastValidIndex() {
        return this._enums.length - 1;
    }
}
