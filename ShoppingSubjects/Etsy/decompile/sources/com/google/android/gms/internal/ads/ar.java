package com.google.android.gms.internal.ads;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.ads.internal.gmsg.o;
import com.google.android.gms.ads.internal.zzbc;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

@bu
public final class ar {
    private final Object a = new Object();
    private final Context b;
    private final ack c;
    private final gb d;
    private final aky e;
    /* access modifiers changed from: private */
    public final zzbc f;
    private OnGlobalLayoutListener g;
    private OnScrollChangedListener h;
    private final DisplayMetrics i;
    private je j;
    private int k = -1;
    private int l = -1;

    public ar(Context context, ack ack, gb gbVar, aky aky, zzbc zzbc) {
        this.b = context;
        this.c = ack;
        this.d = gbVar;
        this.e = aky;
        this.f = zzbc;
        this.j = new je(200);
        ao.e();
        this.i = hd.a((WindowManager) context.getSystemService("window"));
    }

    /* access modifiers changed from: private */
    public final void a(WeakReference<nn> weakReference, boolean z) {
        if (weakReference != null) {
            nn nnVar = (nn) weakReference.get();
            if (nnVar != null && nnVar.getView() != null) {
                if (!z || this.j.a()) {
                    int[] iArr = new int[2];
                    nnVar.getView().getLocationOnScreen(iArr);
                    ajh.a();
                    int b2 = jp.b(this.i, iArr[0]);
                    ajh.a();
                    int b3 = jp.b(this.i, iArr[1]);
                    synchronized (this.a) {
                        if (!(this.k == b2 && this.l == b3)) {
                            this.k = b2;
                            this.l = b3;
                            nnVar.zzuf().zza(this.k, this.l, !z);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(le leVar, nn nnVar, boolean z) {
        this.f.zzdw();
        leVar.b(nnVar);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(JSONObject jSONObject, le leVar) {
        try {
            ao.f();
            nn a2 = nt.a(this.b, ot.a(), "native-video", false, false, this.c, this.d.a.zzacr, this.e, null, this.f.zzbi(), this.d.i);
            a2.zza(ot.b());
            this.f.zzf(a2);
            WeakReference weakReference = new WeakReference(a2);
            oo zzuf = a2.zzuf();
            if (this.g == null) {
                this.g = new ax(this, weakReference);
            }
            OnGlobalLayoutListener onGlobalLayoutListener = this.g;
            if (this.h == null) {
                this.h = new ay(this, weakReference);
            }
            zzuf.zza(onGlobalLayoutListener, this.h);
            a2.zza("/video", o.l);
            a2.zza("/videoMeta", o.m);
            a2.zza("/precache", (ae<? super nn>) new nd<Object>());
            a2.zza("/delayPageLoaded", o.p);
            a2.zza("/instrument", o.n);
            a2.zza("/log", o.g);
            a2.zza("/videoClicked", o.h);
            a2.zza("/trackActiveViewUnit", (ae<? super nn>) new av<Object>(this));
            a2.zza("/untrackActiveViewUnit", (ae<? super nn>) new aw<Object>(this));
            a2.zzuf().zza((oq) new at(a2, jSONObject));
            a2.zzuf().zza((op) new au(this, leVar, a2));
            a2.loadUrl((String) ajh.f().a(akl.bY));
        } catch (Exception e2) {
            gv.c("Exception occurred while getting video view", e2);
            leVar.b(null);
        }
    }
}
