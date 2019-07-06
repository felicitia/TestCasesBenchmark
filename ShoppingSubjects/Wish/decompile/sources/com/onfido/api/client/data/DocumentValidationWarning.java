package com.onfido.api.client.data;

import com.google.gson.annotations.SerializedName;

public class DocumentValidationWarning {
    @SerializedName("valid")
    private boolean isValid;

    public boolean isValid() {
        return this.isValid;
    }
}
