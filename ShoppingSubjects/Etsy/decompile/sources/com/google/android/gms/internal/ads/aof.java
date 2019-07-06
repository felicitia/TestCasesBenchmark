package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;
import java.util.Map;
import org.json.JSONObject;

@bu
public final class aof extends aos<aqd> implements aoo, aou {
    private final zzasv a;

    /* JADX WARNING: type inference failed for: r3v0, types: [com.google.android.gms.internal.ads.aor, com.google.android.gms.internal.ads.aof] */
    public aof(Context context, zzang zzang) throws zzarg {
        try {
            this.a = new zzasv(new zzash(context));
            this.a.setWillNotDraw(true);
            this.a.zza((pg) new aog(this));
            this.a.zza((pi) new aoh(this));
            this.a.addJavascriptInterface(new aon(this), "GoogleJsInterface");
            ao.e().a(context, zzang.zzcw, this.a.getSettings());
        } catch (Throwable th) {
            throw new zzarg("Init failed.", th);
        }
    }

    public final void a() {
        this.a.destroy();
    }

    public final void a(aov aov) {
        this.a.zza((pk) new aok(aov));
    }

    public final void a(String str) {
        b(String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head></html>", new Object[]{str}));
    }

    public final void a(String str, String str2) {
        aop.a((aoo) this, str, str2);
    }

    public final aqe b() {
        return new aqf(this);
    }

    public final void b(String str) {
        kz.a.execute(new aoi(this, str));
    }

    public final void c(String str) {
        kz.a.execute(new aoj(this, str));
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void d(String str) {
        this.a.zzbe(str);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void e(String str) {
        this.a.loadUrl(str);
    }

    public final /* bridge */ /* synthetic */ Object f() {
        if (this != null) {
            return this;
        }
        throw null;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void f(String str) {
        this.a.loadData(str, "text/html", "UTF-8");
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
        kz.a.execute(new aol(this, str));
    }
}
