package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class ak implements OnTouchListener {
    private final /* synthetic */ zzbp a;

    ak(zzbp zzbp) {
        this.a = zzbp;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.a.zzaay != null) {
            this.a.zzaay.a(motionEvent);
        }
        return false;
    }
}
