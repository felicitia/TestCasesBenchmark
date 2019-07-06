package com.google.gson.internal.a;

import com.google.gson.JsonSyntaxException;
import com.google.gson.a.a;
import com.google.gson.e;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.b;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* compiled from: TimeTypeAdapter */
public final class k extends q<Time> {
    public static final r a = new r() {
        public <T> q<T> a(e eVar, a<T> aVar) {
            if (aVar.a() == Time.class) {
                return new k();
            }
            return null;
        }
    };
    private final DateFormat b = new SimpleDateFormat("hh:mm:ss a");

    /* renamed from: a */
    public synchronized Time b(com.google.gson.stream.a aVar) throws IOException {
        if (aVar.f() == JsonToken.NULL) {
            aVar.j();
            return null;
        }
        try {
            return new Time(this.b.parse(aVar.h()).getTime());
        } catch (ParseException e) {
            throw new JsonSyntaxException((Throwable) e);
        }
    }

    public synchronized void a(b bVar, Time time) throws IOException {
        bVar.b(time == null ? null : this.b.format(time));
    }
}
