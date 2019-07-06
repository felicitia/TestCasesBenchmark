package com.onfido.d.a;

import java.util.List;

public final class d {
    private final byte[] a;
    private int b;
    private final String c;
    private final List<byte[]> d;
    private final String e;
    private Integer f;
    private Integer g;
    private Object h;
    private final int i;
    private final int j;

    public d(byte[] bArr, String str, List<byte[]> list, String str2) {
        this(bArr, str, list, str2, -1, -1);
    }

    public d(byte[] bArr, String str, List<byte[]> list, String str2, int i2, int i3) {
        this.a = bArr;
        this.b = bArr == null ? 0 : bArr.length * 8;
        this.c = str;
        this.d = list;
        this.e = str2;
        this.i = i3;
        this.j = i2;
    }

    public void a(Integer num) {
        this.f = num;
    }

    public void a(Object obj) {
        this.h = obj;
    }

    public byte[] a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public void b(Integer num) {
        this.g = num;
    }

    public String c() {
        return this.e;
    }

    public Object d() {
        return this.h;
    }
}
