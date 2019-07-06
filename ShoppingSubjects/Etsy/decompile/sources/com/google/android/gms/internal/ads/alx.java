package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;

final class alx implements ali {
    private final /* synthetic */ View a;
    private final /* synthetic */ zzpn b;

    alx(zzpn zzpn, View view) {
        this.b = zzpn;
        this.a = view;
    }

    public final void a() {
        this.b.onClick(this.a);
    }

    public final void a(MotionEvent motionEvent) {
        this.b.onTouch(null, motionEvent);
    }
}
