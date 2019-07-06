package com.onfido.android.sdk.capture.ui;

import com.onfido.api.client.OnfidoAPI.Listener;
import com.onfido.api.client.data.Applicant;
import com.onfido.api.client.data.ErrorData;
import kotlin.jvm.internal.Intrinsics;

public final class OnfidoPresenter$issueCreateApplicantRequest$1 implements Listener<Applicant> {
    final /* synthetic */ OnfidoPresenter a;

    OnfidoPresenter$issueCreateApplicantRequest$1(OnfidoPresenter onfidoPresenter) {
        this.a = onfidoPresenter;
    }

    public void onError(int i, String str, ErrorData errorData) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(errorData, "errorData");
        OnfidoView access$getView$p = OnfidoPresenter.access$getView$p(this.a);
        String message = errorData.getMessage();
        Intrinsics.checkExpressionValueIsNotNull(message, "errorData.message");
        access$getView$p.showError(message);
    }

    public void onFailure() {
        OnfidoPresenter.access$getView$p(this.a).showNetworkError();
    }

    public void onSuccess(Applicant applicant) {
        Intrinsics.checkParameterIsNotNull(applicant, "savedApplicant");
        this.a.b = applicant;
        this.a.resetAction();
    }
}
