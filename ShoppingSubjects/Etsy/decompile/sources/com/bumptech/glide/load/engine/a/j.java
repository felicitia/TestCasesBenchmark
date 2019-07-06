package com.bumptech.glide.load.engine.a;

import com.bumptech.glide.g.e;
import com.bumptech.glide.g.h;
import com.bumptech.glide.load.b;
import com.google.a.a.a.a.a.a;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: SafeKeyGenerator */
class j {
    private final e<b, String> a = new e<>(1000);

    j() {
    }

    public String a(b bVar) {
        String str;
        synchronized (this.a) {
            str = (String) this.a.b(bVar);
        }
        if (str == null) {
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-256");
                bVar.a(instance);
                str = h.a(instance.digest());
            } catch (UnsupportedEncodingException e) {
                a.a(e);
            } catch (NoSuchAlgorithmException e2) {
                a.a(e2);
            }
            synchronized (this.a) {
                this.a.b(bVar, str);
            }
        }
        return str;
    }
}
