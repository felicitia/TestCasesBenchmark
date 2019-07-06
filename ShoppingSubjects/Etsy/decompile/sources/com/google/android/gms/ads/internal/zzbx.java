package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.ViewSwitcher;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hx;
import com.google.android.gms.internal.ads.jo;
import com.google.android.gms.internal.ads.nn;
import java.util.ArrayList;

public final class zzbx extends ViewSwitcher {
    private final hx zzaed;
    @Nullable
    private final jo zzaee;
    private boolean zzaef = true;

    public zzbx(Context context, String str, String str2, OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        super(context);
        this.zzaed = new hx(context);
        this.zzaed.a(str);
        this.zzaed.b(str2);
        if (context instanceof Activity) {
            this.zzaee = new jo((Activity) context, this, onGlobalLayoutListener, onScrollChangedListener);
        } else {
            this.zzaee = new jo(null, this, onGlobalLayoutListener, onScrollChangedListener);
        }
        this.zzaee.a();
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.zzaee != null) {
            this.zzaee.c();
        }
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.zzaee != null) {
            this.zzaee.d();
        }
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.zzaef) {
            this.zzaed.a(motionEvent);
        }
        return false;
    }

    public final void removeAllViews() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && (childAt instanceof nn)) {
                arrayList.add((nn) childAt);
            }
        }
        super.removeAllViews();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            ((nn) obj).destroy();
        }
    }

    public final hx zzfr() {
        return this.zzaed;
    }

    public final void zzfs() {
        gv.a("Disable position monitoring on adFrame.");
        if (this.zzaee != null) {
            this.zzaee.b();
        }
    }

    public final void zzft() {
        gv.a("Enable debug gesture detector on adFrame.");
        this.zzaef = true;
    }

    public final void zzfu() {
        gv.a("Disable debug gesture detector on adFrame.");
        this.zzaef = false;
    }
}
