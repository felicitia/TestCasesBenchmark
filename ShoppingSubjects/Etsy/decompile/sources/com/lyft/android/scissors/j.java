package com.lyft.android.scissors;

/* compiled from: TouchPoint */
class j {
    private float a;
    private float b;

    public j() {
    }

    public j(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public float a() {
        return this.a;
    }

    public float b() {
        return this.b;
    }

    public float c() {
        return (float) Math.sqrt((double) ((this.a * this.a) + (this.b * this.b)));
    }

    public j a(j jVar) {
        this.a = jVar.a();
        this.b = jVar.b();
        return this;
    }

    public j a(float f, float f2) {
        this.a = f;
        this.b = f2;
        return this;
    }

    public j b(j jVar) {
        this.a += jVar.a();
        this.b += jVar.b();
        return this;
    }

    public static j a(j jVar, j jVar2) {
        return new j(jVar.a - jVar2.a, jVar.b - jVar2.b);
    }

    public String toString() {
        return String.format("(%.4f, %.4f)", new Object[]{Float.valueOf(this.a), Float.valueOf(this.b)});
    }
}
