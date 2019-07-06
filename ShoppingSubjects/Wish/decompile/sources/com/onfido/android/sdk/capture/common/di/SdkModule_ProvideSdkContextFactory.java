package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.a.a.b;
import com.onfido.a.a.d;

public final class SdkModule_ProvideSdkContextFactory implements b<Context> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;

    public SdkModule_ProvideSdkContextFactory(SdkModule sdkModule) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            return;
        }
        throw new AssertionError();
    }

    public static b<Context> create(SdkModule sdkModule) {
        return new SdkModule_ProvideSdkContextFactory(sdkModule);
    }

    public Context get() {
        return (Context) d.a(this.b.provideSdkContext(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
