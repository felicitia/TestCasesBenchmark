package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.a;
import kotlin.jvm.a.b;
import kotlin.jvm.a.c;
import kotlin.jvm.a.d;
import kotlin.jvm.a.e;
import kotlin.jvm.a.f;
import kotlin.jvm.a.g;
import kotlin.jvm.a.h;
import kotlin.jvm.a.i;
import kotlin.jvm.a.j;
import kotlin.jvm.a.k;
import kotlin.jvm.a.l;
import kotlin.jvm.a.m;
import kotlin.jvm.a.n;
import kotlin.jvm.a.o;
import kotlin.jvm.a.p;
import kotlin.jvm.a.q;
import kotlin.jvm.a.r;
import kotlin.jvm.a.s;
import kotlin.jvm.a.t;
import kotlin.jvm.a.u;
import kotlin.jvm.a.v;
import kotlin.jvm.a.w;

@Deprecated
public abstract class FunctionImpl implements Serializable, a, kotlin.jvm.a.a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w {
    public abstract int getArity();

    public Object invokeVararg(Object... objArr) {
        throw new UnsupportedOperationException();
    }

    private void a(int i) {
        if (getArity() != i) {
            b(i);
        }
    }

    private void b(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("Wrong function arity, expected: ");
        sb.append(i);
        sb.append(", actual: ");
        sb.append(getArity());
        throw new IllegalStateException(sb.toString());
    }

    public Object invoke() {
        a(0);
        return invokeVararg(new Object[0]);
    }

    public Object invoke(Object obj) {
        a(1);
        return invokeVararg(obj);
    }

    public Object invoke(Object obj, Object obj2) {
        a(2);
        return invokeVararg(obj, obj2);
    }

    public Object invoke(Object obj, Object obj2, Object obj3) {
        a(3);
        return invokeVararg(obj, obj2, obj3);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        a(4);
        return invokeVararg(obj, obj2, obj3, obj4);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        a(5);
        return invokeVararg(obj, obj2, obj3, obj4, obj5);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        a(6);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        a(7);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8) {
        a(8);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        a(9);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10) {
        a(10);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11) {
        a(11);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12) {
        a(12);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13) {
        a(13);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14) {
        a(14);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15) {
        a(15);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16) {
        a(16);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17) {
        a(17);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18) {
        a(18);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19) {
        a(19);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20) {
        a(20);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20, Object obj21) {
        a(21);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21);
    }

    public Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object obj13, Object obj14, Object obj15, Object obj16, Object obj17, Object obj18, Object obj19, Object obj20, Object obj21, Object obj22) {
        a(22);
        return invokeVararg(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9, obj10, obj11, obj12, obj13, obj14, obj15, obj16, obj17, obj18, obj19, obj20, obj21, obj22);
    }
}
