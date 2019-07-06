package com.crittercism.internal;

import android.content.Context;
import java.io.IOException;

public final class ct {
    public am a;
    public d b;
    public c c;
    public String d;

    public ct(am amVar, d dVar, Context context) {
        this.a = amVar;
        this.b = dVar;
        this.c = new c(context);
        this.d = a(context);
    }

    private static String a(Context context) {
        try {
            return cn.a(context, "www/error.js");
        } catch (IOException e) {
            cm.b((Throwable) e);
            return "";
        }
    }
}
