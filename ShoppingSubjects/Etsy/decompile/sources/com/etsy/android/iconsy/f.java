package com.etsy.android.iconsy;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map.Entry;

/* compiled from: TypefaceCache */
public class f {
    private static f b = new f();
    private final HashMap<String, Typeface> a = new HashMap<>();

    private f() {
    }

    public static f a() {
        return b;
    }

    public Typeface a(a aVar) {
        return (Typeface) this.a.get(aVar.getClass().getName());
    }

    /* access modifiers changed from: protected */
    public void a(Context context) {
        for (Entry entry : c.a().entrySet()) {
            this.a.put(((Class) entry.getKey()).getName(), Typeface.createFromAsset(context.getAssets(), (String) entry.getValue()));
        }
    }
}
