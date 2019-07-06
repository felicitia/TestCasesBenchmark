package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.e;
import com.bumptech.glide.load.engine.i;
import java.io.OutputStream;

/* compiled from: NullResourceEncoder */
public class b<T> implements e<T> {
    private static final b<?> a = new b<>();

    public String a() {
        return "";
    }

    public boolean a(i<T> iVar, OutputStream outputStream) {
        return false;
    }

    public static <T> b<T> b() {
        return a;
    }
}
