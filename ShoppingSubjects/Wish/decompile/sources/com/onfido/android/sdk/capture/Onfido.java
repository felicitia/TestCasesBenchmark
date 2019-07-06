package com.onfido.android.sdk.capture;

import android.app.Activity;
import android.content.Intent;
import com.onfido.android.sdk.capture.errors.OnfidoException;
import com.onfido.android.sdk.capture.upload.Captures;
import com.onfido.api.client.data.Applicant;

public interface Onfido {

    public interface OnfidoResultListener {
        void onError(OnfidoException onfidoException, Applicant applicant);

        void userCompleted(Applicant applicant, Captures captures);

        void userExited(ExitCode exitCode, Applicant applicant);
    }

    void handleActivityResult(int i, Intent intent, OnfidoResultListener onfidoResultListener);

    void startActivityForResult(Activity activity, int i, OnfidoConfig onfidoConfig);
}
