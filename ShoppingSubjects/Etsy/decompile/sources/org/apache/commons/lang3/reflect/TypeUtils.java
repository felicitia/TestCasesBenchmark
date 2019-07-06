package org.apache.commons.lang3.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.lang3.ClassUtils;

public class TypeUtils {
    public static boolean isAssignable(Type type, Type type2) {
        return isAssignable(type, type2, null);
    }

    private static boolean isAssignable(Type type, Type type2, Map<TypeVariable<?>, Type> map) {
        if (type2 == null || (type2 instanceof Class)) {
            return isAssignable(type, (Class) type2);
        }
        if (type2 instanceof ParameterizedType) {
            return isAssignable(type, (ParameterizedType) type2, map);
        }
        if (type2 instanceof GenericArrayType) {
            return isAssignable(type, (GenericArrayType) type2, map);
        }
        if (type2 instanceof WildcardType) {
            return isAssignable(type, (WildcardType) type2, map);
        }
        if (type2 instanceof TypeVariable) {
            return isAssignable(type, (TypeVariable) type2, map);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("found an unhandled type: ");
        sb.append(type2);
        throw new IllegalStateException(sb.toString());
    }

    private static boolean isAssignable(Type type, Class<?> cls) {
        boolean z = true;
        if (type == null) {
            if (cls != null && cls.isPrimitive()) {
                z = false;
            }
            return z;
        } else if (cls == null) {
            return false;
        } else {
            if (cls.equals(type)) {
                return true;
            }
            if (type instanceof Class) {
                return ClassUtils.isAssignable((Class) type, cls);
            }
            if (type instanceof ParameterizedType) {
                return isAssignable((Type) getRawType((ParameterizedType) type), cls);
            }
            if (type instanceof TypeVariable) {
                for (Type isAssignable : ((TypeVariable) type).getBounds()) {
                    if (isAssignable(isAssignable, cls)) {
                        return true;
                    }
                }
                return false;
            } else if (type instanceof GenericArrayType) {
                if (!cls.equals(Object.class) && (!cls.isArray() || !isAssignable(((GenericArrayType) type).getGenericComponentType(), cls.getComponentType()))) {
                    z = false;
                }
                return z;
            } else if (type instanceof WildcardType) {
                return false;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("found an unhandled type: ");
                sb.append(type);
                throw new IllegalStateException(sb.toString());
            }
        }
    }

    private static boolean isAssignable(Type type, ParameterizedType parameterizedType, Map<TypeVariable<?>, Type> map) {
        if (type == null) {
            return true;
        }
        if (parameterizedType == null) {
            return false;
        }
        if (parameterizedType.equals(type)) {
            return true;
        }
        Class rawType = getRawType(parameterizedType);
        Map typeArguments = getTypeArguments(type, rawType, null);
        if (typeArguments == null) {
            return false;
        }
        if (typeArguments.isEmpty()) {
            return true;
        }
        for (Entry entry : getTypeArguments(parameterizedType, rawType, map).entrySet()) {
            Type type2 = (Type) entry.getValue();
            Type type3 = (Type) typeArguments.get(entry.getKey());
            if (type3 != null && !type2.equals(type3)) {
                if (!(type2 instanceof WildcardType) || !isAssignable(type3, type2, map)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isAssignable(Type type, GenericArrayType genericArrayType, Map<TypeVariable<?>, Type> map) {
        boolean z = true;
        if (type == null) {
            return true;
        }
        if (genericArrayType == null) {
            return false;
        }
        if (genericArrayType.equals(type)) {
            return true;
        }
        Type genericComponentType = genericArrayType.getGenericComponentType();
        if (type instanceof Class) {
            Class cls = (Class) type;
            if (!cls.isArray() || !isAssignable((Type) cls.getComponentType(), genericComponentType, map)) {
                z = false;
            }
            return z;
        } else if (type instanceof GenericArrayType) {
            return isAssignable(((GenericArrayType) type).getGenericComponentType(), genericComponentType, map);
        } else {
            if (type instanceof WildcardType) {
                for (Type isAssignable : getImplicitUpperBounds((WildcardType) type)) {
                    if (isAssignable(isAssignable, (Type) genericArrayType)) {
                        return true;
                    }
                }
                return false;
            } else if (type instanceof TypeVariable) {
                for (Type isAssignable2 : getImplicitBounds((TypeVariable) type)) {
                    if (isAssignable(isAssignable2, (Type) genericArrayType)) {
                        return true;
                    }
                }
                return false;
            } else if (type instanceof ParameterizedType) {
                return false;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("found an unhandled type: ");
                sb.append(type);
                throw new IllegalStateException(sb.toString());
            }
        }
    }

    private static boolean isAssignable(Type type, WildcardType wildcardType, Map<TypeVariable<?>, Type> map) {
        if (type == null) {
            return true;
        }
        if (wildcardType == null) {
            return false;
        }
        if (wildcardType.equals(type)) {
            return true;
        }
        Type[] implicitUpperBounds = getImplicitUpperBounds(wildcardType);
        Type[] implicitLowerBounds = getImplicitLowerBounds(wildcardType);
        if (type instanceof WildcardType) {
            WildcardType wildcardType2 = (WildcardType) type;
            Type[] implicitUpperBounds2 = getImplicitUpperBounds(wildcardType2);
            Type[] implicitLowerBounds2 = getImplicitLowerBounds(wildcardType2);
            for (Type substituteTypeVariables : implicitUpperBounds) {
                Type substituteTypeVariables2 = substituteTypeVariables(substituteTypeVariables, map);
                for (Type isAssignable : implicitUpperBounds2) {
                    if (!isAssignable(isAssignable, substituteTypeVariables2, map)) {
                        return false;
                    }
                }
            }
            for (Type substituteTypeVariables3 : implicitLowerBounds) {
                Type substituteTypeVariables4 = substituteTypeVariables(substituteTypeVariables3, map);
                for (Type isAssignable2 : implicitLowerBounds2) {
                    if (!isAssignable(substituteTypeVariables4, isAssignable2, map)) {
                        return false;
                    }
                }
            }
            return true;
        }
        for (Type substituteTypeVariables5 : implicitUpperBounds) {
            if (!isAssignable(type, substituteTypeVariables(substituteTypeVariables5, map), map)) {
                return false;
            }
        }
        for (Type substituteTypeVariables6 : implicitLowerBounds) {
            if (!isAssignable(substituteTypeVariables(substituteTypeVariables6, map), type, map)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isAssignable(Type type, TypeVariable<?> typeVariable, Map<TypeVariable<?>, Type> map) {
        if (type == null) {
            return true;
        }
        if (typeVariable == null) {
            return false;
        }
        if (typeVariable.equals(type)) {
            return true;
        }
        if (type instanceof TypeVariable) {
            for (Type isAssignable : getImplicitBounds((TypeVariable) type)) {
                if (isAssignable(isAssignable, typeVariable, map)) {
                    return true;
                }
            }
        }
        if ((type instanceof Class) || (type instanceof ParameterizedType) || (type instanceof GenericArrayType) || (type instanceof WildcardType)) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("found an unhandled type: ");
        sb.append(type);
        throw new IllegalStateException(sb.toString());
    }

    private static Type substituteTypeVariables(Type type, Map<TypeVariable<?>, Type> map) {
        if (!(type instanceof TypeVariable) || map == null) {
            return type;
        }
        Type type2 = (Type) map.get(type);
        if (type2 != null) {
            return type2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("missing assignment type for type variable ");
        sb.append(type);
        throw new IllegalArgumentException(sb.toString());
    }

    public static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType parameterizedType) {
        return getTypeArguments(parameterizedType, getRawType(parameterizedType), null);
    }

    public static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> cls) {
        return getTypeArguments(type, cls, null);
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> cls, Map<TypeVariable<?>, Type> map) {
        if (type instanceof Class) {
            return getTypeArguments((Class) type, cls, map);
        }
        if (type instanceof ParameterizedType) {
            return getTypeArguments((ParameterizedType) type, cls, map);
        }
        if (type instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            if (cls.isArray()) {
                cls = cls.getComponentType();
            }
            return getTypeArguments(genericComponentType, cls, map);
        }
        int i = 0;
        if (type instanceof WildcardType) {
            Type[] implicitUpperBounds = getImplicitUpperBounds((WildcardType) type);
            int length = implicitUpperBounds.length;
            while (i < length) {
                Type type2 = implicitUpperBounds[i];
                if (isAssignable(type2, cls)) {
                    return getTypeArguments(type2, cls, map);
                }
                i++;
            }
            return null;
        } else if (type instanceof TypeVariable) {
            Type[] implicitBounds = getImplicitBounds((TypeVariable) type);
            int length2 = implicitBounds.length;
            while (i < length2) {
                Type type3 = implicitBounds[i];
                if (isAssignable(type3, cls)) {
                    return getTypeArguments(type3, cls, map);
                }
                i++;
            }
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("found an unhandled type: ");
            sb.append(type);
            throw new IllegalStateException(sb.toString());
        }
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType parameterizedType, Class<?> cls, Map<TypeVariable<?>, Type> map) {
        Map<TypeVariable<?>, Type> map2;
        Class rawType = getRawType(parameterizedType);
        if (!isAssignable((Type) rawType, cls)) {
            return null;
        }
        Type ownerType = parameterizedType.getOwnerType();
        if (ownerType instanceof ParameterizedType) {
            ParameterizedType parameterizedType2 = (ParameterizedType) ownerType;
            map2 = getTypeArguments(parameterizedType2, getRawType(parameterizedType2), map);
        } else {
            map2 = map == null ? new HashMap<>() : new HashMap<>(map);
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        TypeVariable[] typeParameters = rawType.getTypeParameters();
        for (int i = 0; i < typeParameters.length; i++) {
            Type type = actualTypeArguments[i];
            TypeVariable typeVariable = typeParameters[i];
            if (map2.containsKey(type)) {
                type = (Type) map2.get(type);
            }
            map2.put(typeVariable, type);
        }
        if (cls.equals(rawType)) {
            return map2;
        }
        return getTypeArguments(getClosestParentType(rawType, cls), cls, map2);
    }

    private static Map<TypeVariable<?>, Type> getTypeArguments(Class<?> cls, Class<?> cls2, Map<TypeVariable<?>, Type> map) {
        if (!isAssignable((Type) cls, cls2)) {
            return null;
        }
        if (cls.isPrimitive()) {
            if (cls2.isPrimitive()) {
                return new HashMap();
            }
            cls = ClassUtils.primitiveToWrapper(cls);
        }
        HashMap hashMap = map == null ? new HashMap() : new HashMap(map);
        return (cls.getTypeParameters().length > 0 || cls2.equals(cls)) ? hashMap : getTypeArguments(getClosestParentType(cls, cls2), cls2, (Map<TypeVariable<?>, Type>) hashMap);
    }

    public static Map<TypeVariable<?>, Type> determineTypeArguments(Class<?> cls, ParameterizedType parameterizedType) {
        Class rawType = getRawType(parameterizedType);
        if (!isAssignable((Type) cls, rawType)) {
            return null;
        }
        if (cls.equals(rawType)) {
            return getTypeArguments(parameterizedType, rawType, null);
        }
        Type closestParentType = getClosestParentType(cls, rawType);
        if (closestParentType instanceof Class) {
            return determineTypeArguments((Class) closestParentType, parameterizedType);
        }
        ParameterizedType parameterizedType2 = (ParameterizedType) closestParentType;
        Map<TypeVariable<?>, Type> determineTypeArguments = determineTypeArguments(getRawType(parameterizedType2), parameterizedType);
        mapTypeVariablesToArguments(cls, parameterizedType2, determineTypeArguments);
        return determineTypeArguments;
    }

    private static <T> void mapTypeVariablesToArguments(Class<T> cls, ParameterizedType parameterizedType, Map<TypeVariable<?>, Type> map) {
        Type ownerType = parameterizedType.getOwnerType();
        if (ownerType instanceof ParameterizedType) {
            mapTypeVariablesToArguments(cls, (ParameterizedType) ownerType, map);
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        TypeVariable[] typeParameters = getRawType(parameterizedType).getTypeParameters();
        List asList = Arrays.asList(cls.getTypeParameters());
        for (int i = 0; i < actualTypeArguments.length; i++) {
            TypeVariable typeVariable = typeParameters[i];
            Type type = actualTypeArguments[i];
            if (asList.contains(type) && map.containsKey(typeVariable)) {
                map.put((TypeVariable) type, map.get(typeVariable));
            }
        }
    }

    private static Type getClosestParentType(Class<?> cls, Class<?> cls2) {
        Type[] genericInterfaces;
        Class cls3;
        if (cls2.isInterface()) {
            Type type = null;
            for (Type type2 : cls.getGenericInterfaces()) {
                if (type2 instanceof ParameterizedType) {
                    cls3 = getRawType((ParameterizedType) type2);
                } else if (type2 instanceof Class) {
                    cls3 = (Class) type2;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unexpected generic interface type found: ");
                    sb.append(type2);
                    throw new IllegalStateException(sb.toString());
                }
                if (isAssignable((Type) cls3, cls2) && isAssignable(type, (Type) cls3)) {
                    type = type2;
                }
            }
            if (type != null) {
                return type;
            }
        }
        return cls.getGenericSuperclass();
    }

    public static boolean isInstance(Object obj, Type type) {
        boolean z = false;
        if (type == null) {
            return false;
        }
        if (obj != null) {
            z = isAssignable((Type) obj.getClass(), type, null);
        } else if (!(type instanceof Class) || !((Class) type).isPrimitive()) {
            z = true;
        }
        return z;
    }

    public static Type[] normalizeUpperBounds(Type[] typeArr) {
        boolean z;
        if (typeArr.length < 2) {
            return typeArr;
        }
        HashSet hashSet = new HashSet(typeArr.length);
        for (Type type : typeArr) {
            int length = typeArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = false;
                    break;
                }
                Type type2 = typeArr[i];
                if (type != type2 && isAssignable(type2, type, null)) {
                    z = true;
                    break;
                }
                i++;
            }
            if (!z) {
                hashSet.add(type);
            }
        }
        return (Type[]) hashSet.toArray(new Type[hashSet.size()]);
    }

    public static Type[] getImplicitBounds(TypeVariable<?> typeVariable) {
        Type[] bounds = typeVariable.getBounds();
        if (bounds.length != 0) {
            return normalizeUpperBounds(bounds);
        }
        return new Type[]{Object.class};
    }

    public static Type[] getImplicitUpperBounds(WildcardType wildcardType) {
        Type[] upperBounds = wildcardType.getUpperBounds();
        if (upperBounds.length != 0) {
            return normalizeUpperBounds(upperBounds);
        }
        return new Type[]{Object.class};
    }

    public static Type[] getImplicitLowerBounds(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (lowerBounds.length != 0) {
            return lowerBounds;
        }
        return new Type[]{null};
    }

    public static boolean typesSatisfyVariables(Map<TypeVariable<?>, Type> map) {
        for (Entry entry : map.entrySet()) {
            TypeVariable typeVariable = (TypeVariable) entry.getKey();
            Type type = (Type) entry.getValue();
            Type[] implicitBounds = getImplicitBounds(typeVariable);
            int length = implicitBounds.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    if (!isAssignable(type, substituteTypeVariables(implicitBounds[i], map), map)) {
                        return false;
                    }
                    i++;
                }
            }
        }
        return true;
    }

    private static Class<?> getRawType(ParameterizedType parameterizedType) {
        Type rawType = parameterizedType.getRawType();
        if (rawType instanceof Class) {
            return (Class) rawType;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Wait... What!? Type of rawType: ");
        sb.append(rawType);
        throw new IllegalStateException(sb.toString());
    }

    public static Class<?> getRawType(Type type, Type type2) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getRawType((ParameterizedType) type);
        }
        if (type instanceof TypeVariable) {
            if (type2 == null) {
                return null;
            }
            GenericDeclaration genericDeclaration = ((TypeVariable) type).getGenericDeclaration();
            if (!(genericDeclaration instanceof Class)) {
                return null;
            }
            Map typeArguments = getTypeArguments(type2, (Class) genericDeclaration);
            if (typeArguments == null) {
                return null;
            }
            Type type3 = (Type) typeArguments.get(type);
            if (type3 == null) {
                return null;
            }
            return getRawType(type3, type2);
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType(), type2), 0).getClass();
        } else {
            if (type instanceof WildcardType) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("unknown type: ");
            sb.append(type);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static boolean isArrayType(Type type) {
        return (type instanceof GenericArrayType) || ((type instanceof Class) && ((Class) type).isArray());
    }

    public static Type getArrayComponentType(Type type) {
        Class cls = null;
        if (type instanceof Class) {
            Class cls2 = (Class) type;
            if (cls2.isArray()) {
                cls = cls2.getComponentType();
            }
            return cls;
        } else if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        } else {
            return null;
        }
    }
}
