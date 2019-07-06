package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;

final class alz implements ali {
    private final /* synthetic */ View a;
    private final /* synthetic */ zzpp b;

    alz(zzpp zzpp, View view) {
        this.b = zzpp;
        this.a = view;
    }

    public final void a() {
        if (this.b.zza(zzpp.zzbjs)) {
            this.b.onClick(this.a);
        }
    }

    public final void a(MotionEvent motionEvent) {
        this.b.onTouch(null, motionEvent);
    }
}
