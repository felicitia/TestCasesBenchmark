package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.ui.WelcomePresenter;
import com.onfido.b.a.a;

public final class SdkModule_ProvideWelcomePresenterFactory implements b<WelcomePresenter> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<AnalyticsInteractor> c;

    public SdkModule_ProvideWelcomePresenterFactory(SdkModule sdkModule, a<AnalyticsInteractor> aVar) {
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

    public static b<WelcomePresenter> create(SdkModule sdkModule, a<AnalyticsInteractor> aVar) {
        return new SdkModule_ProvideWelcomePresenterFactory(sdkModule, aVar);
    }

    public WelcomePresenter get() {
        return (WelcomePresenter) d.a(this.b.provideWelcomePresenter((AnalyticsInteractor) this.c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
