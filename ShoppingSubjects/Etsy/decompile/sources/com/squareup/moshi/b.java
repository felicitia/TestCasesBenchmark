package com.squareup.moshi;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* compiled from: ClassFactory */
abstract class b<T> {
    /* access modifiers changed from: 0000 */
    public abstract T a() throws InvocationTargetException, IllegalAccessException, InstantiationException;

    b() {
    }

    public static <T> b<T> a(final Class<?> cls) {
        try {
            final Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            return new b<T>() {
                public T a() throws IllegalAccessException, InvocationTargetException, InstantiationException {
                    return declaredConstructor.newInstance(null);
                }

                public String toString() {
                    return cls.getName();
                }
            };
        } catch (NoSuchMethodException unused) {
            try {
                Class cls2 = Class.forName("sun.misc.Unsafe");
                Field declaredField = cls2.getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                final Object obj = declaredField.get(null);
                final Method method = cls2.getMethod("allocateInstance", new Class[]{Class.class});
                return new b<T>() {
                    public T a() throws InvocationTargetException, IllegalAccessException {
                        return method.invoke(obj, new Object[]{cls});
                    }

                    public String toString() {
                        return cls.getName();
                    }
                };
            } catch (IllegalAccessException unused2) {
                throw new AssertionError();
            } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException unused3) {
                try {
                    Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});
                    declaredMethod.setAccessible(true);
                    final int intValue = ((Integer) declaredMethod.invoke(null, new Object[]{Object.class})).intValue();
                    final Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Integer.TYPE});
                    declaredMethod2.setAccessible(true);
                    return new b<T>() {
                        public T a() throws InvocationTargetException, IllegalAccessException {
                            return declaredMethod2.invoke(null, new Object[]{cls, Integer.valueOf(intValue)});
                        }

                        public String toString() {
                            return cls.getName();
                        }
                    };
                } catch (IllegalAccessException unused4) {
                    throw new AssertionError();
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException unused5) {
                    try {
                        final Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Class.class});
                        declaredMethod3.setAccessible(true);
                        return new b<T>() {
                            public T a() throws InvocationTargetException, IllegalAccessException {
                                return declaredMethod3.invoke(null, new Object[]{cls, Object.class});
                            }

                            public String toString() {
                                return cls.getName();
                            }
                        };
                    } catch (Exception unused6) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("cannot construct instances of ");
                        sb.append(cls.getName());
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
            }
        }
    }
}
