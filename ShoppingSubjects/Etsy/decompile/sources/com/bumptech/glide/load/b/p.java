package com.bumptech.glide.load.b;

import android.net.Uri;
import android.text.TextUtils;
import com.bumptech.glide.load.a.c;
import java.io.File;

/* compiled from: StringLoader */
public class p<T> implements l<String, T> {
    private final l<Uri, T> a;

    public p(l<Uri, T> lVar) {
        this.a = lVar;
    }

    public c<T> a(String str, int i, int i2) {
        Uri uri;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("/")) {
            uri = a(str);
        } else {
            Uri parse = Uri.parse(str);
            uri = parse.getScheme() == null ? a(str) : parse;
        }
        return this.a.a(uri, i, i2);
    }

    private static Uri a(String str) {
        return Uri.fromFile(new File(str));
    }
}
