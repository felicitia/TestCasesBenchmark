package com.onfido.android.sdk.capture;

import com.onfido.android.sdk.capture.utils.CountryCode;
import com.onfido.android.sdk.capture.validation.PostCaptureDocumentValidation;
import com.onfido.api.client.data.DocSide;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public enum DocumentType {
    PASSPORT("passport", R.string.onfido_label_doc_type_passport, R.string.onfido_label_doc_type_passport, R.string.onfido_label_doc_type_passport_up, R.drawable.onfido_passport),
    NATIONAL_IDENTITY_CARD("national_id", R.string.onfido_label_doc_type_id_card, R.string.onfido_label_doc_type_id_card, R.string.onfido_label_doc_type_id_card_up, R.drawable.onfido_national_id),
    DRIVING_LICENCE("driving_licence", R.string.onfido_label_doc_type_driving_license, R.string.onfido_label_doc_type_driving_license_short, R.string.onfido_label_doc_type_driving_license_up, R.drawable.onfido_driving_licence),
    RESIDENCE_PERMIT("residence_permit", R.string.onfido_label_doc_type_id_card, R.string.onfido_label_doc_type_residence_permit_short, R.string.onfido_label_doc_type_residence_permit_up, R.drawable.onfido_ic_residence_card);
    
    private final String b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;

    protected DocumentType(String str, int i, int i2, int i3, int i4) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        this.b = str;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
    }

    public final boolean backSideCaptureNeeded(CountryCode countryCode) {
        boolean z = true;
        switch (this) {
            case DRIVING_LICENCE:
            case RESIDENCE_PERMIT:
                break;
            case NATIONAL_IDENTITY_CARD:
                z = true ^ Intrinsics.areEqual(countryCode, CountryCode.IN);
                break;
            default:
                return false;
        }
        return z;
    }

    public final boolean countrySelectionNeeded() {
        return !Intrinsics.areEqual(this, PASSPORT);
    }

    public final String getId() {
        return this.b;
    }

    public final int getUppercaseLabel() {
        return this.e;
    }

    public final List<PostCaptureDocumentValidation> postCaptureValidationsNeeded(DocSide docSide, CountryCode countryCode) {
        Intrinsics.checkParameterIsNotNull(docSide, "documentSide");
        if (!Intrinsics.areEqual(this, DRIVING_LICENCE) || !Intrinsics.areEqual(countryCode, CountryCode.US) || !Intrinsics.areEqual(docSide, DocSide.BACK)) {
            return CollectionsKt.listOf((Object) PostCaptureDocumentValidation.BLUR);
        }
        return CollectionsKt.listOf((Object[]) new PostCaptureDocumentValidation[]{PostCaptureDocumentValidation.BLUR, PostCaptureDocumentValidation.BARCODE});
    }
}
