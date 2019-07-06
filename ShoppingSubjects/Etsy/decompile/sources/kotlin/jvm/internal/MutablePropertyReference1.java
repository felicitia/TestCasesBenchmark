package kotlin.jvm.internal;

import kotlin.reflect.b;
import kotlin.reflect.h;
import kotlin.reflect.l.a;

public abstract class MutablePropertyReference1 extends MutablePropertyReference implements h {
    public MutablePropertyReference1() {
    }

    public MutablePropertyReference1(Object obj) {
        super(obj);
    }

    /* access modifiers changed from: protected */
    public b computeReflected() {
        return s.a(this);
    }

    public Object invoke(Object obj) {
        return get(obj);
    }

    public a getGetter() {
        return ((h) getReflected()).getGetter();
    }

    public h.a getSetter() {
        return ((h) getReflected()).getSetter();
    }

    public Object getDelegate(Object obj) {
        return ((h) getReflected()).getDelegate(obj);
    }
}
