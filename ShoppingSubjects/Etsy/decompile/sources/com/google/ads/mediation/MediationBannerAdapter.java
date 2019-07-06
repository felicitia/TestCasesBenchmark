package com.google.ads.mediation;

import android.app.Activity;
import android.view.View;
import com.google.ads.a;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.e;

@Deprecated
public interface MediationBannerAdapter<ADDITIONAL_PARAMETERS extends e, SERVER_PARAMETERS extends MediationServerParameters> extends b<ADDITIONAL_PARAMETERS, SERVER_PARAMETERS> {
    View getBannerView();

    void requestBannerAd(c cVar, Activity activity, SERVER_PARAMETERS server_parameters, a aVar, a aVar2, ADDITIONAL_PARAMETERS additional_parameters);
}
