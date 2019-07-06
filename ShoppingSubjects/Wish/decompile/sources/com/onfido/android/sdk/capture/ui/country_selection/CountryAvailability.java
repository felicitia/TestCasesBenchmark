package com.onfido.android.sdk.capture.ui.country_selection;

import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.jvm.internal.Intrinsics;

public final class CountryAvailability extends BaseAdapterItem {
    private final CountryCode a;
    private final boolean b;

    public CountryAvailability(CountryCode countryCode, boolean z) {
        Intrinsics.checkParameterIsNotNull(countryCode, "countryCode");
        this.a = countryCode;
        this.b = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CountryAvailability) {
            CountryAvailability countryAvailability = (CountryAvailability) obj;
            if (Intrinsics.areEqual(this.a, countryAvailability.a)) {
                if (this.b == countryAvailability.b) {
                    return true;
                }
            }
        }
        return false;
    }

    public final CountryCode getCountryCode() {
        return this.a;
    }

    public int hashCode() {
        CountryCode countryCode = this.a;
        int hashCode = (countryCode != null ? countryCode.hashCode() : 0) * 31;
        boolean z = this.b;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CountryAvailability(countryCode=");
        sb.append(this.a);
        sb.append(", availability=");
        sb.append(this.b);
        sb.append(")");
        return sb.toString();
    }
}
