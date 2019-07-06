package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.onfido.a.a;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.barcode.BarcodeDetector;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.common.preferences.PreferencesManager;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.DocumentSelectionFragment;
import com.onfido.android.sdk.capture.ui.DocumentSelectionFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.DocumentSelectionPresenter;
import com.onfido.android.sdk.capture.ui.FaceIntroFragment;
import com.onfido.android.sdk.capture.ui.FaceIntroFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.FaceIntroPresenter;
import com.onfido.android.sdk.capture.ui.FinalScreenFragment;
import com.onfido.android.sdk.capture.ui.FinalScreenFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.FinalScreenPresenter;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.OnfidoActivity_MembersInjector;
import com.onfido.android.sdk.capture.ui.OnfidoPresenter;
import com.onfido.android.sdk.capture.ui.WelcomeFragment;
import com.onfido.android.sdk.capture.ui.WelcomeFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.WelcomePresenter;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.CapturePresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallengesProvider;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInfoFragment;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInfoFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInfoPresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInteractor;
import com.onfido.android.sdk.capture.ui.camera.liveness.TimestampProvider;
import com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionFragment;
import com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionFragment_MembersInjector;
import com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionPresenter;
import com.onfido.android.sdk.capture.ui.country_selection.GetCountriesForDocumentTypeUseCase;
import com.onfido.android.sdk.capture.ui.country_selection.GetCurrentCountryCodeUseCase;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.validation.BackendValidationsManager;

public final class DaggerSdkComponent implements SdkComponent {
    static final /* synthetic */ boolean a = true;
    private a<CaptureActivity> A;
    private com.onfido.b.a.a<FaceIntroPresenter> B;
    private a<FaceIntroFragment> C;
    private com.onfido.b.a.a<FinalScreenPresenter> D;
    private a<FinalScreenFragment> E;
    private com.onfido.b.a.a<LivenessInfoPresenter> F;
    private a<LivenessInfoFragment> G;
    private com.onfido.b.a.a<Context> b;
    private com.onfido.b.a.a<com.onfido.c.a.a> c;
    private com.onfido.b.a.a<NativeDetector> d;
    private com.onfido.b.a.a<IdentityInteractor> e;
    private com.onfido.b.a.a<AnalyticsInteractor> f;
    private com.onfido.b.a.a<PreferencesManager> g;
    private com.onfido.b.a.a<OnfidoPresenter> h;
    private a<OnfidoActivity> i;
    private com.onfido.b.a.a<WelcomePresenter> j;
    private a<WelcomeFragment> k;
    private com.onfido.b.a.a<DocumentSelectionPresenter> l;
    private a<DocumentSelectionFragment> m;
    private com.onfido.b.a.a<GetCountriesForDocumentTypeUseCase> n;
    private com.onfido.b.a.a<TelephonyManager> o;
    private com.onfido.b.a.a<GetCurrentCountryCodeUseCase> p;
    private com.onfido.b.a.a<CountrySelectionPresenter> q;
    private a<CountrySelectionFragment> r;
    private com.onfido.b.a.a<ImageUtils> s;
    private com.onfido.b.a.a<BarcodeDetector> t;
    private com.onfido.b.a.a<TimestampProvider> u;
    private com.onfido.b.a.a<LivenessChallengesProvider> v;
    private com.onfido.b.a.a<LivenessInteractor> w;
    private com.onfido.b.a.a<BackendValidationsManager> x;
    private com.onfido.b.a.a<RuntimePermissionsManager> y;
    private com.onfido.b.a.a<CapturePresenter> z;

    public static final class Builder {
        /* access modifiers changed from: private */
        public SdkModule a;

        private Builder() {
        }

        public SdkComponent build() {
            if (this.a != null) {
                return new DaggerSdkComponent(this);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(SdkModule.class.getCanonicalName());
            sb.append(" must be set");
            throw new IllegalStateException(sb.toString());
        }

        public Builder sdkModule(SdkModule sdkModule) {
            this.a = (SdkModule) d.a(sdkModule);
            return this;
        }
    }

    private DaggerSdkComponent(Builder builder) {
        if (a || builder != null) {
            a(builder);
            return;
        }
        throw new AssertionError();
    }

