package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class ay implements OnTouchListener {
    private final /* synthetic */ bh a;
    private final /* synthetic */ aw b;

    ay(aw awVar, bh bhVar) {
        this.b = awVar;
        this.a = bhVar;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        this.a.a();
        if (this.b.b != null) {
            this.b.b.c();
        }
        return false;
    }
}
