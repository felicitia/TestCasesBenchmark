package com.google.gson.internal.a;

import com.google.gson.JsonSyntaxException;
import com.google.gson.a.a;
import com.google.gson.e;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* compiled from: SqlDateTypeAdapter */
public final class j extends q<Date> {
    public static final r a = new r() {
        public <T> q<T> a(e eVar, a<T> aVar) {
            if (aVar.a() == Date.class) {
                return new j();
            }
            return null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("MMM d, yyyy");

    /* renamed from: a */
    public synchronized Date b(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.f() == JsonToken.NULL) {
            aVar.j();
            return null;
        }
        try {
            return new Date(this.b.parse(aVar.h()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException((Throwable) e);
        }
    }

    public synchronized void a(b bVar, Date date) throws IOException {
        bVar.b(date == null ? null : this.b.format(date));
    }
}
