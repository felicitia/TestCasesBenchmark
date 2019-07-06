package com.jakewharton.rxbinding2.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import io.reactivex.Observer;

/* compiled from: TextViewAfterTextChangeEventObservable */
final class o extends com.jakewharton.rxbinding2.a<n> {
    private final TextView a;

    /* compiled from: TextViewAfterTextChangeEventObservable */
    static final class a extends io.reactivex.a.a implements TextWatcher {
        private final TextView a;
        private final Observer<? super n> b;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        a(TextView textView, Observer<? super n> observer) {
            this.a = textView;
            this.b = observer;
        }

        public void afterTextChanged(Editable editable) {
            this.b.onNext(n.create(this.a, editable));
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.a.removeTextChangedListener(this);
        }
    }

    o(TextView textView) {
        this.a = textView;
    }

    /* access modifiers changed from: protected */
    public void b(Observer<? super n> observer) {
        a aVar = new a(this.a, observer);
        observer.onSubscribe(aVar);
        this.a.addTextChangedListener(aVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public n a() {
        return n.create(this.a, this.a.getEditableText());
    }
}
