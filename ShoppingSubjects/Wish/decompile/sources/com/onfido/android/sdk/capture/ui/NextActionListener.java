package com.onfido.android.sdk.capture.ui;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.utils.CountryCode;

public interface NextActionListener {
    void onAlternateDocumentSelected();

    void onCountrySelected(DocumentType documentType, CountryCode countryCode);

    void onDocumentTypeSelected(DocumentType documentType);

    void onNextClicked();

    void onPreviousClicked();
}
