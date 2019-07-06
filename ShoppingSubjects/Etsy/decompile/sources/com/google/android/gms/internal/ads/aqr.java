package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.gmsg.o;
import org.json.JSONObject;

@bu
public final class aqr<I, O> implements aqg<I, O> {
    /* access modifiers changed from: private */
    public final aqi<O> a;
    private final aqj<I> b;
    private final apg c;
    private final String d;

    aqr(apg apg, String str, aqj<I> aqj, aqi<O> aqi) {
        this.c = apg;
        this.d = str;
        this.b = aqj;
        this.a = aqi;
    }

    /* access modifiers changed from: private */
    public final void a(apt apt, aqd aqd, I i, le<O> leVar) {
        try {
            ao.e();
            String a2 = hd.a();
            o.o.a(a2, new aqu(this, apt, leVar));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", a2);
            jSONObject.put("args", this.b.a(i));
            aqd.zzb(this.d, jSONObject);
        } catch (Exception e) {
            leVar.a(e);
            gv.b("Unable to invokeJavaScript", e);
            apt.c();
        } catch (Throwable th) {
            apt.c();
            throw th;
        }
    }

    public final kt<O> a(I i) throws Exception {
        return b(i);
    }

    public final kt<O> b(I i) {
        le leVar = new le();
        apt b2 = this.c.b((ack) null);
        b2.a(new aqs(this, b2, i, leVar), new aqt(this, leVar, b2));
        return leVar;
    }
}
