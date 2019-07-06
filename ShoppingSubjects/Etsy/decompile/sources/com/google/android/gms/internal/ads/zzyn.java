package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.formats.a.b;
import com.google.android.gms.ads.mediation.h;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@bu
public final class zzyn extends zzyd {
    private final h zzbuz;

    public zzyn(h hVar) {
        this.zzbuz = hVar;
    }

    public final String getAdvertiser() {
        return this.zzbuz.n();
    }

    public final String getBody() {
        return this.zzbuz.k();
    }

    public final String getCallToAction() {
        return this.zzbuz.m();
    }

    public final Bundle getExtras() {
        return this.zzbuz.c();
    }

    public final String getHeadline() {
        return this.zzbuz.i();
    }

    public final List getImages() {
        List<b> j = this.zzbuz.j();
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
        return this.zzbuz.b();
    }

    public final boolean getOverrideImpressionRecording() {
        return this.zzbuz.a();
    }

    public final zzlo getVideoController() {
        if (this.zzbuz.g() != null) {
            return this.zzbuz.g().a();
        }
        return null;
    }

    public final void recordImpression() {
        this.zzbuz.e();
    }

    public final void zzb(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        this.zzbuz.a((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public final void zzj(IObjectWrapper iObjectWrapper) {
        this.zzbuz.c((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final void zzk(IObjectWrapper iObjectWrapper) {
        this.zzbuz.a((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzke() {
        return null;
    }

    public final zzps zzkf() {
        return null;
    }

    public final zzpw zzkg() {
        b l = this.zzbuz.l();
        if (l != null) {
            return new zzon(l.a(), l.b(), l.c());
        }
        return null;
    }

    public final void zzl(IObjectWrapper iObjectWrapper) {
        this.zzbuz.b((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    public final IObjectWrapper zzmv() {
        View d = this.zzbuz.d();
        if (d == null) {
            return null;
        }
        return ObjectWrapper.wrap(d);
    }

    public final IObjectWrapper zzmw() {
        View f = this.zzbuz.f();
        if (f == null) {
            return null;
        }
        return ObjectWrapper.wrap(f);
    }
}
