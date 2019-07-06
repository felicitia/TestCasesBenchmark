package com.etsy.android.checkout;

import com.etsy.android.checkout.a.C0042a;
import kotlin.c;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.s;
import kotlin.reflect.j;

/* compiled from: CheckoutStateMachine.kt */
public final class b {
    public static final a a = new a(null);
    private static final kotlin.b c = c.a(CheckoutStateMachine$Companion$instance$2.INSTANCE);
    private a b;

    /* compiled from: CheckoutStateMachine.kt */
    public static final class a {
        static final /* synthetic */ j[] a = {s.a((PropertyReference1) new PropertyReference1Impl(s.a(a.class), "instance", "getInstance()Lcom/etsy/android/checkout/CheckoutStateMachine;"))};

        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    /* renamed from: com.etsy.android.checkout.b$b reason: collision with other inner class name */
    /* compiled from: CheckoutStateMachine.kt */
    private static final class C0043b {
        public static final C0043b a = new C0043b();
        private static final b b = new b(null);

        private C0043b() {
        }

        public final b a() {
            return b;
        }
    }

    private b() {
        this.b = new C0042a();
    }

    public /* synthetic */ b(o oVar) {
        this();
    }
}
