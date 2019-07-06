package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.common.util.Predicate;

final /* synthetic */ class aoy implements Predicate {
    private final ae a;

    aoy(ae aeVar) {
        this.a = aeVar;
    }

    public final boolean apply(Object obj) {
        ae aeVar = (ae) obj;
        return (aeVar instanceof ape) && ((ape) aeVar).a.equals(this.a);
    }
}
