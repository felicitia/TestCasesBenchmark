package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

@bu
public final class mg {
    private final Context a;
    private final mo b;
    private final ViewGroup c;
    private zzapi d;

    @VisibleForTesting
    private mg(Context context, ViewGroup viewGroup, mo moVar, zzapi zzapi) {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        this.a = context;
        this.c = viewGroup;
        this.b = moVar;
        this.d = null;
    }

    public mg(Context context, ViewGroup viewGroup, nn nnVar) {
        this(context, viewGroup, nnVar, null);
    }

    public final zzapi a() {
        Preconditions.checkMainThread("getAdVideoUnderlay must be called from the UI thread.");
        return this.d;
    }

    public final void a(int i, int i2, int i3, int i4) {
        Preconditions.checkMainThread("The underlay may only be modified from the UI thread.");
        if (this.d != null) {
            this.d.zzd(i, i2, i3, i4);
        }
    }

    public final void a(int i, int i2, int i3, int i4, int i5, boolean z, mn mnVar) {
        if (this.d == null) {
            akr.a(this.b.zztp().a(), this.b.zztn(), "vpr2");
            zzapi zzapi = new zzapi(this.a, this.b, i5, z, this.b.zztp().a(), mnVar);
            this.d = zzapi;
            this.c.addView(this.d, 0, new LayoutParams(-1, -1));
            this.d.zzd(i, i2, i3, i4);
            this.b.zzah(false);
        }
    }

    public final void b() {
        Preconditions.checkMainThread("onPause must be called from the UI thread.");
        if (this.d != null) {
            this.d.pause();
        }
    }

    public final void c() {
        Preconditions.checkMainThread("onDestroy must be called from the UI thread.");
        if (this.d != null) {
            this.d.destroy();
            this.c.removeView(this.d);
            this.d = null;
        }
    }
}
