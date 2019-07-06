package com.onfido.api.client;

import com.onfido.api.client.data.Applicant;
import retrofit2.Call;

class ApplicantAPI {
    private final OnfidoService onfidoService;

    ApplicantAPI(OnfidoService onfidoService2) {
        this.onfidoService = onfidoService2;
    }

    /* access modifiers changed from: 0000 */
    public Call<Applicant> create(Applicant applicant) {
        return this.onfidoService.create(applicant);
    }
}
