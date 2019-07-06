package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

@TargetApi(19)
public class hs extends hq {
    public final boolean a(View view) {
        return view.isAttachedToWindow();
    }

    public final LayoutParams d() {
        return new LayoutParams(-1, -1);
    }
}
