package com.google.zxing.qrcode.decoder;

public enum ErrorCorrectionLevel {
    L(1),
    M(0),
    Q(3),
    H(2);
    
    private static final ErrorCorrectionLevel[] a = null;
    private final int bits;

    static {
        a = new ErrorCorrectionLevel[]{M, L, H, Q};
    }

    private ErrorCorrectionLevel(int i) {
        this.bits = i;
    }

    public int getBits() {
        return this.bits;
    }

    public static ErrorCorrectionLevel forBits(int i) {
        if (i >= 0 && i < a.length) {
            return a[i];
        }
        throw new IllegalArgumentException();
    }
}
