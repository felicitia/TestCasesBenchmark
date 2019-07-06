package com.google.android.gms.internal.ads;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class abl {
    static CountDownLatch a = new CountDownLatch(1);
    private static boolean b = false;
    /* access modifiers changed from: private */
    public static MessageDigest c;
    private static final Object d = new Object();
    private static final Object e = new Object();

    private static vy a(long j) {
        vy vyVar = new vy();
        vyVar.k = Long.valueOf(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
        return vyVar;
    }

    static String a(vy vyVar, String str) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] bArr;
        byte[] a2 = aar.a((aar) vyVar);
        if (((Boolean) ajh.f().a(akl.bL)).booleanValue()) {
            Vector a3 = a(a2, 255);
            if (a3 == null || a3.size() == 0) {
                bArr = a(aar.a((aar) a((long) PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)), str, true);
            } else {
                abh abh = new abh();
                abh.a = new byte[a3.size()][];
                Iterator it = a3.iterator();
                int i = 0;
                while (it.hasNext()) {
                    int i2 = i + 1;
                    abh.a[i] = a((byte[]) it.next(), str, false);
                    i = i2;
                }
                abh.b = a(a2);
                bArr = aar.a((aar) abh);
            }
        } else if (ade.a == null) {
            throw new GeneralSecurityException();
        } else {
            byte[] a4 = ade.a.a(a2, str != null ? str.getBytes() : new byte[0]);
            abh abh2 = new abh();
            abh2.a = new byte[][]{a4};
            abh2.c = Integer.valueOf(2);
            bArr = aar.a((aar) abh2);
        }
        return abj.a(bArr, true);
    }

    private static Vector<byte[]> a(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = ((bArr.length + 255) - 1) / 255;
        Vector<byte[]> vector = new Vector<>();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * 255;
            try {
                vector.add(Arrays.copyOfRange(bArr, i3, bArr.length - i3 > 255 ? i3 + 255 : bArr.length));
                i2++;
            } catch (IndexOutOfBoundsException unused) {
                return null;
            }
        }
        return vector;
    }

    static void a() {
        synchronized (e) {
            if (!b) {
                b = true;
                new Thread(new abn()).start();
            }
        }
    }

    public static byte[] a(byte[] bArr) throws NoSuchAlgorithmException {
        byte[] digest;
        synchronized (d) {
            MessageDigest b2 = b();
            if (b2 == null) {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
            b2.reset();
            b2.update(bArr);
            digest = c.digest();
        }
        return digest;
    }

    private static byte[] a(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        ByteBuffer put;
        int i = z ? 239 : 255;
        if (bArr.length > i) {
            bArr = aar.a((aar) a((long) PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM));
        }
        if (bArr.length < i) {
            byte[] bArr2 = new byte[(i - bArr.length)];
            new SecureRandom().nextBytes(bArr2);
            put = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).put(bArr2);
        } else {
            put = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr);
        }
        byte[] array = put.array();
        if (z) {
            array = ByteBuffer.allocate(256).put(a(array)).put(array).array();
        }
        byte[] bArr3 = new byte[256];
        for (abq a2 : new abo().cN) {
            a2.a(array, bArr3);
        }
        if (str != null && str.length() > 0) {
            if (str.length() > 32) {
                str = str.substring(0, 32);
            }
            new vu(str.getBytes("UTF-8")).a(bArr3);
        }
        return bArr3;
    }

    private static MessageDigest b() {
        boolean z;
        a();
        try {
            z = a.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            z = false;
        }
        if (z && c != null) {
            return c;
        }
        return null;
    }
}
