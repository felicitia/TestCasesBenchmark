package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.ao;
import java.lang.ref.WeakReference;

@bu
final class ln extends lp implements OnGlobalLayoutListener {
    private final WeakReference<OnGlobalLayoutListener> a;

    public ln(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        super(view);
        this.a = new WeakReference<>(onGlobalLayoutListener);
    }

    /* access modifiers changed from: protected */
    public final void a(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnGlobalLayoutListener(this);
    }

    /* access modifiers changed from: protected */
    public final void b(ViewTreeObserver viewTreeObserver) {
        ao.g().a(viewTreeObserver, (OnGlobalLayoutListener) this);
    }

    public final void onGlobalLayout() {
        OnGlobalLayoutListener onGlobalLayoutListener = (OnGlobalLayoutListener) this.a.get();
        if (onGlobalLayoutListener != null) {
            onGlobalLayoutListener.onGlobalLayout();
        } else {
            b();
        }
    }
}
