package com.fasterxml.jackson.databind.util;

import android.support.v4.os.EnvironmentCompat;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;

public final class ClassUtil {

    private static class a {
        static final a a = new a();
        private final Field b = a(EnumSet.class, "elementType", Class.class);
        private final Field c = a(EnumMap.class, "elementType", Class.class);

        private a() {
        }

        public Class<? extends Enum<?>> a(EnumSet<?> enumSet) {
            if (this.b != null) {
                return (Class) a(enumSet, this.b);
            }
            throw new IllegalStateException("Can not figure out type for EnumSet (odd JDK platform?)");
        }

        public Class<? extends Enum<?>> a(EnumMap<?, ?> enumMap) {
            if (this.c != null) {
                return (Class) a(enumMap, this.c);
            }
            throw new IllegalStateException("Can not figure out type for EnumMap (odd JDK platform?)");
        }

        private Object a(Object obj, Field field) {
            try {
                return field.get(obj);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        private static Field a(Class<?> cls, String str, Class<?> cls2) {
            Field field;
            Field[] declaredFields = cls.getDeclaredFields();
            int length = declaredFields.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    field = null;
                    break;
                }
                field = declaredFields[i];
                if (str.equals(field.getName()) && field.getType() == cls2) {
                    break;
                }
                i++;
            }
            if (field == null) {
                for (Field field2 : declaredFields) {
                    if (field2.getType() == cls2) {
                        if (field != null) {
                            return null;
                        }
                        field = field2;
                    }
                }
            }
            if (field != null) {
                try {
                    field.setAccessible(true);
                } catch (Throwable unused) {
                }
            }
            return field;
        }
    }

    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2) {
        return findSuperTypes(cls, cls2, new ArrayList(8));
    }

    public static List<Class<?>> findSuperTypes(Class<?> cls, Class<?> cls2, List<Class<?>> list) {
        _addSuperTypes(cls, cls2, list, false);
        return list;
    }

    private static void _addSuperTypes(Class<?> cls, Class<?> cls2, Collection<Class<?>> collection, boolean z) {
        if (cls != cls2 && cls != null && cls != Object.class) {
            if (z) {
                if (!collection.contains(cls)) {
                    collection.add(cls);
                } else {
                    return;
                }
            }
            for (Class _addSuperTypes : cls.getInterfaces()) {
                _addSuperTypes(_addSuperTypes, cls2, collection, true);
            }
            _addSuperTypes(cls.getSuperclass(), cls2, collection, true);
        }
    }

    public static String canBeABeanType(Class<?> cls) {
        if (cls.isAnnotation()) {
            return "annotation";
        }
        if (cls.isArray()) {
            return "array";
        }
        if (cls.isEnum()) {
            return "enum";
        }
        if (cls.isPrimitive()) {
            return "primitive";
        }
        return null;
    }

    public static String isLocalType(Class<?> cls, boolean z) {
        try {
            if (cls.getEnclosingMethod() != null) {
                return "local/anonymous";
            }
            if (!z && cls.getEnclosingClass() != null && !Modifier.isStatic(cls.getModifiers())) {
                return "non-static member class";
            }
            return null;
        } catch (NullPointerException | SecurityException unused) {
        }
    }

    public static Class<?> getOuterClass(Class<?> cls) {
        try {
            if (cls.getEnclosingMethod() == null && !Modifier.isStatic(cls.getModifiers())) {
                return cls.getEnclosingClass();
            }
        } catch (NullPointerException | SecurityException unused) {
        }
        return null;
    }

    public static boolean isProxyType(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith("net.sf.cglib.proxy.") || name.startsWith("org.hibernate.proxy.");
    }

    public static boolean isConcrete(Class<?> cls) {
        return (cls.getModifiers() & 1536) == 0;
    }

    public static boolean isConcrete(Member member) {
        return (member.getModifiers() & 1536) == 0;
    }

    public static boolean isCollectionMapOrArray(Class<?> cls) {
        if (!cls.isArray() && !Collection.class.isAssignableFrom(cls) && !Map.class.isAssignableFrom(cls)) {
            return false;
        }
        return true;
    }

    public static String getClassDescription(Object obj) {
        if (obj == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return (obj instanceof Class ? (Class) obj : obj.getClass()).getName();
    }

    public static Class<?> findClass(String str) throws ClassNotFoundException {
        if (str.indexOf(46) < 0) {
            if ("int".equals(str)) {
                return Integer.TYPE;
            }
            if ("long".equals(str)) {
                return Long.TYPE;
            }
            if ("float".equals(str)) {
                return Float.TYPE;
            }
            if ("double".equals(str)) {
                return Double.TYPE;
            }
            if ("boolean".equals(str)) {
                return Boolean.TYPE;
            }
            if ("byte".equals(str)) {
                return Byte.TYPE;
            }
            if ("char".equals(str)) {
                return Character.TYPE;
            }
            if ("short".equals(str)) {
                return Short.TYPE;
            }
            if ("void".equals(str)) {
                return Void.TYPE;
            }
        }
        Throwable th = null;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            try {
                return Class.forName(str, true, contextClassLoader);
            } catch (Exception e) {
                th = getRootCause(e);
            }
        }
        try {
            return Class.forName(str);
        } catch (Exception e2) {
            if (th == null) {
                th = getRootCause(e2);
            }
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw new ClassNotFoundException(th.getMessage(), th);
        }
    }

    public static boolean hasGetterSignature(Method method) {
        if (Modifier.isStatic(method.getModifiers())) {
            return false;
        }
        Class[] parameterTypes = method.getParameterTypes();
        if ((parameterTypes == null || parameterTypes.length == 0) && Void.TYPE != method.getReturnType()) {
            return true;
        }
        return false;
    }

    public static Throwable getRootCause(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public static void throwRootCause(Throwable th) throws Exception {
        Throwable rootCause = getRootCause(th);
        if (rootCause instanceof Exception) {
            throw ((Exception) rootCause);
        }
        throw ((Error) rootCause);
    }

    public static void throwAsIAE(Throwable th) {
        throwAsIAE(th, th.getMessage());
    }

    public static void throwAsIAE(Throwable th, String str) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else {
            throw new IllegalArgumentException(str, th);
        }
    }

    public static void unwrapAndThrowAsIAE(Throwable th) {
        throwAsIAE(getRootCause(th));
    }

    public static void unwrapAndThrowAsIAE(Throwable th, String str) {
        throwAsIAE(getRootCause(th), str);
    }

    public static <T> T createInstance(Class<T> cls, boolean z) throws IllegalArgumentException {
        Constructor findConstructor = findConstructor(cls, z);
        if (findConstructor == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Class ");
            sb.append(cls.getName());
            sb.append(" has no default (no arg) constructor");
            throw new IllegalArgumentException(sb.toString());
        }
        try {
            return findConstructor.newInstance(new Object[0]);
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to instantiate class ");
            sb2.append(cls.getName());
            sb2.append(", problem: ");
            sb2.append(e.getMessage());
            unwrapAndThrowAsIAE(e, sb2.toString());
            return null;
        }
    }

    public static <T> Constructor<T> findConstructor(Class<T> cls, boolean z) throws IllegalArgumentException {
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (z) {
                checkAndFixAccess(declaredConstructor);
            } else if (!Modifier.isPublic(declaredConstructor.getModifiers())) {
                StringBuilder sb = new StringBuilder();
                sb.append("Default constructor for ");
                sb.append(cls.getName());
                sb.append(" is not accessible (non-public?): not allowed to try modify access via Reflection: can not instantiate type");
                throw new IllegalArgumentException(sb.toString());
            }
            return declaredConstructor;
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to find default constructor of class ");
            sb2.append(cls.getName());
            sb2.append(", problem: ");
            sb2.append(e.getMessage());
            unwrapAndThrowAsIAE(e, sb2.toString());
            return null;
        }
    }

    public static Object defaultValue(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.valueOf(0);
        }
        if (cls == Long.TYPE) {
            return Long.valueOf(0);
        }
        if (cls == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (cls == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (cls == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (cls == Byte.TYPE) {
            return Byte.valueOf(0);
        }
        if (cls == Short.TYPE) {
            return Short.valueOf(0);
        }
        if (cls == Character.TYPE) {
            return Character.valueOf(0);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Class ");
        sb.append(cls.getName());
        sb.append(" is not a primitive type");
        throw new IllegalArgumentException(sb.toString());
    }

    public static Class<?> wrapperType(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Double.TYPE) {
            return Double.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        if (cls == Byte.TYPE) {
            return Byte.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Class ");
        sb.append(cls.getName());
        sb.append(" is not a primitive type");
        throw new IllegalArgumentException(sb.toString());
    }

    public static void checkAndFixAccess(Member member) {
        AccessibleObject accessibleObject = (AccessibleObject) member;
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e) {
            if (!accessibleObject.isAccessible()) {
                Class declaringClass = member.getDeclaringClass();
                StringBuilder sb = new StringBuilder();
                sb.append("Can not access ");
                sb.append(member);
                sb.append(" (from class ");
                sb.append(declaringClass.getName());
                sb.append("; failed to set access: ");
                sb.append(e.getMessage());
                throw new IllegalArgumentException(sb.toString());
            }
        }
    }

    public static Class<? extends Enum<?>> findEnumType(EnumSet<?> enumSet) {
        if (!enumSet.isEmpty()) {
            return findEnumType((Enum) enumSet.iterator().next());
        }
        return a.a.a(enumSet);
    }

    public static Class<? extends Enum<?>> findEnumType(EnumMap<?, ?> enumMap) {
        if (!enumMap.isEmpty()) {
            return findEnumType((Enum) enumMap.keySet().iterator().next());
        }
        return a.a.a(enumMap);
    }

    public static Class<? extends Enum<?>> findEnumType(Enum<?> enumR) {
        Class<? extends Enum<?>> cls = enumR.getClass();
        return cls.getSuperclass() != Enum.class ? cls.getSuperclass() : cls;
    }

    public static Class<? extends Enum<?>> findEnumType(Class<?> cls) {
        return cls.getSuperclass() != Enum.class ? cls.getSuperclass() : cls;
    }

    public static boolean isJacksonStdImpl(Object obj) {
        return obj != null && isJacksonStdImpl(obj.getClass());
    }

    public static boolean isJacksonStdImpl(Class<?> cls) {
        return cls.getAnnotation(JacksonStdImpl.class) != null;
    }
}
