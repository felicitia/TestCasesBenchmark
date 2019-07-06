package org.scribe.model;

import org.scribe.e.b;

/* compiled from: Parameter */
public class d implements Comparable<d> {
    private final String a;
    private final String b;

    public d(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return b.a(this.a).concat("=").concat(b.a(this.b));
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (dVar.a.equals(this.a) && dVar.b.equals(this.b)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return this.a.hashCode() + this.b.hashCode();
    }

    /* renamed from: a */
    public int compareTo(d dVar) {
        int compareTo = this.a.compareTo(dVar.a);
        return compareTo != 0 ? compareTo : this.b.compareTo(dVar.b);
    }
}
