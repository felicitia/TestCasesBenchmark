package com.google.android.gms.ads.formats;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.ads.ka;
import com.google.android.gms.internal.ads.zzqf;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public final class c {
    public static WeakHashMap<View, c> a = new WeakHashMap<>();
    private zzqf b;
    private WeakReference<View> c;

    private final void a(IObjectWrapper iObjectWrapper) {
        View view = this.c != null ? (View) this.c.get() : null;
        if (view == null) {
            ka.e("NativeAdViewHolder.setNativeAd containerView doesn't exist, returning");
            return;
        }
        if (!a.containsKey(view)) {
            a.put(view, this);
        }
        if (this.b != null) {
            try {
                this.b.zza(iObjectWrapper);
            } catch (RemoteException e) {
                ka.b("Unable to call setNativeAd on delegate", e);
            }
        }
    }

    public final void a(a aVar) {
        a((IObjectWrapper) aVar.a());
    }

    public final void a(h hVar) {
        a((IObjectWrapper) hVar.k());
    }
}
