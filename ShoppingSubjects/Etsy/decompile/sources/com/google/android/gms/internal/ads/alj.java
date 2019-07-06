package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@bu
public final class alj extends alp {
    @Nullable
    private zzxz c;
    @Nullable
    private zzyc d;
    @Nullable
    private zzyf e;
    private final alm f;
    @Nullable
    private alk g;
    private boolean h;
    private Object i;

    private alj(Context context, alm alm, ack ack, aln aln) {
        super(context, alm, null, ack, null, aln, null, null);
        this.h = false;
        this.i = new Object();
        this.f = alm;
    }

    public alj(Context context, alm alm, ack ack, zzxz zzxz, aln aln) {
        this(context, alm, ack, aln);
        this.c = zzxz;
    }

    public alj(Context context, alm alm, ack ack, zzyc zzyc, aln aln) {
        this(context, alm, ack, aln);
        this.d = zzyc;
    }

    public alj(Context context, alm alm, ack ack, zzyf zzyf, aln aln) {
        this(context, alm, ack, aln);
        this.e = zzyf;
    }

    private static HashMap<String, View> b(Map<String, WeakReference<View>> map) {
        HashMap<String, View> hashMap = new HashMap<>();
        if (map == null) {
            return hashMap;
        }
        synchronized (map) {
            for (Entry entry : map.entrySet()) {
                View view = (View) ((WeakReference) entry.getValue()).get();
                if (view != null) {
                    hashMap.put((String) entry.getKey(), view);
                }
            }
        }
        return hashMap;
    }

    @Nullable
    public final View a(OnClickListener onClickListener, boolean z) {
        IObjectWrapper iObjectWrapper;
        synchronized (this.i) {
            if (this.g != null) {
                View a = this.g.a(onClickListener, z);
                return a;
            }
            try {
                if (this.e != null) {
                    iObjectWrapper = this.e.zzmv();
                } else if (this.c != null) {
                    iObjectWrapper = this.c.zzmv();
                } else {
                    if (this.d != null) {
                        iObjectWrapper = this.d.zzmv();
                    }
                    iObjectWrapper = null;
                }
            } catch (RemoteException e2) {
                gv.c("Failed to call getAdChoicesContent", e2);
            }
            if (iObjectWrapper == null) {
                return null;
            }
            View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
            return view;
        }
    }

    public final void a(View view) {
        synchronized (this.i) {
            if (this.g != null) {
                this.g.a(view);
            }
        }
    }

    public final void a(View view, Map<String, WeakReference<View>> map) {
        alm alm;
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        synchronized (this.i) {
            this.a = true;
            if (this.g != null) {
                this.g.a(view, map);
                this.f.recordImpression();
            } else {
                try {
                    if (this.e != null && !this.e.getOverrideImpressionRecording()) {
                        this.e.recordImpression();
                        alm = this.f;
                    } else if (this.c != null && !this.c.getOverrideImpressionRecording()) {
                        this.c.recordImpression();
                        alm = this.f;
                    } else if (this.d != null && !this.d.getOverrideImpressionRecording()) {
                        this.d.recordImpression();
                        alm = this.f;
                    }
                    alm.recordImpression();
                } catch (RemoteException e2) {
                    gv.c("Failed to call recordImpression", e2);
                }
            }
        }
    }

    public final void a(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        alm alm;
        Preconditions.checkMainThread("performClick must be called on the main UI thread.");
        synchronized (this.i) {
            if (this.g != null) {
                this.g.a(view, map, bundle, view2);
                this.f.onAdClicked();
            } else {
                try {
                    if (this.e != null && !this.e.getOverrideClickHandling()) {
                        this.e.zzj(ObjectWrapper.wrap(view));
                        alm = this.f;
                    } else if (this.c != null && !this.c.getOverrideClickHandling()) {
                        this.c.zzj(ObjectWrapper.wrap(view));
                        alm = this.f;
                    } else if (this.d != null && !this.d.getOverrideClickHandling()) {
                        this.d.zzj(ObjectWrapper.wrap(view));
                        alm = this.f;
                    }
                    alm.onAdClicked();
                } catch (RemoteException e2) {
                    gv.c("Failed to call performClick", e2);
                }
            }
        }
    }

    public final void a(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        synchronized (this.i) {
            this.h = true;
            HashMap b = b(map);
            HashMap b2 = b(map2);
            try {
                if (this.e != null) {
                    this.e.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(b), ObjectWrapper.wrap(b2));
                } else if (this.c != null) {
                    this.c.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(b), ObjectWrapper.wrap(b2));
                    this.c.zzk(ObjectWrapper.wrap(view));
                } else if (this.d != null) {
                    this.d.zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(b), ObjectWrapper.wrap(b2));
                    this.d.zzk(ObjectWrapper.wrap(view));
                }
            } catch (RemoteException e2) {
                gv.c("Failed to call prepareAd", e2);
            }
            this.h = false;
        }
    }

    public final void a(@Nullable alk alk) {
        synchronized (this.i) {
            this.g = alk;
        }
    }

    public final void a(zzro zzro) {
        synchronized (this.i) {
            if (this.g != null) {
                this.g.a(zzro);
            }
        }
    }

    public final boolean a() {
        synchronized (this.i) {
            if (this.g != null) {
                boolean a = this.g.a();
                return a;
            }
            boolean zzcu = this.f.zzcu();
            return zzcu;
        }
    }

    public final void b(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.i) {
            try {
                if (this.e != null) {
                    this.e.zzl(ObjectWrapper.wrap(view));
                } else if (this.c != null) {
                    this.c.zzl(ObjectWrapper.wrap(view));
                } else if (this.d != null) {
                    this.d.zzl(ObjectWrapper.wrap(view));
                }
            } catch (RemoteException e2) {
                gv.c("Failed to call untrackView", e2);
            }
        }
    }

    public final boolean b() {
        synchronized (this.i) {
            if (this.g != null) {
                boolean b = this.g.b();
                return b;
            }
            boolean zzcv = this.f.zzcv();
            return zzcv;
        }
    }

    public final void c() {
        synchronized (this.i) {
            if (this.g != null) {
                this.g.c();
            }
        }
    }

    public final void d() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on main UI thread.");
        synchronized (this.i) {
            this.b = true;
            if (this.g != null) {
                this.g.d();
            }
        }
    }

    public final boolean e() {
        boolean z;
        synchronized (this.i) {
            z = this.h;
        }
        return z;
    }

    public final alk f() {
        alk alk;
        synchronized (this.i) {
            alk = this.g;
        }
        return alk;
    }

    @Nullable
    public final nn g() {
        return null;
    }

    public final void h() {
    }

    public final void i() {
    }

    public final void j() {
        if (this.g != null) {
            this.g.j();
        }
    }

    public final void k() {
        if (this.g != null) {
            this.g.k();
        }
    }
}
