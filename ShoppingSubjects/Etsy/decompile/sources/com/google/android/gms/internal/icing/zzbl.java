package com.google.android.gms.internal.icing;

final class zzbl extends zzbp {
    private final int zzdu;
    private final int zzdv;

    zzbl(byte[] bArr, int i, int i2) {
        super(bArr);
        zzb(i, i + i2, bArr.length);
        this.zzdu = i;
        this.zzdv = i2;
    }

    public final int size() {
        return this.zzdv;
    }

    public final byte zzi(int i) {
        int size = size();
        if (((size - (i + 1)) | i) >= 0) {
            return this.zzdx[this.zzdu + i];
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
    public final int zzx() {
        return this.zzdu;
    }
}
