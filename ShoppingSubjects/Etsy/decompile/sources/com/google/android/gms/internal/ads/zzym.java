package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.a.b;
import com.google.android.gms.ads.mediation.g;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@bu
public final class zzym extends zzya {
    private final g zzbuy;

    public zzym(g gVar) {
        this.zzbuy = gVar;
    }

    public final String getBody() {
        return this.zzbuy.k();
    }

    public final String getCallToAction() {
        return this.zzbuy.m();
    }

    public final Bundle getExtras() {
        return this.zzbuy.c();
    }

    public final String getHeadline() {
        return this.zzbuy.i();
    }

    public final List getImages() {
        List<b> j = this.zzbuy.j();
        if (j == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (b bVar : j) {
            arrayList.add(new zzon(bVar.a(), bVar.b(), bVar.c()));
        }
        return arrayList;
    }

    public final boolean getOverrideClickHandling() {
        return this.zzbuy.b();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzbuy.a();
    }

    public final String getPrice() {
        return this.zzbuy.p();
    }

    public final double getStarRating() {
        return this.zzbuy.n();
    }

    public final String getStore() {
        return this.zzbuy.o();
    }

    public final zzlo getVideoController() {
        if (this.zzbuy.g() != null) {
            return this.zzbuy.g().a();
        }
        return null;
    }

    public final void recordImpression() {
        this.zzbuy.e();
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzbuy.a((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzj(IObjectWrapper iObjectWrapper) {
        this.zzbuy.c((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final zzpw zzjz() {
        b l = this.zzbuy.l();
        if (l != null) {
            return new zzon(l.a(), l.b(), l.c());
        }
        return null;
    }

    public final void zzk(IObjectWrapper iObjectWrapper) {
        this.zzbuy.a((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzke() {
        return null;
    }

    public final zzps zzkf() {
        return null;
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzbuy.b((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzmv() {
        View d = this.zzbuy.d();
        if (d == null) {
            return null;
        }
        return ObjectWrapper.wrap(d);
    }

    public final IObjectWrapper zzmw() {
        View f = this.zzbuy.f();
        if (f == null) {
            return null;
        }
        return ObjectWrapper.wrap(f);
    }
}
