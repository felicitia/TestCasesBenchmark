package com.bumptech.glide.load.resource;

import java.io.OutputStream;

/* compiled from: NullEncoder */
public class a<T> implements com.bumptech.glide.load.a<T> {
    private static final a<?> a = new a<>();

    public String a() {
        return "";
    }

    public boolean a(T t, OutputStream outputStream) {
        return false;
    }

    public static <T> com.bumptech.glide.load.a<T> b() {
        return a;
    }
}
