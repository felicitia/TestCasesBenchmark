package com.google.android.gms.internal.icing;

abstract class db {
    db() {
    }

    /* access modifiers changed from: 0000 */
    public abstract int a(int i, byte[] bArr, int i2, int i3);

    /* access modifiers changed from: 0000 */
    public abstract int a(CharSequence charSequence, byte[] bArr, int i, int i2);

    /* access modifiers changed from: 0000 */
    public final boolean a(byte[] bArr, int i, int i2) {
        return a(0, bArr, i, i2) == 0;
    }
}
