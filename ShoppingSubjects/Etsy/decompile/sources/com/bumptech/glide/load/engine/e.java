package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.c.c;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* compiled from: EngineKey */
class e implements b {
    private final String a;
    private final int b;
    private final int c;
    private final d d;
    private final d e;
    private final f f;
    private final com.bumptech.glide.load.e g;
    private final c h;
    private final a i;
    private final b j;
    private String k;
    private int l;
    private b m;

    public e(String str, b bVar, int i2, int i3, d dVar, d dVar2, f fVar, com.bumptech.glide.load.e eVar, c cVar, a aVar) {
        this.a = str;
        this.j = bVar;
        this.b = i2;
        this.c = i3;
        this.d = dVar;
        this.e = dVar2;
        this.f = fVar;
        this.g = eVar;
        this.h = cVar;
        this.i = aVar;
    }

    public b a() {
        if (this.m == null) {
            this.m = new h(this.a, this.j);
        }
        return this.m;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        if (!this.a.equals(eVar.a) || !this.j.equals(eVar.j) || this.c != eVar.c || this.b != eVar.b) {
            return false;
        }
        if ((this.f == null) ^ (eVar.f == null)) {
            return false;
        }
        if (this.f != null && !this.f.a().equals(eVar.f.a())) {
            return false;
        }
        if ((this.e == null) ^ (eVar.e == null)) {
            return false;
        }
        if (this.e != null && !this.e.a().equals(eVar.e.a())) {
            return false;
        }
        if ((this.d == null) ^ (eVar.d == null)) {
            return false;
        }
        if (this.d != null && !this.d.a().equals(eVar.d.a())) {
            return false;
        }
        if ((this.g == null) ^ (eVar.g == null)) {
            return false;
        }
        if (this.g != null && !this.g.a().equals(eVar.g.a())) {
            return false;
        }
        if ((this.h == null) ^ (eVar.h == null)) {
            return false;
        }
        if (this.h != null && !this.h.a().equals(eVar.h.a())) {
            return false;
        }
        if ((this.i == null) ^ (eVar.i == null)) {
            return false;
        }
        return this.i == null || this.i.a().equals(eVar.i.a());
    }

    public int hashCode() {
        if (this.l == 0) {
            this.l = this.a.hashCode();
            this.l = (this.l * 31) + this.j.hashCode();
            this.l = (this.l * 31) + this.b;
            this.l = (this.l * 31) + this.c;
            int i2 = 0;
            this.l = (this.l * 31) + (this.d != null ? this.d.a().hashCode() : 0);
            this.l = (this.l * 31) + (this.e != null ? this.e.a().hashCode() : 0);
            this.l = (this.l * 31) + (this.f != null ? this.f.a().hashCode() : 0);
            this.l = (this.l * 31) + (this.g != null ? this.g.a().hashCode() : 0);
            this.l = (this.l * 31) + (this.h != null ? this.h.a().hashCode() : 0);
            int i3 = 31 * this.l;
            if (this.i != null) {
                i2 = this.i.a().hashCode();
            }
            this.l = i3 + i2;
        }
        return this.l;
    }

    public String toString() {
        if (this.k == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("EngineKey{");
            sb.append(this.a);
            sb.append('+');
            sb.append(this.j);
            sb.append("+[");
            sb.append(this.b);
            sb.append('x');
            sb.append(this.c);
            sb.append("]+");
            sb.append('\'');
            sb.append(this.d != null ? this.d.a() : "");
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            sb.append(this.e != null ? this.e.a() : "");
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            sb.append(this.f != null ? this.f.a() : "");
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            sb.append(this.g != null ? this.g.a() : "");
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            sb.append(this.h != null ? this.h.a() : "");
            sb.append('\'');
            sb.append('+');
            sb.append('\'');
            sb.append(this.i != null ? this.i.a() : "");
            sb.append('\'');
            sb.append('}');
            this.k = sb.toString();
        }
        return this.k;
    }

    public void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
        byte[] array = ByteBuffer.allocate(8).putInt(this.b).putInt(this.c).array();
        this.j.a(messageDigest);
        messageDigest.update(this.a.getBytes("UTF-8"));
        messageDigest.update(array);
        messageDigest.update((this.d != null ? this.d.a() : "").getBytes("UTF-8"));
        messageDigest.update((this.e != null ? this.e.a() : "").getBytes("UTF-8"));
        messageDigest.update((this.f != null ? this.f.a() : "").getBytes("UTF-8"));
        messageDigest.update((this.g != null ? this.g.a() : "").getBytes("UTF-8"));
        messageDigest.update((this.i != null ? this.i.a() : "").getBytes("UTF-8"));
    }
}
