package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallengesProvider;
import com.onfido.android.sdk.capture.ui.camera.liveness.TimestampProvider;
import com.onfido.b.a.a;

public final class SdkModule_ProvideLivenessChallengesProviderFactory implements b<LivenessChallengesProvider> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<TimestampProvider> c;

    public SdkModule_ProvideLivenessChallengesProviderFactory(SdkModule sdkModule, a<TimestampProvider> aVar) {
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

    public static b<LivenessChallengesProvider> create(SdkModule sdkModule, a<TimestampProvider> aVar) {
        return new SdkModule_ProvideLivenessChallengesProviderFactory(sdkModule, aVar);
    }

    public LivenessChallengesProvider get() {
        return (LivenessChallengesProvider) d.a(this.b.provideLivenessChallengesProvider((TimestampProvider) this.c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
