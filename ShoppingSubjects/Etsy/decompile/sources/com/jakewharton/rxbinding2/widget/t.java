package com.jakewharton.rxbinding2.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import io.reactivex.Observer;

/* compiled from: TextViewTextChangeEventObservable */
final class t extends com.jakewharton.rxbinding2.a<s> {
    private final TextView a;

    /* compiled from: TextViewTextChangeEventObservable */
    static final class a extends io.reactivex.a.a implements TextWatcher {
        private final TextView a;
        private final Observer<? super s> b;

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        a(TextView textView, Observer<? super s> observer) {
            this.a = textView;
            this.b = observer;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!isDisposed()) {
                this.b.onNext(s.create(this.a, charSequence, i, i2, i3));
            }
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.a.removeTextChangedListener(this);
        }
    }

    t(TextView textView) {
        this.a = textView;
    }

    /* access modifiers changed from: protected */
    public void b(Observer<? super s> observer) {
        a aVar = new a(this.a, observer);
        observer.onSubscribe(aVar);
        this.a.addTextChangedListener(aVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public s a() {
        return s.create(this.a, this.a.getText(), 0, 0, 0);
    }
}
