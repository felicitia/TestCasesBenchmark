package com.onfido.android.sdk.capture.ui.country_selection;

import kotlin.jvm.internal.Intrinsics;

public final class CountrySelectionSeparator extends BaseAdapterItem {
    private final CountrySelectionSeparatorType a;

    public CountrySelectionSeparator(CountrySelectionSeparatorType countrySelectionSeparatorType) {
        Intrinsics.checkParameterIsNotNull(countrySelectionSeparatorType, "type");
        this.a = countrySelectionSeparatorType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r1.a, ((com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionSeparator) r2).a) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionSeparator
            if (r0 == 0) goto L_0x0013
            com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionSeparator r2 = (com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionSeparator) r2
            com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionSeparatorType r0 = r1.a
            com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionSeparatorType r2 = r2.a
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.ui.country_selection.CountrySelectionSeparator.equals(java.lang.Object):boolean");
    }

    public final CountrySelectionSeparatorType getType() {
        return this.a;
    }

    public int hashCode() {
        CountrySelectionSeparatorType countrySelectionSeparatorType = this.a;
        if (countrySelectionSeparatorType != null) {
            return countrySelectionSeparatorType.hashCode();
        }
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CountrySelectionSeparator(type=");
        sb.append(this.a);
        sb.append(")");
        return sb.toString();
    }
}
