package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

abstract class vk implements vf {
    static final int[] a = a(ByteBuffer.wrap(new byte[]{101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107}));
    final ve b;
    private final int c;

    vk(byte[] bArr, int i) throws InvalidKeyException {
        if (bArr.length != 32) {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
        this.b = ve.a(bArr);
        this.c = i;
    }

    static int a(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    static int[] a(ByteBuffer byteBuffer) {
        IntBuffer asIntBuffer = byteBuffer.order(ByteOrder.LITTLE_ENDIAN).asIntBuffer();
        int[] iArr = new int[asIntBuffer.remaining()];
        asIntBuffer.get(iArr);
        return iArr;
    }

    /* access modifiers changed from: 0000 */
    public abstract int a();

    /* access modifiers changed from: 0000 */
    public abstract ByteBuffer a(byte[] bArr, int i);

    /* access modifiers changed from: 0000 */
    public final void a(ByteBuffer byteBuffer, byte[] bArr) throws GeneralSecurityException {
        if (byteBuffer.remaining() - a() < bArr.length) {
            throw new IllegalArgumentException("Given ByteBuffer output is too small");
        }
        byte[] a2 = vi.a(a());
        byteBuffer.put(a2);
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        int remaining = wrap.remaining();
        int i = (remaining / 64) + 1;
        for (int i2 = 0; i2 < i; i2++) {
            ByteBuffer a3 = a(a2, this.c + i2);
            if (i2 == i - 1) {
                ui.a(byteBuffer, wrap, a3, remaining % 64);
            } else {
                ui.a(byteBuffer, wrap, a3, 64);
            }
        }
    }

    public final byte[] a(byte[] bArr) throws GeneralSecurityException {
        int length = bArr.length;
        a();
        if (length > 2147483635) {
            throw new GeneralSecurityException("plaintext too long");
        }
        ByteBuffer allocate = ByteBuffer.allocate(a() + bArr.length);
        a(allocate, bArr);
        return allocate.array();
    }
}
