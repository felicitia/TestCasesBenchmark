package kotlin.jvm.internal;

import kotlin.reflect.b;
import kotlin.reflect.m;
import kotlin.reflect.m.a;

public abstract class PropertyReference2 extends PropertyReference implements m {
    /* access modifiers changed from: protected */
    public b computeReflected() {
        return s.a(this);
    }

    public Object invoke(Object obj, Object obj2) {
        return get(obj, obj2);
    }

    public a getGetter() {
        return ((m) getReflected()).getGetter();
    }

    public Object getDelegate(Object obj, Object obj2) {
        return ((m) getReflected()).getDelegate(obj, obj2);
    }
}
