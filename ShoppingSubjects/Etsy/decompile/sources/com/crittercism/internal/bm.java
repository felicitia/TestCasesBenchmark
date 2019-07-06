package com.crittercism.internal;

public final class bm {
    public int a = (bn.d - 1);
    public int b = bl.OK.ordinal();

    public bm(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public bm(Throwable th) {
        if (th != null) {
            this.a = bn.a(th);
            if (this.a == bn.d - 1) {
                this.b = bl.a(th).C;
                return;
            }
            this.b = Integer.parseInt(th.getMessage());
        }
    }
}
