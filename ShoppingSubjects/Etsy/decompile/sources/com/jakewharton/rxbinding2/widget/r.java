package com.jakewharton.rxbinding2.widget;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.jakewharton.rxbinding2.internal.b;
import io.reactivex.Observer;
import io.reactivex.functions.i;
import io.reactivex.q;

/* compiled from: TextViewEditorActionEventObservable */
final class r extends q<q> {
    private final TextView a;
    private final i<? super q> b;

    /* compiled from: TextViewEditorActionEventObservable */
    static final class a extends io.reactivex.a.a implements OnEditorActionListener {
        private final TextView a;
        private final Observer<? super q> b;
        private final i<? super q> c;

        a(TextView textView, Observer<? super q> observer, i<? super q> iVar) {
            this.a = textView;
            this.b = observer;
            this.c = iVar;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            q create = q.create(this.a, i, keyEvent);
            try {
                if (!isDisposed() && this.c.test(create)) {
                    this.b.onNext(create);
                    return true;
                }
            } catch (Exception e) {
                this.b.onError(e);
                dispose();
            }
            return false;
        }

        /* access modifiers changed from: protected */
        public void a() {
            this.a.setOnEditorActionListener(null);
        }
    }

    r(TextView textView, i<? super q> iVar) {
        this.a = textView;
        this.b = iVar;
    }

    /* access modifiers changed from: protected */
    public void a(Observer<? super q> observer) {
        if (b.a(observer)) {
            a aVar = new a(this.a, observer, this.b);
            observer.onSubscribe(aVar);
            this.a.setOnEditorActionListener(aVar);
        }
    }
}
