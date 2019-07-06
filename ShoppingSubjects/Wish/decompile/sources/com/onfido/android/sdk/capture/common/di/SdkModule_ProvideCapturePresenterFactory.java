package com.onfido.android.sdk.capture.common.di;

import com.onfido.a.a.b;
import com.onfido.a.a.d;
import com.onfido.android.sdk.capture.analytics.AnalyticsInteractor;
import com.onfido.android.sdk.capture.barcode.BarcodeDetector;
import com.onfido.android.sdk.capture.common.permissions.RuntimePermissionsManager;
import com.onfido.android.sdk.capture.common.preferences.PreferencesManager;
import com.onfido.android.sdk.capture.native_detector.NativeDetector;
import com.onfido.android.sdk.capture.ui.camera.CapturePresenter;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInteractor;
import com.onfido.android.sdk.capture.validation.BackendValidationsManager;
import com.onfido.b.a.a;

public final class SdkModule_ProvideCapturePresenterFactory implements b<CapturePresenter> {
    static final /* synthetic */ boolean a = true;
    private final SdkModule b;
    private final a<NativeDetector> c;
    private final a<BarcodeDetector> d;
    private final a<AnalyticsInteractor> e;
    private final a<LivenessInteractor> f;
    private final a<PreferencesManager> g;
    private final a<BackendValidationsManager> h;
    private final a<RuntimePermissionsManager> i;

    public SdkModule_ProvideCapturePresenterFactory(SdkModule sdkModule, a<NativeDetector> aVar, a<BarcodeDetector> aVar2, a<AnalyticsInteractor> aVar3, a<LivenessInteractor> aVar4, a<PreferencesManager> aVar5, a<BackendValidationsManager> aVar6, a<RuntimePermissionsManager> aVar7) {
        if (a || sdkModule != null) {
            this.b = sdkModule;
            if (a || aVar != null) {
                this.c = aVar;
                if (a || aVar2 != null) {
                    this.d = aVar2;
                    if (a || aVar3 != null) {
                        this.e = aVar3;
                        if (a || aVar4 != null) {
                            this.f = aVar4;
                            if (a || aVar5 != null) {
                                this.g = aVar5;
                                if (a || aVar6 != null) {
                                    this.h = aVar6;
                                    if (a || aVar7 != null) {
                                        this.i = aVar7;
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
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static b<CapturePresenter> create(SdkModule sdkModule, a<NativeDetector> aVar, a<BarcodeDetector> aVar2, a<AnalyticsInteractor> aVar3, a<LivenessInteractor> aVar4, a<PreferencesManager> aVar5, a<BackendValidationsManager> aVar6, a<RuntimePermissionsManager> aVar7) {
        SdkModule_ProvideCapturePresenterFactory sdkModule_ProvideCapturePresenterFactory = new SdkModule_ProvideCapturePresenterFactory(sdkModule, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7);
        return sdkModule_ProvideCapturePresenterFactory;
    }

    public CapturePresenter get() {
        return (CapturePresenter) d.a(this.b.provideCapturePresenter((NativeDetector) this.c.get(), (BarcodeDetector) this.d.get(), (AnalyticsInteractor) this.e.get(), (LivenessInteractor) this.f.get(), (PreferencesManager) this.g.get(), (BackendValidationsManager) this.h.get(), (RuntimePermissionsManager) this.i.get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
