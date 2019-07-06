package com.onfido.android.sdk.capture.ui.country_selection;

import com.onfido.android.sdk.capture.utils.CountryCode;

public interface CountrySelectionListener {
    void onAlternativeDocumentSelected();

    void onCountrySelected(CountryCode countryCode);
}
