package com.onfido.android.sdk.capture.common.di;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.analytics.IdentityInteractor;
import com.onfido.android.sdk.capture.analytics.SegmentInteractor;
import com.onfido.android.sdk.capture.barcode.BarcodeDetector;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.common.preferences.PreferencesManager;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.DocumentSelectionPresenter;
import com.onfido.android.sdk.capture.ui.FaceIntroPresenter;
import com.onfido.android.sdk.capture.ui.FinalScreenPresenter;
import com.onfido.android.sdk.capture.ui.OnfidoPresenter;
import com.onfido.android.sdk.capture.ui.WelcomePresenter;
import com.onfido.android.sdk.capture.ui.camera.CapturePresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessChallengesProvider;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInfoPresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInteractor;
import com.onfido.android.sdk.capture.ui.camera.liveness.TimestampProvider;
import com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionPresenter;
import com.onfido.android.sdk.capture.ui.country_selection.GetCountriesForDocumentTypeUseCase;
import com.onfido.android.sdk.capture.ui.country_selection.GetCurrentCountryCodeUseCase;
import com.onfido.android.sdk.capture.utils.ImageUtils;
import com.onfido.android.sdk.capture.validation.BackendValidationsManager;
import com.onfido.c.a.a;
import com.onfido.c.a.a.C0009a;
import com.onfido.d.b.b;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

public class SdkModule {
    private final Context a;

    public SdkModule(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.a = context;
    }

    public a provideAnalyticsApi(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        a a2 = new C0009a(context, "YOUR_RELEASE_SEGMENT_TOKEN").a(new SdkModule$provideAnalyticsApi$1()).a();
        Intrinsics.checkExpressionValueIsNotNull(a2, "Analytics.Builder(contex…\n                .build()");
        return a2;
    }

    public AnalyticsInteractor provideAnalyticsInteractor(a aVar, IdentityInteractor identityInteractor) {
        Intrinsics.checkParameterIsNotNull(aVar, "analyticsApi");
        Intrinsics.checkParameterIsNotNull(identityInteractor, "identityInteractor");
        return new SegmentInteractor(aVar, identityInteractor);
    }

    public BackendValidationsManager provideBackendValidationsManager(NativeDetector nativeDetector) {
        Intrinsics.checkParameterIsNotNull(nativeDetector, "nativeDetector");
        return new BackendValidationsManager(nativeDetector);
    }

    public BarcodeDetector provideBarcodeDetector(ImageUtils imageUtils) {
        Intrinsics.checkParameterIsNotNull(imageUtils, "imageUtils");
        return new BarcodeDetector(new b(), imageUtils);
    }

    public CapturePresenter provideCapturePresenter(NativeDetector nativeDetector, BarcodeDetector barcodeDetector, AnalyticsInteractor analyticsInteractor, LivenessInteractor livenessInteractor, PreferencesManager preferencesManager, BackendValidationsManager backendValidationsManager, RuntimePermissionsManager runtimePermissionsManager) {
        NativeDetector nativeDetector2 = nativeDetector;
        Intrinsics.checkParameterIsNotNull(nativeDetector2, "nativeDetector");
        BarcodeDetector barcodeDetector2 = barcodeDetector;
        Intrinsics.checkParameterIsNotNull(barcodeDetector2, "barcodeDetector");
        AnalyticsInteractor analyticsInteractor2 = analyticsInteractor;
        Intrinsics.checkParameterIsNotNull(analyticsInteractor2, "analyticsInteractor");
        LivenessInteractor livenessInteractor2 = livenessInteractor;
        Intrinsics.checkParameterIsNotNull(livenessInteractor2, "livenessInteractor");
        PreferencesManager preferencesManager2 = preferencesManager;
        Intrinsics.checkParameterIsNotNull(preferencesManager2, "preferencesManager");
        BackendValidationsManager backendValidationsManager2 = backendValidationsManager;
        Intrinsics.checkParameterIsNotNull(backendValidationsManager2, "backendValidationsManager");
        RuntimePermissionsManager runtimePermissionsManager2 = runtimePermissionsManager;
        Intrinsics.checkParameterIsNotNull(runtimePermissionsManager2, "runtimePermissionsManager");
        CapturePresenter capturePresenter = new CapturePresenter(nativeDetector2, barcodeDetector2, analyticsInteractor2, livenessInteractor2, preferencesManager2, backendValidationsManager2, runtimePermissionsManager2, null, 128, null);
        return capturePresenter;
    }

