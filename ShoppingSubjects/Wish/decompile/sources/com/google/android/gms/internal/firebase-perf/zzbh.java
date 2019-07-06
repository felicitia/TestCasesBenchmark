package com.google.android.gms.internal.firebase-perf;

final class zzbh extends zzbm {
    private final int zzht;
    private final int zzhu;

    zzbh(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzht = i;
        this.zzhu = i2;
    }

    public final byte zzj(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzhw[this.zzht + i];
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

    public final int size() {
        return this.zzhu;
    }

    /* access modifiers changed from: protected */
    public final int zzbp() {
        return this.zzht;
    }
}
