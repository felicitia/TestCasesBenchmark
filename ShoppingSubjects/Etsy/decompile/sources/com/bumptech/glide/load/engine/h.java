package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.b;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/* compiled from: OriginalKey */
class h implements b {
    private final String a;
    private final b b;

    public h(String str, b bVar) {
        this.a = str;
        this.b = bVar;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        return this.a.equals(hVar.a) && this.b.equals(hVar.b);
    }

    public int hashCode() {
        return (31 * this.a.hashCode()) + this.b.hashCode();
    }

    public void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
        messageDigest.update(this.a.getBytes("UTF-8"));
        this.b.a(messageDigest);
    }
}
