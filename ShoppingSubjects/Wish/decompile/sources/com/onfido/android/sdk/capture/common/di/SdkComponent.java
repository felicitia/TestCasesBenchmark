package com.onfido.android.sdk.capture.common.di;

import com.onfido.android.sdk.capture.ui.DocumentSelectionFragment;
import com.onfido.android.sdk.capture.ui.FaceIntroFragment;
import com.onfido.android.sdk.capture.ui.FinalScreenFragment;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import com.onfido.android.sdk.capture.ui.WelcomeFragment;
import com.onfido.android.sdk.capture.ui.camera.CaptureActivity;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessInfoFragment;
import com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionFragment;

public interface SdkComponent {
    void inject(DocumentSelectionFragment documentSelectionFragment);

    void inject(FaceIntroFragment faceIntroFragment);

    void inject(FinalScreenFragment finalScreenFragment);

    void inject(OnfidoActivity onfidoActivity);

    void inject(WelcomeFragment welcomeFragment);

    void inject(CaptureActivity captureActivity);

    void inject(LivenessInfoFragment livenessInfoFragment);

    void inject(CountrySelectionFragment countrySelectionFragment);
}
