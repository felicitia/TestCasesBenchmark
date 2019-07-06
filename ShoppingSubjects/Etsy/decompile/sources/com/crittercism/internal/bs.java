package com.crittercism.internal;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import android.support.annotation.NonNull;
import com.crittercism.internal.cj.e;
import com.crittercism.internal.ck.AnonymousClass3;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

public final class bs extends br {
    private final ExecutorService b;
    /* access modifiers changed from: private */
    public final ay<ar> c;
    private ar d;
    /* access modifiers changed from: private */
    public com.crittercism.internal.ap.a e;
    /* access modifiers changed from: private */
    public com.crittercism.internal.ap.b f;
    /* access modifiers changed from: private */
    public ap g;
    private boolean h;
    private ck i;
    private Date j;

    public static abstract class a implements IdleHandler {
        private boolean a = false;
        private String b;
        private ck c;
        private Date d;

        public abstract void a();

        public a(@NonNull String str, @NonNull ck ckVar, @NonNull Date date) {
            this.b = str;
            this.c = ckVar;
            this.d = date;
        }

        public boolean queueIdle() {
            if (this.a) {
                b();
                return true;
            }
            this.a = true;
            ck ckVar = this.c;
            String str = this.b;
            Date date = this.d;
            Date date2 = new Date();
            long time = date.getTime();
            long time2 = date2.getTime();
            if (time - time2 > 0) {
                cm.b(cl.NegativeLifecycleUserflowTime.a());
            } else {
                ScheduledExecutorService scheduledExecutorService = ckVar.b;
                AnonymousClass3 r2 = new Runnable(str, time, time2) {
                    final /* synthetic */ String a;
                    final /* synthetic */ long b;
                    final /* synthetic */ long c;

                    {
                        this.a = r2;
                        this.b = r3;
                        this.c = r5;
                    }

                    public final void run() {
                        com.crittercism.internal.cj.a aVar = new com.crittercism.internal.cj.a();
                        aVar.a = this.a;
                        aVar.b = this.b;
                        aVar.c = -1;
                        aVar.d = Long.MAX_VALUE;
                        aVar.e = e.c;
                        cj a2 = aVar.a();
                        ck.this.a.put(a2.a, a2);
                        ck.this.a(a2.a, this.c);
                    }
                };
                scheduledExecutorService.submit(r2);
            }
            b();
            return true;
        }

        private void b() {
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public static class b extends a {
        public b(@NonNull String str, @NonNull ck ckVar, @NonNull Date date) {
            super(str, ckVar, date);
        }

        @SuppressLint({"NewApi"})
        public final void a() {
            Looper.getMainLooper().getQueue().addIdleHandler(this);
        }
    }

    public static class c extends a {
        public c(@NonNull String str, @NonNull ck ckVar, @NonNull Date date) {
            super(str, ckVar, date);
        }

        public final void a() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Looper.myQueue().addIdleHandler(this);
            }
        }
    }

    public bs(@NonNull Application application, @NonNull ExecutorService executorService, @NonNull ay<ar> ayVar, @NonNull ar arVar, @NonNull ap apVar, @NonNull com.crittercism.internal.ap.a aVar, @NonNull com.crittercism.internal.ap.b bVar, boolean z, ck ckVar, Date date) {
        super(application);
        this.b = executorService;
        this.c = ayVar;
        this.d = arVar;
        this.h = z;
        this.i = ckVar;
        this.j = date;
        this.e = aVar;
        this.f = bVar;
        this.g = apVar;
        a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void d() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.h     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r4)
            return
        L_0x0007:
            r4.h()     // Catch:{ all -> 0x003e }
            com.crittercism.internal.ck r0 = r4.i     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003c
            java.util.Date r0 = r4.j     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003c
            com.crittercism.internal.ck r0 = r4.i     // Catch:{ all -> 0x003e }
            java.util.Date r1 = r4.j     // Catch:{ all -> 0x003e }
            if (r1 == 0) goto L_0x0037
            if (r0 == 0) goto L_0x0037
            boolean r2 = r0.e     // Catch:{ all -> 0x003e }
            if (r2 == 0) goto L_0x001f
            goto L_0x0037
        L_0x001f:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x003e }
            r3 = 23
            if (r2 < r3) goto L_0x002d
            com.crittercism.internal.bs$b r2 = new com.crittercism.internal.bs$b     // Catch:{ all -> 0x003e }
            java.lang.String r3 = "App Load M"
            r2.<init>(r3, r0, r1)     // Catch:{ all -> 0x003e }
            goto L_0x0034
        L_0x002d:
            com.crittercism.internal.bs$c r2 = new com.crittercism.internal.bs$c     // Catch:{ all -> 0x003e }
            java.lang.String r3 = "App Load"
            r2.<init>(r3, r0, r1)     // Catch:{ all -> 0x003e }
        L_0x0034:
            r2.a()     // Catch:{ all -> 0x003e }
        L_0x0037:
            r0 = 0
            r4.i = r0     // Catch:{ all -> 0x003e }
            r4.j = r0     // Catch:{ all -> 0x003e }
        L_0x003c:
            monitor-exit(r4)
            return
        L_0x003e:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crittercism.internal.bs.d():void");
    }

    private void h() {
        final ar arVar = this.d;
        if (arVar != null) {
            this.b.submit(new Runnable() {
                public final void run() {
                    if (((Boolean) bs.this.g.a(bs.this.e)).booleanValue()) {
                        arVar.p = ((Float) bs.this.g.a(bs.this.f)).floatValue();
                        bs.this.c.a(arVar);
                    }
                }
            });
            this.d = null;
        }
    }

    public final synchronized void g() {
        this.h = false;
        if (this.a) {
            h();
        }
    }
}
