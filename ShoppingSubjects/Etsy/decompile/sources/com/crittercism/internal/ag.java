package com.crittercism.internal;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public final class ag extends v {
    private int d = -1;

    /* access modifiers changed from: protected */
    public final int d() {
        return 20;
    }

    /* access modifiers changed from: protected */
    public final int e() {
        return 64;
    }

    public ag(ac acVar) {
        super(acVar);
    }

    public final v b() {
        return new af(this, this.d);
    }

    public final v c() {
        return aj.d;
    }

    public final boolean a(w wVar) {
        String[] split = wVar.toString().split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (split.length >= 3) {
            try {
                this.d = Integer.parseInt(split[1]);
                this.a.a(this.d);
                return true;
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }
}
