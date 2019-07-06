package com.paypal.android.sdk.onetouch.core.metadata;

public class ag {
    public static final ag a = new ag();
    private static final String b = "ag";
    private byte[] c;

    private ag() {
        this.c = null;
        this.c = null;
    }

    public ag(String str) {
        this.c = null;
        try {
            this.c = new byte[((str.length() + 1) / 2)];
            int length = str.length() - 1;
            int i = 0;
            while (length >= 0) {
                int i2 = length - 1;
                if (i2 < 0) {
                    i2 = 0;
                }
                this.c[i] = (byte) Integer.parseInt(str.substring(i2, length + 1), 16);
                length -= 2;
                i++;
            }
        } catch (Exception e) {
            ai.a(b, "PPRiskDataBitSet initialize failed", (Throwable) e);
            this.c = null;
        }
    }

    public final boolean a(ah ahVar) {
        int a2 = ahVar.a() / 8;
        if (this.c == null) {
            return true;
        }
        if (a2 >= this.c.length) {
            return false;
        }
        byte a3 = (byte) (1 << (ahVar.a() % 8));
        return (this.c[a2] & a3) == a3;
    }
}
