package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.ads.internal.gmsg.c;
import org.json.JSONObject;

@bu
public final class afa implements afj {
    /* access modifiers changed from: private */
    public final zzet a;
    private final Context b;
    /* access modifiers changed from: private */
    public final c c;
    private apt d;
    /* access modifiers changed from: private */
    public boolean e;
    private final ae<aqd> f = new aff(this);
    private final ae<aqd> g = new afg(this);
    private final ae<aqd> h = new afh(this);
    private final ae<aqd> i = new afi(this);

    public afa(zzet zzet, apg apg, Context context) {
        this.a = zzet;
        this.b = context;
        this.c = new c(this.b);
        this.d = apg.b((ack) null);
        this.d.a(new afb(this), new afc(this));
        String str = "Core JS tracking ad unit: ";
        String valueOf = String.valueOf(this.a.zzaet.d());
        gv.b(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    /* access modifiers changed from: 0000 */
    public final void a(aqd aqd) {
        aqd.a("/updateActiveView", this.f);
        aqd.a("/untrackActiveViewUnit", this.g);
        aqd.a("/visibilityChanged", this.h);
        if (ao.B().a(this.b)) {
            aqd.a("/logScionEvent", this.i);
        }
    }

    public final void a(JSONObject jSONObject, boolean z) {
        this.d.a(new afd(this, jSONObject), new li());
    }

    public final boolean a() {
        return this.e;
    }

    public final void b() {
        this.d.a(new afe(this), new li());
        this.d.c();
    }

    /* access modifiers changed from: 0000 */
    public final void b(aqd aqd) {
        aqd.b("/visibilityChanged", this.h);
        aqd.b("/untrackActiveViewUnit", this.g);
        aqd.b("/updateActiveView", this.f);
        if (ao.B().a(this.b)) {
            aqd.b("/logScionEvent", this.i);
        }
    }
}
