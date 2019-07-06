package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

public final class qh {
    private tp a;

    private qh(tp tpVar) {
        this.a = tpVar;
    }

    static final qh a(tp tpVar) throws GeneralSecurityException {
        if (tpVar != null && tpVar.c() > 0) {
            return new qh(tpVar);
        }
        throw new GeneralSecurityException("empty keyset");
    }

    /* access modifiers changed from: 0000 */
    public final tp a() {
        return this.a;
    }

    public final String toString() {
        return qp.a(this.a).toString();
    }
}
