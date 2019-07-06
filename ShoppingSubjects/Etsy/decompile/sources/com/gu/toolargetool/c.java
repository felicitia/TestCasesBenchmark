package com.gu.toolargetool;

import android.util.Log;

/* compiled from: Logger */
public class c {
    public static c d = new c(3, "TooLargeTool");
    protected int b;
    protected String c;

    public c(int i, String str) {
        this.b = i;
        this.c = str;
    }

    public void a(String str) {
        Log.println(this.b, this.c, str);
    }

    public void a(Exception exc) {
        Log.println(this.b, this.c, exc.getMessage());
    }
}
