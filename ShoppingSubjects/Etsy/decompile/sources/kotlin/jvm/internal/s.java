package kotlin.jvm.internal;

import kotlin.reflect.c;
import kotlin.reflect.d;
import kotlin.reflect.e;
import kotlin.reflect.g;
import kotlin.reflect.h;
import kotlin.reflect.i;
import kotlin.reflect.k;
import kotlin.reflect.l;
import kotlin.reflect.m;

/* compiled from: Reflection */
public class s {
    private static final t a;
    private static final c[] b = new c[0];

    static {
        t tVar = null;
        try {
            tVar = (t) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (tVar == null) {
            tVar = new t();
        }
        a = tVar;
    }

    public static d a(Class cls, String str) {
        return a.a(cls, str);
    }

    public static c a(Class cls) {
        return a.a(cls);
    }

    public static String a(Lambda lambda) {
        return a.a(lambda);
    }

    public static e a(FunctionReference functionReference) {
        return a.a(functionReference);
    }

    public static k a(PropertyReference0 propertyReference0) {
        return a.a(propertyReference0);
    }

    public static g a(MutablePropertyReference0 mutablePropertyReference0) {
        return a.a(mutablePropertyReference0);
    }

    public static l a(PropertyReference1 propertyReference1) {
        return a.a(propertyReference1);
    }

    public static h a(MutablePropertyReference1 mutablePropertyReference1) {
        return a.a(mutablePropertyReference1);
    }

    public static m a(PropertyReference2 propertyReference2) {
        return a.a(propertyReference2);
    }

    public static i a(MutablePropertyReference2 mutablePropertyReference2) {
        return a.a(mutablePropertyReference2);
    }
}
