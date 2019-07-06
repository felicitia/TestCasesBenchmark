package com.etsy.android.ui.convos.convolistredesign;

/* compiled from: ConvosListSpec.kt */
public final class n {
    public static final a a = new a(null);
    private final int b;
    private final int c;

    /* compiled from: ConvosListSpec.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof n) {
            n nVar = (n) obj;
            if (this.b == nVar.b) {
                if (this.c == nVar.c) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return (this.b * 31) + this.c;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConvosListSpec(offset=");
        sb.append(this.b);
        sb.append(", limit=");
        sb.append(this.c);
        sb.append(")");
        return sb.toString();
    }

    public n(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    public final int b() {
        return this.b;
    }

    public final int c() {
        return this.c;
    }

    public final n a() {
        return new n(this.b + this.c, this.c);
    }
}
