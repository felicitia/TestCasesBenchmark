package com.onfido.api.client.data;

import com.google.gson.annotations.SerializedName;

public class DocumentUpload {
    private String id;
    private DocType type;
    @SerializedName("sdk_warnings")
    private DocumentValidationWarningsBundle warningsBundle;

    public String getId() {
        return this.id;
    }

    public DocType getType() {
        return this.type;
    }

    public DocumentValidationWarningsBundle getWarningsBundle() {
        return this.warningsBundle;
    }
}
