package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.ui.camera.liveness.TimestampProvider;

public final class SdkModule$provideTimestampProvider$1 implements TimestampProvider {
    SdkModule$provideTimestampProvider$1() {
    }

    public long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
}
