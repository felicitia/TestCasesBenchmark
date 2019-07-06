package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.acj;
import com.google.android.gms.internal.ads.ack;
import java.util.concurrent.Callable;

final class al implements Callable<ack> {
    private final /* synthetic */ zzbp a;

    al(zzbp zzbp) {
        this.a = zzbp;
    }

    public final /* synthetic */ Object call() throws Exception {
        return new ack(acj.a(this.a.zzyf.zzcw, this.a.mContext, false));
    }
}
