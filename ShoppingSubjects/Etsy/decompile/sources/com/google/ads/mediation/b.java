package com.google.ads.mediation;

import com.google.ads.mediation.MediationServerParameters;

@Deprecated
public interface b<ADDITIONAL_PARAMETERS, SERVER_PARAMETERS extends MediationServerParameters> {
    void destroy();

    Class<ADDITIONAL_PARAMETERS> getAdditionalParametersType();

    Class<SERVER_PARAMETERS> getServerParametersType();
}
