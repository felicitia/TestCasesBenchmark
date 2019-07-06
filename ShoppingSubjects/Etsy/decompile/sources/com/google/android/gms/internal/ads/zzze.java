package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.a.b;
import com.google.android.gms.ads.mediation.l;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@bu
public final class zzze extends zzyg {
    private final l zzbvh;

    public zzze(l lVar) {
        this.zzbvh = lVar;
    }

    public final String getAdvertiser() {
        return this.zzbvh.f();
    }

    public final String getBody() {
        return this.zzbvh.c();
    }

    public final String getCallToAction() {
        return this.zzbvh.e();
    }

    public final Bundle getExtras() {
        return this.zzbvh.o();
    }

    public final String getHeadline() {
        return this.zzbvh.a();
    }

    public final List getImages() {
        List<b> b = this.zzbvh.b();
        ArrayList arrayList = new ArrayList();
        if (b != null) {
            for (b bVar : b) {
                arrayList.add(new zzon(bVar.a(), bVar.b(), bVar.c()));
            }
        }
        return arrayList;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzbvh.q();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzbvh.p();
    }

    public final String getPrice() {
        return this.zzbvh.i();
    }

    public final double getStarRating() {
        if (this.zzbvh.g() != null) {
            return this.zzbvh.g().doubleValue();
        }
        return -1.0d;
    }

    public final String getStore() {
        return this.zzbvh.h();
    }

    public final zzlo getVideoController() {
        if (this.zzbvh.j() != null) {
            return this.zzbvh.j().a();
        }
        return null;
    }

    public final void recordImpression() {
        this.zzbvh.r();
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzbvh.a((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzj(IObjectWrapper iObjectWrapper) {
        this.zzbvh.a((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final zzpw zzjz() {
        b d = this.zzbvh.d();
        if (d != null) {
            return new zzon(d.a(), d.b(), d.c());
        }
        return null;
    }

    public final IObjectWrapper zzke() {
        Object n = this.zzbvh.n();
        if (n == null) {
            return null;
        }
        return ObjectWrapper.wrap(n);
    }

    public final zzps zzkf() {
        return null;
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzbvh.b((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzmv() {
        View l = this.zzbvh.l();
        if (l == null) {
            return null;
        }
        return ObjectWrapper.wrap(l);
    }

    public final IObjectWrapper zzmw() {
        View m = this.zzbvh.m();
        if (m == null) {
            return null;
        }
        return ObjectWrapper.wrap(m);
    }
}
