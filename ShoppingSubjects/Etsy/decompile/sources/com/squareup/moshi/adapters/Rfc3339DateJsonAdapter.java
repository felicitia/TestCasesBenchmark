package com.squareup.moshi.adapters;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.l;
import java.io.IOException;
import java.util.Date;

public final class Rfc3339DateJsonAdapter extends JsonAdapter<Date> {
    /* renamed from: a */
    public synchronized Date fromJson(JsonReader jsonReader) throws IOException {
        return a.a(jsonReader.k());
    }

    /* renamed from: a */
    public synchronized void toJson(l lVar, Date date) throws IOException {
        lVar.c(a.a(date));
    }
}
