package com.google.firebase.components;

import com.google.firebase.a.a;
import com.google.firebase.a.b;
import java.util.Map.Entry;

final /* synthetic */ class l implements Runnable {
    private final Entry a;
    private final a b;

    l(Entry entry, a aVar) {
        this.a = entry;
        this.b = aVar;
    }

    public final void run() {
        ((b) this.a.getKey()).a(this.b);
    }
}
