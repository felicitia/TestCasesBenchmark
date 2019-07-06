package com.google.android.gms.internal.ads;

import android.os.Build.VERSION;
import android.os.ConditionVariable;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ace {
    protected static volatile ahl a;
    /* access modifiers changed from: private */
    public static final ConditionVariable d = new ConditionVariable();
    private static volatile Random e;
    protected volatile Boolean b;
    /* access modifiers changed from: private */
    public acy c;

    public ace(acy acy) {
        this.c = acy;
        acy.c().execute(new acf(this));
    }

    public static int a() {
        try {
            return VERSION.SDK_INT >= 21 ? ThreadLocalRandom.current().nextInt() : c().nextInt();
        } catch (RuntimeException unused) {
            return c().nextInt();
        }
    }

    private static Random c() {
        if (e == null) {
            synchronized (ace.class) {
                if (e == null) {
                    e = new Random();
                }
            }
        }
        return e;
    }

    public final void a(int i, int i2, long j) throws IOException {
        try {
            d.block();
            if (this.b.booleanValue() && a != null) {
                sb sbVar = new sb();
                sbVar.a = this.c.a.getPackageName();
                sbVar.b = Long.valueOf(j);
                ahn a2 = a.a(aar.a((aar) sbVar));
                a2.a(i2);
                a2.b(i);
                a2.a();
            }
        } catch (Exception unused) {
        }
    }
}
