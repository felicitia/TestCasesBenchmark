package com.google.zxing.datamatrix.encoder;

import com.google.zxing.a;
import java.nio.charset.Charset;

/* compiled from: EncoderContext */
final class h {
    int a;
    private final String b;
    private SymbolShapeHint c;
    private a d;
    private a e;
    private final StringBuilder f;
    private int g;
    private k h;
    private int i;

    h(String str) {
        byte[] bytes = str.getBytes(Charset.forName("ISO-8859-1"));
        int i2 = 0;
        StringBuilder sb = new StringBuilder(bytes.length);
        int length = bytes.length;
        while (i2 < length) {
            char c2 = (char) (bytes[i2] & 255);
            if (c2 != '?' || str.charAt(i2) == '?') {
                sb.append(c2);
                i2++;
            } else {
                throw new IllegalArgumentException("Message contains characters outside ISO-8859-1 encoding.");
            }
        }
        this.b = sb.toString();
        this.c = SymbolShapeHint.FORCE_NONE;
        this.f = new StringBuilder(str.length());
        this.g = -1;
    }

    public void a(SymbolShapeHint symbolShapeHint) {
        this.c = symbolShapeHint;
    }

    public void a(a aVar, a aVar2) {
        this.d = aVar;
        this.e = aVar2;
    }

    public String a() {
        return this.b;
    }

    public void a(int i2) {
        this.i = i2;
    }

    public char b() {
        return this.b.charAt(this.a);
    }

    public StringBuilder c() {
        return this.f;
    }

    public void a(String str) {
        this.f.append(str);
    }

    public void a(char c2) {
        this.f.append(c2);
    }

    public int d() {
        return this.f.length();
    }

    public int e() {
        return this.g;
    }

    public void b(int i2) {
        this.g = i2;
    }

    public void f() {
        this.g = -1;
    }

    public boolean g() {
        return this.a < l();
    }

    private int l() {
        return this.b.length() - this.i;
    }

    public int h() {
        return l() - this.a;
    }

    public k i() {
        return this.h;
    }

    public void j() {
        c(d());
    }

    public void c(int i2) {
        if (this.h == null || i2 > this.h.f()) {
            this.h = k.a(i2, this.c, this.d, this.e, true);
        }
    }

    public void k() {
        this.h = null;
    }
}
