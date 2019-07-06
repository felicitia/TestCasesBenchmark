package kotlin.jvm.internal;

import kotlin.reflect.b;
import kotlin.reflect.l;
import kotlin.reflect.l.a;

public abstract class PropertyReference1 extends PropertyReference implements l {
    public PropertyReference1() {
    }

    public PropertyReference1(Object obj) {
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
        return ((l) getReflected()).getGetter();
    }

    public Object getDelegate(Object obj) {
        return ((l) getReflected()).getDelegate(obj);
    }
}
