package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.internal.ads.acg;
import com.google.android.gms.internal.ads.acj;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hb;
import com.google.android.gms.internal.ads.jp;
import com.google.android.gms.internal.ads.zzang;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

@bu
public final class g implements acg, Runnable {
    private final List<Object[]> a;
    private final AtomicReference<acg> b;
    private Context c;
    private zzang d;
    private CountDownLatch e;

    private g(Context context, zzang zzang) {
        this.a = new Vector();
        this.b = new AtomicReference<>();
        this.e = new CountDownLatch(1);
        this.c = context;
        this.d = zzang;
        ajh.a();
        if (jp.b()) {
            hb.a((Runnable) this);
        } else {
            run();
        }
    }

    public g(zzbw zzbw) {
        this(zzbw.zzrt, zzbw.zzacr);
    }

    private final boolean a() {
        try {
            this.e.await();
            return true;
        } catch (InterruptedException e2) {
            gv.c("Interrupted during GADSignals creation.", e2);
            return false;
        }
    }

    private static Context b(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    private final void b() {
        if (!this.a.isEmpty()) {
            for (Object[] objArr : this.a) {
                if (objArr.length == 1) {
                    ((acg) this.b.get()).a((MotionEvent) objArr[0]);
                } else if (objArr.length == 3) {
                    ((acg) this.b.get()).a(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
                }
            }
            this.a.clear();
        }
    }

    public final String a(Context context) {
        if (a()) {
            acg acg = (acg) this.b.get();
            if (acg != null) {
                b();
                return acg.a(b(context));
            }
        }
        return "";
    }

    public final String a(Context context, String str, View view) {
        return a(context, str, view, null);
    }

    public final String a(Context context, String str, View view, Activity activity) {
        if (a()) {
            acg acg = (acg) this.b.get();
            if (acg != null) {
                b();
                return acg.a(b(context), str, view, activity);
            }
        }
        return "";
    }

    public final void a(int i, int i2, int i3) {
        acg acg = (acg) this.b.get();
        if (acg != null) {
            b();
            acg.a(i, i2, i3);
            return;
        }
        this.a.add(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    public final void a(MotionEvent motionEvent) {
        acg acg = (acg) this.b.get();
        if (acg != null) {
            b();
            acg.a(motionEvent);
            return;
        }
        this.a.add(new Object[]{motionEvent});
    }

    public final void a(View view) {
        acg acg = (acg) this.b.get();
        if (acg != null) {
            acg.a(view);
        }
    }

    public final void run() {
        boolean z = false;
        try {
            boolean z2 = this.d.zzcvg;
            if (!((Boolean) ajh.f().a(akl.aL)).booleanValue() && z2) {
                z = true;
            }
            this.b.set(acj.a(this.d.zzcw, b(this.c), z));
        } finally {
            this.e.countDown();
            this.c = null;
            this.d = null;
        }
    }
}
