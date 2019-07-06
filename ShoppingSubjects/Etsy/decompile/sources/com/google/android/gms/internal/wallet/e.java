package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;

final class e extends zzaf {
    private final ResultHolder<BooleanResult> a;

    public e(ResultHolder<BooleanResult> resultHolder) {
        this.a = resultHolder;
    }

    public final void zza(Status status, boolean z, Bundle bundle) {
        this.a.setResult(new BooleanResult(status, z));
    }
}
