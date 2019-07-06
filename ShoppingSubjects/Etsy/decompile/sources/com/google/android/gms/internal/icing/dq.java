package com.google.android.gms.internal.icing;

import android.accounts.Account;
import java.util.ArrayList;
import java.util.List;

public final class dq {
    private List<zzl> a;
    private String b;
    private boolean c;
    private Account d;

    public final dq a(zzl zzl) {
        if (this.a == null && zzl != null) {
            this.a = new ArrayList();
        }
        if (zzl != null) {
            this.a.add(zzl);
        }
        return this;
    }

    public final dq a(String str) {
        this.b = str;
        return this;
    }

    public final dq a(boolean z) {
        this.c = true;
        return this;
    }

    public final zzg a() {
        return new zzg(this.b, this.c, this.d, this.a != null ? (zzl[]) this.a.toArray(new zzl[this.a.size()]) : null);
    }
}
