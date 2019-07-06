package com.onfido.android.sdk.capture.common.di;

import android.telephony.TelephonyManager;
import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.ui.country_selection.GetCurrentCountryCodeUseCase;
import com.onfido.b.a.a;

public final class SdkModule_ProvideGetCurrentCountryCodeUseCaseFactory implements b<GetCurrentCountryCodeUseCase> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<TelephonyManager> c;

    public SdkModule_ProvideGetCurrentCountryCodeUseCaseFactory(SdkModule sdkModule, a<TelephonyManager> aVar) {
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

    public static b<GetCurrentCountryCodeUseCase> create(SdkModule sdkModule, a<TelephonyManager> aVar) {
        return new SdkModule_ProvideGetCurrentCountryCodeUseCaseFactory(sdkModule, aVar);
    }

    public GetCurrentCountryCodeUseCase get() {
        return (GetCurrentCountryCodeUseCase) d.a(this.b.provideGetCurrentCountryCodeUseCase((TelephonyManager) this.c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
