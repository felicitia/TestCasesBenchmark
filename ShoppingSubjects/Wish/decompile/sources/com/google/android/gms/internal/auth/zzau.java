package com.google.android.gms.internal.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

final class zzau extends zzam {
    private ResultHolder<Status> zzdu;

    zzau(ResultHolder<Status> resultHolder) {
        this.zzdu = resultHolder;
    }

    public final void zze(Status status) {
        this.zzdu.setResult(status);
    }
}
