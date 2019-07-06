package com.onfido.api.client.data;

import com.google.gson.JsonObject;

public class LiveVideoLanguage extends JsonSerializable {
    private String languageCode;
    private String source;

    public LiveVideoLanguage(String str, String str2) {
        this.source = str;
        this.languageCode = str2;
    }

    public JsonObject serialise() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("source", this.source);
        jsonObject.addProperty("language_code", this.languageCode);
        return jsonObject;
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LiveVideoLanguage)) {
            return false;
        }
        LiveVideoLanguage liveVideoLanguage = (LiveVideoLanguage) obj;
        if (this.source.equals(liveVideoLanguage.source) && this.languageCode.equals(liveVideoLanguage.languageCode)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.source != null ? this.source.hashCode() : 0) * 31;
        if (this.languageCode != null) {
            i = this.languageCode.hashCode();
        }
        return hashCode + i;
    }
}
