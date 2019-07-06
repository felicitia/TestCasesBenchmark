package com.squareup.moshi;

import com.squareup.moshi.a.a;
import com.squareup.moshi.a.a.C0176a;
import com.squareup.moshi.a.a.b;
import com.squareup.moshi.a.a.c;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;

/* compiled from: Types */
public final class o {
    public static ParameterizedType a(Type type, Type... typeArr) {
        return new b(null, type, typeArr);
    }

    public static GenericArrayType a(Type type) {
        return new C0176a(type);
    }

    public static WildcardType b(Type type) {
        return new c(new Type[]{type}, a.b);
    }

    public static WildcardType c(Type type) {
        return new c(new Type[]{Object.class}, new Type[]{type});
    }

    public static Class<?> d(Type type) {
        String str;
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) type).getRawType();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(d(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return d(((WildcardType) type).getUpperBounds()[0]);
        }
        if (type == null) {
            str = "null";
        } else {
            str = type.getClass().getName();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Expected a Class, ParameterizedType, or GenericArrayType, but <");
        sb.append(type);
        sb.append("> is of type ");
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    public static Type a(Type type, Class<?> cls) {
        Type a = a(type, cls, Collection.class);
        if (a instanceof WildcardType) {
            a = ((WildcardType) a).getUpperBounds()[0];
        }
        if (a instanceof ParameterizedType) {
            return ((ParameterizedType) a).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static boolean a(Type type, Type type2) {
        Type[] typeArr;
        Type[] typeArr2;
        boolean z = true;
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            if (type2 instanceof GenericArrayType) {
                return a((Type) ((Class) type).getComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return type.equals(type2);
        } else if (type instanceof ParameterizedType) {
            if (!(type2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedType2 = (ParameterizedType) type2;
            if (parameterizedType instanceof b) {
                typeArr = ((b) parameterizedType).a;
            } else {
                typeArr = parameterizedType.getActualTypeArguments();
            }
            if (parameterizedType2 instanceof b) {
                typeArr2 = ((b) parameterizedType2).a;
            } else {
                typeArr2 = parameterizedType2.getActualTypeArguments();
            }
            if (!a(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) || !parameterizedType.getRawType().equals(parameterizedType2.getRawType()) || !Arrays.equals(typeArr, typeArr2)) {
                z = false;
            }
            return z;
        } else if (type instanceof GenericArrayType) {
            if (type2 instanceof Class) {
                return a((Type) ((Class) type2).getComponentType(), ((GenericArrayType) type).getGenericComponentType());
            }
            if (!(type2 instanceof GenericArrayType)) {
                return false;
            }
            return a(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
        } else if (type instanceof WildcardType) {
            if (!(type2 instanceof WildcardType)) {
                return false;
            }
            WildcardType wildcardType = (WildcardType) type;
            WildcardType wildcardType2 = (WildcardType) type2;
            if (!Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) || !Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds())) {
                z = false;
            }
            return z;
        } else if (!(type instanceof TypeVariable) || !(type2 instanceof TypeVariable)) {
            return false;
        } else {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            if (typeVariable.getGenericDeclaration() != typeVariable2.getGenericDeclaration() || !typeVariable.getName().equals(typeVariable2.getName())) {
                z = false;
            }
            return z;
        }
    }

    static Type[] b(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type a = a(type, cls, Map.class);
        if (a instanceof ParameterizedType) {
            return ((ParameterizedType) a).getActualTypeArguments();
        }
        return new Type[]{Object.class, Object.class};
    }

    static Type a(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2.isAssignableFrom(cls)) {
            return a.a(type, cls, a.a(type, cls, cls2));
        }
        throw new IllegalArgumentException();
    }

    static Type e(Type type) {
        Class d = d(type);
        return a.a(type, d, d.getGenericSuperclass());
    }

    static Type f(Type type) {
        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }
        if (type instanceof Class) {
            return ((Class) type).getComponentType();
        }
        return null;
    }

    static boolean g(Type type) {
        return type == Boolean.class || type == Byte.class || type == Character.class || type == Double.class || type == Float.class || type == Integer.class || type == Long.class || type == Short.class || type == String.class || type == Object.class;
    }
}
