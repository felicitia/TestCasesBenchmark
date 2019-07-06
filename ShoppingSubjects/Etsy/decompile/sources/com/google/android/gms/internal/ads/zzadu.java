package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;

@bu
@VisibleForTesting
final class zzadu extends Exception {
    private final int mErrorCode;

    public zzadu(String str, int i) {
        super(str);
        this.mErrorCode = i;
    }

    public final int getErrorCode() {
        return this.mErrorCode;
    }
}
