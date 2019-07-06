package com.jakewharton.rxbinding2.internal;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import io.reactivex.functions.i;
import java.util.concurrent.Callable;

@RestrictTo({Scope.LIBRARY_GROUP})
/* compiled from: Functions */
public final class a {
    public static final Callable<Boolean> a = c;
    public static final i<Object> b = c;
    private static final C0155a c = new C0155a(Boolean.valueOf(true));

    /* renamed from: com.jakewharton.rxbinding2.internal.a$a reason: collision with other inner class name */
    /* compiled from: Functions */
    private static final class C0155a implements i<Object>, Callable<Boolean> {
        private final Boolean a;

        C0155a(Boolean bool) {
            this.a = bool;
        }

        /* renamed from: a */
        public Boolean call() {
            return this.a;
        }

        public boolean test(Object obj) throws Exception {
            return this.a.booleanValue();
        }
    }
}
