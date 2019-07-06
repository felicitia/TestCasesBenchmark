package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;

final class ox implements OnAttachStateChangeListener {
    private final /* synthetic */ fl a;
    private final /* synthetic */ ou b;

    ox(ou ouVar, fl flVar) {
        this.b = ouVar;
        this.a = flVar;
    }

    public final void onViewAttachedToWindow(View view) {
        this.b.a(view, this.a, 10);
    }

    public final void onViewDetachedFromWindow(View view) {
    }
}
