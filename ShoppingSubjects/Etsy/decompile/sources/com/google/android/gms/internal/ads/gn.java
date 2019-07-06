package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.ao;

@bu
public final class gn {
    private final Object a;
    private int b;
    private int c;
    private final go d;
    private final String e;

    private gn(go goVar, String str) {
        this.a = new Object();
        this.d = goVar;
        this.e = str;
    }

    public gn(String str) {
        this(ao.j(), str);
    }

    public final String a() {
        return this.e;
    }

    public final void a(int i, int i2) {
        synchronized (this.a) {
            this.b = i;
            this.c = i2;
            this.d.a(this);
        }
    }

    public final Bundle b() {
        Bundle bundle;
        synchronized (this.a) {
            bundle = new Bundle();
            bundle.putInt("pmnli", this.b);
            bundle.putInt("pmnll", this.c);
        }
        return bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        gn gnVar = (gn) obj;
        return this.e != null ? this.e.equals(gnVar.e) : gnVar.e == null;
    }

    public final int hashCode() {
        if (this.e != null) {
            return this.e.hashCode();
        }
        return 0;
    }
}
