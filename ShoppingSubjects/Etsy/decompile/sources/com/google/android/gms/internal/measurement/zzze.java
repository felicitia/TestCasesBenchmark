package com.google.android.gms.internal.measurement;

final class zzze extends zzzh {
    private final int zzbth;
    private final int zzbti;

    zzze(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzbth = i;
        this.zzbti = i2;
    }

    public final int size() {
        return this.zzbti;
    }

    public final byte zzae(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzbtj[this.zzbth + i];
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(22);
            sb.append("Index < 0: ");
            sb.append(i);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Index > length: ");
        sb2.append(i);
        sb2.append(", ");
        sb2.append(size);
        throw new ArrayIndexOutOfBoundsException(sb2.toString());
    }

    /* access modifiers changed from: protected */
    public final int zztn() {
        return this.zzbth;
    }
}
