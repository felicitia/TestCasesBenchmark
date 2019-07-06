package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallengesProvider;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInfoPresenter;
import com.onfido.b.a.a;

public final class SdkModule_ProvideLivenessInfoPresenterFactory implements b<LivenessInfoPresenter> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<AnalyticsInteractor> c;
    private final a<LivenessChallengesProvider> d;
    private final a<IdentityInteractor> e;

    public SdkModule_ProvideLivenessInfoPresenterFactory(SdkModule sdkModule, a<AnalyticsInteractor> aVar, a<LivenessChallengesProvider> aVar2, a<IdentityInteractor> aVar3) {
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

    public static b<LivenessInfoPresenter> create(SdkModule sdkModule, a<AnalyticsInteractor> aVar, a<LivenessChallengesProvider> aVar2, a<IdentityInteractor> aVar3) {
        return new SdkModule_ProvideLivenessInfoPresenterFactory(sdkModule, aVar, aVar2, aVar3);
    }

    public LivenessInfoPresenter get() {
        return (LivenessInfoPresenter) d.a(this.b.provideLivenessInfoPresenter((AnalyticsInteractor) this.c.get(), (LivenessChallengesProvider) this.d.get(), (IdentityInteractor) this.e.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
