package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ao;

@bu
public final class apg {
    /* access modifiers changed from: private */
    public final Object a;
    private final Context b;
    private final String c;
    private final zzang d;
    /* access modifiers changed from: private */
    public ii<aou> e;
    private ii<aou> f;
    /* access modifiers changed from: private */
    @Nullable
    public apx g;
    /* access modifiers changed from: private */
    public int h;

    public apg(Context context, zzang zzang, String str) {
        this.a = new Object();
        this.h = 1;
        this.c = str;
        this.b = context.getApplicationContext();
        this.d = zzang;
        this.e = new aps();
        this.f = new aps();
    }

    public apg(Context context, zzang zzang, String str, ii<aou> iiVar, ii<aou> iiVar2) {
        this(context, zzang, str);
        this.e = iiVar;
        this.f = iiVar2;
    }

    /* access modifiers changed from: protected */
    public final apx a(@Nullable ack ack) {
        apx apx = new apx(this.f);
        kz.a.execute(new aph(this, ack, apx));
        apx.a(new app(this, apx), new apq(this, apx));
        return apx;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void a(ack ack, apx apx) {
        try {
            Context context = this.b;
            zzang zzang = this.d;
            aou aof = ((Boolean) ajh.f().a(akl.aA)).booleanValue() ? new aof(context, zzang) : new aow(context, zzang, ack, null);
            aof.a((aov) new api(this, apx, aof));
            aof.a("/jsLoaded", new apl(this, apx, aof));
            jf jfVar = new jf();
            apm apm = new apm(this, ack, aof, jfVar);
            jfVar.a(apm);
            aof.a("/requestReload", apm);
            if (this.c.endsWith(".js")) {
                aof.a(this.c);
            } else if (this.c.startsWith("<html>")) {
                aof.b(this.c);
            } else {
                aof.c(this.c);
            }
            hd.a.postDelayed(new apn(this, apx, aof), (long) apr.a);
        } catch (Throwable th) {
            gv.b("Error creating webview.", th);
            ao.i().a(th, "SdkJavascriptFactory.loadJavascriptEngine");
            apx.a();
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void a(com.google.android.gms.internal.ads.apx r4, com.google.android.gms.internal.ads.aou r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.a
            monitor-enter(r0)
            int r1 = r4.b()     // Catch:{ all -> 0x002a }
            r2 = -1
            if (r1 == r2) goto L_0x0028
            int r1 = r4.b()     // Catch:{ all -> 0x002a }
            r2 = 1
            if (r1 != r2) goto L_0x0012
            goto L_0x0028
        L_0x0012:
            r4.a()     // Catch:{ all -> 0x002a }
            java.util.concurrent.Executor r4 = com.google.android.gms.internal.ads.kz.a     // Catch:{ all -> 0x002a }
            r5.getClass()     // Catch:{ all -> 0x002a }
            java.lang.Runnable r5 = com.google.android.gms.internal.ads.apk.a(r5)     // Catch:{ all -> 0x002a }
            r4.execute(r5)     // Catch:{ all -> 0x002a }
            java.lang.String r4 = "Could not receive loaded message in a timely manner. Rejecting."
            com.google.android.gms.internal.ads.gv.a(r4)     // Catch:{ all -> 0x002a }
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            return
        L_0x002a:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002a }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.apg.a(com.google.android.gms.internal.ads.apx, com.google.android.gms.internal.ads.aou):void");
    }

    public final apt b(@Nullable ack ack) {
        synchronized (this.a) {
            if (this.g != null) {
                if (this.g.b() != -1) {
                    if (this.h == 0) {
                        apt c2 = this.g.c();
                        return c2;
                    } else if (this.h == 1) {
                        this.h = 2;
                        a((ack) null);
                        apt c3 = this.g.c();
                        return c3;
                    } else if (this.h == 2) {
                        apt c4 = this.g.c();
                        return c4;
                    } else {
                        apt c5 = this.g.c();
                        return c5;
                    }
                }
            }
            this.h = 2;
            this.g = a((ack) null);
            apt c6 = this.g.c();
            return c6;
        }
    }
}
