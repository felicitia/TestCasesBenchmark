package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.gmsg.k;
import com.google.android.gms.ads.internal.gmsg.m;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.aen;
import com.google.android.gms.internal.ads.ahh;
import com.google.android.gms.internal.ads.ahi;
import com.google.android.gms.internal.ads.ait;
import com.google.android.gms.internal.ads.aiw;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.akw;
import com.google.android.gms.internal.ads.aky;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.bw;
import com.google.android.gms.internal.ads.fu;
import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.gb;
import com.google.android.gms.internal.ads.gc;
import com.google.android.gms.internal.ads.gl;
import com.google.android.gms.internal.ads.gn;
import com.google.android.gms.internal.ads.gs;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.jp;
import com.google.android.gms.internal.ads.ka;
import com.google.android.gms.internal.ads.ot;
import com.google.android.gms.internal.ads.z;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzabc;
import com.google.android.gms.internal.ads.zzagp;
import com.google.android.gms.internal.ads.zzagx;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzhu.zza.zzb;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkt;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzms;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zzod;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public abstract class zza extends zzkt implements k, m, com.google.android.gms.ads.internal.overlay.k, ait, bw, gl, z {
    protected aky zzvr;
    protected akw zzvs;
    private akw zzvt;
    protected boolean zzvu = false;
    protected final af zzvv;
    protected final zzbw zzvw;
    @Nullable
    protected transient zzjj zzvx;
    protected final aen zzvy;
    private final Bundle zzvz = new Bundle();
    private boolean zzwa = false;
    @Nullable
    protected IObjectWrapper zzwb;
    protected final bg zzwc;

    @VisibleForTesting
    zza(zzbw zzbw, @Nullable af afVar, bg bgVar) {
        this.zzvw = zzbw;
        this.zzvv = new af(this);
        this.zzwc = bgVar;
        ao.e().b(this.zzvw.zzrt);
        ao.e().c(this.zzvw.zzrt);
        gs.a(this.zzvw.zzrt);
        ao.C().a(this.zzvw.zzrt);
        ao.i().a(this.zzvw.zzrt, this.zzvw.zzacr);
        ao.k().a(this.zzvw.zzrt);
        this.zzvy = ao.i().g();
        ao.h().a(this.zzvw.zzrt);
        ao.E().a(this.zzvw.zzrt);
        if (((Boolean) ajh.f().a(akl.cn)).booleanValue()) {
            Timer timer = new Timer();
            timer.schedule(new u(this, new CountDownLatch(((Integer) ajh.f().a(akl.cp)).intValue()), timer), 0, ((Long) ajh.f().a(akl.co)).longValue());
        }
    }

    protected static boolean zza(zzjj zzjj) {
        Bundle bundle = zzjj.zzaqg.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle == null || !bundle.containsKey("gw");
    }

    @VisibleForTesting
    private static long zzq(String str) {
        int indexOf = str.indexOf("ufe");
        int indexOf2 = str.indexOf(44, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        try {
            return Long.parseLong(str.substring(indexOf + 4, indexOf2));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ka.b("", e);
            return -1;
        }
    }

    public void destroy() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: destroy");
        this.zzvv.a();
        this.zzvy.b(this.zzvw.zzacw);
        zzbw zzbw = this.zzvw;
        if (zzbw.zzacs != null) {
            zzbw.zzacs.zzfs();
        }
        zzbw.zzada = null;
        zzbw.zzadc = null;
        zzbw.zzadb = null;
        zzbw.zzado = null;
        zzbw.zzadd = null;
        zzbw.zzg(false);
        if (zzbw.zzacs != null) {
            zzbw.zzacs.removeAllViews();
        }
        zzbw.zzfm();
        zzbw.zzfn();
        zzbw.zzacw = null;
    }

    public String getAdUnitId() {
        return this.zzvw.zzacp;
    }

    public zzlo getVideoController() {
        return null;
    }

    public final boolean isLoading() {
        return this.zzvu;
    }

    public final boolean isReady() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: isLoaded");
        return this.zzvw.zzact == null && this.zzvw.zzacu == null && this.zzvw.zzacw != null;
    }

    public void onAdClicked() {
        if (this.zzvw.zzacw == null) {
            gv.e("Ad state was null when trying to ping click URLs.");
            return;
        }
        gv.b("Pinging click URLs.");
        if (this.zzvw.zzacy != null) {
            this.zzvw.zzacy.b();
        }
        if (this.zzvw.zzacw.c != null) {
            ao.e();
            hd.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, zzc(this.zzvw.zzacw.c));
        }
        if (this.zzvw.zzacz != null) {
            try {
                this.zzvw.zzacz.onAdClicked();
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
    }

    public final void onAppEvent(String str, @Nullable String str2) {
        if (this.zzvw.zzadb != null) {
            try {
                this.zzvw.zzadb.onAppEvent(str, str2);
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
    }

    public void pause() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: pause");
    }

    public void resume() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: resume");
    }

    public void setImmersiveMode(boolean z) {
        throw new IllegalStateException("#005 Unexpected call to an abstract (unimplemented) method.");
    }

    public void setManualImpressionsEnabled(boolean z) {
        gv.e("Attempt to call setManualImpressionsEnabled for an unsupported ad type.");
    }

    public final void setUserId(String str) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setUserId");
        this.zzvw.zzadr = str;
    }

    public final void stopLoading() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: stopLoading");
        this.zzvu = false;
        this.zzvw.zzg(true);
    }

    public final void zza(akw akw) {
        this.zzvr = new aky(((Boolean) ajh.f().a(akl.N)).booleanValue(), "load_ad", this.zzvw.zzacv.zzarb);
        this.zzvt = new akw(-1, null, null);
        if (akw == null) {
            this.zzvs = new akw(-1, null, null);
        } else {
            this.zzvs = new akw(akw.a(), akw.b(), akw.c());
        }
    }

    public final void zza(gb gbVar) {
        if (gbVar.b.zzceu != -1 && !TextUtils.isEmpty(gbVar.b.zzcfd)) {
            long zzq = zzq(gbVar.b.zzcfd);
            if (zzq != -1) {
                akw a = this.zzvr.a(gbVar.b.zzceu + zzq);
                this.zzvr.a(a, "stc");
            }
        }
        this.zzvr.a(gbVar.b.zzcfd);
        this.zzvr.a(this.zzvs, "arf");
        this.zzvt = this.zzvr.a();
        this.zzvr.a("gqi", gbVar.b.zzamj);
        this.zzvw.zzact = null;
        this.zzvw.zzacx = gbVar;
        gbVar.i.a((ahi) new ap(this, gbVar));
        gbVar.i.a(zzb.AD_LOADED);
        zza(gbVar, this.zzvr);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(gb gbVar, aky aky);

    public void zza(zzaaw zzaaw) {
        gv.e("#006 Unexpected call to a deprecated method.");
    }

    public final void zza(zzabc zzabc, String str) {
        gv.e("#006 Unexpected call to a deprecated method.");
    }

    public final void zza(zzagx zzagx) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedAdSkuListener");
        this.zzvw.zzadq = zzagx;
    }

    public final void zza(zzahe zzahe) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setRewardedVideoAdListener");
        this.zzvw.zzadp = zzahe;
    }

    /* access modifiers changed from: protected */
    public final void zza(@Nullable zzaig zzaig) {
        if (this.zzvw.zzadp != null) {
            String str = "";
            int i = 1;
            if (zzaig != null) {
                try {
                    str = zzaig.type;
                    i = zzaig.zzcmk;
                } catch (RemoteException e) {
                    gv.d("#007 Could not call remote method.", e);
                    return;
                }
            }
            zzagp zzagp = new zzagp(str, i);
            this.zzvw.zzadp.zza(zzagp);
            if (this.zzvw.zzadq != null) {
                this.zzvw.zzadq.zza(zzagp, this.zzvw.zzacx.a.zzcdi);
            }
        }
    }

    public final void zza(zzjn zzjn) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdSize");
        this.zzvw.zzacv = zzjn;
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.b == null || this.zzvw.zzadv != 0)) {
            this.zzvw.zzacw.b.zza(ot.a(zzjn));
        }
        if (this.zzvw.zzacs != null) {
            if (this.zzvw.zzacs.getChildCount() > 1) {
                this.zzvw.zzacs.removeView(this.zzvw.zzacs.getNextView());
            }
            this.zzvw.zzacs.setMinimumWidth(zzjn.widthPixels);
            this.zzvw.zzacs.setMinimumHeight(zzjn.heightPixels);
            this.zzvw.zzacs.requestLayout();
        }
    }

    public final void zza(zzke zzke) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdClickListener");
        this.zzvw.zzacz = zzke;
    }

    public final void zza(zzkh zzkh) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAdListener");
        this.zzvw.zzada = zzkh;
    }

    public final void zza(zzkx zzkx) {
        this.zzvw.zzadc = zzkx;
    }

    public final void zza(zzla zzla) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setAppEventListener");
        this.zzvw.zzadb = zzla;
    }

    public final void zza(zzlg zzlg) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setCorrelationIdProvider");
        this.zzvw.zzadd = zzlg;
    }

    public final void zza(@Nullable zzlu zzlu) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setIconAdOptions");
        this.zzvw.zzadl = zzlu;
    }

    public final void zza(@Nullable zzmu zzmu) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: setVideoOptions");
        this.zzvw.zzadk = zzmu;
    }

    public void zza(zzod zzod) {
        throw new IllegalStateException("#005 Unexpected call to an abstract (unimplemented) method.");
    }

    public final void zza(String str, Bundle bundle) {
        this.zzvz.putAll(bundle);
        if (this.zzwa && this.zzvw.zzadc != null) {
            try {
                this.zzvw.zzadc.zzt();
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zza(HashSet<gc> hashSet) {
        this.zzvw.zza(hashSet);
    }

    /* access modifiers changed from: 0000 */
    public boolean zza(ga gaVar) {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(@Nullable ga gaVar, ga gaVar2);

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzjj zzjj, aky aky);

    /* access modifiers changed from: protected */
    public final List<String> zzb(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (String b : list) {
            arrayList.add(fu.b(b, this.zzvw.zzrt));
        }
        return arrayList;
    }

    public void zzb(ga gaVar) {
        ahh ahh;
        zzb zzb;
        this.zzvr.a(this.zzvt, "awr");
        this.zzvw.zzacu = null;
        if (!(gaVar.d == -2 || gaVar.d == 3 || this.zzvw.zzfl() == null)) {
            ao.j().a(this.zzvw.zzfl());
        }
        if (gaVar.d == -1) {
            this.zzvu = false;
            return;
        }
        if (zza(gaVar)) {
            gv.b("Ad refresh scheduled.");
        }
        if (gaVar.d != -2) {
            if (gaVar.d == 3) {
                ahh = gaVar.K;
                zzb = zzb.AD_FAILED_TO_LOAD_NO_FILL;
            } else {
                ahh = gaVar.K;
                zzb = zzb.AD_FAILED_TO_LOAD;
            }
            ahh.a(zzb);
            zzi(gaVar.d);
            return;
        }
        if (this.zzvw.zzadt == null) {
            this.zzvw.zzadt = new gn(this.zzvw.zzacp);
        }
        if (this.zzvw.zzacs != null) {
            this.zzvw.zzacs.zzfr().d(gaVar.B);
        }
        this.zzvy.a(this.zzvw.zzacw);
        if (zza(this.zzvw.zzacw, gaVar)) {
            this.zzvw.zzacw = gaVar;
            zzbw zzbw = this.zzvw;
            if (zzbw.zzacy != null) {
                if (zzbw.zzacw != null) {
                    zzbw.zzacy.a(zzbw.zzacw.y);
                    zzbw.zzacy.b(zzbw.zzacw.z);
                    zzbw.zzacy.b(zzbw.zzacw.n);
                }
                zzbw.zzacy.a(zzbw.zzacv.zzarc);
            }
            this.zzvr.a("is_mraid", this.zzvw.zzacw.a() ? "1" : "0");
            this.zzvr.a("is_mediation", this.zzvw.zzacw.n ? "1" : "0");
            if (!(this.zzvw.zzacw.b == null || this.zzvw.zzacw.b.zzuf() == null)) {
                this.zzvr.a("is_delay_pl", this.zzvw.zzacw.b.zzuf().zzux() ? "1" : "0");
            }
            this.zzvr.a(this.zzvs, "ttc");
            if (ao.i().b() != null) {
                ao.i().b().a(this.zzvr);
            }
            zzbv();
            if (this.zzvw.zzfo()) {
                zzbq();
            }
        }
        if (gaVar.J != null) {
            ao.e().a(this.zzvw.zzrt, gaVar.J);
        }
    }

    /* access modifiers changed from: protected */
    public void zzb(boolean z) {
        gv.a("Ad finished loading.");
        this.zzvu = z;
        this.zzwa = true;
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdLoaded();
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdLoaded();
            } catch (RemoteException e2) {
                gv.d("#007 Could not call remote method.", e2);
            }
        }
        if (this.zzvw.zzadc != null) {
            try {
                this.zzvw.zzadc.zzt();
            } catch (RemoteException e3) {
                gv.d("#007 Could not call remote method.", e3);
            }
        }
    }

    public boolean zzb(zzjj zzjj) {
        String sb;
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: loadAd");
        ao.k().a();
        this.zzvz.clear();
        this.zzwa = false;
        if (((Boolean) ajh.f().a(akl.aN)).booleanValue()) {
            zzjj = zzjj.zzhv();
            if (((Boolean) ajh.f().a(akl.aO)).booleanValue()) {
                zzjj.extras.putBoolean(AdMobAdapter.NEW_BUNDLE, true);
            }
        }
        if (DeviceProperties.isSidewinder(this.zzvw.zzrt) && zzjj.zzaqe != null) {
            zzjj = new aiw(zzjj).a(null).a();
        }
        if (this.zzvw.zzact == null && this.zzvw.zzacu == null) {
            gv.d("Starting ad request.");
            zza((akw) null);
            this.zzvs = this.zzvr.a();
            if (zzjj.zzapz) {
                sb = "This request is sent from a test device.";
            } else {
                ajh.a();
                String a = jp.a(this.zzvw.zzrt);
                StringBuilder sb2 = new StringBuilder(71 + String.valueOf(a).length());
                sb2.append("Use AdRequest.Builder.addTestDevice(\"");
                sb2.append(a);
                sb2.append("\") to get test ads on this device.");
                sb = sb2.toString();
            }
            gv.d(sb);
            this.zzvv.a(zzjj);
            this.zzvu = zza(zzjj, this.zzvr);
            return this.zzvu;
        }
        gv.e(this.zzvx != null ? "Aborting last ad request since another ad request is already in progress. The current request object will still be cached for future refreshes." : "Loading already in progress, saving this object for future refreshes.");
        this.zzvx = zzjj;
        return false;
    }

    public final Bundle zzba() {
        return this.zzwa ? this.zzvz : new Bundle();
    }

    public final bg zzbi() {
        return this.zzwc;
    }

    public final IObjectWrapper zzbj() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: getAdFrame");
        return ObjectWrapper.wrap(this.zzvw.zzacs);
    }

    @Nullable
    public final zzjn zzbk() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: getAdSize");
        if (this.zzvw.zzacv == null) {
            return null;
        }
        return new zzms(this.zzvw.zzacv);
    }

    public final void zzbl() {
        zzbo();
    }

    public final void zzbm() {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.: recordManualImpression");
        if (this.zzvw.zzacw == null) {
            gv.e("Ad state was null when trying to ping manual tracking URLs.");
            return;
        }
        gv.b("Pinging manual tracking URLs.");
        if (!this.zzvw.zzacw.H) {
            ArrayList arrayList = new ArrayList();
            if (this.zzvw.zzacw.g != null) {
                arrayList.addAll(this.zzvw.zzacw.g);
            }
            if (!(this.zzvw.zzacw.o == null || this.zzvw.zzacw.o.i == null)) {
                arrayList.addAll(this.zzvw.zzacw.o.i);
            }
            if (!arrayList.isEmpty()) {
                ao.e();
                hd.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, (List<String>) arrayList);
                this.zzvw.zzacw.H = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzbn() {
        gv.a("Ad closing.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdClosed();
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdClosed();
            } catch (RemoteException e2) {
                gv.d("#007 Could not call remote method.", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbo() {
        gv.a("Ad leaving application.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdLeftApplication();
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdLeftApplication();
            } catch (RemoteException e2) {
                gv.d("#007 Could not call remote method.", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbp() {
        gv.a("Ad opening.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdOpened();
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdOpened();
            } catch (RemoteException e2) {
                gv.d("#007 Could not call remote method.", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zzbq() {
        zzb(false);
    }

    public final void zzbr() {
        gv.d("Ad impression.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdImpression();
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzbs() {
        gv.d("Ad clicked.");
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdClicked();
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbt() {
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoStarted();
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbu() {
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoCompleted();
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzbv() {
        ga gaVar = this.zzvw.zzacw;
        if (gaVar != null && !TextUtils.isEmpty(gaVar.B) && !gaVar.I && ao.o().b()) {
            gv.b("Sending troubleshooting signals to the server.");
            ao.o().b(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, gaVar.B, this.zzvw.zzacp);
            gaVar.I = true;
        }
    }

    public final zzla zzbw() {
        return this.zzvw.zzadb;
    }

    public final zzkh zzbx() {
        return this.zzvw.zzada;
    }

    /* access modifiers changed from: protected */
    public final void zzby() {
        if (this.zzwb != null) {
            ao.u().b(this.zzwb);
            this.zzwb = null;
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final String zzbz() {
        gb gbVar = this.zzvw.zzacx;
        if (gbVar == null) {
            return "javascript";
        }
        if (gbVar.b == null) {
            return "javascript";
        }
        String str = gbVar.b.zzcfq;
        if (TextUtils.isEmpty(str)) {
            return "javascript";
        }
        try {
            if (new JSONObject(str).optInt(ResponseConstants.MEDIA_TYPE, -1) == 0) {
                return null;
            }
            return "javascript";
        } catch (JSONException e) {
            ka.c("", e);
            return "javascript";
        }
    }

    /* access modifiers changed from: protected */
    public final List<String> zzc(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (String a : list) {
            arrayList.add(fu.a(a, this.zzvw.zzrt));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void zzc(int i, boolean z) {
        StringBuilder sb = new StringBuilder(30);
        sb.append("Failed to load ad: ");
        sb.append(i);
        gv.e(sb.toString());
        this.zzvu = z;
        if (this.zzvw.zzada != null) {
            try {
                this.zzvw.zzada.onAdFailedToLoad(i);
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
        if (this.zzvw.zzadp != null) {
            try {
                this.zzvw.zzadp.onRewardedVideoAdFailedToLoad(i);
            } catch (RemoteException e2) {
                gv.d("#007 Could not call remote method.", e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean zzc(zzjj zzjj) {
        if (this.zzvw.zzacs == null) {
            return false;
        }
        ViewParent parent = this.zzvw.zzacs.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        return ao.e().a(view, view.getContext());
    }

    /* access modifiers changed from: protected */
    public final void zzg(View view) {
        zzbx zzbx = this.zzvw.zzacs;
        if (zzbx != null) {
            zzbx.addView(view, ao.g().d());
        }
    }

    /* access modifiers changed from: protected */
    public void zzi(int i) {
        zzc(i, false);
    }
}
