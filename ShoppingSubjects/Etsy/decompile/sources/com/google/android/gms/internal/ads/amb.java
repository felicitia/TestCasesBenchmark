package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.a.b;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@bu
public final class amb extends b {
    private final zzpw a;
    private final Drawable b;
    private final Uri c;
    private final double d;

    public amb(zzpw zzpw) {
        Drawable drawable;
        this.a = zzpw;
        Uri uri = null;
        try {
            IObjectWrapper zzjy = this.a.zzjy();
            if (zzjy != null) {
                drawable = (Drawable) ObjectWrapper.unwrap(zzjy);
                this.b = drawable;
                uri = this.a.getUri();
                this.c = uri;
                double d2 = 1.0d;
                d2 = this.a.getScale();
                this.d = d2;
            }
        } catch (RemoteException e) {
            ka.b("", e);
        }
        drawable = null;
        this.b = drawable;
        try {
            uri = this.a.getUri();
        } catch (RemoteException e2) {
            ka.b("", e2);
        }
        this.c = uri;
        double d22 = 1.0d;
        try {
            d22 = this.a.getScale();
        } catch (RemoteException e3) {
            ka.b("", e3);
        }
        this.d = d22;
    }

    public final Drawable a() {
        return this.b;
    }

    public final Uri b() {
        return this.c;
    }

    public final double c() {
        return this.d;
    }
}
