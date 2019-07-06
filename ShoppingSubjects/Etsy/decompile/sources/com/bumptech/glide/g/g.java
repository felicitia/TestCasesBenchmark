package com.bumptech.glide.g;

/* compiled from: MultiClassKey */
public class g {
    private Class<?> a;
    private Class<?> b;

    public g() {
    }

    public g(Class<?> cls, Class<?> cls2) {
        a(cls, cls2);
    }

    public void a(Class<?> cls, Class<?> cls2) {
        this.a = cls;
        this.b = cls2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MultiClassKey{first=");
        sb.append(this.a);
        sb.append(", second=");
        sb.append(this.b);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        g gVar = (g) obj;
        return this.a.equals(gVar.a) && this.b.equals(gVar.b);
    }

    public int hashCode() {
        return (31 * this.a.hashCode()) + this.b.hashCode();
    }
}
