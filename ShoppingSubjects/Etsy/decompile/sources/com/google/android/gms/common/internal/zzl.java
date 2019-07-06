package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.StatusListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.common.internal.PendingResultUtil.StatusConverter;
import com.google.android.gms.tasks.g;
import java.util.concurrent.TimeUnit;

final class zzl implements StatusListener {
    private final /* synthetic */ PendingResult zzuo;
    private final /* synthetic */ g zzup;
    private final /* synthetic */ ResultConverter zzuq;
    private final /* synthetic */ StatusConverter zzur;

    zzl(PendingResult pendingResult, g gVar, ResultConverter resultConverter, StatusConverter statusConverter) {
        this.zzuo = pendingResult;
        this.zzup = gVar;
        this.zzuq = resultConverter;
        this.zzur = statusConverter;
    }

    public final void onComplete(Status status) {
        if (status.isSuccess()) {
            this.zzup.a(this.zzuq.convert(this.zzuo.await(0, TimeUnit.MILLISECONDS)));
            return;
        }
        this.zzup.a((Exception) this.zzur.convert(status));
    }
}
