package com.paypal.android.sdk.onetouch.core.fpti;

import java.util.Random;

class FptiToken {
    String mToken;
    private long mValidUntil;

    FptiToken() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mToken == null) {
            this.mValidUntil = currentTimeMillis;
        }
        if (this.mValidUntil + 1800000 > currentTimeMillis) {
            this.mValidUntil = currentTimeMillis + 1800000;
            Random random = new Random(this.mValidUntil);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                sb.append((char) ((Math.abs(random.nextInt()) % 10) + 48));
            }
            this.mToken = sb.toString();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isValid() {
        return this.mValidUntil > System.currentTimeMillis();
    }
}
