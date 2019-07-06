package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.validation.BackendValidationsManager;
import com.onfido.b.a.a;

public final class SdkModule_ProvideBackendValidationsManagerFactory implements b<BackendValidationsManager> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<NativeDetector> c;

    public SdkModule_ProvideBackendValidationsManagerFactory(SdkModule sdkModule, a<NativeDetector> aVar) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            if (a || aVar != null) {
                this.c = aVar;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static b<BackendValidationsManager> create(SdkModule sdkModule, a<NativeDetector> aVar) {
        return new SdkModule_ProvideBackendValidationsManagerFactory(sdkModule, aVar);
    }

    public BackendValidationsManager get() {
        return (BackendValidationsManager) d.a(this.b.provideBackendValidationsManager((NativeDetector) this.c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
