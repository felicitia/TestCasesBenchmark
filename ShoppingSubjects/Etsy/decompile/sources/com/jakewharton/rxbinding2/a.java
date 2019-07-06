package com.jakewharton.rxbinding2;

import io.reactivex.Observer;
import io.reactivex.q;

/* compiled from: InitialValueObservable */
public abstract class a<T> extends q<T> {

    /* renamed from: com.jakewharton.rxbinding2.a$a reason: collision with other inner class name */
    /* compiled from: InitialValueObservable */
    private final class C0154a extends q<T> {
        C0154a() {
        }

        /* access modifiers changed from: protected */
        public void a(Observer<? super T> observer) {
            a.this.b(observer);
        }
    }

    /* access modifiers changed from: protected */
    public abstract T a();

    /* access modifiers changed from: protected */
    public abstract void b(Observer<? super T> observer);

    /* access modifiers changed from: protected */
    public final void a(Observer<? super T> observer) {
        b(observer);
        observer.onNext(a());
    }

    public final q<T> b() {
        return new C0154a();
    }
}
