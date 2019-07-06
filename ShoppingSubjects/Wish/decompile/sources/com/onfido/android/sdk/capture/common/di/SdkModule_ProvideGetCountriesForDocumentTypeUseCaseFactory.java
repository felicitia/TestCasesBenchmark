package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.ui.country_selection.GetCountriesForDocumentTypeUseCase;

public final class SdkModule_ProvideGetCountriesForDocumentTypeUseCaseFactory implements b<GetCountriesForDocumentTypeUseCase> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;

    public SdkModule_ProvideGetCountriesForDocumentTypeUseCaseFactory(SdkModule sdkModule) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            return;
        }
        throw new AssertionError();
    }

    public static b<GetCountriesForDocumentTypeUseCase> create(SdkModule sdkModule) {
        return new SdkModule_ProvideGetCountriesForDocumentTypeUseCaseFactory(sdkModule);
    }

    public GetCountriesForDocumentTypeUseCase get() {
        return (GetCountriesForDocumentTypeUseCase) d.a(this.b.provideGetCountriesForDocumentTypeUseCase(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
