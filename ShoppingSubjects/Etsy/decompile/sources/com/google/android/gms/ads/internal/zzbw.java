package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.internal.ads.acg;
import com.google.android.gms.internal.ads.ack;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.gb;
import com.google.android.gms.internal.ads.gc;
import com.google.android.gms.internal.ads.gn;
import com.google.android.gms.internal.ads.gq;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hw;
import com.google.android.gms.internal.ads.je;
import com.google.android.gms.internal.ads.jp;
import com.google.android.gms.internal.ads.zzagx;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzrl;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@bu
public final class zzbw implements OnGlobalLayoutListener, OnScrollChangedListener {
    final String zzaco;
    public String zzacp;
    final ack zzacq;
    public final zzang zzacr;
    @Nullable
    zzbx zzacs;
    @Nullable
    public gq zzact;
    @Nullable
    public hw zzacu;
    public zzjn zzacv;
    @Nullable
    public ga zzacw;
    public gb zzacx;
    @Nullable
    public gc zzacy;
    @Nullable
    zzke zzacz;
    @Nullable
    zzkh zzada;
    @Nullable
    zzla zzadb;
    @Nullable
    zzkx zzadc;
    @Nullable
    zzlg zzadd;
    @Nullable
    zzqw zzade;
    @Nullable
    zzqz zzadf;
    @Nullable
    zzrl zzadg;
    SimpleArrayMap<String, zzrc> zzadh;
    SimpleArrayMap<String, zzrf> zzadi;
    zzpl zzadj;
    @Nullable
    zzmu zzadk;
    @Nullable
    zzlu zzadl;
    @Nullable
    zzri zzadm;
    @Nullable
    List<Integer> zzadn;
    @Nullable
    zzod zzado;
    @Nullable
    zzahe zzadp;
    @Nullable
    zzagx zzadq;
    @Nullable
    public String zzadr;
    @Nullable
    List<String> zzads;
    @Nullable
    public gn zzadt;
    @Nullable
    View zzadu;
    public int zzadv;
    private HashSet<gc> zzadw;
    private int zzadx;
    private int zzady;
    private je zzadz;
    private boolean zzaea;
    private boolean zzaeb;
    private boolean zzaec;
    public final Context zzrt;
    boolean zzze;

    public zzbw(Context context, zzjn zzjn, String str, zzang zzang) {
        this(context, zzjn, str, zzang, null);
    }

    private zzbw(Context context, zzjn zzjn, String str, zzang zzang, ack ack) {
        this.zzadt = null;
        this.zzadu = null;
        this.zzadv = 0;
        this.zzze = false;
        this.zzadw = null;
        this.zzadx = -1;
        this.zzady = -1;
        this.zzaea = true;
        this.zzaeb = true;
        this.zzaec = false;
        akl.a(context);
        if (ao.i().b() != null) {
            List b = akl.b();
            if (zzang.zzcve != 0) {
                b.add(Integer.toString(zzang.zzcve));
            }
            ao.i().b().a(b);
        }
        this.zzaco = UUID.randomUUID().toString();
        if (zzjn.zzarc || zzjn.zzare) {
            this.zzacs = null;
        } else {
            zzbx zzbx = new zzbx(context, str, zzang.zzcw, this, this);
            this.zzacs = zzbx;
            this.zzacs.setMinimumWidth(zzjn.widthPixels);
            this.zzacs.setMinimumHeight(zzjn.heightPixels);
            this.zzacs.setVisibility(4);
        }
        this.zzacv = zzjn;
        this.zzacp = str;
        this.zzrt = context;
        this.zzacr = zzang;
        this.zzacq = new ack(new g(this));
        this.zzadz = new je(200);
        this.zzadi = new SimpleArrayMap<>();
    }

    private final void zzf(boolean z) {
        if (this.zzacs != null && this.zzacw != null && this.zzacw.b != null && this.zzacw.b.zzuf() != null) {
            if (!z || this.zzadz.a()) {
                if (this.zzacw.b.zzuf().zzfz()) {
                    int[] iArr = new int[2];
                    this.zzacs.getLocationOnScreen(iArr);
                    ajh.a();
                    int b = jp.b(this.zzrt, iArr[0]);
                    ajh.a();
                    int b2 = jp.b(this.zzrt, iArr[1]);
                    if (!(b == this.zzadx && b2 == this.zzady)) {
                        this.zzadx = b;
                        this.zzady = b2;
                        this.zzacw.b.zzuf().zza(this.zzadx, this.zzady, !z);
                    }
                }
                if (this.zzacs != null) {
                    View findViewById = this.zzacs.getRootView().findViewById(16908290);
                    if (findViewById != null) {
                        Rect rect = new Rect();
                        Rect rect2 = new Rect();
                        this.zzacs.getGlobalVisibleRect(rect);
                        findViewById.getGlobalVisibleRect(rect2);
                        if (rect.top != rect2.top) {
                            this.zzaea = false;
                        }
                        if (rect.bottom != rect2.bottom) {
                            this.zzaeb = false;
                        }
                    }
                }
            }
        }
    }

    public final void onGlobalLayout() {
        zzf(false);
    }

    public final void onScrollChanged() {
        zzf(true);
        this.zzaec = true;
    }

    public final void zza(HashSet<gc> hashSet) {
        this.zzadw = hashSet;
    }

    public final HashSet<gc> zzfl() {
        return this.zzadw;
    }

    public final void zzfm() {
        if (this.zzacw != null && this.zzacw.b != null) {
            this.zzacw.b.destroy();
        }
    }

    public final void zzfn() {
        if (!(this.zzacw == null || this.zzacw.p == null)) {
            try {
                this.zzacw.p.destroy();
            } catch (RemoteException unused) {
                gv.e("Could not destroy mediation adapter.");
            }
        }
    }

    public final boolean zzfo() {
        return this.zzadv == 0;
    }

    public final boolean zzfp() {
        return this.zzadv == 1;
    }

    public final String zzfq() {
        return (!this.zzaea || !this.zzaeb) ? this.zzaea ? this.zzaec ? "top-scrollable" : "top-locked" : this.zzaeb ? this.zzaec ? "bottom-scrollable" : "bottom-locked" : "" : "";
    }

    public final void zzg(boolean z) {
        if (!(this.zzadv != 0 || this.zzacw == null || this.zzacw.b == null)) {
            this.zzacw.b.stopLoading();
        }
        if (this.zzact != null) {
            this.zzact.b();
        }
        if (this.zzacu != null) {
            this.zzacu.b();
        }
        if (z) {
            this.zzacw = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zzj(View view) {
        if (((Boolean) ajh.f().a(akl.bG)).booleanValue()) {
            acg a = this.zzacq.a();
            if (a != null) {
                a.a(view);
            }
        }
    }
}
