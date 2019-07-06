package com.crittercism.internal;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public final class ae extends v {
    /* access modifiers changed from: protected */
    public final int d() {
        return 64;
    }

    /* access modifiers changed from: protected */
    public final int e() {
        return 2048;
    }

    public ae(ac acVar) {
        super(acVar);
    }

    public final v b() {
        return new ad(this);
    }

    public final v c() {
        return aj.d;
    }

    public final boolean a(w wVar) {
        String[] split = wVar.toString().split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (split.length != 3) {
            return false;
        }
        this.a.a(split[0], split[1]);
        return true;
    }
}
