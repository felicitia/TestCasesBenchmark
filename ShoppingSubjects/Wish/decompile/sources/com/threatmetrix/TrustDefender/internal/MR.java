package com.threatmetrix.TrustDefender.internal;

import android.os.HandlerThread;
import android.os.Looper;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

final class MR implements O {

    /* renamed from: byte reason: not valid java name */
    private final int f338byte = 0;

    /* renamed from: case reason: not valid java name */
    final AtomicInteger f339case = new AtomicInteger(0);

    /* renamed from: char reason: not valid java name */
    private final int f340char = 1;

    /* renamed from: do reason: not valid java name */
    L f341do;

    /* renamed from: else reason: not valid java name */
    private final int f342else = 2;

    /* renamed from: for reason: not valid java name */
    U6 f343for;

    /* renamed from: goto reason: not valid java name */
    private final int f344goto = 4;

    /* renamed from: if reason: not valid java name */
    HandlerThread f345if = null;

    /* renamed from: int reason: not valid java name */
    volatile boolean f346int = false;

    /* renamed from: long reason: not valid java name */
    private final int f347long = 3;

    /* renamed from: new reason: not valid java name */
    XZ f348new;

    /* renamed from: try reason: not valid java name */
    private final int f349try = -1;

    class I implements InvocationHandler {

        /* renamed from: new reason: not valid java name */
        final boolean f351new;

        I(boolean z) {
            this.f351new = z;
        }

        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (!"onResult".equals(method.getName())) {
                return J.m92if(this, method, objArr);
            }
            if (objArr != null && objArr.length > 0) {
                if (J.m90for(objArr[0])) {
                    MR mr = MR.this;
                    if (!this.f351new) {
                        mr.f339case.compareAndSet(4, 1);
                    } else if (mr.f339case.compareAndSet(2, 3)) {
                        mr.f348new.m438for();
                    }
                    mr.m143int();
                } else {
                    MR mr2 = MR.this;
                    if (this.f351new) {
                        mr2.f339case.compareAndSet(2, 1);
                    } else {
                        mr2.f339case.compareAndSet(4, 3);
                    }
                }
            }
            return null;
        }
    }

    class W implements InvocationHandler {
        private W() {
        }

        /* synthetic */ W(MR mr, byte b) {
            this();
        }

        public final Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            if (!"onLocationChanged".equals(method.getName())) {
                return J.m92if(this, method, objArr);
            }
            if (objArr != null && objArr.length > 0) {
                MR.this.f343for.onLocationChanged(objArr[0]);
            }
            return null;
        }
    }

    MR() {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: int reason: not valid java name */
    public final void m143int() {
        if (this.f346int) {
            if (this.f339case.compareAndSet(3, 4)) {
                L l = this.f341do;
                I i = new I(false);
                W w = J.f184do;
                if (w != null) {
                    Object obj = D2.m39do(D2.m40do(w.f208final), w.f202continue, l.f192new, l.f189for);
                    if (obj != null) {
                        Object obj2 = D2.m47new(w.f218long.getClassLoader(), new Class[]{w.f218long}, i);
                        D2.m39do(obj, w.f193abstract, obj2);
                    }
                }
            }
            return;
        }
        if (this.f339case.compareAndSet(1, 2)) {
            L l2 = this.f341do;
            I i2 = new I(true);
            Looper looper = this.f345if.getLooper();
            l2.m98new(l2.f190if, l2.f189for, i2, looper);
            l2.m98new(l2.f191int, l2.f188do, i2, looper);
        }
    }

    /* renamed from: new reason: not valid java name */
    public final void m144new() {
        if (this.f339case.compareAndSet(0, 1)) {
            m143int();
        }
    }

    /* renamed from: if reason: not valid java name */
    public final void m142if() {
        this.f339case.set(-1);
    }
}
