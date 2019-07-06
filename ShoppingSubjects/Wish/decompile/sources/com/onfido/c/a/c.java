package com.onfido.c.a;

import android.content.SharedPreferences;

public class c {
    private final SharedPreferences a;
    private final String b;
    private final boolean c;

    public c(SharedPreferences sharedPreferences, String str, boolean z) {
        this.a = sharedPreferences;
        this.b = str;
        this.c = z;
    }

    public void a(boolean z) {
        this.a.edit().putBoolean(this.b, z).apply();
    }

    public boolean a() {
        return this.a.getBoolean(this.b, this.c);
    }
}
