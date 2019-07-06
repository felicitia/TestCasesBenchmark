package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.a;
import com.google.android.gms.tasks.f;
import com.google.android.gms.tasks.g;

@KeepForSdk
public class TaskUtil {
    @KeepForSdk
    public static void setResultOrApiException(Status status, g<Void> gVar) {
        setResultOrApiException(status, null, gVar);
    }

    @KeepForSdk
    public static <TResult> void setResultOrApiException(Status status, TResult tresult, g<TResult> gVar) {
        if (status.isSuccess()) {
            gVar.a(tresult);
        } else {
            gVar.a((Exception) new ApiException(status));
        }
    }

    @KeepForSdk
    @Deprecated
    public static f<Void> toVoidTaskThatFailsOnFalse(f<Boolean> fVar) {
        return fVar.a((a<TResult, TContinuationResult>) new zzcg<TResult,TContinuationResult>());
    }
}
