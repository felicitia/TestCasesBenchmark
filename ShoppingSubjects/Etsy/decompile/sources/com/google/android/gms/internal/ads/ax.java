package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import java.lang.ref.WeakReference;

final class ax implements OnGlobalLayoutListener {
    private final /* synthetic */ WeakReference a;
    private final /* synthetic */ ar b;

    ax(ar arVar, WeakReference weakReference) {
        this.b = arVar;
        this.a = weakReference;
    }

    public final void onGlobalLayout() {
        this.b.a(this.a, false);
    }
}
