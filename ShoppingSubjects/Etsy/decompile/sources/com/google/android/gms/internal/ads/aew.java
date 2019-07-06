package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import org.json.JSONObject;

@bu
public final class aew implements afj {
    /* access modifiers changed from: private */
    public final zzet a;
    private final nn b;
    private final ae<nn> c = new aex(this);
    private final ae<nn> d = new aey(this);
    private final ae<nn> e = new aez(this);

    public aew(zzet zzet, nn nnVar) {
        this.a = zzet;
        this.b = nnVar;
        nn nnVar2 = this.b;
        nnVar2.zza("/updateActiveView", this.c);
        nnVar2.zza("/untrackActiveViewUnit", this.d);
        nnVar2.zza("/visibilityChanged", this.e);
        String str = "Custom JS tracking ad unit: ";
        String valueOf = String.valueOf(this.a.zzaet.d());
        gv.b(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public final void a(JSONObject jSONObject, boolean z) {
        if (!z) {
            this.b.zzb("AFMA_updateActiveView", jSONObject);
        } else {
            this.a.zzb(this);
        }
    }

    public final boolean a() {
        return true;
    }

    public final void b() {
        nn nnVar = this.b;
        nnVar.zzb("/visibilityChanged", this.e);
        nnVar.zzb("/untrackActiveViewUnit", this.d);
        nnVar.zzb("/updateActiveView", this.c);
    }
}
