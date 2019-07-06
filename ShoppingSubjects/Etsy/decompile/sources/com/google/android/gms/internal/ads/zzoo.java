package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

@bu
public final class zzoo extends zzql implements alo {
    private Bundle mExtras;
    private Object mLock = new Object();
    private String zzbhw;
    private List<zzon> zzbhx;
    private String zzbhy;
    private zzpw zzbhz;
    private String zzbia;
    private double zzbib;
    private String zzbic;
    private String zzbid;
    @Nullable
    private zzoj zzbie;
    @Nullable
    private zzlo zzbif;
    @Nullable
    private View zzbig;
    @Nullable
    private IObjectWrapper zzbih;
    @Nullable
    private String zzbii;
    /* access modifiers changed from: private */
    public alk zzbij;

    public zzoo(String str, List<zzon> list, String str2, zzpw zzpw, String str3, double d, String str4, String str5, @Nullable zzoj zzoj, Bundle bundle, zzlo zzlo, View view, IObjectWrapper iObjectWrapper, String str6) {
        this.zzbhw = str;
        this.zzbhx = list;
        this.zzbhy = str2;
        this.zzbhz = zzpw;
        this.zzbia = str3;
        this.zzbib = d;
        this.zzbic = str4;
        this.zzbid = str5;
        this.zzbie = zzoj;
        this.mExtras = bundle;
        this.zzbif = zzlo;
        this.zzbig = view;
        this.zzbih = iObjectWrapper;
        this.zzbii = str6;
    }

    public final void destroy() {
        hd.a.post(new ald(this));
        this.zzbhw = null;
        this.zzbhx = null;
        this.zzbhy = null;
        this.zzbhz = null;
        this.zzbia = null;
        this.zzbib = 0.0d;
        this.zzbic = null;
        this.zzbid = null;
        this.zzbie = null;
        this.mExtras = null;
        this.mLock = null;
        this.zzbif = null;
        this.zzbig = null;
    }

    public final String getBody() {
        return this.zzbhy;
    }

    public final String getCallToAction() {
        return this.zzbia;
    }

    public final String getCustomTemplateId() {
        return "";
    }

    public final Bundle getExtras() {
        return this.mExtras;
    }

    public final String getHeadline() {
        return this.zzbhw;
    }

    public final List getImages() {
        return this.zzbhx;
    }

    @Nullable
    public final String getMediationAdapterClassName() {
        return this.zzbii;
    }

    public final String getPrice() {
        return this.zzbid;
    }

    public final double getStarRating() {
        return this.zzbib;
    }

    public final String getStore() {
        return this.zzbic;
    }

    public final zzlo getVideoController() {
        return this.zzbif;
    }

    public final void performClick(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                gv.c("#001 Attempt to perform click before app native ad initialized.");
            } else {
                this.zzbij.b(bundle);
            }
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                gv.c("#002 Attempt to record impression before native ad initialized.");
                return false;
            }
            boolean a = this.zzbij.a(bundle);
            return a;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzbij == null) {
                gv.c("#003 Attempt to report touch event before native ad initialized.");
            } else {
                this.zzbij.c(bundle);
            }
        }
    }

    public final void zzb(alk alk) {
        synchronized (this.mLock) {
            this.zzbij = alk;
        }
    }

    public final zzpw zzjz() {
        return this.zzbhz;
    }

    public final IObjectWrapper zzka() {
        return ObjectWrapper.wrap(this.zzbij);
    }

    public final String zzkb() {
        return "2";
    }

    public final zzoj zzkc() {
        return this.zzbie;
    }

    public final View zzkd() {
        return this.zzbig;
    }

    public final IObjectWrapper zzke() {
        return this.zzbih;
    }

    public final zzps zzkf() {
        return this.zzbie;
    }
}
