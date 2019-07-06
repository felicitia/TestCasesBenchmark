package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map.Entry;

final class cg extends cm {
    private final /* synthetic */ cd a;

    private cg(cd cdVar) {
        this.a = cdVar;
        super(cdVar, null);
    }

    /* synthetic */ cg(cd cdVar, ce ceVar) {
        this(cdVar);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new cf(this.a, null);
    }
}
