package com.squareup.moshi;

import java.io.IOException;
import java.util.Date;

public final class Rfc3339DateJsonAdapter extends JsonAdapter<Date> {
    com.squareup.moshi.adapters.Rfc3339DateJsonAdapter delegate = new com.squareup.moshi.adapters.Rfc3339DateJsonAdapter();

    /* renamed from: a */
    public Date fromJson(JsonReader jsonReader) throws IOException {
        return this.delegate.fromJson(jsonReader);
    }

    /* renamed from: a */
    public void toJson(l lVar, Date date) throws IOException {
        this.delegate.toJson(lVar, date);
    }
}
