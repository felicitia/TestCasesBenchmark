package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.utils.ImageUtils;

public final class SdkModule_ProvideImageUtilsFactory implements b<ImageUtils> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;

    public SdkModule_ProvideImageUtilsFactory(SdkModule sdkModule) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            return;
        }
        throw new AssertionError();
    }

    public static b<ImageUtils> create(SdkModule sdkModule) {
        return new SdkModule_ProvideImageUtilsFactory(sdkModule);
    }

    public ImageUtils get() {
        return (ImageUtils) d.a(this.b.provideImageUtils(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
