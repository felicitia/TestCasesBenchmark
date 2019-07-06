package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionPresenter;
import com.onfido.android.sdk.capture.ui.country_selection.GetCountriesForDocumentTypeUseCase;
import com.onfido.android.sdk.capture.ui.country_selection.GetCurrentCountryCodeUseCase;
import com.onfido.b.a.a;

public final class SdkModule_ProvideCountrySelectionPresenterFactory implements b<CountrySelectionPresenter> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<GetCountriesForDocumentTypeUseCase> c;
    private final a<AnalyticsInteractor> d;
    private final a<GetCurrentCountryCodeUseCase> e;

    public SdkModule_ProvideCountrySelectionPresenterFactory(SdkModule sdkModule, a<GetCountriesForDocumentTypeUseCase> aVar, a<AnalyticsInteractor> aVar2, a<GetCurrentCountryCodeUseCase> aVar3) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            if (a || aVar != null) {
                this.c = aVar;
                if (a || aVar2 != null) {
                    this.d = aVar2;
                    if (a || aVar3 != null) {
                        this.e = aVar3;
                        return;
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static b<CountrySelectionPresenter> create(SdkModule sdkModule, a<GetCountriesForDocumentTypeUseCase> aVar, a<AnalyticsInteractor> aVar2, a<GetCurrentCountryCodeUseCase> aVar3) {
        return new SdkModule_ProvideCountrySelectionPresenterFactory(sdkModule, aVar, aVar2, aVar3);
    }

    public CountrySelectionPresenter get() {
        return (CountrySelectionPresenter) d.a(this.b.provideCountrySelectionPresenter((GetCountriesForDocumentTypeUseCase) this.c.get(), (AnalyticsInteractor) this.d.get(), (GetCurrentCountryCodeUseCase) this.e.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
