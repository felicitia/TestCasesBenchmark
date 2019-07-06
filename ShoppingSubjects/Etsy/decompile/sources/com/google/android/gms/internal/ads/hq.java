package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.view.View;

@TargetApi(18)
public class hq extends hp {
    public boolean a(View view) {
        return super.a(view) || view.getWindowId() != null;
    }

    public final int c() {
        return 14;
    }
}
