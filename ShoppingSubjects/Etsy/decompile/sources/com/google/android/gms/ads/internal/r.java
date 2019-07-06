package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.nn;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class r implements ae<nn> {
    private final /* synthetic */ CountDownLatch a;

    r(CountDownLatch countDownLatch) {
        this.a = countDownLatch;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        nn nnVar = (nn) obj;
        gv.e("Adapter returned an ad, but assets substitution failed");
        this.a.countDown();
        nnVar.destroy();
    }
}
