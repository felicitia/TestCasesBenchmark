package kotlin.jvm.internal;

import kotlin.reflect.b;
import kotlin.reflect.g;
import kotlin.reflect.k.a;

public abstract class MutablePropertyReference0 extends MutablePropertyReference implements g {
    public MutablePropertyReference0() {
    }

    public MutablePropertyReference0(Object obj) {
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
        return ((g) getReflected()).getGetter();
    }

    public g.a getSetter() {
        return ((g) getReflected()).getSetter();
    }

    public Object getDelegate() {
        return ((g) getReflected()).getDelegate();
    }
}
