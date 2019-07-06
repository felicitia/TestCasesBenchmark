package com.threatmetrix.TrustDefender.internal;

import java.util.List;

public final class TN extends HF {

    /* renamed from: for reason: not valid java name */
    boolean f541for;

    /* renamed from: if reason: not valid java name */
    AP f542if = null;

    /* renamed from: int reason: not valid java name */
    int f543int;

    public TN(List<String> list) {
        super(list);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public final void m341new(int i, boolean z) {
        this.f543int = i;
        this.f541for = true;
        if (z && I.f375const) {
            this.f542if = new AP();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: new reason: not valid java name */
    public final JR m340new(Y y) {
        return new EQ(this, y, this.f176new, this.f542if);
    }
}
