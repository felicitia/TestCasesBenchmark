package com.google.android.gms.internal.ads;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class abn implements Runnable {
    private abn() {
    }

    public final void run() {
        try {
            abl.c = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused) {
        } catch (Throwable th) {
            abl.a.countDown();
            throw th;
        }
        abl.a.countDown();
    }
}
