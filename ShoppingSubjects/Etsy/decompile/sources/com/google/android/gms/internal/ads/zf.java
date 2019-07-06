package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

final class zf {
    private static final Class<?> a = d();
    private static final zv<?, ?> b = a(false);
    private static final zv<?, ?> c = a(true);
    private static final zv<?, ?> d = new zx();

    static int a(int i, Object obj, zd zdVar) {
        return obj instanceof xs ? zzbav.a(i, (xs) obj) : zzbav.b(i, (yk) obj, zdVar);
    }

    static int a(int i, List<?> list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        int e = zzbav.e(i) * size;
        if (list instanceof xu) {
            xu xuVar = (xu) list;
            while (i2 < size) {
                Object b2 = xuVar.b(i2);
                e += b2 instanceof zzbah ? zzbav.b((zzbah) b2) : zzbav.b((String) b2);
                i2++;
            }
        } else {
            while (i2 < size) {
                Object obj = list.get(i2);
                e += obj instanceof zzbah ? zzbav.b((zzbah) obj) : zzbav.b((String) obj);
                i2++;
            }
        }
        return e;
    }

    static int a(int i, List<?> list, zd zdVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int e = zzbav.e(i) * size;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            e += obj instanceof xs ? zzbav.a((xs) obj) : zzbav.a((yk) obj, zdVar);
        }
        return e;
    }

    static int a(int i, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return a(list) + (list.size() * zzbav.e(i));
    }

    static int a(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof xz) {
            xz xzVar = (xz) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.d(xzVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.d(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zv<?, ?> a() {
        return b;
    }

    private static zv<?, ?> a(boolean z) {
        try {
            Class e = e();
            if (e == null) {
                return null;
            }
            return (zv) e.getConstructor(new Class[]{Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(z)});
        } catch (Throwable unused) {
            return null;
        }
    }

    static <UT, UB> UB a(int i, int i2, UB ub, zv<UT, UB> zvVar) {
        if (ub == null) {
            ub = zvVar.a();
        }
        zvVar.a(ub, i, (long) i2);
        return ub;
    }

    static <UT, UB> UB a(int i, List<Integer> list, xl<?> xlVar, UB ub, zv<UT, UB> zvVar) {
        UB ub2;
        if (xlVar == null) {
            return ub;
        }
        if (!(list instanceof RandomAccess)) {
            Iterator it = list.iterator();
            loop1:
            while (true) {
                ub2 = ub;
                while (it.hasNext()) {
                    int intValue = ((Integer) it.next()).intValue();
                    if (xlVar.a(intValue) == null) {
                        ub = a(i, intValue, ub2, zvVar);
                        it.remove();
                    }
                }
                break loop1;
            }
        } else {
            int size = list.size();
            ub2 = ub;
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                int intValue2 = ((Integer) list.get(i3)).intValue();
                if (xlVar.a(intValue2) != null) {
                    if (i3 != i2) {
                        list.set(i2, Integer.valueOf(intValue2));
                    }
                    i2++;
                } else {
                    ub2 = a(i, intValue2, ub2, zvVar);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return ub2;
            }
        }
        return ub2;
    }

    public static void a(int i, List<String> list, aai aai) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.a(i, list);
        }
    }

    public static void a(int i, List<?> list, aai aai, zd zdVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.a(i, list, zdVar);
        }
    }

    public static void a(int i, List<Double> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.g(i, list, z);
        }
    }

    static <T, FT extends xd<FT>> void a(wy<FT> wyVar, T t, T t2) {
        xb a2 = wyVar.a((Object) t2);
        if (!a2.b()) {
            wyVar.b(t).a(a2);
        }
    }

    static <T> void a(yf yfVar, T t, T t2, long j) {
        aab.a((Object) t, j, yfVar.a(aab.f(t, j), aab.f(t2, j)));
    }

    static <T, UT, UB> void a(zv<UT, UB> zvVar, T t, T t2) {
        zvVar.a((Object) t, zvVar.c(zvVar.b(t), zvVar.b(t2)));
    }

    public static void a(Class<?> cls) {
        if (!xh.class.isAssignableFrom(cls) && a != null && !a.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static boolean a(int i, int i2, int i3) {
        if (i2 < 40) {
            return true;
        }
        long j = (long) i3;
        return ((((long) i2) - ((long) i)) + 1) + 9 <= (3 + (2 * j)) + (3 * (3 + j));
    }

    static boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static int b(int i, List<zzbah> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int e = size * zzbav.e(i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            e += zzbav.b((zzbah) list.get(i2));
        }
        return e;
    }

    static int b(int i, List<yk> list, zd zdVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            i2 += zzbav.c(i, (yk) list.get(i3), zdVar);
        }
        return i2;
    }

    static int b(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return b(list) + (size * zzbav.e(i));
    }

    static int b(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof xz) {
            xz xzVar = (xz) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.e(xzVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.e(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zv<?, ?> b() {
        return c;
    }

    public static void b(int i, List<zzbah> list, aai aai) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.b(i, list);
        }
    }

    public static void b(int i, List<?> list, aai aai, zd zdVar) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.b(i, list, zdVar);
        }
    }

    public static void b(int i, List<Float> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.f(i, list, z);
        }
    }

    static int c(int i, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return c(list) + (size * zzbav.e(i));
    }

    static int c(List<Long> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof xz) {
            xz xzVar = (xz) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.f(xzVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.f(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static zv<?, ?> c() {
        return d;
    }

    public static void c(int i, List<Long> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.c(i, list, z);
        }
    }

    static int d(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return d(list) + (size * zzbav.e(i));
    }

    static int d(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof xi) {
            xi xiVar = (xi) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.k(xiVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.k(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    private static Class<?> d() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void d(int i, List<Long> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.d(i, list, z);
        }
    }

    static int e(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return e(list) + (size * zzbav.e(i));
    }

    static int e(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof xi) {
            xi xiVar = (xi) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.f(xiVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.f(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    private static Class<?> e() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void e(int i, List<Long> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.n(i, list, z);
        }
    }

    static int f(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return f(list) + (size * zzbav.e(i));
    }

    static int f(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof xi) {
            xi xiVar = (xi) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.g(xiVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.g(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void f(int i, List<Long> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.e(i, list, z);
        }
    }

    static int g(int i, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return g(list) + (size * zzbav.e(i));
    }

    static int g(List<Integer> list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof xi) {
            xi xiVar = (xi) list;
            i = 0;
            while (i2 < size) {
                i += zzbav.h(xiVar.b(i2));
                i2++;
            }
        } else {
            int i3 = 0;
            while (i2 < size) {
                i3 = i + zzbav.h(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static void g(int i, List<Long> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.l(i, list, z);
        }
    }

    static int h(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.i(i, 0);
    }

    static int h(List<?> list) {
        return list.size() << 2;
    }

    public static void h(int i, List<Integer> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.a(i, list, z);
        }
    }

    static int i(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.g(i, 0);
    }

    static int i(List<?> list) {
        return list.size() << 3;
    }

    public static void i(int i, List<Integer> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.j(i, list, z);
        }
    }

    static int j(int i, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbav.b(i, true);
    }

    static int j(List<?> list) {
        return list.size();
    }

    public static void j(int i, List<Integer> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.m(i, list, z);
        }
    }

    public static void k(int i, List<Integer> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.b(i, list, z);
        }
    }

    public static void l(int i, List<Integer> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.k(i, list, z);
        }
    }

    public static void m(int i, List<Integer> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.h(i, list, z);
        }
    }

    public static void n(int i, List<Boolean> list, aai aai, boolean z) throws IOException {
        if (list != null && !list.isEmpty()) {
            aai.i(i, list, z);
        }
    }
}
