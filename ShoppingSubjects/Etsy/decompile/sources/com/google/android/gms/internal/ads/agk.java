package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@bu
public abstract class agk {
    @Nullable
    private static MessageDigest b;
    protected Object a = new Object();

    /* access modifiers changed from: protected */
    @Nullable
    public final MessageDigest a() {
        synchronized (this.a) {
            if (b != null) {
                MessageDigest messageDigest = b;
                return messageDigest;
            }
            for (int i = 0; i < 2; i++) {
                try {
                    b = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            MessageDigest messageDigest2 = b;
            return messageDigest2;
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract byte[] a(String str);
}
