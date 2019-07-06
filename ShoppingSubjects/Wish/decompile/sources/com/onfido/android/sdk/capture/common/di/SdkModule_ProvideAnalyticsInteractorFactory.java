package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.b.a.a;

public final class SdkModule_ProvideAnalyticsInteractorFactory implements b<AnalyticsInteractor> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<com.onfido.c.a.a> c;
    private final a<IdentityInteractor> d;

    public SdkModule_ProvideAnalyticsInteractorFactory(SdkModule sdkModule, a<com.onfido.c.a.a> aVar, a<IdentityInteractor> aVar2) {
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

    public static b<AnalyticsInteractor> create(SdkModule sdkModule, a<com.onfido.c.a.a> aVar, a<IdentityInteractor> aVar2) {
        return new SdkModule_ProvideAnalyticsInteractorFactory(sdkModule, aVar, aVar2);
    }

    public AnalyticsInteractor get() {
        return (AnalyticsInteractor) d.a(this.b.provideAnalyticsInteractor((com.onfido.c.a.a) this.c.get(), (IdentityInteractor) this.d.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
