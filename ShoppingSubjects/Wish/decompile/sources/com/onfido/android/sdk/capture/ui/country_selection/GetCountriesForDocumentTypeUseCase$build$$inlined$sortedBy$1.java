package com.onfido.android.sdk.capture.ui.country_selection;

import com.onfido.android.sdk.capture.utils.CountryCode;
import java.util.Comparator;
import kotlin.Pair;
import kotlin.comparisons.ComparisonsKt;

public final class GetCountriesForDocumentTypeUseCase$build$$inlined$sortedBy$1<T> implements Comparator<T> {
    public final int compare(T t, T t2) {
        return ComparisonsKt.compareValues(((CountryCode) ((Pair) t).getFirst()).getDisplayName(), ((CountryCode) ((Pair) t2).getFirst()).getDisplayName());
    }
}
