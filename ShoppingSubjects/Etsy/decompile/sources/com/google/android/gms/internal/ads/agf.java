package com.google.android.gms.internal.ads;

import android.view.View;

final class agf implements Runnable {
    private final /* synthetic */ View a;
    private final /* synthetic */ age b;

    agf(age age, View view) {
        this.b = age;
        this.a = view;
    }

    public final void run() {
        this.b.a(this.a);
    }
}
