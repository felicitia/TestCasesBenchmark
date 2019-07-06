package kotlin.jvm.internal;

import kotlin.reflect.b;
import kotlin.reflect.k;
import kotlin.reflect.k.a;

public abstract class PropertyReference0 extends PropertyReference implements k {
    public PropertyReference0() {
    }

    public PropertyReference0(Object obj) {
        super(obj);
    }

    /* access modifiers changed from: protected */
    public b computeReflected() {
        return s.a(this);
    }

    public Object invoke() {
        return get();
    }

    public a getGetter() {
        return ((k) getReflected()).getGetter();
    }

    public Object getDelegate() {
        return ((k) getReflected()).getDelegate();
    }
}
