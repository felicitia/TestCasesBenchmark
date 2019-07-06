package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.common.preferences.PreferencesManager;

public final class SdkModule_ProvideSharedPreferencesFactory implements b<PreferencesManager> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;

    public SdkModule_ProvideSharedPreferencesFactory(SdkModule sdkModule) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            return;
        }
        throw new AssertionError();
    }

    public static b<PreferencesManager> create(SdkModule sdkModule) {
        return new SdkModule_ProvideSharedPreferencesFactory(sdkModule);
    }

    public PreferencesManager get() {
        return (PreferencesManager) d.a(this.b.provideSharedPreferences(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
