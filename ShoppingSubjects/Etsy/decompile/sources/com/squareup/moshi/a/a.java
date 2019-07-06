package com.squareup.moshi.a;

import com.squareup.moshi.f;
import com.squareup.moshi.o;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/* compiled from: Util */
public final class a {
    public static final Set<Annotation> a = Collections.emptySet();
    public static final Type[] b = new Type[0];

    /* renamed from: com.squareup.moshi.a.a$a reason: collision with other inner class name */
    /* compiled from: Util */
    public static final class C0176a implements GenericArrayType {
        private final Type a;

        public C0176a(Type type) {
            this.a = a.a(type);
        }

        public Type getGenericComponentType() {
            return this.a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && o.a((Type) this, (Type) (GenericArrayType) obj);
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(a.b(this.a));
            sb.append("[]");
            return sb.toString();
        }
    }

    /* compiled from: Util */
    public static final class b implements ParameterizedType {
        public final Type[] a;
        private final Type b;
        private final Type c;

        public b(Type type, Type type2, Type... typeArr) {
            Type type3;
            if (type2 instanceof Class) {
                Class enclosingClass = ((Class) type2).getEnclosingClass();
                if (type != null) {
                    if (enclosingClass == null || o.d(type) != enclosingClass) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("unexpected owner type for ");
                        sb.append(type2);
                        sb.append(": ");
                        sb.append(type);
                        throw new IllegalArgumentException(sb.toString());
                    }
                } else if (enclosingClass != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("unexpected owner type for ");
                    sb2.append(type2);
                    sb2.append(": null");
                    throw new IllegalArgumentException(sb2.toString());
                }
            }
            if (type == null) {
                type3 = null;
            } else {
                type3 = a.a(type);
            }
            this.b = type3;
            this.c = a.a(type2);
            this.a = (Type[]) typeArr.clone();
            for (int i = 0; i < this.a.length; i++) {
                if (this.a[i] == null) {
                    throw new NullPointerException();
                }
                a.c(this.a[i]);
                this.a[i] = a.a(this.a[i]);
            }
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.a.clone();
        }

        public Type getRawType() {
            return this.c;
        }

