package com.onfido.android.sdk.capture.ui.country_selection;

import com.onfido.a.a;

public final class CountrySelectionFragment_MembersInjector implements a<CountrySelectionFragment> {
    static final /* synthetic */ boolean a = true;
    private final com.onfido.b.a.a<CountrySelectionPresenter> b;

    public CountrySelectionFragment_MembersInjector(com.onfido.b.a.a<CountrySelectionPresenter> aVar) {
        if (a || aVar != null) {
            this.b = aVar;
            return;
        }
        throw new AssertionError();
    }

    public static a<CountrySelectionFragment> create(com.onfido.b.a.a<CountrySelectionPresenter> aVar) {
        return new CountrySelectionFragment_MembersInjector(aVar);
    }

    public void injectMembers(CountrySelectionFragment countrySelectionFragment) {
        if (countrySelectionFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        countrySelectionFragment.presenter = (CountrySelectionPresenter) this.b.get();
    }
}
