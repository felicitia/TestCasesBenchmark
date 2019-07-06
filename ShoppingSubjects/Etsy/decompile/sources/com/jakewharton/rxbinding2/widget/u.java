package com.jakewharton.rxbinding2.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import io.reactivex.Observer;

/* compiled from: TextViewTextObservable */
final class u extends com.jakewharton.rxbinding2.a<CharSequence> {
    private final TextView a;

    /* compiled from: TextViewTextObservable */
    static final class a extends io.reactivex.a.a implements TextWatcher {
        private final TextView a;
        private final Observer<? super CharSequence> b;

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        a(TextView textView, Observer<? super CharSequence> observer) {
            this.a = textView;
            this.b = observer;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!isDisposed()) {
                this.b.onNext(charSequence);
            }
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.a.removeTextChangedListener(this);
        }
    }

    u(TextView textView) {
        this.a = textView;
    }

    /* access modifiers changed from: protected */
    public void b(Observer<? super CharSequence> observer) {
        a aVar = new a(this.a, observer);
        observer.onSubscribe(aVar);
        this.a.addTextChangedListener(aVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public CharSequence a() {
        return this.a.getText();
    }
}
