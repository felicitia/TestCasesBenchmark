package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.ui.camera.liveness.TimestampProvider;

public final class SdkModule_ProvideTimestampProviderFactory implements b<TimestampProvider> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;

    public SdkModule_ProvideTimestampProviderFactory(SdkModule sdkModule) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            return;
        }
        throw new AssertionError();
    }

    public static b<TimestampProvider> create(SdkModule sdkModule) {
        return new SdkModule_ProvideTimestampProviderFactory(sdkModule);
    }

    public TimestampProvider get() {
        return (TimestampProvider) d.a(this.b.provideTimestampProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
