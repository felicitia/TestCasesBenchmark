package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.hx;

@bu
@VisibleForTesting
final class d extends RelativeLayout {
    @VisibleForTesting
    boolean a;
    @VisibleForTesting
    private hx b;

    public d(Context context, String str, String str2) {
        super(context);
        this.b = new hx(context, str);
        this.b.b(str2);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.a) {
            this.b.a(motionEvent);
        }
        return false;
    }
}