    public CountrySelectionPresenter provideCountrySelectionPresenter(GetCountriesForDocumentTypeUseCase getCountriesForDocumentTypeUseCase, AnalyticsInteractor analyticsInteractor, GetCurrentCountryCodeUseCase getCurrentCountryCodeUseCase) {
        Intrinsics.checkParameterIsNotNull(getCountriesForDocumentTypeUseCase, "getCountriesForDocumentTypeUseCase");
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        Intrinsics.checkParameterIsNotNull(getCurrentCountryCodeUseCase, "getCurrentCountryCodeUseCase");
        return new CountrySelectionPresenter(getCountriesForDocumentTypeUseCase, analyticsInteractor, getCurrentCountryCodeUseCase);
    }

    public DocumentSelectionPresenter provideDocumentSelectionPresenter(AnalyticsInteractor analyticsInteractor) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        return new DocumentSelectionPresenter(analyticsInteractor);
    }

    public FaceIntroPresenter provideFaceIntroPresenter(AnalyticsInteractor analyticsInteractor) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        return new FaceIntroPresenter(analyticsInteractor);
    }

    public FinalScreenPresenter provideFinalScreenPresenter(AnalyticsInteractor analyticsInteractor) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        return new FinalScreenPresenter(analyticsInteractor);
    }

    public GetCountriesForDocumentTypeUseCase provideGetCountriesForDocumentTypeUseCase() {
        return new GetCountriesForDocumentTypeUseCase(this.a);
    }

    public GetCurrentCountryCodeUseCase provideGetCurrentCountryCodeUseCase(TelephonyManager telephonyManager) {
        Intrinsics.checkParameterIsNotNull(telephonyManager, "telephonyManager");
        return new GetCurrentCountryCodeUseCase(telephonyManager);
    }

    public IdentityInteractor provideIdentityInteractor(Context context, NativeDetector nativeDetector) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(nativeDetector, "nativeDetector");
        return new IdentityInteractor(context, nativeDetector);
    }

    public ImageUtils provideImageUtils() {
        return new ImageUtils();
    }

    public LivenessChallengesProvider provideLivenessChallengesProvider(TimestampProvider timestampProvider) {
        Intrinsics.checkParameterIsNotNull(timestampProvider, "timestampProvider");
        return new LivenessChallengesProvider(timestampProvider);
    }

    public LivenessInfoPresenter provideLivenessInfoPresenter(AnalyticsInteractor analyticsInteractor, LivenessChallengesProvider livenessChallengesProvider, IdentityInteractor identityInteractor) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        Intrinsics.checkParameterIsNotNull(livenessChallengesProvider, "livenessChallengesProvider");
        Intrinsics.checkParameterIsNotNull(identityInteractor, "identityInteractor");
        return new LivenessInfoPresenter(analyticsInteractor, livenessChallengesProvider, identityInteractor);
    }

    public LivenessInteractor provideLivenessInteractor(LivenessChallengesProvider livenessChallengesProvider) {
        Intrinsics.checkParameterIsNotNull(livenessChallengesProvider, "livenessChallengesProvider");
        return new LivenessInteractor(livenessChallengesProvider);
    }

    public NativeDetector provideNativeDetector() {
        return new NativeDetector();
    }

    public OnfidoPresenter provideOnfidoPresenter(AnalyticsInteractor analyticsInteractor, PreferencesManager preferencesManager) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        Intrinsics.checkParameterIsNotNull(preferencesManager, "preferencesManager");
        return new OnfidoPresenter(analyticsInteractor, preferencesManager);
    }

    public RuntimePermissionsManager provideRuntimePermissionsManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new RuntimePermissionsManager(context);
    }

    public Context provideSdkContext() {
        return this.a;
    }

    public PreferencesManager provideSharedPreferences() {
        return new PreferencesManager(this.a);
    }

    public TelephonyManager provideTelephonyManager(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("phone");
        if (systemService != null) {
            return (TelephonyManager) systemService;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.telephony.TelephonyManager");
    }

    public TimestampProvider provideTimestampProvider() {
        return new SdkModule$provideTimestampProvider$1();
    }

    public WelcomePresenter provideWelcomePresenter(AnalyticsInteractor analyticsInteractor) {
        Intrinsics.checkParameterIsNotNull(analyticsInteractor, "analyticsInteractor");
        return new WelcomePresenter(analyticsInteractor);
    }
}
