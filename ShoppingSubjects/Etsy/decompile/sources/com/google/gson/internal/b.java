package com.google.gson.internal;

import com.google.gson.JsonIOException;
import com.google.gson.a.a;
import com.google.gson.g;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/* compiled from: ConstructorConstructor */
public final class b {
    private final Map<Type, g<?>> a;

    public b(Map<Type, g<?>> map) {
        this.a = map;
    }

    public <T> e<T> a(a<T> aVar) {
        final Type b = aVar.b();
        Class a2 = aVar.a();
        final g gVar = (g) this.a.get(b);
        if (gVar != null) {
            return new e<T>() {
                public T a() {
                    return gVar.a(b);
                }
            };
        }
        final g gVar2 = (g) this.a.get(a2);
        if (gVar2 != null) {
            return new e<T>() {
                public T a() {
                    return gVar2.a(b);
                }
            };
        }
        e<T> a3 = a(a2);
        if (a3 != null) {
            return a3;
        }
        e<T> a4 = a(b, a2);
        if (a4 != null) {
            return a4;
        }
        return b(b, a2);
    }

    private <T> e<T> a(Class<? super T> cls) {
        try {
            final Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new e<T>() {
                public T a() {
                    try {
                        return declaredConstructor.newInstance(null);
                    } catch (InstantiationException e) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to invoke ");
                        sb.append(declaredConstructor);
                        sb.append(" with no args");
                        throw new RuntimeException(sb.toString(), e);
                    } catch (InvocationTargetException e2) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Failed to invoke ");
                        sb2.append(declaredConstructor);
                        sb2.append(" with no args");
                        throw new RuntimeException(sb2.toString(), e2.getTargetException());
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    }
                }
            };
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    private <T> e<T> a(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new e<T>() {
                    public T a() {
                        return new TreeSet();
                    }
                };
            }
            if (EnumSet.class.isAssignableFrom(cls)) {
                return new e<T>() {
                    public T a() {
                        if (type instanceof ParameterizedType) {
                            Type type = ((ParameterizedType) type).getActualTypeArguments()[0];
                            if (type instanceof Class) {
                                return EnumSet.noneOf((Class) type);
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append("Invalid EnumSet type: ");
                            sb.append(type.toString());
                            throw new JsonIOException(sb.toString());
                        }
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Invalid EnumSet type: ");
                        sb2.append(type.toString());
                        throw new JsonIOException(sb2.toString());
                    }
                };
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new e<T>() {
                    public T a() {
                        return new LinkedHashSet();
                    }
                };
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new e<T>() {
                    public T a() {
                        return new ArrayDeque();
                    }
                };
            }
            return new e<T>() {
                public T a() {
                    return new ArrayList();
                }
            };
        } else if (!Map.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                return new e<T>() {
                    public T a() {
                        return new ConcurrentSkipListMap();
                    }
                };
            }
            if (ConcurrentMap.class.isAssignableFrom(cls)) {
                return new e<T>() {
                    public T a() {
                        return new ConcurrentHashMap();
                    }
                };
            }
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new e<T>() {
                    public T a() {
                        return new TreeMap();
                    }
                };
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(a.a(((ParameterizedType) type).getActualTypeArguments()[0]).a())) {
                return new e<T>() {
                    public T a() {
                        return new LinkedTreeMap();
                    }
                };
            }
            return new e<T>() {
                public T a() {
                    return new LinkedHashMap();
                }
            };
        }
    }

    private <T> e<T> b(final Type type, final Class<? super T> cls) {
        return new e<T>() {
            private final h d = h.a();

            public T a() {
                try {
                    return this.d.a(cls);
                } catch (Exception e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to invoke no-args constructor for ");
                    sb.append(type);
                    sb.append(". Registering an InstanceCreator with Gson for this type may fix this problem.");
                    throw new RuntimeException(sb.toString(), e);
                }
            }
        };
    }

    public String toString() {
        return this.a.toString();
    }
}
