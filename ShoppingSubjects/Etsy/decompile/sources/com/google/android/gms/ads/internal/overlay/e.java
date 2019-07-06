package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.nn;

@bu
@VisibleForTesting
public final class e {
    public final int a;
    public final LayoutParams b;
    public final ViewGroup c;
    public final Context d;

    public e(nn nnVar) throws zzg {
        this.b = nnVar.getLayoutParams();
        ViewParent parent = nnVar.getParent();
        this.d = nnVar.zzua();
        if (parent == null || !(parent instanceof ViewGroup)) {
            throw new zzg("Could not get the parent of the WebView for an overlay.");
        }
        this.c = (ViewGroup) parent;
        this.a = this.c.indexOfChild(nnVar.getView());
        this.c.removeView(nnVar.getView());
        nnVar.zzai(true);
    }
}
