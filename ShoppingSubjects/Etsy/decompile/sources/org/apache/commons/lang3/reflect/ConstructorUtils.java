package org.apache.commons.lang3.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

public class ConstructorUtils {
    public static <T> T invokeConstructor(Class<T> cls, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        Class[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return invokeConstructor(cls, objArr, clsArr);
    }

    public static <T> T invokeConstructor(Class<T> cls, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (clsArr == null) {
            clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        Constructor matchingAccessibleConstructor = getMatchingAccessibleConstructor(cls, clsArr);
        if (matchingAccessibleConstructor != null) {
            return matchingAccessibleConstructor.newInstance(objArr);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("No such accessible constructor on object: ");
        sb.append(cls.getName());
        throw new NoSuchMethodException(sb.toString());
    }

    public static <T> T invokeExactConstructor(Class<T> cls, Object... objArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        int length = objArr.length;
        Class[] clsArr = new Class[length];
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return invokeExactConstructor(cls, objArr, clsArr);
    }

    public static <T> T invokeExactConstructor(Class<T> cls, Object[] objArr, Class<?>[] clsArr) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (objArr == null) {
            objArr = ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        if (clsArr == null) {
            clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Constructor accessibleConstructor = getAccessibleConstructor(cls, clsArr);
        if (accessibleConstructor != null) {
            return accessibleConstructor.newInstance(objArr);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("No such accessible constructor on object: ");
        sb.append(cls.getName());
        throw new NoSuchMethodException(sb.toString());
    }

    public static <T> Constructor<T> getAccessibleConstructor(Class<T> cls, Class<?>... clsArr) {
        try {
            return getAccessibleConstructor(cls.getConstructor(clsArr));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public static <T> Constructor<T> getAccessibleConstructor(Constructor<T> constructor) {
        if (!MemberUtils.isAccessible(constructor) || !Modifier.isPublic(constructor.getDeclaringClass().getModifiers())) {
            return null;
        }
        return constructor;
    }

    public static <T> Constructor<T> getMatchingAccessibleConstructor(Class<T> cls, Class<?>... clsArr) {
        Constructor[] constructors;
        try {
            Constructor<T> constructor = cls.getConstructor(clsArr);
            MemberUtils.setAccessibleWorkaround(constructor);
            return constructor;
        } catch (NoSuchMethodException unused) {
            Constructor<T> constructor2 = null;
            for (Constructor constructor3 : cls.getConstructors()) {
                if (ClassUtils.isAssignable(clsArr, (Class<?>[]) constructor3.getParameterTypes(), true)) {
                    Constructor<T> accessibleConstructor = getAccessibleConstructor(constructor3);
                    if (accessibleConstructor != null) {
                        MemberUtils.setAccessibleWorkaround(accessibleConstructor);
                        if (constructor2 == null || MemberUtils.compareParameterTypes(accessibleConstructor.getParameterTypes(), constructor2.getParameterTypes(), clsArr) < 0) {
                            constructor2 = accessibleConstructor;
                        }
                    }
                }
            }
            return constructor2;
        }
    }
}