        public Type getOwnerType() {
            return this.b;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && o.a((Type) this, (Type) (ParameterizedType) obj);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.a) ^ this.c.hashCode()) ^ a.a((Object) this.b);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(30 * (this.a.length + 1));
            sb.append(a.b(this.c));
            if (this.a.length == 0) {
                return sb.toString();
            }
            sb.append("<");
            sb.append(a.b(this.a[0]));
            for (int i = 1; i < this.a.length; i++) {
                sb.append(", ");
                sb.append(a.b(this.a[i]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    /* compiled from: Util */
    public static final class c implements WildcardType {
        private final Type a;
        private final Type b;

        public c(Type[] typeArr, Type[] typeArr2) {
            if (typeArr2.length > 1) {
                throw new IllegalArgumentException();
            } else if (typeArr.length != 1) {
                throw new IllegalArgumentException();
            } else if (typeArr2.length == 1) {
                if (typeArr2[0] == null) {
                    throw new NullPointerException();
                }
                a.c(typeArr2[0]);
                if (typeArr[0] != Object.class) {
                    throw new IllegalArgumentException();
                }
                this.b = a.a(typeArr2[0]);
                this.a = Object.class;
            } else if (typeArr[0] == null) {
                throw new NullPointerException();
            } else {
                a.c(typeArr[0]);
                this.b = null;
                this.a = a.a(typeArr[0]);
            }
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.a};
        }

        public Type[] getLowerBounds() {
            if (this.b == null) {
                return a.b;
            }
            return new Type[]{this.b};
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && o.a((Type) this, (Type) (WildcardType) obj);
        }

        public int hashCode() {
            return (this.b != null ? this.b.hashCode() + 31 : 1) ^ (31 + this.a.hashCode());
        }

        public String toString() {
            if (this.b != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("? super ");
                sb.append(a.b(this.b));
                return sb.toString();
            } else if (this.a == Object.class) {
                return "?";
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("? extends ");
                sb2.append(a.b(this.a));
                return sb2.toString();
            }
        }
    }

    public static Set<? extends Annotation> a(AnnotatedElement annotatedElement) {
        return a(annotatedElement.getAnnotations());
    }

    public static Set<? extends Annotation> a(Annotation[] annotationArr) {
        LinkedHashSet linkedHashSet = null;
        for (Annotation annotation : annotationArr) {
            if (annotation.annotationType().isAnnotationPresent(f.class)) {
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet();
                }
                linkedHashSet.add(annotation);
            }
        }
        return linkedHashSet != null ? Collections.unmodifiableSet(linkedHashSet) : a;
    }

    public static boolean b(Annotation[] annotationArr) {
        for (Annotation annotationType : annotationArr) {
            if (annotationType.annotationType().getSimpleName().equals("Nullable")) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith("android.") || name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("kotlin.") || name.startsWith("scala.");
    }

    /* JADX WARNING: type inference failed for: r3v8, types: [java.lang.reflect.Type] */
    /* JADX WARNING: type inference failed for: r0v11, types: [com.squareup.moshi.a.a$a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type a(java.lang.reflect.Type r3) {
        /*
            boolean r0 = r3 instanceof java.lang.Class
            if (r0 == 0) goto L_0x001b
            java.lang.Class r3 = (java.lang.Class) r3
            boolean r0 = r3.isArray()
            if (r0 == 0) goto L_0x001a
            com.squareup.moshi.a.a$a r0 = new com.squareup.moshi.a.a$a
            java.lang.Class r3 = r3.getComponentType()
            java.lang.reflect.Type r3 = a(r3)
            r0.<init>(r3)
            r3 = r0
        L_0x001a:
            return r3
        L_0x001b:
            boolean r0 = r3 instanceof java.lang.reflect.ParameterizedType
            if (r0 == 0) goto L_0x0038
            boolean r0 = r3 instanceof com.squareup.moshi.a.a.b
            if (r0 == 0) goto L_0x0024
            return r3
        L_0x0024:
            java.lang.reflect.ParameterizedType r3 = (java.lang.reflect.ParameterizedType) r3
            com.squareup.moshi.a.a$b r0 = new com.squareup.moshi.a.a$b
            java.lang.reflect.Type r1 = r3.getOwnerType()
            java.lang.reflect.Type r2 = r3.getRawType()
            java.lang.reflect.Type[] r3 = r3.getActualTypeArguments()
            r0.<init>(r1, r2, r3)
            return r0
        L_0x0038:
            boolean r0 = r3 instanceof java.lang.reflect.GenericArrayType
            if (r0 == 0) goto L_0x004d
            boolean r0 = r3 instanceof com.squareup.moshi.a.a.C0176a
            if (r0 == 0) goto L_0x0041
            return r3
        L_0x0041:
            java.lang.reflect.GenericArrayType r3 = (java.lang.reflect.GenericArrayType) r3
            com.squareup.moshi.a.a$a r0 = new com.squareup.moshi.a.a$a
            java.lang.reflect.Type r3 = r3.getGenericComponentType()
            r0.<init>(r3)
            return r0
        L_0x004d:
            boolean r0 = r3 instanceof java.lang.reflect.WildcardType
            if (r0 == 0) goto L_0x0066
            boolean r0 = r3 instanceof com.squareup.moshi.a.a.c
            if (r0 == 0) goto L_0x0056
            return r3
        L_0x0056:
            java.lang.reflect.WildcardType r3 = (java.lang.reflect.WildcardType) r3
            com.squareup.moshi.a.a$c r0 = new com.squareup.moshi.a.a$c
            java.lang.reflect.Type[] r1 = r3.getUpperBounds()
            java.lang.reflect.Type[] r3 = r3.getLowerBounds()
            r0.<init>(r1, r3)
            return r0
        L_0x0066:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.a.a.a(java.lang.reflect.Type):java.lang.reflect.Type");
    }

    /* JADX WARNING: type inference failed for: r0v20, types: [java.lang.reflect.Type] */
    /* JADX WARNING: type inference failed for: r0v21, types: [java.lang.reflect.GenericArrayType] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.reflect.Type a(java.lang.reflect.Type r8, java.lang.Class<?> r9, java.lang.reflect.Type r10) {
        /*
        L_0x0000:
            boolean r0 = r10 instanceof java.lang.reflect.TypeVariable
            if (r0 == 0) goto L_0x000f
            java.lang.reflect.TypeVariable r10 = (java.lang.reflect.TypeVariable) r10
            java.lang.reflect.Type r0 = a(r8, r9, r10)
            if (r0 != r10) goto L_0x000d
            return r0
        L_0x000d:
            r10 = r0
            goto L_0x0000
        L_0x000f:
            boolean r0 = r10 instanceof java.lang.Class
            if (r0 == 0) goto L_0x002c
            r0 = r10
            java.lang.Class r0 = (java.lang.Class) r0
            boolean r1 = r0.isArray()
            if (r1 == 0) goto L_0x002c
            java.lang.Class r10 = r0.getComponentType()
            java.lang.reflect.Type r8 = a(r8, r9, r10)
            if (r10 != r8) goto L_0x0027
            goto L_0x002b
        L_0x0027:
            java.lang.reflect.GenericArrayType r0 = com.squareup.moshi.o.a(r8)
        L_0x002b:
            return r0
        L_0x002c:
            boolean r0 = r10 instanceof java.lang.reflect.GenericArrayType
            if (r0 == 0) goto L_0x0042
            java.lang.reflect.GenericArrayType r10 = (java.lang.reflect.GenericArrayType) r10
            java.lang.reflect.Type r0 = r10.getGenericComponentType()
            java.lang.reflect.Type r8 = a(r8, r9, r0)
            if (r0 != r8) goto L_0x003d
            goto L_0x0041
        L_0x003d:
            java.lang.reflect.GenericArrayType r10 = com.squareup.moshi.o.a(r8)
        L_0x0041:
            return r10
        L_0x0042:
            boolean r0 = r10 instanceof java.lang.reflect.ParameterizedType
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0085
            java.lang.reflect.ParameterizedType r10 = (java.lang.reflect.ParameterizedType) r10
            java.lang.reflect.Type r0 = r10.getOwnerType()
            java.lang.reflect.Type r3 = a(r8, r9, r0)
            if (r3 == r0) goto L_0x0056
            r0 = r1
            goto L_0x0057
        L_0x0056:
            r0 = r2
        L_0x0057:
            java.lang.reflect.Type[] r4 = r10.getActualTypeArguments()
            int r5 = r4.length
        L_0x005c:
            if (r2 >= r5) goto L_0x0077
            r6 = r4[r2]
            java.lang.reflect.Type r6 = a(r8, r9, r6)
            r7 = r4[r2]
            if (r6 == r7) goto L_0x0074
            if (r0 != 0) goto L_0x0072
            java.lang.Object r0 = r4.clone()
            r4 = r0
            java.lang.reflect.Type[] r4 = (java.lang.reflect.Type[]) r4
            r0 = r1
        L_0x0072:
            r4[r2] = r6
        L_0x0074:
            int r2 = r2 + 1
            goto L_0x005c
        L_0x0077:
            if (r0 == 0) goto L_0x0083
            com.squareup.moshi.a.a$b r8 = new com.squareup.moshi.a.a$b
            java.lang.reflect.Type r9 = r10.getRawType()
            r8.<init>(r3, r9, r4)
            goto L_0x0084
        L_0x0083:
            r8 = r10
        L_0x0084:
            return r8
        L_0x0085:
            boolean r0 = r10 instanceof java.lang.reflect.WildcardType
            if (r0 == 0) goto L_0x00b8
            java.lang.reflect.WildcardType r10 = (java.lang.reflect.WildcardType) r10
            java.lang.reflect.Type[] r0 = r10.getLowerBounds()
            java.lang.reflect.Type[] r3 = r10.getUpperBounds()
            int r4 = r0.length
            if (r4 != r1) goto L_0x00a5
            r1 = r0[r2]
            java.lang.reflect.Type r8 = a(r8, r9, r1)
            r9 = r0[r2]
            if (r8 == r9) goto L_0x00b7
            java.lang.reflect.WildcardType r8 = com.squareup.moshi.o.c(r8)
            return r8
        L_0x00a5:
            int r0 = r3.length
            if (r0 != r1) goto L_0x00b7
            r0 = r3[r2]
            java.lang.reflect.Type r8 = a(r8, r9, r0)
            r9 = r3[r2]
            if (r8 == r9) goto L_0x00b7
            java.lang.reflect.WildcardType r8 = com.squareup.moshi.o.b(r8)
            return r8
        L_0x00b7:
            return r10
        L_0x00b8:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.a.a.a(java.lang.reflect.Type, java.lang.Class, java.lang.reflect.Type):java.lang.reflect.Type");
    }

    static Type a(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Class a2 = a(typeVariable);
        if (a2 == null) {
            return typeVariable;
        }
        Type a3 = a(type, cls, a2);
        if (!(a3 instanceof ParameterizedType)) {
            return typeVariable;
        }
        return ((ParameterizedType) a3).getActualTypeArguments()[a((Object[]) a2.getTypeParameters(), (Object) typeVariable)];
    }

    public static Type a(Type type, Class<?> cls, Class<?> cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == cls2) {
                    return cls.getGenericInterfaces()[i];
                }
                if (cls2.isAssignableFrom(interfaces[i])) {
                    return a(cls.getGenericInterfaces()[i], interfaces[i], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return a(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    static int a(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    static String b(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }

    static int a(Object[] objArr, Object obj) {
        for (int i = 0; i < objArr.length; i++) {
            if (obj.equals(objArr[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    static Class<?> a(TypeVariable<?> typeVariable) {
        GenericDeclaration genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    static void c(Type type) {
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected primitive ");
            sb.append(type);
            sb.append(". Use the boxed type.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static String a(Type type, Set<? extends Annotation> set) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(type);
        if (set.isEmpty()) {
            str = " (with no annotations)";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(" annotated ");
            sb2.append(set);
            str = sb2.toString();
        }
        sb.append(str);
        return sb.toString();
    }
}
