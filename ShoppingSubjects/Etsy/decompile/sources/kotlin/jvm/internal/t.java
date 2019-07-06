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

/* compiled from: ReflectionFactory */
public class t {
    public e a(FunctionReference functionReference) {
        return functionReference;
    }

    public g a(MutablePropertyReference0 mutablePropertyReference0) {
        return mutablePropertyReference0;
    }

    public h a(MutablePropertyReference1 mutablePropertyReference1) {
        return mutablePropertyReference1;
    }

    public i a(MutablePropertyReference2 mutablePropertyReference2) {
        return mutablePropertyReference2;
    }

    public k a(PropertyReference0 propertyReference0) {
        return propertyReference0;
    }

    public l a(PropertyReference1 propertyReference1) {
        return propertyReference1;
    }

    public m a(PropertyReference2 propertyReference2) {
        return propertyReference2;
    }

    public d a(Class cls, String str) {
        return new r(cls, str);
    }

    public c a(Class cls) {
        return new m(cls);
    }

    public String a(Lambda lambda) {
        String obj = lambda.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith("kotlin.jvm.functions.") ? obj.substring("kotlin.jvm.functions.".length()) : obj;
    }
}