    private void a(Builder builder) {
        this.b = com.onfido.a.a.a.a(SdkModule_ProvideSdkContextFactory.create(builder.a));
        this.c = com.onfido.a.a.a.a(SdkModule_ProvideAnalyticsApiFactory.create(builder.a, this.b));
        this.d = com.onfido.a.a.a.a(SdkModule_ProvideNativeDetectorFactory.create(builder.a));
        this.e = com.onfido.a.a.a.a(SdkModule_ProvideIdentityInteractorFactory.create(builder.a, this.b, this.d));
        this.f = com.onfido.a.a.a.a(SdkModule_ProvideAnalyticsInteractorFactory.create(builder.a, this.c, this.e));
        this.g = SdkModule_ProvideSharedPreferencesFactory.create(builder.a);
        this.h = SdkModule_ProvideOnfidoPresenterFactory.create(builder.a, this.f, this.g);
        this.i = OnfidoActivity_MembersInjector.create(this.h);
        this.j = SdkModule_ProvideWelcomePresenterFactory.create(builder.a, this.f);
        this.k = WelcomeFragment_MembersInjector.create(this.j);
        this.l = SdkModule_ProvideDocumentSelectionPresenterFactory.create(builder.a, this.f);
        this.m = DocumentSelectionFragment_MembersInjector.create(this.l);
        this.n = SdkModule_ProvideGetCountriesForDocumentTypeUseCaseFactory.create(builder.a);
        this.o = SdkModule_ProvideTelephonyManagerFactory.create(builder.a, this.b);
        this.p = SdkModule_ProvideGetCurrentCountryCodeUseCaseFactory.create(builder.a, this.o);
        this.q = SdkModule_ProvideCountrySelectionPresenterFactory.create(builder.a, this.n, this.f, this.p);
        this.r = CountrySelectionFragment_MembersInjector.create(this.q);
        this.s = SdkModule_ProvideImageUtilsFactory.create(builder.a);
        this.t = SdkModule_ProvideBarcodeDetectorFactory.create(builder.a, this.s);
        this.u = SdkModule_ProvideTimestampProviderFactory.create(builder.a);
        this.v = SdkModule_ProvideLivenessChallengesProviderFactory.create(builder.a, this.u);
        this.w = SdkModule_ProvideLivenessInteractorFactory.create(builder.a, this.v);
        this.x = SdkModule_ProvideBackendValidationsManagerFactory.create(builder.a, this.d);
        this.y = SdkModule_ProvideRuntimePermissionsManagerFactory.create(builder.a, this.b);
        this.z = SdkModule_ProvideCapturePresenterFactory.create(builder.a, this.d, this.t, this.f, this.w, this.g, this.x, this.y);
        this.A = CaptureActivity_MembersInjector.create(this.z, this.s, this.e);
        this.B = SdkModule_ProvideFaceIntroPresenterFactory.create(builder.a, this.f);
        this.C = FaceIntroFragment_MembersInjector.create(this.B);
        this.D = SdkModule_ProvideFinalScreenPresenterFactory.create(builder.a, this.f);
        this.E = FinalScreenFragment_MembersInjector.create(this.D);
        this.F = SdkModule_ProvideLivenessInfoPresenterFactory.create(builder.a, this.f, this.v, this.e);
        this.G = LivenessInfoFragment_MembersInjector.create(this.F);
    }

    public static Builder builder() {
        return new Builder();
    }

    public void inject(DocumentSelectionFragment documentSelectionFragment) {
        this.m.injectMembers(documentSelectionFragment);
    }

    public void inject(FaceIntroFragment faceIntroFragment) {
        this.C.injectMembers(faceIntroFragment);
    }

    public void inject(FinalScreenFragment finalScreenFragment) {
        this.E.injectMembers(finalScreenFragment);
    }

    public void inject(OnfidoActivity onfidoActivity) {
        this.i.injectMembers(onfidoActivity);
    }

    public void inject(WelcomeFragment welcomeFragment) {
        this.k.injectMembers(welcomeFragment);
    }

    public void inject(CaptureActivity captureActivity) {
        this.A.injectMembers(captureActivity);
    }

    public void inject(LivenessInfoFragment livenessInfoFragment) {
        this.G.injectMembers(livenessInfoFragment);
    }

    public void inject(CountrySelectionFragment countrySelectionFragment) {
        this.r.injectMembers(countrySelectionFragment);
    }
}
