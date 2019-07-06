package com.google.android.gms.internal.ads;

import android.view.ViewTreeObserver.OnScrollChangedListener;
import java.lang.ref.WeakReference;

final class ay implements OnScrollChangedListener {
    private final /* synthetic */ WeakReference a;
    private final /* synthetic */ ar b;

    ay(ar arVar, WeakReference weakReference) {
        this.b = arVar;
        this.a = weakReference;
    }

    public final void onScrollChanged() {
        this.b.a(this.a, true);
    }
}
