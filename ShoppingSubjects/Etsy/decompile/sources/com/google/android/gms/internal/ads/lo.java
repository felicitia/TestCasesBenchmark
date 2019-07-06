package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import java.lang.ref.WeakReference;

@bu
final class lo extends lp implements OnScrollChangedListener {
    private final WeakReference<OnScrollChangedListener> a;

    public lo(View view, OnScrollChangedListener onScrollChangedListener) {
        super(view);
        this.a = new WeakReference<>(onScrollChangedListener);
    }

    /* access modifiers changed from: protected */
    public final void a(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnScrollChangedListener(this);
    }

    /* access modifiers changed from: protected */
    public final void b(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.removeOnScrollChangedListener(this);
    }

    public final void onScrollChanged() {
        OnScrollChangedListener onScrollChangedListener = (OnScrollChangedListener) this.a.get();
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollChanged();
        } else {
            b();
        }
    }
}
