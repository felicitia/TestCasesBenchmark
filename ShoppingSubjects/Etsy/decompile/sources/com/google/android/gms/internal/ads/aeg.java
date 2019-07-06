package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class aeg {
    private static final String a = "aeg";
    private final acy b;
    private final String c;
    private final String d;
    private final int e = 2;
    private volatile Method f = null;
    private final Class<?>[] g;
    private CountDownLatch h = new CountDownLatch(1);

    public aeg(acy acy, String str, String str2, Class<?>... clsArr) {
        this.b = acy;
        this.c = str;
        this.d = str2;
        this.g = clsArr;
        this.b.c().submit(new aeh(this));
    }

    private final String a(byte[] bArr, String str) throws zzcl, UnsupportedEncodingException {
        return new String(this.b.e().a(bArr, str), "UTF-8");
    }

    /* access modifiers changed from: private */
    public final void b() {
        try {
            Class loadClass = this.b.d().loadClass(a(this.b.f(), this.c));
            if (loadClass != null) {
                this.f = loadClass.getMethod(a(this.b.f(), this.d), this.g);
                Method method = this.f;
            }
        } catch (zzcl | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException unused) {
        } catch (Throwable th) {
            this.h.countDown();
            throw th;
        }
        this.h.countDown();
    }

    public final Method a() {
        if (this.f != null) {
            return this.f;
        }
        try {
            if (!this.h.await(2, TimeUnit.SECONDS)) {
                return null;
            }
            return this.f;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}
