package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.IntentSender.SendIntentException;

public class ResolvableApiException extends ApiException {
    public ResolvableApiException(Status status) {
        super(status);
    }

    public void startResolutionForResult(Activity activity, int i) throws SendIntentException {
        this.mStatus.startResolutionForResult(activity, i);
    }
}
