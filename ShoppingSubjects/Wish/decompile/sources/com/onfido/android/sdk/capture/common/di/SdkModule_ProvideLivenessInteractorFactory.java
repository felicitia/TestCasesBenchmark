package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallengesProvider;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInteractor;
import com.onfido.b.a.a;

public final class SdkModule_ProvideLivenessInteractorFactory implements b<LivenessInteractor> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<LivenessChallengesProvider> c;

    public SdkModule_ProvideLivenessInteractorFactory(SdkModule sdkModule, a<LivenessChallengesProvider> aVar) {
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

    public static b<LivenessInteractor> create(SdkModule sdkModule, a<LivenessChallengesProvider> aVar) {
        return new SdkModule_ProvideLivenessInteractorFactory(sdkModule, aVar);
    }

    public LivenessInteractor get() {
        return (LivenessInteractor) d.a(this.b.provideLivenessInteractor((LivenessChallengesProvider) this.c.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
