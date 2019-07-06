package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;

public final class SdkModule_ProvideNativeDetectorFactory implements b<NativeDetector> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;

    public SdkModule_ProvideNativeDetectorFactory(SdkModule sdkModule) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            return;
        }
        throw new AssertionError();
    }

    public static b<NativeDetector> create(SdkModule sdkModule) {
        return new SdkModule_ProvideNativeDetectorFactory(sdkModule);
    }

    public NativeDetector get() {
        return (NativeDetector) d.a(this.b.provideNativeDetector(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
