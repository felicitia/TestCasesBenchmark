package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.common.preferences.PreferencesManager;
import com.onfido.android.sdk.capture.ui.OnfidoPresenter;
import com.onfido.b.a.a;

public final class SdkModule_ProvideOnfidoPresenterFactory implements b<OnfidoPresenter> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<AnalyticsInteractor> c;
    private final a<PreferencesManager> d;

    public SdkModule_ProvideOnfidoPresenterFactory(SdkModule sdkModule, a<AnalyticsInteractor> aVar, a<PreferencesManager> aVar2) {
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

    public static b<OnfidoPresenter> create(SdkModule sdkModule, a<AnalyticsInteractor> aVar, a<PreferencesManager> aVar2) {
        return new SdkModule_ProvideOnfidoPresenterFactory(sdkModule, aVar, aVar2);
    }

    public OnfidoPresenter get() {
        return (OnfidoPresenter) d.a(this.b.provideOnfidoPresenter((AnalyticsInteractor) this.c.get(), (PreferencesManager) this.d.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
