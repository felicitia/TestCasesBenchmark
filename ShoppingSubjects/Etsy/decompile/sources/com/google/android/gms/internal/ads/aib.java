package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aib extends aam<aib> {
    private Integer a;
    private Integer b;

    public aib() {
        this.a = null;
        this.b = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0073, code lost:
        throw new java.lang.IllegalArgumentException(r5.toString());
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.aib a(com.google.android.gms.internal.ads.aaj r7) throws java.io.IOException {
        /*
            r6 = this;
        L_0x0000:
            int r0 = r7.a()
            if (r0 == 0) goto L_0x007b
            r1 = 8
            r2 = 2
            if (r0 == r1) goto L_0x0048
            r1 = 16
            if (r0 == r1) goto L_0x0016
            boolean r0 = super.a(r7, r0)
            if (r0 != 0) goto L_0x0000
            return r6
        L_0x0016:
            int r1 = r7.j()
            int r3 = r7.g()     // Catch:{ IllegalArgumentException -> 0x0074 }
            if (r3 < 0) goto L_0x0023
            if (r3 > r2) goto L_0x0023
            goto L_0x0028
        L_0x0023:
            r2 = 4
            if (r3 < r2) goto L_0x002f
            if (r3 > r2) goto L_0x002f
        L_0x0028:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            r6.b = r2     // Catch:{ IllegalArgumentException -> 0x0074 }
            goto L_0x0000
        L_0x002f:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0074 }
            r4 = 51
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0074 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x0074 }
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            java.lang.String r3 = " is not a valid enum CellularNetworkType"
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            java.lang.String r3 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x0074 }
            r2.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            throw r2     // Catch:{ IllegalArgumentException -> 0x0074 }
        L_0x0048:
            int r1 = r7.j()
            int r3 = r7.g()     // Catch:{ IllegalArgumentException -> 0x0074 }
            if (r3 < 0) goto L_0x005b
            if (r3 > r2) goto L_0x005b
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            r6.a = r2     // Catch:{ IllegalArgumentException -> 0x0074 }
            goto L_0x0000
        L_0x005b:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0074 }
            r4 = 43
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0074 }
            r5.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x0074 }
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            java.lang.String r3 = " is not a valid enum NetworkType"
            r5.append(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            java.lang.String r3 = r5.toString()     // Catch:{ IllegalArgumentException -> 0x0074 }
            r2.<init>(r3)     // Catch:{ IllegalArgumentException -> 0x0074 }
            throw r2     // Catch:{ IllegalArgumentException -> 0x0074 }
        L_0x0074:
            r7.e(r1)
            r6.a(r7, r0)
            goto L_0x0000
        L_0x007b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.aib.a(com.google.android.gms.internal.ads.aaj):com.google.android.gms.internal.ads.aib");
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.a != null) {
            a2 += aal.b(1, this.a.intValue());
        }
        return this.b != null ? a2 + aal.b(2, this.b.intValue()) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.a != null) {
            aal.a(1, this.a.intValue());
        }
        if (this.b != null) {
            aal.a(2, this.b.intValue());
        }
        super.a(aal);
    }
}
