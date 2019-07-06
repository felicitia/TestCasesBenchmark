package com.google.android.gms.ads;

import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.etsy.android.ui.dialog.EtsyDialogFragment;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.jp;
import com.google.android.gms.internal.ads.zzjn;

@VisibleForTesting
public final class d {
    public static final d a = new d(320, 50, "320x50_mb");
    public static final d b = new d(468, 60, "468x60_as");
    public static final d c = new d(320, 100, "320x100_as");
    public static final d d = new d(728, 90, "728x90_as");
    public static final d e = new d(300, Callback.DEFAULT_SWIPE_ANIMATION_DURATION, "300x250_as");
    public static final d f = new d(160, 600, "160x600_as");
    public static final d g = new d(-1, -2, "smart_banner");
    public static final d h = new d(-3, -4, "fluid");
    public static final d i = new d(50, 50, "50x50_mb");
    public static final d j = new d(-3, 0, "search_v2");
    private final int k;
    private final int l;
    private final String m;

    public d(int i2, int i3) {
        String valueOf = i2 == -1 ? "FULL" : String.valueOf(i2);
        String valueOf2 = i3 == -2 ? "AUTO" : String.valueOf(i3);
        StringBuilder sb = new StringBuilder(4 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append(EtsyDialogFragment.OPT_X_BUTTON);
        sb.append(valueOf2);
        sb.append("_as");
        this(i2, i3, sb.toString());
    }

    d(int i2, int i3, String str) {
        if (i2 < 0 && i2 != -1 && i2 != -3) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Invalid width for AdSize: ");
            sb.append(i2);
            throw new IllegalArgumentException(sb.toString());
        } else if (i3 >= 0 || i3 == -2 || i3 == -4) {
            this.k = i2;
            this.l = i3;
            this.m = str;
        } else {
            StringBuilder sb2 = new StringBuilder(38);
            sb2.append("Invalid height for AdSize: ");
            sb2.append(i3);
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public final int a() {
        return this.l;
    }

    public final int a(Context context) {
        switch (this.l) {
            case ProfilePictureView.LARGE /*-4*/:
            case -3:
                return -1;
            case -2:
                return zzjn.zzc(context.getResources().getDisplayMetrics());
            default:
                ajh.a();
                return jp.a(context, this.l);
        }
    }

    public final int b() {
        return this.k;
    }

    public final int b(Context context) {
        int i2 = this.k;
        if (i2 == -1) {
            return zzjn.zzb(context.getResources().getDisplayMetrics());
        }
        switch (i2) {
            case ProfilePictureView.LARGE /*-4*/:
            case -3:
                return -1;
            default:
                ajh.a();
                return jp.a(context, this.k);
        }
    }

    public final boolean c() {
        return this.k == -3 && this.l == -4;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.k == dVar.k && this.l == dVar.l && this.m.equals(dVar.m);
    }

    public final int hashCode() {
        return this.m.hashCode();
    }

    public final String toString() {
        return this.m;
    }
}
