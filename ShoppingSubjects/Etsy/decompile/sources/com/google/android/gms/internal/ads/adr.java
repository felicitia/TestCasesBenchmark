package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class adr extends aei {
    public adr(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 24);
    }

    private final void c() {
        AdvertisingIdClient m = this.a.m();
        if (m != null) {
            try {
                Info info = m.getInfo();
                String a = adg.a(info.getId());
                if (a != null) {
                    synchronized (this.b) {
                        this.b.T = a;
                        this.b.V = Boolean.valueOf(info.isLimitAdTrackingEnabled());
                        this.b.U = Integer.valueOf(5);
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        if (this.a.g()) {
            c();
            return;
        }
        synchronized (this.b) {
            this.b.T = (String) this.c.invoke(null, new Object[]{this.a.a()});
        }
    }

    public final Void b() throws Exception {
        if (this.a.b()) {
            return super.call();
        }
        if (this.a.g()) {
            c();
        }
        return null;
    }

    public final /* synthetic */ Object call() throws Exception {
        return call();
    }
}
