package kotlin.jvm.internal;

import kotlin.reflect.b;
import kotlin.reflect.i;
import kotlin.reflect.m.a;

public abstract class MutablePropertyReference2 extends MutablePropertyReference implements i {
    /* access modifiers changed from: protected */
    public b computeReflected() {
        return s.a(this);
    }

    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    public a getGetter() {
        return ((i) getReflected()).getGetter();
    }

    public i.a getSetter() {
        return ((i) getReflected()).getSetter();
    }

    public Object getDelegate(Object obj, Object obj2) {
        return ((i) getReflected()).getDelegate(obj, obj2);
    }
}
