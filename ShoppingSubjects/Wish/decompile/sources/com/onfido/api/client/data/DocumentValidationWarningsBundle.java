package com.onfido.api.client.data;

import com.google.gson.annotations.SerializedName;

public class DocumentValidationWarningsBundle {
    @SerializedName("detect_glare")
    private DocumentValidationWarning detectGlare;

    public DocumentValidationWarning getDetectGlare() {
        return this.detectGlare;
    }
}
