package com.etsy.android.ui.convos.convoredesign;

import kotlin.jvm.internal.p;

/* compiled from: ConvoDraftDbModel.kt */
public final class n {
    private final long a;
    private final String b;
    private final String c;
    private final String d;
    private final int e;
    private final int f;
    private final String g;
    private final int h;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof n) {
            n nVar = (n) obj;
            if ((this.a == nVar.a) && p.a((Object) this.b, (Object) nVar.b) && p.a((Object) this.c, (Object) nVar.c) && p.a((Object) this.d, (Object) nVar.d)) {
                if (this.e == nVar.e) {
                    if ((this.f == nVar.f) && p.a((Object) this.g, (Object) nVar.g)) {
                        if (this.h == nVar.h) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.a;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        String str = this.b;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.c;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.d;
        int hashCode3 = (((((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.e) * 31) + this.f) * 31;
        String str4 = this.g;
        if (str4 != null) {
            i2 = str4.hashCode();
        }
        return ((hashCode3 + i2) * 31) + this.h;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConvoDraftDbModel(id=");
        sb.append(this.a);
        sb.append(", message=");
        sb.append(this.b);
        sb.append(", subject=");
        sb.append(this.c);
        sb.append(", userName=");
        sb.append(this.d);
        sb.append(", selectionStart=");
        sb.append(this.e);
        sb.append(", selectionEnd=");
        sb.append(this.f);
        sb.append(", imageFilePaths=");
        sb.append(this.g);
        sb.append(", status=");
        sb.append(this.h);
        sb.append(")");
        return sb.toString();
    }

    public final long a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final int e() {
        return this.e;
    }

    public final int f() {
        return this.f;
    }

    public final String g() {
        return this.g;
    }

    public final int h() {
        return this.h;
    }
}
