package com.onfido.d.b.a;

final class d {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private int e = -1;

    d(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    /* access modifiers changed from: 0000 */
    public boolean a() {
        return a(this.e);
    }

    /* access modifiers changed from: 0000 */
    public boolean a(int i) {
        return i != -1 && this.c == (i % 3) * 3;
    }

    /* access modifiers changed from: 0000 */
    public void b() {
        this.e = ((this.d / 30) * 3) + (this.c / 3);
    }

    /* access modifiers changed from: 0000 */
    public void b(int i) {
        this.e = i;
    }

    /* access modifiers changed from: 0000 */
    public int c() {
        return this.b - this.a;
    }

    /* access modifiers changed from: 0000 */
    public int d() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public int e() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public int f() {
        return this.c;
    }

    /* access modifiers changed from: 0000 */
    public int g() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public int h() {
        return this.e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.e);
        sb.append("|");
        sb.append(this.d);
        return sb.toString();
    }
}
