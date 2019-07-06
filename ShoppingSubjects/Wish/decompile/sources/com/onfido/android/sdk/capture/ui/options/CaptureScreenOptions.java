package com.onfido.android.sdk.capture.ui.options;

import com.onfido.android.sdk.capture.DocumentType;
import com.onfido.android.sdk.capture.utils.CountryCode;
import kotlin.jvm.internal.Intrinsics;

public final class CaptureScreenOptions extends BaseOptions {
    private final DocumentType a;
    private final CountryCode b;

    public CaptureScreenOptions(DocumentType documentType, CountryCode countryCode) {
        Intrinsics.checkParameterIsNotNull(documentType, "documentType");
        this.a = documentType;
        this.b = countryCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CaptureScreenOptions)) {
            return false;
        }
        CaptureScreenOptions captureScreenOptions = (CaptureScreenOptions) obj;
        return !(Intrinsics.areEqual(this.a, captureScreenOptions.a) ^ true) && !(Intrinsics.areEqual(this.b, captureScreenOptions.b) ^ true);
    }

    public final CountryCode getCountry() {
        return this.b;
    }

    public final DocumentType getDocumentType() {
        return this.a;
    }

    public int hashCode() {
        int hashCode = this.a.hashCode();
        CountryCode countryCode = this.b;
        return countryCode != null ? (hashCode * 31) + countryCode.hashCode() : hashCode;
    }
}
