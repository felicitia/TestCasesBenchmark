package com.onfido.android.sdk.capture.ui.country_selection;

public final class UnsupportedSuggestedCountryException extends Throwable {
    public UnsupportedSuggestedCountryException() {
        super("The suggested country is not supported for the selected document type");
    }
}
