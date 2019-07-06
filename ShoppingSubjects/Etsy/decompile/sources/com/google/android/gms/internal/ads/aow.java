package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bg;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.common.util.Predicate;
import java.util.Map;
import org.json.JSONObject;

@bu
public final class aow implements aoo, aou {
    /* access modifiers changed from: private */
    public final nn a;
    private final Context b;

    public aow(Context context, zzang zzang, @Nullable ack ack, bg bgVar) throws zzarg {
        this.b = context;
        ao.f();
        this.a = nt.a(context, ot.a(), "", false, false, ack, zzang, null, null, null, ahh.a());
        this.a.getView().setWillNotDraw(true);
    }

    private static void a(Runnable runnable) {
        ajh.a();
        if (jp.b()) {
            runnable.run();
        } else {
            hd.a.post(runnable);
        }
    }

    public final void a() {
        this.a.destroy();
    }

    public final void a(aov aov) {
        oo zzuf = this.a.zzuf();
        aov.getClass();
        zzuf.zza(aoz.a(aov));
    }

    public final void a(String str) {
        a((Runnable) new apb(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str})));
    }

    public final void a(String str, ae<? super aqd> aeVar) {
        this.a.zza(str, (ae<? super nn>) new ape<Object>(this, aeVar));
    }

    public final void a(String str, String str2) {
        aop.a((aoo) this, str, str2);
    }

    public final aqe b() {
        return new aqf(this);
    }

    public final void b(String str) {
        a((Runnable) new apc(this, str));
    }

    public final void b(String str, ae<? super aqd> aeVar) {
        this.a.zza(str, (Predicate<ae<? super nn>>) new aoy<ae<? super nn>>(aeVar));
    }

    public final void c(String str) {
        a((Runnable) new apd(this, str));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void d(String str) {
        this.a.zzbe(str);
    }

    public final void zza(String str, Map map) {
        aop.a((aoo) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        aop.b(this, str, jSONObject);
    }

    public final void zzb(String str, JSONObject jSONObject) {
        aop.a((aoo) this, str, jSONObject);
    }

    public final void zzbe(String str) {
        a((Runnable) new aox(this, str));
    }
}
