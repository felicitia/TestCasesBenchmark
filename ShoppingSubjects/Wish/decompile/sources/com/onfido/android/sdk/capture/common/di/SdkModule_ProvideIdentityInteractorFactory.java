package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.b.a.a;

public final class SdkModule_ProvideIdentityInteractorFactory implements b<IdentityInteractor> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<Context> c;
    private final a<NativeDetector> d;

    public SdkModule_ProvideIdentityInteractorFactory(SdkModule sdkModule, a<Context> aVar, a<NativeDetector> aVar2) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            if (a || aVar != null) {
                this.c = aVar;
                if (a || aVar2 != null) {
                    this.d = aVar2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static b<IdentityInteractor> create(SdkModule sdkModule, a<Context> aVar, a<NativeDetector> aVar2) {
        return new SdkModule_ProvideIdentityInteractorFactory(sdkModule, aVar, aVar2);
    }

    public IdentityInteractor get() {
        return (IdentityInteractor) d.a(this.b.provideIdentityInteractor((Context) this.c.get(), (NativeDetector) this.d.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
