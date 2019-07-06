package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.View.OnAttachStateChangeListener;

final class nr implements OnAttachStateChangeListener {
    private final /* synthetic */ fl a;
    private final /* synthetic */ zzaqx b;

    nr(zzaqx zzaqx, fl flVar) {
        this.b = zzaqx;
        this.a = flVar;
    }

    public final void onViewAttachedToWindow(View view) {
        this.b.zza(view, this.a, 10);
    }

    public final void onViewDetachedFromWindow(View view) {
    }
}
