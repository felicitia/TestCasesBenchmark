package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.a;
import com.google.android.gms.tasks.f;

final class zzcg implements a<Boolean, Void> {
    zzcg() {
    }

    public final /* synthetic */ Object then(@NonNull f fVar) throws Exception {
        if (((Boolean) fVar.d()).booleanValue()) {
            return null;
        }
        throw new ApiException(new Status(13, "listener already unregistered"));
    }
}
