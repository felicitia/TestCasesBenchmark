package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.internal.ads.nn;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class q implements ae<nn> {
    private final /* synthetic */ CountDownLatch a;

    q(CountDownLatch countDownLatch) {
        this.a = countDownLatch;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        this.a.countDown();
        nnVar.getView().setVisibility(0);
    }
}
